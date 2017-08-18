package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Goupto;
import com._520it.crm.mapper.GouptoMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IGouptoService;
@Service
public class GouptoServiceImpl implements IGouptoService {
	@Autowired
	private GouptoMapper gouptoMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return gouptoMapper.deleteByPrimaryKey(id);
	}

	public int insert(Goupto record) {
		return gouptoMapper.insert(record);
	}

	public Goupto selectByPrimaryKey(Long id) {
		return gouptoMapper.selectByPrimaryKey(id);
	}

	public List<Goupto> selectAll() {
		return gouptoMapper.selectAll();
	}

	public int updateByPrimaryKey(Goupto record) {
		return gouptoMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = gouptoMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Goupto> result = gouptoMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
