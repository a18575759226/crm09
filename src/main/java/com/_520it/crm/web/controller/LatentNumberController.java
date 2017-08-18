package com._520it.crm.web.controller;

import com._520it.crm.domain.LatentNumber;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.query.LatentNumberQueryObject;
import com._520it.crm.service.ILatentNumberService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/latentnumber")
public class LatentNumberController {
    @Autowired
    private ILatentNumberService latentNumberService;
    List<LatentNumber> pageResult = null;

    @RequestMapping("")
    public String index() {
        return "latentnumber";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<LatentNumber> list(LatentNumberQueryObject qo) {
        System.out.println(qo.getKeyword());
        System.out.println(qo.getBeginDate());
        System.out.println("=-=-");
        pageResult = latentNumberService.queryLatentNumber(qo);
        System.out.println("---------------");
        return pageResult;
    }

    @RequestMapping("/Excel")
    @ResponseBody
    public void excel(HttpServletResponse response) throws Exception {
        try {
            //设置下载头
            String fileName = new String("template.xls".getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            Workbook wb = new HSSFWorkbook();
            //创建
            Sheet sheet = wb.createSheet("报表数据");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("时间");
            row.createCell(1).setCellValue("销售人");
            row.createCell(2).setCellValue("数量");
            Row pageResultRow = null;
            for (int i = 0; i < pageResult.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(pageResult.get(i).getInputTime().toLocaleString());
                pageResultRow.createCell(1).setCellValue(pageResult.get(i).getMarketingMan());
                pageResultRow.createCell(2).setCellValue(pageResult.get(i).getClientNumber());
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        FileInputStream is = null;
        try {
            String fileName = new String("template.xls".getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            is = new FileInputStream(request.getSession().getServletContext().getRealPath("/excelTemplate/template.xls"));
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxResult uploadExcelFile(MultipartFile excel) throws Exception {
        AjaxResult result = null;
        try {
            Workbook wb = new HSSFWorkbook(excel.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            Row latentnumberRow = null;
            for (int i = 1; i < rowNum; i++) {
                latentnumberRow = sheet.getRow(i);
                System.out.println(getCellValue(latentnumberRow.getCell(0))+"\t");
                System.out.println(getCellValue(latentnumberRow.getCell(1))+"\t");
                System.out.println(getCellValue(latentnumberRow.getCell(2))+"\t");
                System.out.println();
            }
            result = new AjaxResult(true, "导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("导出失败");
        }
        return result;
    }

    public Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return  null;
        }
    }
}
