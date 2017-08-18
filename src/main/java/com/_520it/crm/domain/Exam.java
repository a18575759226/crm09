package com._520it.crm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("考试管理")
public class Exam {
	public static final int ZERO = 0;//表示考试结果还未处理
	public static final int ONE = 1;//表示考试通过
	public static final int TWO = 2;//表示考试不通过
	private Long id;
	@ObjectProp("考试科目")
	private String coursename;
	@ObjectProp("考试编号")
	private String sn;
	@ObjectProp("考试学员")
	private CustomerInfo customerInfo;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@ObjectProp("考试时间")
	private Date examDate;
	@ObjectProp("考试结果")
	private Integer examResult = ZERO;//默认情况下还未处理
	@ObjectProp("备注")
	private String remark;
	private Schoolroom schoolroom;//考试地址
	@ObjectProp("处理人")
	private Employee employee;
	
}
