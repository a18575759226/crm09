package com._520it.crm.web.controller;

import com._520it.crm.domain.TransferLog;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TransferLogQueryObject;
import com._520it.crm.service.ICustomerInfoService;
import com._520it.crm.service.ITransferLogService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/transferLog")
public class TransferLogController extends BaseController{
	@Autowired
	ITransferLogService transferLogService;
	@Autowired
    ICustomerInfoService customerInfoService;

    @RequiresPermissions("transferLog:index")
    @PermissionName("移交记录列表")
	@RequestMapping("")
	public String index(){
		return "transferLog";
	}

    @RequiresPermissions("transferLog:list")
    @PermissionName("移交记录数据")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(TransferLogQueryObject qo){
		PageResult pageResult = null;
		pageResult = transferLogService.queryPage(qo);
		return pageResult;
	}

    /**
     * 移交
     * @param transferLog
     * @return
     */
    @RequiresPermissions("transferLog:transfer")
    @PermissionName("移交潜在学员")
    @RequestMapping("/transfer")
    @ResponseBody
    public AjaxResult transfer(TransferLog transferLog){
        AjaxResult result = null;
        try{
            transferLogService.transfer(transferLog);
            result = new AjaxResult(true,"移交中,等待主管审核");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("移交失败,请联系管理员！");
        }
        return result;
    }

    @RequiresPermissions("transferLog:transferCheck")
    @PermissionName("审核客户移交")
    @RequestMapping("/transferCheck")
    @ResponseBody
    public AjaxResult transferCheck(TransferLog transferLog){
        AjaxResult result = null;
        try{
            transferLogService.transferCheck(transferLog);
            result = new AjaxResult(true,"审核成功");
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult("审核失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/getLogByCustomerId")
    @ResponseBody
    public AjaxResult getLogByCustomerId(Long customerId){
        AjaxResult result = null;
        try{
            TransferLog transferLog = transferLogService.getLogByCustomerId(customerId);
            if (transferLog != null) {//移交表中有记录,不能再移交
                result = new AjaxResult(false,"该客户移交正在审核中,不能再移交");
            }else {
                result = new AjaxResult(true,"");
            }
            //result = new AjaxResult(true,"审核成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }




    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }

}
