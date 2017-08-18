package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 学校联系人实体类
 * @author
 * @date 2017/7/16
 */
@Setter@Getter@ObjectProp("学校联系人")
public class SchoolContact {
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("联系人姓名")
    private String name;
    @ObjectProp("性别")
    private SystemDictionaryItem gender;
    @ObjectProp("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date birthday;
    @ObjectProp("联系电话")
    private String tel;
    @ObjectProp("QQ")
    private String qq;
    @ObjectProp("邮箱")
    private String email;
    @ObjectProp("所属学校")
    private BigCustomer school;
    @ObjectProp("特殊爱好")
    private String hobby;
}
