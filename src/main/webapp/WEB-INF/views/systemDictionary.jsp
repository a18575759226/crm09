<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>字典目录管理</title>
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/js/views/systemDictionaryItem.js"></script>
	<script type="text/javascript" src="/js/views/systemDictionary.js"></script>
</head>
<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
	<div data-options="region:'west',title:'字典目录'" style="width:700px;">
		<!-- 数据字典数据表格 -->
		<table id="systemDictionary_datagrid" fit="true">
			<thead>
			<tr>
				<th data-options="field:'sn',width:1,align:'center'">字典目录编号</th>
				<th data-options="field:'name',width:1,align:'center'">字典目录名称</th>
				<th data-options="field:'intro',width:1,align:'center'">字典目录简介</th>
			</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'center',title:'字典目录明细'" style="padding:5px;background:#eee;">
		<!-- 数据字典目录数据表格 -->
		<table id="systemDictionaryItem_datagrid">
			<thead>
			<tr>
				<th data-options="field:'id',width:1,align:'center'">字典明细编号</th>
				<th data-options="field:'name',width:1,align:'center'">字典明细名称</th>
				<th data-options="field:'intro',width:1,align:'center'">字典明细简介</th>
				<th data-options="field:'parent',width:1,align:'center'" formatter="parentFormatter">字典目录</th>
			</tr>
			</thead>
		</table>
	</div>
</div>
</body>

<!-- 新增编辑对话框 -->
<div id="systemDictionaryItem_dialog">
	<form id="systemDictionaryItem_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
		<%--	<tr>
				<td>字典编号</td>
				<td><input type="text" name="id"></td>
			</tr>--%>
			<tr>
				<td>字典明细名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>字典明细简介</td>
				<td><input type="text" name="intro"></td>
			</tr>
			<td>字典父目录</td>
			<td>
				<input class="easyui-combobox" name="parent.id"
				       data-options="valueField:'id',textField:'name',url:'/systemDictionary/selectListFromsystemDictionary'"/>
			</td>
		</table>
	</form>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="systemDictionaryItem_datagrid_tb">
	<div>
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
	</div>
</div>
<!-- 对话框保存取消按钮 -->
<div id="systemDictionaryItem_dialog_bt">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

</body>
</html>