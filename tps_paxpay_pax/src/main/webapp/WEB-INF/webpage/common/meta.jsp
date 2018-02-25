<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<c:set var="ctx" value="${pageContext.request.contextPath}"/>
		
		<script>
			var contextPath='${pageContext.request.contextPath}'
		</script>
		<%--<meta name="renderer" content="webkit|ie-comp|ie-stand">--%>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<link href="${ctx}/css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

		<link href="${ctx}/dist/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/dist/ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/plugin/select2/select2.min.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/dist/css/skins/skin-blue.min.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/new_style.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/plugin/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/plugin/loading/loading.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/plugin/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/plugin/messenger/css/messenger.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/plugin/messenger/css/messenger-theme-flat.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/plugin/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/plugin/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />

		<link rel="stylesheet" href="${ctx}/js/plugin/zTree/css/zTreeStyle/metro.css">
		<link rel="stylesheet" href="${ctx}/js/plugin/zTree/css/zTreeStyle/zTreeStyle.css">

		<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/checkout.css" rel="stylesheet" type="text/css" />

		
		<script src="${ctx}/js/plugin/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/jQuery/jquery.md5.js" type="text/javascript"></script>
		<script src="${ctx}/js/common/jquery.i18n.properties-min-1.0.9.js" type="text/javascript"></script>
		<script src="${ctx}/css/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${ctx}/dist/js/app.min.js" type="text/javascript"></script>

		<script src="${ctx}/js/plugin/messenger/js/messenger.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/messenger/js/messenger.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/fileupload/ajaxfileupload.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/select2/select2.full.min.js" type="text/javascript"></script>
		
		<script src="${ctx}/js/plugin/input-mask/jquery.inputmask.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
		
		<script src="${ctx}/js/plugin/datatables/jquery.dataTables.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/iCheck/icheck.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/daterangepicker/moment.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/daterangepicker/daterangepicker.js" type="text/javascript"></script>
		
		<script src="${ctx}/js/plugin/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/datepicker/locales/bootstrap-datepicker.zh-CN.js" type="text/javascript"></script>
		
		<script src="${ctx}/js/plugin/timepicker/bootstrap-timepicker.min.js" type="text/javascript"></script>
		<script src="${ctx}/js/plugin/jQuery/jquery.cookie.js" type="text/javascript"></script>
		<script src="${ctx}/js/common/common.js"  type="text/javascript"></script>
		<script src="${ctx}/js/common/commonUI.js"  type="text/javascript"></script>
		<script src="${ctx}/js/common/commonPlugins.js"  type="text/javascript"></script>
		<script src="${ctx}/js/plugin/layer/layer.js"  type="text/javascript"></script>
		<script src="${ctx}/js/plugin/layer/extend/layer.ext.js"  type="text/javascript"></script>
		<script src="${ctx}/js/plugin/loading/loading.js"  type="text/javascript"></script>
		<script src="${ctx}/js/plugin/jQuery/jquery-form.js"  type="text/javascript"></script>
		<script src="${ctx}/js/plugin/zTree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	</head>
</html>
