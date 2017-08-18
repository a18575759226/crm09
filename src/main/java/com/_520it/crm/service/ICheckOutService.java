package com._520it.crm.service;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.vo.CheckOut;

import java.util.List;

public interface ICheckOutService {
	int deleteByPrimaryKey(Long id);
    int insert(CheckOut record);
    CheckOut selectByPrimaryKey(Long id);
    List<CheckOut> selectAll();
    List<CheckOut> selectByCondition(QueryObject qo);
    int updateByPrimaryKey(CheckOut record);
	PageResult queryPage(QueryObject qo);
}
