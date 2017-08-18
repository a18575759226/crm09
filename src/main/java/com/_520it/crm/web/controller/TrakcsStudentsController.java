package com._520it.crm.web.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.TrakcsStudents;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TrakcsStudentsQueryObject;
import com._520it.crm.service.ITrakcsStudentsService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/trakcsStudents")
public class TrakcsStudentsController {
    @Autowired
    ITrakcsStudentsService trakcsStudentsService;

    @RequestMapping("")
    public String index() {
        return "trakcsStudents";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(TrakcsStudentsQueryObject qo) {
        PageResult pageResult = null;
        pageResult = trakcsStudentsService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(TrakcsStudents trakcsStudents, MultipartFile picturePath) {
        AjaxResult result = null;
        System.out.println(picturePath.getContentType());//image/jpeg  文件的类型
        System.out.println(picturePath.getName());//文件在前台表单的名称 file
        System.out.println(picturePath.getOriginalFilename());//文件的原始名称  小黄人.jpg
        System.out.println(picturePath.getSize());//文件的大小  687176
        //设置文件存储地址
        String filename = "D:/" +picturePath.getOriginalFilename();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            inputStream = picturePath.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            //设置跟进对象内的picture字段
            trakcsStudents.setPicture(filename);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            trakcsStudentsService.insert(trakcsStudents);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(TrakcsStudents trakcsStudents) {
        AjaxResult result = null;
        try {
            trakcsStudentsService.updateByPrimaryKey(trakcsStudents);
            result = new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新失败,请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long trakcsStudentsId) {
        AjaxResult result = null;
        try {
            trakcsStudentsService.deleteByPrimaryKey(trakcsStudentsId);
            result = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除失败,请联系管理员！");
        }
        return result;
    }
}
