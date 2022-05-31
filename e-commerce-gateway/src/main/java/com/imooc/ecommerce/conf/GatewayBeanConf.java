package com.imooc.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/17
 */
@Configuration
public class GatewayBeanConf {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
