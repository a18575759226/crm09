package com._520it.crm.vo;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Created by Administrator
 * on 2017/7/18.
 */
@Getter
@Setter
@ToString
@ObjectProp("考勤导出")
public class CheckOut {
    private Long id;
    private String name;//签到人姓名
    private int state;//状态
    private int times;//次数
}
