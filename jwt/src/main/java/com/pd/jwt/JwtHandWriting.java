package com.pd.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

/**
 * 手写JWT
 *
 * @author: pd
 * @date: 2021-02-19 上午11:21
 */
public class JwtHandWriting {

  public static void main(String[] args) {
    String SIGN_KEY = "pendd";

    // 加密
    // 第一部分 header
    ObjectNode header = new ObjectMapper().createObjectNode();
    header.put("alg", "HS256");

    // 第二部分 payload
    ObjectNode payload = new ObjectMapper().createObjectNode();
    payload.put("phone", 123456789);

    // 第三部分 sign组成：payload 进行md5加密
    String sign = DigestUtils.md5Hex(payload.toString() + SIGN_KEY);

    String jwt =
        Base64.getEncoder().encodeToString(header.toString().getBytes())
            + "."
            + Base64.getEncoder().encodeToString(payload.toString().getBytes())
            + "."
            + sign;

    System.out.println(jwt);

    // 解密
    String[] split = jwt.split("\\.");
    String decHeader = new String(Base64.getDecoder().decode(split[0].getBytes()));
    String decPayload = new String(Base64.getDecoder().decode(split[1].getBytes()));

    // 后台验证通过md5加盐值去对传过来解析后的payload加密 和 传过来的第三部分进行比对，如果一致，说明jwt没有被串改
    System.out.println(DigestUtils.md5Hex(decPayload + SIGN_KEY).equals(split[2]));
    System.out.println(decPayload);
  }
}
