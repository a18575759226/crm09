package com._520it.crm.web.controller;

import com._520it.crm.domain.TrainingRecords;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TrainingRecordsQueryObject;
import com._520it.crm.service.ITrainingRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/trainingRecords")
public class TrainingRecordsController {
	@Autowired
	ITrainingRecordsService trainingRecordsService;
	
	@RequestMapping("")
	public String index(){
		return "trainingRecords";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(TrainingRecordsQueryObject qo){
		PageResult pageResult = null;
		pageResult = trainingRecordsService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(TrainingRecords trainingRecords){
		AjaxResult result = null;
		try{
			trainingRecordsService.insert(trainingRecords);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(TrainingRecords trainingRecords){
		AjaxResult result = null;
		try{
			trainingRecordsService.updateByPrimaryKey(trainingRecords);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long trainingRecordsId){
		AjaxResult result = null;
		try{
			trainingRecordsService.deleteByPrimaryKey(trainingRecordsId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

    @RequestMapping("/listRecordsByBigCustomerId")
    @ResponseBody
    public List<TrainingRecords> listRecordsByBigCustomerId(Long id){
        List<TrainingRecords> result = null;
        result = trainingRecordsService.listRecordsByBigCustomerId(id);
        return result;
    }

}
