<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学校联系人管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/schoolContact.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="schoolContact_datagrid">
		<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">联系人姓名</th>
				<th data-options="field:'gender',width:1,align:'center',formatter:genderFormatter">性别</th>
				<th data-options="field:'birthday',width:1,align:'center'">生日</th>
				<th data-options="field:'qq',width:1,align:'center'">QQ</th>

				<th data-options="field:'school',width:1,align:'center',formatter:schoolFormatter">所属学校</th>
				<th data-options="field:'tel',width:1,align:'center'">联系电话</th>
				<th data-options="field:'email',width:1,align:'center'">邮箱</th>
				<th data-options="field:'hobby',width:1,align:'center'">特殊爱好</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="schoolContact_dialog">
		<form id="schoolContact_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>联系人姓名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<input id="gender" class="easyui-combobox" name="gender.name" data-options="valueField:'name',textField:'name',url:'/systemDictionaryItem/selectListByparentName?parentName=gender'"/>
				</td>
			</tr>
			<tr>
				<td>生日</td>
				<%--<td><input type="text" name="birthday"></td>--%>
				<td><input id="dd" name="birthday" type="text" class="easyui-datebox"></td>

			</tr>
			<tr>
				<td>QQ</td>
				<td><input type="text" name="qq"></td>
			</tr>

			<tr>
				<td>所属学校</td>
				<td>
					<input id="school" class="easyui-combobox" name="school.id" data-options="valueField:'id',textField:'name',url:'/bigCustomer/listBigCustomerForSchoolContact'" />
				</td>
			</tr>

			<tr>
				<td>联系电话</td>
				<td><input type="text" name="tel"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>特殊爱好</td>
				<td><input type="text" name="hobby"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="schoolContact_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="schoolContact_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>