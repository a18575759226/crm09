$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var schoolContactDatagrid,schoolContactDialog,schoolContactForm;
	schoolContactDatagrid = $("#schoolContact_datagrid");
	schoolContactDialog = $("#schoolContact_dialog");
	schoolContactForm = $("#schoolContact_form");
	/*
	 * 初始化数据表格 
	 */
	schoolContactDatagrid.datagrid({
		url:"/schoolContact/list",
		fit:true,
        //title:'实训记录',
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#schoolContact_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	schoolContactDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#schoolContact_dialog_bt'
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
				schoolContactForm.form("clear");
				schoolContactDialog.dialog("setTitle","新增");
				schoolContactDialog.dialog("open");
			},
			edit:function(){
				var rowData = schoolContactDatagrid.datagrid("getSelected");
				//console.debug(rowData);
				//return;
				if(rowData){
					schoolContactForm.form("clear");
					schoolContactDialog.dialog("setTitle","编辑");
					schoolContactDialog.dialog("open");
					schoolContactForm.form("load",rowData);
					$("#gender").combobox("setValue",rowData.gender.name);
					$("#school").combobox("setValue",rowData.school.id);

				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = schoolContactDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/schoolContact/delete?schoolContactId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										schoolContactDatagrid.datagrid("reload");
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
				schoolContactDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/schoolContact/update"
				}else{
					url = "/schoolContact/save";
				}
				schoolContactForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								schoolContactDialog.dialog("close");
								schoolContactDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				schoolContactDialog.dialog("close");
			}
	}
});

function genderFormatter(value) {
    return value?value.name:"";
}
function schoolFormatter(value) {
    return value?value.name:"";
}

