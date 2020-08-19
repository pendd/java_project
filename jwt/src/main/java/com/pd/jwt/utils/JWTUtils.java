package com.pd.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

/**
 * @author dpeng
 */
public class JWTUtils {

    // 盐值
    private static final String SIGN = "!Q@W#E$E%T6G&U*E(";

    // 生成token  header.payload.sign
    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        // 1min后过期
        instance.add(Calendar.MINUTE, 1);

        // 创建jwt builder
        Builder builder = JWT.create();

        // payload
        map.forEach(builder::withClaim);

        // 指定令牌的过期时间
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    // 验证token
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
