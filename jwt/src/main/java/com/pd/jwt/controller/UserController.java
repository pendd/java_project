package com.pd.jwt.controller;

import com.pd.jwt.entity.User;
import com.pd.jwt.service.UserService;
import com.pd.jwt.utils.JWTUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dpeng
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        log.info("用户名：[{}]", user.getName());
        log.info("密码：[{}]", user.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        try{
            User userDB = service.login(user);

            Map<String, String> payload = new HashMap<>();
            payload.put("id", user.getId().toString());
            payload.put("name", user.getName());

            // 生成JWT的令牌
            String token = JWTUtils.getToken(payload);

            map.put("state", true);
            map.put("msg", "认证成功");
            // 响应token 给前端
            map.put("token", token);
        }catch (Exception e) {
            map.put("state", false);
            map.put("msg", "认证失败");
        }
        return map;
    }
}
