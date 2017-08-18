package com._520it.crm.service;
import com._520it.crm.domain.Calendar;
import java.util.List;

public interface ICalendarService {
    int insert(Calendar record);
    int deleteByPrimaryKey(Long id);
    int updateByPrimaryKey(Calendar record);
    Calendar selectByPrimaryKey(Long id);
    List<Calendar> selectAll();
}
