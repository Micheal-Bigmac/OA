<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta http-equiv=”content-type” content=”text/html; charset=UTF-8″ />
<link href="css/bootstrap-fileupload.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
<link href="css/chosen.css" rel="stylesheet" type="text/css" />
<link href="css/profile.css" rel="stylesheet" type="text/css" />
<base href="<%=basePath%>">
<div class="row-fluid">
	<div class="span12">
		<!-- END BEGIN STYLE CUSTOMIZER -->
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">用户预置文件</h3>
		<ul class="breadcrumb">
			<li><i class="icon-home"></i> <a href="index.html">主页</a> <i class="icon-angle-right"></i></li>
			<li><a href="javascript:;">个人管理</a> <i class="icon-angle-right"></i></li>
			<li><a href="javascript:;">用户预置文件</a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<s:debug></s:debug>
<div class="row-fluid profile">
	<div class="span12">
		<!--BEGIN TABS-->
		<div class="tabbable tabbable-custom tabbable-full-width">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1_1" data-toggle="tab">预览</a></li>
				<li><a href="#tab_1_2" data-toggle="tab">个人信息</a></li>
				<li><a href="#tab_1_3" data-toggle="tab">账号</a></li>
				<li><a href="#tab_1_4" data-toggle="tab">项目</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane row-fluid active" id="tab_1_1">
					<ul class="unstyled profile-nav span3">
						<li><img style="width:282px;height:206px" src="${admin.personid.photoPath == null ? 'image/avatar_small_1.jpg' : admin.personid.photoPath }" alt="" /> <a href="javascript:;"
							class="profile-edit">edit</a></li>
						<li><a href="javascript:;">计划</a></li>
						<li><a href="javascript:;">客户留言<span>*</span> </a></li>
						<li><a href="javascript:;">好友</a></li>
						<li><a href="javascript:;">设置</a></li>
					</ul>
					<div class="span9">
						<div class="row-fluid">
							<div class="span8 profile-info">
								<h1>${admin.personid.name }</h1>
								<p>爱岗敬业</p>
								<p>
									<a href="javascript:;">${admin.personid.email }</a>
								</p>
								<ul class="unstyled inline">
									<li><i class="icon-map-marker"></i> ${admin.personid.address }</li>
									<li><i class="icon-calendar"></i> ${admin.personid.birthday }</li>
									<li><i class="icon-briefcase"></i> ${admin.personid.personPosition.name }</li>
									<li><i class="icon-star"></i> ${admin.personid.organization.name }</li>
									<li><i class="icon-heart"></i>${admin.personid.personType.name }</li>
								</ul>
							</div>
							<!--end span8-->
							<div class="span4">
								<div class="portlet sale-summary">
									<div class="portlet-title">
										<div class="caption">销售汇总</div>
										<div class="tools">
											<a class="reload" href="javascript:;"></a>
										</div>
									</div>
									<ul class="unstyled">
										<li><span class="sale-info">天销售 <i class="icon-img-up"></i> </span> <span class="sale-num">23</span>
										</li>
										<li><span class="sale-info">周销售<i class="icon-img-down"></i> </span> <span class="sale-num">87</span>
										</li>
										<li><span class="sale-info">总销售</span> <span class="sale-num">2377</span>
										</li>
										<li><span class="sale-info">薪水</span> <span class="sale-num">$37.990</span>
										</li>
									</ul>
								</div>
							</div>
							<!--end span4-->
						</div>
						<!--end row-fluid-->
					</div>
					<!--end span9-->
				</div>
				<!--end tab-pane-->
				<div class="tab-pane profile-classic row-fluid" id="tab_1_2">
					<div class="span2">
						<img src="${admin.personid.photoPath == null ? 'image/avatar_small_1.jpg' : admin.personid.photoPath }" alt="" /> <a href="javascript:;" class="profile-edit">edit</a>
					</div>
					<ul class="unstyled span10">
						<li><span>真实姓名:</span> ${admin.personid.name }</li>
						<li><span>性别:</span> ${admin.personid.sex }</li>
						<li><span>出生日期:</span> ${admin.personid.birthday }</li>
						<li><span>电子邮箱:</span> ${admin.personid.email }</li>
						<li><span>电话号码:</span> ${admin.personid.phone }</li>
						<li><span>入职时间:</span>${admin.personid.hiredate }</li>
						<!-- 						<li><span>居住地址:</span> <a href="javascript:;">john@mywebsite.com</a></li> -->
						<li><span>居住地址:</span>${ admin.personid.address}</li>
						<li><span>职位:</span> ${admin.personid.personPosition.name }</li>
						<li><span>员工类型:</span> ${admin.personid.personType.name}</li>
						<li><span>关于:</span> 我是一个好员工</li>
					</ul>
				</div>
				<!--tab_1_2-->
				<div class="tab-pane row-fluid profile-account" id="tab_1_3">
					<div class="row-fluid">
						<div class="span12">
							<div class="span3">
								<ul class="ver-inline-menu tabbable margin-bottom-10">
									<li class="active"><a data-toggle="tab" href="#tab_1-1"> <i class="icon-cog"></i>个人信息 </a> <span class="after"></span>
									</li>
									<li class=""><a data-toggle="tab" href="#tab_2-2"><i class="icon-picture"></i> 更改头像</a></li>
									<li class=""><a data-toggle="tab" href="#tab_3-3"><i class="icon-lock"></i> 修改口令</a></li>
								</ul>
							</div>
							<div class="span9">
								<div class="tab-content">
									<div id="tab_1-1" class="tab-pane active">
										<div style="height: auto;" id="accordion1-1" class="accordion collapse">
											<form id="form" action="PersonAction!addUser">
												<input name='person.id' type="hidden" value="${admin.personid.id }" />
												<s:set var="org" value="#session.listOrg.{id}.indexOf(#session.admin.personid.organization.id)" />
												<s:set var="type" value="#session.listPT.{id}.indexOf(#session.admin.personid.personType.id)" />
												<s:set var="position" value="#session.listPP.{id}.indexOf(#session.admin.personid.personPosition.id)" />
												<%-- <input type="hidden" name="org" valule="${org }"/>
												<input type="hidden" name="type" valule="${type }"/>
												<input type="hidden" name="position" valule="${position }"/> --%>
												<label class="control-label">真实姓名:</label> <input type="text" name="person.name" placeholder="" class="m-wrap span8" value="${admin.personid.name }" /> <label class="control-label">性别:</label>
												<%-- <input type="text" name="person.sex" value="${admin.personid.sex }" placeholder="Doe" class="m-wrap span8" /> --%>
												<select name="person.sex" class="m-wrap span8">
													<option ${admin.personid.sex=='女'? 'selected':''}>女</option>
													<option ${admin.personid.sex=='男'? 'selected':''}>男</option>
												</select> <label class="control-label">出生日期:</label> <input type="text" name='person.birthday' value="${admin.personid.birthday }" placeholder="" class="m-wrap span8 datapicker" readOnly /> <label
													class="control-label">电子邮箱:</label> <input type="text" name="person.email" value="${admin.personid.email }" placeholder="" class="m-wrap span8" /> <label class="control-label">电话号码:</label>
												<input type="text" name="person.phone" value="${admin.personid.phone }" placeholder="" class="m-wrap span8" /> <label class="control-label">居住地址:</label> <input type="text"
													name="person.address" value="${ admin.personid.address}" placeholder="" class="m-wrap span8" />
												<div class="submit-btn">
													<button id="submit" type="button" class="btn green">提交</button>
													<button type="reset" class="btn">取消</button>
												</div>
											</form>
										</div>
									</div>
									<div id="tab_2-2" class="tab-pane">
										<div style="height: auto;" id="accordion2-2" class="accordion collapse">
											<form id="from" action="UserAction!accountModify" enctype="multipart/form-data">
												<p>修改用户头像UserAction!accountModify</p>
												<br />
												<div class="controls">
													<!-- <div class="thumbnail" style="width: 291px; height: 170px;">
														<img id="pic" src="" alt="" />
													</div> -->
												</div>
												<div class="space10"></div>
												<input type="hidden" name="user.id" value="${admin.id }" />
												<div class="fileupload fileupload-new" data-provides="fileupload">
													<div class="input-append">
														<div class="uneditable-input">
															<i class="icon-file fileupload-exists"></i> <span class="fileupload-preview"></span>
														</div>
														<span class="btn btn-file"> <span class="fileupload-new">选择文件</span> <span class="fileupload-exists"> 改变</span> <input type="file" id="uploadFile" name="uploadFile"
															class="default" /> </span> <a href="" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
													</div>
												</div>
												<div class="clearfix"></div>
												<div class="space10"></div>
												<div class="submit-btn">
													<button type="button" name="uploadFile" id="tijiao" class="btn green">submit</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
										</div>
									</div>
									<div id="tab_3-3" class="tab-pane">
										<div style="height: auto;" id="accordion3-3" class="accordion collapse">
											<form id="ChangePasswd" action="UserAction!modifyPassword" method="post">
												<input type="hidden" name="user.id" value="${admin.id }"/>
												<label class="control-label">当前密码</label>
													<input name="currentPw" type="password" class="m-wrap span8" />
												 	<label class="control-label">新密码</label> 
												 	<input type="password" name="newPd"	class="m-wrap span8" />
													<label class="control-label">再输入新的密码</label>
													<input type="password" name="rePd" class="m-wrap span8" />
												<div class="submit-btn">
													<button input type="button" id="changePd" class="btn green">修改口令</button>
													 <button input type="button" class="btn">取消</button>
												</div>
											</form>
										</div>
									</div>
									
								</div>
							</div>
							<!--end span9-->
						</div>
					</div>
				</div>
				<!--end tab-pane-->
				<div class="tab-pane" id="tab_1_4">
					<div class="row-fluid add-portfolio">
						<div class="pull-left">
							<span>502 Items sold this week</span>
						</div>
						<div class="pull-left">
							<a href="javascript:;" class="btn icn-only green">Add a new Project <i class="m-icon-swapright m-icon-white"></i> </a>
						</div>
					</div>
					<!--end add-portfolio-->
					<div class="row-fluid portfolio-block">
						<div class="span5 portfolio-text">
							<img src="image/logo_metronic.jpg" alt="" />
							<div class="portfolio-text-info">
								<h4>Metronic - Responsive Template</h4>
								<p>Lorem ipsum dolor sit consectetuer adipiscing elit.</p>
							</div>
						</div>
						<div class="span5" style="overflow:hidden;">
							<div class="portfolio-info">
								Today Sold <span>187</span>
							</div>
							<div class="portfolio-info">
								Total Sold <span>1789</span>
							</div>
							<div class="portfolio-info">
								Earns <span>$37.240</span>
							</div>
						</div>
						<div class="span2 portfolio-btn">
							<a href="javascript:;" class="btn bigicn-only"><span>Manage</span> </a>
						</div>
					</div>
					<!--end row-fluid-->
					<div class="row-fluid portfolio-block">
						<div class="span5 portfolio-text">
							<img src="image/logo_azteca.jpg" alt="" />
							<div class="portfolio-text-info">
								<h4>Metronic - Responsive Template</h4>
								<p>Lorem ipsum dolor sit consectetuer adipiscing elit.</p>
							</div>
						</div>
						<div class="span5">
							<div class="portfolio-info">
								Today Sold <span>24</span>
							</div>
							<div class="portfolio-info">
								Total Sold <span>660</span>
							</div>
							<div class="portfolio-info">
								Earns <span>$7.060</span>
							</div>
						</div>
						<div class="span2 portfolio-btn">
							<a href="javascript:;" class="btn bigicn-only"><span>Manage</span> </a>
						</div>
					</div>
					<!--end row-fluid-->
					<div class="row-fluid portfolio-block">
						<div class="span5 portfolio-text">
							<img src="image/logo_conquer.jpg" alt="" />
							<div class="portfolio-text-info">
								<h4>Metronic - Responsive Template</h4>
								<p>Lorem ipsum dolor sit consectetuer adipiscing elit.</p>
							</div>
						</div>
						<div class="span5" style="overflow:hidden;">
							<div class="portfolio-info">
								Today Sold <span>24</span>
							</div>
							<div class="portfolio-info">
								Total Sold <span>975</span>
							</div>
							<div class="portfolio-info">
								Earns <span>$21.700</span>
							</div>
						</div>
						<div class="span2 portfolio-btn">
							<a href="javascript:;" class="btn bigicn-only"><span>Manage</span> </a>
						</div>
					</div>
					<!--end row-fluid-->
				</div>
				<!--end tab-pane-->
				<!--end tab-pane-->
			</div>
		</div>
		<!--END TABS-->
	</div>
