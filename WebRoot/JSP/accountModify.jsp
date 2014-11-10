<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body class="ContentBody">
	<form action="UserAction!accountModify" method="post" name="form"
			target="mainFrame">
			<div class="MainDiv">
				<table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							员工基本信息
						</th>
					</tr>
					<TR>
						<TD width="100%">
							<fieldset style="height: 100%;">
								<legend>
									用户登录信息
								</legend>
								<table border="0" cellpadding="2" cellspacing="1"
									style="width: 100%">
									<tr>
										<td nowrap align="right" width="15%">
											用户账号:
										</td>
										<td width="35%">
											<input type="hidden" name="user.id" value="${admin.id }"/>
											<input name='user.account' type="text" class="text"
												style="width: 154px" value="${admin.account}" readonly/>
											<span class="red">*</span>
										</td>
										<td width="16%" align="right" nowrap="nowrap">
											用户密码:
										</td>
										<td width="34%">
											<input name='user.password' type="password" style="width: 154px"
												value="${admin.password}">
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="right">
											创建时间:
										</td>
										<td>
											<input class="text" name='user.createTime' style="width: 154px"
												value="${admin.createTime}" readonly/>
										</td>
										<td align="right">
											账号终止时间:
										</td>
										<td>
											<input class="text" name='user.expireTime' style="width: 154px"
												value="${admin.expireTime}" readonly/>
										</td>
									</tr>
								</table>
								<br />
							</fieldset>
						</TD>
					</TR>
					<TR>
						<TD colspan="2" align="center" height="50px">
							<input type="submit" name="Submit" value="保存" class="button"
								onclick="" />

							<input type="button" name="Submit2" value="返回" class="button"
								onclick="window.history.go(-1);" />
						</TD>
					</TR>
				</TABLE>
				</td>
				</tr>
				</table>
			</div>
		</form>
	</body>
</html>