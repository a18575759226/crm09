<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<%@include file="/WEB-INF/views/common.jsp"%>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north'"
			style="height: 80px; background: #FAEBD7;">
			<h1 style="font-family: verdana; color: #00BFFF" align="left">
				\(^o^)/~欢迎来到QQ空间^(*￣(oo)￣)^^(*￣(oo)￣)^~~~~~~~</h1>
		</div>
		 <div align="center" data-options="region:'west',title:'今日头条'" style="width:200px;background: #FFC0CB;">
	    	<div style="height: 20px; background: #AFEEEE" align="left">
	    		<a href="/main" style="text-decoration: none;">返回主页面</a>
	    	</div>
	    	<div style="height: 20px; background: #E0FFFF" align="left">
				<a href="http://pao.qq.com/cp/a20170627summer/index.html">天天酷跑</a>
			</div>
			<div style="height: 20px; background: #FFF8DC" align="left">天天消除</div>
			<div style="height: 20px; background: #7FFFD4" align="left">欢乐斗地主</div>
			<div style="height: 20px; background: #FAEBD7" align="left">象棋</div>
			<div style="height: 20px; background: #E0FFFF" align="left">天天打飞机</div>
			<div style="height: 20px; background: #FFF8DC" align="left">全民英雄</div>
			<div style="height: 20px; background: #7FFFD4" align="left">王者荣耀</div>
			<div style="height: 20px; background: #FAEBD7" align="left">忍者神龟</div>
	    </div> 
		<div data-options="region:'center'" style="background: #E6E6FA;">
			<h1 align="center" style="color: #ADD8E6">下面是您的个人信息</h1>
			<div id="user_tabs" class="easyui-tabs"  align="left">
				<table id="user_datagrid" align="center">
					<thead>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">用户名:</td>
							<td align="left" >
								<shiro:principal property="realname"></shiro:principal>
							</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">年龄:</td>
							<td align="left">21</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">性别:</td>
							<td align="left">女</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">电话:</td>
							<td align="left">
								<shiro:principal property="tel"></shiro:principal>
							</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">邮箱:</td>
							<td align="left"><shiro:principal property="email"></shiro:principal></td>
						</tr>
						<tr align="right"
						>
							<td data-options="field:'course',width:1,align:'center'">地址:</td>
							<td align="left">广州天河</td>
						</tr>
						<tr >
							<td align="right" data-options="field:'course',width:1,align:'center'">生日:</td>
							<td align="left">1995-01-05</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">家乡:</td>
							<td align="left">湖南长沙</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">星座:</td>
							<td align="left">双鱼</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">血型:</td>
							<td align="left">O型</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">爱好:</td>
							<td align="left">学java</td>
						</tr>
						<tr align="right">
							<td data-options="field:'course',width:1,align:'center'">职业:</td>
							<td align="left">演员</td>
						</tr>
					</thead>
				</table>
			</div>
			<div style="height: 30px; background: #00FFFF"></div>
			<div style="height: 30px; background: #FFF8DC"></div>
			<div style="height: 30px; background: #7FFFD4"></div>
			<div style="height: 30px; background: #FAEBD7"></div>
			<div style="height: 30px; background: #AFEEEE" align="right"></div>
			<div style="height: 30px; background: #ADFF2F"></div>
		</div>
		<div data-options="region:'south'" align="center"
			style="height: 20px; background: #F0FFFF;">
			版权所有,侵权必究
		</div>
</body>
</html>
