package com._520it.crm.mapper;



import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import com._520it.crm.domain.Calendar;


import java.util.List;

public interface CalendarMapper {
    int insert(Calendar record);
    int deleteByPrimaryKey(Long id);
    int updateByPrimaryKey(Calendar record);
    Calendar selectByPrimaryKey(Long id);
    List<Calendar> selectAll();
}