$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var schoolClassDatagrid,schoolClassDialog,schoolClassForm;
	schoolClassDatagrid = $("#schoolClass_datagrid");
	schoolClassDialog = $("#schoolClass_dialog");
	schoolClassForm = $("#schoolClass_form");
	/*
	 * 初始化数据表格 
	 */
	schoolClassDatagrid.datagrid({
		url:"/schoolClass/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#schoolClass_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	schoolClassDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#schoolClass_dialog_bt'
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
				schoolClassForm.form("clear");
				schoolClassDialog.dialog("setTitle","新增");
				schoolClassDialog.dialog("open");
			},
			edit:function(){
				var rowData = schoolClassDatagrid.datagrid("getSelected");
				if(rowData){
					schoolClassForm.form("clear");
					schoolClassDialog.dialog("setTitle","编辑");
					schoolClassDialog.dialog("open");
					if(rowData.institute){
						rowData["institute.id"] = rowData.institute.id;
					}
					if(rowData.employee){
						rowData["employee.id"] = rowData.employee.id;
					}
					if(rowData.schoolroom){
						rowData["schoolroom.id"] = rowData.schoolroom.id;
					}
					//回显数据
					schoolClassForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = schoolClassDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/schoolClass/delete?schoolClassId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										schoolClassDatagrid.datagrid("reload");
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
				schoolClassDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/schoolClass/update"
				}else{
					url = "/schoolClass/save";
				}
				schoolClassForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								schoolClassDialog.dialog("close");
								schoolClassDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				schoolClassDialog.dialog("close");
			}
	}
});
function instituteFormatter(value,record,index){
	if(value){
		return value.name;
	}
	return value;
	
}
function schoolroomFormatter(value,record,index){
	console.debug(value);
	console.debug(record);
	console.debug(index);
	if(value){
		return value.name;
	}
	return value;
	
}
function employeeFormatter(value,record,index){
	if(value){
		return value.realname;
	}
	return value;
	
}
