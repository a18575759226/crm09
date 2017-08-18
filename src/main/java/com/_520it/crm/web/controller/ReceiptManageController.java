package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com._520it.crm.domain.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.ReceiptManage;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ReceiptManageQueryObject;
import com._520it.crm.service.IReceiptManageService;

@Controller
@RequestMapping("/receiptManage")
public class ReceiptManageController {
    @Autowired
    IReceiptManageService receiptManageService;

    @RequestMapping("")
    public String index() {
        return "receiptManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(ReceiptManageQueryObject qo) {
        PageResult pageResult = null;
        pageResult = receiptManageService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/expendMethodByReceiptManage")
    @ResponseBody
    public List<ReceiptManage> expendMethodByReceiptManage() {
        List<ReceiptManage> result = receiptManageService.selectAll();
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(ReceiptManage receiptManage) {
        AjaxResult result = null;
        try {
            receiptManageService.insert(receiptManage);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(ReceiptManage receiptManage) {
        AjaxResult result = null;
        try {
            receiptManageService.updateByPrimaryKey(receiptManage);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long receiptManageId) {
        AjaxResult result = null;
        try {
            receiptManageService.deleteByPrimaryKey(receiptManageId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }
}
