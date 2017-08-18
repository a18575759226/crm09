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
@ObjectProp("学员流失")
public class TurnoverStudent {
    private Long id;
    @ObjectProp("学生名称")
    private String studentName;
    @ObjectProp("QQ")
    private String qq;
    @ObjectProp("电话")
    private String phone;
    @ObjectProp("上课天数")
    private Integer schoolDate;
    @ObjectProp("流失班级")
    private String turnoverClass;
    @ObjectProp("流失阶段")
    private String turnoverPhase;
    @ObjectProp("流失原因")
    private String turnoverCause;
    @ObjectProp("流失时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date turnoverDate = new Date();
    @ObjectProp("营售人员")
    private String campPersonnel;
    @ObjectProp("录入人员")
    private String enteringPersonnel;
    @ObjectProp("是否退款")
    private String whetherReund = "否";
    @ObjectProp("状态")
    private String state = "流失学员";
    private CustomerInfo rec_tur;
}
