package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter@ObjectProp("管理报表")
public class LatentNumber {
    private Long id;
    @ObjectProp("录入时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputTime;
    @ObjectProp("销售人员")
    private String marketingMan;
    @ObjectProp("客户数量")
    private Integer clientNumber;
}
