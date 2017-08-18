package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 实训记录实体
 * @author dongc
 * @date 2017/7/16
 */
@Setter@Getter@ObjectProp("实训记录")
public class TrainingRecords {
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("实训时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trainingTime;
    @ObjectProp("实训内容")
    private String trainingContent;
    @ObjectProp("实训地址")
    private String address;
    @ObjectProp("实训效果")
    private String trainingResult;
    @ObjectProp("备注")
    private String remark;
    @ObjectProp("大客户")
    private BigCustomer bigCustomer;
}
