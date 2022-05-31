package com.imooc.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用响应对象类型
 * {
 *     "code": 0,
 *     "message": '',
 *     "data": {}
 * }
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    /** 返回码，200表示成功 */
    private Integer code = 200;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T data;

    public CommonResponse(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonResponse success(String message) {
        return new CommonResponse(200,message);
    }

    public static <T> CommonResponse<T> success(T data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(data);
        return commonResponse;
    }

    public static <T> CommonResponse<T> success(String message,T data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        return commonResponse;
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse(500,message);
    }


    public static CommonResponse fail(Integer code,String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        return commonResponse;
    }

    public static <T> CommonResponse<T> fail(Integer code, String message, T data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setData(data);
        return commonResponse;
    }


}