</div>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/bootstrap-fileupload.js"></script>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script>
$.ajaxSetup({
  contentType: "application/x-www-form-urlencoded; charset=utf-8"
});
	$('#changePd').click(function(e){
		console.log($('#ChangePasswd').serialize());
		console.log($('#ChangePasswd').attr('action'));
		$.post($('#ChangePasswd').attr('action'),$('#ChangePasswd').serialize(),function(data){
				$('#ChangePasswd')[0].reset();
				if(data==0){
					alert("当前密码不一致");
				}else if(data==1){
					alert("重置密码不一致");
				}else {
					alert("设置成功");
				}
		});
	});
	$("#submit").click(function(e) {
		$.post($('#form').attr('action')+"?"+$('#form').serialize()+"&org="+${org}+"&type="+${type}+"&position="+${position},null,function(){
				$('#form')[o].reset();
				alert("修改成功");
		});
});
$('.datapicker').datepicker({
	autoclose : true,
	format: "yyyy-mm-dd",
	todayHighlight: true,
	todayBtn: true,
	language: "zh-CN"
});
$("#tijiao").click(function(e) {
	console.log("asdfasd");
	
	$.ajaxFileUpload({
		url:$('#from').attr('action')+"?"+$('#from').serialize(),
		secureuri:false,
		fileElementId:'uploadFile',
		success:function(data){
			console.log("sfasdfasdfasdf");
			alert("修改成功 ，请重新登陆");
		},
		error:function(){
			alert("修改失败");
		}
	});
});
</script>