$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var systemDictionaryItemDatagrid,systemDictionaryItemDialog,systemDictionaryItemForm;
	systemDictionaryItemDatagrid = $("#systemDictionaryItem_datagrid");
	systemDictionaryItemDialog = $("#systemDictionaryItem_dialog");
	systemDictionaryItemForm = $("#systemDictionaryItem_form");
	/*
	 * 初始化数据表格 
	 */
	systemDictionaryItemDatagrid.datagrid({
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
        toolbar:"#systemDictionaryItem_datagrid_tb"
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	systemDictionaryItemDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#systemDictionaryItem_dialog_bt'
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
				systemDictionaryItemForm.form("clear");
				systemDictionaryItemDialog.dialog("setTitle","新增");
				systemDictionaryItemDialog.dialog("open");
			},
			edit:function(){
				var rowData = systemDictionaryItemDatagrid.datagrid("getSelected");
				if(rowData){
					systemDictionaryItemForm.form("clear");
					systemDictionaryItemDialog.dialog("setTitle","编辑");
					systemDictionaryItemDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					systemDictionaryItemForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = systemDictionaryItemDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/systemDictionaryItem/delete?systemDictionaryItemId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										systemDictionaryItemDatagrid.datagrid("reload");
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
				systemDictionaryItemDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/systemDictionaryItem/update"
				}else{
					url = "/systemDictionaryItem/save";
				}
				systemDictionaryItemForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								systemDictionaryItemDialog.dialog("close");
								systemDictionaryItemDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				systemDictionaryItemDialog.dialog("close");
			}
	}
});

