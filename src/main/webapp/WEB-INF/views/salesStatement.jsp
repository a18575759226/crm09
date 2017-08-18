<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/salesStatement.js"></script>
    <title>销售报表</title>
</head>
<body>

<table id="salesStatement_datagrid"></table>
<div id="salesStatement_toolbar">
    开始时间:<input name="beginDate" class="easyui-datebox"/>
    结束时间:<input name="endDate" class="easyui-datebox"/>

    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchKey">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true"
       data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="highChart">生成报表</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="window.open('/salesStatement/Excel')">导出到Excel</a>

</div>
<div id="salesStatement_dialog">
    <form method="post" id="salesStatement_form">
        <input type="hidden" name='id'/>
        <table align="center">
        </table>
    </form>
</div>
</body>
</html>