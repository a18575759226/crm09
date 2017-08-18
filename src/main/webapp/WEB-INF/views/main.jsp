<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台首页</title>
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/static/js/views/main.js"></script>
	<script type="text/javascript" src="/js/views/checkTerm.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		h1 {
			margin-top: 10px;
			margin-left: 15px;
			ont-style: italic;
			font-weight: bold;
			font-size: 25px;
			font-family: "Fira Code Light";
			text-align: center;
			color: #0E2D5F;
		}

		.right_border_no {
			border-right: none;
		}
	</style>
</head>
<body>
<div id="logoutSystem" class="easyui-layout" fit="true">
	<div data-options="region:'north',border:false" style="height:65px;background:url('/static/images/index_head.jpg');" class="right_border_no">
		<iframe align="right" name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=16&num=1" width="150" height="65" frameborder="0" marginwidth="600" marginheight="100" scrolling="no"></iframe>
		<div style="float: left;">
			<h1>客户关系管理系统</h1>
		</div>
		<div style="float: right;margin-top: 25px;margin-right: 15px;">
			当前用户:
			<a href="/user.jsp" style="text-decoration: none;">
				[<shiro:principal property="realname"></shiro:principal>]
			</a>
			[<a class="easyui-linkbutton" plain="true" onclick="editpassword();">修改密码</a>]
			[<a href="/logout" style="text-decoration: none">退出</a>]
		</div>
	</div>
	<div data-options="region:'west',title:'菜单',border:false" style="width:180px;" class="right_border_no">
		<!-- 菜单树 -->
		<ul id="main_menu"></ul>
	</div>
	<div data-options="region:'center',border:false" style="background: url('/static/images/main.jpg');background-size: cover">
		<div id="main_tabs" class="easyui-tabs" fit="true">
			<div title="欢迎页" closable="true">
				<div id="protal_main" style="width:auto;height:auto;">
					<!--定义每列宽度占比-->
					<div style="width:40%;"></div>
					<div style="width:40%;"></div>
					<div style="width:20%;"></div>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:20px;background:url('/static/images/index_tail.jpg'); ">
		<center>版权信息@第九工作室</center>
		<%--#84B1ED--%>
	</div>
</div>
<!-- 定义对话框 -->
<div id="main_dialog">
	<form id="mian_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<tr>
				<td>原始密码</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input id="password" name="newpassword" validType="length[6,32]" class="easyui-validatebox"
				           required="true" type="password" value=""/></td>
			</tr>
			<tr>
				<td> 确认密码</td>
				<td><input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"
				           validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"/></td>
			</tr>
		</table>
	</form>
</div>
<!-- 对话框底部按钮 -->
<div id="main_dialog_bt">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancel();">取消</a>
</div>

<%--面板--%>
<div id="protal_one" class="easyui-panel" title="每日任务"
     style="height:500px;"
     data-options="closable:true, collapsible:true">
	<table id="index_table"></table>
</div>

<div id="protal_two" class="easyui-panel" title="日程安排"
     style="height:400px;"
     data-options="closable:true, collapsible:true">
	<div id="index_schedule" class="easyui-calendar" style="width:416px;height:371px;"></div>
</div>
<div id="protal_three" class="easyui-panel"
     style="height:500px;border: none"
     data-options="closable:true, collapsible:true">
	<a onclick="signIn()">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="/static/images/111.gif"
		                                                                       width="50%" align="middle"/>
	</a>
	<a onclick="signOut()">
		<img border="0" src="/static/images/222.gif" width="80%" align="middle"/>
	</a>
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
</body>
</html>