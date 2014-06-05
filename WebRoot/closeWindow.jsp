<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJdffsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <body class="ContentBody">
	<span id="time">2</span>秒后回到主题，请等待
	
<script language="JavaScript" type="text/javascript">
	function delayURL(url) {
	alert('ol');
		var delay = document.getElementById("time").innerHTML;
		if (delay > 0) {
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else {
			window.location.href = url;
		}
		setTimeout("delayURL('" + url + "')", 1000);
	}
</script>

  </body>
</html>