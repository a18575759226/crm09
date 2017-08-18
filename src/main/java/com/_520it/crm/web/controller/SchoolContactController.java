package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.SchoolContact;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SchoolContactQueryObject;
import com._520it.crm.service.ISchoolContactService;

@Controller
@RequestMapping("/schoolContact")
public class SchoolContactController {
	@Autowired
	ISchoolContactService schoolContactService;
	
	@RequestMapping("")
	public String index(){
		return "schoolContact";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SchoolContactQueryObject qo){
		PageResult pageResult = null;
		pageResult = schoolContactService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SchoolContact schoolContact){
		AjaxResult result = null;
		try{
			schoolContactService.insert(schoolContact);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(SchoolContact schoolContact){
		AjaxResult result = null;
		try{
			schoolContactService.updateByPrimaryKey(schoolContact);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long schoolContactId){
		AjaxResult result = null;
		try{
			schoolContactService.deleteByPrimaryKey(schoolContactId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
