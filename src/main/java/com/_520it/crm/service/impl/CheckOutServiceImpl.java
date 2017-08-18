package com._520it.crm.service.impl;

import com._520it.crm.mapper.CheckOutMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ICheckOutService;
import com._520it.crm.vo.CheckOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CheckOutServiceImpl implements ICheckOutService {
	@Autowired
	private CheckOutMapper checkOutMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return checkOutMapper.deleteByPrimaryKey(id);
	}

	public int insert(CheckOut record) {
		return checkOutMapper.insert(record);
	}

	public CheckOut selectByPrimaryKey(Long id) {
		return checkOutMapper.selectByPrimaryKey(id);
	}

    @Override
    public List<CheckOut> selectAll() {
        return checkOutMapper.selectAll();
    }

    public List<CheckOut> selectByCondition(QueryObject qo) {
		return checkOutMapper.selectByCondition(qo);
	}

	public int updateByPrimaryKey(CheckOut record) {
		return checkOutMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = checkOutMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<CheckOut> result = checkOutMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
