package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 潜在客户移交记录表
 * @author dongc
 * @date 2017/7/17
 */
@Getter@Setter@ObjectProp("客户移交记录")
public class TransferLog {
    public static final int underReview = 0;//审核中
    public static final int approve = 1;//审核通过
    public static final int noApprove = 2;//审核不通过
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("潜在客户")
    private CustomerInfo customer;
    @ObjectProp("当前责任人")
    private Employee currentPrincipal;
    @ObjectProp("接收人")
    private Employee accepter;
    @ObjectProp("审核人")
    private Employee checker;
    @ObjectProp("备注")
    private String remark;
    @ObjectProp("审核状态")
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ObjectProp("移交时间")
    private Date overTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ObjectProp("审核时间")
    private Date checkTime;
}
