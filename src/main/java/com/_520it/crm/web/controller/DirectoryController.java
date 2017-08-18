package com._520it.crm.web.controller;

import com._520it.crm.domain.Directory;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DirectoryQueryObject;
import com._520it.crm.service.IDirectoryService;
import com._520it.crm.vo.DicMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/directory")
public class DirectoryController {
	@Autowired
	IDirectoryService directoryService;
	
	@RequestMapping("")
	public String index(){
		return "directory";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(DirectoryQueryObject qo,Model model,Long parentId){
        qo.setParentId(parentId);
        List<DicMenuVO> dicMenuVOS = directoryService.listParentMenus(qo.getParentId());
        model.addAttribute("dicMenuVOS", dicMenuVOS);
		PageResult pageResult = null;
        pageResult = directoryService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Directory directory){
		AjaxResult result = null;
		try{
			directoryService.insert(directory);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Directory directory){
		AjaxResult result = null;
		try{
			directoryService.updateByPrimaryKey(directory);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long directoryId){
		AjaxResult result = null;
		try{
			directoryService.deleteByPrimaryKey(directoryId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

    @RequestMapping("/listSonDic")
    @ResponseBody
    public PageResult listSonDic(DirectoryQueryObject qo){
        PageResult pageResult = null;
        pageResult = directoryService.listSonDic(qo);
        return pageResult;
    }

    @RequestMapping("/listParentMenus")
    @ResponseBody
    public PageResult listParentMenus(DirectoryQueryObject qo){
        PageResult pageResult = null;
        pageResult = directoryService.listSonDic(qo);
        return pageResult;
    }

}
