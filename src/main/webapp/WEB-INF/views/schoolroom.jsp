<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教室管理管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/schoolroom.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="schoolroom_datagrid">
		<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">教室名称</th>
				<th data-options="field:'address',width:1,align:'center'">教室地址</th>
				<th data-options="field:'institute',width:1,align:'center',formatter:instituteFormatter">所属学院</th>
				<th data-options="field:'seatnumber',width:1,align:'center'">座位数</th>
				<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">教室状态</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="schoolroom_dialog">
		<form id="schoolroom_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>教室名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>教室地址</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>所属学院</td>
				<td><input class="easyui-combobox" name="institute.id"
				data-options="valueField:'id',textField:'name',url:'/institute/selectListForInstitutelistForm'"></td>
			</tr>
			<tr>
				<td>座位数</td>
				<td><input type="text" name="seatnumber"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="schoolroom_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="schoolroom_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>