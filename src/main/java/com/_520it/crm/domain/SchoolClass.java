package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

//班级管理
@Getter
@Setter
@ObjectProp("班级管理")
public class SchoolClass {
 	private Long id;
 	@ObjectProp("班级名称")
 	private String name;
 	@ObjectProp("所属学院")
 	private Institute institute;
 	@ObjectProp("班主任")
 	private Employee employee;
 	@ObjectProp("所在教室")
 	private Schoolroom schoolroom;
}
