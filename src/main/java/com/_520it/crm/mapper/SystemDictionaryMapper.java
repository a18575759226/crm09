package com._520it.crm.mapper;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemDictionary record);
    SystemDictionary selectByPrimaryKey(Long id);
    List<SystemDictionary> selectAll();
    int updateByPrimaryKey(SystemDictionary record);
	Long queryPageCount(QueryObject qo);
	List<SystemDictionary> queryPageData(QueryObject qo);
}