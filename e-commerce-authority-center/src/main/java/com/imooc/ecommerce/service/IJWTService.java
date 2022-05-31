package com.imooc.ecommerce.service;

import com.imooc.ecommerce.vo.UsernameAndPassword;

/**
 * JWT 相关服务接口定义
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
public interface IJWTService {
    /**
     * 生成 JWT Token，使用默认的超时时间
     * @param username 用户名
     * @param password 密码
     * @return Token
     */
    String generateToken(String username,String password) throws Exception;

    /**
     * 生成指定超时时间的 Token
     * @param username
     * @param password
     * @param expire
     * @return
     */
    String generateToken(String username,String password,int expire) throws Exception;

    /**
     * 注册用户并生成 Token
     * @param usernameAndPassword
     * @return
     */
    String registerUserAndPassword(UsernameAndPassword usernameAndPassword) throws Exception;



}

