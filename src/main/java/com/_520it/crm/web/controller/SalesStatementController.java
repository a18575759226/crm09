package com._520it.crm.web.controller;

import com._520it.crm.domain.SalesStatement;
import com._520it.crm.query.SalesStatementQueryObject;
import com._520it.crm.service.ISalesStatementService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/salesStatement")
public class SalesStatementController {
    @Autowired
    private ISalesStatementService SalesStatementService;
    List<SalesStatement> pageResult = null;

    @RequestMapping("")
    public String index() {
        return "salesStatement";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<SalesStatement> list(SalesStatementQueryObject qo) {
        pageResult = SalesStatementService.querySalesStatement(qo);
        System.out.println("======");
        System.out.println(pageResult.toString());
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
            Sheet sheet = wb.createSheet("销售报表");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("销售时间");
            row.createCell(1).setCellValue("销售金额");
            System.out.println("**********************");
            Row pageResultRow = null;
            for (int i = 0; i < pageResult.size(); i++) {
                pageResultRow = sheet.createRow(i + 1);
                pageResultRow.createCell(0).setCellValue(pageResult.get(i).getSalesTime().toLocaleString());
                pageResultRow.createCell(1).setCellValue(pageResult.get(i).getSalesAmount().toString());

            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/saleChartByLine")
    @ResponseBody
    //线性图标
    public String saleChartByLine(SalesStatementQueryObject qo) throws Exception {
        //把数据查询出来
        List<SalesStatement> voList = SalesStatementService.querySalesStatement(qo);
        //获取到所有的groupTypad的值
        List<String> groupTypeList = new ArrayList<>();//存放销售时间
        List<BigDecimal> saleAmountList = new ArrayList<>();//存放销售额数据
        for (SalesStatement vo : voList) {
            groupTypeList.add(vo.getSalesTime().toString());
            saleAmountList.add(vo.getSalesAmount());
        }
        return "saleChartByLine";
    }
}
