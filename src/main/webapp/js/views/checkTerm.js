$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var checkTermDatagrid, checkTermDialog, checkTermForm, stateCondition;
    checkTermDatagrid = $("#checkTerm_datagrid");
    checkTermDialog = $("#checkTerm_dialog");
    checkTermForm = $("#checkTerm_form");
    stateCondition = $("#stateCondition_select");
    /*
     * 初始化数据表格
     */
    checkTermDatagrid.datagrid({
        url: "/checkTerm/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#checkTerm_datagrid_tb'
    });
    var excelUrl="/checkTerm/Excel?state=-1";
    stateCondition.combobox({
        onSelect: function (record) {
            excelUrl=$("#excel").data("url")+record.value;
            checkTermDatagrid.datagrid("load", {
                state: record.value
            });
        }
    });
    $("#excel").on("click",function () {
      //  console.debug(excelUrl);
        window.open(excelUrl);
    })
    /*
     * 初始化新增/编辑对话框
     */
    checkTermDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#checkTerm_dialog_bt'
    });
    /*
     * 对页面按钮进行统一监听
     */
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    /*
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        reload: function () {
            checkTermDatagrid.datagrid("reload");
        }
    }
});
function signIn() {
    $.get("/checkTerm/signIn", function (data) {
        if (data.success) {
            $.messager.alert('温馨提示', data.msg, 'info');
        } else {
            $.messager.alert('温馨提示', data.msg, 'warning');
        }
        reload();
    })
}
function signOut() {
    $.get("/checkTerm/signOut", function (data) {
        if (data.success) {
            $.messager.alert('温馨提示', data.msg, 'info');
        } else {
            $.messager.alert('温馨提示', data.msg, 'warning');
        }
        reload();
    }, "json")
}
function patch() {
    $.get("/checkTerm/patch", function (data) {
        if (data.success) {
            $.messager.alert('温馨提示', data.msg, 'info');
        } else {
            $.messager.alert('温馨提示', data.msg, 'warning');
        }
        reload();
    }, "json")
}
function reload() {
    $("#checkTerm_datagrid").datagrid("reload");
}
function stateFormatter(value, record, index) {
    if (value == 1) {
        return '<font color="green">正常上班</font>';
    } else {
        return '<font color="red">迟到早退</font>';
    }
}
function searchContent() {
    var state = $("[name='state']").val();
    var beginDate = $("[name='beginDate']").val();
    var endDate = $("[name='endDate']").val();
    $("#checkTerm_datagrid").datagrid("load", {
        state: state,
        beginDate:beginDate,
        endDate:endDate
    });
}
