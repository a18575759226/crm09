package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Department;
import com._520it.crm.mapper.DepartmentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IDepartmentService;
@Service
public class DepartmentServiceImpl implements IDepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private EmployeeMapper employeeMapper;
	
	public int deleteByPrimaryKey(Long id) {
		int i = departmentMapper.deleteByPrimaryKey(id);
		//调用EmployeeMapper里面的方法 删除在需要撤销的部门之内的员工的部门ID
		employeeMapper.deleteDeptIdByDptId(id);
		return i;
	}

	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	public Department selectByPrimaryKey(Long id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	public List<Department> selectAll() {
		return departmentMapper.selectAll();
	}

	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = departmentMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Department> result = departmentMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<Department> selectAllonSteate() {
		return departmentMapper.selectAllonSteate();
	}
}
