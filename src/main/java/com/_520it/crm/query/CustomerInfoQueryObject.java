package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
public class CustomerInfoQueryObject extends QueryObject {

    private int state=0;

    //高级查询的条件
    private String name;//姓名
    private Integer gender;//性别
    private Integer age;//年龄
    private String tel;//电话
    private String qq;//qq
    private String email;//邮箱
    private String intentionDisciplineName;//意向学科
    private String intentionDegreeName;//意向程度
    private String intentionSchoolName;//意向学校
    private String introducer;//介绍人
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginAppointmentTime;//约访时间(起)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endAppointmentTime;//约访时间(终)
    private String customerSourceName;//客户来源
    private String territoryName;//地域
    private String adress;//地址
    private Long inputUserId;//负责人
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginInputTime;//录入时间(起)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endInputTime;//录入时间(终)
    private String  keyword;

    private Long classId;
}
