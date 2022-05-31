package com.imooc.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_ecommerce_user")
public class EcommerceUser {
    /** 自增主键 */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /** 用户名 */
    private String username;

    /** MD5 密码 */
    private String password;

    /** 额外的信息, json 字符串存储 */
    private String extraInfo;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

