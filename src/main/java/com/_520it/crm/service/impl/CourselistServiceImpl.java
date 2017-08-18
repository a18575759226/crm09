package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Courselist;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SchoolClass;
import com._520it.crm.mapper.CourselistMapper;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.mapper.SchoolClassMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ICourselistService;
@Service
public class CourselistServiceImpl implements ICourselistService {
	@Autowired
	private CourselistMapper courselistMapper;
	@Autowired
	private SchoolClassMapper schoolClassMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return courselistMapper.deleteByPrimaryKey(id);
	}

	public int insert(Courselist record) {
		record.setWeekday(record.getDate().getDay());
		//获取班级的id
		Long classId = record.getSchoolclass().getId();
		//根据班级的id获取班主任的id
		Long headTeacherId = schoolClassMapper.selectByPrimaryKey(classId).getEmployee().getId();
		//根据班主任的id查询出班主任的信息
		Employee headTeacher = employeeMapper.selectByPrimaryKey(headTeacherId);
		record.setHeadTeacher(headTeacher); 
		record.setState(0);
		return courselistMapper.insert(record);
	}

	public Courselist selectByPrimaryKey(Long id) {
		return courselistMapper.selectByPrimaryKey(id);
	}

	public List<Courselist> selectAll() {
		return courselistMapper.selectAll();
	}

	public int updateByPrimaryKey(Courselist record) {
		record.setWeekday(record.getDate().getDay());
		return courselistMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = courselistMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Courselist> result = courselistMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void checkBycourselistId(Long courselistId) {
		courselistMapper.checkBycourselistId(courselistId);
	}
}
