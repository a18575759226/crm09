<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实训记录管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/trainingRecords.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="trainingRecords_datagrid">
		<thead>
			<tr>
				<th data-options="field:'address',width:1,align:'center'">实训地址</th>
				<th data-options="field:'trainingTime',width:1,align:'center'">实训时间</th>
				<th data-options="field:'trainingContent',width:1,align:'center'">实训内容</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
				<th data-options="field:'trainingResult',width:1,align:'center'">实训效果</th>
				<th data-options="field:'bigCustomer',width:1,align:'center'">大客户</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="trainingRecords_dialog">
		<form id="trainingRecords_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>实训地址</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>实训时间</td>
				<td><input type="text" name="trainingTime"></td>
			</tr>
			<tr>
				<td>实训内容</td>
				<td><input type="text" name="trainingContent"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
			<tr>
				<td>实训效果</td>
				<td><input type="text" name="trainingResult"></td>
			</tr>
			<tr>
				<td>大客户</td>
				<td><input type="text" name="bigCustomer"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="trainingRecords_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="trainingRecords_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>