<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支付管理管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/expend.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="expend_datagrid">
    <thead>
    <tr>
        <th data-options="field:'expendTime',width:1,align:'center'">支付时间</th>
        <th data-options="field:'expendAmount',width:1,align:'center'">支付金额</th>
        <th data-options="field:'digest',width:1,align:'center'">摘要</th>
        <th data-options="field:'cashier',width:1,align:'center'">出纳</th>
        <th data-options="field:'handlerPerson',width:1,align:'center'">经手人</th>
        <th data-options="field:'expendMethod',width:1,align:'center'">支付方式</th>
        <th data-options="field:'expendType',width:1,align:'center'">支付类型</th>
        <th data-options="field:'subclass',width:1,align:'center'">小类</th>
        <th data-options="field:'documentNumber',width:1,align:'center'">单据号</th>
        <th data-options="field:'shareCost',width:1,align:'center'">共享费用</th>
        <th data-options="field:'apportionmentType',width:1,align:'center'">分摊类型</th>
        <th data-options="field:'subject',width:1,align:'center'">归属学科</th>
        <th data-options="field:'audit',width:1,align:'center',formatter:auditFormatter">审核</th>
    </tr>
    </thead>
</table>
<!-- 新增编辑对话框 -->
<div id="expend_dialog">
    <form id="expend_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>支付时间</td>
                <td>
                    <input type="text" name="expendTime" class="easyui-datebox" required="required"></input>
                </td>
            </tr>
            <tr>
                <td>支付金额</td>
                <td><input type="text" name="expendAmount"></td>
            </tr>
            <tr>
                <td>摘要</td>
                <td><input type="text" name="digest"></td>
            </tr>
            <tr>
                <td>出纳</td>
                <td>
                    <input class="easyui-combobox" name="cashier" class="easyui-combobox"
                           data-options="valueField:'realname',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
                </td>
            </tr>
            <tr>
                <td>经手人</td>
                <td><%--<input class="easyui-combobox" name="handlerPerson"
                           data-options="
                           url:'/employee/selectListForEmployeelistForm',
                            valueField:'name',textField:'realname'"/>--%>
                    <input id="principalId" class="easyui-combobox" name="handlerPerson"
                           data-options="valueField:'realname',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
                </td>
            </tr>
            <tr>
                <td>支付方式</td>
                <td><input type="text" name="expendMethod"></td>
              <%--  <td><input class="easyui-combobox" name="expendMethod"
                           data-options="
                           url:'/receiptManage/expendMethodByReceiptManage?way=expendMethod',
                            valueField:'id',textField:'name',panelHeight:'auto',
                           "/></td>--%>
            </tr>
            <tr>
                <td>支付类型</td>
                <td><input type="text" name="expendType"></td>
                <%-- <td><input class="easyui-combobox" name="expendType" data-options="
                            url:'',
                             valueField:'name',textField:'name',panelHeight:'auto',
                            "/></td>--%>
            </tr>
            <tr>
                <td>小类</td>
                <td><input type="text" name="subclass"></td>
                <%-- <td><input class="easyui-combobox" name="subclass" data-options="
                            url:'',
                             valueField:'name',textField:'name',panelHeight:'auto',
                            "/></td>--%>
            </tr>
            <tr>
                <td>单据号</td>
                <td><input type="text" name="documentNumber"></td>
            </tr>
            <tr>
                <td>共享费用</td>
                <td><input type="text" name="shareCost"></td>
            </tr>
            <tr>
                <td>分摊类型</td>
                <td><input type="text" name="apportionmentType"></td>
                <%--<td><input class="easyui-combobox" name="apportionmentType" data-options="
                           url:'',
                            valueField:'name',textField:'name',panelHeight:'auto',
                           "/></td>--%>
            </tr>
            <tr>
                <td>归属学科</td>
                <td><input class="easyui-combobox" name="subject" data-options="
                           url:'/courselist/selectListForCourselistForm',
                            valueField:'coursename',textField:'coursename',panelHeight:'auto',
                           "/></td>
            </tr>
            <tr>
                <td>审核</td>
                <td>
                    <input type="text" name="audit" class="easyui-combobox"
                           data-options="panelHeight:'auto',valueField:'id',textField:'name',data:[{id:'1',name:'已审核'},{id:'0',name:'未审核'}]"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="expend_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
</div>
<!-- 对话框保存取消按钮 -->
<div id="expend_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>