$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var customerInfoDatagrid, customerInfoDialog, customerInfoForm, expertSearchDialog, expertSearchForm,
        customerTransferDialog, customerTransferForm;
    customerInfoDatagrid = $("#customerInfo_datagrid");
    customerInfoDialog = $("#customerInfo_dialog");
    customerInfoForm = $("#customerInfo_form");
    expertSearchDialog = $("#expertSearch_dialog");
    expertSearchForm = $("#expertSearch_form");
    customerTransferDialog = $("#customer_transfer_dialog");
    customerTransferForm = $("#customer_transfer_form");
    /*
     * 初始化数据表格
     */
    customerInfoDatagrid.datagrid({
        url: "/customerInfo/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#customerInfo_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    customerInfoDialog.dialog({
        width: 800,
        height: 400,
        closed: true,
        buttons: '#customerInfo_dialog_bt'
    });
    /**
     * 初始化高级查询对话框
     */
    expertSearchDialog.dialog({
        width: 600,
        height: 400,
        closed: true,
        buttons: '#customerInfo_dialog_expertSearch'
    });
    //移交dialog
    customerTransferDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#customerInfo_transfer_btn'
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
        add: function () {
            customerInfoForm.form("clear");
            customerInfoDialog.dialog("setTitle", "新增");
            customerInfoDialog.dialog("open");
        },
        edit: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                customerInfoForm.form("clear");
                customerInfoDialog.dialog("setTitle", "编辑");
                customerInfoDialog.dialog("open");
                //基于同名规则的数据回显
                customerInfoForm.form("load", rowData);
                //下拉框的数据回显
                if (rowData.intentionDiscipline != null) {
                    $("#intentionDiscipline").combobox('select', rowData.intentionDiscipline.name);
                }
                if (rowData.educationalHistory != null) {
                    $("#educationalHistory").combobox('select', rowData.educationalHistory.name);
                }
                if (rowData.intentionDegree != null) {
                    $("#intentionDegree").combobox('select', rowData.intentionDegree.name);
                }
                if (rowData.intentionSchool != null) {
                    $("#intentionSchool").combobox('select', rowData.intentionSchool.name);
                }
                if (rowData.major != null) {
                    $("#major").combobox('select', rowData.major.name);
                }
                if (rowData.customerSource != null) {
                    $("#customerSource").combobox('select', rowData.customerSource.name);
                }
                if (rowData.territory != null) {
                    $("#territory").combobox('select', rowData.territory.name);
                }
                if (rowData.workingCondition != null) {
                    $("#workingCondition").combobox('select', rowData.workingCondition.name);
                }
                if (rowData.inputUser != null) {
                    $("#inputUser_id").val(rowData.inputUser.id);
                }
                if (rowData.principal != null) {
                    $("#principal_id").val(rowData.principal.id);
                }
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/customerInfo/delete?customerInfoId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    customerInfoDatagrid.datagrid("reload");
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
            customerInfoDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/customerInfo/update"
            } else {
                url = "/customerInfo/save";
            }
            customerInfoForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            customerInfoDialog.dialog("close");
                            customerInfoDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            customerInfoDialog.dialog("close");
            expertSearchDialog.dialog("close");
        },
        search: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (!rowData) {

                $.messager.alert("温馨提示", "请选择需要查看的数据!", "warining");
            } else {
                customerInfoForm.form("clear");
                console.debug(customerInfoDialog.dialog("options"));
                customerInfoDialog.dialog("setTitle", "查看");
                customerInfoDialog.dialog("options").buttons = "#bt_search";
                customerInfoDialog.dialog("open");
                customerInfoDialog.dialog("refresh");
                //基于同名规则的数据回显
                customerInfoForm.form("load", rowData);
                //设置下拉框只读
                $(".easyui-combobox").combobox({disabled: true});
                //下拉框的数据回显
                if (rowData.intentionDiscipline != null) {
                    $("#intentionDiscipline").combobox('setValue', rowData.intentionDiscipline.name);
                }
                if (rowData.educationalHistory != null) {
                    $("#educationalHistory").combobox('setValue', rowData.educationalHistory.name);
                }
                if (rowData.intentionDegree != null) {
                    $("#intentionDegree").combobox('setValue', rowData.intentionDegree.name);
                }
                if (rowData.intentionSchool != null) {
                    $("#intentionSchool").combobox('setValue', rowData.intentionSchool.name);
                }
                if (rowData.major != null) {
                    $("#major").combobox('setValue', rowData.major.name);
                }
                if (rowData.customerSource != null) {
                    $("#customerSource").combobox('setValue', rowData.customerSource.name);
                }
                if (rowData.territory != null) {
                    $("#territory").combobox('setValue', rowData.territory.name);
                }
                if (rowData.workingCondition != null) {
                    $("#workingCondition").combobox('setValue', rowData.workingCondition.name);
                }
                if (rowData.gender != null) {
                    $("#gender_id").combobox('setValue', rowData.gender);
                }
                if (rowData.inputUser != null) {
                    $("#inputUser_id").val(rowData.inputUser.id);
                }
                if (rowData.principal != null) {
                    $("#principal_id").val(rowData.principal.id);
                }
                $("form input").prop("readonly", true);
            }
        },
        becomeFormalStudent: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要将该学员进行升班操作吗？", function (yes) {
                    if (yes) {
                        $.get("/customerInfo/becomeFormalStudent?customerInfoId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    customerInfoDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要升班的学员!", "warining");
            }
        },
        expertSearch: function () {
            expertSearchForm.form("clear");
            expertSearchDialog.dialog("setTitle", "高级查询");
            expertSearchDialog.dialog("open");
        },
        searchUp: function () {
            var url = "/customerInfo/list";
            expertSearchForm.form("submit", {
                url: url, success: function (data) {
                    data = $.parseJSON(data);
                    if (data) {
                        console.debug(data)
                        expertSearchDialog.dialog("close");
                        //loadData 加载本地数据 旧的将会被移除
                        customerInfoDatagrid.datagrid("loadData", data);
                    }
                }
            });
        },
        searchReflash: function () {
            expertSearchForm.form("clear");
        },
        //移交按钮
        transfer: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            console.debug(rowData);
            //$.get("/transferLog/getLogByCustomerId?customerId="+id,function (data) {
            //    if (data.success){
            //
            //    }else {
            //        $.messager.alert("温馨提示", data.msg, "warning");
            //    }
            //},"json");
            if (rowData) {
                var id = rowData.id;
                $.get("/transferLog/getLogByCustomerId?customerId="+id,function (data) {
                    if (data.success){
                        customerTransferDialog.form("clear");
                        customerTransferDialog.dialog("setTitle", "移交");
                        customerTransferDialog.dialog("open");
                        customerTransferForm.form("load", rowData);
                        if (rowData.name != null) {
                            $("#customer_id").val(rowData.name);
                        }
                        if (rowData.principal) {
                            $("#currentPrincipal_id").val(rowData.principal.realname);
                        }
                    }else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                },"json");

            } else {
                $.messager.alert("温馨提示", "请选择需要移交的客户!", "warning");
            }
        },
        //移交潜在客户信息
        transferBtn: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            var url = '/transferLog/transfer';
            customerTransferForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                    //param['currentPrincipal.id'] = rowData.principal.id;
                    param['customer.id'] = rowData.id;

                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        customerTransferDialog.dialog("close");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            })
        },
        searchContent: function () {
            var keyword = $("[name='keyword']").val();
            customerInfoDatagrid.datagrid("load", {
                keyword: keyword
            });
        },
        movePotentialClientPool: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要将该学员放入资源池吗？", function (yes) {
                    if (yes) {
                        $.get("/customerInfo/moveToPotentialClientPool?customerInfoId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    customerInfoDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要放入资源池的学员对象!", "warining");
            }
        },
    }

    $("#state_change").combobox({
            //newValue 改变后的值 oldValue 改变前的值
            onChange: function (newValue, oldValue) {
                console.debug(newValue);
                console.debug(oldValue);
                var url = "/customerInfo/list?=" + newValue;
                customerInfoDatagrid.datagrid("reload", {
                    state: newValue
                });
            }
        }
    )
});

//状态
function stateFormatter(value, record, index) {
    if (value == 0) {
        return "<font color='green'>潜在学员</font>";
    } else if (value == 1) {
        return "<font color='#8b0000'>正式学员</font>";
    }else if (value == 2) {
        return "<font color='aqua'>学员池</font>";
    }else if (value == 3) {
        return "<font color='red'>流失状态</font>";
    }else if (value == 4) {
        return "<font color='#8b0000'>休学状态</font>";
    }
}

//录入人
function inputUserFormatter(value, record, index) {
    if (value) {
        return value.realname;
    }
}

//负责人
function principalFormatter(value, record, index) {
    // console.debug(value)
    if (value) {
        return value.realname;
    }else{
        return "暂无";
    }
}

//学历
function educationalHistoryFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function intentionDisciplineFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function intentionDegreeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function intentionSchoolFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function customerSourceFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function territoryFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

function workingConditionFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}