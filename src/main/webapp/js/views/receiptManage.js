$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var receiptManageDatagrid, receiptManageDialog, receiptManageForm;
    receiptManageDatagrid = $("#receiptManage_datagrid");
    receiptManageDialog = $("#receiptManage_dialog");
    receiptManageForm = $("#receiptManage_form");
    /*
     * 初始化数据表格
     */
    receiptManageDatagrid.datagrid({
        url: "/receiptManage/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#receiptManage_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    receiptManageDialog.dialog({
        width: 500,
        height: 420,
        closed: true,
        buttons: '#receiptManage_dialog_bt'
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
        add: function () {
            receiptManageForm.form("clear");
            receiptManageDialog.dialog("setTitle", "新增");
            receiptManageDialog.dialog("open");
        },
        edit: function () {
            var rowData = receiptManageDatagrid.datagrid("getSelected");
            // console.log(rowData);
            // return;
            if (rowData) {
                receiptManageForm.form("clear");
                receiptManageDialog.dialog("setTitle", "编辑");
                receiptManageDialog.dialog("open");
                if (rowData.salesman != null) {
                    $("#salesman_id").combobox("setValue", rowData.salesman.id);
                }
                if (rowData.receiver != null) {
                    $("#receiver_id").combobox("setValue", rowData.receiver.id);
                }
                if (rowData.student != null) {
                    $("#student_id").combobox("setValue", rowData.student.id);
                }
                if (rowData.room != null) {
                    $("#room_id").combobox("setValue", rowData.room.id);
                }
                if (rowData.subject != null) {
                    $("#subject_id").combobox("setValue", rowData.subject.name);
                }
                receiptManageForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = receiptManageDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/receiptManage/delete?receiptManageId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    receiptManageDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的数据!", "warining");
            }
        },
        reload: function () {
            receiptManageDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/receiptManage/update"
            } else {
                url = "/receiptManage/save";
            }
            receiptManageForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            receiptManageDialog.dialog("close");
                            receiptManageDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            receiptManageDialog.dialog("close");
        }
    }
});
//学员
function studentFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
};
//班级
function roomFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}
//销售人员
function salesmanFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
};
//收款人
function receiverFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
    return value;
}
//学科
function subjectFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}
function invoiceFormatter(value, record, index) {
    //1表示正式学员
    if (value == 1) {
        return "<font color='green'>已开</font>";
    } else if (value == 0) {
        return "<font color='red'>未开</font>";
    } else {
        return ""
    }
}


