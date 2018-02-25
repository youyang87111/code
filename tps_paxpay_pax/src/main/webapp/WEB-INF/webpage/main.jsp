<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script >
        <%--var webtile = '${webtitle}';--%>
        $(document).attr("title",I("tps_plat"));
	</script>
	<title ></title>
	<%--<title>${webtitle}</title>--%>
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" />
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="${ctx}/js/plugin/zTree/css/zTreeStyle/metro.css">
	<link rel="stylesheet" href="${ctx}/js/plugin/validform/css/style.css">

	<link rel="stylesheet" href="${ctx}/js/plugin/zTree/css/demo.css">
	<link rel="stylesheet" href="${ctx}/js/plugin/zTree/css/zTreeStyle/zTreeStyle.css">
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<style>
		.text-r{
			text-align:right;
			margin-top:6px;
		}
		body.modal-open {
			overflow-y: auto !important;
			padding-right: 0 !important;
		}

		.sidebar-menu.ztree ul.level0{
			padding-left:5px !important;
		}
		
		.sidebar-menu.ztree li.level1,.sidebar-menu.ztree ul.level1{
			border-top:1px solid #EEE;
		}
		.sidebar-menu.ztree li.level1:nth-child(1){
			border-top:none !important;
		}
		.sidebar-menu.ztree li.level0{
			border-bottom:1px solid #EEE;
		}
		.sidebar-menu.ztree a{
			width:100%;
		}
		.sidebar-menu.ztree span {
			color:#000 !important;
			font-size:14px !important;
		}
		.sidebar-menu.ztree a.level1 span{
			font-weight:600 !important;
		}
		.sidebar-menu.ztree a.curSelectedNode {
			border:none !important;
			color:#74DCEF !important;
			background-color:transparent !important;
		}
		
		.sidebar-menu.ztree a.curSelectedNode,.sidebar-menu.ztree a {
			height:30px !important;
		}
		.sidebar-menu.ztree a.curSelectedNode span,.sidebar-menu.ztree a span{
			line-height:30px !important;
		}
		
		.sidebar-menu.ztree li a.curSelectedNode span{
			color:#74DCEF !important;
			/*font-weight:600;*/
		}
		.sidebar-menu.ztree li a.active{
			color:#74DCEF !important;
		}
		
		.sidebar-menu.ztree li a.active span{
			color:#74DCEF !important;
			/*font-weight:600;*/
		}
		
		.sidebar-menu.ztree li ul.line{
			background:none !important;
		}
		
		.sidebar-menu.ztree a{
			line-height:30px !important;
		}
		
		.sidebar-menu.ztree li a:hover span{
			color:#74DCEF !important;
		}
					
	</style>
</head>
<body class="skin-blue sidebar-mini skin-green-light" >
<div class="wrapper">

	<!-- Main Header -->
	<header class="main-header">
		<a class="logo">
			<span class="logo-mini">PAX</span>
			<span class="logo-lg">
				<%--<img src="${contextPath}/img/PAXPAY_new.png" style="margin-top:-10px"  class="img-responsive">--%>
			</span>
		</a>
		<nav class="navbar navbar-static-top" role="navigation">
			<%--<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">--%>
				<%--<span class="sr-only">Toggle navigation</span>--%>
			<%--</a>--%>
			<div class="user" id="userinfo" i18n-data="">${username}<span class="i18n-flag" id="uerOrg"></span></div>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="user user-menu tasks-menu">
						<button id="modifyPwd" type="button" class="btn" data-toggle="modal" data-target="#modal_modifyPwd">
							<span class="glyphicon glyphicon-lock"></span><br/>
							<span class="hidden-xs i18n-flag" i18n-data="commonUI_modify_password"> 修改密码</span>
						</button>
					</li>
					<li class="user user-menu tasks-menu">
						<button id="signOut" type="button" class="btn" data-toggle="modal">
							<span class="glyphicon glyphicon-off "></span><br/>
							<span id="" class="hidden-xs i18n-flag" i18n-data="commonUI_cancellation">注销</span>
						</button>
					</li>
					<li></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">

		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">

			<!-- Sidebar Menu -->
			<ul id="menu_tree" class="sidebar-menu ztree">
				<!-- Optionally, you can add icons to the links -->

			</ul><!-- /.sidebar-menu -->
		</section>
		<!-- /.sidebar -->
	</aside>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" id="myContent">
		<!-- Content Header (Page header) -->
		<section class="" style="background-color: #fff;border-top: 1px solid #ddd">
			<div style="position:relative">
				<img class="img-responsive" src="${ctx}/img/main_bg.png" style="width:100%;" alt="welcome page"/>
				<%--<div style="font-size:60px;color:#53aad1;width:100%;text-align:center;position:absolute;top:24%;font-weight:bold;" class="i18n-flag" i18n-data="welcom_cms"></div>--%>
				<div style="font-size:16px;color:#53aad1;width:100%;text-align:center;position:absolute;top:60%;" class="i18n-flag" i18n-data="welcom_tip"></div>
			</div>

		</section>

		<!-- Main content -->
		<!--<section class="content">-->

		<!-- Your Page Content Here -->

		<!--</section>-->
	</div><!-- /.content-wrapper -->


	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Create the tabs -->

		<!-- Tab panes -->
		<div class="tab-content">
			<!-- Home tab content -->
			<div class="tab-pane active" id="control-sidebar-home-tab">
				<h4 class="control-sidebar-heading">Skins</h4>
			</div><!-- /.tab-pane -->
			<!-- Stats tab content -->
			<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div><!-- /.tab-pane -->
		</div>
	</aside><!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div><!-- ./wrapper -->
<script src="${ctx}/js/plugin/formAutoFill/jquery.formautofill.min.js"></script>
<%--<script src="${ctx}/js/common/commonUI.js"  type="text/javascript"></script>--%>
<script src="${ctx}/js/plugin/validform/js/Validform_v5.3.2.js"></script>
<script>

	var orgname = '${orgname}';
	$("#uerOrg").text("["+_T(orgname)+"]");	

    setTimeout(function(){
        $('body').addClass('modal-open')
    },1000)

</script>
</body>
</html>