package com._520it.crm.mapper;

import com._520it.crm.query.QueryObject;
import com._520it.crm.vo.CheckOut;

import java.util.List;

public interface CheckOutMapper {
    int deleteByPrimaryKey(Long id);
    int insert(CheckOut record);
    CheckOut selectByPrimaryKey(Long id);
    List<CheckOut> selectAll();
    List<CheckOut> selectByCondition(QueryObject qo);
    int updateByPrimaryKey(CheckOut record);
	Long queryPageCount(QueryObject qo);
	List<CheckOut> queryPageData(QueryObject qo);
}