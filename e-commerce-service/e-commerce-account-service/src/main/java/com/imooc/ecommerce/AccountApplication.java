package com.imooc.ecommerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户账户微服务启动入口
 * @author tangdapeng
 * @description
 * @create 2022/05/20
 */
//开启swaggerUI注解 扫描config文件夹
@MapperScan("com.imooc.ecommerce.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
    }
}

