package com._520it.crm.mapper;

import com._520it.crm.domain.Courselist;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourselistMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Courselist record);
    Courselist selectByPrimaryKey(Long id);
    List<Courselist> selectAll();
    int updateByPrimaryKey(Courselist record);
	Long queryPageCount(QueryObject qo);
	List<Courselist> queryPageData(QueryObject qo);
	void checkBycourselistId(Long courselistId);
}