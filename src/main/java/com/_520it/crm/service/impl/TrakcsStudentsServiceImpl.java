package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.TrakcsStudents;
import com._520it.crm.mapper.TrakcsStudentsMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITrakcsStudentsService;
@Service
public class TrakcsStudentsServiceImpl implements ITrakcsStudentsService {
	@Autowired
	private TrakcsStudentsMapper trakcsStudentsMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return trakcsStudentsMapper.deleteByPrimaryKey(id);
	}

	public int insert(TrakcsStudents record) {
		return trakcsStudentsMapper.insert(record);
	}

	public TrakcsStudents selectByPrimaryKey(Long id) {
		return trakcsStudentsMapper.selectByPrimaryKey(id);
	}

	public List<TrakcsStudents> selectAll() {
		return trakcsStudentsMapper.selectAll();
	}

	public int updateByPrimaryKey(TrakcsStudents record) {
		return trakcsStudentsMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = trakcsStudentsMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<TrakcsStudents> result = trakcsStudentsMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
