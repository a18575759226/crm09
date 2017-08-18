package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter@ObjectProp("任务安排")
public class Task {

    private Long id;
    @ObjectProp("日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    @ObjectProp("处理人")
    private Employee processor;
    @ObjectProp("任务描述")
    private String taskDescription;
    @ObjectProp("处理描述")
    private String processDescription;
    @ObjectProp("状态")
    private int state;
    //处理人所在的部门id
    private Long deptId;
}
