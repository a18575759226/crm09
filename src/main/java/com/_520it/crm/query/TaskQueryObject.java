package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class TaskQueryObject extends QueryObject {

    //对应的员工的ID
    private Long empId;

    //对应的部门ID
    private Long deptId;

}
