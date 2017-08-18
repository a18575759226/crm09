<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>小码哥客户关系管理系统</title>
	<link rel="stylesheet" href="/static/css/style.css">
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/static/js/canvas-particle.js"></script>
	<script type="text/javascript">
		$(function () {
			$("#login_btn").click(function () {
				$("#login_form").form("submit", {
					success: function (data) {
						data = $.parseJSON(data);
						if (data.success) {
							window.location.href = "/main";
						} else {
							$.messager.alert('温馨提示', data.msg, 'warning');
						}

					}
				})
			});
		});
		window.onload = function() {
			//配置
			var config = {
				vx: 4,	//小球x轴速度,正为右，负为左
				vy: 4,	//小球y轴速度
				height: 2,	//小球高宽，其实为正方形，所以不宜太大
				width: 2,
				count: 200,		//点个数
				color: "121, 162, 185", 	//点颜色
				stroke: "130,255,255", 		//线条颜色
				dist: 6000, 	//点吸附距离
				e_dist: 20000, 	//鼠标吸附加速距离
				max_conn: 10 	//点到点最大连接数
			}

			//调用
			CanvasParticle(config);
		}
	</script>
</head>
<body>
<shiro:authenticated>
	<script type="text/javascript">
		window.location.href = "/main";
	</script>
</shiro:authenticated>
<section class="container">
	<div class="login">
		<h1>用户登录</h1>
		<form method="post" id="login_form">
			<p><input type="text" name="username" value="" placeholder="账号"></p>
			<p><input type="password" name="password" value="" placeholder="密码"></p>
			<p class="submit">
				<input type="button" value="登录" id="login_btn">
				<input type="button" value="重置">
			</p>
		</form>
	</div>
</section>
<div style="text-align:center;" class="login-help">
	<p>Copyright ©crm客户关系管理</p>
</div>
<div id="mydiv" style="height:500px;"></div>
</body>
</html>