package com.imooc.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 工程启动入口
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/11
 */
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }
}
