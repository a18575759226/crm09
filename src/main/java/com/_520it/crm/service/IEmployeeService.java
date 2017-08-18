package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
	int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);
	PageResult queryPage(EmployeeQueryObject qo);
	void quit(Long id);

    Employee queryUserByUsername(String username);
	Employee queryUserByUsernameAndPassword(String username, String password);
	void editPasswordByUsername(String newpassword, Long userId);
	Long selectByPrimaryRealname(String realname);

    List<Employee> listEmployeeExcludeSelf();

    /**
     * 查找到处理自己以外的所有的本部门之下的员工
     * @return
     */
    List<Employee> listEmployeeByTask();
}
