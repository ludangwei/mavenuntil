<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		<form id="myForm" action="login.action" method="post">
			姓名: <input type="text" name="name" /> </br>
			性别: <input type="radio" name="sex" value="1">男<input type="radio" name="sex" value="0">女</br>
			邮箱：<input type="text" name="email"></br></br>
			<input type="submit" value="提交" /> 
		</form>
	</body>
<script type="text/javascript">
	$(function() {
		var options = {
			beforeSubmit : showRequest, //提交前回调
			success : showResponse,     //提交成功后回调
			clearForm: true    //提交成功后清除所有表单数据 
			//url: url         //覆盖form中action属性值 
			//type: type       //'get' 或  'post', 覆盖form中method属性值 
			//dataType: null   //'xml', 'script', or 'json'
		};
		$('#myForm').submit(function() {
			$(this).ajaxSubmit(options);
			return false;
		});
	})

	function showRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		console.info('所提交数据为: ' + queryString);
		return true;
	}
	function showResponse(responseText, statusText)  {
		var s = eval("("+responseText+")");
		console.info(s);  //从后台返回的数据 
		console.info(statusText);
	}
</script>
</html>
