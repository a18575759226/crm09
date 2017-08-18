package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 大客户实体类
 * @author
 * @date 2017/7/16
 */
@Setter@Getter@ObjectProp("大客户")
public class BigCustomer {
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("客户名称")
    private String name;
    @ObjectProp("负责人")
    private Employee salesman;
    @ObjectProp("办学性质")
    private SystemDictionaryItem schoolProperty;
    @ObjectProp("星级")
    private SystemDictionaryItem starLevel;
    @ObjectProp("学制")
    private SystemDictionaryItem curriculum;
    @ObjectProp("合作高校")
    private int cooperationSchool;
    @ObjectProp("实训记录")
    private List<TrainingRecords> trainingRecords = new ArrayList<>();
}
