package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

//教室
@Setter
@Getter
@ObjectProp("教室管理")
public class Schoolroom {
	public static final int NORMAL = 0;//未用
	public static final int LEAVE = 1;//已用
	private Long id;
	@ObjectProp("教室名称")
	private String name;
	@ObjectProp("教室地址")
	private String address;
	@ObjectProp("座位数")
	private Integer seatnumber;
	@ObjectProp("教室状态")
	private Integer state;
	@ObjectProp("所属学院")
	private Institute institute;
}
