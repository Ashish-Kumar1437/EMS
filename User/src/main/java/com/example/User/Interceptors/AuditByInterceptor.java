package com.example.User.Interceptors;

import com.example.User.Utils.AuditUtil;
import com.example.User.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuditByInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtUtil.extractToken(request);
        if(token != null)
            AuditUtil.actionBy = jwtUtil.extractUsername(token);
        return true;
    }
}
