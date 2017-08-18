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
	<script>

		//-**************************************************************
		$(document).ready(function () {
			//国际化默认值为'en'，代表使用英文
			var initialLangCode = 'zh-cn';


					//***********提交表单
					$("#formsave").on("click", function () {
						$("#myform").form("submit", {
							url: "/calendar/save",
							success: function (data) {
								data = $.parseJSON(data);
								if (data.success) {
									// 说明是成功,info是图表
									//$.messager.alert("提示消息", data.msg, "info")
									// 关闭窗口,
									$("#mydialog").dialog("close");
									// 页面刷新
									window.location.reload();
								} else {
									// 说明是失败
									$("#mydialog").dialog("close");
									$.messager.alert("提示消息", data.msg, "info")
								}
							}
						})
					});
			//初始化FullCalendar
			$('#calendar').fullCalendar({

						//设置头部信息，如果不想显示，可以设置header为false
						header: {
							//日历头部左边：初始化切换按钮
							left: 'prev,next today',
							//日历头部中间：显示当前日期信息
							center: 'title',
							//日历头部右边：初始化视图
							right: 'month,agendaWeek,agendaDay'
						},
						//设置是否显示周六和周日，设为false则不显示
						weekends: true,
						//日历初始化时显示的日期，月视图显示该月，周视图显示该周，日视图显示该天，和当前日期没有关系
						    defaultDate: new Date(),
				events: function(start,end,timezone,callback){
					$.ajax({
						url: "/calendar/selectAll",
						cache: false,
						success: function(doc){
							console.log(doc);
							callback(doc);
							alert(doc)
						}
					});
				} ,




						//日程数据
					/*	events: function (start, end, timezone, callback) {
							console.debug(arguments);
							$.ajax({
								url: '/calendar/selectAll',
								dataType: 'xml',

								success: function (doc) {
									var events = [];
									$(doc).find('event').each(function () {
										events.push({
											title: $(this).attr('title'),
											start: $(this).attr('start') // will be parsed
										});
									});
									callback(events);
								}
							});
						},*/
						dayClick: function (date, allDay, jsEvent, view, event) {
							// 把对话框打开
							$("#mydialog").dialog("open");
							$("#mydialog").dialog("setTitle", "新增日程");
							$("#myform").form("clear");
						},
						eventClick: function (event, jsEvent, view) {
							$.messager.confirm("确认", "确定要把这个日程删除?", function (ok) {
								if (ok) {
									$.get("calendar_delete?delId=" + event.id,
											function (data) {
												if (data.success) {
													//                                            $.messager.alert("提示消息", data.msg,
													//                                                    "info");
													window.location.reload();
												}
											})
								}
							})
						}
					}
			);

			//初始化语言选择的下拉菜单值
			$.each($.fullCalendar.langs, function (langCode) {
				$('#lang-selector').append(
						$('<option/>')
								.attr('value', langCode)
								.prop('selected', langCode == initialLangCode)
								.text(langCode)
				);
			});


			//当选择一种语言时触发
			$('#lang-selector').on('change', function () {
				if (this.value) {
					$('#calendar').fullCalendar('option', 'lang', this.value);
				}
			});
		});

		//*****************************************保存
		/*   $("#formsave").on("click", function () {
		 $("#myform").form("submit", {
		 url: "/calendar/save",
		 success: function (data) {
		 data = $.parseJSON(data);
		 if (data.success) {
		 // 说明是成功,info是图表
		 //$.messager.alert("提示消息", data.msg, "info")
		 // 关闭窗口,
		 $("#mydialog").dialog("close");
		 // 页面刷新
		 window.location.reload();
		 } else {
		 // 说明是失败
		 $("#mydialog").dialog("close");
		 $.messager.alert("提示消息", data.msg, "info")
		 }
		 }
		 })
		 });*/


		//-****888888888888888888888888888888888888888888888888888888888888


		/*$(document).ready(function () {
		 var initialLangCode = 'zh-cn';

		 $("#formsave").on("click", function () {
		 $("#myform").form("submit", {
		 url: "calendar_save",
		 success: function (data) {
		 data = $.parseJSON(data);
		 if (data.success) {
		 // 说明是成功,info是图表
		 //$.messager.alert("提示消息", data.msg, "info")
		 // 关闭窗口,
		 $("#mydialog").dialog("close");
		 // 页面刷新
		 window.location.reload();
		 } else {
		 // 说明是失败
		 $("#mydialog").dialog("close");
		 $.messager.alert("提示消息", data.msg, "info")
		 }
		 }
		 })
		 });

		 $('#calendar').fullCalendar({
		 header: {
		 left: 'prev,next today',
		 center: 'title',
		 right: 'month,agendaWeek,agendaDay'
		 },
		 defaultDate: '2017-07-20',
		 lang: initialLangCode,
		 buttonIcons: false, // show the prev/next text
		 weekNumbers: true,
		 editable: true,
		 eventLimit: true, // allow "more" link when too many events
		 events: function (start, end, timezone, callback) {
		 $.ajax({
		 //url: "/calendar/selectAll",
		 cache: false,
		 success: function (doc) {
		 console.log(doc);
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
		 $.get("calendar_delete?delId=" + event.id,
		 function (data) {
		 if (data.success) {
		 //                                            $.messager.alert("提示消息", data.msg,
		 //                                                    "info");
		 window.location.reload();
		 }
		 })
		 }
		 })

		 }

		 });
		 //!**************************************************************


		 // build the language selector's options
		 $.each($.fullCalendar.langs, function (langCode) {
		 $('#lang-selector').append(
		 $('<option/>')
		 .attr('value', langCode)
		 .prop('selected', langCode == initialLangCode)
		 .text(langCode)
		 );
		 });

		 // when the selected option changes, dynamically change the calendar option
		 $('#lang-selector').on('change', function () {
		 if (this.value) {
		 $('#calendar').fullCalendar('option', 'lang', this.value);
		 }
		 });
		 });*/

	</script>
	<style>

		body {
			margin: 0;
			padding: 0;
			font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
			font-size: 14px;
		}

		#top {
			background: #eee;
			border-bottom: 1px solid #ddd;
			padding: 0 10px;
			line-height: 40px;
			font-size: 12px;
		}

		#calendar {
			max-width: 900px;
			margin: 40px auto;
			padding: 0 10px;
		}

	</style>
