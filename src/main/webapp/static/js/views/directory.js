$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var directoryDatagrid,directoryDialog,directoryForm,uploadDialog;
	directoryDatagrid = $("#directory_datagrid");
	directoryDialog = $("#directory_dialog");
	directoryForm = $("#directory_form");
    uploadDialog = $("#upload_dialog");
	/*
	 * 初始化数据表格 
	 */
	directoryDatagrid.datagrid({
		url:"/directory/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#directory_datagrid_tb',
        //双击事件,发送请求
        onDblClickRow:function (rowIndex, rowData) {

            console.debug(rowData);
            $.get("/directory/listSonDic?parentPath="+rowData.dicPath,function (data) {
                directoryDatagrid.datagrid("loadData",data);
            },"json");


        }
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	directoryDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#directory_dialog_bt'
	});
    uploadDialog.dialog({
        width:300,
        height:150,
        title:"文件上传",
        //closed:true
        buttons:"#upload_dialog_bt"
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
				directoryForm.form("clear");
				directoryDialog.dialog("setTitle","新增");
				directoryDialog.dialog("open");
                var allData = directoryDatagrid.datagrid("getSelected");
                console.debug(allData);
			},
			edit:function(){
				var rowData = directoryDatagrid.datagrid("getSelected");
				if(rowData){
					directoryForm.form("clear");
					directoryDialog.dialog("setTitle","编辑");
					directoryDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					directoryForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = directoryDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/directory/delete?directoryId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										directoryDatagrid.datagrid("reload");
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
				directoryDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/directory/update"
				}else{
					url = "/directory/save";
				}
				directoryForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								directoryDialog.dialog("close");
								directoryDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				directoryDialog.dialog("close");
			}
	}
});

function intoRoot() {
    $.get("/directory/list?parentId",function (data) {
        $("#directory_datagrid").datagrid("loadData",data);
    },"json");
}

function iconFormatter(value) {
    if(value.fileType == 1){
        var filepath = "/static/images/dicicon.jpg";
        //return '<img src='+"/static/images/dicicon.jpg"+' alt="">';

        return "文件";
    }
}
