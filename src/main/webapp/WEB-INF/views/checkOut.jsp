<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>考勤导出管理</title>
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/js/views/checkOut.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="checkOut_datagrid">
	<thead>
	<tr>
		<th data-options="field:'name',width:1,align:'center'">签到人</th>
		<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
		<th data-options="field:'times',width:1,align:'center',formatter:timesFormatter">次数</th>
	</tr>
	</thead>
</table>
<!-- 数据表格CRUD按钮 -->
<div id="checkOut_datagrid_tb">
	<div>
		状态:
		<select id="stateCondition_select" class="easyui-combobox" name="state" style="width:100px;">
			<option value="-1">全部</option>
			<option value="1">正常上班</option>
			<option value="0">迟到早退</option>
		</select>
		日期:<input name="beginDate" class="easyui-datebox"/>
		----<input name="endDate" class="easyui-datebox"/>
		<a class="easyui-linkbutton" iconCls="icon-search"
		   plain="true" onclick="searchContent()">搜索</a>
		<div style="float: right">
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
			<a id="excel" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-url="/checkOut/Excel?state="  >导出到Excel</a>
		</div>
	</div>
</div>
<!-- 对话框保存取消按钮 -->
<div id="checkOut_dialog_bt">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>