package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Expend;
import com._520it.crm.mapper.ExpendMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IExpendService;
@Service
public class ExpendServiceImpl implements IExpendService {
	@Autowired
	private ExpendMapper expendMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return expendMapper.deleteByPrimaryKey(id);
	}

	public int insert(Expend record) {
		return expendMapper.insert(record);
	}

	public Expend selectByPrimaryKey(Long id) {
		return expendMapper.selectByPrimaryKey(id);
	}

	public List<Expend> selectAll() {
		return expendMapper.selectAll();
	}

	public int updateByPrimaryKey(Expend record) {
		return expendMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = expendMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Expend> result = expendMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
