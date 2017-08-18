$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var trakcsStudentsDatagrid,trakcsStudentsDialog,trakcsStudentsForm;
	trakcsStudentsDatagrid = $("#trakcsStudents_datagrid");
	trakcsStudentsDialog = $("#trakcsStudents_dialog");
	trakcsStudentsForm = $("#trakcsStudents_form");
	/*
	 * 初始化数据表格 
	 */
	trakcsStudentsDatagrid.datagrid({
		url:"/trakcsStudents/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#trakcsStudents_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	trakcsStudentsDialog.dialog({
		width:800,
		height:400,
		closed:true,
		buttons:'#trakcsStudents_dialog_bt'
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
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				trakcsStudentsForm.form("clear");
				trakcsStudentsDialog.dialog("setTitle","新增");
				trakcsStudentsDialog.dialog("open");
			},
			edit:function(){
				var rowData = trakcsStudentsDatagrid.datagrid("getSelected");
				if(rowData){
					trakcsStudentsForm.form("clear");
					trakcsStudentsDialog.dialog("setTitle","编辑");
					trakcsStudentsDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					trakcsStudentsForm.form("load",rowData);

                    //下拉框的数据回显
                    if (rowData.talkWay != null) {
                        $("#talkWay").combobox('select', rowData.talkWay.name);
                    }
                    if (rowData.intentionDiscipline != null) {
                        $("#intentionDiscipline").combobox('select', rowData.intentionDegree.name);
                    }
                    if (rowData.employee != null) {
                        $("#employee_id").combobox('select', rowData.employee.realname);
                    }
                    if (rowData.customerInfo != null) {
                        $("#customerInfo").combobox('select', rowData.customerInfo.name);
                    }
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = trakcsStudentsDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/trakcsStudents/delete?trakcsStudentsId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										trakcsStudentsDatagrid.datagrid("reload");
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
				trakcsStudentsDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/trakcsStudents/update"
				}else{
					url = "/trakcsStudents/save";
				}
				trakcsStudentsForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								trakcsStudentsDialog.dialog("close");
								trakcsStudentsDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				trakcsStudentsDialog.dialog("close");
			},
        search: function () {
            var rowData = trakcsStudentsDatagrid.datagrid("getSelected");
            if (!rowData) {
                $.messager.alert("温馨提示", "请选择需要查看的数据!", "warining");
            } else {
                trakcsStudentsForm.form("clear");
                trakcsStudentsDialog.dialog("setTitle", "查看");
                trakcsStudentsDialog.dialog("open");
                trakcsStudentsDialog.dialog("refresh");
                //基于同名规则的数据回显
                trakcsStudentsForm.form("load", rowData);
                //设置下拉框只读
                $(".easyui-combobox").combobox({disabled: true});
                //下拉框的数据回显
                if (rowData.talkWay != null) {
                    $("#talkWay").combobox('select', rowData.talkWay.name);
                }
                if (rowData.intentionDiscipline != null) {
                    $("#intentionDiscipline").combobox('select', rowData.intentionDegree.name);
                }
                if (rowData.employee != null) {
                    $("#employee_id").combobox('select', rowData.employee.realname);
                }
                if (rowData.customerInfo != null) {
                    $("#customerInfo").combobox('select', rowData.customerInfo.name);
                }
                $("form input").prop("readonly", true);
            }
        }
	}
});

//意向程度
function intentionDegreeFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

//交流方式
function talkWayFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}

//咨询人员
function employeeFormatter(value, record, index) {
    // console.debug(value)
    if (value) {
        return value.realname;
    }else{
        return "暂无";
    }
}

//跟进学员
function customerInfoFormatter(value, record, index) {
    // console.debug(value)
    if (value) {
        return value.name;
    }else{
        return "暂无";
    }
}