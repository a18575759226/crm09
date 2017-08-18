package com._520it.crm.service;
import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ISystemDictionaryItemService {
	int deleteByPrimaryKey(Long id);
    int insert(SystemDictionaryItem record);
    SystemDictionaryItem selectByPrimaryKey(Long id);
    List<SystemDictionaryItem> selectAll();
    int updateByPrimaryKey(SystemDictionaryItem record);
	PageResult queryPage(QueryObject qo);

    PageResult queryPageFromsystemDictionary(Long parentId);

    List<SystemDictionaryItem> selectListByparentName(String parentName);
}
