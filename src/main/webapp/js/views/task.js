$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var taskDatagrid, taskDialog, taskForm, describeTaskDialog, describeTaskForm;
    taskDatagrid = $("#task_datagrid");
    taskDialog = $("#task_dialog");
    taskForm = $("#task_form");
    describeTaskDialog = $("#describeTask_dialog");
    describeTaskForm = $("#describeTask_form");
    /*
     * 初始化数据表格
     */
    taskDatagrid.datagrid({
        url: "/task/list",
        nowrap: true,
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#task_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    taskDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#task_dialog_bt'
    });


    describeTaskDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#describeTask_dialog_bt'
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

    var cmdObj = {
        add: function () {
            taskForm.form("clear");
            taskDialog.dialog("setTitle", "新增");
            taskDialog.dialog("open");
        },
        edit: function () {
            var rowData = taskDatagrid.datagrid("getSelected");
            if (rowData) {
                taskForm.form("clear");
                taskDialog.dialog("setTitle", "编辑");
                taskDialog.dialog("open");
                //设置日期只读
                $("#timeId").datebox({
                    readonly: true
                });
                //下拉框的数据回显
                if (rowData.processor != null) {
                    $("#processor_id").combobox('setValue', rowData.processor.id);
                }
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                taskForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要修改的任务!", "warining");
            }
        },
        del: function () {
            var rowData = taskDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/task/delete?taskId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    taskDatagrid.datagrid("reload");
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
            taskDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/task/update"
            } else {
                url = "/task/save";
            }
            taskForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            taskDialog.dialog("close");
                            taskDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            taskDialog.dialog("close");
        },
        updateFinish: function () {
            var rowData = taskDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定修改任务为完成状态吗？", function (yes) {
                    if (yes) {
                        $.get("/task/updateFinish?taskId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    taskDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要修改的数据!", "warining");
            }
        },
        updateDefeat: function () {
            var rowData = taskDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定修改任务为完成状态吗？", function (yes) {
                    if (yes) {
                        $.get("/task/updateDefeat?taskId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    taskDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要修改的数据!", "warining");
            }
        },
        describeTask: function () {
            var rowData = taskDatagrid.datagrid("getSelected");
            if (rowData) {
                describeTaskForm.form("clear");
                describeTaskDialog.dialog("setTitle", "修改任务描述");
                describeTaskDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                describeTaskForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的任务!", "warining");
            }
        },
        saveDescribeTask: function () {
            var updateUrl = "/task/updateProcessDescription";
            describeTaskForm.form("submit", {
                url: updateUrl,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            describeTaskDialog.dialog("close");
                            taskDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
    }
});

//状态
function stateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='aqua'>初始状态</font>";
    } else if (value == 1) {
        return "<font color='green'>已完成</font>";
    } else if (value == 2) {
        return "<font color='red'>失败</font>";
    }
}

function processDescriptionFormatter(value,row,index) {
    if (value!=null){
        return '<span title=' + value + '>' + value + '</span>';
    }
    return "<font color='green'>暂时还没有任何描述哦</font>";
}

function taskDescriptionFormatter(value,row,index) {
    return '<span title=' + value + '>' + value + '</span>';
}

//负责人
function processorFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}

//负责人
function processorNameFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}