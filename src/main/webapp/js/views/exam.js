$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var examDatagrid,examDialog,examForm;
	examBtnAndQuitBtn = $("#exam_editBtn,#exam_checkOKBtn,#exam_checkUNBtn");
	examDatagrid = $("#exam_datagrid");
	examDialog = $("#exam_dialog");
	examForm = $("#exam_form");
	/*
	 * 初始化数据表格 
	 */
	examDatagrid.datagrid({
		url:"/exam/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#exam_datagrid_tb',
		onClickRow:function(rowIndex,rowData){
			if(rowData.examResult==1){
				examBtnAndQuitBtn.linkbutton("disable");
			}else if(rowData.examResult==2){
				examBtnAndQuitBtn.linkbutton("disable");
			}
			else{
				examBtnAndQuitBtn.linkbutton("enable");
			}
		}
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	examDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#exam_dialog_bt'
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
				examForm.form("clear");
				examDialog.dialog("setTitle","新增");
				examDialog.dialog("open");
			},
			edit:function(){
				var rowData = examDatagrid.datagrid("getSelected");
				if(rowData){
					examForm.form("clear");
					examDialog.dialog("setTitle","编辑");
					examDialog.dialog("open");
					if(rowData.customerInfo){
						rowData["customerInfo.id"] = rowData.customerInfo.id;
					}
					if(rowData.schoolroom){
						rowData["schoolroom.id"] = rowData.schoolroom.id;
					}
					examForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = examDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/exam/delete?examId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										examDatagrid.datagrid("reload");
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
			//审核为合格
			checkOK:function(){
				var rowData = examDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定审核为合格吗？",function(yes){
						if(yes){
							$.get("/exam/check?examResult=1&id="+rowData.id,function(data){
								if(data.success){
								 	$.messager.alert("温馨提示",data.msg,"info",function(){
										examDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择您需要的数据!","warining");
				}
			},
			//审核为不合格
			checkUN:function(){
				var rowData = examDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定审核为不合格吗？",function(yes){
						if(yes){
							$.get("/exam/check?examResult=2&id="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										examDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择您需要的数据!","warining");
				}
			},
			reload:function(){
				examDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/exam/update"
				}else{
					url = "/exam/save";
				}
				examForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								examDialog.dialog("close");
								examDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				examDialog.dialog("close");
			},
			//审核
			searchContent:function(){
				var keyword = $("[name='keyword']").val();
				$("#exam_datagrid").datagrid("load",{
					keyword:keyword
				});
			}
	}
});
//数据格式化
function studentNameFormatter(value,row,index){
	if(row.customerInfo){
		return row.customerInfo.name;
	}
	return "";
	
}
function schoolroomFormatter(value,record,index){
	if(value){
		return value.name;
	}
	return value;
}
function QQFormatter(value,row,index){
	if(row.customerInfo){
		return row.customerInfo.qq;
	}
	return value;
}
function telFormatter(value,row,index){
	if(row.customerInfo){
		return row.customerInfo.tel;
	}
	return "";
}
function examResultFormatter(value,record,index){
	if(value==0){
		return "<font color='blue'>未处理</font>";
	}else if(value==1){
		return "<font color='green'>合格</font>";
	}else if(value==2){
		return "<font color='red'>不合格</font>";
	}
}
function employeeFormatter(value,record,index){
	if(value){
		return value.realname;
	}
	return value;
}
