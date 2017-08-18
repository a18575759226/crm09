package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by asus on 2017/07/16.
 */
@Setter
@Getter
@ObjectProp("升班留级")
public class Goupto {
    private Long id;
    @ObjectProp("真实姓名")
    private String realName;
    @ObjectProp("总学费")
    private String receiptAmount;
    @ObjectProp("销售流水")
    private String salesWater;
    @ObjectProp("其他费用")
    private String otherCosts;
    @ObjectProp("升班/留级时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date receiptTime;
    @ObjectProp("QQ")
    private String qq;
    @ObjectProp("联系电话")
    private String phone;
    @ObjectProp("以前的班级")
    private String oldClass;
    @ObjectProp("流入的班级")
    private String intoClass;
    @ObjectProp("营售人员")
    private String salesman;
    @ObjectProp("状态")
    private String state;
}
