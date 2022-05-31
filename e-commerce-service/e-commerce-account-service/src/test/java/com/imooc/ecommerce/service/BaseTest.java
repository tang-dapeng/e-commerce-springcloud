package com.imooc.ecommerce.service;

import com.imooc.ecommerce.filter.AccessContext;
import com.imooc.ecommerce.vo.LoginUserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 基础测试
 *
 * @author MI
 * @date 2022/05/28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTest {
    protected final LoginUserInfo loginUserInfo = new LoginUserInfo("tangdapeng","123456");

    /**
     * 初始化
     */
    @Before
    public void init() {
        AccessContext.setLoginUserInfo(loginUserInfo);
    }

    /**
     * 毁坏
     */
    @After
    public void destory() {
        AccessContext.clearLoginUserInfo();
    }
}
