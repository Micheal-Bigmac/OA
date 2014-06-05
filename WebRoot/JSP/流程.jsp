<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>项目管理系统 by www.865171.cn</title>
<link rel="stylesheet" rev="stylesheet" href="../css/style.css" type="text/css" media="all" />


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
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="" method="post" enctype="multipart/form-data" name="form" target="sypost" >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >制定功能计划</th>
  </tr>
  <tr>
    <td class="CPanel">
      <table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr align="center"><td class="TablePanel" height="30" style="font-size:16px">XXX项目</td>
		</tr>
		<tr align="center"><td><a href="#">+添加模块+</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#">-删除模块-</a></td></tr>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>模块一</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">模块名字:</td>
					    <td width="46%"><input name="text" class="text" style="width:300px" type="text" size="40" />
				        <span class="red"> *</span></td>
					    <td align="right" width="9%"></td>
					    <td width="32%">&nbsp;</td>
					    </tr>
					  <tr>
					    <td nowrap align="right">模块标识:</td>
					    <td><input name="" id="" class="text" style="width:154px" /></td>
					    <td align="right">所属项目:</td>
					    <td><select name="select7" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					   <tr>
					    <td nowrap align="right">所属需求:</td>
					    <td><select name="select2" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					    <td align="right">优先级:</td>
					    <td><select name="select" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					  <tr>
					    <td nowrap align="right" height="120px">模块描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
						<tr>
					    <td nowrap align="right" height="120px">需求描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>模块一</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">模块名字:</td>
					    <td width="46%"><input name="text" class="text" style="width:300px" type="text" size="40" />
				        <span class="red"> *</span></td>
					    <td align="right" width="9%"></td>
					    <td width="32%">&nbsp;</td>
					    </tr>
					  <tr>
					    <td nowrap align="right">模块标识:</td>
					    <td><input name="" id="" class="text" style="width:154px" /></td>
					    <td align="right">所属项目:</td>
					    <td><select name="select7" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					   <tr>
					    <td nowrap align="right">所属需求:</td>
					    <td><select name="select2" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					    <td align="right">优先级:</td>
					    <td><select name="select" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					  <tr align="top">
					    <td nowrap align="right" height="120px">模块描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
						<tr align="top">
					    <td nowrap align="right" height="120px">需求描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
		
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>模块一</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">模块名字:</td>
					    <td width="46%"><input name="text" class="text" style="width:300px" type="text" size="40" />
				        <span class="red"> *</span></td>
					    <td align="right" width="9%"></td>
					    <td width="32%">&nbsp;</td>
					    </tr>
					  <tr>
					    <td nowrap align="right">模块标识:</td>
					    <td><input name="" id="" class="text" style="width:154px" /></td>
					    <td align="right">所属项目:</td>
					    <td><select name="select7" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					   <tr>
					    <td nowrap align="right">所属需求:</td>
					    <td><select name="select2" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					    <td align="right">优先级:</td>
					    <td><select name="select" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					  <tr align="top">
					    <td nowrap align="right" height="120px">模块描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
						<tr align="top">
					    <td nowrap align="right" height="120px">需求描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
		
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>模块一</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">模块名字:</td>
					    <td width="46%"><input name="text" class="text" style="width:300px" type="text" size="40" />
				        <span class="red"> *</span></td>
					    <td align="right" width="9%"></td>
					    <td width="32%">&nbsp;</td>
					    </tr>
					  <tr>
					    <td nowrap align="right">模块标识:</td>
					    <td><input name="" id="" class="text" style="width:154px" /></td>
					    <td align="right">所属项目:</td>
					    <td><select name="select7" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					   <tr>
					    <td nowrap align="right">所属需求:</td>
					    <td><select name="select2" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					    <td align="right">优先级:</td>
					    <td><select name="select" >
                          <option  selected="selected">==请选择==</option>
                          <option>暂不</option>
                          <option>一般</option>
                          <option>需要</option>
                          <option>急</option>
                          <option>很急</option>
                        </select></td>
					  </tr>
					  <tr align="top">
					    <td nowrap align="right" height="120px">模块描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
						<tr align="top">
					    <td nowrap align="right" height="120px">需求描述:</td>
					    <td colspan="3">
							<textarea id="" name="" rows="5" cols="80"></textarea>
						</td>
					    </tr>
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
</TABLE>
</td>
</tr>
<TR>
<TD colspan="2" align="center" height="50px">
<input type="button" name="Submit" value="保存" class="button" onclick="alert('保存成功！');"/>
<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/></TD>
</TR>
</TABLE>
 </td>
</tr>
</table>

</div>
</form>
</body>
</html>
