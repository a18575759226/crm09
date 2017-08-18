package com._520it.crm.web.controller;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CustomerInfoQueryObject;
import com._520it.crm.service.ICustomerInfoService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customerInfo")
public class CustomerInfoController extends BaseController{
	@Autowired
	ICustomerInfoService customerInfoService;

	@RequiresPermissions("customerInfo:index")
	@PermissionName("学员列表")
	@RequestMapping("")
	public String index(){
		return "customerInfo";
	}

	@RequiresPermissions("customerInfo:list")
	@PermissionName("学员列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(CustomerInfoQueryObject qo){
		PageResult pageResult = null;
		pageResult = customerInfoService.queryPage(qo);
		return pageResult;
	}

	@RequiresPermissions("customerInfo:save")
	@PermissionName("学员保存")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(CustomerInfo customerInfo){
		AjaxResult result = null;
		try{
			customerInfoService.insert(customerInfo);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}

	@RequiresPermissions("customerInfo:update")
	@PermissionName("学员修改")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(CustomerInfo customerInfo){
		AjaxResult result = null;
		try{
			customerInfoService.updateByPrimaryKey(customerInfo);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}

	@RequiresPermissions("customerInfo:delete")
	@PermissionName("学员删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long customerInfoId){
		AjaxResult result = null;
		try{
			customerInfoService.deleteByPrimaryKey(customerInfoId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;

	}

	@RequiresPermissions("customerInfo:becomeFormalStudent")
	@PermissionName("学员升班")
	@RequestMapping("/becomeFormalStudent")
	@ResponseBody
	public AjaxResult becomeFormalStudent(Long customerInfoId){
		AjaxResult result = null;
		try{
			customerInfoService.becomeFormalStudent(customerInfoId);
			result = new AjaxResult(true,"升班成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("升班失败,请联系管理员！");
		}
		return result;

	}

	@RequiresPermissions("customerInfo:moveToPotentialClientPool")
	@PermissionName("学员放入客户池")
	@RequestMapping("/moveToPotentialClientPool")
	@ResponseBody
	public AjaxResult moveToPotentialClientPool(Long customerInfoId){
		AjaxResult result = null;
		try{
			customerInfoService.moveToPotentialClientPool(customerInfoId);
			result = new AjaxResult(true,"添加成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("添加失败,请联系管理员！");
		}
		return result;

	}

	//查询所有客户信息(学员下拉列表)
	@RequiresPermissions("customerInfo:selectListForCustomerInfolistForm")
	@PermissionName("查询所有学员")
	@RequestMapping("/selectListForCustomerInfolistForm")
	@ResponseBody
	public List<CustomerInfo> selectListForCustomerInfolistForm(){
		List<CustomerInfo> result = customerInfoService.selectAll();
		return result;
	}


}
