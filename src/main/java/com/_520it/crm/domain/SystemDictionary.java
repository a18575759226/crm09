package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator
 * on 2017/7/16.
 */
@Setter@Getter@ObjectProp("字典目录")
public class SystemDictionary {
    private Long id ;
    @ObjectProp("字典目录编号")
    private String sn;
    @ObjectProp("字典目录名称")
    private String name;
    @ObjectProp("字典目录简介")
    private String intro;
}
