$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var gouptoDatagrid, gouptoDialog, gouptoForm, goupto_dialogView, goupto_formView;
    gouptoDatagrid = $("#goupto_datagrid");
    gouptoDialog = $("#goupto_dialog");
    gouptoForm = $("#goupto_form");
    goupto_dialogView = $("#goupto_dialogView");
    goupto_formView = $("#goupto_formView");
    /*
     * 初始化数据表格
     */
    gouptoDatagrid.datagrid({
        url: "/goupto/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#goupto_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    gouptoDialog.dialog({
        width: 500,
        height: 400,
        closed: true,
        buttons: '#goupto_dialog_bt'
    });
    //查看
    goupto_dialogView.dialog({
        closable: true,
        width: 500,
        height: 400,
        buttons: '#goupto_dialogView_bt',
        closed: true
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
            gouptoForm.form("clear");
            gouptoDialog.dialog("setTitle", "新增");
            gouptoDialog.dialog("open");
        },
        edit: function () {
            var rowData = gouptoDatagrid.datagrid("getSelected");
            if (rowData) {
                gouptoForm.form("clear");
                gouptoDialog.dialog("setTitle", "编辑");
                gouptoDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                gouptoForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = gouptoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/goupto/delete?gouptoId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    gouptoDatagrid.datagrid("reload");
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
            gouptoDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/goupto/update"
            } else {
                url = "/goupto/save";
            }
            gouptoForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            gouptoDialog.dialog("close");
                            gouptoDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            gouptoDialog.dialog("close");
        },
        cancelView: function () {
            goupto_dialogView.dialog("close");
        },
        //查看按钮
        view: function () {
            var rowData = gouptoDatagrid.datagrid("getSelected");
            if (rowData) {
                goupto_formView.form("clear");
                goupto_dialogView.dialog("setTitle", "查看学员");
                goupto_dialogView.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                goupto_formView.form("load", rowData);
                $(".easyui-combobox").combobox({disabled: true});
            } else {
                $.messager.alert("温馨提示", "请选择需要查看的数据!", "warining");
            }
        },
        //高级查询
        lossStudentSearch: function () {
            var keyword = $("[name='keyword']").val();
            console.log(keyword)
            gouptoDatagrid.datagrid("load", {
                keyword: keyword
            });
        },
    }
});
function stateFormatter(value, record, index) {
    //1表示正式学员
    if (value == 1) {
        return "<font color='green'>升班学员</font>";
    } else if (value == 0) {
        return "<font color='red'>留级学员</font>";
    }else {
        return ""
    }
}
