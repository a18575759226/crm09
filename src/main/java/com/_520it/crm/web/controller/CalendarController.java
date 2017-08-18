package com._520it.crm.web.controller;

import com._520it.crm.domain.Calendar;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private ICalendarService calendarService;
    @RequestMapping("")
    public String handleRequest() throws Exception {
        System.out.println("CalendarController.handleRequest");
        return "calendar";
    }
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Calendar calendar){
        System.out.println(calendarService);
        AjaxResult result = null;
        try{
            calendarService.insert(calendar);
            result = new AjaxResult(true,"保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员!");
        }
        return result;
    }
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Calendar> selectAll(Calendar calendar){
        List<Calendar> calendars = calendarService.selectAll();
        return calendars;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long delId) {
        System.out.println("CalendarController.delete");
        AjaxResult result = null;
        try {
            calendarService.deleteByPrimaryKey(delId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

}
