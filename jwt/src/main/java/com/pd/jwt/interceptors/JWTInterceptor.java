package com.pd.jwt.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.jwt.utils.JWTUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author dpeng
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map<String, Object> map = new HashMap<>();

        // 获取Header中的令牌
        String token = request.getHeader("token");

        try{
            // 验证令牌
            JWTUtils.verify(token);
            // 放行请求
            return true;
        }catch (SignatureVerificationException e) {
            map.put("msg", "无效签名!");
        }catch (TokenExpiredException e) {
            map.put("msg", "token过期!");
        }catch (AlgorithmMismatchException e) {
            map.put("msg", "token算法不一致!");
        }catch (Exception e) {
            map.put("msg", "token无效!");
        }
        map.put("state", false);

        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
        return false;
    }
}
