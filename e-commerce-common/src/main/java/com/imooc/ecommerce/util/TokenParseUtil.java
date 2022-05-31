package com.imooc.ecommerce.util;

import cn.hutool.core.codec.Base64Decoder;
import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * JWT Token 解析工具类
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/15
 */
@Slf4j
public class TokenParseUtil {

    /**
     * 从JWT Token 中解析 LoginUserInfo 对象
     * @param token
     * @return
     */
    public static LoginUserInfo parseUserInfoFrom(String token) throws Exception {
        if (null == token) {
            return null;
        }
        PublicKey publicKey = getPublicKey();
        Jws<Claims> claimsJws = parseToken(token,publicKey);
        Claims body = claimsJws.getBody();

        // 如果 Token 已经过期了，返回null
        if(body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }

        // 返回 Token 中保存的用户信息
        return JSON.parseObject(
                body.get(CommonConstant.JWT_USER_INFO_KEY).toString(),
                LoginUserInfo.class
        );

    }

    /**
     * 通过公钥去解析JWT Token
     * @param token
     * @param publicKey
     * @return
     */
    public static Jws<Claims> parseToken(String token,PublicKey publicKey) {
       return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 根据本地存储的公钥获取到 PublicKey 对象
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY));
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }
}

