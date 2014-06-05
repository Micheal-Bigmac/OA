<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.oa.model.Users"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/jsp; charset=UTF-8" />
<title>项目管理系统 by www.865171.cn</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/left.gif);
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT language=JavaScript>
	function tupian(idt) {
		var nametu = "xiaotu" + idt;
		var tp = document.getElementById(nametu);
		tp.src = "images/ico05.gif";//图片ico04为白色的正方形

		for ( var i = 1; i < 30; i++) {

			var nametu2 = "xiaotu" + i;
			if (i != idt * 1) {
				var tp2 = document.getElementById('xiaotu' + i);
				if (tp2 != undefined) {
					tp2.src = "images/ico06.gif";
				}//图片ico06为蓝色的正方形
			}
		}
	}

	function list(idstr) {
		var name1 = "subtree" + idstr;
		var name2 = "img" + idstr;
		var objectobj = document.all(name1);
		var imgobj = document.all(name2);

		try {
			if (objectobj.style.display == "none") {
				for (i = 1; i < 10; i++) {
					var name3 = "img" + i;
					var name = "subtree" + i;
					var o = document.all(name);
					if (o != undefined) {
						o.style.display = "none";
						var image = document.all(name3);
						//alert(image);
						image.src = "images/ico04.gif";
					}
				}
				objectobj.style.display = "";
				imgobj.src = "images/ico03.gif";
			} else {
				objectobj.style.display = "none";
				imgobj.src = "images/ico04.gif";
			}
		} catch (err) {
			return;
		}
	}
</SCRIPT>
<body>
	<table width="198" border="0" cellpadding="0" cellspacing="0"
		class="left-table01">
		<tr>
			<TD>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="207" height="55" background="images/nav01.gif">
							<table width="90%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="25%" rowspan="2"><img src="images/ico02.gif"
										width="35" height="35" />
									</td>
									<td width="75%" height="22" class="left-font01">
										您好，${admin.personid.name } <span class="left-font02"></span>
									</td>
								</tr>
								<tr>
									<td height="22" class="left-font01">[&nbsp; <a
										href="login.jsp" target="_top" class="left-font01">退出</a>&nbsp;]
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table> <s:iterator var="module" value="#session.modules" status="num">
					<s:set name="te" value="#num.index+1" />

					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%"><img name="img${num.index+1 }" id="img8"
											src="images/ico04.gif" width="8" height="11" />
										</td>
										<td width="92%"><a href="javascript:" target="mainFrame" class="left-font03"
											onClick="list('${te}');">${module.key.name}</a>
										</td>
									</tr>
								</table></td>
						</tr>
					</TABLE>
					<s:if test=" #module.value.size">
						<table id="subtree${te}" style="DISPLAY: none" width="80%"
							border="0" align="center" cellpadding="0" cellspacing="0"
							class="left-table02">
							<s:iterator var="children" value="#module.value">
								<tr>
									<td width="9%" height="20"><img id="xiaotu20"
										src="images/ico06.gif" width="8" height="12" />
									</td>
									<td width="91%"><a href="${children.url }" target="mainFrame"
										class="left-font03" onClick="tupian('20');">${children.name
											}</a>
									</td>
								</tr>
							</s:iterator>
						</table>
						<!--  任务系统结束    -->

					</s:if>
				</s:iterator>
			</TD>
		</tr>

	</table>
</body>
</html>