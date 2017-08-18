<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务安排管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/task.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="task_datagrid">
		<thead>
			<tr>
				<th data-options="field:'time',width:1,align:'center'">日期</th>
				<th data-options="field:'processor',width:1,align:'center',formatter:processorFormatter">处理人</th>
				<%--<th data-options="field:'processor',width:1,align:'center',formatter:processorNameFormatter">员工姓名</th>--%>
				<th data-options="field:'taskDescription',width:1,align:'center',formatter: taskDescriptionFormatter">任务描述</th>
				<th data-options="field:'processDescription',width:1,align:'center',formatter: processDescriptionFormatter">处理描述</th>
				<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 新增对话框 -->
	<div id="task_dialog">
		<form id="task_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<input type="hidden" name="deptId">
			<tr>
				<td>日期</td>
				<td><input type="text" class="easyui-datebox" name="time" id="timeId"></td>
			</tr>
			<tr>
				<td>处理人</td>
				<td><input type="text" name="processor.id" id="processor_id" class="easyui-combobox" data-options="valueField:'id',textField:'realname',
						url:'/employee/listEmployeeByTask'"></td>
			</tr>
			<tr>
				<td>任务描述</td>
				<td><input type="text" name="taskDescription"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 修改任务描述框 -->
	<div id="describeTask_dialog">
		<form id="describeTask_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
				<td>任务处理描述</td>
				<td><input type="text" name="processDescription"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="task_datagrid_tb">
		<div>
			<%--权限控制--%>
			<shiro:hasPermission name="task:save">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">指派任务</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="task:update">
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">修改任务</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="task:delete">
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除任务</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="task:updateFinish">
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="updateFinish">标记完成</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="task:updateDefeat">
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="updateDefeat">标记失败</a>
			</shiro:hasPermission>
				<%--权限控制--%>
			<shiro:hasPermission name="task:updateProcessDescription">
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="describeTask">修改处理描述</a>
			</shiro:hasPermission>

		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="task_dialog_bt">
		<%--<shiro:lacksPermission name="task:save">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveDescribeTask">确认修改</a>
		</shiro:lacksPermission>--%>
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 对话框保存取消按钮 -->

	<div id="describeTask_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveDescribeTask">确认修改</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>