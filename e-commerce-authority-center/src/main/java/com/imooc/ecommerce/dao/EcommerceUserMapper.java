package com.imooc.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.ecommerce.entity.EcommerceUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * EcommerceUser Dao 接口定义类
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Mapper
public interface EcommerceUserMapper extends BaseMapper<EcommerceUser> {
    /**
     *
     * @param username
     * @return
     */
    EcommerceUser findByUsername(String username);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    EcommerceUser findByUsernameAndPassword(String username,String password);
}
