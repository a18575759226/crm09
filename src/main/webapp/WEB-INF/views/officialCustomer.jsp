<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户管理管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/officialCustomer.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="customerInfo_datagrid">
    <thead>
    <tr>
        <th data-options="field:'name',width:1,align:'center'">学员名字</th>
        <th data-options="field:'gender',width:1,align:'center',formatter:genderFormatter">性别</th>
        <th data-options="field:'age',width:1,align:'center'">年龄</th>
        <th data-options="field:'principal',width:1,align:'center', formatter:principalFormatter">销售人员</th>

        <th data-options="field:'allTuitiong',width:1,align:'center'">总学费</th>
        <th data-options="field:'surplus',width:1,align:'center' ">待交学费</th>
        <th data-options="field:'yetPay',width:1,align:'center'">已交学费</th>
        <th data-options="field:'payState',width:1,align:'center'">缴费状态</th>

        <th data-options="field:'email',width:1,align:'center'">邮箱</th>
        <th data-options="field:'qq',width:1,align:'center'">qq</th>
        <th data-options="field:'tel',width:1,align:'center'">电话</th>
        <th data-options="field:'classId',width:1,align:'center', formatter:classFormatter">所属班级</th>
        <th data-options="field:'way',width:1,align:'center'">付款方式</th>
        <th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
    </tr>
    </thead>
</table>
<!-- 新增编辑对话框 -->
<div id="customerInfo_dialog">
    <form id="customerInfo_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">

            <tr>
                <td>客户姓名</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><input id="gender_id" name="gender" class="easyui-combobox" data-options="
						valueField: 'gender',
						textField: 'value',
						data: [{
							gender: '0',
							value: '男'
						},{
							gender: '1',
							value: '女'
						}]" /></td>
            </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text" name="age"></td>
            </tr>
            <%--<tr>
                <td>负责人</td>
                <td><input id="principal_id" type="text" name="principal"></td>
            </tr>--%>
            <tr>
                <td>负责人</td>
                <td>
                    <input id="principalId" class="easyui-combobox" name="principal.id"
                           data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
                </td>
            </tr>


            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>总学费</td>
                <td><input type="text" name="allTuitiong"></td>
            </tr>
            <tr>
                <td>待加学费</td>
                <td><input type="text" name="surplus"></td>
            </tr>
            <tr>
                <td>已交学费</td>
                <td><input type="text" name="yetPay"></td>
            </tr>
            <tr>
                <td>缴费状态</td>
                <td><input type="text" name="paySatae"></td>
             </tr>
            <tr>
                <td>qq</td>
                <td><input type="text" name="qq"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="tel"></td>
            </tr>
            <tr>
                <td>所属班级</td>
                <td>
                    <input id="classId" class="easyui-combobox" name="classId.id"
                           data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                </td>
            </tr>
            <tr>
                <td>付款方式</td>
                <td><input type="text" name="way"></td>
            </tr>

            <tr>
                <td>状态</td>
                <%--<td><input  type="text" name="state"></td>--%>
                <td><input id="state_id" name="state" class="easyui-combobox" data-options="
						valueField: 'state',
						textField: 'value',
						data: [{
							state: '0',
							value: '潜在学员'
						},{
							state: '1',
							value: '正式学员'
						},{
							state: '4',
							value: '休学学员'
						}]" /></td>
            </tr>

        </table>
    </form>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="customerInfo_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="check">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="window.open('/officialCustomerInfo/Excel')">导出到Excel</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="turnClass">转班</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="lose">流失</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="quit">休学</a>
        <a id="pay_a_id" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="pay">付款</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true"  data-cmd="loan">贷款</a>
        班级:
        <input id="class_select" class="easyui-combobox" name="classId.id" <%--label="请选择班级"--%>
               data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'" onChange="change()" />
       <%-- <a class="easyui-linkbutton" iconCls="icon-search" plain="true"  data-cmd="searchClass">查询</a>--%>

       <%-- <a class="easyui-linkbutton" iconCls="icon-edit" plain="true">通知</a>--%>
    </div>
    <div>
        关键字:<input type="text" placeholder="姓名/电话" name="keyword"><a class="easyui-linkbutton" iconCls="icon-search"
                                                                     data-cmd="searchContent">搜索</a>
    </div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="customerInfo_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>


