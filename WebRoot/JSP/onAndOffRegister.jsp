<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'MyJdffsp.jsp' starting page</title>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function onSubmit() {
		document.getElementById("fom").action = "AttendanceManagementAction!AttendanceRegisterSubmitOn";
		document.getElementById("fom").submit();
	}
	function offSubmit() {
		document.getElementById("fom").action = "AttendanceManagementAction!AttendanceRegisterSubmitOff";
		document.getElementById("fom").submit();
	}
</script>
</head>
<body>
	<form name="fom" id="fom" method="post" action="">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="93%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="<%=basePath%>images/nav04.gif">

								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21"><img src="<%=basePath%>images/ico07.gif"
											width="20" height="18" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><table id="subtree1" style="DISPLAY: " width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="94%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="20"><span class="newfont07">设置：</span> 
										</td>
										<td></td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="92%" border="0" cellpadding="4" cellspacing="1"
												bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="7" align="center"
														style="font-size:16px"></td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<td width="30%">登记的时间</td>
													<td width="20%">规定时间</td>
													<td width="20%">操作</td>
													<td width="30%"></td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<td width="30%">第一次登记的时间（上班）:</td>
													<td width="20%">${request.onTime}</td>
													<td width="20%"><s:if test="#request.hasClickedOn == null"><input type="button" value="上班登记" onclick="onSubmit();"  /></s:if><s:if test="#request.lated != null">迟到</s:if></td>
													<td width="30%">在此时间后登记即为“迟到”</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<td width="30%">第二次登记的时间（下班）：</td>
													<td width="20%">${request.offTime}</td>
													<td width="20%"><s:if test="#request.hasClickedOff == null"><input type="button" value="下班登记" onclick="offSubmit();" /></s:if><s:if test="#request.zaotui != null">早退</s:if></td>
													<td width="30%">在此时间前登记即为“早退”</td>
												</tr>
											</table>
											<table><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr></table>
											<tr>
												<td>
													<input type="submit" name="Submit" value="提交"/>
												</td>
											</tr>
									<tr>
										<td height="40" class="font42">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>

	</form>
</body>
</html>
