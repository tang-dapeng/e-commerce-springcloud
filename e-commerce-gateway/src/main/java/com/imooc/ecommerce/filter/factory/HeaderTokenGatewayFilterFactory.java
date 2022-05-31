package com.imooc.ecommerce.filter.factory;

import com.imooc.ecommerce.filter.HeaderTokenGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.PrefixPathGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 请求头部携带 token 验证过滤器 工厂类
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/16
 */
@Component
public class HeaderTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new HeaderTokenGatewayFilter();
    }
}
