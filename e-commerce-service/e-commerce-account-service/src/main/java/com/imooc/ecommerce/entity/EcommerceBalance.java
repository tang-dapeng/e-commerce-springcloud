package com.imooc.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户账户余额表实体类定义
 * @author tangdapeng
 * @description
 * @create 2022/05/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_ecommerce_balance")
public class EcommerceBalance {
    /** 指定主键生成策略使用雪花算法（默认策略）*/
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 用户 id */
    private String userId;

    /** 账户余额 */
    private Long balance;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
