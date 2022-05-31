package com.imooc.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.ecommerce.account.AddressInfo;
import com.imooc.ecommerce.common.TableId;
import com.imooc.ecommerce.entity.EcommerceAddress;

/**
 * 用户地址相关服务接口定义
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/23
 */
public interface IAddressService extends IService<EcommerceAddress> {
    /**
     * <h2>创建用户地址信息</h2>
     * */
    TableId createAddressInfo(AddressInfo addressInfo);

    /**
     * <h2>获取当前登录的用户地址信息</h2>
     * */
    AddressInfo getCurrentAddressInfo();

    /**
     * <h2>通过 id 获取用户地址信息, id 是 EcommerceAddress 表的主键</h2>
     * */
    AddressInfo getAddressInfoById(String id);

    /**
     * <h2>通过 TableId 获取用户地址信息</h2>
     * */
    AddressInfo getAddressInfoByTableId(TableId tableId);
}
