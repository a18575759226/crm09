package com._520it.crm.filter;

import com._520it.crm.domain.SystemMenu;
import com._520it.crm.service.ISystemMenuService;
import com._520it.crm.util.DiskUtils;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author
 * @date 2017/7/12
 */
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {
    @Setter
    private ISystemMenuService systemMenuService;
    /**
     * 登录成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        List<SystemMenu> systemMenus = systemMenuService.indexMenu();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        DiskUtils.mkUserRootDic();//创建用户网盘跟目录
        SecurityUtils.getSubject().getSession().setAttribute("SYSTEMMENUS_IN_SESSION",systemMenus);
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("{\"success\":true,\"msg\":\"登录成功\"}");
        out.flush();
        out.close();
        //返回false表示不再拦截后面的拦截器和请求
        return false;
    }

    /**
     * 登录失败
     * @param token
     * @param e 登录的异常信息
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            }else if("AuthenticationException".equals(message)){
                out.println("{\"success\":false,\"msg\":\"认证失败\"}");
            }else {
                out.println("{\"success\":false,\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//返回false,表示不再执行后面的拦截器和请求
        return false;
    }
}
