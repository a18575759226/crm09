<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程安排管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/courselist.js"></script> 
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;height:200px;" fit="true">
	<div data-options="region:'west',title:'课程表目录',split:true" style="width:200px;" >
     <h3 style="color: #9eb9c2" align="center">请点击您所在的班级</h3>
     <table id="schoolClass_datagrid">
		<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">班级</th>
			</tr>
		</thead>
	</table>    
    </div> 
	<!-- 数据表格 -->
	<div data-options="region:'center',title:'课程表明细'" style="padding:3px;background:#eee;">
    <h3 style="color: #9eb9c2" align="center">您的课程表显示如下:</h3>
	<table id="courselist_datagrid">
		<thead>
			<tr>
				<!-- <th data-options="field:'course',width:1,align:'center'">课程</th> -->
				<th data-options="field:'date',width:1,align:'center',remoteSort:'false'">日期</th>
				<th data-options="field:'weekday',width:1,align:'center',formatter:weekdayFormatter">星期</th>
				<th data-options="field:'schoolclass',width:1,align:'center',formatter:schoolclassFormatter">班级</th>
				<th data-options="field:'coursename',width:1,align:'center'">课程名称</th>
				<th data-options="field:'schoolroom',width:1,align:'center',formatter:schoolroomFormatter">教室</th>
				<th data-options="field:'headTeacher',width:1,align:'center',formatter:headTeacherFormatter">班主任</th>
				<th data-options="field:'teacher',width:1,align:'center',formatter:teacherFormatter">任课老师</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
				<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
			</tr>
		</thead>
	</table>
	 </div>
	<!-- 新增编辑对话框 -->
	<div id="courselist_dialog">
		<form id="courselist_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>课程</td>
				<td><input type="text" name="course"></td>
			</tr>
			<tr>
				<td>教室</td>
				<td><input class="easyui-combobox" name="schoolroom.id"
				data-options="valueField:'id',textField:'name',url:'/schoolroom/selectListForSchoolroomlistForm'"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
			<tr>
				<td>班级</td>
				<td><input class="easyui-combobox" name="schoolclass.id"
				data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'"></td>
			</tr>
			<tr>
				<td>日期</td>
				<td><input class="easyui-datebox" name="date"></td>
			</tr>
			<tr>
				<td>任课老师</td>
				<td><input class="easyui-combobox" name="teacher.id"
				data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'"></td>
			</tr>
			<tr>
				<td>课程名称</td>
				<td><input type="text" name="coursename"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 新增导出对话框 -->
	<div id="importExcel_dialog">
		<form id="importExcel_form" method="post" enctype="multipart/form-data">
		<table align="center" style="margin-top: 15px;">
			<tr>
				<td>请选择</td>
				<td><input type="file" name="excel"/></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="courselist_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" id="courselist_editBtn" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" id="courselist_delBtn" plain="true" data-cmd="del">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-ok" id="courselist_checkBtn" plain="true" data-cmd="check">审核</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="window.open('/courselist/Excel')">导出Excel</a>
			<a class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="importExcel">导入Excel</a>
		</div>
		<!-- 高级查询 -->
	<div>
		    教室:<input class="easyui-combobox" name="schoolroomId" 
			data-options="valueField:'id',textField:'name',url:'/schoolroom/selectAll'">
		任课老师:<input class="easyui-combobox" name="teacherId"
			data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'">
		    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchContent">搜索</a>
	</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="courselist_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 导出Excel对话框保存取消按钮 -->
	<div id="importExcel_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="import">确定</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</div>
</body>
</html>