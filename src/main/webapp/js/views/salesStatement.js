$(function () {
    var salesStatement_datagrid = $("#salesStatement_datagrid");
    var salesStatement_dialog = $("#salesStatement_dialog");
    var salesStatement_form = $("#salesStatement_form");
    var salesStatementExcelUpload = $("#salesStatement_excelUpload");
    salesStatement_datagrid.datagrid({
        url: "/salesStatement/list",
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        toolbar: '#salesStatement_toolbar',
        striped: true,
        columns: [[
            {field: 'salesTime', title: '销售时间', width: 100},
            {field: 'salesAmount', title: '销售金额', width: 100},
        ]]
    });
    var cmdObj = {

        //******************刷新*************************************************************
        reload: function () {
            salesStatement_datagrid.datagrid("reload");
        },
        searchKey: function () {
            var beginDate = $("[name='beginDate']").val();
            var endDate = $("[name='endDate']").val();
            if (beginDate || endDate) {
                salesStatement_datagrid.datagrid("reload", {
                    beginDate: beginDate,
                    endDate: endDate
                });
            }
        },
        highChart: function () {
            var beginDate = $("[name='beginDate']").val();
            var endDate = $("[name='endDate']").val();
                /*window.showModalDialog("/salesStatement" + "&beginDate=" + beginDate + "&endDate=" + endDate, "", "dialogHeight:600px;dialogWidth:1000px;");*/
            window.showModalDialog("/js/views/saleChartByLine.jsp");
        }


    };
    $("[data-cmd]").on("click", function () {

        var cmd = $(this).data("cmd");
        cmdObj[cmd]();
    });
});

