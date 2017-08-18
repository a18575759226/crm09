package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author
 * @date 2017/7/13
 */
@Setter@Getter@ToString@ObjectProp("系统日志")
public class SystemLog {
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("操作人")
    private Employee opUser;
    @ObjectProp("操作时间")
    private Date opTime;
    @ObjectProp("登录ip")
    private String opIp;
    @ObjectProp("使用功能")
    private String function;
    @ObjectProp("操作参数信息")
    private String params;

}
