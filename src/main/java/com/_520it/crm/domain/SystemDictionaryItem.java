package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator
 * on 2017/7/16.
 */
@Setter@Getter@ObjectProp("字典目录")
public class SystemDictionaryItem {
    private Long id ;
    @ObjectProp("字典目录")
    private SystemDictionary parent;
    @ObjectProp("字典明细名称")
    private String name;
    @ObjectProp("字典明细简介")
    private String intro;
}