</head>
<body>

<%--<div id='top'>--%>

<%--Language:--%>
<%--<select id='lang-selector'></select>--%>

<%--</div>--%>

<div id='calendar'></div>
<!-- 对话框按钮 -->
<div id="mydialog">
	<form id="myform" method="post" action="">
		<input type="hidden" name="id"/>
		<table align="center" style="margin-top: 30px;">
			<tr>
				<th>标题</th>
				<th><input type="text" name="title"/></th>
			</tr>
			<tr>
				<th>是否全天</th>
				<th><select name="allDay">
					<option value="false">--请选择--</option>
					<option value=true>是</option>
					<option value=false>否</option>
				</select></th>
			</tr>
			<tr>
				<th>开始时间</th>
				<th><input class="easyui-datetimebox" name="start"
				           data-options="showSeconds:true"
				           value="3/4/2010 2:3" style="width:150px"></th>
			</tr>
			<tr>
				<th>结束时间</th>
				<th><input class="easyui-datetimebox" name="end"
				           data-options="showSeconds:true"
				           value="3/4/2010 2:3" style="width:150px"></th>
			</tr>
			<tr>
				<th>链接地址</th>
				<th><input type="text" name="url"/></th>
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


	<div id="mydailogbut" style="text-align:center">

		<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="formsave">保存</a>
	</div>

</div>
</body>
</html>
