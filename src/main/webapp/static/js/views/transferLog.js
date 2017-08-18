$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var transferLogDatagrid, transferLogDialog, transferLogForm,transferLogDatagridCheck;
    transferLogDatagrid = $("#transferLog_datagrid");
    transferLogDialog = $("#transferLog_dialog");
    transferLogForm = $("#transferLog_form");
    transferLogDatagridCheck = $("#transferLog_datagrid_check");
    /*
     * 初始化数据表格
     */
    transferLogDatagrid.datagrid({
        url: "/transferLog/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#transferLog_datagrid_tb',
        onClickRow:function (rowIndex, rowData) {
            if (rowData.state){
                $("#check_button").linkbutton("disable");
            }else {
                $("#check_button").linkbutton("enable");
            }
        }
    });
    /*
     * 初始化新增/编辑对话框
     */
    transferLogDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#transferLog_dialog_bt'
    });
    transferLogDatagridCheck.dialog({
        width: 300,
        height: 180,
        closed: true,
        buttons: '#transferLog_datagrid_btn'
    })
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
        check: function () {
            var getSelected = transferLogDatagrid.datagrid("getSelected");
            if(getSelected){
                transferLogDatagridCheck.dialog("open");
                transferLogDatagridCheck.dialog("setTitle","移交审核");
            }else {
                $.messager.alert("温馨提示", "请选择需要移交的记录", "warning");
            }

            //return;
            //if (rowData) {
            //    transferLogForm.form("clear");
            //    transferLogDialog.dialog("setTitle", "编辑");
            //    transferLogDialog.dialog("open");
            //    if (rowData.dept)
            //        rowData["dept.id"] = rowData.dept.id;
            //    transferLogForm.form("load", rowData);
            //} else {
            //    $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            //}

        },
        checkConfirm:function () {
            var getSelected= transferLogDatagrid.datagrid("getSelected");
            $("#check_form").form("submit",{
                url:"/transferLog/transferCheck",
                onSubmit:function (param) {
                    param.id = getSelected.id
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        transferLogDatagridCheck.dialog("close");
                        transferLogDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            })
        },
        reload: function () {
            transferLogDatagrid.datagrid("reload");
        },
        cancel: function () {
            transferLogDialog.dialog("close");
        }
    }
});

function customerFormatter(value) {
    return value ? value.name : "";
}
function currentPrincipalFormatter(value) {
    return value ? value.realname : "";
}
function accepterFormatter(value) {
    return value ? value.realname : "";
}

function checkerFormatter(value) {
    return value ? value.realname : "";
}

function stateFormatter(value){
    if (value == 0){
        return "审核中";
    }else if (value == 1){
        return "<span style='color: green'>审核通过</span>";
    }else {
        return "<span style='color: red'>审核不通过</span>";
    }
}

