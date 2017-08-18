package com._520it.crm.mapper;

import com._520it.crm.domain.TrakcsStudents;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrakcsStudentsMapper {
    int deleteByPrimaryKey(Long id);
    int insert(TrakcsStudents record);
    TrakcsStudents selectByPrimaryKey(Long id);
    List<TrakcsStudents> selectAll();
    int updateByPrimaryKey(TrakcsStudents record);
	Long queryPageCount(QueryObject qo);
	List<TrakcsStudents> queryPageData(QueryObject qo);
}