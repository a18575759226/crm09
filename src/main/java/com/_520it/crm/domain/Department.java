package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("部门管理")
public class Department {
    private Long id;
    @ObjectProp("部门编号")
    private String sn;
    @ObjectProp("部门名称")
    private String name;
    @ObjectProp("部门状态")
    private int state;
}