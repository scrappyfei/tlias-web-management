package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "itheima";
    private static Long expire = 43200000L;  //12小时

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey) //指定签名算法和密钥
                .addClaims(claims) //设置令牌中要保存的数据(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //令牌的有效期
                .compact();//生成令牌字符串
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) //指定签名密钥，要与生存jwt使用的密钥相同
                .parseClaimsJws(jwt) //解析令牌
                .getBody(); ; //获取之前保存的数据
        return claims;
    }
}
