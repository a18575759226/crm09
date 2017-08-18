<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试管理管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/exam.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="exam_datagrid">
		<thead>
			<tr>
				<th data-options="field:'customerInfoName',width:1,align:'center',formatter:studentNameFormatter">姓名</th>
				<th data-options="field:'coursename',width:1,align:'center'">科目</th>
				<th data-options="field:'sn',width:1,align:'center'">准考证号</th>
				<th data-options="field:'examDate',width:1.5,align:'center'">时间</th>
				<th data-options="field:'schoolroom',width:1,align:'center',formatter:schoolroomFormatter">教室</th>
				<th data-options="field:'customerInfoQQ',width:1,align:'center',formatter:QQFormatter">QQ</th>
				<th data-options="field:'customerInfoTel',width:1,align:'center',formatter:telFormatter">电话</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
				<th data-options="field:'examResult',width:1,align:'center',formatter:examResultFormatter">考试结果</th>
				<th data-options="field:'employee',width:1,align:'center',formatter:employeeFormatter">审核人</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="exam_dialog">
		<form id="exam_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>科目</td>
				<td><input type="text" name="coursename"></td>
			</tr>
			<tr>
				<td>学号</td>
				<td><input type="text" name="sn"></td>
			</tr>
			<tr>
				<td>考试时间</td>
				<td><input class="easyui-datetimebox" name="examDate"></td>
			</tr>
			<tr>
				<td>考试学员</td>
				<td><input class="easyui-combobox" name="customerInfo.id" 
				data-options="valueField:'id',textField:'name',url:'/customerInfo/selectListForCustomerInfolistForm'"></td>
			</tr>
			<tr>
				<td>考试教室</td>
				<td><input class="easyui-combobox" name="schoolroom.id"
				data-options="valueField:'id',textField:'name',url:'/schoolroom/selectAll'"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="exam_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a id="exam_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
			<a id="exam_checkOKBtn" class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="checkOK">审核合格</a>
			<a id="exam_checkUNBtn" class="easyui-linkbutton" iconCls="icon-no" plain="true" data-cmd="checkUN">审核不合格</a>
		<input type="text" class="easyui-textbox" placeholder="科目/学号" name="keyword">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-cmd="searchContent">搜索</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="exam_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>