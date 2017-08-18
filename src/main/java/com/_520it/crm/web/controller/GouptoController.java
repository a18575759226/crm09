package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._520it.crm.domain.LatentNumber;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.Goupto;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.GouptoQueryObject;
import com._520it.crm.service.IGouptoService;

@Controller
@RequestMapping("/goupto")
public class GouptoController {
    @Autowired
    private IGouptoService gouptoService;
    List<Goupto> pageResult = null;

    @RequestMapping("")
    public String index() {
        return "goupto";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(GouptoQueryObject qo) {
        PageResult pageResult = null;
        pageResult = gouptoService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Goupto goupto) {
        AjaxResult result = null;
        try {
            gouptoService.insert(goupto);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Goupto goupto) {
        AjaxResult result = null;
        try {
            gouptoService.updateByPrimaryKey(goupto);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long gouptoId) {
        AjaxResult result = null;
        try {
            gouptoService.deleteByPrimaryKey(gouptoId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
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
            List<Goupto> gouptos = gouptoService.selectAll();
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
            for (int i = 0; i < gouptos.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(gouptos.get(i).getRealName());
                pageResultRow.createCell(1).setCellValue(gouptos.get(i).getReceiptAmount());
                pageResultRow.createCell(2).setCellValue(gouptos.get(i).getSalesWater());
                pageResultRow.createCell(3).setCellValue(gouptos.get(i).getOtherCosts());
                pageResultRow.createCell(4).setCellValue(gouptos.get(i).getReceiptTime());
                pageResultRow.createCell(5).setCellValue(gouptos.get(i).getQq());
                pageResultRow.createCell(6).setCellValue(gouptos.get(i).getPhone());
                pageResultRow.createCell(7).setCellValue(gouptos.get(i).getOldClass());
                pageResultRow.createCell(8).setCellValue(gouptos.get(i).getIntoClass());
                pageResultRow.createCell(9).setCellValue(gouptos.get(i).getSalesman());
                pageResultRow.createCell(10).setCellValue(gouptos.get(i).getState());
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
