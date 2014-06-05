<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.oa.model.Users"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8" />

<title>OA管理系统 | 仪表盘</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<link rel="shortcut icon" href="image/favicon.ico" />

<!-- GLOBAL MANDATORY STYLES -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="css/uniform.default.css" rel="stylesheet" type="text/css" />
 <link href="css/Mystyle.css" rel="stylesheet" type="text/css"> 

<!-- PAGE LEVEL STYLES -->
<link href="css/jquery.gritter.css" rel="stylesheet" type="text/css" />
<link href="css/spinner.css" rel="stylesheet" type="text/css" />

<!-- CORE JAVASCRIPT -->
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var required_files = "";
	function ajax_require(filelist, callback) {
		var fn;
		
// 		console.log("ajax_required files: ", required_files);
		
		// remove loaded js file
		for (var i = 0; i < filelist.length; i++) {
			fn = filelist[i];
			
			if (required_files.search(fn) !== -1) {
				filelist.splice(i, 1);
				i--;
			}
		}
		
		function aaa() {
			if (filelist.length === 0) {
				callback();
				return;
			}
			fn = filelist[0];
			
// 			console.log("ajax_require file: ", fn);
			
			$.ajax({
				url: "js/"+fn,
				success: function(js) {
					eval(js);
					
					var fn = this.url.substr(3);
					
					required_files += " " + fn;
					for (var i in filelist) {
						if (fn === filelist[i]) {
							filelist.splice(i, 1);
							break;
						}
					}
					aaa();
				},
				error: function() {
// 					console.log("ajax_require fail: ", this.url);
					for (var i in filelist) {
						if (fn === filelist[i]) {
							filelist.splice(i, 1);
							break;
						}
					}
					aaa();
				}
			});
		}
		aaa();
	}
</script>
</head>

