package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator
 * on 2017/7/16.
 */
@Setter
@Getter
@ObjectProp("考勤")@ToString
public class CheckTerm {
    public static final int NORMAL = 1;//正常上班
    public static final int LATE = 0;//状态:迟到早退
    @ObjectProp("编号")
    private Long id;
    @ObjectProp("姓名")
    private String name;
    @ObjectProp("签到IP")
    private String signIp;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ObjectProp("签到时间")
    private Date signInTime;
    @ObjectProp("签退时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signOutTime;
    @ObjectProp("状态")
    private int state;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ObjectProp("补签时间")
    private Date patchTime;
    private Employee e;
}
