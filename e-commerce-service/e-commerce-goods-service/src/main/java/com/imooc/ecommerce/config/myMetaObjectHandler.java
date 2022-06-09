package com.imooc.ecommerce.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充处理类
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/11
 */
@Component
public class myMetaObjectHandler implements MetaObjectHandler {

    @Override    //在执行mybatisPlus的insert()时，为我们自动给某些字段填充值，这样的话，我们就不需要手动给insert()里的实体类赋值了
    public void insertFill(MetaObject metaObject) {
        //其中方法参数中第一个是前面自动填充所对应的字段，第二个是要自动填充的值。第三个是指定实体类的对象
//        IdaasUser sysUser = AuthUtil.getUserInfo();
//        this.setFieldValByName("creater", sysUser.getId(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("modifier", sysUser.getId(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override//在执行mybatisPlus的update()时，为我们自动给某些字段填充值，这样的话，我们就不需要手动给update()里的实体类赋值了
    public void updateFill(MetaObject metaObject) {
//        IdaasUser sysUser = AuthUtil.getUserInfo();
//        this.setFieldValByName("modifier", sysUser.getId(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
