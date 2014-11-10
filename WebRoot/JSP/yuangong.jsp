<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
<form class="form-horizontal" id="modal_form" action="PersonAction!addUser?method=1">
	<div class="control-group">
		<label class="control-label" for="inputEmail"></label>
		<div class="controls">
			<input name='person.id' type="hidden" value="${person.id }" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">真实姓名:</label>
		<div class="controls">
			<input type="text" name='person.name' value="${person.name }" check-type="required" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">性别:</label>
		<div class="controls">
			<select name="person.sex" check-type="required">
				<option ${person.sex=='女'? 'selected':''}>女</option>
				<option ${person.sex=='男'? 'selected':''}>男</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">出生日期:</label>
		<div class="controls">
			<input class="datapicker" type="text" name='person.birthday' value="${person.birthday }" readOnly check-type="required" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">电子邮箱:</label>
		<div class="controls">
			<input type="text" name='person.email' value="${person.email }" check-type="email" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">电话号码:</label>
		<div class="controls">
			<input type="text" name='person.phone' value="${person.phone }" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">入职时间:</label>
		<div class="controls">
			<input class="datapicker" type="text" name='person.hiredate' value="${person.hiredate }" readOnly />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">居住地址:</label>
		<div class="controls">
			<input name='person.address' type="text" value="${ person.address}" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">职位:</label>
		<div class="controls">
			<select name="position" check-type="required">
				<s:iterator var="personposition" value="#session.listPP" status="status">
					<option value="${status.index}">${status.index}|${personposition.name}</option>
				</s:iterator>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">员工类型:</label>
		<div class="controls">
			<select name='type' check-type="required">
				<s:iterator var="persontype" value="#session.listPT" status="status">
					<option value="${status.index}">${status.index}|${persontype.name}</option>
				</s:iterator>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">员工部门:</label>
		<div class="controls">
			<select name="org" check-type="required">
				<s:iterator var="personOrg" value="#session.listOrg" status="status">
					<option value="${status.index}">${status.index}|${personOrg.name}</option>
				</s:iterator>
			</select>
		</div>
	</div>
	<!-- <div class="form-actions">
				<button type="submit" class="btn btn-primary">保存</button>
				<button type="button" class="btn">Cancel</button>
			</div> -->
</form>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/myAjaxify.js"></script>
<script type="text/javascript">
	$(function() {
		$("#modal_form").validation();
	})
</script>