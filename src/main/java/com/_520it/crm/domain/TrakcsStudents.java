package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter@ObjectProp("学员跟进")
public class TrakcsStudents {

    private Long id;
    @ObjectProp("跟进时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date inputTime;
    @ObjectProp("跟进学员")
    private CustomerInfo customerInfo;
    @ObjectProp("跟进人员")
    private Employee employee;
    @ObjectProp("下次跟进时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date nextTime;
    @ObjectProp("跟进目的")
    private String purpose;
    @ObjectProp("意向程度")
    private SystemDictionaryItem intentionDegree;
    @ObjectProp("交流方式")
    private SystemDictionaryItem talkWay;
    @ObjectProp("学员qq")
    private String qq;
    @ObjectProp("学员电话")
    private String tel;
    @ObjectProp("交谈时间")
    private Integer longTime;
    @ObjectProp("摘要")
    private String digest;

    //上传的聊天记录
    private String picture;
}
