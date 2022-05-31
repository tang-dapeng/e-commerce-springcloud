package com.imooc.ecommerce.service;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.account.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.DateTime.now;

/**
 * 用户地址相关服务功能测试
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/26
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest extends BaseTest {
    @Resource
    private IAddressService addressService;

    /**
     * 测试创建地址信息
     */
    @Test
    public void testCreateAddressInfo() {
        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();
        addressItem.setUsername("tangdapeng");
        addressItem.setPhone("18166299329");
        addressItem.setProvince("湖南省");
        addressItem.setCity("长沙市");
        addressItem.setAddressDetail("洋湖");
        addressItem.setCreateTime(new Date());
        addressItem.setUpdateTime(new Date());

        List<AddressInfo> list = new ArrayList<>();

        log.info("test create address info: [{}]", JSON.toJSONString(
                addressService.createAddressInfo(new AddressInfo(
                        loginUserInfo.getId(), Collections.singletonList(addressItem)))
                )
        );
    }
}
