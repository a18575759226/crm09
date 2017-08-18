package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.ISystemMenuService;
import com._520it.crm.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private ISystemMenuService systemMenuService;
	public int deleteByPrimaryKey(Long id) {
		return employeeMapper.deleteByPrimaryKey(id);
	}
	public int insert(Employee record) {
		//需要给用户默认的密码,默认的状态,默认是普通用户,设置入职时间
		record.setState(Employee.NORMAL);
		record.setAdmin(false);
		record.setInputtime(new Date());
		Md5Hash md5 = new Md5Hash("1",record.getUsername(),1);
		record.setPassword(md5.toString());
		int count = employeeMapper.insert(record);
		//处理员工和角色的中间表关系
		for(Role role:record.getRoles()){
			employeeMapper.insertRelation(role.getId(),record.getId());
		}
		return count;
	}

	public Employee selectByPrimaryKey(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	public List<Employee> selectAll() {
		return employeeMapper.selectAll();
	}

	public int updateByPrimaryKey(Employee record) {
		int count = employeeMapper.updateByPrimaryKey(record);
		//删除之前所有的关系
		employeeMapper.deleteRelation(record.getId());
		//处理员工和角色的中间表关系
		for(Role role:record.getRoles()){
			employeeMapper.insertRelation(role.getId(),record.getId());
		}
		return count;
	}

	public PageResult queryPage(EmployeeQueryObject qo) {
		//符合条件的总数
		Long count = employeeMapper.queryPageCount(qo);
		if(count <= 0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Employee> result =  employeeMapper.queryPageData(qo);
		return new PageResult(count, result);
	}

	public void quit(Long id) {
		employeeMapper.changeState(id,Employee.LEAVE);
	}

    @Override
    public Employee queryUserByUsername(String username) {
        return employeeMapper.queryUserByUsername(username);
    }
	@Override
	public Employee queryUserByUsernameAndPassword(String username, String password) {
		//通过账号和密码查询用户的信息
		return employeeMapper.queryUserByUsernameAndPassword(username,password);
	}
	@Override
	public void editPasswordByUsername(String newpassword,Long userId) {
		employeeMapper.editPasswordByUsername(newpassword,userId);
	}
	//根据名字查询员工的id
	@Override
	public Long selectByPrimaryRealname(String realname) {
		return employeeMapper.selectByPrimaryRealname(realname);
	}

    @Override
    public List<Employee> listEmployeeExcludeSelf() {
        List<Employee> result = null;
        Employee currentUser = UserUtils.getCurrentUser();

        result = employeeMapper.listEmployeeExcludeSelf(currentUser.getId(),"MARKETMAN");
        return result;
    }

    @Override
    public List<Employee> listEmployeeByTask() {
		//首先查询到当前登录的用户
		Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
		//获取到当前用户的ID 以及所在部门的ID
		Long empId = currentUser.getId();//本身id
		//根据自身ID获取到自身对象
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		Long deptId = -1L;
		if (employee.getDept()!=null){
			deptId = employee.getDept().getId();
		}
		//调用mapper里面的方法获取到本部门下的所有的员工处理自己
		List<Employee> result = employeeMapper.listEmployeeByTask(empId,deptId);
        return result;
    }


}
