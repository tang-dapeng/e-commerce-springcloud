package com.imooc.ecommerce.service;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.imooc.ecommerce.dao.EcommerceUserMapper;
import com.imooc.ecommerce.entity.EcommerceUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * EcommerceUser 相关测试类
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EcommerceUserTest {
    @Resource
    private EcommerceUserMapper ecommerceUserMapper;

    @Test
    public void createUserRecord() {
        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setUsername("tangdapeng22");
        ecommerceUser.setPassword("123456");
        ecommerceUser.setExtraInfo("{}");
        log.info("save user: [{}]",ecommerceUserMapper.insert(ecommerceUser));
    }
}
