<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<script>
        var contextPath='${pageContext.request.contextPath}'
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta mame="renderer" content="webkit | ie-comp| ie-stand">
	<%--<meta name="renderer" content="webkit">--%>
	<%--<meta name="renderer" content="ie-comp">--%>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Pragma" ,content="no-cache">
	<title>PAXPAY</title>

	<%--<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">--%>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" />
	<link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/css/login.css" rel="stylesheet">
</head>
<body style="overflow-x:hidden;">
<div class="">
	<header class="my-top">
		<div class="row">
			<div class="col-sm-offset-2 col-sm-7">
				<img src="${ctx}/img/PAXPAY_SM.png" alt="PAX logo" class="lf" style="height: 40px;margin-top:5px">
				<span class="rt"><span class="hot_line"></span>：023-67904081
                    <%--<a class="my-link PAX_website" target="_blank" href="http://www.paxsz.com" >进入百富</a>--%>
				</span>
			</div>
			<div class="i18n-sel-box col-sm-2">
				<select id="i18n_locale" style="width:150px;">
					<!--
                        <option value="zh_CN">简体中文</option>
                      <option value="en_US">English</option>
                  -->
				</select>
			</div>

		</div>
	</header>
	<section class="">
		<div class="row p-relative bg-class" style="background-image:url(${ctx}/img/001.png);">

			<div class="login-wrap" style="z-index:20">
				 <p style="position:relative;bottom:20px;color:#e15f63; background-color: #f0f0f0;text-align: center;line-height: 24px;">${emsg}</p>
				<h3 class="color-w text-c user_login" ></h3>
				<form id="loginForm" action="${ctx}/user/login" method="post">
					<div class="row form-group has-feedback mt20">

						<div class="col-sm-8 col-sm-offset-2" style="line-height: 80px;width: 225px;">
							<input type="text" name="loginname" class="form-control " id="user_input" placeholder=user_nickname />
							<span class="glyphicon glyphicon-user form-control-feedback" style="right:10px"></span>
						</div>
					</div>
					<div class="row form-group has-feedback mt20">
						<div class="col-sm-8 col-sm-offset-2" style="line-height: 80px;width: 225px;">
							<input type="password"  class="form-control " id="password_input" placeholder=the_pwd />
							<input type="hidden" name="password" id="pwdHide" />
							<input type="hidden" name="loginlocal" id="loginlocal" />
							<span class="glyphicon glyphicon-lock form-control-feedback" style="right:10px"></span>
						</div>
					</div>
					<div class="row form-group  mt20">
						<div class="col-sm-4 col-sm-offset-2" style="width:115px;float:left">
							<input type="text" name="randomCode" id="randomCode" class="form-control " maxlength="4" placeholder=vali_code>
						</div>
						<div class="col-sm-4" style="width:110px;float:left">
							<img src="${ctx}/user/createRandomCode" onclick="this.src='${ctx}/user/createRandomCode?'+(new Date()).getTime()" width="100%" height="32px">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-sm-5 col-sm-offset-3 mt50" style="width:150px;">
							<button class="btn btn-warning btn-block mb10 login_btn login-btn" id="login_submit" type="button" style="margin-left:20px " ></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<section>
		<div class="row">
			<div class="col-sm-2 col-sm-offset-2">
				<div class="text-c">
					<img src="${ctx}/img/login_2.png" alt="Sample">
					<p class="mt50">MPOS&nbsp;&nbsp;<span class="login_breakthrough" ></span></p>
				</div>
			</div>
			<div class="col-sm-2 col-sm-offset-1">
				<div class="text-c">
					<img src="${ctx}/img/login_3.png" alt="Sample">
					<p class="mt50"><span class="desk_pos" ></span>&nbsp;&nbsp;<span class="login_performace" ></span></p>
				</div>
			</div>
			<div class="col-sm-2 col-sm-offset-1">
				<div class="text-c">
					<img src="${ctx}/img/login_4.png" alt="Sample">
					<p class="mt50"><span class="smart_pos"></span>&nbsp;&nbsp;<span class="login_new_experience" ></span></p>
				</div>
			</div>
		</div>
	</section>
	<footer class="text-c my-footer">
        <span class="apply_merch_company"></span><b>V2.00.02_20171213</b>
	</footer>
</div>

