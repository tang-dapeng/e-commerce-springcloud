package com.imooc.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.ecommerce.account.AddressInfo;
import com.imooc.ecommerce.common.TableId;
import com.imooc.ecommerce.dao.EcommerAddressMapper;
import com.imooc.ecommerce.entity.EcommerceAddress;
import com.imooc.ecommerce.filter.AccessContext;
import com.imooc.ecommerce.service.IAddressService;
import com.imooc.ecommerce.util.TokenParseUtil;
import com.imooc.ecommerce.vo.CommonResponse;
import com.imooc.ecommerce.vo.JwtToken;
import com.imooc.ecommerce.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/23
 */
@Slf4j
@Service
public class AddressServiceImpl extends ServiceImpl<EcommerAddressMapper, EcommerceAddress> implements IAddressService {

    /**
     * 创建地址信息
     *
     * @param addressInfo 地址信息
     * @return {@link TableId}
     */
    @Override
    public TableId


    createAddressInfo(AddressInfo addressInfo) {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        List<EcommerceAddress> ecommerceAddress = addressInfo.getAddressItems().stream()
                .map(item -> EcommerceAddress.to(loginUserInfo.getId(),item))
                .collect(Collectors.toList());
        boolean b = saveBatch(ecommerceAddress);
        List<String> ids = ecommerceAddress.stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());
        log.info("create address info: [{}],[{}]",loginUserInfo.getId(), JSON.toJSONString(ids));
        return new TableId(
                ids.stream().map(TableId.Id::new).collect(Collectors.toList())
        );
    }

    /**
     * 获取当前地址信息
     *
     * @return {@link AddressInfo}
     */
    @Override
    public AddressInfo getCurrentAddressInfo() {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        LambdaQueryWrapper<EcommerceAddress> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EcommerceAddress::getUserId,loginUserInfo.getId());
        List<EcommerceAddress> ecommerceAddresses = this.baseMapper.selectList(lqw);
        List<AddressInfo.AddressItem> addressItem = ecommerceAddresses.stream()
                .map(EcommerceAddress::toAddressItem)
                .collect(Collectors.toList());
        return new AddressInfo(loginUserInfo.getId(),addressItem);
    }

    /**
     * 通过id获取地址信息
     *
     * @param id id
     * @return {@link AddressInfo}
     */
    @Override
    public AddressInfo getAddressInfoById(String id) {
        LambdaQueryWrapper<EcommerceAddress> lqw = new LambdaQueryWrapper<>();
        EcommerceAddress ecommerceAddress = this.baseMapper.selectById(id);
        if (null == ecommerceAddress) {
            throw new RuntimeException("address is not exist");
        }
        return new AddressInfo(
                ecommerceAddress.getUserId(),
                Collections.singletonList(ecommerceAddress.toAddressItem()));
    }

    /**
     * 被表id地址信息
     *
     * @param tableId 表id
     * @return {@link AddressInfo}
     */
    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {
        LambdaQueryWrapper<EcommerceAddress> lqw = new LambdaQueryWrapper<>();
        List<EcommerceAddress> ecommerceAddresses = this.baseMapper.selectBatchIds(tableId.getIds().stream()
                        .map(TableId.Id::toString)
                        .collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(ecommerceAddresses)) {
            return new AddressInfo("-1", Collections.emptyList());
        }
        List<AddressInfo.AddressItem> addressItem = ecommerceAddresses.stream()
                .map(EcommerceAddress::toAddressItem)
                .collect(Collectors.toList());
        return new AddressInfo(ecommerceAddresses.get(0).getUserId(),addressItem);
    }
}
