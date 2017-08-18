package com._520it.crm.domain;
//课程表

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ObjectProp("课程安排")
public class Courselist {
	private Long id;
	@ObjectProp("课程")
	private String course;
	@ObjectProp("日期")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	@ObjectProp("星期")
	private Integer weekday;
	@ObjectProp("课程名称")
	private String coursename;
	@ObjectProp("班级")
	private SchoolClass schoolclass;
	@ObjectProp("班主任")
	private Employee headTeacher;
	@ObjectProp("任课老师")
	private Employee teacher;
	@ObjectProp("教室")
	private Schoolroom schoolroom;
	@ObjectProp("备注")
	private String remark;
	@ObjectProp("状态")
	private Integer state;
}
