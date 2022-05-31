package com.imooc.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MinioApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class,args);
    }
}
