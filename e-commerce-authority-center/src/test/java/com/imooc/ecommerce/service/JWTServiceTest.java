package com.imooc.ecommerce.service;

import com.imooc.ecommerce.util.TokenParseUtil;
import com.imooc.ecommerce.vo.LoginUserInfo;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/15
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTServiceTest {
    @Resource
    private IJWTService ijwtService;

    @Test
    public void testGenerateAndParseToken() throws Exception {
        String jwtToken = ijwtService.generateToken("tangdapeng", "123456");
        log.info("jwt token is: [{}]",jwtToken);

        LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFrom(jwtToken);
        log.info("parse token: [{}]",loginUserInfo);
    }

    @Test
    public void testRegisterUsername() throws Exception {
        String jwtToken = ijwtService.registerUserAndPassword(new UsernameAndPassword("tangdapeng","123456"));
        log.info("jwt token is: [{}]",jwtToken);

        LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFrom(jwtToken);
        log.info("parse token: [{}]",loginUserInfo);
    }
}
