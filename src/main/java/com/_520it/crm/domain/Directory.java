package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹实体类
 * @author
 * @date 2017/7/18
 */
@Getter@Setter@ObjectProp("文件夹管理")
public class Directory {
    public static final int isFile = 0;//文件类型
    public static final int isDirectory = 1;//文件夹类型
    @ObjectProp("主键")
    private Long id;
    @ObjectProp("文件夹路径")
    private String dicPath;
    @ObjectProp("文件名称")
    private String name;
    @ObjectProp("上级目录")
    private Directory parent;
    @ObjectProp("文件类型")
    private int fileType;
    @ObjectProp("文件状态")
    private int fileState;
    @ObjectProp("图标")
    private String icon;
    private List<Employee> employees = new ArrayList<>();
}
