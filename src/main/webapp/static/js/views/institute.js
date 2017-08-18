$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var instituteDatagrid,instituteDialog,instituteForm;
	instituteDatagrid = $("#institute_datagrid");
	instituteDialog = $("#institute_dialog");
	instituteForm = $("#institute_form");
	/*
	 * 初始化数据表格 
	 */
	instituteDatagrid.datagrid({
		url:"/institute/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#institute_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	instituteDialog.dialog({
		width:300,
		height:150,
		closed:true,
		buttons:'#institute_dialog_bt'
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
				instituteForm.form("clear");
				instituteDialog.dialog("setTitle","新增");
				instituteDialog.dialog("open");
			},
			edit:function(){
				var rowData = instituteDatagrid.datagrid("getSelected");
				if(rowData){
					instituteForm.form("clear");
					instituteDialog.dialog("setTitle","编辑");
					instituteDialog.dialog("open");
					if(rowData.institute)
						rowData["institute.id"] = rowData.institute.id;
					instituteForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = instituteDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/institute/delete?instituteId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										instituteDatagrid.datagrid("reload");
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
				instituteDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/institute/update"
				}else{
					url = "/institute/save";
				}
				instituteForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								instituteDialog.dialog("close");
								instituteDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				instituteDialog.dialog("close");
			}
	}
});
