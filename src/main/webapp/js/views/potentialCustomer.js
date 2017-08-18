$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var potentialCustomerDatagrid, potentialCustomerDialog, potentialCustomerForm;
    potentialCustomerDatagrid = $("#potentialCustomer_datagrid");
    potentialCustomerDialog = $("#potentialCustomer_dialog");
    potentialCustomerForm = $("#potentialCustomer_form");
    /*
     * 初始化数据表格
     */
    potentialCustomerDatagrid.datagrid({
        url: "/potentialCustomer/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#potentialCustomer_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    potentialCustomerDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#potentialCustomer_dialog_bt'
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
        /*add: function () {
            potentialCustomerForm.form("clear");
            potentialCustomerDialog.dialog("setTitle", "新增");
            potentialCustomerDialog.dialog("open");
        },
        edit: function () {
            var rowData = potentialCustomerDatagrid.datagrid("getSelected");
            if (rowData) {
                potentialCustomerForm.form("clear");
                potentialCustomerDialog.dialog("setTitle", "编辑");
                potentialCustomerDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                potentialCustomerForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },*/
        getOutToCustomerInfo: function () {
            var rowData = potentialCustomerDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定将该学员吗？", function (yes) {
                    if (yes) {
                        $.get("/potentialCustomer/getOutToCustomerInfo?potentialCustomerId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    potentialCustomerDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择取出的学员!", "warining");
            }
        },
        reload: function () {
            potentialCustomerDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/potentialCustomer/update"
            } else {
                url = "/potentialCustomer/save";
            }
            potentialCustomerForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            potentialCustomerDialog.dialog("close");
                            potentialCustomerDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            potentialCustomerDialog.dialog("close");
        }
    }
});

//录入人
function inputUserFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}
//性别
function genderFormatter(value, record, index) {
    if (value == 0) {
        return "男";
    } else if (value == 1) {
        return "女";
    }
}

//负责人
function iinChargeUserFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}

//客户来源
function customerSourceFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}