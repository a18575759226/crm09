<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员跟进管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/trakcsStudents.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="trakcsStudents_datagrid">
		<thead>
			<tr>
				<th data-options="field:'inputTime',width:1,align:'center'">跟进时间</th>
				<th data-options="field:'customerInfo',width:1,align:'center',formatter:customerInfoFormatter">跟进学员</th>
				<th data-options="field:'employee',width:1,align:'center',formatter:employeeFormatter">咨询人员</th>
				<th data-options="field:'nextTime',width:1,align:'center'">下次跟进时间</th>
				<th data-options="field:'purpose',width:1,align:'center'">跟进目的</th>
				<th data-options="field:'intentionDegree',width:1,align:'center',formatter:intentionDegreeFormatter">意向程度</th>
				<th data-options="field:'qq',width:1,align:'center'">学员qq</th>
				<th data-options="field:'tel',width:1,align:'center'">学员电话</th>
				<th data-options="field:'talkWay',width:1,align:'center',formatter:talkWayFormatter">交流方式</th>
				<th data-options="field:'digest',width:1,align:'center'">摘要</th>
				<th data-options="field:'longTime',width:1,align:'center'">交谈时长</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="trakcsStudents_dialog">
		<form id="trakcsStudents_form" method="post" enctype="multipart/form-data">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>跟进学员</td>
                <td><input id="customerInfo" name="customerInfo.id" class="easyui-combobox" data-options="valueField:'id',textField:'name',
						url:'/customerInfo/selectListForCustomerInfolistForm'"/></td>
				<td>咨询人员</td>
                <td><input  id="employee_id" name="employee.id" class="easyui-combobox" data-options="valueField:'id',textField:'realname',
						url:'/employee/listAll'"/></td>
                <td>跟进时间</td>
                <td><input class="easyui-datebox" name="inputTime"></td>
			</tr>
			<tr>
				<td>学员电话</td>
				<td><input type="text" name="tel"></td>
				<td>学员qq</td>
				<td><input type="text" name="qq"></td>
				<td>交谈时长</td>
				<td><input type="text" name="longTime"></td>
			</tr>
			<tr>
				<td>跟进目的</td>
				<td><input type="text" name="purpose"></td>
				<td>交流方式</td>
				<td><input id="talkWay" class="easyui-combobox" name="talkWay.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=交流方式'" /></td>
				<td>意向程度</td>
				<td><input id="intentionDegree" class="easyui-combobox" name="intentionDegree.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=意向程度'" /></td>
			</tr>
			<tr>
				<td>下次跟进时间</td>
				<td><input class="easyui-datebox" name="nextTime"></td>
			</tr>
			<tr>
				<td>文件(截图)</td>
				<td><input type="file" name="picturePath"></td>
			</tr>
			<tr>
				<td>摘要</td>
				<td colspan="3"><input type="text" name="digest" style="width: 400px;height:50px"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="trakcsStudents_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增记录</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯记录</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="search">查看记录</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除记录</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="trakcsStudents_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>