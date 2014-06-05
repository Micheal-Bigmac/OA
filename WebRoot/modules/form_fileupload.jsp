<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Metronic | Form Stuff - Multiple File Upload</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="media/css/style.css" rel="stylesheet" type="text/css" />
<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="media/css/jquery.fancybox.css" rel="stylesheet" />
<link href="media/css/jquery.fileupload-ui.css" rel="stylesheet" />
<!-- END PAGE LEVEL STYLES -->
<link rel="shortcut icon" href="media/image/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<div class="page-container row-fluid">
		<div class="page-content">
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<blockquote>
							<p style="font-size:16px">
								File Upload widget with multiple file selection, drag&amp;drop support, progress bars and preview images for jQuery.<br> Supports cross-domain, chunked and resumable file uploads and
								client-side image resizing.<br> Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.
							</p>
						</blockquote>
						<br>
						<!-- The file upload form used as target for the file upload widget -->
						<form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
							<!-- Redirect browsers with JavaScript disabled to the origin page -->
							<noscript>
								<input type="hidden" name="redirect" value="http://blueimp.github.com/jQuery-File-Upload/">
							</noscript>
							<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
							<div class="row-fluid fileupload-buttonbar">
								<div class="span7">
									<!-- The fileinput-button span is used to style the file input field as button -->
									<span class="btn green fileinput-button"> <i class="icon-plus icon-white"></i> <span>Add files...</span> <input type="file" name="files[]" multiple> </span>
									<button type="submit" class="btn blue start">
										<i class="icon-upload icon-white"></i> <span>Start upload</span>
									</button>
									<button type="reset" class="btn yellow cancel">
										<i class="icon-ban-circle icon-white"></i> <span>Cancel upload</span>
									</button>
									<button type="button" class="btn red delete">
										<i class="icon-trash icon-white"></i> <span>Delete</span>
									</button>
									<input type="checkbox" class="toggle fileupload-toggle-checkbox">
								</div>
								<!-- The global progress information -->
								<div class="span5 fileupload-progress fade">
									<!-- The global progress bar -->
									<div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
										<div class="bar" style="width:0%;"></div>
									</div>
									<!-- The extended global progress information -->
									<div class="progress-extended">&nbsp;</div>
								</div>
							</div>
							<!-- The loading indicator is shown during file processing -->
							<div class="fileupload-loading"></div>
							<br>
							<!-- The table listing the files available for upload/download -->
							<table role="presentation" class="table table-striped">
								<tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
							</table>
						</form>
						<br>
						<div class="well">
							<h3>Demo Notes</h3>
							<ul>
								<li>The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).</li>
								<li>Only image files (<strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction).</li>
								<li>Uploaded files will be deleted automatically after <strong>5 minutes</strong> (demo setting).</li>
								<li>You can <strong>drag &amp; drop</strong> files from your desktop on this webpage with Google Chrome, Mozilla Firefox and Apple Safari.</li>
								<li>Please refer to the <a href="https://github.com/blueimp/jQuery-File-Upload/wiki">official plugin documentation</a> for more information.</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<script id="template-upload" type="text/x-tmpl">

							{% for (var i=0, file; file=o.files[i]; i++) { %}

							    <tr class="template-upload fade">

							        <td class="preview"><span class="fade"></span></td>

							        <td class="name"><span>{%=file.name%}</span></td>

							        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>

							        {% if (file.error) { %}

							            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>

							        {% } else if (o.files.valid && !i) { %}

							            <td>

							                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>

							            </td>

							            <td class="start">{% if (!o.options.autoUpload) { %}

							                <button class="btn">

							                    <i class="icon-upload icon-white"></i>

							                    <span>Start</span>

							                </button>

							            {% } %}</td>

							        {% } else { %}

							            <td colspan="2"></td>

							        {% } %}

							        <td class="cancel">{% if (!i) { %}

							            <button class="btn red">

							                <i class="icon-ban-circle icon-white"></i>

							                <span>Cancel</span>

							            </button>

							        {% } %}</td>

							    </tr>

							{% } %}

						</script>
						<!-- The template to display files available for download -->
						<script id="template-download" type="text/x-tmpl">

							{% for (var i=0, file; file=o.files[i]; i++) { %}

							    <tr class="template-download fade">

							        {% if (file.error) { %}

							            <td></td>

							            <td class="name"><span>{%=file.name%}</span></td>

							            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>

							            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>

							        {% } else { %}

							            <td class="preview">

							            {% if (file.thumbnail_url) { %}

							                <a class="fancybox-button" data-rel="fancybox-button" href="{%=file.url%}" title="{%=file.name%}">

							                <img src="media/image/{%=file.thumbnail_url%}">

							                </a>

							            {% } %}</td>

							            <td class="name">

							                <a href="{%=file.url%}" title="{%=file.name%}" data-gallery="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>

							            </td>

							            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>

							            <td colspan="2"></td>

							        {% } %}

							        <td class="delete">

							            <button class="btn red" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}"{% if (file.delete_with_credentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>

							                <i class="icon-trash icon-white"></i>

							                <span>Delete</span>

							            </button>

							            <input type="checkbox" class="fileupload-checkbox hide" name="delete" value="1">

							        </td>

							    </tr>

							{% } %}

						</script>
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>
		<div class="footer-tools">
			<span class="go-top"> <i class="icon-angle-up"></i> </span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->
	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="media/js/jquery.fancybox.pack.js"></script>
	<!-- BEGIN:File Upload Plugin JS files-->
	<script src="media/js/jquery.ui.widget.js"></script>
	<!-- The Templates plugin is included to render the upload/download listings -->
	<script src="media/js/tmpl.min.js"></script>
	<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
	<script src="media/js/load-image.min.js"></script>
	<!-- The Canvas to Blob plugin is included for image resizing functionality -->
	<script src="media/js/canvas-to-blob.min.js"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
	<script src="media/js/jquery.iframe-transport.js"></script>
	<!-- The basic File Upload plugin -->
	<script src="media/js/jquery.fileupload.js"></script>
	<!-- The File Upload file processing plugin -->
	<script src="media/js/jquery.fileupload-fp.js"></script>
	<!-- The File Upload user interface plugin -->
	<script src="media/js/jquery.fileupload-ui.js"></script>
	<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE8+ -->
	<!--[if gte IE 8]><script src="media/js/jquery.xdr-transport.js"></script><![endif]-->
	<!-- END:File Upload Plugin JS files-->
	<!-- END PAGE LEVEL PLUGINS -->
	<script src="media/js/app.js"></script>
	<script src="media/js/form-fileupload.js"></script>
	<script>

		jQuery(document).ready(function() {

		// initiate layout and plugins

		App.init();

		FormFileUpload.init();

		});

	</script>
	<!-- END JAVASCRIPTS -->
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>
</body>
<!-- END BODY -->
</html>

<body class="ContentBody">
	
	<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr></tr>
		<tr align="center">
			<td class="TablePanel">添加流程</td>
		</tr>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
					<legend>流程信息</legend>
					<s:form action="WorkFlowAction!addWorkFlow" method="post" enctype="multipart/form-data"
						id="form">
						<div class="MainDiv">
						<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
						
							<tr>
								<td align="right">添加流程定义</td>
								<td align="center"><input name="uploadFiles" type="file"
									 />
								</td>
								<td align="center">&nbsp;</td>
							</tr>
							<tr>
								<td align="right">添加流程图片${param.id }</td>
								<td align="center"><input name="uploadFiles" type="file"
									/>
									<input type="hidden" name="workFlow.id" value="${param.id }"> 
								</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
						<div > 
							<table>
								<tr >
								<td> <input type="submit" value="提交" onclick=""></td>
								<td> <input type="button" value="返回" onclick="window.history.go(-1);"></td>
								</tr>
							</table>
						</div>
						</div>
					</s:form>
					<br />
				</fieldset></TD>
		</TR>
	</TABLE>
	
</body>
</html>
