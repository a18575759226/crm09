<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件夹管理管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/directory.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="directory_datagrid">
		<thead>
			<tr>
				<%--,formatter:iconFormatter--%>
				<th data-options="field:'icon',width:1,align:'center'">图标</th>
				<th data-options="field:'parent',width:1,align:'center'">上级目录</th>
				<th data-options="field:'name',width:1,align:'center'">文件夹名称</th>
				<th data-options="field:'dicPath',width:1,align:'center'">文件夹路径</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="directory_dialog">
		<form id="directory_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>上级目录</td>
				<td><input type="text" name="parent.id"></td>
			</tr>
			<tr>
				<td>文件夹名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>文件夹路径</td>
				<td><input type="text" name="dicPath"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="directory_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="upload">上传文件</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="download">下载文件</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
		<div>
			路径:${dicMenuVOS}
			<a href="javascript:void(0);" onclick="intoRoot()">根路径</a>
			<input type="text" name="parentId">
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="directory_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>

	<div id="upload_dialog">
		<form method="post" enctype="multipart/form-data" style="margin-left: 65px;margin-top: 35px">
			<input type="file" name="file">
		</form>
	</div>
	<!-- 文件上传button-->
	<div id="upload_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="uploadConfirm">上床</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>