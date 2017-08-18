package com._520it.crm.service.impl;

import com._520it.crm.domain.Calendar;
import com._520it.crm.mapper.CalendarMapper;
import com._520it.crm.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/7/19.
 */
@Service
public class CalendarServiceImpl implements ICalendarService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Override
    public int insert(Calendar record) {
        return calendarMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return calendarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Calendar record) {
        return 0;
    }

    @Override
    public Calendar selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Calendar> selectAll() {
        return calendarMapper.selectAll();
    }
}
