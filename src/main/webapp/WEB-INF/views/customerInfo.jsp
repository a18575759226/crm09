<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/customerInfo.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="customerInfo_datagrid">
		<thead>
			<tr>
				<th data-options="field:'name',width:1,align:'center'">客户姓名</th>
				<th data-options="field:'inputUser',width:1,align:'center',formatter:inputUserFormatter">录入人员</th>
				<th data-options="field:'tel',width:1,align:'center'">电话</th>
				<th data-options="field:'qq',width:1,align:'center'">qq</th>
				<th data-options="field:'appointmentTime',width:1,align:'center'">约访时间</th>
				<th data-options="field:'inputTime',width:1,align:'center'">录入时间</th>
				<th data-options="field:'intentionDiscipline',width:1,align:'center',formatter:intentionDisciplineFormatter">意向学科</th>
				<th data-options="field:'intentionDegree',width:1,align:'center',formatter:intentionDegreeFormatter">意向程度</th>
				<th data-options="field:'intentionSchool',width:1,align:'center',formatter:intentionSchoolFormatter">意向学校</th>
				<th data-options="field:'customerSource',width:1,align:'center',formatter:customerSourceFormatter">客户来源</th>
				<th data-options="field:'territory',width:1,align:'center',formatter:territoryFormatter">地域</th>
				<th data-options="field:'principal',width:1,align:'center',formatter:principalFormatter">负责人</th>
				<th data-options="field:'workingCondition',width:1,align:'center',formatter:workingConditionFormatter">工作状态</th>
				<th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
				<th data-options="field:'educationalHistory',width:1,align:'center',formatter:educationalHistoryFormatter">学历</th>

				<%--<th data-options="field:'attentionMatter',width:1,align:'center'">客户关注问题</th>
				<th data-options="field:'major',width:1,align:'center'">专业</th>
				<th data-options="field:'age',width:1,align:'center'">年龄</th>
				<th data-options="field:'gender',width:1,align:'center'">性别</th>
				<th data-options="field:'birthday',width:1,align:'center'">生日</th>
				<th data-options="field:'email',width:1,align:'center'">邮箱</th>
				<th data-options="field:'address',width:1,align:'center'">地址</th>
				<th data-options="field:'educationalHistory',width:1,align:'center'">学历</th>
				<th data-options="field:'introducer',width:1,align:'center'">介绍人</th>
				<th data-options="field:'identityCard',width:1,align:'center'">身份证</th>
				<th data-options="field:'paymentWay',width:1,align:'center'">付款方式</th>
				<th data-options="field:'isPledge',width:1,align:'center'">是否叫押金</th>--%>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="customerInfo_dialog">
		<form id="customerInfo_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<input type="hidden" name="inputUser.id" id="inputUser_id">
			<input type="hidden" name="principal.id" id="principal_id">
			<tr>
				<td>客户姓名</td>
				<td><input type="text" name="name" validType="length[2,10]" class="easyui-validatebox" required="true"></td>
				<td>性别</td>
				<td><input id="gender_id" name="gender" class="easyui-combobox" data-options="
					valueField: 'gender',
					textField: 'value',
					data: [{
							gender: '0',
							value: '男',
							selected:true
						},{
							gender: '1',
							value: '女'
						}]" /></td>
				<td>年龄</td>
				<td><input type="text" name="age" validType="length[2,3]" class="easyui-validatebox" required="true"></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="tel" validType="length[11,11]" class="easyui-validatebox" required="true"></td>
				<td>qq</td>
				<td><input type="text" name="qq"></td>
				<td>邮箱</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>意向学科</td>
				<td><input id="intentionDiscipline" class="easyui-combobox" name="intentionDiscipline.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=意向学科'" /></td>
				<td>意向程度</td>
				<td><input id="intentionDegree" class="easyui-combobox" name="intentionDegree.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=意向程度'" /></td>
				<td>意向学校</td>
				<td><input id="intentionSchool" class="easyui-combobox" name="intentionSchool.name" data-options="SvalueField:'name',textField:'name',
						 url:'/systemDictionaryItem/selectListByparentName?parentName=意向学校'" /></td>
			</tr>
			<tr>
				<td>介绍人</td>
				<td><input type="text" name="introducer"></td>
				<td>学历</td>
				<td><input id="educationalHistory" class="easyui-combobox" name="educationalHistory.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=学历'" /></td>
				<td>专业</td>
				<td><input id="major" class="easyui-combobox" name="major.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=专业'" /></td>

			</tr>
			<tr>
				<td>约访时间</td>
				<td><input class="easyui-datebox" name="appointmentTime"></td>
				<td>客户来源</td>
				<td><input id="customerSource" class="easyui-combobox" name="customerSource.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=客户来源'" /></td>
				<td>地域</td>
				<td><input id="territory" class="easyui-combobox" name="territory.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=地域'" /></td>
			</tr>
			<tr>
				<td>身份证号码</td>
				<td><input type="text" name="identityCard"></td>
				<td>地址</td>
				<td><input type="text" name="address"></td>
				<td>工作状态</td>
				<td><input id="workingCondition" class="easyui-combobox" name="workingCondition.name" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=工作状态'" /></td>
			</tr>
			<%--<tr>
				<td>付款方式</td>
				<td><input id="paymentWay_id" name="paymentWay" class="easyui-combobox" required:true data-options="
						valueField: 'paymentWay',
						textField: 'value',
						data: [{
							paymentWay: '0',
							value: '分期付款'
						},{
							paymentWay: '1',
							value: '一次付清'
						}]" /></td>
				<td>是否已交押金</td>
				<td><input id="isPledge_id" name="isPledge" class="easyui-combobox" required:true data-options="
						valueField: 'isPledge',
						textField: 'value',
						data: [{
							isPledge: '0',
							value: '不交押金'
						},{
							isPledge: '1',
							value: '交押金'
						}]" /></td>
			</tr>--%>
			<tr>
				<td>客户关注问题</td>
				<td colspan="3"><input type="text" name="attentionMatter" style="width: 600px;height:50px" ></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 新增高级查询对话框 -->
	<div id="expertSearch_dialog">
		<form id="expertSearch_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<tr>
				<td>客户姓名</td>
				<td><input type="text" name="name"></td>
				<td>性别</td>
				<td><input name="gender" class="easyui-combobox" data-options="
						valueField: 'gender',
						textField: 'value',
						data: [{
							gender: '0',
							value: '男'
						},{
							gender: '1',
							value: '女'
						}]" /></td>
				<td>年龄</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="tel"></td>
				<td>qq</td>
				<td><input type="text" name="qq"></td>
				<td>邮箱</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>意向学科</td>
				<td><input  class="easyui-combobox" name="intentionDisciplineName" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=意向学科'" /></td>
				<td>意向程度</td>
				<td><input  class="easyui-combobox" name="intentionDegreeName" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=意向程度'" /></td>
				<td>意向学校</td>
				<td><input  class="easyui-combobox" name="intentionSchoolName" data-options="valueField:'name',textField:'name',
						 url:'/systemDictionaryItem/selectListByparentName?parentName=意向学校'" /></td>
			</tr>
			<tr>
				<td>介绍人</td>
				<td><input type="text" name="introducer"></td>
				<td>约访时间(起)</td>
				<td><input class="easyui-datebox" name="beginAppointmentTime"></td>
				<td>约访时间(止)</td>
				<td><input class="easyui-datebox" name="endAppointmentTime"></td>
			</tr>
			<tr>
				<td>客户来源</td>
				<td><input  class="easyui-combobox" name="customerSourceName" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=客户来源'" /></td>
				<td>地域</td>
				<td><input  class="easyui-combobox" name="territoryName" data-options="valueField:'name',textField:'name',
						url:'/systemDictionaryItem/selectListByparentName?parentName=territory'" /></td>
				<td>地址</td>
				<td><input type="text" name="adress"></td>
			</tr>
			<tr>
				<td>录入(负责人)</td>
				<td><input type="text" name="inputUserId"></td>
				<td>录入时间(起)</td>
				<td><input class="easyui-datebox" name="beginInputTime"></td>
				<td>录入时间(起)</td>
				<td><input class="easyui-datebox" name="endInputTime"></td>
			</tr>
			<%--<tr>
				<td>付款方式</td>
				<td><input id="paymentWay_id" name="paymentWay" class="easyui-combobox" required:true data-options="
						valueField: 'paymentWay',
						textField: 'value',
						data: [{
							paymentWay: '0',
							value: '分期付款'
						},{
							paymentWay: '1',
							value: '一次付清'
						}]" /></td>
				<td>是否已交押金</td>
				<td><input id="isPledge_id" name="isPledge" class="easyui-combobox" required:true data-options="
						valueField: 'isPledge',
						textField: 'value',
						data: [{
							isPledge: '0',
							value: '不交押金'
						},{
							isPledge: '1',
							value: '交押金'
						}]" /></td>
			</tr>--%>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="customerInfo_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="search">查看</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="expertSearch">高级查询</a>
			<a class="easyui-linkbutton" iconCls="icon-filter" plain="true" data-cmd="exportStudent">导出Excel(O)</a>
			<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="becomeFormalStudent">升班</a>
			<a class="easyui-linkbutton" iconCls="icon-large-smartart" plain="true" data-cmd="examRegister">考试登记</a>
			<a class="easyui-linkbutton" iconCls="icon-large-redo" plain="true" data-cmd="movePotentialClientPool">放入客户池</a>
			<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="transfer">移交</a>
		</div>
		<div>
			<input id="state_change" name="state" class="easyui-combobox" data-options="
						valueField: 'state',
						textField: 'value',
						data: [{
							state: '-1',
							value: '全部客户'
						},{
							state: '0',
							value: '潜在客户'
						},{
							state: '1',
							value: '正式客户'
						}]"  />
		</div>
		<div>
			关键字:<input type="text" placeholder="姓名/电话" name="keyword"><a class="easyui-linkbutton" iconCls="icon-search"  data-cmd="searchContent">搜索</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="customerInfo_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 高级查询框保存取消按钮 -->
	<div id="customerInfo_dialog_expertSearch">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="searchUp">查询</a>
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="searchReflash">重置</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 对话框确认按钮 -->
	<div id="bt_search">
		<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="cancel">确认</a>
	</div>
	<%--移交的dialog--%>
	<div id="customer_transfer_dialog">
		<form id="customer_transfer_form" method="post">
			<%--<input type="hidden" name="customer.id">--%>
			<%--<input type="hidden" name="currentPrincipal.id">--%>
			<table align="center" style="margin-top: 80px">
				<tr>
					<td>潜在客户</td>
					<td><input id="customer_id" type="text"></td>
				</tr>
				<tr>
					<td>当前责任人</td>
					<td><input id="currentPrincipal_id" type="text"></td>
				</tr>
				<tr>
					<td>接收人</td>
					<td><input  class="easyui-combobox" name="accepter.id" data-options="valueField:'id',textField:'realname',url:'/employee/listEmployeeExcludeSelf'"/></td>
				</tr>
				<tr>
					<td>移交原因</td>
					<td><input type="text" name="remark"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 移交对话框buttons -->
	<div id="customerInfo_transfer_btn">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="transferBtn">移交</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancelBtn">取消</a>
	</div>
</body>
</html>