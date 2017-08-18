
$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var checkOutDatagrid,checkOutDialog,checkOutForm,stateCondition_select;
	checkOutDatagrid = $("#checkOut_datagrid");
	checkOutDialog = $("#checkOut_dialog");
	checkOutForm = $("#checkOut_form");
    stateConditionSelect = $("#stateCondition_select");
	/*
	 * 初始化数据表格 
	 */
	checkOutDatagrid.datagrid({
		url:"/checkOut/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#checkOut_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	checkOutDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#checkOut_dialog_bt'
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
    var excelUrl="/checkOut/Excel?state=-1";
    stateConditionSelect.combobox({
        onSelect: function (record) {
            excelUrl=$("#excel").data("url")+record.value;
            checkOutDatagrid.datagrid("load", {
                state: record.value
            });
        }
    });
    $("#excel").on("click",function () {
        window.open(excelUrl);
    })
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				checkOutForm.form("clear");
				checkOutDialog.dialog("setTitle","新增");
				checkOutDialog.dialog("open");
			},
			edit:function(){
				var rowData = checkOutDatagrid.datagrid("getSelected");
				if(rowData){
					checkOutForm.form("clear");
					checkOutDialog.dialog("setTitle","编辑");
					checkOutDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					checkOutForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = checkOutDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/checkOut/delete?checkOutId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										checkOutDatagrid.datagrid("reload");
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
			},
			reload:function(){
				checkOutDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/checkOut/update"
				}else{
					url = "/checkOut/save";
				}
				checkOutForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								checkOutDialog.dialog("close");
								checkOutDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				checkOutDialog.dialog("close");
			}
	}
});
function stateFormatter(value, record, index) {
    if (value == 1) {
        return '<font color="green">正常上班</font>';
    } else if(value == 0) {
        return '<font color="red">迟到早退</font>';
    }else{
        return '<font color="red">没记录</font>';
    }
}
function timesFormatter(value, record, index) {
    if(record.state==-1){0
        return 0;
    }else{
        return record.times;
    }
}
function searchContent() {
    var state = $("[name='state']").val();
    var beginDate = $("[name='beginDate']").val();
    var endDate = $("[name='endDate']").val();
    console.debug(state,beginDate,endDate);
    $("#checkOut_datagrid").datagrid("load", {
        state: state,
        beginDate:beginDate,
        endDate:endDate
    });
}
