package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ISystemDictionaryService {
	int deleteByPrimaryKey(Long id);
    int insert(SystemDictionary record);
    SystemDictionary selectByPrimaryKey(Long id);
    List<SystemDictionary> selectAll();
    int updateByPrimaryKey(SystemDictionary record);
	PageResult queryPage(QueryObject qo);
}
