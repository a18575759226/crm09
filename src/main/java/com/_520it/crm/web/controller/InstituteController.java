package com._520it.crm.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.crm.domain.Institute;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.InstituteQueryObject;
import com._520it.crm.service.IInstituteService;
import com._520it.crm.util.PermissionName;

@Controller
@RequestMapping("/institute")
public class InstituteController extends BaseController{
	@Autowired
	IInstituteService instituteService;
	
	@RequestMapping("")
	public String index(){
		return "institute";
	}
	@RequiresPermissions("institute:list")
	@PermissionName("学院列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(InstituteQueryObject qo){
		PageResult pageResult = null;
		pageResult = instituteService.queryPage(qo);
		return pageResult;
	}
	@RequiresPermissions("institute:save")
	@PermissionName("学院添加")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Institute institute){
		AjaxResult result = null;
		try{
			instituteService.insert(institute);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("institute:update")
	@PermissionName("学院修改")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Institute institute){
		AjaxResult result = null;
		try{
			instituteService.updateByPrimaryKey(institute);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("institute:delete")
	@PermissionName("学院删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long instituteId){
		AjaxResult result = null;
		try{
			instituteService.deleteByPrimaryKey(instituteId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

	// 查询所有员工信息(课程表下拉列表)
	@RequestMapping("/selectListForInstitutelistForm")
	@ResponseBody
	public List<Institute> selectListForInstitutelistForm() {
		List<Institute> result = instituteService.selectAll();
		return result;
	}
}
