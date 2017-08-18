package com._520it.crm.web.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.Task;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TaskQueryObject;
import com._520it.crm.service.ITaskService;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    ITaskService taskService;

    @RequiresPermissions("task:index")
    @PermissionName("任务列表")
    @RequestMapping("")
    public String index() {
        return "task";
    }

    @RequiresPermissions("task:list")
    @PermissionName("获取任务列表")
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(TaskQueryObject qo) {
        PageResult pageResult = null;
        pageResult = taskService.queryPage(qo);
        return pageResult;
    }

    @RequiresPermissions("task:save")
    @PermissionName("指派任务")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Task task) {
        AjaxResult result = null;
        try {
            taskService.insert(task);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequiresPermissions("task:update")
    @PermissionName("修改任务")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Task task) {
        AjaxResult result = null;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(task.getTime());
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        calendar1.setTime(new Date());
        int day2 = calendar1.get(Calendar.DAY_OF_YEAR);
        if (day2 - day1 > 0) {
            result = new AjaxResult("修改失败,只能修改当天的数据！");
            return result;
        }
        try {
            taskService.updateByPrimaryKey(task);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequiresPermissions("task:updateProcessDescription")
    @PermissionName("修改任务处理描述")
    @RequestMapping("/updateProcessDescription")
    @ResponseBody
    public AjaxResult updateProcessDescription(Task task) {
        AjaxResult result = null;
        String processDescription = task.getProcessDescription();
        task = taskService.selectByPrimaryKey(task.getId());
        task.setProcessDescription(processDescription);
        if (task.getState() != 0) {
            result = new AjaxResult("修改失败,只能修改初始状态的任务！");
            return result;
        }
        try {
            taskService.updateByPrimaryKey(task);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("修改失败,请联系管理员！");
        }
        return result;
    }

    @RequiresPermissions("task:delete")
    @PermissionName("删除任务")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long taskId) {
        AjaxResult result = null;
        try {
            taskService.deleteByPrimaryKey(taskId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }

    /**
     * 指派任务未完成
     *
     * @param taskId
     * @return
     */
    @RequiresPermissions("task:updateDefeat")
    @PermissionName("标记任务失败")
    @RequestMapping("/updateDefeat")
    @ResponseBody
    public AjaxResult updateDefeat(Long taskId) {
        AjaxResult result = null;
        Task task = taskService.selectByPrimaryKey(taskId);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(task.getTime());
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        calendar1.setTime(new Date());
        int day2 = calendar1.get(Calendar.DAY_OF_YEAR);
        if (day2 - day1 > 7) {
            result = new AjaxResult("标记失败,只能标记七天内的任务状态！");
            return result;
        }
        try {
            taskService.updateDefeat(taskId);
            result = new AjaxResult(true, "标记成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("标记失败,请联系管理员！");
        }
        return result;
    }

    /**
     * 指派任务已完成
     *
     * @param taskId
     * @return
     */
    @RequiresPermissions("task:updateFinish")
    @PermissionName("标记任务完成")
    @RequestMapping("/updateFinish")
    @ResponseBody
    public AjaxResult updateFinish(Long taskId) {
        AjaxResult result = null;
        Task task = taskService.selectByPrimaryKey(taskId);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(task.getTime());
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        calendar1.setTime(new Date());
        int day2 = calendar1.get(Calendar.DAY_OF_YEAR);
        if (day2 - day1 > 7) {
            result = new AjaxResult("标记失败,只能标记七天内的任务状态！");
            return result;
        }
        try {
            taskService.updateFinish(taskId);
            result = new AjaxResult(true, "标记成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("标记失败,请联系管理员！");
        }
        return result;
    }
}
