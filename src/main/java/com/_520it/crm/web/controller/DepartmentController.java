package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObject;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.util.PermissionName;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
	@Autowired
	IDepartmentService departmentService;

	@RequiresPermissions("department:index")
	@PermissionName("部门列表")
	@RequestMapping("")
	public String index(){
		return "department";
	}

	@RequiresPermissions("department:list")
	@PermissionName("部门数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(DepartmentQueryObject qo){
		PageResult pageResult = null;
		pageResult = departmentService.queryPage(qo);
		return pageResult;
	}

	@RequiresPermissions("department:save")
	@PermissionName("部门保存")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Department department){
		AjaxResult result = null;
		try{
			departmentService.insert(department);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}

	@RequiresPermissions("department:update")
	@PermissionName("部门更新")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Department department){
		AjaxResult result = null;
		try{
			departmentService.updateByPrimaryKey(department);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}

	@RequiresPermissions("department:delete")
	@PermissionName("部门撤销")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long departmentId){
		AjaxResult result = null;
		try{
			departmentService.deleteByPrimaryKey(departmentId);
			result = new AjaxResult(true,"撤销成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("撤销失败,请联系管理员！");
		}
		return result;
	}

	@RequestMapping("/selectListForEmployeeForm")
	@ResponseBody
	public List<Department> selectListForEmployeeForm(){
		List<Department> result = departmentService.selectAllonSteate();
		return result;
	}
}
