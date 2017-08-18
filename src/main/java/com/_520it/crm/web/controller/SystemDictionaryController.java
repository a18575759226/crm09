package com._520it.crm.web.controller;

import com._520it.crm.domain.SystemDictionary;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemDictionaryQueryObject;
import com._520it.crm.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {
	@Autowired
	ISystemDictionaryService systemDictionaryService;
	
	@RequestMapping("")
	public String index(){
		return "systemDictionary";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemDictionaryQueryObject qo){
		PageResult pageResult = null;
		pageResult = systemDictionaryService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemDictionary systemDictionary){
		AjaxResult result = null;
		try{
			systemDictionaryService.insert(systemDictionary);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(SystemDictionary systemDictionary){
		AjaxResult result = null;
		try{
			systemDictionaryService.updateByPrimaryKey(systemDictionary);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long systemDictionaryId){
		AjaxResult result = null;
		try{
			systemDictionaryService.deleteByPrimaryKey(systemDictionaryId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/selectListFromsystemDictionary")
	@ResponseBody
	public List<SystemDictionary> selectListFromsystemDictionary(){
        return systemDictionaryService.selectAll();
    }
}
