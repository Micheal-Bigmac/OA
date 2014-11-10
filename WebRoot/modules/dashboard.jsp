
<!-- PAGE LEVEL STYLES -->
<link href="css/jquery.gritter.css" rel="stylesheet" type="text/css" />
<link href="css/daterangepicker.css" rel="stylesheet" type="text/css" />
<link href="css/fullcalendar.css" rel="stylesheet" type="text/css" />
<link href="css/jqvmap.css" rel="stylesheet" type="text/css" media="screen" />
<link href="css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen" />

<%@include file="../JSP/debugFile.inc" %>
<div class="row-fluid">
	<div class="span12">

		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			仪表盘 <small>统计和更多 </small>
		</h3>
		<ul class="breadcrumb">
			<li>
				<i class="icon-home"></i>
				<a href="index.jsp">主页</a>
				<i class="icon-angle-right"></i>
			</li>
			<li><a href="#">仪表盘</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<div id="dashboard">

	<div class="row-fluid">
		<div class="span6">
			<!-- BEGIN REGIONAL STATS PORTLET-->
			<div class="portlet">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-globe"></i>Regional Stats
					</div>
					<div class="tools">
						<a href="" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a>
						<a href="" class="reload"></a> <a href="" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="region_statistics_loading">
						<img src="image/loading.gif" alt="loading" />
					</div>
					<div id="region_statistics_content" class="hide">
						<div class="btn-toolbar">
							<div class="btn-group " data-toggle="buttons-radio">
								<a href="" class="btn mini active">Users</a> <a href="" class="btn mini">Orders</a>
							</div>
							<div class="btn-group pull-right">
								<a href="" class="btn mini dropdown-toggle" data-toggle="dropdown"> Select Region <span
									class="icon-angle-down"></span>
								</a>
								<ul class="dropdown-menu pull-right">
									<li><a href="javascript:;" id="regional_stat_world">World</a></li>
									<li><a href="javascript:;" id="regional_stat_usa">USA</a></li>
									<li><a href="javascript:;" id="regional_stat_europe">Europe</a></li>
									<li><a href="javascript:;" id="regional_stat_russia">Russia</a></li>
									<li><a href="javascript:;" id="regional_stat_germany">Germany</a></li>
								</ul>
							</div>
						</div>
						<div id="vmap_world" class="vmaps chart hide"></div>
						<div id="vmap_usa" class="vmaps chart hide"></div>
						<div id="vmap_europe" class="vmaps chart hide"></div>
						<div id="vmap_russia" class="vmaps chart hide"></div>
						<div id="vmap_germany" class="vmaps chart hide"></div>
					</div>
				</div>
			</div>
			<!-- END REGIONAL STATS PORTLET-->
		</div>
		<div class="span6">
			<!-- BEGIN PORTLET-->
			<div class="portlet paddingless">
				<div class="portlet-title line">
					<div class="caption">
						<i class="icon-bell"></i>Feeds
					</div>
					<div class="tools">
						<a href="" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a>
						<a href="" class="reload"></a> <a href="" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body">
					<!--BEGIN TABS-->
					<div class="tabbable tabbable-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab_1_1" data-toggle="tab">System</a></li>
							<li><a href="#tab_1_2" data-toggle="tab">Activities</a></li>
							<li><a href="#tab_1_3" data-toggle="tab">Recent Users</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_1_1">
								<div class="scroller" data-height="290px" data-always-visible="1" data-rail-visible="0">
									<ul class="feeds">
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-success">
															<i class="icon-bell"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															You have 4 pending tasks. <span class="label label-important label-mini"> Take
																action <i class="icon-share-alt"></i>
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">Just now</div>
											</div>
										</li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New version v1.4 just lunched!</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">20 mins</div>
												</div>
										</a></li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-important">
															<i class="icon-bolt"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">Database server #12 overloaded. Please fix the issue.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">24 mins</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">30 mins</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-success">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">40 mins</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-warning">
															<i class="icon-plus"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New user registered.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">1.5 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-success">
															<i class="icon-bell-alt"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															Web server hardware needs to be upgraded. <span
																class="label label-inverse label-mini">Overdue</span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">2 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">3 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-warning">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">5 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">18 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">21 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">22 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">21 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">22 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">21 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">22 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">21 hours</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-info">
															<i class="icon-bullhorn"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">New order received. Please take care of it.</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">22 hours</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="tab-pane" id="tab_1_2">
								<div class="scroller" data-height="290px" data-always-visible="1" data-rail-visible1="1">
									<ul class="feeds">
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New order received</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">10 mins</div>
												</div>
										</a></li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-important">
															<i class="icon-bolt"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															Order #24DOP4 has been rejected. <span class="label label-important label-mini">Take
																action <i class="icon-share-alt"></i>
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">24 mins</div>
											</div>
										</li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-success">
																<i class="icon-bell"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">New user registered</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">Just now</div>
												</div>
										</a></li>
									</ul>
								</div>
							</div>
							<div class="tab-pane" id="tab_1_3">
								<div class="scroller" data-height="290px" data-always-visible="1" data-rail-visible1="1">
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Robert Nilson</a> <span class="label label-success">Approved</span>
												</div>
												<div>29 Jan 2013 10:45AM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 10:45AM</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Eric Kim</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 12:45PM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-important">In progress</span>
												</div>
												<div>19 Jan 2013 11:55PM</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Eric Kim</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 12:45PM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-important">In progress</span>
												</div>
												<div>19 Jan 2013 11:55PM</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Eric Kim</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 12:45PM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-important">In progress</span>
												</div>
												<div>19 Jan 2013 11:55PM</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Eric Kim</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 12:45PM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-important">In progress</span>
												</div>
												<div>19 Jan 2013 11:55PM</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Eric Kim</a> <span class="label label-info">Pending</span>
												</div>
												<div>19 Jan 2013 12:45PM</div>
											</div>
										</div>
										<div class="span6 user-info">
											<img alt="" src="image/avatar.png" />
											<div class="details">
												<div>
													<a href="#">Lisa Miller</a> <span class="label label-important">In progress</span>
												</div>
												<div>19 Jan 2013 11:55PM</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--END TABS-->
				</div>
			</div>
			<!-- END PORTLET-->
		</div>
	</div>
	
	<div class="clearfix"></div>
	<div class="row-fluid">
		<div class="span6">
			<!-- BEGIN PORTLET-->
			<div class="portlet box blue calendar">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-calendar"></i>Calendar
					</div>
				</div>
				<div class="portlet-body light-grey">
					<div id="calendar"></div>
				</div>
			</div>
			<!-- END PORTLET-->
		</div>
		<div class="span6">
			<!-- BEGIN PORTLET-->
			<div class="portlet">
				<div class="portlet-title line">
					<div class="caption">
						<i class="icon-comments"></i>Chats
					</div>
					<div class="tools">
						<a href="" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a>
						<a href="" class="reload"></a> <a href="" class="remove"></a>
					</div>
				</div>
				<div class="portlet-body" id="chats">
					<div class="scroller" data-height="435px" data-always-visible="1" data-rail-visible1="1">
						<ul class="chats">
							<li class="in"><img class="avatar" alt="" src="image/avatar1.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Bob Nilson</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="out"><img class="avatar" alt="" src="image/avatar2.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Lisa Wong</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="in"><img class="avatar" alt="" src="image/avatar1.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Bob Nilson</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="out"><img class="avatar" alt="" src="image/avatar3.jpg" />
								<div class="message">
									<span class="arrow"></span>
									<a href="#" class="name">Richard Doe</a> 
									<span class="datetime">at Jul 25, 2012 11:09</span>
									<span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. 
									</span>
								</div></li>
							<li class="in"><img class="avatar" alt="" src="image/avatar3.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Richard Doe</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="out"><img class="avatar" alt="" src="image/avatar1.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Bob Nilson</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="in"><img class="avatar" alt="" src="image/avatar3.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Richard Doe</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. </span>
								</div></li>
							<li class="out"><img class="avatar" alt="" src="image/avatar1.jpg" />
								<div class="message">
									<span class="arrow"></span> <a href="#" class="name">Bob Nilson</a> <span class="datetime">at
										Jul 25, 2012 11:09</span> <span class="body"> Lorem ipsum dolor sit amet, consectetuer
										adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam
										erat volutpat. sed diam nonummy nibh euismod tincidunt ut laoreet. </span>
								</div></li>
						</ul>
					</div>
					<div class="chat-form">
						<div class="input-cont">
							<input class="m-wrap" type="text" placeholder="Type a message here..." />
						</div>
						<div class="btn-cont">
							<span class="arrow"></span> <a href="" class="btn blue icn-only"><i
								class="icon-ok icon-white"></i></a>
						</div>
					</div>
				</div>
			</div>
			<!-- END PORTLET-->
		</div>
	</div>