<script src="${ctx}/js/plugin/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${ctx}/css/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/js/plugin/jQuery/jquery.md5.js" type="text/javascript"></script>
<script src="${ctx}/js/common/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${ctx}/js/plugin/jQuery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/js/common/common.js"></script>
<script src="${ctx}/js/auth/login.js"></script>
<script>
    (function(window) {
        var theUA = window.navigator.userAgent.toLowerCase();
        if ((theUA.match(/msie\s\d+/) && theUA.match(/msie\s\d+/)[0]) || (theUA.match(/trident\s?\d+/) && theUA.match(/trident\s?\d+/)[0])) {
            var ieVersion = theUA.match(/msie\s\d+/)[0].match(/\d+/)[0] || theUA.match(/trident\s?\d+/)[0];
            if (ieVersion < 9) {
                var str = "您的浏览器版本太低了,可能存在安全风险:";
                var str2 = "推荐使用:<a href='https://www.baidu.com/s?ie=UTF-8&wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8' target='_blank' style='color:#cc0;font-size: 20px;'>谷歌</a>,"
                    + "<a href='https://www.baidu.com/s?ie=UTF-8&wd=%E7%81%AB%E7%8B%90%E6%B5%8F%E8%A7%88%E5%99%A8' target='_blank' style='color:#cc0;font-size: 20px'>火狐</a>,"
                    + "其他双核极速模式";
                document.writeln("<pre style='text-align:center;color:#fff;background-color:#0cc; height:100%;border:0;position:fixed;top:0;left:0;width:100%;z-index:1234'>" +
                    "<h2 style='padding-top:200px;margin:0'><strong>" + str + "<br/></strong></h2><p>" +
                    str2 + "</p><h2 style='margin:0'><strong>如果你的使用的是360浏览器,请切换到极速模式访问<br/><br/>" +
					"参考方法：<a href='http://tieba.baidu.com/p/2913957700' target='_blank'>360浏览器切换极速模式</a></strong></h2></pre>");
                document.execCommand("Stop");
            }
        }
    })(window);

    document.onkeydown = function(e) {
        e = e ? e : window.event;
        var keyCode = e.which ? e.which : e.keyCode;
        if(keyCode == 13)
        $("#login_submit").click();
    };


    //TODO：更换语言接口

    function selLocale(){
        var locale = $.cookie("locale");
        console.log(locale);
        if(!locale){
            //TODO:取URL中的语言设置
            //TODO:如果没有指定语言，根据浏览器或者系统语言选择展示语言
            if (navigator.userLanguage) {
                var baseLang = navigator.userLanguage.substring(0,2).toLowerCase();
            } else {
                var baseLang = navigator.language.substring(0,2).toLowerCase();
            }

            if($("#i18n_locale option[value^="+baseLang+"_]").length <= 0)
                locale = 'en_US';//不支持的语言,默认英文
            else{
                locale = $("#i18n_locale option[value^="+baseLang+"_]").eq(0).val();//
			}
        }else if($("#i18n_locale option[value="+locale+"]").length <= 0){
            locale = 'en_US';
        }
        $("#loginlocal").val(locale);
        $.cookie("locale", locale);
        $("#i18n_locale").val(locale);

        jQuery.i18n.properties({
            name: 'i18n',
            path: '${ctx}/i18n/',
            mode: 'map',
            cache:true,
            language: locale,
            callback: function () {
                $('.user_login').html($.i18n.prop('user_login'));
                $('#user_input').attr("placeholder", $.i18n.prop('user_nickname'));
                $('#password_input').attr("placeholder",$.i18n.prop('the_pwd'));
                $('#randomCode').attr("placeholder",$.i18n.prop('vali_code'));
                $('.login-btn').html($.i18n.prop('login_btn'));
                $('.apply_merch_company').html($.i18n.prop('apply_merch_company'));
                $('.login_wrong_pwd').html($.i18n.prop('login_wrong_pwd'));
                $('.not_found_error_service_system_improve').html($.i18n.prop('not_found_error_service_system_improve'));
                $('.login_timeout').html($.i18n.prop('login_timeout'));
				$('.hot_line').html($.i18n.prop('hot_line'));
				$('.PAX_website').html($.i18n.prop('PAX_website'));
				$('.login_breakthrough').html($.i18n.prop('login_breakthrough'));
				$('.desk_pos').html($.i18n.prop('desk_pos'));
				$('.login_performace').html($.i18n.prop('login_performace'));
				$('.smart_pos').html($.i18n.prop('smart_pos'));
				$('.login_new_experience').html($.i18n.prop('login_new_experience'));
            }
        });
    }

    $(document).ready(function(){

        //获取系统支持的语言
        PAX_FUNCTION.ajaxGet(contextPath + "/common/locale/dropdown",null,
            function (data) {
//				if(data.success=="true"){}
                //成功，初始化语言下拉框

                $("#i18n_locale").html("");
                $.each(data.data.data,function(index, v){
                    if(index==0){
                    	$("#i18n_locale").append("<option value=\""+v.abbrname+"\" selected>"+v.disname+"</option>");	
                    }else{
                    	
                    	$("#i18n_locale").append("<option value=\""+v.abbrname+"\">"+v.disname+"</option>");
                    }
                });
                selLocale();
            },function(data){
                //失败，只初始化中文
                $("#i18n_locale").html("<option value=\"zh_CN\">简体中文</option>");
                selLocale();
            }
        );
        $("#i18n_locale").change(function(){
            console.log($.cookie('locale'));
            PAX_FUNCTION.ajaxGet(contextPath + "/common/locale/switch", {locale:$("#i18n_locale").val()},
                function (data) {
                    var locale = $("#i18n_locale").val();
//                    var href = document.location.href;
                    //记录Cookie
                    $.cookie("locale", locale);
                    //判断Cookie是否启用,如果Cookie没有生效采用URL传值
//                    if(!$.cookie("locale"))
//                    {
//                        if(href.indexOf("?") < 0)
//                            href += "?locale="+locale;
//                        else if(href.indexOf("locale=") < 0)
//                            href += "&locale="+locale;
//                        else
//                            href = href.replace(/locale=([^&]*)/gi, "locale="+locale);
//                    }
                    //更换语言成功，刷新页面
                    //location.reload();
//                    document.location.href = href;
                    selLocale();
                });
        });

    });

</script>

</body>
</html>