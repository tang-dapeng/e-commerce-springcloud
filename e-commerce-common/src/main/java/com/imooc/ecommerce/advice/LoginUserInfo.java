package com.imooc.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录用户信息
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo {
    /** 用户id */
    private String id;

    /** 用户名 */
    private String username;
}
