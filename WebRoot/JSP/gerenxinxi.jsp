<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>项目管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
<!-- <script src="js/Calendar.js">  </script> -->
<!-- <script type="text/javascript"> -->
<!-- /*调取日历*/ -->
<!--         var c = new Calendar("c"); -->
<!--         document.write(c); -->
<!--     </script> -->

<script language="JavaScript" type="text/javascript">
		
function tishi()
{
  var a=confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
  if(a!=true)return false;
  window.open("冲突页.htm","","depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}

function check()
{
document.getElementById("aa").style.display="";
}

</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>

<s:debug></s:debug>

<body class="ContentBody">
	<form action="PersonAction!addUser" method="post" name="form" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				<tr>
					<th class="tablestyle_title">员工基本信息</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0" width="984" height="258">
							<tr>
								<td align="left"><input type="button" name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr>
							<TR>
								<TD width="100%">
									<fieldset style="height: 100%;">
										<legend> 员工信息 </legend>
										<table border="0" cellpadding="2" cellspacing="1" style="width: 100%">
											<tr>
												<td nowrap align="right" width="15%">员工编号:</td>

												<td width="35%"><input name='person.id' type="text" class="text"
													style="width: 154px" value="${admin.personid.id }" readonly /> <span class="red">*</span>
												</td>
											</tr>


											<tr>
												<td nowrap="nowrap" align="right">真实姓名:</td>
												<td><input class="text" name='person.name' style="width: 154px"
													value="${admin.personid.name }" /></td>
												<td align="right">性别:</td>
												<td><select name="person.sex">
														<option <s:if test="#session.admin.personid.sex=='男'">selected="selected"</s:if>>
															男</option>
														<option <s:if test="#session.admin.personid.sex=='女'">selected="selected"</s:if>>
															女</option>
												</select></td>
											</tr>
											<tr>
												<td align="right">出生日期:</td>
												<td><input class="text" name='person.birthday' value="${admin.personid.birthday }"
													style="width: 154px" onfocus="c.showMoreDay = false;c.show(this);" /></td>
												<td align="right">电子邮箱:</td>
												<td><input class="text" name='person.email' style="width: 154px"
													value="${admin.personid.email }" /></td>
											</tr>
											<tr>
												<td align="right">电话号码:</td>
												<td><input class="text" name='person.phone' style="width: 154px"
													value="${admin.personid.phone }" /></td>

												<td align="right">入职时间:</td>
												<td><input class="text" name='person.hiredate' value="${admin.personid.hiredate }"
													style="width: 154px" value="" onfocus="c.showMoreDay = false;c.show(this);" /></td>
											</tr>
											<tr>
												<td align="right">居住地址:</td>
												<td><input name='person.address' type="text" size="30"
													value="${ admin.personid.address}" /></td>
												<td align="right">职位:</td>
												<td><select name='person.type'>

														<option <s:if test="#session.admin.personid.type=='程序员'">selected="selected"</s:if>>
															程序员</option>
														<option <s:if test="#session.admin.personid.type=='项目经理'">selected="selected"</s:if>>
															项目经理</option>
														<option <s:if test="#session.admin.personid.type=='经理'">selected="selected"</s:if>>
															经理</option>
												</select></td>
											</tr>
											<tr>
												<td align="right">员工类型:</td>
												<td><select name="person.position">
														<option <s:if test="#session.admin.personid.position=='实习'">selected="selected"</s:if>>
															实习</option>
														<option <s:if test="#session.admin.personid.position=='试用'">selected="selected"</s:if>>
															试用</option>
														<option
															<s:if test="#session.admin.personid.position=='正式员工'">selected="selected"</s:if>>
															正式员工</option>
												</select></td>
											</tr>

										</table>
										<br />
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
				<TR>
					<TD width="100%">
						<fieldset style="height: 100%;">
							<legend> 用户登录信息 </legend>
							<table border="0" cellpadding="2" cellspacing="1" style="width: 100%">

								<tr>
									<td nowrap align="right" width="15%">用户账号:</td>
									<td width="35%"><input name='user.account' type="text" class="text"
										style="width: 154px" value="${admin.account}" /> <span class="red">*</span></td>
									<td width="16%" align="right" nowrap="nowrap">用户密码:</td>
									<td width="34%"><input name='user.password' type="password" style="width: 154px"
										value="${admin.password}"></td>
								</tr>
								<tr>
									<td nowrap="nowrap" align="right">创建时间:</td>
									<td><input class="text" name='user.createTime' style="width: 154px"
										value="${admin.createTime}" /></td>
									<td align="right">账号终止时间:</td>
									<td><input class="text" name='user.expireTime' style="width: 154px"
										value="${admin.expireTime}" /></td>
								</tr>
							</table>
							<br />
						</fieldset>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" align="center" height="50px"><input type="submit" name="Submit" value="保存"
						class="button" onclick="" /> <input type="button" name="Submit2" value="返回" class="button"
						onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
	</form>
</body>
</html>
