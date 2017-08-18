package com._520it.crm.mapper;

import com._520it.crm.domain.Goupto;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GouptoMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Goupto record);
    Goupto selectByPrimaryKey(Long id);
    List<Goupto> selectAll();
    int updateByPrimaryKey(Goupto record);
	Long queryPageCount(QueryObject qo);
	List<Goupto> queryPageData(QueryObject qo);
}