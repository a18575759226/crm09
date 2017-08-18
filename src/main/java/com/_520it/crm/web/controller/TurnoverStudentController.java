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
import com._520it.crm.domain.TurnoverStudent;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TurnoverStudentQueryObject;
import com._520it.crm.service.ITurnoverStudentService;

@Controller
@RequestMapping("/turnoverStudent")
public class TurnoverStudentController {
	@Autowired
	ITurnoverStudentService turnoverStudentService;
	
	@RequestMapping("")
	public String index(){
		return "turnoverStudent";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(TurnoverStudentQueryObject qo){
		PageResult pageResult = null;
		pageResult = turnoverStudentService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(TurnoverStudent turnoverStudent){
		AjaxResult result = null;
		try{
			turnoverStudentService.insert(turnoverStudent);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(TurnoverStudent turnoverStudent){
		AjaxResult result = null;
		try{
			turnoverStudentService.updateByPrimaryKey(turnoverStudent);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long turnoverStudentId){
		AjaxResult result = null;
		try{
			turnoverStudentService.deleteByPrimaryKey(turnoverStudentId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/lose")
	@ResponseBody
	public AjaxResult lose(TurnoverStudent turnoverStudent ,Long customerInfoId){
		AjaxResult result = null;
		try{
			turnoverStudentService.lose(turnoverStudent,customerInfoId);
			result = new AjaxResult(true,"流失成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("流失失败,请联系管理员！");
		}
		return result;
	}



}
