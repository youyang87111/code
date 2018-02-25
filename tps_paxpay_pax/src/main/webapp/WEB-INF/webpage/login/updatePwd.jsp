<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/meta.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <script>
        var contextPath='${pageContext.request.contextPath}'
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta mame="renderer" content="webkit | ie-comp| ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>PAXPAY</title>

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
    <link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" />
    <link href="${ctx}/img/favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <link href="${ctx}/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/js/plugin/messenger/css/messenger.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/plugin/messenger/css/messenger-theme-flat.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/js/plugin/validform/css/style.css">
    <%--<link href="${ctx}/css/login.css" rel="stylesheet">--%>
    <style>
        .lh34{
            line-height: 34px;
        }
        .glyphicon:before{
            color: #333 !important;
        }
        .messenger .messenger-actions {
            background-color: #F1F4F5 !important;
            float: none !important;
            padding-top: 6px !important;
            padding-bottom: 6px !important;
            margin-top: 0px !important;
            width: 100% !important;
            text-align: center !important;
        }
        .messenger, .messenger div {
            background-color: transparent !important;
        }
        .alert-danger, .alert-error{
            background-color: #fff !important;
            border: 1px solid transparent !important;
        }
        .messenger .messenger-message-inner{
            background-color: #fff !important;
        }
    </style>
</head>
<body style="background-color: #eee">
<div style="width: 50%;margin: 50px auto">
    <h3 style="margin-bottom: 50px;text-align: center;color: red;" id="firstLogin"></h3>
    <form id="updatePwdFormPage">
        <div class="form-group has-feedback" style="bottom: 20px">
            <input type="hidden" value="${loginname}" id="loginname">
            <input type="hidden" value="${password}" id="oldVal">
            <label class="col-sm-3 control-label lh34 i18n-flag" i18n-data="commonUI_old_password" id="oldPwdText"></label>
            <div class="col-sm-9">
                <input id="oldPwd" class="form-control  i18n-flag" type="password"  maxlength="20" autocomplete="off" datatype="notEqual"  >
                <span class="glyphicon glyphicon-lock form-control-feedback" style="right:10px"></span>
            </div>
            <div class="col-sm-offset-3 col-sm-9"></div>
        </div>
        <div class="form-group has-feedback"  style="bottom: 0px">
            <label class="col-sm-3 control-label lh34 i18n-flag  " i18n-data="commonUI_new_password" id="newPwdText"></label>
            <div class="col-sm-9">
                <input id="newPwd1" class="form-control i18n-flag" type="password"  maxlength="20" datatype="modPwd" name="password1" >
                <span class="glyphicon glyphicon-lock form-control-feedback" style="right:10px"></span>
            </div>
            <div class="col-sm-offset-3 col-sm-9"></div>
        </div>
        <div class="form-group has-feedback" style="bottom: -20px">
            <label class="col-sm-3 control-label lh34 i18n-flag" i18n-data="commonUI_confirm_password" id="cfmPwdText"></label>
            <div class="col-sm-9">
                <input id="newPwd2" class="form-control  i18n-flag" type="password" maxlength="20" autocomplete="off" datatype="modPwd,pwdcpm" name="password2"   >
                <span class="glyphicon glyphicon-lock form-control-feedback" style="right:10px;"></span>
            </div>
            <div class="col-sm-offset-3 col-sm-9"></div>
        </div>

        <div class="row" style="position:relative;bottom: -160px">
            <div class="col-sm-3 col-sm-offset-3 " >
                <button class="btn btn-warning btn-block mb10 login_btn login-btn" id="cfmSbt"
                        type="button" style="margin-left:20px " ></button>
            </div>
            <div class="col-sm-3 ">
                <button class="btn btn-default btn-block mb10 login_btn login-btn" id="cancelUpdateBtn"
                        type="button" style="margin-left:20px " ></button>
            </div>
        </div>
    </form>
</div>

