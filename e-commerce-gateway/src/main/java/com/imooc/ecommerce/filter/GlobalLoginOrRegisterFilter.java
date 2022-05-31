package com.imooc.ecommerce.filter;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.conf.GatewayBeanConf;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.constant.GatewayConstant;
import com.imooc.ecommerce.util.TokenParseUtil;
import com.imooc.ecommerce.vo.JwtToken;
import com.imooc.ecommerce.vo.LoginUserInfo;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 登录注册全局过滤器
 * @author tangdapeng
 * @description
 * @create 2022/05/17
 */
@Slf4j
@Component
public class GlobalLoginOrRegisterFilter implements GlobalFilter, Ordered {
    @Resource
    private  LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;

    /**
     * 登录、注册、鉴权
     * 1.如果是登录或注册，则去授权中心拿到 token 并返回给客户端
     * 2.如果访问其他服务，则鉴权，没有权限，则返回401
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String token = "";

        //1. 如果是登录
        if (request.getURI().getPath().contains(GatewayConstant.LOGIN_URI)) {
            token = getTokenFromAuthorityCenter(request,GatewayConstant.AUTHORITY_CENTER_TOKEN_URL_FORMAT);
            response.getHeaders().add(
                    CommonConstant.JWT_USER_INFO_KEY,
                    token == null ? "null" : token
                    );
            response.setStatusCode(HttpStatus.OK);
            return response.setComplete();
        }

        //2. 如果是注册
        if (request.getURI().getPath().contains(GatewayConstant.REGISTER_URI)) {
            // 去授权中心拿到 token： 先创建用户，再返回 token
            token = getTokenFromAuthorityCenter(request,GatewayConstant.AUTHORITY_CENTER_REGISTER_URL_FORMAT);
            response.getHeaders().add(
                    CommonConstant.JWT_USER_INFO_KEY,
                    token == null ? "null" : token
            );
            response.setStatusCode(HttpStatus.OK);
            return response.setComplete();
        }

        //3. 访问其他服务，则鉴权，校验是否能够从 Token 中解析出用户信息
        HttpHeaders headers = request.getHeaders();
        token = headers.getFirst(CommonConstant.JWT_USER_INFO_KEY);
        LoginUserInfo loginUserInfo = null;
        try {
            loginUserInfo = TokenParseUtil.parseUserInfoFrom(token);
            // 获取不到登录用户信息，返回401
            if (null == loginUserInfo) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            log.info(">>>>>>>>loginUserInfo>>>>>>>>>>>>>: [{}]", loginUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse user info form token error: [{}]", e.getMessage());
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 解析通过，则放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }

    /**
     * 从授权中心获取 Token
     * @param request
     * @param uriFormat
     * @return
     */
    private String getTokenFromAuthorityCenter(ServerHttpRequest request,String uriFormat) {

        // service id 就是服务名字，负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose(CommonConstant.AUTHORITY_CENTER_SERVICE_ID);
        log.info(">>>>>Nacos Client Info：[{}],[{}],[{}]",
                serviceInstance.getServiceId(),
                serviceInstance.getInstanceId(),
                JSON.toJSONString(serviceInstance.getMetadata()));
        String requestUrl = String.format(
                uriFormat,
                serviceInstance.getHost(),
                serviceInstance.getPort()
        );
        UsernameAndPassword requestBody = JSON.parseObject(parseBodyFormRequest(request),UsernameAndPassword.class);
        log.info("login request url and body: [{}]",JSON.toJSONString(requestBody));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JwtToken token = restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(requestBody),headers),
                JwtToken.class
        );
        if (null != token) {
            return token.getToken();
        }
        return null;
    }

    /**
     * 从 Post 请求中获取到请求数据
     * @param request
     * @return
     */
    private String parseBodyFormRequest(ServerHttpRequest request) {
        Flux<DataBuffer> body = request.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();

        // 订阅缓冲区去消费消息体中的数据
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            // 一定要使用 DataBufferUtils.relese 释放掉，否则会出现内存泄漏
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return  bodyRef.get();
    }
}
