<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='/plugins/fullcalendar-2.9.1/lib/cupertino/jquery-ui.min.css'/>
	<link href='/plugins/fullcalendar-2.9.1/fullcalendar.css' rel='stylesheet'/>
	<link href='/plugins/fullcalendar-2.9.1/fullcalendar.print.css' rel='copyright' media='print'/>
	<link rel="stylesheet" href="/plugins/jiaoben295/css/style.css">

	<script src='/plugins/fullcalendar-2.9.1/lib/moment.min.js'></script>
	<script src='/plugins/fullcalendar-2.9.1/lib/jquery.min.js'></script>
	<script src='/plugins/fullcalendar-2.9.1/fullcalendar.min.js'></script>
	<script src='/plugins/fullcalendar-2.9.1/lang-all.js'></script>

	<script type="text/javascript" src="/plugins/jquery-easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/plugins/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/plugins/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/js/views/calendar.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				defaultDate: '2017-07-19',
				selectable: true,
				selectHelper: true,
				editable: true,
				eventLimit: true, // allow "more" link when too many events
				events: function (start, end, timezone, callback) {
					$.ajax({
						url: "/calendar/selectAll",
						cache: false,
						success: function (doc) {
							callback(doc);
						}
					});
				},
				dayClick: function (date, allDay, jsEvent, view, event) {
					// 把对话框打开
					$("#mydialog").dialog("open");
					$("#mydialog").dialog("setTitle", "新增日程");
					$("#myform").form("clear");
				},
				eventClick: function (event, jsEvent, view) {
					$.messager.confirm("确认", "确定要把这个日程删除?", function (ok) {
						if (ok) {
							$.get("calendar/delete?delId=" + event.id,
									function (data) {
										if (data.success) {

											$.messager.alert("提示消息", data.msg,
													"info");
											window.location.reload();
										}
									})
						}
					})

				}
			});
			$("#mydialog").dialog({
				buttons: "#mydailogbut"
			});
			$("#formsave").on("click", function () {
				$("#myform").form("submit", {
					url: "calendar/save",
					success: function (data) {
						data = $.parseJSON(data);
						if (data.success) {
							$.messager.alert("提示消息", data.msg, "info")
							$("#mydialog").dialog("close");
							window.location.reload();
						} else {
							$("#mydialog").dialog("close");
							$.messager.alert("提示消息", data.msg, "info")
						}
					}
				})
			});

		});
	</script>
	<style>
		body {
			margin: 100px 10px;
			padding: 0;
			font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
			font-size: 14px;
		}

		#calendar {
			max-width: 900px;
			margin: 0 auto;
		}
	</style>
</head>
<body>

<div id='calendar'></div>
<!-- 对话框按钮 -->
<div id="mydialog">
	<form id="myform" method="post" >
		<input type="hidden" name=id/>
		<table align="center" style="margin-top: 30px;">
			<tr>
				<th>标题</th>
				<th><input type="text" name="title"/></th>
			</tr>
			<tr>
				<th>开始时间</th>
				<th><input class="easyui-datetimebox" name="start"
				           data-options="required:true,showSeconds:true"
				           value="2017-07-10 00:00:00" style="width:150px"></th>
			</tr>
			<tr>
				<th>结束时间</th>
				<th><input class="easyui-datetimebox" name="end"
				           data-options="showSeconds:true"
				           value="3/4/2010 2:3" style="width:150px"></th>
			</tr>
			<tr>
				<th>标记颜色</th>
				<th>
					<select name="color">
						<option value="red">红色</option>
						<option value="blue">蓝色</option>
						<option value="gray">灰色</option>
					</select>
				</th>
			</tr>
		</table>
	</form>
</div>
<div id="mydailogbut" style="text-align:center">
	<a class="easyui-linkbutton" iconCls=icon-save' plain="true" id="formsave">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
