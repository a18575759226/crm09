package com._520it.crm.mapper;

import com._520it.crm.domain.SchoolContact;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolContactMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SchoolContact record);
    SchoolContact selectByPrimaryKey(Long id);
    List<SchoolContact> selectAll();
    int updateByPrimaryKey(SchoolContact record);
	Long queryPageCount(QueryObject qo);
	List<SchoolContact> queryPageData(QueryObject qo);
}