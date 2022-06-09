package com.imooc.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权中心鉴权之后给客户端的token
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    /** JWT */
    private String token;
}
