$(function () {
        $("#main_menu").tree({
            url: '/systemMenu/indexMenu',
            onClick: function (node) {
                var mainTabs = $("#main_tabs");
                //判断当前点击的节点是否已经创建对应的面板了?
                if (mainTabs.tabs("exists", node.text)) {
                    //选中选项卡
                    mainTabs.tabs("select", node.text);
                } else {
                    //新增选项卡
                    //找到选项卡,往选项卡中添加面板
                    mainTabs.tabs("add", {
                        title: node.text,
                        closable: true,
                        //href:node.attributes.url//只能加载远程页面中的body部分的内容
                        content: '<iframe src="' + node.attributes.url + '" style="width:100%;height:100%" frameborder="0"></iframe>'
                    });
                }
            }
        });
        //初始化对话框
        $("#main_dialog").dialog({
            width: 300,
            height: 300,
            buttons: '#main_dialog_bt',
            closed: true
        });


        //-------------------------首页面板--------------------------
        //设置portal
        $("#protal_main").portal();
        //为portal添加面板
        $("#protal_main").portal("add", {
            panel: $("#protal_one"),
            columnIndex: 0
        });
        $("#protal_main").portal("add", {
            panel: $("#protal_two"),
            columnIndex: 1
        });
        $("#protal_main").portal("add", {
            panel: $("#protal_three"),
            columnIndex: 2
        });
        //给日常安排一个点击事件 弹出具体的日程安排界面
        $("#index_schedule").calendar({
            onSelect: function (date) {
                window.showModalDialog("/calendar");
            }
        });

        //为日程安排的表格添加值
        $("#index_table").datagrid({
            url: "/task/list",
//			pagination: true,
            rownumbers: true,
            fit: true,
            fitColumns: true,
            singleSelect: true,
            striped: true,
            nowrap: true,
            toolbar: '#task_datagrid_tb',
            columns: [[
                {field: 'time', title: '日期', width: 100, align: 'center'},
                {field: 'processor', title: '处理人', width: 150, align: 'center', formatter: processorFormatter},
                {field: 'taskDescription', title: '任务描述', width: 150, align: 'center', formatter: taskDescriptionFormatter},
                {
                    field: 'processDescription',
                    title: '处理描述',
                    width: 100,
                    align: 'center',
                    formatter: processDescriptionFormatter
                },
                {field: 'state', title: '状态', width: 150, align: 'center', formatter: taskStateFormatter}
            ]]
        });


        //任务分配的相关js

        var taskDatagrid, taskDialog, taskForm, describeTaskDialog, describeTaskForm;
        taskDatagrid = $("#index_table");
        taskDialog = $("#task_dialog");
        taskForm = $("#task_form");
        describeTaskDialog = $("#describeTask_dialog");
        describeTaskForm = $("#describeTask_form");
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
                console.debug(rowData);
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
    }
);


function processDescriptionFormatter(value, row, index) {
    if (value != null) {
        return '<span title=' + value + '>' + value + '</span>';
    }
    return "<font color="
    green
    ">暂无</font>";
}


function taskDescriptionFormatter(value, row, index) {
    return '<span title=' + value + '>' + value + '</span>';
}
//负责人
function processorNameFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}

//录入人
function processorFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}
//状态
function taskStateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='blue'>初始状态</font>";
    } else if (value == 1) {
        return "<font color='green'>已完成</font>";
    } else if (value == 2) {
        return "<font color='red'>失败</font>";
    }
}


function editpassword() {
    $("#mian_form").form("clear");
    $("#main_dialog").dialog("setTitle", "修改密码");
    $("#main_dialog").dialog("open");
}

    function editpassword() {
        $("#mian_form").form("clear");
        $("#main_dialog").dialog("setTitle", "修改密码");
        $("#main_dialog").dialog("open");

    }

    function cancel() {
        $("#main_dialog").dialog("close");
    }

    function save() {
        var url = "/employee/editPasswordByUsername";
        $("#mian_form").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    //点击提交的时候,关闭对话框,弹出提示消息
                    $.messager.alert("温馨提示", data.msg, "info", function () {
                        $("#main_dialog").dialog("close");
                        $("#mian_form").form("clear");
                        //退出系统重新登录
                        $.get("/logout");
                    })
                } else {
                    $.messager.alert("温馨提示", data.msg, "error");
                }
            }
        });
    }

    $.extend($.fn.validatebox.defaults.rules, {
        /* 必须和某个字段相等 */
        equalTo: {
            validator: function (value, param) {
                return $(param[0]).val() == value;
            },
            message: '字段不匹配'
        }
    })

