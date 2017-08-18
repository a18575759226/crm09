<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户移交记录管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/transferLog.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="transferLog_datagrid">
		<thead>
			<tr>
				<th data-options="field:'customer',width:1,align:'center',formatter:customerFormatter">潜在客户</th>
				<th data-options="field:'overTime',width:1,align:'center'">移交时间</th>
				<th data-options="field:'currentPrincipal',width:1,align:'center',formatter:currentPrincipalFormatter">原始责任人</th>
				<th data-options="field:'accepter',width:1,align:'center',formatter:accepterFormatter">接收人</th>
				<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">审核状态</th>
				<th data-options="field:'checkTime',width:1,align:'center'">审核时间</th>
				<th data-options="field:'checker',width:1,align:'center',formatter:checkerFormatter">审核人</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="transferLog_dialog">
		<form id="transferLog_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>潜在客户</td>
				<td><input type="text" name="customer"></td>
			</tr>
			<tr>
				<td>移交时间</td>
				<td><input type="text" name="overTime"></td>
			</tr>
			<tr>
				<td>当前责任人</td>
				<td><input type="text" name="currentPrincipal"></td>
			</tr>
			<tr>
				<td>接收人</td>
				<td><input type="text" name="accepter"></td>
			</tr>
			<tr>
				<td>审核状态</td>
				<td><input type="text" name="state"></td>
			</tr>
			<tr>
				<td>审核时间</td>
				<td><input type="text" name="checkTime"></td>
			</tr>
			<tr>
				<td>审核人</td>
				<td><input type="text" name="checker"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>


		</table>
		</form>
	</div>

	<div id="transferLog_datagrid_tb">
		<div>
			<shiro:hasPermission name="transferLog:transferCheck">
				<a id="check_button" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="check">审核</a>
			</shiro:hasPermission>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="transferLog_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<%--审核对话框--%>
	<div id="transferLog_datagrid_check">
		<div style="margin-top: 50px;margin-left: 85px">
			<form id="check_form" method="post">
				<input type="radio" name="state" value="1">通过
				<input type="radio" name="state" value="2">不通过
			</form>
		</div>
	</div>
	<div id="transferLog_datagrid_btn">
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="checkConfirm">审核</a>
	</div>
</body>
</html>