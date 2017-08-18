package com._520it.crm.web.controller;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.domain.Goupto;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CustomerInfoQueryObject;
import com._520it.crm.service.ICustomerInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;


import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/officialCustomerInfo")
public class OfficialCustomerController {
    @Autowired
    ICustomerInfoService customerInfoService;

    @RequestMapping("")
    public String index() {

        return "officialCustomer";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CustomerInfoQueryObject qo) {
        //1代表只查询正式学员
        PageResult pageResult = null;
        qo.setState(1);
        pageResult = customerInfoService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(CustomerInfo customerInfo) {
        AjaxResult result = null;
        try {
            customerInfoService.insert(customerInfo);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(CustomerInfo customerInfo) {
        AjaxResult result = null;
        try {
            customerInfoService.updateByPrimaryKey(customerInfo);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long customerInfoId) {
        AjaxResult result = null;
        try {
            customerInfoService.deleteByPrimaryKey(customerInfoId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

    //修改班级ID
    @RequestMapping("/updateClassId")
    @ResponseBody
    public AjaxResult updateClassId(CustomerInfo customerInfo, Long customerInfoId) {
        AjaxResult result = null;
        try {
            customerInfoService.updateClassId(customerInfo, customerInfoId);
            result = new AjaxResult(true, "转班成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("转班失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/quit")
    @ResponseBody
    public AjaxResult quit(Long customerInfoId) {
        AjaxResult result = null;
        try {
            customerInfoService.quit(customerInfoId);
            result = new AjaxResult(true, "休学成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("休学失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/loan")
    @ResponseBody
    public AjaxResult loan(Long customerInfoId) {
        AjaxResult result = null;
        try {
            customerInfoService.loan(customerInfoId);
            result = new AjaxResult(true, "修改为贷款成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("修改为贷款失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/Excel")
    @ResponseBody
    public void Excel(HttpServletResponse response) throws Exception {
        // System.out.println("*************************5555");
        try {
            //设置下载头
            String fileName = new String("template.xls".getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            Workbook wb = new HSSFWorkbook();
            //创建
            List<CustomerInfo> customerInfoServices = customerInfoService.selectAll();
            Sheet sheet = wb.createSheet("升班留级");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("真实姓名");
            row.createCell(1).setCellValue("总学费");
            row.createCell(2).setCellValue("销售流水");
            row.createCell(3).setCellValue("其他费用");
            row.createCell(4).setCellValue("升班/留级时间");
            row.createCell(5).setCellValue("QQ");
            row.createCell(6).setCellValue("联系电话");
            row.createCell(7).setCellValue("以前的班级");
            row.createCell(8).setCellValue("流入的班级");
            row.createCell(9).setCellValue("营售人员");
            row.createCell(10).setCellValue("状态");
            System.out.println("*************************5555");
            Row pageResultRow = null;
            for (int i = 0; i < customerInfoServices.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(customerInfoServices.get(i).getName());
                pageResultRow.createCell(1).setCellValue(customerInfoServices.get(i).getEmail());
                pageResultRow.createCell(2).setCellValue(customerInfoServices.get(i).getQq());
                pageResultRow.createCell(3).setCellValue(customerInfoServices.get(i).getTel());
                pageResultRow.createCell(4).setCellValue(customerInfoServices.get(i).getWay());
                pageResultRow.createCell(5).setCellValue(customerInfoServices.get(i).getState());
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	@RequestMapping("/affirmPay")
	@ResponseBody
	public AjaxResult affirmPay(Long customerInfoId , BigDecimal pay){
		AjaxResult result = null;
		try{
			customerInfoService.affirmPay(customerInfoId,pay);
			result = new AjaxResult(true,"付款成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("付款失败,请联系管理员！");
		}
		return result;
	}
}