<body class="page-header-fixed page-sidebar-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- LOGO -->
				<a class="brand" href="index.jsp"> <img src="image/logo.png" alt="logo" />
				</a>
				
				<!-- RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse"
					data-target=".nav-collapse"> <img src="image/menu-toggler.png" alt="" />
				</a>
				
				<!-- TOP NAVIGATION MENU -->
				<ul class="nav pull-right">
					<!-- NOTIFICATION DROPDOWN -->
					<li class="dropdown" id="header_notification_bar"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <i class="icon-warning-sign"></i> <span class="badge">6</span>
					</a>
						<ul class="dropdown-menu extended notification">
							<li>
								<p>You have 14 new notifications</p>
							</li>
							<li><a href="#"> <span class="label label-success"><i class="icon-plus"></i></span>
									New user registered. <span class="time">Just now</span>
							</a></li>
							<li><a href="#"> <span class="label label-important"><i class="icon-bolt"></i></span>
									Server #12 overloaded. <span class="time">15 mins</span>
							</a></li>
							<li><a href="#"> <span class="label label-warning"><i class="icon-bell"></i></span>
									Server #2 not respoding. <span class="time">22 mins</span>
							</a></li>
							<li><a href="#"> <span class="label label-info"><i class="icon-bullhorn"></i></span>
									Application error. <span class="time">40 mins</span>
							</a></li>
							<li><a href="#"> <span class="label label-important"><i class="icon-bolt"></i></span>
									Database overloaded 68%. <span class="time">2 hrs</span>
							</a></li>
							<li><a href="#"> <span class="label label-important"><i class="icon-bolt"></i></span>
									2 user IP blocked. <span class="time">5 hrs</span>
							</a></li>
							<li class="external"><a href="#">See all notifications <i class="m-icon-swapright"></i></a>
							</li>
						</ul></li>
					<!-- INBOX DROPDOWN -->
					<li class="dropdown" id="header_inbox_bar"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <i class="icon-envelope"></i> <span class="badge">5</span>
					</a>
						<ul class="dropdown-menu extended inbox">
							<li>
								<p>You have 12 new messages</p>
							</li>
							<li><a href="inbox.html?a=view"> <span class="photo"><img
										src="image/avatar2.jpg" alt="" /></span> <span class="subject"> <span class="from">Lisa
											Wong</span> <span class="time">Just Now</span>
								</span> <span class="message"> Vivamus sed auctor nibh congue nibh. auctor nibh auctor
										nibh... </span>
							</a></li>
							<li><a href="inbox.html?a=view"> <span class="photo"><img
										src="./image/avatar3.jpg" alt="" /></span> <span class="subject"> <span class="from">Richard
											Doe</span> <span class="time">16 mins</span>
								</span> <span class="message"> Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh
										auctor nibh... </span>
							</a></li>
							<li><a href="inbox.html?a=view"> <span class="photo"><img
										src="./image/avatar1.jpg" alt="" /></span> <span class="subject"> <span class="from">Bob
											Nilson</span> <span class="time">2 hrs</span>
								</span> <span class="message"> Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor
										nibh... </span>
							</a></li>
							<li class="external"><a href="inbox.html">See all messages <i
									class="m-icon-swapright"></i></a></li>
						</ul></li>
						
					<!-- TODO DROPDOWN -->
					<li class="dropdown" id="header_task_bar"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <i class="icon-tasks"></i> <span class="badge">5</span>
					</a>
						<ul class="dropdown-menu extended tasks">
							<li>
								<p>You have 12 pending tasks</p>
							</li>
							<li><a href="#"> <span class="task"> <span class="desc">New release
											v1.2</span> <span class="percent">30%</span>
								</span> <span class="progress progress-success "> <span style="width: 30%;" class="bar"></span>
								</span>
							</a></li>
							<li><a href="#"> <span class="task"> <span class="desc">Application
											deployment</span> <span class="percent">65%</span>
								</span> <span class="progress progress-danger progress-striped active"> <span
										style="width: 65%;" class="bar"></span>
								</span>
							</a></li>
							<li><a href="#"> <span class="task"> <span class="desc">Mobile app
											release</span> <span class="percent">98%</span>
								</span> <span class="progress progress-success"> <span style="width: 98%;" class="bar"></span>
								</span>
							</a></li>
							<li><a href="#"> <span class="task"> <span class="desc">Database
											migration</span> <span class="percent">10%</span>
								</span> <span class="progress progress-warning progress-striped"> <span style="width: 10%;"
										class="bar"></span>
								</span>
							</a></li>
							<li><a href="#"> <span class="task"> <span class="desc">Web server
											upgrade</span> <span class="percent">58%</span>
								</span> <span class="progress progress-info"> <span style="width: 58%;" class="bar"></span>
								</span>
							</a></li>
							<li><a href="#"> <span class="task"> <span class="desc">Mobile
											development</span> <span class="percent">85%</span>
								</span> <span class="progress progress-success"> <span style="width: 85%;" class="bar"></span>
								</span>
							</a></li>
							<li class="external"><a href="#">See all tasks <i class="m-icon-swapright"></i></a></li>
						</ul></li>
						
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" src="${admin.personid.photoPath == null ? 'image/avatar_small_1.jpg' : admin.personid.photoPath }" style="height: 29px; width: 29px;"/>
							<span class="username">${admin.personid.name}</span>
							<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a class="ajaxify" href="JSP/userProfile.jsp" target="page"><i class="icon-user"></i> 个人信息</a></li>
							<li><a href="inbox.html" target="page"><i class="icon-envelope"></i> 收件箱(3)</a></li>
							<li><a href="#" target="page"><i class="icon-tasks"></i> 任务列表</a></li>
							<li class="divider"></li>
							<li><a href="extra_lock.jsp"><i class="icon-lock"></i> 锁屏</a></li>
							<li><a href="UserAction!back"><i class="icon-key"></i> 注销</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
				<!-- END TOP NAVIGATION MENU -->
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse ">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu">
				<!-- SIDEBAR TOGGLER BUTTON -->
				<li>
					<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- RESPONSIVE QUICK SEARCH FORM -->
				<li>
					<form class="sidebar-search">
						<div class="input-box">
							<a href="javascript:;" class="remove"></a> <input type="text" placeholder="Search..." /> <input
								type="button" class="submit" value=" " />
						</div>
					</form>
				</li>
				<!-- DASHBORD -->
				 <li class="start active ">
					<a class="ajaxify start" href="modules/dashboard.jsp"> 
						<i class="icon-home"></i>
						<span class="title">仪表盘</span>
						<span class="selected"></span>
					</a>
				</li> 
				<!-- OTHER MODULE MENUS -->
				<s:iterator var="module" value="#session.modules" status="num">
				<li>
					<a href="javascript:;">
						<i class="icon-cogs"></i>
						<span class="title">${module.key.name}</span>
						<span class="arrow "></span>
						<span class="selected"></span>
					</a> 
					<s:if test=" #module.value.size">
					<ul class="sub-menu">
						<s:iterator var="children" value="#module.value">
							<li>
								<a class="ajaxmenu" href="${children.key.url}">
									<span>${children.key.name}</span>
								</a>
							</li>
						</s:iterator>
					</ul>
					</s:if>
				</li>
				</s:iterator>
				<!-- <li>
					<a href="javascript:;">
						<i class="icon-cogs"></i>
						<span class="title">其他</span>
						<span class="arrow "></span>
						<span class="selected"></span>
					</a> 
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="modules/calendar.jsp">
								<span>日历</span>
							</a>
						</li>
					</ul>
				</li> -->
			</ul>
		</div>

		<!-- BEGIN PAGE -->
		<div class="page-content">
			<div class="container-fluid">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="color-panel hidden-phone">
				<div class="color-mode-icons icon-color"></div>
				<div class="color-mode-icons icon-color-close"></div>
				<div class="color-mode">
					<p>THEME COLOR</p>
					<ul class="inline">
						<li class="color-black current color-default" data-style="default"></li>
						<li class="color-blue" data-style="blue"></li>
						<li class="color-brown" data-style="brown"></li>
						<li class="color-purple" data-style="purple"></li>
						<li class="color-grey" data-style="grey"></li>
						<li class="color-white color-light" data-style="light"></li>
					</ul>
					<label>
						<span>Layout</span>
						<select class="layout-option m-wrap small">
							<option value="fluid" selected="">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</label>
					<label>
						<span>Header</span>
						<select class="header-option m-wrap small">
							<option value="fixed" selected="">Fixed</option>
							<option value="default">Default</option>
						</select>
					</label>
					<label>
						<span>Sidebar</span>
						<select class="sidebar-option m-wrap small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="">Default</option>
						</select>
					</label>
					<label>
						<span>Footer</span>
						<select class="footer-option m-wrap small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="">Default</option>
						</select>
					</label>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN AJAX CONTENT -->
			<div class="page-content-body">
				<jsp:include page="modules/dashboard.jsp" />
			</div>
			<!-- END AJAX CONTENT -->
			</div>
		</div>
	<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	
	<!-- FOOTER -->
	<div class="footer">
		<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>
		<div class="footer-tools">
			<span class="go-top"> <i class="icon-angle-up"></i>
			</span>
		</div>
	</div>

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
		<!-- BEGIN CORE PLUGINS -->
			<script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
			<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
			<script src="js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
			<script src="js/bootstrap.min.js" type="text/javascript"></script>
			<!--[if lt IE 9]>
				<script src="js/excanvas.min.js"></script>
				<script src="js/respond.min.js"></script>  
			<![endif]-->
			<script src="js/jquery.pulsate.min.js" type="text/javascript"></script>
			<script src="js/jquery.slimscroll.min.js" type="text/javascript"></script>
			<script src="js/jquery.blockui.min.js" type="text/javascript"></script>
			<script src="js/jquery.cookie.min.js" type="text/javascript"></script>
			<script src="js/jquery.uniform.min.js" type="text/javascript"></script>
			<script src="js/jquery.hz2py-min.js" type="text/javascript"></script>
			
			
			<script src="js/myAjaxify.js" type="text/javascript"></script>
		<!-- END CORE PLUGINS -->
		<!-- BEGIN PAGE LEVEL SCRIPTS -->
			<script src="js/index.js" type="text/javascript"></script>
			<script>
				jQuery(document).ready(function() {
					// init layout and core plugins
					App.init();
					// load the content for the dashboard page.
// 					$('.page-sidebar .ajaxify.start').click(); 
				});
		</script>
		<!-- END PAGE LEVEL SCRIPTS -->
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>