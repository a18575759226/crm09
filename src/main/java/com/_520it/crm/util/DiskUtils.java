package com._520it.crm.util;

import java.io.File;

/**
 * 网盘工具类
 * @author
 * @date 2017/7/19
 */
public class DiskUtils {
    private DiskUtils(){}
    private static String rootPath = "D:"+File.separator+"root";

    public static String getUserPath(){
        String username = UserUtils.getCurrentUser().getUsername();
        return rootPath +File.separator+ username;
    }
    /**
     * 创建用户网盘根目录
     */
    public static void mkUserRootDic(){
        //String username = UserUtils.getCurrentUser().getUsername();
        //String userRootPath = rootPath +File.separator+ username;
        //File fileDisk = new File(getUserPath());
        //if (!fileDisk.exists()) {
        //    fileDisk.mkdirs();
        //}
        mkUserDir(getUserPath());
    }
    public static void main(String[] args) {
        System.out.println(File.separator);
    }

    public static void mkUserDir(String filePath){
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
