package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.mapper.CustomerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.TurnoverStudent;
import com._520it.crm.mapper.TurnoverStudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITurnoverStudentService;
@Service
public class TurnoverStudentServiceImpl implements ITurnoverStudentService {
	@Autowired
	private TurnoverStudentMapper turnoverStudentMapper;
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return turnoverStudentMapper.deleteByPrimaryKey(id);
	}

	public int insert(TurnoverStudent record) {
		return turnoverStudentMapper.insert(record);
	}

	public TurnoverStudent selectByPrimaryKey(Long id) {
		return turnoverStudentMapper.selectByPrimaryKey(id);
	}

	public List<TurnoverStudent> selectAll() {
		return turnoverStudentMapper.selectAll();
	}

	public int updateByPrimaryKey(TurnoverStudent record) {
		return turnoverStudentMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = turnoverStudentMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<TurnoverStudent> result = turnoverStudentMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	//学员流失
	@Override
	public void lose(TurnoverStudent turnoverStudent, Long customerInfoId) {
		//获取客户信息
		CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfoId);
		turnoverStudent.setRec_tur(customerInfo);
		turnoverStudentMapper.insert(turnoverStudent);
		//修改学员状态
		customerInfo.setState(3);
		customerInfoMapper.updateByPrimaryKey(customerInfo);

	}
}
