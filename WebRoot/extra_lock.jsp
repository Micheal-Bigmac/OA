
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>

<head>

	<meta charset="utf-8" />

	<title>Metronic | Extra - Lock Screen</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="css/style.css" rel="stylesheet" type="text/css"/>

	<link href="css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="css/lock.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body>

	<div class="page-lock">

		<div class="page-logo">

			<a class="brand" href="index.html">

			<img src="image/logo-big.png" alt="logo" />

			</a>

		</div>

		<div class="page-body">

			<img class="page-lock-img" style="width: 200px;height:200px" src= "${admin.personid.photoPath == null ? 'image/avatar_small_1.jpg' : admin.personid.photoPath }" alt="">

			<div class="page-lock-info">

				<h1>${admin.account}</h1>

				<span>${admin.personid.email}</span>

				<span><em>Locked</em></span>

				<form class="form-search" action="UserAction!login">

					<div class="input-append">
						<input type="hidden" name="user.account" value="${admin.account }">
						<input type="password" name="user.password" class="m-wrap" placeholder="Password">

						<button type="submit" class="btn blue icn-only"><i class="m-icon-swapright m-icon-white"></i></button>

					</div>

					<div class="relogin">

						<a href="login.jsp">Not ${admin.account} ?</a>

					</div>

				</form>

			</div>

		</div>

		<div class="page-footer">

			2013 &copy; Metronic. Admin Dashboard Template.

		</div>

	</div>

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="js/jquery-1.10.1.min.js" type="text/javascript"></script>

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

	<script src="js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="js/jquery.backstretch.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->   

	<script src="js/app.js"></script>  

	<script src="js/lock.js"></script>      

	<script>

		jQuery(document).ready(function() {    

		   App.init();

		   Lock.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>