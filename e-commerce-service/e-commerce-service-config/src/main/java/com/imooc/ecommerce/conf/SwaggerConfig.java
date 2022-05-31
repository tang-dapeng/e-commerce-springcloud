package com.imooc.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置类
 * 原生:/swagger-ui.html
 * 美化:/doc.html
 * @author tangdapeng
 * @description
 * @create 2022/05/20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger 实例 Bean 是Docket，所以通过配置 Docket 实例类配置
     * @return
     */
    @Bean
    public Docket docket(Environment environment) {
        //设置要使用的环境中
        Profiles profiles = Profiles.of("dev");
        //通过方法  判断自己项目所使用的的环境  在不在这里的指定的环境里 如果在就能访问的到 不在就访问不到
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                // 展示在 Swagger 页面上的自定义工程描述信息
                .apiInfo(apiInfo())
                // 通过方法  判断自己项目所使用的的环境  在不在这里的指定的环境里 如果在就能访问的到 不在就访问不到
                .enable(flag)
                // 选择展示哪些接口
                .select()
                // 只有 com.imooc.ecommerce 包内的才去展示
                .apis(RequestHandlerSelectors.basePackage("com.imooc.ecommerce"))
                .paths(PathSelectors.any())
                .build();

    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("imooc-micro-service")
                .description("e-commerce-service")
                .contact(new Contact("tangdapeng","www.baidu.com","1"))
                .version("1.0")
                .build();
    }

}
