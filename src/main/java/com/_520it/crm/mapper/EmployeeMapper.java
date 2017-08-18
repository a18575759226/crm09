package com._520it.crm.mapper;



import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);
    Long queryPageCount(EmployeeQueryObject qo);
	List<Employee> queryPageData(EmployeeQueryObject qo);
	void changeState(@Param("employeeId")Long id,@Param("employeeState") int employeeStateQuit);
	void insertRelation(@Param("roleId")Long roleId, @Param("employeeId")Long employeeId);
	List<Permission> queryPermissionByEmployeeId(Long employeeId);
	void deleteRelation(Long id);
    Employee queryUserByUsername(String username);
    void deleteDeptIdByDptId(Long deptId);
    //通过账号和密码查询用户的信息
	Employee queryUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
	//修改密码
	void editPasswordByUsername(@Param("newpassword")String newpassword,@Param("userId")Long userId);
	Long selectByPrimaryRealname(String realname);

    /**
     * 查询除了当前登录用户的所有市场专员和超级管理员
     * @param id
     * @return
     */
    List<Employee> listEmployeeExcludeSelf(@Param("id") Long id,@Param("roleSn") String roleSn);

    /**
     * 根据自己的ID以及所在部门的ID查询出处理自己以外的所有的部门人员
     * @param empId
     * @param deptId
     * @return
     */
    List<Employee> listEmployeeByTask(@Param("empId") Long empId, @Param("deptId") Long deptId);
}