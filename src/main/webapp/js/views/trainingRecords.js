$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var trainingRecordsDatagrid, trainingRecordsDialog, trainingRecordsForm;
    trainingRecordsDatagrid = $("#trainingRecords_datagrid");
    trainingRecordsDialog = $("#trainingRecords_dialog");
    trainingRecordsForm = $("#trainingRecords_form");
    /*
     * 初始化数据表格
     */
    trainingRecordsDatagrid.datagrid({
        //url:"/trainingRecords/listRecordsByBigCustomerId?id=1",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#trainingRecords_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    trainingRecordsDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#trainingRecords_dialog_bt'
    });
    /*
     * 对页面按钮进行统一监听
     */
    $("a[data-record]").on("click", function () {
        var cmd = $(this).data("record");
        //console.debug(cmd);
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    /*
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        addRecord: function () {
            var selectBig = $("#bigCustomer_datagrid").datagrid("getSelected");
            if (selectBig) {
                trainingRecordsForm.form("clear");
                trainingRecordsDialog.dialog("setTitle", "新增实训记录");
                trainingRecordsDialog.dialog("open");

            } else {
                $.messager.alert("温馨提示", "请选择对应的大客户!", "warining");
            }

        },
        editRecord: function () {
            var rowData = trainingRecordsDatagrid.datagrid("getSelected");
            if (rowData) {
                trainingRecordsForm.form("clear");
                trainingRecordsDialog.dialog("setTitle", "编辑");
                trainingRecordsDialog.dialog("open");
                trainingRecordsForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        /*del:function(){
         var rowData = trainingRecordsDatagrid.datagrid("getSelected");
         if(rowData){
         $.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
         if(yes){
         $.get("/trainingRecords/delete?trainingRecordsId="+rowData.id,function(data){
         if(data.success){
         $.messager.alert("温馨提示",data.msg,"info",function(){
         trainingRecordsDatagrid.datagrid("reload");
         });
         }else{
         $.messager.alert("温馨提示",data.msg,"error");
         }
         },"json")
         }
         });
         }else{
         $.messager.alert("温馨提示","请选择需要删除的数据!","warining");
         }
         },*/
        /*	reload:function(){
         trainingRecordsDatagrid.datagrid("reload");
         },*/
        saveRecord: function () {
            var url;
            var idVal = $("#rainingRecords_id").val();
            if (idVal) {
                url = "/trainingRecords/update"
            } else {
                url = "/trainingRecords/save";
            }
            //console.debug(url);
            //获取到大客户的id,并提交到后台
            var selectBig = $("#bigCustomer_datagrid").datagrid("getSelected");
            var school_id = selectBig.id;
            //trainingRecordsForm.form("clear");
            trainingRecordsForm.form("submit", {
                url: url,
                onSubmit: function(param){
                    param['bigCustomer.id'] = school_id;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            trainingRecordsDialog.dialog("close");
                            trainingRecordsDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancelRecord: function () {
            trainingRecordsDialog.dialog("close");
        }
    }
});
