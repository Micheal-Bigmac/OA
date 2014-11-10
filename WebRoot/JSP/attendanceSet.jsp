<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="debugFile.inc" %>
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
</head>
<body>
	<form name="fom" id="fom" method="post" action="AttendanceManagementAction!AttendanceSetSubmit">
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
													<td width="30%">第一次登记的时间（上班）：</td>
													<td width="20%">
														<s:set value="#request.requestHourOn" var="hourOn"></s:set>
														<select name="registerHourOn">
															<s:iterator value="#session.listHours" var="hour">
																<option value="${hour}" ${hourOn == hour ? "selected":""}>${hour}</option>
															</s:iterator>
														</select>
													时</td>
													<td width="20%">
													<s:set value="#request.requestMinuteOn" var="minuteOn"></s:set>
													<select name="registerMinuteOn">
														<s:iterator value="#session.listMinutes" var="minute">
															<option value="${minute}" ${minuteOn == minute ? "selected":""}>${minute}</option>															
														</s:iterator>
														</select>
													分</td>
													<td width="30%">
															 在此时间后登记即为“迟到”
													</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<td width="30%">第二次登记的时间（下班）：</td>
													<td width="20%">
													<s:set value="#request.requestHourOff" var="hourOff"></s:set>
													<select name="registerHourOff">
														<s:iterator value="#session.listHours" var="hour">
															<option value="${hour}" ${hourOff == hour ? "selected":"" }>${hour}</option>
														</s:iterator>
													</select>
													时</td>
													<td width="20%">
													<s:set value="#request.requestMinuteOff" var="minuteOff"></s:set>
													<select name="registerMinuteOff">
														<s:iterator value="#session.listMinutes" var="minute">
															<option value="${minute}" ${minuteOff == minute ? "selected":"" }>${minute}</option>															
														</s:iterator>
														</select>
													分</td>
													<td width="30%">
															 在此时间后登记即为“早退”
													</td>
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
