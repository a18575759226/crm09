<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>大客户管理</title>
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/js/views/bigCustomer.js"></script>
	<script type="text/javascript" src="/js/views/trainingRecords.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
	<%--展示大客户信息--%>
	<div data-options="region:'north'" style="height:500px;">
		<!-- 数据表格 -->
		<table id="bigCustomer_datagrid">
			<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">客户名称</th>
				<%--<th data-options="field:'contact',width:1,align:'center',formatter:contactPropertyFormatter">学校联系人</th>--%>
				<th data-options="field:'schoolProperty',width:1,align:'center',formatter:schoolPropertyFormatter">办学性质</th>
				<th data-options="field:'salesman',width:1,align:'center',formatter:salesmanFormatter">负责人</th>
				<th data-options="field:'curriculum',width:1,align:'center',formatter:curriculumFormatter">学制</th>
				<th data-options="field:'starLevel',width:1,align:'center',formatter:starLevelFormatter">星级</th>
				<th data-options="field:'cooperationSchool',width:1,align:'center',formatter:cooperationSchoolFormatter">合作高校</th>
			</tr>
			</thead>
		</table>
	</div>
	<%--展示大客户对应的实训记录--%>
	<div data-options="region:'south'" style="height:200px;">
		<!-- 数据表格 -->
		<table id="trainingRecords_datagrid">
			<thead>
			<tr>
				<th data-options="field:'address',width:1,align:'center'">实训地址</th>
				<th data-options="field:'trainingTime',width:1,align:'center'">实训时间</th>
				<th data-options="field:'trainingContent',width:1,align:'center'">实训内容</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
				<th data-options="field:'trainingResult',width:1,align:'center'">实训效果</th>
			</tr>
			</thead>
		</table>
		<%--<%@ include file="/WEB-INF/views/trainingRecords.jsp"%>--%>
	</div>
</div>

	<!-- 新增编辑对话框 -->
	<div id="bigCustomer_dialog">
		<form id="bigCustomer_form" method="post">
			<table align="center" style="margin-top: 15px;">
				<input type="hidden" name="id">

				<tr>
					<td>客户名称</td>
					<td><input type="text" name="name"></td>
				</tr>
				<%--<tr>--%>
					<%--<td>学校联系人</td>--%>
					<%--<td><input type="text" name="name"></td>--%>
				<%--</tr>--%>
					<td>办学性质</td>
					<td>
						<input id="school_property" class="easyui-combobox" name="schoolProperty.name" data-options="valueField:'name',textField:'name',url:'/systemDictionaryItem/selectListByparentName?parentName=schoolProperty'"/>
					</td>
				<tr>
					<td>学制</td>
					<td>
						<input id="curriculum" class="easyui-combobox" name="curriculum.name" data-options="valueField:'name',textField:'name',url:'/systemDictionaryItem/selectListByparentName?parentName=curriculum'"/>
					</td>
				</tr>
				<tr>
					<td>星级</td>
					<td>
						<input id="starLevel" class="easyui-combobox" name="starLevel.name"
							   data-options="valueField:'name',textField:'name',url:'/systemDictionaryItem/selectListByparentName?parentName=starLevel'"/>
					</td>

				</tr>
				<tr>
					<td>合作高校</td>
					<td>
						<input type="radio" name="cooperationSchool" value="0">否
						<input type="radio" name="cooperationSchool" value="1">是
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="bigCustomer_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
			&nbsp;&nbsp;&nbsp;
			<input id="keyword" type="text" name="keyword" placeholder="学校名称" style="border-style: solid;border-radius:5px;width: 100px">
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchSchool">搜索</a>
			&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-sum" plain="true" onclick="window.open('/bigCustomer/downloadExcel')">导出Excel</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="bigCustomer_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<%--实训js--%>
	<!-- 新增编辑对话框 -->
	<div id="trainingRecords_dialog">
		<form id="trainingRecords_form" method="post">
			<table align="center" style="margin-top: 15px;">
				<input type="hidden" name="id" id="rainingRecords_id">
				<tr>
					<td>实训地址</td>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<td>实训时间</td>
					<td><input type ="text" class="easyui-datebox" name="trainingTime"></td>
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
			</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="trainingRecords_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-record="addRecord">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-record="editRecord">编辑</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="trainingRecords_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-record="saveRecord">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-record="cancelRecord">取消</a>
	</div>
</body>
</html>