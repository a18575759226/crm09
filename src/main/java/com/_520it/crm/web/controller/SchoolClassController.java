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
import com._520it.crm.domain.SchoolClass;
import com._520it.crm.domain.Schoolroom;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolClassQueryObject;
import com._520it.crm.service.ISchoolClassService;
import com._520it.crm.service.ISchoolroomService;
import com._520it.crm.util.PermissionName;

@Controller
@RequestMapping("/schoolClass")
public class SchoolClassController extends BaseController{
	@Autowired
	ISchoolClassService schoolClassService;
	@Autowired
	ISchoolroomService schoolroomService;
	@RequestMapping("")
	public String index(){
		return "schoolClass";
	}
	@RequiresPermissions("schoolClass:list")
	@PermissionName("班级列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SchoolClassQueryObject qo){
		PageResult pageResult = null;
		pageResult = schoolClassService.queryPage(qo);
		return pageResult;
	}
	@RequiresPermissions("schoolClass:save")
	@PermissionName("班级添加")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SchoolClass schoolClass){
		AjaxResult result = null;
		try{
			schoolClassService.insert(schoolClass);
			//设置教室为使用状态
			schoolroomService.checkByschoolroomId(schoolClass.getSchoolroom().getId());
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("schoolClass:update")
	@PermissionName("班级修改")
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(SchoolClass schoolClass){
		System.out.println("编辑");
		AjaxResult result = null;
		try{
			schoolClassService.updateByPrimaryKey(schoolClass);
			//设置教室为使用状态
			schoolroomService.checkByschoolroomId(schoolClass.getSchoolroom().getId());
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequiresPermissions("schoolClass:delete")
	@PermissionName("班级删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long schoolClassId){
		AjaxResult result = null;
		try{
			schoolClassService.deleteByPrimaryKey(schoolClassId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	//查询所有班级信息(课程表下拉列表)
	@RequestMapping("/selectListForCourselistForm")
	@ResponseBody
	public List<SchoolClass> selectListForCourselistForm() {
		List<SchoolClass> result = schoolClassService.selectAll();
		return result;
	}
}
