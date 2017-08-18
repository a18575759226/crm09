$(function() {
	var mygrid, mydialog, toolsa, myform, role_sele;

	mygrid = $("#mydg");
	mydialog = $("#mydialog");
	toolsa = $("#mytools a");
	myform = $("#myform");
	role_sele = $("#role_sele");
	mydialog.dialog({
		width : 300,
		height : 300,
		top : 100,
		resizable : true,
		modal : true,
		closed : true,
		buttons : "#dialogbutton"
	});

	$("a").on("click", function() {
		var cmd = $(this).data("cmd");
		if (cmd) {
			cmdObj[cmd]();
		}
	});
	mygrid.datagrid({
		url : "employee",
		columns : [ [ {
			field : "id",
			title : "编号",
			width : 1
		}, {
			field : "userName",
			title : "用户名",
			width : 1
		}, {
			field : "realName",
			title : "真实姓名",
			width : 1
		}, {
			field : "tel",
			title : "联系电话",
			width : 1
		}, {
			field : "email",
			title : "邮箱",
			width : 1
		}, {
			field : "inputTime",
			title : "录入时间",
			width : 1
		}, {
			field : "dept.name",
			title : "部门",
			width : 1
		}, {
			field : "state",
			title : "状态",
			width : 1,
			formatter : function(value, row) {
				if (row.state) {
					return "<font  color='green'>正常</font>";
				} else {
					return "<font  color='gray'>离职</font>";
				}
			}
		} ] ],
		fitColumns : true,
		toolbar : "#mytools",
		pagination : true,
		singleSelect : true,
		rownumbers : true,
		pagePosition : "bottom",
		fit : true,
		pageSize : 5,
		pageList : [ 5, 8, 10 ],
		onClickRow : function(rowIndex, rowData) {
			if (rowData.state == 0) {
				// 离职
				toolsa.eq(1).linkbutton({
					disabled : true
				})
				toolsa.eq(2).linkbutton({
					disabled : true
				})
			} else {
				// 正常
				toolsa.eq(1).linkbutton("enable");
				toolsa.eq(2).linkbutton("enable");
			}
		}
	});

	

});
