package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.CustomerInfoMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.PotentialCustomer;
import com._520it.crm.mapper.PotentialCustomerMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPotentialCustomerService;
@Service
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {
	@Autowired
	private PotentialCustomerMapper potentialCustomerMapper;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return potentialCustomerMapper.deleteByPrimaryKey(id);
	}

	public int insert(PotentialCustomer record) {
		return potentialCustomerMapper.insert(record);
	}

	public PotentialCustomer selectByPrimaryKey(Long id) {
		return potentialCustomerMapper.selectByPrimaryKey(id);
	}

	public List<PotentialCustomer> selectAll() {
		return potentialCustomerMapper.selectAll();
	}

	public int updateByPrimaryKey(PotentialCustomer record) {
		return potentialCustomerMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = potentialCustomerMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<PotentialCustomer> result = potentialCustomerMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void getOutToCustomerInfo(Long potentialCustomerId) {
		//首先获取到当前登录的用户
		Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
		//根据ID获取到当前选择的客户池里面的学员
        PotentialCustomer potentialCustomer = potentialCustomerMapper.selectByPrimaryKey(potentialCustomerId);
        //根据customerInfo_id获取到该学员之前的潜在学员对象
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(potentialCustomer.getCustomerInfo_id());
        //设置该潜在学员的状态为潜在学员 并且设置 录入人和负责人为当前登录的用户
        customerInfo.setState(0);
        customerInfo.setInputUser(currentUser);
        customerInfo.setPrincipal(currentUser);
        //修改潜在学员表之内的信息
        customerInfoMapper.updateByPrimaryKey(customerInfo);

        //删除该学员在客户池之内的信息
        potentialCustomerMapper.deleteByPrimaryKey(potentialCustomerId);
    }
}
