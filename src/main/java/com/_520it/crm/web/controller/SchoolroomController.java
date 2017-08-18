package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Schoolroom;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolroomQueryObject;
import com._520it.crm.service.ISchoolroomService;
import com._520it.crm.util.PermissionName;

@Controller
@RequestMapping("/schoolroom")
public class SchoolroomController extends BaseController{
	@Autowired
	ISchoolroomService schoolroomService;
	
	@RequestMapping("")
	public String index(){
		return "schoolroom";
	}
	@RequiresPermissions("schoolroom:list")
	@PermissionName("教室列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SchoolroomQueryObject qo){
		PageResult pageResult = null;
		pageResult = schoolroomService.queryPage(qo);
		return pageResult;
	}
	@RequiresPermissions("schoolroom:save")
	@PermissionName("教室添加")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Schoolroom schoolroom){
		AjaxResult result = null;
		try{
			schoolroomService.insert(schoolroom);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("schoolroom:update")
	@PermissionName("教室修改")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Schoolroom schoolroom){
		AjaxResult result = null;
		try{
			schoolroomService.updateByPrimaryKey(schoolroom);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("schoolroom:delete")
	@PermissionName("教室删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long schoolroomId){
		AjaxResult result = null;
		try{
			schoolroomService.deleteByPrimaryKey(schoolroomId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	//查询所有未使用教室
	@RequestMapping("/selectListForSchoolroomlistForm")
	@ResponseBody
	public List<Schoolroom> selectListForSchoolroomlistForm(Long schoolroomId){
		List<Schoolroom> result = schoolroomService.selectListForSchoolroomlistForm();
		return result;
	}

	// 查询所的教室
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Schoolroom> selectAll() {
		List<Schoolroom> result = schoolroomService.selectAll();
		return result;
	}
}
