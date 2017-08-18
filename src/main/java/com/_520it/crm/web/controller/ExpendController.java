package com._520it.crm.web.controller;

import com._520it.crm.domain.Expend;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ExpendQueryObject;
import com._520it.crm.service.IExpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/expend")
public class ExpendController {
	@Autowired
	IExpendService expendService;
	
	@RequestMapping("")
	public String index(){
		return "expend";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ExpendQueryObject qo){
		PageResult pageResult = null;
		pageResult = expendService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Expend expend){
		AjaxResult result = null;
		try{
			expendService.insert(expend);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Expend expend){
		AjaxResult result = null;
		try{
			expendService.updateByPrimaryKey(expend);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long expendId){
		AjaxResult result = null;
		try{
			expendService.deleteByPrimaryKey(expendId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
