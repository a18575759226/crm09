$(function () {
    var latentnumber_datagrid = $("#latentnumber_datagrid");
    var latentnumber_dialog = $("#latentnumber_dialog");
    var latentnumber_form = $("#latentnumber_form");
    var latentnumberExcelUpload = $("#latentnumber_excelUpload");
    latentnumberExcelUpload.dialog({
       //closable: true,
       closed: true,
        title: "下载",
        width: 280,
        height: 120,
        buttons: "#latentnumber_excelUpload_bt",

    })
    latentnumber_datagrid.datagrid({
        url: "/latentnumber/list",
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        toolbar: '#latentnumber_toolbar',
        striped: true,
        columns: [[
            {field: 'inputTime', title: '录入时间', width: 100},
            {field: 'marketingMan', title: '销售人员', width: 100},
            {field: 'clientNumber', title: '客户数量', width: 100}
        ]],
        onClickRow: function (rowindex, rowdata) {
            if (rowdata.state) {

            }
        }

    });
    latentnumber_dialog.dialog({
        closable: true,
        width: 300,
        height: 250,
        buttons: '#latentnumber_dialog_buttons',
        closed: true
    });


    var cmdObj = {

        //******************刷新*************************************************************
        reload: function () {
            latentnumber_datagrid.datagrid("reload");
        },
        //***************高级查询*******************************************************************
        // 搜索按钮



        add: function () {
           // alert(21212)
            //清空窗口数据
            //$('#latentnumber_excelFrom').form("clear")
            //设置标题
            $('#latentnumber_excelUpload').dialog("setTitle", "新增");
            //打开窗口
            $('#latentnumber_excelUpload').dialog("open");
        },
        excelsave: function () {
           // alert("5121")
            //$('#latentnumber_excelFrom').form("clear")
            //设置标题
            $('#latentnumber_excelUpload').dialog("setTitle", "新增");
            //打开窗口
            $('#latentnumber_excelUpload').dialog("open");
          /*  $.("#latentnumber_excelFrom").form("submit",{
                url: '/latentnumber/uploadExcelFile',
                success:function (data) {
                    data = $.parseJSON(data);
                    if(data.success){
                        $.messager.alert("温馨提示",data.msg,function () {
                            latentnumber_datagrid.datagrid("reload");
                        });
                    }else {
                        $.messager.alert("温馨提示",data.msg,"error")
                    }
                }
            })*/
        },

        searchKey: function () {
            var keyWordValue = $('[name="keyword"]').val();
            var beginDate = $("[name='beginDate']").val();
            var endDate = $("[name='endDate']").val();
            if (keyWordValue) {
                latentnumber_datagrid.datagrid("load", {
                    keyword: keyWordValue,
                });
            } else {
                //代码手动清除数据
                keyWordValue = "";
                latentnumber_datagrid.datagrid("reload", {
                    keyword: keyWordValue
                });
            }
            if (beginDate) {

                    latentnumber_datagrid.datagrid("reload", {
                        beginDate: beginDate
                    });
            }
            if (endDate) {
                latentnumber_datagrid.datagrid("reload", {
                    endDate: endDate
                });
            }
        }


    };
    $("[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    });
    //生成图标
    $(".btn_showChart").click(function () {

        window.showModalDialog("/latentnumber_showlist", "", "dialogHeight:600px;dialogWidth:1000px;")

    });

});
function marketingmanFormatter(value, row, index) {
    return value ? value.username : '';

}

