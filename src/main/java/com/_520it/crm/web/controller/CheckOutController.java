package com._520it.crm.web.controller;

import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CheckOutQueryObject;
import com._520it.crm.service.ICheckOutService;
import com._520it.crm.vo.CheckOut;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checkOut")
public class CheckOutController {
    @Autowired
    ICheckOutService checkOutService;

    @RequestMapping("")
    public String index() {
        return "checkOut";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CheckOutQueryObject qo) {
        PageResult pageResult = null;
        List<CheckOut> checkOuts = checkOutService.selectByCondition(qo);
        System.out.println(checkOuts);
        return new PageResult(1L, checkOuts);
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(CheckOut checkOut) {
        AjaxResult result = null;
        try {
            checkOutService.insert(checkOut);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(CheckOut checkOut) {
        AjaxResult result = null;
        try {
            checkOutService.updateByPrimaryKey(checkOut);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long checkOutId) {
        AjaxResult result = null;
        try {
            checkOutService.deleteByPrimaryKey(checkOutId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/Excel")
    @ResponseBody
    public void excel(HttpServletResponse response, Integer state, CheckOutQueryObject qo) throws Exception {
        List<CheckOut> checkOuts = checkOutService.selectByCondition(qo);
        try {
            String fileNamep = "考勤统计" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";
            String fileName = new String(fileNamep.getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            Workbook wb = new HSSFWorkbook();
            //创建
            Sheet sheet = wb.createSheet("员工考勤数据");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("签到人");
            row.createCell(1).setCellValue("状态 ");
            row.createCell(2).setCellValue("次数 ");
            Row pageResultRow = null;
            for (int i = 0; i < checkOuts.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(checkOuts.get(i).getName());
                pageResultRow.createCell(1).setCellValue(checkOuts.get(i).getState());
                pageResultRow.createCell(2).setCellValue(checkOuts.get(i).getTimes());
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
