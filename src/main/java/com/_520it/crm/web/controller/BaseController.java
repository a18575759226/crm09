package com._520it.crm.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @date 2017/7/12
 */
public class BaseController {
    @ExceptionHandler(UnauthorizedException.class)//捕获授权异常
    public void handlerException(HandlerMethod method, HttpServletResponse response){
        try {
            if (method != null) {
                ResponseBody annotation = method.getMethodAnnotation(ResponseBody.class);
                if (annotation != null) {//异步请求
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write("{\"success\":false,\"msg\":\"您没有操作权限!\"}");
                }else {//请求页面
                    response.sendRedirect("/noPermission.jsp");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
