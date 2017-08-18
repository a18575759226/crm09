package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*/employee---->访问employee页面
/employee/list---->employee相关的数据
/employee/save--->保存对象*/
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
	@Autowired
	private IEmployeeService employeeService;
	@RequiresPermissions("employee:index")
	@PermissionName("员工列表")
	@RequestMapping("")
	public String index(){
		System.out.println("in......."+employeeService);
		return "employee";
	}
	@RequiresPermissions("employee:list")
	@PermissionName("员工数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(EmployeeQueryObject qo){
		PageResult result = employeeService.queryPage(qo);
		return result;
	}
	@RequiresPermissions("employee:save")
	@RequestMapping("/save")
	@ResponseBody
	@PermissionName("员工新增")
	public AjaxResult save(Employee employee){
		AjaxResult result = null;
		try{
			employeeService.insert(employee);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员!");
		}
		return result;
	}
	@RequiresPermissions("employee:update")
	@PermissionName("员工更新")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Employee employee){
		AjaxResult result = null;
		try{
			employeeService.updateByPrimaryKey(employee);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员!");
		}
		return result;
	}
	@RequiresPermissions("employee:quit")
	@PermissionName("员工离职")
	@RequestMapping("/quit")
	@ResponseBody
	public AjaxResult quit(Long id){
		AjaxResult result = null;
		try{
			employeeService.quit(id);
			result = new AjaxResult(true,"离职成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("离职失败,请联系管理员!");
		}
		return result;
	}
	//查询所有员工信息(课程表下拉列表)
	@RequestMapping("/selectListForEmployeelistForm")
	@ResponseBody
	public List<Employee> selectListForEmployeeForm(){
		List<Employee> result = employeeService.selectAll();
		return result;
	}

	/**
	 * 通过ID 查找到本部门之下除了自己以外的所有的员工对象
	 * @return
	 */
	@RequestMapping("/listEmployeeByTask")
	@ResponseBody
	public List<Employee> listEmployeeByTask(){
		List<Employee> result = employeeService.listEmployeeByTask();
		return result;
	}

	/**
     * 查询排除当前登录用户之外的所有用户
     * @return
     */
    @RequestMapping("/listEmployeeExcludeSelf")
    @ResponseBody
    public List<Employee> listEmployeeExcludeSelf(){
        List<Employee> result = employeeService.listEmployeeExcludeSelf();
        return result;
    }

	//修改密码
	@RequestMapping("/editPasswordByUsername")
	@ResponseBody
	public AjaxResult editPasswordByUsername(String password,String newpassword){
		AjaxResult result = null;
		try {
			//获取当前登录用户
			Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
			//给实体设置原始的密码
			currentUser.setPassword(newpassword);
			//通过账号和原始的密码查询用户信息
			Employee user =employeeService.queryUserByUsernameAndPassword(currentUser.getUsername(),password);
			if (user==null) {
				return new AjaxResult("原始密码错误,请重新输入!");
			}
			//如果不为空,则说明可以修改
			employeeService.editPasswordByUsername(newpassword,currentUser.getId());
			result = new AjaxResult(true,"修改成功,请重新登录");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("修改失败,请联系管理员!");
		}
		return result;
	}


	//查询出所有的员工
	@RequestMapping("/listAll")
	@ResponseBody
	public List<Employee> listAll(EmployeeQueryObject qo){
		return employeeService.selectAll();
	}



	//用户点击用户名,跳转至用户自己的页面selectByCurrentUser
	@RequestMapping("/userPage")
	public String userPage(){
		return "user";
	}
}
