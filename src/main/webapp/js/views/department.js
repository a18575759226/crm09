$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var departmentDatagrid,departmentDialog,departmentForm,departmentEditBtnAndQuitBtn;
	departmentDatagrid = $("#department_datagrid");
	departmentDialog = $("#department_dialog");
	departmentForm = $("#department_form");
    departmentEditBtnAndQuitBtn=$("#department_editBtn,#department_quitBtn");
	/*
	 * 初始化数据表格 
	 */
	departmentDatagrid.datagrid({
		url:"/department/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#department_datagrid_tb',
        onClickRow:function(rowIndex,rowData){
            //判断当前记录中的状态的值.
            if(rowData.state==1){
                //员工已经离职了,编辑和离职按钮应该变灰.
                departmentEditBtnAndQuitBtn.linkbutton("disable");
            }else{
                //启用按钮
                departmentEditBtnAndQuitBtn.linkbutton("enable");
            }
        }
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	departmentDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#department_dialog_bt'
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
				departmentForm.form("clear");
				departmentDialog.dialog("setTitle","新增");
				departmentDialog.dialog("open");
			},
			edit:function(){
				var rowData = departmentDatagrid.datagrid("getSelected");
				if(rowData){
					departmentForm.form("clear");
					departmentDialog.dialog("setTitle","编辑");
					departmentDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					departmentForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的部门!","warining");
				}
			},
			del:function(){
				var rowData = departmentDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要撤销本部门吗？",function(yes){
						if(yes){
							$.get("/department/delete?departmentId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										departmentDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要撤销的部门!","warining");
				}
			},
			reload:function(){
				departmentDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/department/update"
				}else{
					url = "/department/save";
				}
				departmentForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								departmentDialog.dialog("close");
								departmentDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				departmentDialog.dialog("close");
			}
	}
});

function stateFormatter(value,record,index){
    if(value==0){
        return "<font color='green'>正常</font>";
    }else if(value==1){
        return "<font color='red'>已撤销</font>";
    }
}
