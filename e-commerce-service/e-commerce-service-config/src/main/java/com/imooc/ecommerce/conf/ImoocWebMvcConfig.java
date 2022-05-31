package com.imooc.ecommerce.conf;

import com.imooc.ecommerce.filter.LoginUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Wev Mvc 配置
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/20
 */
@Configuration
public class ImoocWebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 添加拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 添加用户身份统一登录拦截的拦截器
        registry.addInterceptor(new LoginUserInfoInterceptor())
                .addPathPatterns("/**")
                .order(0);
    }

    /**
     * 让 MVC 加载 Swagger 的静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }
}
