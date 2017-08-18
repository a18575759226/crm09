$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var expendDatagrid, expendDialog, expendForm;
    expendDatagrid = $("#expend_datagrid");
    expendDialog = $("#expend_dialog");
    expendForm = $("#expend_form");
    /*
     * 初始化数据表格
     */
    expendDatagrid.datagrid({
        url: "/expend/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#expend_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    expendDialog.dialog({
        width: 550,
        height: 500,
        closed: true,
        buttons: '#expend_dialog_bt'
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
            expendForm.form("clear");
            expendDialog.dialog("setTitle", "新增");
            expendDialog.dialog("open");
        },
        edit: function () {
            var rowData = expendDatagrid.datagrid("getSelected");
            if (rowData) {
                expendForm.form("clear");
                expendDialog.dialog("setTitle", "编辑");
                expendDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                expendForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = expendDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/expend/delete?expendId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    expendDatagrid.datagrid("reload");
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
            expendDatagrid.datagrid("reload");
        },
        save: function () {
            //判断是否有id,如有就更新数据
            var id = $("[name='id']").val();
            var url;
            if (id) {
                url = "/expend/update";
            } else {
                url = '/expend/save';
            }
            expendForm.form("submit", {
                url: url,
                success: function (result) {
                    result = $.parseJSON(result);
                    if (result.success) {
                        $.messager.alert("温馨提示", result.msg, 'ok', function () {
                            expendDialog.dialog('close');
                            expendDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", result.msg, 'warning', function () {

                        });
                    }
                }
            });
        },
        cancel: function () {
            expendDialog.dialog("close");
        }
    }
});
function auditFormatter(value, record, index) {
    //1表示正式学员
    if (value == 1) {
        return "<font color='green'>已审核</font>";
    } else if (value == 0) {
        return "<font color='red'>未审核</font>";
    } else {
        return ""
    }
}
