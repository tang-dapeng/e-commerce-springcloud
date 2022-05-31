package com.imooc.ecommerce.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求头部携带 token 验证过滤器
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/16
 */
public class HeaderTokenGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从HTTP Header中寻找key为token的，value为imooc 的键值对
        String name = exchange.getRequest().getHeaders().getFirst("token");
        if ("imooc".equals(name)) {
            return chain.filter(exchange);
        }

        // 标记此次请求没有权限，并结束这次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }
}
