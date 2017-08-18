package com._520it.crm.util;


import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemLog;
import com._520it.crm.service.ISystemLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * log的切面类
 * @author
 * @date 2017/7/13
 */
public class LogAspect {
    @Setter
    private ISystemLogService systemLogService;

    public void writeLog(JoinPoint joinpoint){
        SystemLog log = new SystemLog();
        log.setOpTime(new Date());
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        log.setOpUser(currentUser);
        String function = joinpoint.getTarget().getClass().getName() + ":" + joinpoint.getSignature().getName();
        log.setFunction(function);
        //将参数转换为json数据保存在数据库中
        ObjectMapper mapper = new ObjectMapper();
        try {
            String params = mapper.writeValueAsString(joinpoint.getArgs());
            log.setParams(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //用springMVC中的工具类获取到session数据,因为是springMVC调用这个方法,所有我们不能手动创建request
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //获取操作人的ip
        log.setOpIp(request.getRemoteAddr());
        systemLogService.insert(log);
    }
}
