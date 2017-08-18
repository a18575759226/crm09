package com._520it.crm.web.controller;

import com._520it.crm.domain.SystemDictionaryItem;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemDictionaryItemQueryObject;
import com._520it.crm.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {
	@Autowired
	ISystemDictionaryItemService systemDictionaryItemService;
	
	@RequestMapping("")
	public String index(){
		return "systemDictionaryItem";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemDictionaryItemQueryObject qo){
		PageResult pageResult = null;
		pageResult = systemDictionaryItemService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemDictionaryItem systemDictionaryItem){
		AjaxResult result = null;
		try{
			systemDictionaryItemService.insert(systemDictionaryItem);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(SystemDictionaryItem systemDictionaryItem){
		AjaxResult result = null;
		try{
			systemDictionaryItemService.updateByPrimaryKey(systemDictionaryItem);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long systemDictionaryItemId){
		AjaxResult result = null;
		try{
			systemDictionaryItemService.deleteByPrimaryKey(systemDictionaryItemId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
    @RequestMapping("/queryPageFromsystemDictionary")
    @ResponseBody
    public PageResult queryPageFromsystemDictionary(Long parentId){
        PageResult result=null;
        result=systemDictionaryItemService.queryPageFromsystemDictionary(parentId);
        return result;
    }

	/**
	 * 根据字典名称获取到所有的
	 * @param parentName
	 * @return
	 */
	@RequestMapping("/selectListByparentName")
	@ResponseBody
	public List<SystemDictionaryItem> selectListByparentName(String parentName){
		List<SystemDictionaryItem> result = systemDictionaryItemService.selectListByparentName(parentName);
		return result;
	}
}
