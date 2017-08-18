package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Exam;
import com._520it.crm.mapper.ExamMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IExamService;
@Service
public class ExamServiceImpl implements IExamService {
	@Autowired
	private ExamMapper examMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return examMapper.deleteByPrimaryKey(id);
	}

	public int insert(Exam record) {
		return examMapper.insert(record);
	}

	public Exam selectByPrimaryKey(Long id) {
		return examMapper.selectByPrimaryKey(id);
	}

	public List<Exam> selectAll() {
		return examMapper.selectAll();
	}

	public int updateByPrimaryKey(Exam record) {
		return examMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = examMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Exam> result = examMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void checkByPrimaryKey(Exam exam) {
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		exam.setEmployee(employee);
		examMapper.checkByPrimaryKey(exam);
	}
}
