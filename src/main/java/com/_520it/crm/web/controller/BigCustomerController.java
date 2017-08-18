package com._520it.crm.web.controller;

import com._520it.crm.domain.BigCustomer;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.BigCustomerQueryObject;
import com._520it.crm.service.IBigCustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/bigCustomer")
public class BigCustomerController {
	@Autowired
	IBigCustomerService bigCustomerService;
	
	@RequestMapping("")
	public String index(){
		return "bigCustomer";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(BigCustomerQueryObject qo){
		PageResult pageResult = null;
		pageResult = bigCustomerService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(BigCustomer bigCustomer){
		AjaxResult result = null;
		try{
			bigCustomerService.insert(bigCustomer);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(BigCustomer bigCustomer){
		AjaxResult result = null;
		try{
			bigCustomerService.updateByPrimaryKey(bigCustomer);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long bigCustomerId){
		AjaxResult result = null;
		try{
			bigCustomerService.deleteByPrimaryKey(bigCustomerId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

    @RequestMapping("/listBigCustomerForSchoolContact")
    @ResponseBody
    public List<BigCustomer> listBigCustomerForSchoolContact(){
        List<BigCustomer> result = null;
        result = bigCustomerService.selectAll();
        return result;
    }

    /**
     * 查询出所有符合条件的大客户并导出到Excel,给用户下载
     * @param response
     * @return
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response){
        //设置下载头
        String fileName = null;
        try {
            fileName = new String("大客户信息.xls".getBytes("utf-8"),"iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        List<BigCustomer> bigCustomers =  bigCustomerService.selectAll();
        Workbook wb = new HSSFWorkbook();
        //创建工作本sheet
        Sheet sheet = wb.createSheet("大客户信息");
        //输出第一行
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("客户名称");
        row.createCell(2).setCellValue("办学性质");
        row.createCell(3).setCellValue("负责人");
        row.createCell(4).setCellValue("学制");
        row.createCell(5).setCellValue("星级");
        row.createCell(6).setCellValue("是否合作高校");
        Row bigCustomerRow = null;
        for (int i = 0; i < bigCustomers.size(); i++) {
            bigCustomerRow = sheet.createRow(i + 1);
            if (bigCustomers.get(i)!=null){
                bigCustomerRow.createCell(0).setCellValue(bigCustomers.get(i).getName());
            }
            bigCustomerRow.createCell(2).setCellValue(bigCustomers.get(i).getSchoolProperty().getName());
            bigCustomerRow.createCell(3).setCellValue(bigCustomers.get(i).getSalesman().getRealname());
            bigCustomerRow.createCell(4).setCellValue(bigCustomers.get(i).getCurriculum().getName());
            bigCustomerRow.createCell(5).setCellValue(bigCustomers.get(i).getStarLevel().getName());
            bigCustomerRow.createCell(6).setCellValue(bigCustomers.get(i).getCooperationSchool());
        }
        //输出到浏览器
        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
