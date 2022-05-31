package com.imooc.ecommerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/13
 */
@RefreshScope
@EnableDiscoveryClient
@MapperScan("com.imooc.ecommerce.dao")
@SpringBootApplication
public class AuthorityCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class,args);
    }
}
