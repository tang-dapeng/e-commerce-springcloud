package com.imooc.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.imooc.ecommerce.account.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_ecommerce_address")
public class EcommerceAddress {
    /** 指定主键生成策略使用雪花算法（默认策略）*/
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 用户ID */
    @TableField("user_id")
    private String userId;

    /** 用户名 */
    private String username;

    /** 电话 */
    @TableField("phone")
    private String phone;

    /** 省 */
    @TableField("province")
    private String province;

    /** 市 */
    @TableField("city")
    private String city;

    /** 详细地址 */
    @TableField("address_detail")
    private String addressDetail;

    /** 创建时间 */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * <h2>根据 userId + AddressItem 得到 EcommerceAddress</h2>
     * */
    public static EcommerceAddress to(String userId, AddressInfo.AddressItem addressItem) {

        EcommerceAddress ecommerceAddress = new EcommerceAddress();

        ecommerceAddress.setUserId(userId);
        ecommerceAddress.setUsername(addressItem.getUsername());
        ecommerceAddress.setPhone(addressItem.getPhone());
        ecommerceAddress.setProvince(addressItem.getProvince());
        ecommerceAddress.setCity(addressItem.getCity());
        ecommerceAddress.setAddressDetail(addressItem.getAddressDetail());

        ecommerceAddress.setCreateTime(addressItem.getCreateTime());
        ecommerceAddress.setUpdateTime(addressItem.getUpdateTime());

        return ecommerceAddress;
    }

    /**
     * <h2>将 EcommerceAddress 对象转成 AddressInfo</h2>
     * */
    public  AddressInfo.AddressItem toAddressItem() {

        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();

        addressItem.setId(this.id);
        addressItem.setUsername(this.username);
        addressItem.setPhone(this.phone);
        addressItem.setProvince(this.province);
        addressItem.setCity(this.city);
        addressItem.setAddressDetail(this.addressDetail);
        addressItem.setCreateTime(this.createTime);
        addressItem.setUpdateTime(this.updateTime);

        return addressItem;
    }
}