<!--查看-->
<div id="customerInfo_checkDialog">
    <form id="customerInfo_checkForm" method="post">
        <table align="center" style="margin-top: 15px;">
           <%-- <input type="hidden" name="id">--%>

            <tr>
                <td>客户姓名</td>
                <td><input type="text" class="easyui-combobox"  name="name"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><input  name="gender" class="easyui-combobox"  data-options="
						valueField: 'gender',
						textField: 'value',
						data: [{
							gender: '0',
							value: '男'
						},{
							gender: '1',
							value: '女'
						}]" /></td>
            </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text"  name="age"></td>
            </tr>
            <%--<tr>
                <td>负责人</td>
                <td><input id="principal_id" type="text" name="principal"></td>
            </tr>--%>
            <tr>
                <td>负责人</td>
                <td>
                    <input  class="easyui-combobox"  name="principal.id"
                           data-options="valueField:'id',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
                </td>
            </tr>
               <tr>
                   <td>总学费</td>
                   <td><input type="text" name="allTuitiong"></td>
               </tr>
               <tr>
                   <td>待加学费</td>
                   <td><input type="text" name="surplus"></td>
               </tr>
               <tr>
                   <td>已交学费</td>
                   <td><input type="text" name="yetPay"></td>
               </tr>
               <tr>
                   <td>缴费状态</td>
                   <td><input type="text" name="paySatae"></td>
               </tr>
               <tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text"  name="email"></td>
            </tr>
            <tr>
                <td>qq</td>
                <td><input type="text"  name="qq"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text"  name="tel"></td>
            </tr>
               <tr>
                   <td>所属班级</td>
                   <td>
                       <input id="classSee" class="easyui-combobox" name="classId.id"
                              data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                   </td>
               </tr>

               <tr>
                   <td>状态</td>
                   <%--<td><input  type="text" name="state"></td>--%>
                   <td><input  name="state" class="easyui-combobox" data-options="
						valueField: 'state',
						textField: 'value',
						data: [{
							state: '0',
							value: '潜在学员'
						},{
							state: '1',
							value: '正式学员'
						}]" /></td>
               </tr>

        </table>
    </form>
</div>
<div id="check_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="checkCancel">关闭</a>
</div>

<!--转班-->
<div id="turnClass_dialog">
    <form id="turnClass_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>原来的班级</td>
                <td><input id="formerClass" type="text" ></td>
            </tr>
            <tr>
                <td>需要转到的班级</td>
                <td>
                    <input id="turnClass" class="easyui-combobox" name="classId.id"
                           data-options="valueField:'id',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 转班取消按钮 -->
<div id="turnClass_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="affirmTurnClass">确认转班</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="turnClassCancel">取消</a>
</div>

<!--付款-->
<div id="pay_dialog">
    <form id="pay_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
        <tr>
            <td>总学费</td>
            <td>
                <input  type="text" name="allTuitiong" id="allTuitiong_id">
            </td>
        </tr>
            <tr>
                <td>待交学费</td>
                <td>
                    <input type="text" name="surplus" id="surplus_id">
                </td>
            </tr>
            <tr>
                <td>已付学费</td>
                <td>
                    <input type="text" name="yetPay" id="yetPay_id">
                </td>
            </tr>
            <tr>
                <td>付款</td>
                <td>
                    <input type="text" name="pay" id="pay_id">
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
      // var val = $('#surplus_id').val();

       $("#pay_id").numberbox({
           min:0,
           max:14990,
           invalidMessage:'输入的最小数值为0,付款金额不能大于待缴金额',
           required:true,
           missingMessage:'请输入付款金额'
       });
      // $("#pay_id").attr("max",val);

</script>

<!-- 付款确认取消按钮 -->
<div id="pay_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="affirmPay">确认付款</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="payClose">取消</a>
</div>



</body>
</html>