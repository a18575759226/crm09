package com._520it.crm.mapper;

import com._520it.crm.domain.Institute;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstituteMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Institute record);
    Institute selectByPrimaryKey(Long id);
    List<Institute> selectAll();
    int updateByPrimaryKey(Institute record);
	Long queryPageCount(QueryObject qo);
	List<Institute> queryPageData(QueryObject qo);
}