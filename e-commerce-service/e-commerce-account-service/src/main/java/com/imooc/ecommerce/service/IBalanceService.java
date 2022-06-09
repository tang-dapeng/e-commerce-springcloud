package com.imooc.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.ecommerce.account.BalanceInfo;
import com.imooc.ecommerce.entity.EcommerceBalance;

/**
 * 用于余额相关的接口定义
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/23
 */
public interface IBalance extends IService<EcommerceBalance> {
    /**
     * 获取当前用户的余额信息
     * @return
     */
    BalanceInfo getCurrentUserBalance();

    /**
     * 扣除用户余额
     * @param balanceInfo 代表想要扣减的余额
     * @return
     */
    BalanceInfo deductBalance(BalanceInfo balanceInfo);


}
