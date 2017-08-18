<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>升班留级管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/goupto.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="goupto_datagrid">
    <thead>
    <tr>
        <th data-options="field:'realName',width:1,align:'center'">真实姓名</th>
        <th data-options="field:'receiptAmount',width:1,align:'center'">总学费</th>
        <th data-options="field:'salesWater',width:1,align:'center'">销售流水</th>
        <th data-options="field:'otherCosts',width:1,align:'center'">其他费用</th>
        <th data-options="field:'receiptTime',width:1,align:'center'">升班/留级时间</th>
        <th data-options="field:'qq',width:1,align:'center'">QQ</th>
        <th data-options="field:'phone',width:1,align:'center'">联系电话</th>
        <th data-options="field:'oldClass',width:1,align:'center'">以前的班级</th>
        <th data-options="field:'intoClass',width:1,align:'center'">流入的班级</th>
        <th data-options="field:'salesman',width:1,align:'center'">营售人员</th>
        <th data-options="field:'state',width:1,align:'center',formatter:stateFormatter">状态</th>
    </tr>
    </thead>
</table>
<!-- 数据表格CRUD按钮 -->
<div id="goupto_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="view" ,id="view_btn">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input class="easyui-textbox" name='keyword' placeholder="姓名/QQ"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="lossStudentSearch">查询</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="window.open('/goupto/Excel')">导出到Excel</a>
    </div>
</div>
<!-- 新增编辑对话框 -->
<div id="goupto_dialog">
    <form id="goupto_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="realName"></td>
            </tr>
            <tr>
                <td>总学费</td>
                <td><input type="text" name="receiptAmount"></td>
            </tr>
            <tr>
                <td>销售流水</td>
                <td><input type="text" name="salesWater"></td>
            </tr>
            <tr>
                <td>其他费用</td>
                <td><input type="text" name="otherCosts"></td>
            </tr>
            <tr>
                <td>升班/留级时间</td>
                <td><%--<input type="text" name="receiptTime">--%>
                    <input type="text"name="receiptTime" class="easyui-datebox" required="required"></input>

                </td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input type="text" name="qq"></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td>以前的班级</td>
                <td>
                    <input id="classId"  name="oldClass"class="easyui-combobox"
                           data-options="valueField:'name',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                </td>
            </tr>
            <tr>
                <td>流入的班级</td>
                <td>
                    <input name="intoClass"class="easyui-combobox"
                           data-options="valueField:'name',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                </td>
            </tr>
            <tr>
                <td>营售人员</td>
                <td>
                    <input id="principalId" name="salesman"class="easyui-combobox"
                           data-options="valueField:'realname',textField:'realname',url:'/employee/selectListForEmployeelistForm'"/>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <input type="text" name="state"class="easyui-combobox"
                           data-options="panelHeight:'auto',valueField:'id',textField:'name',data:[{id:'1',name:'升班学员'},{id:'0',name:'留级学员'}]"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="goupto_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--*****************************************************查看***********************************************************--%>
<div id="goupto_dialogView">

    <form id="goupto_formView" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <tr>
                <td>真实姓名</td>
                <td><input type="text" class="easyui-combobox" name="realName"></td>
            </tr>
            <tr>
                <td>总学费</td>
                <td><input type="text" class="easyui-combobox" name="receiptAmount"></td>
            </tr>
            <tr>
                <td>销售流水</td>
                <td><input type="text" class="easyui-combobox" name="salesWater"></td>
            </tr>
            <tr>
                <td>其他费用</td>
                <td><input type="text" class="easyui-combobox" name="otherCosts"></td>
            </tr>
            <tr>
                <td>升班/留级时间</td>
                <td><input type="text" class="easyui-combobox" name="receiptTime"></td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input type="text" class="easyui-combobox" name="qq"></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input type="text" class="easyui-combobox" name="phone"></td>
            </tr>
            <tr>
                <
                <td>以前的班级</td>
                <td><input class="easyui-combobox"  name="oldClass"
                           data-options="valueField:'id',textField:'username',url:'/employee_combobox',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>流入的班级</td>
                <td><input class="easyui-combobox" name="intoClass"
                           data-options="valueField:'id',textField:'username',url:'/employee_combobox',panelHeight:'auto'"/>
                </td>
            </tr>
            </tr>
            <tr>
                <td>营售人员</td>
                <td><input class="easyui-combobox"  name="salesman"
                           data-options="valueField:'id',textField:'username',url:'/employee_combobox',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td><input class="easyui-combobox" name="state"
                           data-options="valueField:'id',textField:'username',url:'/employee_combobox',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="goupto_dialogView_bt">
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancelView">关闭</a>
</div>
</body>
</html>