package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by asus on 2017/07/17.
 */
@Setter
@Getter
@ObjectProp("支付管理")
public class Expend {
    private Long id;
    @ObjectProp("支付时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expendTime;
    @ObjectProp("支付金额")
    private String expendAmount;
    @ObjectProp("摘要")
    private String digest;
    @ObjectProp("出纳")
    private String cashier;
    @ObjectProp("经手人")
    private String handlerPerson;
    @ObjectProp("支付方式")
    private String expendMethod;
    @ObjectProp("支付类型")
    private String expendType;
    @ObjectProp("小类")
    private String subclass;
    @ObjectProp("单据号")
    private int documentNumber;
    @ObjectProp("共享费用")
    private int shareCost;
    @ObjectProp("分摊类型")
    private String apportionmentType;
    @ObjectProp("归属学科")
    private String subject;
    @ObjectProp("审核")
    private String audit;
}
