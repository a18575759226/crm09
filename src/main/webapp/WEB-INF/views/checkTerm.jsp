<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>checkTerm管理</title>
	<link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui/themes/default/easyui.css"><!-- 样式包 -->
	<link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui/themes/icon.css"><!-- 图标的样式 -->
	<script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script><!-- jQuery核心包 -->
	<script type="text/javascript" src="/static/js/jquery-easyui/copyeasyuiforsi/jquery.easyui.mincopy.js"></script>
	<!-- EaysUI核心包 -->
	<script type="text/javascript" src="/static/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script><!-- 中文包 -->
	<script type="text/javascript" src="/static/js/jquery-easyui/base.js"></script><!-- 中文包 -->
	<script type="text/javascript" src="/js/views/checkTerm.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="checkTerm_datagrid">
	<thead>
	<th data-options="field:'name',width:1,align:'center'">姓名</th>
	<th data-options="field:'signIp',width:1,align:'center'">签到IP</th>
	<th data-options="field:'signInTime',width:1,align:'center'">签到时间</th>
	<th data-options="field:'signOutTime',width:1,align:'center'">签退时间</th>
	<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
	<th data-options="field:'patchTime',width:1,align:'center'">补签时间</th>
	</tr>
	</thead>
</table>
<!-- 数据表格CRUD按钮 -->
<div id="checkTerm_datagrid_tb">
	<div>
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="signIn()">签到</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="signOut()">签退</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="patch()">补签</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload()">刷新</a>
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
		<a id="excel" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-url="/checkTerm/Excel?state=">导出考勤明细表</a>
	</div>
</div>
</body>
</html>