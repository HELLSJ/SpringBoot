package com.example.springblog;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;


public class JWTUtilsTest {
    @Test
    public void genJwt(){
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 1);
        claim.put("name", "zhangsan");
        String token = Jwts.builder().setClaims(claim).compact();
        System.out.println(token);
    }
}
