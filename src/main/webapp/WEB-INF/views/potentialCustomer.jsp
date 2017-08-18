<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源池客户管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/potentialCustomer.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="potentialCustomer_datagrid">
		<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">客户姓名</th>
				<th data-options="field:'gender',width:1,align:'center',formatter:genderFormatter">客户性别</th>
				<th data-options="field:'age',width:1,align:'center'">客户年龄</th>
				<th data-options="field:'tel',width:1,align:'center'">客户电话</th>
				<th data-options="field:'qq',width:1,align:'center'">客户qq</th>
				<th data-options="field:'email',width:1,align:'center'">客户邮箱</th>
				<th data-options="field:'inputTime',width:1,align:'center'">录入时间</th>
				<th data-options="field:'inChargeUser',width:1,align:'center',formatter:iinChargeUserFormatter">负责人</th>
				<th data-options="field:'inputUser',width:1,align:'center',formatter:inputUserFormatter">录入人</th>
				<th data-options="field:'customerSource',width:1,align:'center',formatter:customerSourceFormatter">客户来源</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<%--<div id="potentialCustomer_dialog">
		<form id="potentialCustomer_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>客户来源</td>
				<td><input type="text" name="customerSource"></td>
			</tr>
			<tr>
				<td>客户邮箱</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>录入时间</td>
				<td><input type="text" name="inputTime"></td>
			</tr>
			<tr>
				<td>客户年龄</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>客户姓名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>客户电话</td>
				<td><input type="text" name="tel"></td>
			</tr>
			<tr>
				<td>客户性别</td>
				<td><input type="text" name="gender"></td>
			</tr>
			<tr>
				<td>负责人</td>
				<td><input type="text" name="inChargeUser"></td>
			</tr>
			<tr>
				<td>录入人</td>
				<td><input type="text" name="inputUser"></td>
			</tr>
			<tr>
				<td>客户qq</td>
				<td><input type="text" name="qq"></td>
			</tr>
			<tr>
				<td>历史id</td>
				<td><input type="text" name="customerInfo_id"></td>
			</tr>
		</table>
		</form>
	</div>--%>
	<!-- 数据表格CRUD按钮 -->
	<div id="potentialCustomer_datagrid_tb">
		<div>
			<%--<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a--%>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="getOutToCustomerInfo">取出</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="potentialCustomer_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>