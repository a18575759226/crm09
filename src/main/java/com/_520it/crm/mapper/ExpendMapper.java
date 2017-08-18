package com._520it.crm.mapper;

import com._520it.crm.domain.Expend;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpendMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Expend record);
    Expend selectByPrimaryKey(Long id);
    List<Expend> selectAll();
    int updateByPrimaryKey(Expend record);
	Long queryPageCount(QueryObject qo);
	List<Expend> queryPageData(QueryObject qo);
}