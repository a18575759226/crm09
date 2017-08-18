package com._520it.crm.web.controller;

import com._520it.crm.domain.CheckTerm;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CheckTermQueryObject;
import com._520it.crm.service.ICheckTermService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checkTerm")
public class CheckTermController {
	@Autowired
	ICheckTermService checkTermService;
	
	@RequestMapping("")
	public String index(){
		return "checkTerm";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(CheckTermQueryObject qo){
		PageResult pageResult = null;
		pageResult = checkTermService.queryPage(qo);
		return pageResult;
	}
	//签到
	@RequestMapping("/signIn")
	@ResponseBody
	public AjaxResult signIn(HttpServletRequest request){
		AjaxResult result = null;
		try{
			checkTermService.signIn(request);
			result = new AjaxResult(true,"签到成功");
		}catch(Exception e){
			e.printStackTrace();

			result = new AjaxResult(e.getMessage());
		}
		return result;
	}
	//签退
    @RequestMapping("/signOut")
    @ResponseBody
    public AjaxResult signOut(){
        AjaxResult result = null;
        try{
            checkTermService.signOut();
            result = new AjaxResult(true,"签退成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(e.getMessage());
        }
        return result;
    }
    //补签
    @RequestMapping("/patch")
    @ResponseBody
    public AjaxResult patch(){
        AjaxResult result = null;
        try{
            checkTermService.patch();
            result = new AjaxResult(true,"补签成功,当月可补签3次,剩余2次欧!^-^");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(e.getMessage());
        }
        return result;
    }
    @RequestMapping("/Excel")
    @ResponseBody
    public void excel(HttpServletResponse response,Integer state, CheckTermQueryObject qo) throws Exception {
        List<CheckTerm> pageResult = checkTermService.selectByCondition(qo);
        try {
            String fileNamep = "考勤明细表"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";
            String fileName = new String(fileNamep.getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            Workbook wb = new HSSFWorkbook();
            //创建
            Sheet sheet = wb.createSheet("员工考勤数据");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("姓名");
            row.createCell(1).setCellValue("签到IP");
            row.createCell(2).setCellValue("签到时间");
            row.createCell(3).setCellValue("签退时间");
            row.createCell(4).setCellValue("状态");
            row.createCell(5).setCellValue("补签时间");
            Row pageResultRow = null;
            for (int i = 0; i < pageResult.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(pageResult.get(i).getName());
                pageResultRow.createCell(1).setCellValue(pageResult.get(i).getSignIp());
                if(pageResult.get(i).getSignInTime()!=null){
                    pageResultRow.createCell(2).setCellValue(pageResult.get(i).getSignInTime().toLocaleString());
                }
                if(pageResult.get(i).getSignOutTime()!=null){
                    pageResultRow.createCell(3).setCellValue(pageResult.get(i).getSignOutTime().toLocaleString());
                }
                pageResultRow.createCell(4).setCellValue(pageResult.get(i).getState());
                if(pageResult.get(i).getPatchTime()!=null){
                    pageResultRow.createCell(5).setCellValue(pageResult.get(i).getPatchTime().toLocaleString());
                }
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
