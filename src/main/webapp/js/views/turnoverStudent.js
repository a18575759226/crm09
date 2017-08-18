$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var turnoverStudentDatagrid, turnoverStudentDialog, turnoverStudentForm, turnoverStudent_dialogView,
        turnoverStudent_formView, turnoverStudent_dialogView_bt;
    turnoverStudentDatagrid = $("#turnoverStudent_datagrid");
    turnoverStudentDialog = $("#turnoverStudent_dialog");
    turnoverStudentForm = $("#turnoverStudent_form");
    turnoverStudent_dialogView = $("#turnoverStudent_dialogView");
    turnoverStudent_formView = $("#turnoverStudent_formView");
    /*
     * 初始化数据表格
     */
    turnoverStudentDatagrid.datagrid({
        url: "/turnoverStudent/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#turnoverStudent_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    turnoverStudentDialog.dialog({
        width: 500,
        height: 420,
        closed: true,
        buttons: '#turnoverStudent_dialog_bt'
    });
    //查看
    turnoverStudent_dialogView.dialog({
        closable: true,
        width: 500,
        height: 420,
        closed: true,
        buttons: '#turnoverStudent_dialogView_bt',
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
            turnoverStudentForm.form("clear");
            turnoverStudentDialog.dialog("setTitle", "新增");
            turnoverStudentDialog.dialog("open");
        },
        edit: function () {
            var rowData = turnoverStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                turnoverStudentForm.form("clear");
                turnoverStudentDialog.dialog("setTitle", "编辑");
                turnoverStudentDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                turnoverStudentForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = turnoverStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/turnoverStudent/delete?turnoverStudentId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    turnoverStudentDatagrid.datagrid("reload");
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
            turnoverStudentDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/turnoverStudent/update"
            } else {
                url = "/turnoverStudent/save";
            }
            turnoverStudentForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            turnoverStudentDialog.dialog("close");
                            turnoverStudentDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            turnoverStudentDialog.dialog("close");
        },
        cancelView: function () {
            turnoverStudent_dialogView.dialog("close");
        },
        //查看按钮
        view: function () {
            var rowData = turnoverStudentDatagrid.datagrid("getSelected");
            if (rowData) {
                turnoverStudent_formView.form("clear");
                turnoverStudent_dialogView.dialog("setTitle", "查看学员");
                turnoverStudent_dialogView.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                turnoverStudent_formView.form("load", rowData);
                $(".easyui-combobox").combobox({disabled: true});
            } else {
                $.messager.alert("温馨提示", "请选择需要查看的数据!", "warining");
            }
        },
        //高级查询
        lossStudentSearch: function () {
            var keyword = $("[name='keyword']").val();
            console.log(keyword)
            turnoverStudentDatagrid.datagrid("load", {
                keyword: keyword
            });
        }
    }
});
function recTurFormatter(value, record, index) {
    if (value) {
        return value.name;
    } else {
        return "";
    }
}
