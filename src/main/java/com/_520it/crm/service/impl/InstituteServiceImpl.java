package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Institute;
import com._520it.crm.mapper.InstituteMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IInstituteService;
@Service
public class InstituteServiceImpl implements IInstituteService {
	@Autowired
	private InstituteMapper instituteMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return instituteMapper.deleteByPrimaryKey(id);
	}

	public int insert(Institute record) {
		return instituteMapper.insert(record);
	}

	public Institute selectByPrimaryKey(Long id) {
		return instituteMapper.selectByPrimaryKey(id);
	}

	public List<Institute> selectAll() {
		return instituteMapper.selectAll();
	}

	public int updateByPrimaryKey(Institute record) {
		return instituteMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = instituteMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Institute> result = instituteMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
