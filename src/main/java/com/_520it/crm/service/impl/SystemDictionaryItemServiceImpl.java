package com._520it.crm.service.impl;

import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.mapper.SystemDictionaryItemMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemDictionaryItemMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemDictionaryItem record) {
		return systemDictionaryItemMapper.insert(record);
	}

	public SystemDictionaryItem selectByPrimaryKey(Long id) {
		return systemDictionaryItemMapper.selectByPrimaryKey(id);
	}

	public List<SystemDictionaryItem> selectAll() {
		return systemDictionaryItemMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemDictionaryItem record) {
		return systemDictionaryItemMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = systemDictionaryItemMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemDictionaryItem> result = systemDictionaryItemMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

    @Override
    public PageResult queryPageFromsystemDictionary(Long parentId) {
        List<SystemDictionaryItem> result = systemDictionaryItemMapper.queryPageFromsystemDictionary(parentId);
        return new PageResult((1L), result);
    }

	@Override
	public List<SystemDictionaryItem> selectListByparentName(String parentName) {
		return systemDictionaryItemMapper.selectListByparentName(parentName);
	}
}
