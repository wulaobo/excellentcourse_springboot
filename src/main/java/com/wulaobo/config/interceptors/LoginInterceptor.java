package com.wulaobo.config.interceptors;

import com.wulaobo.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//判断用户是否登录的拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        User user = (User) request.getSession().getAttribute("user");
        User admin = (User) request.getSession().getAttribute("admin");
        if(user==null && admin==null) {
            response.sendRedirect("/toLogin");
        }else{
            flag = true;
        }

        return flag;
    }
}
