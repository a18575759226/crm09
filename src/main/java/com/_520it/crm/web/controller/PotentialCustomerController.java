package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.PotentialCustomer;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PotentialCustomerQueryObject;
import com._520it.crm.service.IPotentialCustomerService;

@Controller
@RequestMapping("/potentialCustomer")
public class PotentialCustomerController extends BaseController{
	@Autowired
	IPotentialCustomerService potentialCustomerService;

	@RequiresPermissions("customerInfo:index")
	@PermissionName("查询所有客户池")
	@RequestMapping("")
	public String index(){
		return "potentialCustomer";
	}

	@RequiresPermissions("customerInfo:list")
	@PermissionName("查询所有客户池")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PotentialCustomerQueryObject qo){
		PageResult pageResult = null;
		pageResult = potentialCustomerService.queryPage(qo);
		return pageResult;
	}

	/*@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(PotentialCustomer potentialCustomer){
		AjaxResult result = null;
		try{
			potentialCustomerService.insert(potentialCustomer);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(PotentialCustomer potentialCustomer){
		AjaxResult result = null;
		try{
			potentialCustomerService.updateByPrimaryKey(potentialCustomer);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long potentialCustomerId){
		AjaxResult result = null;
		try{
			potentialCustomerService.deleteByPrimaryKey(potentialCustomerId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}*/

	@RequiresPermissions("customerInfo:getOutToCustomerInfo")
	@PermissionName("取出客户池对象")
	@RequestMapping("/getOutToCustomerInfo")
	@ResponseBody
	public AjaxResult getOutToCustomerInfo(Long potentialCustomerId){
		AjaxResult result = null;
		try{
			potentialCustomerService.getOutToCustomerInfo(potentialCustomerId);
			result = new AjaxResult(true,"成功取出");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("取出失败,请联系管理员！");
		}
		return result;
	}
}