</div>

<!-- BEGIN JAVASCRIPTS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- 		<script src="js/jquery.vmap.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.russia.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.world.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.europe.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.germany.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.usa.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.vmap.sampledata.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.flot.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.flot.resize.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.pulsate.min.js" type="text/javascript"></script> -->
<!-- 		<script src="js/date.js" type="text/javascript"></script> -->
<!-- 		<script src="js/daterangepicker.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.gritter.js" type="text/javascript"></script> -->
<!-- 		<script src="js/fullcalendar.min.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.easy-pie-chart.js" type="text/javascript"></script> -->
<!-- 		<script src="js/jquery.sparkline.min.js" type="text/javascript"></script> -->
		
<!-- 	<!-- END PAGE LEVEL PLUGINS --> 
<!-- 	<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<!-- 		<script src="js/dashboard.js" type="text/javascript"></script> -->
		
		<script>
			jQuery(document).ready(function() {
				ajax_require(["jquery.vmap.js",
				              "jquery.vmap.sampledata.js",
				              "jquery.vmap.world.js",
				              "jquery.vmap.russia.js",
				              "jquery.vmap.europe.js",
				              "jquery.vmap.germany.js",
				              "jquery.vmap.usa.js",
				              "jquery.gritter.js", 
				              "fullcalendar.min.js", 
				              "dashboard.js"], function() {
					Index.initJQVMAP();
					Index.initCalendar();
					Index.initChat();
					//Index.initDashboardDaterange();
					//Index.initIntro();
				});
			});
		</script>
	<!-- END PAGE LEVEL SCRIPTS -->
<!-- END JAVASCRIPTS -->
