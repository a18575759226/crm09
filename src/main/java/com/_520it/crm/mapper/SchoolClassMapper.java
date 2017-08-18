package com._520it.crm.mapper;

import com._520it.crm.domain.SchoolClass;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolClassMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SchoolClass record);
    SchoolClass selectByPrimaryKey(Long id);
    List<SchoolClass> selectAll();
    int updateByPrimaryKey(SchoolClass record);
	Long queryPageCount(QueryObject qo);
	List<SchoolClass> queryPageData(QueryObject qo);
	Long selectByPrimaryRealname(String className);
}