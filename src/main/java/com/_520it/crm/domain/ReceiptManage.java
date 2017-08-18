package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JINZHAOYU on 2017/7/17.
 */
@Setter@Getter@ObjectProp("收款管理")
public class ReceiptManage {
    private Long id ;
    @ObjectProp("学员")
    private CustomerInfo student;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ObjectProp("收款时间")
    private Date gatherTime;
    @ObjectProp("收款金额")
    private BigDecimal getherSum ;
    @ObjectProp("所属班级")
    private SchoolClass room ;
    @ObjectProp("收款人")
    private  Employee receiver ;
    @ObjectProp("收款方法")
    private String way ;
    @ObjectProp("收款类型")
    private String gatheringType ;
    @ObjectProp("收款单据号")
    private String bills ;
    @ObjectProp("开票")
    private Integer invoice ;
    @ObjectProp("学科")
    private  SystemDictionaryItem subject ;
    @ObjectProp("备注")
    private String remark ;
    @ObjectProp("营销人员")
    private Employee salesman ;
   /* @ObjectProp("班级变动")
    private  String change;*/

}
