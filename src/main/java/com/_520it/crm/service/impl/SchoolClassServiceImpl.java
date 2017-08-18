package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.SchoolClass;
import com._520it.crm.mapper.SchoolClassMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISchoolClassService;
@Service
public class SchoolClassServiceImpl implements ISchoolClassService {
	@Autowired
	private SchoolClassMapper schoolClassMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return schoolClassMapper.deleteByPrimaryKey(id);
	}

	public int insert(SchoolClass record) {
		return schoolClassMapper.insert(record);
	}

	public SchoolClass selectByPrimaryKey(Long id) {
		return schoolClassMapper.selectByPrimaryKey(id);
	}

	public List<SchoolClass> selectAll() {
		return schoolClassMapper.selectAll();
	}

	public int updateByPrimaryKey(SchoolClass record) {
		return schoolClassMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = schoolClassMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SchoolClass> result = schoolClassMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public Long selectByPrimaryRealname(String className) {
		return schoolClassMapper.selectByPrimaryRealname(className);
	}
}
