package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Exam;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ExamQueryObject;
import com._520it.crm.service.IExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	IExamService examService;
	
	@RequestMapping("")
	public String index(){
		return "exam";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ExamQueryObject qo){
		PageResult pageResult = null;
		pageResult = examService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Exam exam){
		AjaxResult result = null;
		try{
			examService.insert(exam);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Exam exam){
		AjaxResult result = null;
		try{
			examService.updateByPrimaryKey(exam);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long examId){
		AjaxResult result = null;
		try{
			examService.deleteByPrimaryKey(examId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/check")
	@ResponseBody
	public AjaxResult check(Exam exam){
		AjaxResult result = null;
		try{
			examService.checkByPrimaryKey(exam);
			result = new AjaxResult(true,"审核成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("审核失败,请联系管理员！");
		}
		return result;
	}
}
