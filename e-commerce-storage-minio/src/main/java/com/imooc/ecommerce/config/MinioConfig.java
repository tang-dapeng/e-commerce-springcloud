package com.imooc.ecommerce.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * minio配置
 *
 * @author tangdapeng
 * @date 2022/05/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfig {
    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 秘密密钥
     */
    private String secretKey;

    /**
     * url
     */
    private String url;

    /**
     * bucket名称
     */
    private String bucketName;

    /**
     * minio客户
     *
     * @return {@link MinioClient}
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
