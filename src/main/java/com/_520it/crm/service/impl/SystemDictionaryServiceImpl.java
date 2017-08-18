package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.mapper.SystemDictionaryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemDictionaryService;
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemDictionaryMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemDictionary record) {
		return systemDictionaryMapper.insert(record);
	}

	public SystemDictionary selectByPrimaryKey(Long id) {
		return systemDictionaryMapper.selectByPrimaryKey(id);
	}

	public List<SystemDictionary> selectAll() {
		return systemDictionaryMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemDictionary record) {
		return systemDictionaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = systemDictionaryMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemDictionary> result = systemDictionaryMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
