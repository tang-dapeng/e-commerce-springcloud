package com.imooc.ecommerce.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.annotation.IgnoreResponseAdvice;
import com.imooc.ecommerce.service.IJWTService;
import com.imooc.ecommerce.vo.JwtToken;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Slf4j
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Resource
    private IJWTService ijwtService;

    /**
     * 从授权中心获取token（其实就是登录功能），且返回信息中没有统一响应的包装
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    @IgnoreResponseAdvice
    @PostMapping("/token")
    public JwtToken token(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        log.info("request to get token with param: [{}]", JSON.toJSONString(usernameAndPassword));
        return new JwtToken(ijwtService.generateToken(usernameAndPassword.getUsername(),usernameAndPassword.getPassword()));
    }

    /**
     * 注册用户并返回当前注册用户的token，即通过授权中心创建用户
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    @IgnoreResponseAdvice
    @PostMapping("/register")
    public JwtToken register(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        log.info("request to get token with param: [{}]", JSON.toJSONString(usernameAndPassword));
        return new JwtToken(ijwtService.registerUserAndPassword(usernameAndPassword));
    }

}