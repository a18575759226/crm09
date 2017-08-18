package com._520it.crm.service.impl;

import com._520it.crm.domain.BigCustomer;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.BigCustomerMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IBigCustomerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class BigCustomerServiceImpl implements IBigCustomerService {
	@Autowired
	private BigCustomerMapper bigCustomerMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return bigCustomerMapper.deleteByPrimaryKey(id);
	}

	public int insert(BigCustomer record) {
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setSalesman(currentUser);
		return bigCustomerMapper.insert(record);
	}

	public BigCustomer selectByPrimaryKey(Long id) {
		return bigCustomerMapper.selectByPrimaryKey(id);
	}

	public List<BigCustomer> selectAll() {
		return bigCustomerMapper.selectAll();
	}

	public int updateByPrimaryKey(BigCustomer record) {
		return bigCustomerMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = bigCustomerMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<BigCustomer> result = bigCustomerMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
