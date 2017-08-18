package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户实体类
 * @author
 * @date 2017/7/16
 */
@Setter@Getter@ObjectProp("客户管理")
public class CustomerInfo {

    public static final int ZERO = 0;
    public static final int ONE = 1;

    @ObjectProp("主键")
    private Long id;
    @ObjectProp("客户姓名")
    private String name;
    @ObjectProp("生日")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    @ObjectProp("年龄")
    private Integer age;
    @ObjectProp("身份证")
    private String identityCard;
    @ObjectProp("性别")
    private Integer gender;
    @ObjectProp("电话")
    private String tel;
    @ObjectProp("qq")
    private String qq;
    @ObjectProp("邮箱")
    private String email;
    @ObjectProp("学历")
    private SystemDictionaryItem educationalHistory;
    @ObjectProp("专业")
    private SystemDictionaryItem major;
    @ObjectProp("工作状态")
    private SystemDictionaryItem workingCondition;
    @ObjectProp("工作地址")
    private String address;
    @ObjectProp("客户来源")
    private SystemDictionaryItem customerSource;
    @ObjectProp("介绍人")
    private String introducer;
    @ObjectProp("意向学科")
    private SystemDictionaryItem intentionDiscipline;
    @ObjectProp("意向学校")
    private SystemDictionaryItem intentionSchool;
    @ObjectProp("地域")
    private SystemDictionaryItem territory;
    @ObjectProp("意向程度")
    private SystemDictionaryItem intentionDegree;
    @ObjectProp("状态")
    private int state=ZERO;//给一个默认值 默认情况下的为潜在学员
    @ObjectProp("约访时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date appointmentTime;
    @ObjectProp("付款方式")
    private int paymentWay=ZERO;//给一个默认值 默认情况下为分期付款
    @ObjectProp("是否交押金")
    private int isPledge=ZERO;//给一个默认值 默认情况为已经交押金
    @ObjectProp("录入时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputTime;
    @ObjectProp("录入员工")
    private Employee inputUser;
    @ObjectProp("客户关注问题")
    private String attentionMatter;
    @ObjectProp("负责人")
    private Employee principal;
    @ObjectProp("所属班级")
    private  SchoolClass classId;
    private String way ;//交费方式
    private BigDecimal allTuitiong  ;//总学费
    private BigDecimal surplus ;//待交学费
    private BigDecimal yetPay =new BigDecimal(0) ;//已交学费
    private String payState ="未缴清" ; //交费状态

}
