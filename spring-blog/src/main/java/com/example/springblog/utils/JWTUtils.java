package com.example.springblog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;
@Slf4j
public class JWTUtils {
    private static final long JWT_EXPIRATION = 600*600*1000; //过期时间
    private static final String secretStr = "Le++o8NQWVXWo3+SJtAtnjBW9iA0OvPL0c0mMrol2fU=";
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretStr));
    /**
     * 生成token
     */
    public static String genJwtToken(Map<String, Object> claim){
        String token = Jwts.builder().setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .signWith(key)
                .compact();
        return token;
    }
    /**
     * 校验token
     * Claims 为 null 表示校验失败
     */

    public static Claims parseToken(String token){
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        }catch (Exception e){
            log.error("解析token失败, token:{}", token);
            return null;
        }
        return claims;
    }

}