<script src="${ctx}/js/plugin/jQuery/jQuery-2.1.4.min.js"></script>
<script src="${ctx}/css/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/js/plugin/jQuery/jquery.md5.js" type="text/javascript"></script>
<script src="${ctx}/js/common/jquery.i18n.properties-min-1.0.9.js"></script>
<script src="${ctx}/js/plugin/jQuery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/js/plugin/messenger/js/messenger.js" type="text/javascript"></script>
<script src="${ctx}/js/common/common.js"></script>
<script src="${ctx}/js/plugin/validform/js/Validform_v5.3.2.js"></script>
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
            };
        }
    })(window);


    //TODO：更换语言接口

    function selLocale(){
        var locale = $.cookie("locale");
        if(!locale){locale="zh_CN"}

        jQuery.i18n.properties({
            name: 'i18n',
            path: '${ctx}/i18n/',
            mode: 'map',
            cache:true,
            language: locale,
            callback: function () {
                $('#oldPwdText').html($.i18n.prop('commonUI_old_password'));//旧密码
                $('#newPwd1,#newPwd2').attr("placeholder", $.i18n.prop('modPwd'));
                $('#newPwd1,#newPwd2').attr("errormsg", $.i18n.prop('modPwd'));
                $('#oldPwd,#newPwd1,#newPwd2').attr("nullmsg", $.i18n.prop('enter_pwd'));//密码不能为空
                $('#oldPwd,#newPwd1,#newPwd2').attr("sucmsg", $.i18n.prop('tip_fourth'));//密码不能为空
                $('#newPwd2').attr("placeholder",$.i18n.prop('modPwd'));
                $('#newPwdText').html($.i18n.prop('commonUI_new_password'));//新密码
                $('#cfmPwdText').html($.i18n.prop('commonUI_confirm_password'));//确认密码
                $('#cfmSbt').html($.i18n.prop('error_data_comfirm_1'));//确定
                $('#cancelUpdateBtn').html($.i18n.prop('cp_cancel'));//取消
                $('#firstLogin').html($.i18n.prop('first_login'));//首次登录
            }
        });
    }
    selLocale();


    $("#updatePwdFormPage").Validform({//修改密码表单校验
        tiptype: 2,
        datatype: {
            "notEqual":function (get) {
                if($.md5(get)!=$("#oldVal").val()){//对比之前的密码，不相等时提示
                    return I("init_pwd")
                }
                return true;
            },
            "pwdcpm":function (get) {
                var newpwd=$("#newPwd1").val();
                if(newpwd!=get){//新密码与确认密码不相等
                    return I("tip_third")
                }
                return true;
            }
        },
        showAllError: true,
        postonce: true,
        ignoreHidden: true

    });


    //修改密码提交
    $("#cfmSbt").on(
        "click",
        function(e) {
            $("#updatePwdFormPage input").trigger("blur");
            if($("#updatePwdFormPage .Validform_wrong:visible").length>0){
                PAX_OBJECT.Messenger.alert("error", I('main_improve_info'));//请完善信息再提交
                return;
            }
            e.preventDefault();
            PAX_FUNCTION.ajaxPost(contextPath+"/user/firstUpdatePwd", {
                "oldPwd" : $.md5($("#oldPwd").val()),
                "newPwd1" : $.md5($("#newPwd1").val()),
                "newPwd2" : $.md5($("#newPwd2").val()),
                "loginname":$("#loginname").val()
            }, function(data) {
                PAX_OBJECT.Messenger.confirm(I("mod_pwd_relo"),
                    function() {
                        var form = $("<form action='" + contextPath
                            + "/user/logout.do'></form>");
                        $("body").append(form);
                        form.submit();
                    });
            }, function(data) {
                PAX_OBJECT.Messenger.alert('error', data.msg);
            });
        });

    //重置表单
    $("#cancelUpdateBtn").click(function () {
    	$("#updatePwdFormPage").Validform().resetForm();
    })
</script>

</body>
</html>