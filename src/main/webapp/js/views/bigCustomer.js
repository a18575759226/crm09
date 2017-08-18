$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var bigCustomerDatagrid,bigCustomerDialog,bigCustomerForm;
	bigCustomerDatagrid = $("#bigCustomer_datagrid");
	bigCustomerDialog = $("#bigCustomer_dialog");
	bigCustomerForm = $("#bigCustomer_form");
	/*
	 * 初始化数据表格 
	 */
	bigCustomerDatagrid.datagrid({
		url:"/bigCustomer/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#bigCustomer_datagrid_tb',
        //绑定点击事件
        onClickRow:function (rowIndex, rowData) {
            var id= rowData.id;
            console.debug(rowData);
            var trainingRecords = $("#trainingRecords_datagrid").datagrid("options");
            trainingRecords.url = "/trainingRecords/listRecordsByBigCustomerId?id="+id;
            $("#trainingRecords_datagrid").datagrid("reload");
        }
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	bigCustomerDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#bigCustomer_dialog_bt'
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		//console.debug(cmd);
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				bigCustomerForm.form("clear");
				bigCustomerDialog.dialog("setTitle","新增大客户");
				bigCustomerDialog.dialog("open");
			},
			edit:function(){
				var rowData = bigCustomerDatagrid.datagrid("getSelected");
				//console.debug(rowData);
                ////console.debug(rowData.schoolProperty.name);
				//return;
				if(rowData){
					bigCustomerForm.form("clear");
					bigCustomerDialog.dialog("setTitle","编辑");
					bigCustomerDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					bigCustomerForm.form("load",rowData);
                    $("#school_property").combobox("setValue",rowData.schoolProperty.name);
                    $("#starLevel").combobox("setValue",rowData.starLevel.name);
                    $("#curriculum").combobox("setValue",rowData.curriculum.name);
                    //if(rowData.cooperationSchool){//是高校
                    //    $("input[value='1']").attr("checked","true");
                    //}else {//不是合作高校
                    //    $("input[value='0']").attr("checked","true");
                    //}

				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = bigCustomerDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/bigCustomer/delete?bigCustomerId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										bigCustomerDatagrid.datagrid("reload");
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
				bigCustomerDatagrid.datagrid("reload");
			},
			save:function(){
                var rowData = bigCustomerDatagrid.datagrid("getSelected");
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/bigCustomer/update"
				}else{
					url = "/bigCustomer/save";
				}
				bigCustomerForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								bigCustomerDialog.dialog("close");
								bigCustomerDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				bigCustomerDialog.dialog("close");
			},
            searchSchool:function () {
			    var keyword = $("#keyword").val();
                bigCustomerDatagrid.datagrid("load",{
                    keyword:keyword
                });
            }
	}
});

function schoolPropertyFormatter(value,record,index) {
    return value?value.name:"";
}

function curriculumFormatter(value,record,index) {
    return value?value.name:"";
}
function starLevelFormatter(value,record,index) {
    return value?value.name:"";
}
function cooperationSchoolFormatter(value,record,index) {
    return value == 0?"<span style='color: red'>否</span>":"<span style='color: green'>是</span>";
}
function salesmanFormatter(value,record,index) {
    return value?value.realname:"";
}
function contactPropertyFormatter(value,record,index) {
    return value?value.name:"";
}


