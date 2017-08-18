$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var customerInfoDatagrid, customerInfoDialog, customerInfoForm, customerInfoCheckDialog, customerInfoCheckForm,
        turnClassDialog, turnClassForm, payDialog, payForm;
    customerInfoDatagrid = $("#customerInfo_datagrid");
    customerInfoDialog = $("#customerInfo_dialog");
    customerInfoForm = $("#customerInfo_form");
    customerInfoCheckDialog = $("#customerInfo_checkDialog");
    customerInfoCheckForm = $("#customerInfo_checkForm");
    turnClassDialog = $("#turnClass_dialog");
    turnClassForm = $("#turnClass_form");
    payDialog = $("#pay_dialog");
    payForm = $("#pay_form");

    /*
     * 初始化数据表格
     */
    customerInfoDatagrid.datagrid({
        url: "/officialCustomerInfo/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#customerInfo_datagrid_tb',
        onClickRow: function (rowIndex, rowData) {
            if (rowData.surplus == 0) {
                $("#pay_a_id").linkbutton({disabled: true})
            } else {
                $("#pay_a_id").linkbutton({disabled: false})
            }
        }
    });
    /*
     * 初始化新增/编辑对话框
     */
    customerInfoDialog.dialog({
        width: 400,
        height: 350,
        closed: true,
        buttons: '#customerInfo_dialog_bt'
    });
    //查看对话款
    customerInfoCheckDialog.dialog({
        width: 400,
        height: 350,
        closed: true,
        buttons: '#check_dialog_bt'
    });
    //转班对话款
    turnClassDialog.dialog({
        width: 400,
        height: 350,
        closed: true,
        buttons: '#turnClass_bt'
    });
    //付款对话款
    payDialog.dialog({
        width: 400,
        height: 200,
        closed: true,
        buttons: '#pay_bt'
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
            customerInfoForm.form("clear");
            customerInfoDialog.dialog("setTitle", "新增");
            customerInfoDialog.dialog("open");
        },
        edit: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                console.log(rowData)
                customerInfoForm.form("clear");
                customerInfoDialog.dialog("setTitle", "编辑");
                customerInfoDialog.dialog("open");

                $("#classId").combobox({disabled: true});

                customerInfoForm.form("load", rowData);
                if (rowData.principal != null) {
                    $("#principalId").combobox("setValue", rowData.principal.id);
                }
                if (rowData.classId != null) {
                    $("#classId").combobox("setValue", rowData.classId.id);
                }

                //$("#principal_id").val(rowData.principal.realname);
                //$("#class_id").val(rowData.classId.name);
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
                url = "/officialCustomerInfo/update"
            } else {
                url = "/officialCustomerInfo/save";
            }
            console.log(url);
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

        },
        checkCancel: function () {
            $("#customerInfo_checkDialog").dialog("close");

        },


        turnClassCancel: function () {
            turnClassDialog.dialog("close");
        },
        //查看
        check: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {

                customerInfoForm.form("clear");
                customerInfoDialog.dialog("setTitle", "查看");
                customerInfoCheckDialog.dialog("open");
                //只读
                $(".easyui-combobox").combobox({disabled: true});
                if (rowData.principal)
                    rowData["principal.id"] = rowData.principal.id;
                if (rowData.principal != null) {
                    $("#principalId").combobox("setValue", rowData.principal.id);
                }
                if (rowData.classId != null) {
                    $("#classSee").combobox("setValue", rowData.classId.id);
                }
                customerInfoCheckForm.form("load", rowData);

                $("#state_id").val("正式学员");
                //只读

                $("form input").prop("readonly", true);
            } else {
                $.messager.alert("温馨提示", "请选择需要查看的数据!", "warining");
            }
        },
        searchContent: function () {
            var keyword = $("[name='keyword']").val();
            customerInfoDatagrid.datagrid("load", {
                keyword: keyword
            });
        },
        //转班
        turnClass: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            console.log(rowData);
            if (rowData) {
                turnClassForm.form("clear");
                turnClassDialog.dialog("setTitle", "编辑");
                turnClassDialog.dialog("open");
                $("#formerClass").prop("readonly", true);
                customerInfoForm.form("load", rowData);
                if (rowData.classId != null) {
                    $("#turnClass").combobox("setValue", rowData.classId.id);
                }
                $("#formerClass").val(rowData.classId.name);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        //确认转班
        affirmTurnClass: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            console.log(rowData);
            var cusId = rowData.id;
            console.log(cusId);
            var url = "/officialCustomerInfo/updateClassId?customerInfoId=" + cusId;//修改班级的id Sql怎么写
            turnClassForm.form("submit", {
                url: url,

                /*onSubmit:function (param) {
                 var classId = $("#turnClass").combobox("getValue");
                 param["classId.id"]=classId;
                 // console.log(classId)
                 return true ;
                 },*/
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            turnClassDialog.dialog("close");
                            customerInfoDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //流失
        lose: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要流失选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/turnoverStudent/lose?customerInfoId=" + rowData.id, function (data) {
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
        //休学
        quit: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要休学该学员吗？", function (yes) {
                    if (yes) {
                        $.get("/officialCustomerInfo/quit?customerInfoId=" + rowData.id, function (data) {
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
                $.messager.alert("温馨提示", "请选择需要休学的学员!", "warining");
            }
        },
        //贷款
        loan: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要修改贷款方式为贷款吗？", function (yes) {
                    if (yes) {
                        $.get("/officialCustomerInfo/loan?customerInfoId=" + rowData.id, function (data) {
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
        //付款
        pay: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            console.log(rowData);
            if (rowData) {
                payForm.form("clear");
                payDialog.dialog("setTitle", "付款");
                payDialog.dialog("open");
                // $(".readonly").combobox({disabled: true});
                $("#allTuitiong_id").prop("readonly", true);//除了付款,其他都只读
                $("#surplus_id").prop("readonly", true);//除了付款,其他都只读
                $("#yetPay_id").prop("readonly", true);//除了付款,其他都只读

                payForm.form("load", rowData);


            } else {
                $.messager.alert("温馨提示", "请选择需要付款的学员!", "warining");
            }
        },

        payClose: function () {
            payDialog.dialog("close");
        },
        //付款
        affirmPay: function () {
            var rowData = customerInfoDatagrid.datagrid("getSelected");
            var pay = $("#pay_id").val();
            var customerInfoId = rowData.id;
            console.log(customerInfoId);
            var url = "/officialCustomerInfo/affirmPay?pay=" + pay + "&customerInfoId=" + customerInfoId;
            payForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            payDialog.dialog("close");
                            customerInfoDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });

        },
        //按班级查询
        /*searchClass:function () {

         var calssId = $("#class_select").val();
         alert(calssId);
         console.log(calssId);
         customerInfoDatagrid.datagrid("load",{
         calssId:calssId
         });
         }*/

    }



    $("#class_select").combobox({
        //newValue 改变后的值 oldValue 改变前的值
        onChange: function (newValue, oldValue) {


            console.debug(newValue);
            console.debug(oldValue);
           // var url = "/officialCustomerInfo/list?classId=" + newValue;
           // console.log(url);
            customerInfoDatagrid.datagrid("reload", {
                classId: newValue
             });
        }

    });


});
// 销售人员
function principalFormatter(value, record, index) {

    if (value) {
        return value.realname;
    }
    return value;
}

function stateFormatter(value, record, index) {
    //1表示正式学员
    if (value == 1) {
        return "<font color='green'>正式学员</font>";
    }
    if (value == 4) {
        return "<font color='red'>休学学员</font>";
    }
}
function classFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}
//性别
function genderFormatter(value, record, index) {
    if (value == 0) {
        return "男";
    } else {
        return "女";
    }
}
