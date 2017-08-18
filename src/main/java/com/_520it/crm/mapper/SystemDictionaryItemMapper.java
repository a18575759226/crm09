package com._520it.crm.mapper;

import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemDictionaryItem record);
    SystemDictionaryItem selectByPrimaryKey(Long id);
    List<SystemDictionaryItem> selectAll();
    int updateByPrimaryKey(SystemDictionaryItem record);
	Long queryPageCount(QueryObject qo);
	List<SystemDictionaryItem> queryPageData(QueryObject qo);

    List<SystemDictionaryItem> queryPageFromsystemDictionary(Long parentId);


    /**
     * 根据字典目录名称查询明细
     * @param name
     * @return
     */
    SystemDictionaryItem queryDicionaryItemByname(String name);


    List<SystemDictionaryItem> selectListByparentName(String parentName);

}