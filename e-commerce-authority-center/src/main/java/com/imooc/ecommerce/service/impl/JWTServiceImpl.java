package com.imooc.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.ecommerce.constant.AuthorityConstant;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.dao.EcommerceUserMapper;
import com.imooc.ecommerce.entity.EcommerceUser;
import com.imooc.ecommerce.service.IJWTService;
import com.imooc.ecommerce.vo.LoginUserInfo;
import com.imooc.ecommerce.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * JWT相关服务接口实现
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements IJWTService {
    @Resource
    private EcommerceUserMapper ecommerceUserMapper;

    @Override
    public String generateToken(String username, String password) throws Exception {
        return generateToken(username,password,0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {
//        EcommerceUser ecommerceUser = ecommerceUserMapper.findByUsernameAndPassword(username,password);
        EcommerceUser ecommerceUser = ecommerceUserMapper.selectOne(
                new LambdaQueryWrapper<EcommerceUser>()
                        .eq(EcommerceUser::getUsername,username).eq(EcommerceUser::getPassword,MD5Utils.encodeHexString(password.getBytes())));
        if (null == ecommerceUser) {
            log.error("can not find user: [{}] [{}]",username,password);
        }

        LoginUserInfo loginUserInfo = new LoginUserInfo(ecommerceUser.getId(),ecommerceUser.getUsername());
        if (expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS).atStartOfDay(ZoneId.systemDefault());
        log.info(">>>>>>>>>>>>>>zonedDateTime: [{}]>>>>>>>>>>>>>>>>>>>",zdt);
        Date expitreDate = Date.from(zdt.toInstant());
        log.info(">>>>>>>>>>>>>>expitreDate: [{}]>>>>>>>>>>>>>>>>>>>",expitreDate);
        SignatureAlgorithm alg = SignatureAlgorithm.forSigningKey(getPrivateKey());
        return Jwts.builder()
                // jwt payload --> KV
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 超时时间
                .setExpiration(expitreDate)
                // jwt 签名 --> 加密
                .signWith(getPrivateKey(), alg)
                .compact();
    }

    @Override
    public String registerUserAndPassword(UsernameAndPassword usernameAndPassword) throws Exception {
//        EcommerceUser oldUser = ecommerceUserMapper.findByUsername(usernameAndPassword.getUsername());
        EcommerceUser oldUser = ecommerceUserMapper.selectOne(
                new LambdaQueryWrapper<EcommerceUser>()
                        .eq(EcommerceUser::getUsername,usernameAndPassword.getUsername()));
        if(null != oldUser) {
            log.error("username is registered: [{}]", oldUser.getUsername());
            return null;
        }

        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setUsername(usernameAndPassword.getUsername());
        ecommerceUser.setPassword(MD5Utils.encodeHexString(usernameAndPassword.getPassword().getBytes()));
        ecommerceUser.setExtraInfo("{}");

        // 注册一个新用户
        ecommerceUserMapper.insert(ecommerceUser);
        log.info("register user success: [{}] [{}]",ecommerceUser.getUsername(),ecommerceUser.getId());

        // 生成 Token 并返回
        return generateToken(usernameAndPassword.getUsername(),usernameAndPassword.getPassword());
    }

    /**
     * 根据本地存储的私钥获取到 PrivateKey 对象
     * @return
     */
    private PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }
}
