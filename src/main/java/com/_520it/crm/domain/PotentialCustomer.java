package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
@Setter@Getter@ObjectProp("资源池客户")
public class PotentialCustomer {
    private Long id;
    @ObjectProp("客户姓名")
    private String name;
    @ObjectProp("客户年龄")
    private Integer age;
    @ObjectProp("客户性别")
    private int gender;
    @ObjectProp("客户电话")
    private String tel;
    @ObjectProp("客户邮箱")
    private String email;
    @ObjectProp("客户qq")
    private String qq;
    @ObjectProp("客户来源")
    private SystemDictionaryItem customerSource;
    @ObjectProp("负责人")
    private Employee inChargeUser;
    @ObjectProp("录入人")
    private Employee inputUser;
    @ObjectProp("录入时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputTime;
    @ObjectProp("历史id")
    private Long customerInfo_id;
}
