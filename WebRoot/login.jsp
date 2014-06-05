<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.oa.model.Users"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<head>

<meta charset="utf-8" />

<title>OA管理系统 | 登陆</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<link rel="shortcut icon" href="image/favicon.ico" />

<!-- GLOBAL MANDATORY STYLES -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />

<link href="css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/style-responsive.css" rel="stylesheet" type="text/css" />

<link href="css/default.css" rel="stylesheet" type="text/css" id="style_color" />

<link href="css/uniform.default.css" rel="stylesheet" type="text/css" />

<!-- PAGE LEVEL STYLES -->
<link href="css/login.css" rel="stylesheet" type="text/css" />

</head>
<body class="login">
	
	<div class="logo">
		<div class="title">OA管理系统</div>
	</div>

	<!-- LOGIN -->
	<div class="content">
		<!-- LOGIN FORM -->
		<form class="form-vertical login-form" action="UserAction!login" method="post">
			<h3 class="form-title">登陆</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>请输入登陆名和密码。</span>
			</div>
			
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">登陆名</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i> 
						<input class="m-wrap placeholder-no-fix" id="account" type="text" placeholder="登陆名" name="user.account" />
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i> 
						<input class="m-wrap placeholder-no-fix" id="password" type="password" placeholder="密码" name="user.password" />
					</div>
				</div>
			</div>

			<div class="form-actions">
				<label class="checkbox">
					<input type="checkbox" id="remember" name="remember"/> 记住密码
				</label>
				<button type="submit" class="btn green pull-right">
					Login <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
	</div>

	<!-- COPYRIGHT -->
	<div class="copyright">2013 &copy; Metronic. Admin Dashboard Template.</div>
	<!-- COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
		<!-- BEGIN CORE PLUGINS -->
			<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
			<script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
			<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
			<script src="js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
			<script src="js/bootstrap.min.js" type="text/javascript"></script>
			<!--[if lt IE 9]>
			<script src="js/excanvas.min.js"></script>
			<script src="js/respond.min.js"></script>  
			<![endif]-->
			<script src="js/jquery.slimscroll.min.js" type="text/javascript"></script>
			<script src="js/jquery.blockui.min.js" type="text/javascript"></script>
			<script src="js/jquery.cookie.min.js" type="text/javascript"></script>
			<script src="js/jquery.uniform.min.js" type="text/javascript"></script>
		<!-- END CORE PLUGINS -->
		<!-- BEGIN PAGE LEVEL PLUGINS -->
			<script src="js/jquery.validate.min.js" type="text/javascript"></script>
		<!-- END PAGE LEVEL PLUGINS -->
		<!-- BEGIN PAGE LEVEL SCRIPTS -->
			<script src="js/app.js" type="text/javascript"></script>
			<script src="js/login.js" type="text/javascript"></script>
			<script>
				jQuery(document).ready(function() {   
				
				  App.init();
				  Login.init();
				});
			</script>
		<!-- END PAGE LEVEL SCRIPTS -->
	<!-- END JAVASCRIPTS -->

	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>
</body>

<!-- END BODY -->

</html>