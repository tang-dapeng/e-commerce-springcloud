package com.imooc.ecommerce.vo;

import com.imooc.ecommerce.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一响应
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/10
 */
@RestControllerAdvice
@SuppressWarnings("all")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 类上有IgnoreResponseAdvice注解
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 方法上有IgnoreResponseAdvice注解
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param body body要写入的主体
     * @param methodParameter 控制器方法的返回类型
     * @param mediaType 通过内容协商选择的内容类型
     * @param aClass 选择写入响应的转换器类型
     * @param serverHttpRequest request当前请求
     * @param serverHttpResponse response当前响应
     * @return 传入的主体或修改过的(可能是新的)实例
     */
    @Override
    public  CommonResponse<Object> beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(0,"");
        if(null == body) {
            return response;
        }else if(body instanceof CommonResponse) {
            response =  (CommonResponse<Object>) body;
        }else {
            response.setData(body);
        }
        return response;
    }
}
