<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<% response.reset();response.setContentType("application/vnd.ms-excel;charset=UTF-8");%>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>学员流失管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/turnoverStudent.js"></script>
    <script type="text/javascript">//发送请求
    function exportExcel() {
        window.location = "<%=request.getContextPath()%>/partOfferPast/exportExcel";
    }
    </script>
</head>
<body>
<!-- 数据表格 -->
<table id="turnoverStudent_datagrid">
    <thead>
    <tr>
        <th data-options="field:'rec_tur',width:1,align:'center',formatter:recTurFormatter">学生名称</th>
        <th data-options="field:'qq',width:1,align:'center'">QQ</th>
        <th data-options="field:'phone',width:1,align:'center'">电话</th>
        <th data-options="field:'schoolDate',width:1,align:'center'">上课天数</th>
        <th data-options="field:'turnoverClass',width:1,align:'center'">流失班级</th>
        <th data-options="field:'turnoverPhase',width:1,align:'center'">流失阶段</th>
        <th data-options="field:'turnoverCause',width:1,align:'center'">流失原因</th>
        <th data-options="field:'turnoverDate',width:1,align:'center'">流失时间</th>
        <th data-options="field:'campPersonnel',width:1,align:'center'">营售人员</th>
        <th data-options="field:'enteringPersonnel',width:1,align:'center'">录入人</th>
        <th data-options="field:'whetherReund',width:1,align:'center'">是否退款</th>
        <th data-options="field:'state',width:1,align:'center'">状态</th>
    </tr>
    </thead>
</table>
<!-- 数据表格CRUD按钮 -->
<div id="turnoverStudent_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="view" ,id="view_btn">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input class="easyui-textbox" name='keyword' placeholder="学生姓名/QQ"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="lossStudentSearch">查询</a>
        <a class="easyui-linkbutton" iconCls="icon-no" plain="true" href="/lossstudent_export">导出Excel</a>
    </div>
</div>
<!-- 新增编辑对话框 -->
<div id="turnoverStudent_dialog">
    <form id="turnoverStudent_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <tr>
                <td>学生名称</td>
                <td><input type="text" name="studentName"></td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input type="text" name="qq"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td>上课天数</td>
                <td><input type="text" name="schoolDate"></td>
            </tr>
            <tr>
                <td>流失班级:</td>
                <td>
                    <input name="turnoverClass" class="easyui-combobox"
                           data-options="valueField:'name',textField:'name',url:'/schoolClass/selectListForCourselistForm'"/>
                </td>
            </tr>
            <tr>
                <td>流失阶段</td>
                <td><input type="text" name="turnoverPhase"></td>
            </tr>
            <tr>
                <td>流失原因</td>
                <td><input type="text" name="turnoverCause"></td>
            </tr>
            <tr>
                <td>流失时间</td>
                <td>
                    <input type="text" name="turnoverDate" class="easyui-datebox" required="required"></input>
                </td>
            </tr>
            <td>营销人员:</td>
            <td><input name="campPersonnel" class="easyui-combobox"
                       data-options="valueField:'realname',textField:'realname',url:'/employee/selectListForEmployeelistForm',panelHeight:'auto'"/>
            </td>
            <tr>
                <td>录入人:</td>
                <td><input name="enteringPersonnel" class="easyui-combobox"
                           data-options="valueField:'realname',textField:'realname',url:'/employee/selectListForEmployeelistForm',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>是否退款:</td>
                <td>
                    <input type="text" name="whetherReund" class="easyui-combobox"
                           data-options="panelHeight:'auto',valueField:'id',textField:'name',data:[{id:'1',name:'是'},{id:'0',name:'否'}]"/>
                </td>
            </tr>
            <tr>
                <td>状态:</td>
                <td><input name="state" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="turnoverStudent_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--*****************************************************查看***********************************************************--%>
<div id="turnoverStudent_dialogView">
    <form id="turnoverStudent_formView" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px;">
            <tr>
                <td>学生名称</td>
                <td><input type="text" class="easyui-combobox" name="studentName"></td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input type="text" class="easyui-combobox" name="qq"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" class="easyui-combobox" name="phone"></td>
            </tr>
            <tr>
                <td>上课天数</td>
                <td><input type="text" class="easyui-combobox" name="schoolDate"></td>
            </tr>
            <tr>
                <td>流失班级</td>
                <td><input type="text" class="easyui-combobox" name="turnoverClass"></td>
            </tr>
            <tr>
                <td>流失阶段</td>
                <td><input type="text" class="easyui-combobox" name="turnoverPhase"></td>
            </tr>
            <tr>
                <td>流失原因</td>
                <td><input type="text" class="easyui-combobox" name="turnoverCause"></td>
            </tr>
            <tr>
                <td>流失时间</td>
                <td><input type="text" class="easyui-combobox" name="turnoverDate"></td>
            </tr>
            <tr>
                <td>营售人员</td>
                <td><input type="text" class="easyui-combobox" name="campPersonnel"></td>
            </tr>
            <tr>
                <td>录入人</td>
                <td><input type="text" class="easyui-combobox" name="enteringPersonnel"></td>
            </tr>
            <tr>
                <td>是否退款</td>
                <td><input type="text" class="easyui-combobox" name="whetherReund"></td>
            </tr>
            <tr>
                <td>状态</td>
                <td><input type="text" class="easyui-combobox" name="state"></td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="turnoverStudent_dialogView_bt">
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancelView">取消</a>
</div>
</body>
</html>