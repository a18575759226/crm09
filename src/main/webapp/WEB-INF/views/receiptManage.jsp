<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收款管理管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/receiptManage.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="receiptManage_datagrid">
		<thead>
			<tr>
				<th data-options="field:'student',width:1,align:'center',formatter:studentFormatter">学员</th>
				<th data-options="field:'room',width:1,align:'center',formatter:roomFormatter">班级</th>
				<th data-options="field:'gatherTime',width:1,align:'center'">收款时间</th>
				<th data-options="field:'getherSum',width:1,align:'center'">收款金额</th>
				<th data-options="field:'receiver',width:1,align:'center' ,formatter:receiverFormatter">收款人</th>
				<th data-options="field:'way',width:1,align:'center'">收款方法</th>
				<th data-options="field:'gatheringType',width:1,align:'center'">收款类型</th>
				<th data-options="field:'bills',width:1,align:'center'">收款单据号</th>
				<th data-options="field:'invoice',width:1,align:'center',formatter:invoiceFormatter">开票</th>
				<th data-options="field:'subject',width:1,align:'center',formatter:subjectFormatter">学科</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
				<th data-options="field:'salesman',width:1,align:'center',formatter:salesmanFormatter">营销人员</th>
				<%--<th data-options="field:'change',width:1,align:'center'">班级变动</th>--%>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="receiptManage_dialog">
		<form id="receiptManage_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<%--<tr>
				<td>营销人员</td>
				<td><input type="text" name="salesman"></td>
			</tr>--%>
			<tr>
				<td>营销人员</td>
				<td>
					<input id="salesman_id" class="easyui-combobox" name="salesman.id"
						   data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
				</td>
			</tr>
			<tr>
				<td>收款时间</td>
				<td><input type="text" class="easyui-datebox" name="gatherTime"></td>

			</tr>
			<%--<tr>
				<td>收款人</td>
				<td><input type="text" name="receiver"></td>
			</tr>--%>
			<tr>
				<td>收款人</td>
				<td>
					<input id="receiver_id" class="easyui-combobox" name="receiver.id"
						   data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
				</td>
			</tr>
			<%--<tr>
				<td>学员</td>
				<td><input id="student_id" type="text" name="student"></td>
			</tr>--%>
			<tr>
				<td>学员</td>
				<td>
					<input id="student_id" class="easyui-combobox" name="student.id"
						   data-options="valueField:'id',textField:'name',url:'/customerInfo/selectListForCustomerInfolistForm'"/>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
			<%--<<tr>
				<td>班级变动</td>
				<td><input type="text" name="change"></td>
			</tr>--%>
			<tr>
				<td>开票</td>
				<td><%--<input type="text" name="invoice">--%>
					<input type="text" name="invoice"class="easyui-combobox"
						   data-options="panelHeight:'auto',valueField:'id',textField:'name',data:[{id:'1',name:'已开'},{id:'0',name:'未开'}]"/>
				</td>
			</tr>
			<%--<tr>
				<td>学科</td>
				<td><input type="text" name="subject"></td>
			</tr>--%>
			<tr>
				<td>学科</td>
				<td>
					<input id="subject_id" class="easyui-combobox" name="subject.name"
						   data-options="valueField:'name',textField:'name',url:'/systemDictionaryItem/selectListByparentName?parentName=subject'"/>
				</td>
			</tr>
			<tr>
				<td>收款金额</td>
				<td><input type="text" name="getherSum"></td>
			</tr>
			<tr>
				<td>收款方法</td>
				<td><input type="text" name="way"></td>
			</tr>
			<tr>
				<td>收款类型</td>
				<td><input type="gatheringType" name="gatheringType"></td>
			</tr>
			<tr>
				<td>收款单据号</td>
				<td><input type="text" name="bills"></td>
			</tr>
			<%--<tr>
				<td>班级</td>
				<td><input type="text" name="room"></td>
			</tr>--%>
			<tr>
				<td>所属班级</td>
				<td>
					<input id="room_id" class="easyui-combobox" name="room.id"
						   data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="receiptManage_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="receiptManage_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>