package com.imooc.ecommerce.filter;

import com.imooc.ecommerce.constant.GatewayConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 缓存请求 body 的全局过滤器
 * Spring webFlux
 * @author tangdapeng
 * @description
 * @create 2022/05/16
 */
@Slf4j
@Component
public class GlobalCacheRequestBodyFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        boolean isLoginOrRegister = exchange.getRequest().getURI().getPath().contains(GatewayConstant.LOGIN_URI)
                || exchange.getRequest().getURI().getPath().contains(GatewayConstant.REGISTER_URI);
        if (null == exchange.getRequest().getHeaders().getContentType() || !isLoginOrRegister) {
            return chain.filter(exchange);
        }
        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
            // 确保数据缓冲区不被释放，必须要 DataBufferUtils.ratain
            DataBufferUtils.retain(dataBuffer);

            // defer,just 都是去创建数据源，得到当前数据的副本
            Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));

            // 重新包装 ServerHttpRequest,重写 getBody 方法，能够返回请求数据
            ServerHttpRequest mutatedRuquest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }
            };

            // 将包装之后的 ServerHttpRequest 向下继续传递
            return chain.filter(exchange.mutate().request(mutatedRuquest).build());
        });
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE +1;
    }
}
