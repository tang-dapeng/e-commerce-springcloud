package com.imooc.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，读取 Nacos 相关的配置项，用户配置监听器
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/15
 */
@Configuration
public class GatewayConfig {
    /** 默认超时时间 */
    public static final long DEFAULT_TIMEOUT = 3000;

    /** nacos 服务器地址 */
    public static String NACOS_SERVER_ADDR;

    /** 命名空间 */
    public static String NACOS_NAMESPACE;

    /** data-id */
    public static String NACOS_ROUTE_DATA_ID;

    /** 分组id */
    public static String NACOS_GROUP;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr) {
        NACOS_SERVER_ADDR = nacosServerAddr;
    }

    @Value("${spring.cloud.nacos.discovery.namespace}")
    public void setNacosNamespace(String nacosNamespace) {
        NACOS_NAMESPACE = nacosNamespace;
    }


    @Value("${nacos.gateway.route.config.data-id}")
    public void setNacosRouteDataId(String nacosRouteDataId) {
        NACOS_ROUTE_DATA_ID = nacosRouteDataId;
    }


    @Value("${nacos.gateway.route.config.group}")
    public void setNacosGroup(String nacosGroup) {
        NACOS_GROUP = nacosGroup;
    }
}
