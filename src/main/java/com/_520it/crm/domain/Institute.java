package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("学院管理")
public class Institute {
	private Long id;
	@ObjectProp("学院编号")
	private String sn;
	@ObjectProp("学院名称")
	private String name;
}
