<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/latentnumber.js"></script>
    <title>管理报表</title>
</head>
<body>
<div id="latentnumber_excelUpload">
    <form method="post"  enctype="multipart/form-data" id="latentnumber_excelFrom">
        <table>
            <tr>
                <td><input type="file" name="excel"></td>
                <td><a href="javascript:void(0);" onclick="window.open('/latentnumber/downloadTemplate')">下载模板</a></td>
            </tr>
        </table>
    </form>
</div>
<table id="latentnumber_datagrid"></table>
<div id="latentnumber_toolbar">
    名称关键字查询: <input type="text" name="keyword" class="easyui-textbox"/>
    日期:<input name="beginDate" class="easyui-datebox"/>
    ----<input name="endDate" class="easyui-datebox"/>

    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchKey">提交</a>
    <div style="float:right;">
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"
           data-cmd="reload">刷新</a>
        <%--<a class="easyui-linkbutton btn_showChart" iconCls="icon-search" plain="true">生成报表</a>--%>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="window.open('/latentnumber/Excel')">导出到Excel</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"  data-options="iconCls:'icon-add',plain:true" data-cmd="add">导入Excel</a>
    </div>

</div>
<div id="latentnumber_dialog">
    <form method="post" id="latentnumber_form">
        <input type="hidden" name='id'/>
        <table align="center">
        </table>
    </form>
</div>
<div id="latentnumber_dialog_buttons">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true"
       data-cmd="save">保存</a> <a class="easyui-linkbutton"
                                 iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<div id="latentnumber_excelUpload_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="excelsave">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="excelcancel">取消</a>
</div>
</body>
</html>