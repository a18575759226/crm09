package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.SchoolContact;
import com._520it.crm.mapper.SchoolContactMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISchoolContactService;
@Service
public class SchoolContactServiceImpl implements ISchoolContactService {
	@Autowired
	private SchoolContactMapper schoolContactMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return schoolContactMapper.deleteByPrimaryKey(id);
	}

	public int insert(SchoolContact record) {
		return schoolContactMapper.insert(record);
	}

	public SchoolContact selectByPrimaryKey(Long id) {
		return schoolContactMapper.selectByPrimaryKey(id);
	}

	public List<SchoolContact> selectAll() {
		return schoolContactMapper.selectAll();
	}

	public int updateByPrimaryKey(SchoolContact record) {
		return schoolContactMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = schoolContactMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SchoolContact> result = schoolContactMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
