$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var courselistDatagrid,courselistDialog,courselistForm;
	courselistDatagrid = $("#courselist_datagrid");
	courselistEditBtnAndQuitBtn = $("#courselist_editBtn,#courselist_checkBtn");
	courselistDialog = $("#courselist_dialog");
	courselistForm = $("#courselist_form");
	/*
	 * 初始化数据表格 
	 */
	courselistDatagrid.datagrid({
		url:"/courselist/list",
		fit:true,
		rownumbers:true,
		height:500,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#courselist_datagrid_tb',
		onClickRow:function(rowIndex,rowData){
			if(rowData.state==1){
				courselistEditBtnAndQuitBtn.linkbutton("disable");
			}else{
				courselistEditBtnAndQuitBtn.linkbutton("enable");
			}
		}
	});
	/*
	 * 初始化班级表格 
	 */
	$("#schoolClass_datagrid").datagrid({
		url:"/schoolClass/list",
		fit:true,
		rownumbers:true,
		height:500,
		singleSelect:true,
		striped:true,
		fitColumns:true,
		 onClickRow: function (rowIndex, rowData) {
	            var options = $("#schoolClass_datagrid").datagrid('options');
	            options.url = "/courselist/list";
	            courselistDatagrid.datagrid('load', {
	            	schoolclassId: rowData.id
	            });
	        }
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	courselistDialog.dialog({
		width:300,
		height:350,
		closed:true,
		buttons:'#courselist_dialog_bt'
	});
	/*
	 * 初始化导出Excel对话框 
	 */
	$("#importExcel_dialog").dialog({
		width:300,
		height:200,
		closed:true,
		buttons:'#importExcel_dialog_bt'
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
				courselistForm.form("clear");
				courselistDialog.dialog("setTitle","新增");
				courselistDialog.dialog("open");
			},
			edit:function(){
				var rowData = courselistDatagrid.datagrid("getSelected");
				if(rowData){
					courselistForm.form("clear");
					courselistDialog.dialog("setTitle","编辑");
					courselistDialog.dialog("open");
					if(rowData.headTeacher){
						rowData["headTeacher.id"] = rowData.headTeacher.id;
					}
					if(rowData.schoolclass){
						rowData["schoolclass.id"] = rowData.schoolclass.id;
					}
					if(rowData.schoolroom){
						rowData["schoolroom.id"] = rowData.schoolroom.id;
					}
					if(rowData.teacher){
						rowData["teacher.id"] = rowData.teacher.id;
					}
					//回显数据,基于同名匹配规则
					courselistForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = courselistDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/courselist/delete?courselistId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										courselistDatagrid.datagrid("reload");
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
			check:function(){
				var rowData = courselistDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定该课程已经上过吗？",function(yes){
						if(yes){
							$.get("/courselist/checkBycourselistId?courselistId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										courselistDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要的数据!","warining");
				}
			},
			reload:function(){
				courselistDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/courselist/update"
				}else{
					url = "/courselist/save";
				}
				courselistForm.form("submit",{
					url:url,
					success:function(data){
						console.debug(data)
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								courselistDialog.dialog("close");
								courselistDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				//取消按钮,关闭窗口
				courselistDialog.dialog("close");
				$("#importExcel_dialog").dialog("close");
			},
			//高级查询
			searchContent:function(){
				var teacherId = $("[name='teacherId']").val();
				var schoolroomId = $("[name='schoolroomId']").val();
				courselistDatagrid.datagrid("load",{
					teacherId:teacherId,
					schoolroomId:schoolroomId
				});
			},
			//导入excel onclick="window.open('/courselist/importExcel')"
			importExcel:function (){
				$("#importExcel_form").form("clear");
				$("#importExcel_dialog").dialog("setTitle","选择文件");
				$("#importExcel_dialog").dialog("open");
			},
			//确定按钮
			import:function(){
				var url = "/courselist/importExcel";
				$("#importExcel_form").form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								$("#importExcel_dialog").dialog("close");
								courselistDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			}
	}
});
//状态
function stateFormatter(value,record,index){
	if(value==0){
		return "<font color='green'>未上</font>";
	}else if(value==1){
		return "<font color='red'>已上</font>";
	}
};
//班级
function schoolclassFormatter(value,record,index){
	if(value){
		return value.name;
	}
	return value;
};
//教室
function schoolroomFormatter(value,record,index){
	if(value){
		return value.name;
	}
	return value;
};
//班主任
function headTeacherFormatter(value,record,index){
	if(value){
		return value.realname;
	}
	return value;
};
//任课老师
function teacherFormatter(value,record,index){
	if(value){
		return value.realname;
	}
	return value;
};
// 星期
function weekdayFormatter(value, record, index) {
	if (value == 0) {
		return "星期天";
	} else if (value == 1) {
		return "星期一";
	} else if (value == 2) {
		return "星期二";
	} else if (value == 3) {
		return "星期三";
	} else if (value == 4) {
		return "星期四";
	} else if (value == 5) {
		return "星期五";
	}else if (value == 5) {
		return "星期六";
	}
	else{
		return "";
	}
};
