package com._520it.crm.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com._520it.crm.domain.Courselist;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SchoolClass;
import com._520it.crm.domain.Schoolroom;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourselistQueryObject;
import com._520it.crm.service.ICourselistService;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.ISchoolClassService;
import com._520it.crm.service.ISchoolroomService;
import com._520it.crm.service.impl.EmployeeServiceImpl;
import com._520it.crm.service.impl.SchoolroomServiceImpl;
import com._520it.crm.util.PermissionName;


@Controller
@RequestMapping("/courselist")
public class CourselistController extends BaseController{
    @Autowired
    ICourselistService courselistService;
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    ISchoolroomService schoolroomService;
    @Autowired
    ISchoolClassService schoolClassService;
    @RequestMapping("")
    public String index() {
        return "courselist";
    }
    @RequiresPermissions("courselist:list")
    @PermissionName("课程列表")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CourselistQueryObject qo) {
        PageResult pageResult = null;
        pageResult = courselistService.queryPage(qo);
        return pageResult;
    }
    @RequiresPermissions("courselist:save")
    @PermissionName("添加课程表")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Courselist courselist) {
        System.out.println(courselist);
        AjaxResult result = null;
        try {
            courselistService.insert(courselist);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }
    @RequiresPermissions("courselist:update")
    @PermissionName("修改课程表")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Courselist courselist) {
        AjaxResult result = null;
        try {
            courselistService.updateByPrimaryKey(courselist);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }
    @RequiresPermissions("courselist:delete")
    @PermissionName("删除课程表")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long courselistId) {
        AjaxResult result = null;
        try {
            courselistService.deleteByPrimaryKey(courselistId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }
    @RequiresPermissions("courselist:checkBycourselistId")
    @PermissionName("审核课程表")
    @RequestMapping("/checkBycourselistId")
    @ResponseBody
    public AjaxResult checkBycourselistId(Long courselistId) {
        AjaxResult result = null;
        try {
            courselistService.checkBycourselistId(courselistId);
            result = new AjaxResult(true, "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("审核失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/selectListForCourselistForm")
    @ResponseBody
    public List<Courselist> selectListForCourselistForm() {
        List<Courselist> result = courselistService.selectAll();
        return result;
    }
    //导出Excel
    @RequiresPermissions("courselist:Excel")
    @PermissionName("导出课程表")
    @RequestMapping("/Excel")
    @ResponseBody
    public void Excel(HttpServletResponse response) throws Exception {
    	List<Courselist> pageResult = courselistService.selectAll();
    	try {
            //设置下载头
            String fileName = new String("template.xls".getBytes("utf-8"), "iso-8859-1");
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            Workbook wb = new HSSFWorkbook();
            //创建工作本
            Sheet sheet = wb.createSheet("课程安排表");
            //设置默认列宽
            sheet.setDefaultColumnWidth(11);
            //设置具体列宽(第一个参数表示那一列,第二个参数表示列的宽度)
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(2, 1500);
            Row row = sheet.createRow(0);
            //WritableCellFormat cellFormat = new WritableCellFormat();
            //设置单元格水平居中
            //cellFormat.setAlignment(Alignment.CENTRE);
            //设置单元格垂直居中
            //cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            //设置表头
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("日期");
            row.createCell(2).setCellValue("星期");
            row.createCell(3).setCellValue("课程");
            row.createCell(4).setCellValue("班级");
            row.createCell(5).setCellValue("班主任");
            row.createCell(6).setCellValue("任课老师");
            row.createCell(7).setCellValue("教室");
            row.createCell(8).setCellValue("备注");
            Row pageResultRow = null;
            for (int i=0;i<pageResult.size();i++){
                 pageResultRow = sheet.createRow(i + 1);
                //数字表示列,i表示行
                pageResultRow.createCell(0).setCellValue(pageResult.get(i).getId());
                Date date = pageResult.get(i).getDate();
                pageResultRow.createCell(1).setCellValue((date.getYear()+1900)+"-"+(date.getMonth()-1)+"-"+date.getDate());
                pageResultRow.createCell(2).setCellValue(pageResult.get(i).getWeekday());
                pageResultRow.createCell(3).setCellValue(pageResult.get(i).getCoursename());
                pageResultRow.createCell(4).setCellValue(pageResult.get(i).getSchoolclass().getName());
                pageResultRow.createCell(5).setCellValue(pageResult.get(i).getHeadTeacher().getRealname());
                pageResultRow.createCell(6).setCellValue(pageResult.get(i).getTeacher().getRealname());
                pageResultRow.createCell(7).setCellValue(pageResult.get(i).getSchoolroom().getName());
                pageResultRow.createCell(8).setCellValue(pageResult.get(i).getRemark());
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //导入Excel
    @RequiresPermissions("courselist:Excel")
    @PermissionName("导入课程表")
    @RequestMapping("/importExcel")
    @ResponseBody
    public AjaxResult downloadTemplate(MultipartFile excel){
    	System.out.println(excel.getOriginalFilename());
    	System.out.println("===============");
    	AjaxResult result = null;
    	Courselist courselist = new Courselist();
    	try {
			HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			Row latennumber = null;
			for (int i = 0; i < rowNum+1; i++) {
				latennumber = sheet.getRow(i);
				courselist.setDate(new Date());
				courselist.setWeekday(5);
				courselist.setCoursename("语文");
				//班级
				//Long classId = schoolClassService.selectByPrimaryRealname(getCellValue(latennumber.getCell(4)).toString());
				SchoolClass schoolclass = new SchoolClass();
				schoolclass.setId(1L);
				courselist.setSchoolclass(schoolclass);
				//班主任
				//Long employeeId = employeeService.selectByPrimaryRealname(getCellValue(latennumber.getCell(5)).toString());
				Employee headTeacher = new Employee();
				headTeacher.setId(1L);
				courselist.setHeadTeacher(headTeacher);
				//教室
				//Long roomId = schoolroomService.selectByPrimaryRealname(getCellValue(latennumber.getCell(7)).toString());
				Schoolroom schoolroom  = new Schoolroom();
				schoolroom.setId(1L);
				courselist.setSchoolroom(schoolroom);
				//讲师
				//Long teacherId = employeeService.selectByPrimaryRealname(getCellValue(latennumber.getCell(6)).toString());
				Employee teacher = new Employee();
				teacher.setId(1l);
				courselist.setTeacher(teacher);
				courselist.setRemark("你们很棒");
				//将数据插入到数据库
				courselistService.insert(courselist);
			}
			result = new AjaxResult(true,"恭喜您导入成功!");
    	} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("导入失败,请检查文件格式或内容是否正确!");
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
			return null;
		}
    }
}
