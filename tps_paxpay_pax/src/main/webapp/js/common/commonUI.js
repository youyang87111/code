// 功能过滤对外开放接口
var PAX_AUTH_FUNCTION = {};

function initI18N() {
	// TODO:Cookie禁用的情况

	if(true == _I18N_init){
		i18nText();
	}else{
		var locale = $.cookie('locale');
		if (!locale) {
            locale = "zh_CN";
    }
        jQuery.i18n.properties({
			name : 'i18n',
			path : 'i18n/',
			mode : 'map',
			language : locale,
			callback : function() {
				 i18nText();
			}
		});
	}
}

// i18n
function i18nText($o) {
	if ($o) {
		var $element = $o.find(".i18n-flag");
	} else {
		var $element = $(".i18n-flag");
	}

	$element.each(function() {
		var placeholder = $(this).attr("placeholder");
		if (placeholder) {
			$(this).attr('placeholder', I(placeholder));
		}
		var title = $(this).attr("title");
		if (title) {
			$(this).attr('title', I(title));
		}
		var errormsg = $(this).attr("errormsg");
		if (errormsg) {
			$(this).attr('errormsg', I(errormsg));
		}
		var nullmsg = $(this).attr("nullmsg");
		if (nullmsg) {
			$(this).attr('nullmsg', I(nullmsg));
		}

        var confirmmsg = $(this).attr("confirmmsg");
        if (confirmmsg) {
            $(this).attr('confirmmsg', I(confirmmsg));
        }

		var i18n = $(this).attr("i18n-data");
		if (i18n == undefined) {
			return;
		}

		if (i18n.length > 0) {
			if ($(this).is("input[type=text]")
					|| $(this).is("input[type=hidden]")) {
				$(this).val(I(i18n));
			} else {
				$(this).text(I(i18n))
			}
		}

	})
}

/**
 * 主要用於生成共同部分UI
 */
(function($, AdminLTE) {

	/**
	 * List of all the available skins
	 * 
	 * @type Array
	 */
	var my_skins = [ "skin-blue", "skin-black", "skin-red", "skin-yellow",
			"skin-purple", "skin-green", "skin-blue-light", "skin-black-light",
			"skin-red-light", "skin-yellow-light", "skin-purple-light",
			"skin-green-light" ];
	$(document).ready(function(e) {
		setup();
	});
	

	/**
	 * Replaces the old skin with the new skin
	 * 
	 * @param String
	 *            cls the new skin class
	 * @returns Boolean false to prevent link's default action
	 */
	function change_skin(cls) {
		$.each(my_skins, function(i) {
			$("body").removeClass(my_skins[i]);
		});

		$("body").addClass(cls);
		store('skin', cls);
		return false;
	}

	/**
	 * Store a new settings in the browser
	 * 
	 * @param String
	 *            name Name of the setting
	 * @param String
	 *            val Value of the setting
	 * @returns void
	 */
	function store(name, val) {
		if (typeof (Storage) !== "undefined") {
			localStorage.setItem(name, val);
		} else {
			alert('Please use a modern browser to properly view this template!');
		}
	}

	/**
	 * Get a prestored setting
	 * 
	 * @param String
	 *            name Name of of the setting
	 * @returns String The value of the setting | null
	 */
	function get(name) {
		if (typeof (Storage) !== "undefined") {
			return localStorage.getItem(name);
		} else {
			alert('Please use a modern browser to properly view this template!');
		}
	}

	/**
	 * Retrieve default settings and apply them to the template
	 * 
	 * @returns void
	 */
	function setup() {

		init_header();
		init_setter();
		init_menu();
		init_footer();
		init_modal_modifyPwd();
		$("#modifyPwd").click(function() {
			$("#oldPwd").val("");
			$("#newPwd1").val("");
			$("#newPwd2").val("");
            $("#updatePwdForm")[0].reset();
			// $('#modal_modifyPwd').modal();
		});
		var tmp = get('skin');
		if (tmp && $.inArray(tmp, my_skins))
			change_skin(tmp);

		// Add the change skin listener
		$("[data-skin]").on('click', function(e) {
			e.preventDefault();
			change_skin($(this).data('skin'));
		});

		 initI18N();
		 
		 $(window).resize(function(e) {
			$("table.dataTable").each(function(index, element){
				var lastchagesize = $(element).data("lastchagesize");
				var time = (new Date()).getTime();
				var delay = 300;
				
				if(lastchagesize && time-lastchagesize<delay &&$(element).data("redrawTimer") ){
						clearTimeout($(element).data("redrawTimer"));
				}
				$(element)
					.data("lastchagesize", time)
					.data("redrawTimer", setTimeout(function(){
						$(element).attr({"width":"100%"}).css({"width":"100%"}).dataTable().fnDraw();
					}, delay));
				
			});
		});
	}

	/**
	 * 生成共同header部
	 */
	function init_header() {
		$("#signOut").click(
				function() {
					// 确认退出？
					PAX_OBJECT.Messenger.confirm(I("commonUI_confirm_exit"),
							function() {
								var form = $("<form action='" + contextPath
										+ "/user/logout.do'></form>");
								$("body").append(form);
								form.submit();
							});
				});
	}

	/**
	 * 生成共同footer部
	 */
	function init_footer() {
		var footer = '<footer class="main-footer">' + '<strong>'
				+ 'Copyright &copy; 2017' + 'PAX.'
				+ '</strong> All rights reserved.' + '</footer>';
		$(".content-wrapper").after(footer);
	}

	/**
	 * 生成共同menu部
	 */
	function init_menu() {

		// 生成检索框
		// var searchBox = '<form class="sidebar-form">'
		// 		+ '<div class="input-group">'
		// 		+ '<input type="text" name="menuname" class="form-control i18n-flag" placeholder="search_bar" onkeydown="keyDown(event)"/>'
		// 		+ ' <input style="display:none" />'
		// 		+ '<span class="input-group-btn">'
		// 		+ '<button type="button" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>'
		// 		+ '</span>' + '</div>' + '</form>';
		// $("section.sidebar").prepend(searchBox);
		// 获取功能权限 menu选中
		var requestObj = PAX_FUNCTION.GetRequest();
		// 生成MENU
		
		PAX_FUNCTION.ajaxPost('/menu/getMenusByUser.do', {},
			function(data){	
				var zNodes =data.data;
				zNodes.sort(function(a,b){return a.orderno-b.orderno});
				
				$.each(zNodes, function(index, ele){
					ele.name=_T(ele.name);
					if(ele.pId == "")
						ele.open = true;
					else
						ele.open = false;
				});
				var zTree = $.fn.zTree.init($("#menu_tree"), {
					view: {
						dblClickExpand: false//屏蔽掉双击事件
					},
					data: {
						simpleData: {//简单数据模式
							enable:true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: ""
						}
					},
					callback: {
						onNodeCreated: function(e,treeId,treeNode){
							$("#"+treeNode.tId+"_switch").remove();
							$("#"+treeNode.tId+"_ico").remove();
							$("#"+treeNode.tId+"_a.level0").hide();
							$("#"+treeNode.tId+"_span").text(_T($("#"+treeNode.tId+"_span").text()));

							if(treeNode.url != ""){
								$("#"+treeNode.tId+"_a").removeAttr("href").click(function(){
									changePageFn(contextPath+treeNode.url, treeNode.id);
								});
							}
						},
						onClick: function(e,treeId,treeNode){
							if(treeNode.isParent){
								if(treeNode.open == false){
									$.each(getPeerNodes(treeNode), function(index, ele){
										zTree.expandNode(ele, false);
									});
									zTree.expandNode(treeNode, true);
								}
							}
							else
							{
								$("#menu_tree a.active").removeClass("active");
								$("#"+treeNode.tId+"_a").addClass("active");
							}
								/*
							else
							{
								$("#"+treeNode.getParentNode().tId+"_a").addClass("active");
							}*/
						}
					}
				}, zNodes);

				$("body").data("menu_tree", zTree);
				$("#menu_tree_2_a").click();
			},
			function(data){
					PAX_OBJECT.Messenger.alert('error', data.msg);
			}
    );

		// 表示只有点menu后才会进行功能过滤
		if (requestObj.menuId && requestObj.moduleId) {
			var currentMenuId = requestObj.menuId;
			// 保留起来以供后续过滤功能权限使用
			$("body").data("currentMenuId", currentMenuId);
		}
		$("#search-btn").click(menuSearch);
		
		function getPeerNodes(targetNode){  
			if(targetNode == null){  
				return null;
			}else{  
				if(targetNode.getParentNode() != null){  
					return targetNode.getParentNode().children;  
				}  
				return null;  
			}  
    } 
	}

	/**
	 * 生成共同設置部
	 */
	function init_setter() {
		var skins_list = $("<ul />", {
			"class" : 'list-unstyled clearfix'
		});

		// Dark sidebar skins
		var skin_blue = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-blue' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px; background: #367fa9;'></span><span class='bg-light-blue' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222d32;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Blue</p>");
		skins_list.append(skin_blue);
		var skin_black = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-black' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div style='box-shadow: 0 0 2px rgba(0,0,0,0.1)' class='clearfix'><span style='display:block; width: 20%; float: left; height: 7px; background: #fefefe;'></span><span style='display:block; width: 80%; float: left; height: 7px; background: #fefefe;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Black</p>");
		skins_list.append(skin_black);
		var skin_purple = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-purple' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-purple-active'></span><span class='bg-purple' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222d32;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Purple</p>");
		skins_list.append(skin_purple);
		var skin_green = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-green' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-green-active'></span><span class='bg-green' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222d32;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Green</p>");
		skins_list.append(skin_green);
		var skin_red = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-red' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-red-active'></span><span class='bg-red' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222d32;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Red</p>");
		skins_list.append(skin_red);
		var skin_yellow = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-yellow' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-yellow-active'></span><span class='bg-yellow' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #222d32;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin'>Yellow</p>");
		skins_list.append(skin_yellow);

		// Light sidebar skins
		var skin_blue_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-blue-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px; background: #367fa9;'></span><span class='bg-light-blue' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px'>Blue Light</p>");
		skins_list.append(skin_blue_light);
		var skin_black_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-black-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div style='box-shadow: 0 0 2px rgba(0,0,0,0.1)' class='clearfix'><span style='display:block; width: 20%; float: left; height: 7px; background: #fefefe;'></span><span style='display:block; width: 80%; float: left; height: 7px; background: #fefefe;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px'>Black Light</p>");
		skins_list.append(skin_black_light);
		var skin_purple_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-purple-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-purple-active'></span><span class='bg-purple' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px'>Purple Light</p>");
		skins_list.append(skin_purple_light);
		var skin_green_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-green-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-green-active'></span><span class='bg-green' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px'>Green Light</p>");
		skins_list.append(skin_green_light);
		var skin_red_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-red-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-red-active'></span><span class='bg-red' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px'>Red Light</p>");
		skins_list.append(skin_red_light);
		var skin_yellow_light = $("<li />", {
			style : "float:left; width: 33.33333%; padding: 5px;"
		})
		.append(
						"<a href='javascript:void(0);' data-skin='skin-yellow-light' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 7px;' class='bg-yellow-active'></span><span class='bg-yellow' style='display:block; width: 80%; float: left; height: 7px;'></span></div>"
								+ "<div><span style='display:block; width: 20%; float: left; height: 20px; background: #f9fafc;'></span><span style='display:block; width: 80%; float: left; height: 20px; background: #f4f5f7;'></span></div>"
								+ "</a>"
								+ "<p class='text-center no-margin' style='font-size: 12px;'>Yellow Light</p>");
		skins_list.append(skin_yellow_light);

		// $("#control-sidebar-home-tab").append(skins_list);
	}

	/**
	 * 生成修改密码modal
	 */
	function init_modal_modifyPwd() {
		var modifyPwdHtml = '<div id="modal_modifyPwd" class="modal fade modal-primary" tabindex="-1" role="dialog" aria-hidden="true">'
				+ '<div class="modal-dialog">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
				+ '<span aria-hidden="true">&times;</span>'
				+ '</button>'
				+ '<h4 class="modal-title"><span id="modal_title" class="i18n-flag" i18n-data="commonUI_modify_password">修改密码</span></h4>'
				+ '</div>'
				+ '<form class="form-horizontal" id="updatePwdForm">'
				+ '<div class="modal-body">'
				+ '<div class="form-group has-feedback">'
				+ '<label class="col-sm-4 control-label i18n-flag" i18n-data="commonUI_old_password">旧密码:</label>'
				+ '<div class="col-sm-8">'
				+ '<input id="oldPwd" class="form-control  i18n-flag" type="password"  maxlength="20" autocomplete="off" required="true"   pattern="^[0-9A-z_]{6,20}$" title=ph_change_pw>'
				+ '<span class="glyphicon glyphicon-lock form-control-feedback"></span>'
				+ '</div>'
				+ '</div>'
				+ '<div class="form-group has-feedback">'
				+ '<label class="col-sm-4 control-label i18n-flag  i18n-flag" i18n-data="commonUI_new_password">新密码:</label>'
				+ '<div class="col-sm-8">'
				+ '<input id="newPwd1" class="form-control i18n-flag" type="password"  maxlength="20" autocomplete="off" required="true"   pattern="^(?=.*[A-z])(?=.*\\d)(?=.*[#@!~%^&*_])[A-z\\d#@!~%^&_*]{8,20}$" title=modPwd>'
				+ '<span class="i18n-flag" i18n-data="modPwd">格式：8-20位，包含数字、字母、特殊字符</span>'
				+ '<span class="glyphicon glyphicon-lock form-control-feedback"></span>'
				+ '</div>'
				+ '</div>'
				+ '<div class="form-group has-feedback">'
				+ '<label class="col-sm-4 control-label i18n-flag" i18n-data="commonUI_confirm_password">确认密码:</label>'
				+ '<div class="col-sm-8">'
				+ '<input id="newPwd2" class="form-control  i18n-flag" type="password" maxlength="20" autocomplete="off"  required="true" pattern="^(?=.*[A-z])(?=.*\\d)(?=.*[#@!~%^&*_])[A-z\\d#@!~%^&_*]{8,20}$" title=modPwd>'
				+ '<span class="i18n-flag" i18n-data="modPwd">格式：8-20位，包含数字、字母、特殊字符</span>'
				+ '<span class="glyphicon glyphicon-lock form-control-feedback"></span>'
				+ '</div>'
				+ '</div>'
				+ '</div>'
				+ '<div class="modal-footer">'
				+ '<button type="submit" id="modify_save" class="btn btn-primary rkibtn_save i18n-flag" i18n-data="authority_save">保存</button>'
				+ '<button type="button" id="modify_cancel" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal" i18n-data="authority_close">关闭</button>'
				+ '</div>' + '</form>' + '</div>' + '</div>' + '</div>';

		$("body").append(modifyPwdHtml);

		$("#updatePwdForm").on(
				"submit",
				function(e) {

					// 加载层-默认风格
					layer.load();
					// var msg =
					// PAX_PLUGINS.showAlert(I('main_modify_success1'));
					e.preventDefault();
					PAX_FUNCTION.ajaxPost("/user/updatePwd", {
						"oldPwd" : $.md5($("#oldPwd").val()),
						"newPwd1" : $.md5($("#newPwd1").val()),
						"newPwd2" : $.md5($("#newPwd2").val())
					}, function(data) {
						layer.closeAll('loading');
						// PAX_OBJECT.Messenger.alert('success', $.i18n
						//     .prop('main_modify_success1'));
						PAX_OBJECT.Messenger.confirm(I("mod_pwd_relo"),
								function() {
										var form = $("<form action='" + contextPath
												+ "/user/logout.do'></form>");
										$("body").append(form);
										form.submit();
								});
						$('#modal_modifyPwd').modal('hide');
						// msg.hide();


						// PAX_PLUGINS.showAlertWithType(data.msg.replace(/\n/g,
						// "<br>"), "success");
						// window.location.href = contextPath + "/";
						// layer.alert(I("main_modify_success1"), {//
						// '密码修改成功，请重新登录'
						// skin : 'layui-layer-molv' // 样式类名
						// ,
						// closeBtn : 0
						// }, function() {
						// window.location = contextPath + "/login.jsp";
						// });
					}, function(data) {
						layer.closeAll('loading');
						// layer.alert(data.msg, {icon: "5"});
						PAX_OBJECT.Messenger.alert('error', data.msg);
					});
				});
		/*
		 * $("#modify_save").click(function(){
		 *
		 * var msg = PAX_PLUGINS.showAlert('提交中，请等待...');
		 *
		 * PAX_FUNCTION.ajaxPost(contextPath+"modifyPwdAction",{
		 * "modifyInput.oldPwd" : $("#oldPwd").val(), "modifyInput.newPwd1" :
		 * $("#newPwd1").val(), "modifyInput.newPwd2" : $("#newPwd2").val()
		 * },function(data){
		 * //PAX_PLUGINS.afterSubmit($('#modal_modifyPwd'),msg,data.msg,"success",oTable);
		 * //PAX_OBJECT.Messenger.alert("success",result.msg);
		 * $('#modal_modifyPwd').modal('hide'); msg.hide();
		 *
		 * PAX_OBJECT.Messenger.alert('密码修改成功，请重新登录', { skin: 'layui-layer-molv'
		 * //样式类名 ,closeBtn: 0 }, function(){ window.location =
		 * contextPath+"/login.jsp"; });
		 *
		 * //PAX_PLUGINS.showAlertWithType(data.msg.replace(/\n/g,"<br>"),"success");
		 * },function(data){
		 * PAX_OBJECT.Messenger.alert("error",data.msg.replace(/\n/g,"<br/>"));
		 * }); });
		 */
	}
})(jQuery, $.AdminLTE);
function checkPasswords(){
	var pwd1 = $("#newPwd1").val();
	var pwd2 = $("#newPwd2").val();
	if(pwd1!=pwd2){
		$("#newPwd1").setCustomvalidity("两次输入的密码不匹配!");
	}else{
		$("#newPwd1").setCustomvalidity("");
	}
}
function menuSearch() {
	var searchStr = $("input[name='menuname']").val();

	if (searchStr) {
		/*
		// 先将展开的收起来
		$("li.active").removeClass("active");
		// 将包含检索到的第一个菜单项展开
		$("a:contains(" + searchStr.toUpperCase() + ")").first().parent("li")
				.parent("ul").parent("li").addClass("active");
		$("a:contains(" + searchStr.toUpperCase() + ")").first().parent("li")
				.addClass("active");
		*/
		var treeObj = $("body").data("menu_tree");
		var $treeNode = $(".sidebar-menu.ztree span:contains(" + searchStr.toUpperCase() + ")").first();
		
		if($treeNode.length > 0)
		{
			var treeNode = treeObj.getNodeByTId($treeNode.attr("id").replace(/_span$/, ""));
			expandParent(treeNode);
		}
		
		function expandParent(tn){
			var parentNode = tn.getParentNode();
			if(parentNode && parentNode.level != 0)
				expandParent(parentNode);

			treeObj.expandNode(tn, true, false, true);
		}
	}
	
	
}

function keyDown(e) {
	var ev = window.event || e;

	// 13是键盘上面固定的回车键
	if (ev.keyCode == 13) {
		menuSearch();
	}
}

function changePageFn(page, menuId) {
	$("#myContent").children().remove();
	var pageId = page.replace(/\..*$/, '').replace(/\//g, '_');
	var $pageObj = $('<div id="' + pageId + '"></div>');
	$("#myContent").append($pageObj);
	var currentMenuId = $("body").data("currentMenuId", menuId);
	$.ajax({
		url:page,
		type:"get",
		dataType:"html",
		success: function(responseText, textStatus, XMLHttpRequest){
			//$pageObj.load(page, function(responseText, textStatus, XMLHttpRequest) {
			if(responseText.match(/login\.css/)){
				document.location = contextPath;
			}else if(responseText.match(/^\{"success":"false"/)){
				var data = eval("("+responseText+")");
				if(data.status == "302"){
					document.location = contextPath+data.location;
					return false;
				}
				$pageObj.empty();
				PAX_OBJECT.Messenger.alert("error", _T("你没有权限访问该页面"));
				return false;
			} else if (textStatus == "error" || textStatus == "timeout") {
				document.location.href = contextPath + "/404.html";
			} else {
                $pageObj.html(responseText).initCommonUI();
                i18nText($pageObj);
				// if(menuId==68){
                 //    $pageObj.html(responseText);
                 //    console.log($pageObj.html(responseText));
                 //    i18nText($pageObj);
				// }else{
                 //    $pageObj.html(responseText).initCommonUI();
                 //    i18nText($pageObj);
				// }
			}
		},
		error : function(error, textStatus, errorThrown) {
				PAX_FUNCTION.errorHandler(error, textStatus, errorThrown);
		}
	});
}

//function checkInput(name) {
//	return name.setCustomValidity(I("fill_this_field"));
//}

function checkSelect(name) {
	return name.setCustomValidity(I("cho_one_list"));
}

function checkFile(name) {
	return name.setCustomValidity(I("cho_one_file"));
}

function checkCancel(name) {
	return name.setCustomValidity("");
}

function upFile(name) {
	var nameTail=$(name).val().split("\\")[2];
	$(name).prev().val(nameTail);
}

/* UI 通用方法 */
// 标签选择器列表，参数名直接取标签的name属性
function getSelectorsValue(s) {
	var param = {};
	$.each(s.split(","), function(index, value) {
		if ($(value).length > 0) {
			// 去前缀
			param[$(value).attr("name").split(".").pop()] = $(value).val();
		}
	});

	return param;
}
function getSelectorsValue2(s) {
	var param = {};
	$.each(s.split(","), function(index, value) {
		var v = value.split("|");
		param[v[0]] = $(v[1]).val();
	});
	return param;
}
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
function previewImage($obj) {
	// TODO:限制最大尺寸
	var $preview = $('<div class="modal modal-primary" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">');
	$preview.append(
		'<div class="modal-dialog" style="width:800px;">'
				+ '<div class="modal-content">'
				+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="position:relative;top:-10px; right:-10px;border-radius: 50%!important;z-index:10"><span aria-hidden="true" style="font-size:22px!important;">&times;</span></button>'
				+ '<div class="modal-body" style="padding:0 0;">'
				+ '<div class="tab-pane active imgContainer" style="margin:20px;text-align: center; height:550px;text-align:center;overflow:hidden">'
				+ '<img class="img-responsive currentImg" style="display:none; height:550px;" src="'
				+ $obj.attr("src") + '"/>' + '</div>' + '</div>'
				+ '</div>' + '</div>')
		.appendTo('body').on('hide.bs.modal', function() {
			$(this).remove();
		})
		.on('show.bs.modal',function() {
			setTimeout(function() {
				var pw = $preview.find("img").parent().width();
				if ($preview.find("img").width() <= pw)
					$preview.find("img").show();
				else
					$preview.find("img").width(pw).show();
				$(".currentImg").smartZoom({
					'containerClass' : 'zoomableContainer'
				});
				function moveButtonClickHandler(e) {
					var pixelsToMoveOnX = 0;
					var pixelsToMoveOnY = 0;

					switch (e.target.id) {
						case "leftPositionMap":
							pixelsToMoveOnX = 50;
							break;
						case "rightPositionMap":
							pixelsToMoveOnX = 0;
							break;
						case "topPositionMap":
							pixelsToMoveOnY = 50;
							break;
						case "bottomPositionMap":
							pixelsToMoveOnY = 0;
							break;
					}
					$(".currentImg").smartZoom('pan', pixelsToMoveOnX, pixelsToMoveOnY);
				}
			}, 100);
		})
		.modal('show');
}

// 下拉框ajax初始化
$.fn.initSelect = function(param, initdata) {
	var $this = $(this);

	$(this).each(function(index, element) {
		$this = $(element);
		var action = $this.attr("data-action");// ajax方法
		var params = {};
	
		var p = $this.attr("data-rel");// 依赖的数据元素选择器列表，逗号分隔
		if (p != undefined) {
			params = $.extend(params, getSelectorsValue(p));
		}
	
		switch (typeof param) {
			case "object":// json参数
				params = $.extend(params, param);
				break;
			case "string":// input标签选择器列表
				// selector1,selector2....
				params = $.extend(params, getSelectorsValue(param));
			default:
				break;
		}
	
		// 初始值
		/*
		 * if($this.attr("data-refresh") == undefined &&
		 * $this.find("option:not([fixed])").length > 0){
		 * if(initdata){ var vname =
		 * $this.attr("name").split(".").pop(); if(
		 * initdata[vname] != undefined){
		 * $this.val(initdata[vname]); } return; } }
		 */
	
		// TODO:没有action属性的级联对象
		if (action != undefined) {
			var namerel = $this.attr("name-rel");
			var valuerel = $this.attr("value-rel");
	
			// 附加属性设置，用作需要绑定额外属性的情况
			// 格式参考 attrname|dataname,attrname1|dataname1...
			var attrrel = $this.attr("attr-rel");
	
			var async = $this.attr("sync") == undefined ? true : false;// 同步标识，默认异步
	
			if (namerel != undefined && valuerel != undefined) {
				// 清空多余数据
				$this.find("option:not([fixed])").remove();
				PAX_FUNCTION.ajax(action,
					params,
					"post",
					async,
					function(data) {
						var datas = data;
						// eval('(' + data+ ')');
						if (typeof datas.success != "undefined"
								&& datas.success != "true") {
							// 获取失败
							// TODO:错误处理
							var cascade = $this
									.attr("cascade-target");
							if (cascade != undefined
									&& $(cascade).length > 0) {
								$(cascade).initSelect();
							}
							return;//
						}
						// TODO：统一ajax返回的数据格式！！！
						// 这里返回的数据格式不统一，做兼容处理
						if (datas instanceof Array) {// 返回的数据直接是数组
							$.each(datas, function(index,value) {
								var attrs = "";
								if (attrrel != undefined) {
									$.each(attrrel.split(","),function(index, v) {
										v = v.split("|");
										attrs += v[0]+ "=\""+ value[v[1]]+ "\" "
									});
								}
								$this.append("<option value='"
									+ value[valuerel]
									+ "' "
									+ attrs
									+ ">"
									+ _T(value[namerel])
									+ "</option>");
								});
							} else {
								// 查找数据数组后并初始化
								$.each(datas,function(name,value) {
									if (value instanceof Array) {
										$.each(value,function(index,value) {
											var attrs = "";
											if (attrrel != undefined) {
												$.each(attrrel.split(","),function(index,v) {
													v = v.split("|");
													attrs += v[0]
														+ "=\""
														+ value[v[1]]
														+ "\" "
												});
											}
											$this.append("<option value='"
												+ value[valuerel]
												+ "' "
												+ attrs
												+ ">"
												+ _T(value[namerel])
												+ "</option>");
										});
									}
								});
							}
	
							// 初始化数据
							if (initdata) {
								try {
									var vname = $this.attr("name").split(".").pop();
									if (initdata[vname] != undefined) {
										$this.val(initdata[vname]);
									}
								} catch (e) {
									console.log("初始化ajax-select无name属性");
								}
							}
	
							// 级联对象初始化
							var cascade = $this.attr("cascade-target");
							if (cascade != undefined && $(cascade).length > 0) {
								// 存在级联对象，对父级下拉框注册change事件
								var $cascade = $(cascade);
	
								try {
									//
									$this.unbind("change.cascade.select");
								} catch (e) {
									console.log("unbind cascadeChange error:"+ $this.attr("id"));
								}
	
								{
									$this.bind("change.cascade.select",function(obj) {
										// 级联数据参数传递
										// 格式：参数名1|input标签选择器1,参数名2|input标签选择器2
										// ...
										// 标签选择器不能带","
										// TODO:扩展更多的参数传递方式
										var $obj = $(this);
										// console.log("change.cascade.select:"+
										// $obj.attr("id"));
										var param = $obj.attr("cascade-param");
										var params = {};
										if (param != undefined) {
											// 分装成JSON格式
											// TODO：通用方法提取
											$.each(param.split(","),function(index,value) {
												var v = value.split("|");
												params[v[0]] = $(v[1]).val();
											});
										}
	
										// console.log(JSON.stringify(params));
										var c = $obj.attr("cascade-target");
										if (c != undefined && $(c).length > 0) {
											$(c).initSelect(params,initdata);
										}
									})
									.trigger("change");
								}
							} else {
								$this.trigger("change");
							}
	
						});
				}
		} else {
			// 初始化数据
			if (initdata) {
				try {
					var vname = $this.attr("name").split(".").pop();
					if (initdata[vname] != undefined) {
						$this.val(initdata[vname]);
					}
				} catch (e) {
					console.log("初始化ajax-select无name属性");
				}
			}
		}
	});
	return $this;
};

// 针对table的初始化方法
$.fn.initAjaxTable = function(param) {

	var params = [];
	var data_rel = $(this).attr("data-rel");
	if (undefined != data_rel) {
		param = param ? $.extend(getSelectorsValue(data_rel), param)
				: getSelectorsValue(data_rel);
	}

	if (param) {
		// 传入的自定义参数
		$.each(param, function(name, value) {
			params.push({
				"name" : name,
				"value" : value
			});
		});
	}
	$(this).each(function(index, element) {
		var $this = $(element);
		if ($this.is("table.ajax-table[data-action]")) {
			if ($.fn.DataTable.isDataTable($this)) {
				// 刷新
				$this.dataTable().fnSettings()._iDisplayStart = 0;
				// 此处重新设置fnServerData， 以使传入的参数生效
				$this.dataTable().fnSettings().fnServerData = fnServerData;
				// console.log(JSON.stringify($this.dataTable().fnSettings()))
				$this.dataTable().fnDraw(false);
			} else {// 初始化
				var aoColumns = [];
				$this.find("thead>tr>th[data-name]").each(function(index, element) {
					aoColumns.push({
						"mDataProp" : $(element).attr(
								"data-name"),
						"sClass" : "center",
						"bSortable" : false
					});
				});
	
				var default_options = $.extend({}, PAX_OBJECT.DataTable.option, {
					"bAutoWidth" : false,
					"sPaginationType" : "full_numbers",
					"sAjaxSource" : contextPath
							+ $this
									.attr("data-action"),
					"sScrollY" : "330px",
					"sScrollX" : "100%",
					"aoColumns" : aoColumns,
					"fnServerData" : fnServerData,
					"fnDrawCallback" : function() {
						// TODO：操作过滤
					},
					"bPageRedirect" : true,// 开启跳页功能
					"bRowHighlight" : true,// 记录点击高亮
				});
	
				// 调用自定义的表格参数设置函数
				var optionfn = $this.attr("optionfn");
				var options = default_options;
	
				if (optionfn != undefined && eval("typeof " + optionfn) == "function") {
					var mOption = eval(optionfn + "($this)");
					var fnDrawCallback = mOption.fnDrawCallback;
					delete mOption["fnDrawCallback"];
	
					options = $.extend(options, mOption);
				}
				
				options.fnDrawCallback = function(oSettings) {
					var $dtobj = $("#" + oSettings.sTableId);
					var $dt = $dtobj.dataTable();
					var page_total = Math.ceil(oSettings._iRecordsDisplay/oSettings._iDisplayLength);
					// TODO:操作过滤
					// TODO:跳页多语言设置
					if (oSettings.oInit.bPageRedirect === true && page_total > 1) {
						var $pagination = $("#"+ oSettings.sTableId+ "_paginate ul.pagination");
						var $redirect = $('<li class="paginate_button redirect"><div class="input-group" style="float:left"><input type="text" maxlength="8" class="form-control input-sm" size="3"  /><span class="i18n-flag" i18n-data="page"></span></div><a href="#" style="float:left" aria-controls="'
							+ oSettings.sTableId
							+ '" data-dt-idx="'
							+ $pagination.children("li").length
							+ '" tabindex="'
							+ $pagination.find("li:last>a").attr("tabindex")
							+ '" class="i18n-flag" i18n-data="go_to_page">'+I("go_to_page")+'</a></li>').appendTo($pagination);
	
						// 输入框回车跳页
						$redirect
						.find("input:text").keydown(function(e) {
							if (e.keyCode == 13) {
								$redirect.find("a").click();
							}
						});
						//.val(Math.ceil(oSettings._iDisplayStart/oSettings._iDisplayLength) + 1);
	
						// 按钮跳页
						$redirect.find("a").click(function() {
							var r = $redirect.find("input:text").val();
							var reg = /^[0-9]+$/;
							if (r != null && r != "") {
								if (!reg.test(r)) {
									PAX_OBJECT.Messenger.alert("error", $.i18n
										.prop('page_number'));// 页码只能为数字
								} else if (r > page_total) {
									PAX_OBJECT.Messenger.alert("error", $.i18n
										.prop('ph_merchant_add_ttfiveth'));// 已超过最大页数
								} else if (r <= 0) {
									PAX_OBJECT.Messenger.alert("error", $.i18n
										.prop('ph_merchant_add_ttsixth'));// 页码需大于0
								} else {
									$dt.fnPageChange(r - 1);
								}
							} else {
								PAX_OBJECT.Messenger.alert("error", $.i18n
									.prop('ph_merchant_add_ttsevth'));// 请输入页码
							}

						});
					}
	
					// 记录选中高亮
					if (oSettings.oInit.bRowHighlight === true) {
						 $(this).find("tbody>tr").click(
							 function() {
								 $(this).addClass("tr-bg").siblings().removeClass("tr-bg");
						 });
					}
	
					// 全选check BOX 绑定动作
					var $checkall = $(oSettings.nScrollHead).find('input.checkall[type=checkbox]');
					if ($checkall.length > 0) {
						// 每次刷新重置状态
						$checkall.prop("checked", false);
						$checkall.unbind('click').bind('click',function() {
							$(oSettings.nScrollBody)
							.find('input[name=checkList][type=checkbox]')
							.prop('checked', $(this).prop('checked'));
						});
	
						$(oSettings.nScrollBody).find('input[name=checkList][type=checkbox]').click(function() {
							if (!$(this).prop('checked')) {
								$checkall.prop('checked',false);
							}
						});
					}
	
					$(oSettings.nScrollBody).find(".ajax-button").initAjaxButton();
	
					// 自定义回调
					if (fnDrawCallback != undefined) {
						fnDrawCallback(oSettings);
					}
				};
	
				$this.dataTable(options);
			}
		}
	});

	function fnServerData(sSource, aDataSet, fnCallback,oSettings) {
		var $search_form = $("form.search-form[for-table='" + this.attr('id') + "']");
		aDataSet = $.unique(aDataSet.concat(params));
		if ($search_form.length > 0) {
			var data = $search_form.serializeArray();
			$.each(data, function(index, element) {
				element.name = "search_" + element.name;
			});
			aDataSet = $.unique(aDataSet.concat(data))
		}

		PAX_FUNCTION.ajaxPost(sSource, aDataSet, function(data){
				fnCallback(data);
		},
		function(data){
			PAX_OBJECT.Messenger.alert("error", /*"加载列表["+sSource+"]："+*/data.msg);
				$(oSettings.nTable).dataTable()._fnProcessingDisplay(null, false);
				$(oSettings.nTable).dataTable()._fnClearTable();
				$(oSettings.nTBody).children().detach();
		});
	}

	return $(this);
};

// 图片预览初始化，可删除
$.fn.imgCancellable = function(action) {
	$this = $(this);
	var $delete = $this.find(".img-delete");

	if (action && typeof action == "string") {
		switch (action) {
		case "destory":
			$this.unbind("hover");
			$delete.remove();
			return;
			break;
		default:
			break;
		}
	}

	if ($delete.length == 0) {
		$('<i class="fa fa-trash-o img-delete"></i>')
			.appendTo($this)
			.click(function() {
				var $img = $(this).siblings("img.img-preview");
				var $file = $(this).parent().siblings("input:file");
				// 隐藏图片
				$img.hide().attr({
					"src" : "",
					"width" : "",
					"height" : ""
				}).siblings("span").remove();
				$img
						.after("<span class='i18n-flag' i18n-data='no_uploade'>未上传</span>");

				// 重置文件域
				if ($file.length > 0) {
					var $file_n = $file.clone(true).val("");
					$file.after($file_n);
					$file.remove();
					$file_n.trigger("blur");
				}
				// 移除删除图标
				$(this).parent().imgCancellable("destory");
			});

		$this.hover(function() {
			$(this).find(".img-delete").show();
		}, function() {
			$(this).find(".img-delete").hide();
		});
	}
};

$.fn.initAjaxButton = function() {
	$(this).each(function() {
		if (!$(this).is(".ajax-button"))
			return;

		$(this).click(function() {
			var confirm_msg = $(this).attr(
					'confirmmsg');
			var action = $(this).attr('action');
			var action_type = $(this).attr(
					'action-type');
			var param_name = $(this).attr(
					'param-name') == undefined ? 'id'
					: $(this)
							.attr('param-name');
			var param_value = $(this).attr(
					'param-value');
			var data_rel = $(this).attr(
					'data-rel');
			var table_rel = $(this).attr(
					'table-rel');
			var checkedRecords=$(table_rel).find("input[name=checkList][type=checkbox]:checked");
			if(action_type==("batchdel"||"batchaudit"||"createkey")&&checkedRecords.length==0){
                PAX_OBJECT.Messenger.alert("error", I("main_choose_record"));//没有选中任何记录
                return;
			}
			// 提示信息
			if (confirm_msg) {
				PAX_OBJECT.Messenger.confirm(confirm_msg, function() {
						do_action(action_type, action, param_name, table_rel);
					}
				);
			} else {
				do_action(action_type, action, param_name, table_rel);
			}

			function do_action(type, action, param_name, table_rel) {
				var param = {};
				if (data_rel) {
					param = $.extend(param, getSelectorsValue(data_rel));
				}
				var v = [];
				switch (type) {
					case 'batchdel':// 批量删除
						if (table_rel) {
							$(table_rel).dataTable()
								.find("input[name=checkList][type=checkbox]:checked")
								.each(function() {
									v.push($(this).val());
								});
								if (v.length == 0) {
                                    PAX_OBJECT.Messenger.alert("error", I("main_choose_record"));//没有选中任何记录
									return;
								}

								param[param_name] = v.join(",");
						}
						break;
                    case 'batchaudit':// 批量审核
                        if (table_rel) {
                            $(table_rel).dataTable()
                                .find("input[name=checkList][type=checkbox]:checked")
                                .each(function() {
                                    v.push($(this).val());
                                });
                            if (v.length == 0) {
                                PAX_OBJECT.Messenger.alert("error", I("main_choose_record"));//没有选中任何记录
                                return;
                            }

                            param[param_name] = v.join(",");
                        }
                        break;
                    case 'createkey':// 生成密钥
						if (table_rel) {
							$(table_rel).dataTable()
								.find("input[name=checkList][type=checkbox]:checked")
								.each(function() {
									v.push($(this).val());
								});
								if (v.length == 0) {
                                    PAX_OBJECT.Messenger.alert("error", I("main_choose_record"));//没有选中任何记录
									return;
								}

								param[param_name] = v.join(",");
						}
						break;
					case 'delete':// 删除
						v.push(param_value);
						param[param_name] = v.join(",");
						break;
					case 'lock':// 冻结
						param[param_name] = param_value;
						param['lock'] = 1;
						break;
					case 'unlock':// 解冻
						param[param_name] = param_value;
						param['lock'] = 0;
						break;
					case 'auditpass':// 审核通过
						param[param_name] = param_value;
						param['audit'] = 1;
						break;
					case 'auditfail':// 审核失败
						param[param_name] = param_value;
						param['audit'] = 0;
						break;
					default:
						param[param_name] = param_value;
						break;
					}

					if (action) {
						var _load = new Loading();
						_load.init();
						_load.start();
						PAX_FUNCTION.ajaxPost(action,param,
							function(data) {
								_load.stop();
								PAX_OBJECT.Messenger.alert("success", data.msg);
								if (table_rel)
									$(table_rel).dataTable().fnDraw(false)
							},
							function(data) {
								_load.stop();
								PAX_OBJECT.Messenger.alert("error",data.msg);
							}
						);
					}
				}
		});
	});
	return $(this);
};
// 子界面加载初始化
// 如果存在动态添加的页面元素，可在添加到页面后调用此方法绑定通用UI事件
// 在调用此方法时，应避免重复绑定
$.fn.initCommonUI = function() {
	var $this = $(this);

	// i18nText($this);
	// 搜索栏初始化
	$this.find('form.search-form').each(function(index, element) {
		var $this = $(this);
		


		
		// 搜索按钮事件触发
		$this.find('.search').click(function() {
			if($this.attr("before-search")){
				var fun=$this.attr("before-search").substring(0,12);
				var data=$this.attr("before-search").substring(12);
	          var flag = window[fun](data);
	          if(flag=="1"){
	        	  var $dataTable = $("#" + $this.attr('for-table'));
	        	  if ($dataTable.length > 0) {
	        		  $dataTable.initAjaxTable();
	        	  }
	          }else{
	          }
			}else{
				var $dataTable = $("#" + $this.attr('for-table'));
	        	  if ($dataTable.length > 0) {
	        		  $dataTable.initAjaxTable();
	        	  }
			}
		});

		// 重置按钮事件触发
		$this.find('.reset').click(function() {
            $(this).parents('form')[0].reset();
			$(this).parents("form").find(".dropdown-toggle").text(I("cposmer_choose"));
			// TODO：是否需要刷新表格?
			//$this.find('.search').click()
			var notReset=$(this).attr("not-reset");
			if(notReset){
				window[notReset]();
			}
		});

		// 检索表单元素按下回车键进行查询
		$(element.elements).keydown(function(e) {
			if (e.keyCode == 13) {
				e.preventDefault();
				$this.find('.search').click();
			}
		});
	});

	// ajax按钮请求公共方法
	// if($this.find('.ajax-button').length>0){
        $this.find('.ajax-button').initAjaxButton();
	// }


	// ajax模态框触发传值
	$this.on('click.bs.modal.data-api', '[data-toggle="modal"]', function(e) {
		var $target = $($(this).attr('data-target'));
		// 判断是否有数据需要传递
		if ($(this).attr("data-rel")/* key=value串 */!= undefined) {
			// 向模态框对象传值
			$target.data('data-rel', $(this).attr("data-rel"));
		} else {
			$target.data('data-rel', '');
		}

		// 传递table关联，在对话框保存的时候好自动刷新关系的表格
		if ($(this).attr("table-rel") != undefined) {
			// 向模态框对象传值
			$target.data('table-rel', $(this).attr("table-rel"));
		} else {
			$target.data('table-rel', '');
		}

		// 传递来源关联
		if ($(this).attr("source-rel") != undefined) {
			// 向模态框对象传值
			$target.data('source-rel', $(this).selector);
		} else {
			$target.data('source-rel', '');
		}
	});

	// ajax模态框自动数据填充
	$this.find("div.modal.ajax-modal")
		.each(function(index, element) {
			var $this = $(element);
			// 图片上传预览事件注册
			$this.find(".img-preview-box")
				.parent()
				.find("input:file")
				.change(function() {
					var file = $(this).val();
					var $img = $(this).parent().find("img.img-preview");
					if (file) {
						var objUrl = getObjectURL(this.files[0]);
						$img.hide()
							.removeAttr("width height")
							.attr("src", objUrl)
							.unbind("click")
							.click(function() {
								// 放大预览
								previewImage($(this));
							})
							.unbind('load')
							.load(function() {
								var w = $(this).width();
								var h = $(this).height();
								if (w > h)
									$(this).attr({
										"width" : $(this).parent().width()
									});
								else
									$(this).attr({
										"height" : $(this).parent().height()
									});
								$(this).show();
								if ($(this).parent().is(".img-cancellable")) {
									$(this).parent().imgCancellable();
								}
							})
							.siblings("span").remove();
							
						} else {
							$img.hide()
								.attr("src", "")
								.after("<span class='i18n-flag' i18n-data='no_uploade'>未上传</span>");

							if ($img.parent().is(".img-cancellable")) {
								$img.parent().imgCancellable("destory");
							}
						}
					});

					$(this).on('hide.bs.modal', function() {
						$.each($(this).find('form'), function(index, element) {
						// 隐藏时自动清除之前显示的数据
						element.reset();

						// select级联对象清空
						$(element).find("select.ajax-select[cascade-target]")
							.each(function(index,elemnet) {
								$($(this).attr("cascade-target")).find(":not([fixed])").remove();
							});
																// 清除上传预览图片
						$(element).find("img.img-preview")
							.hide()
							.attr({"src" : ""})
							.removeAttr("width height");
						$(element).find(".img-list").empty();

						// 清除验证提示信息
						$(element).Validform().resetForm();

						// 重置滚动条，如果有
						$(element).find(".modal-body").scrollTop(0);

						// 重置表单disable状态
						// TODO:其他表单元素状态重置
						$(element).find("input:text[ori-enable],texteara[ori-enable]").prop("readonly", false);
						$(element).find("input:text[ori-disable],texteara[ori-disable]").prop("readonly", true);
						$(element).find("select[ori-enable]").prop("disabled", false);
						$(element).find("select[ori-disable]").prop("disabled", true);
					})
				});

				// 调用指定的ajax方法获取数据
				$(this).on('shown.bs.modal',function() {
					var action = $(this).attr('action');
					if (action != undefined) {
						var data_rel = $(this).data('data-rel')|| {};
						var $this = $(this);

						var _load = new Loading();
						_load.init();
						_load.start();
						PAX_FUNCTION.ajaxGet(action,
							data_rel,
							function(data) {
								var data = data.data;
								fillForm($this.find('form.ajax-form'), data);
								_load.stop();
								// 延时加载图片
								$this.find("img[data-imageid]").each(function(index,element) {
									var imageid = $(element).data("imageid");
									if (imageid == "") {
										$(element).hide().attr("src", "").siblings("span").remove();
										$(element).after("<span class='i18n-flag' i18n-data='no_uploade'>未上传</span>");
									} else {
										$(element).hide().parent().addClass("img-loading");

										PAX_FUNCTION.ajaxPost("common/getImage64.do",
											{
												imageId : imageid
											},
											function(data) {
												if (data.data != undefined) {
													$(element)
														.removeAttr("width height")
														.attr("src", "data:image/png;base64,"+ data.data)
														.unbind("click")
														.click(function() {
															// 放大预览
															previewImage($(this));
														})
														.unbind('load')
														.load(function() {
															var w = $(this).width();
															var h = $(this).height();
															if (w > h)
																$(this).attr("width", $(this).parent().width());
															else
																$(this).attr("height", $(this).parent().height());
															$(this).show().parent().removeClass("img-loading");
															// 删除操作
															if ($(this).parent().is(".img-cancellable")) {
																$(this).parent().imgCancellable();
															}
														})
														.siblings("span").remove();
													} else {
														$(element).hide().attr("src","").siblings("span").remove();
														$(element).after("<span class='i18n-flag' i18n-data='no_uploade'>未上传</span>");
														if ($(element).parent().is(".img-cancellable")) {
															$(element).parent().imgCancellable("destory");
														}
													}
												},
												function(data) {
													$(element).hide().attr("src", "").siblings("span").remove();
													$(element).after("<span class='i18n-flag' i18n-data='faild_img'>获取图片失败</span>");
													if ($(element).parent().is(".img-cancellable")) {
														$(element).parent().imgCancellable("destory");
													}
												});
											}
										});
									});
								} else {
									// 初始化select选项
									// TODO:数据依赖加载
									$(this).find("select.ajax-select[rely-dialog]").each(function(index,element) {
										var $this = $(element);
										// data-refresh:
										// 该属性指定下来框在每次对话框打开的时候刷新数据
										// option[fixed]:初始化固定数据
										if ($this.attr("data-refresh") == undefined && $this.find("option:not([fixed])").length > 0) {
											// 数据已经初始化过，不需要再加载

										} else {
											$this.initSelect();
										}
									});
								}
								// TODO：初始化其他

								// 初始化表格
								$(this).find(":not(.dataTables_scrollHeadInner)>table.ajax-table[rely-dialog]")
									.each(function(index, element) {
										$(element).initAjaxTable();
									});
							})
					});

	// 通用表单提交方法
	$this.find(".ajax-submit").click(function() {
		var $this = $(this);
		var $form = $($this.parents('form')[0]);
		var $modal = $this.parents('div.modal.ajax-modal:first');

								//首先执行自定义函数
		var fnCallback = $this.attr("fnCallback");
		if(fnCallback)
			if(!window[fnCallback]($(this))) return false;
			
		// TODO:文件上传
		if ($form.length > 0) {
			// 触发数据验证
			// $($this.parents('form')[0].elements).trigger("blur");
			$($this.parents('form')[0]).find("[datatype]").trigger("blur");

			// TODO:提交操作触发验证所有表单?
			if ($form.find(".Validform_wrong:visible").length > 0) {
				return;// 有数据验证不过，直接返回
			}
			// wait...
			var _load = new Loading();
			_load.init();
			_load.start();

			// 提交ajax数据
			// TODO:提供get & post方法自定义
			$form.ajaxSubmit({
				url : contextPath + $form.attr('action'),
				type : 'post',
				dataType : 'json',
				beforeSubmit : function(data, from, o) {
					// 前缀Struts 使用，暂时保留
					var prefix = $(from).attr(
							"data-prefix");
					if (prefix != undefined) {
						// 统一添加数据前缀
						$.each(data,function(index,element) {
							if (element.name.indexOf(".") != -1) {
								var vs = element.name.split(".");
								if (prefix != vs[0])// 前缀相同，不用重复添加
									element.name = prefix + "." + element.name;
							} else {
								element.name = prefix + "." + element.name;
							}
	
						});
					}

					$.each(data, function(index, element) {
						if (element.name.indexOf(".") != -1) {
							element.name = element.name.replace(/^.*\./,'');
						}
					});

					return true;
				},
				success : function(data) {
					_load.stop();
					if(data.status == "302"){
						document.location = contextPath+"/"+data.location;
						return false;
					} else {
						// 处理成功
						if (data.success == "true") {
							if ($modal.length > 0) {
								// 如果是属于对话框的表单提交，则在提交后自动关闭对话框
								if ($modal.data('table-rel')&& $($modal.data('table-rel')).length > 0) {
									// 如果有关联的表格，则在关闭后自动刷新表格
									$($modal.data('table-rel')).dataTable().fnDraw(false);
								}
								$modal.modal("hide");
							}
							PAX_OBJECT.Messenger.alert("success",data.msg);// 保存成功
						} else {
							if(typeof data.msg == "string")
								PAX_OBJECT.Messenger.alert("error", data.msg);
							else{
							//新代码返回错误的JSON对象，回显页面提示错误
								var worng_msg = [];
								$.each(data.msg, function(name,msg){
									var worng_hint = $form.find("[name="+name+"]").parent().next(".hint-color");
									if(worng_hint.length > 0){
										worng_hint.empty()
										.html('<span class="Validform_checktip Validform_wrong">'+_T(msg)+'</span>')
									}else{
										worng_msg.push(name+":"+msg);
									}
								});
								if(worng_msg.length > 0)
									PAX_OBJECT.Messenger.alert("error", worng_msg.join("\n"));
							}
						}
												
						//自定义回调
						var callback = $form.attr("callback");
						if(callback){
							window[callback](data);

						}

                        var callbackSpecial=$form.attr("callbackSpecial");
						var callbackSpecialData=$("#cid_for_add_qr").val();
                        if(callbackSpecial){
                            // oBankstoreTable.fnDestroy();
                            // window[callbackSpecial](callbackSpecialData);
                            $("#bankstoreTable").dataTable().fnDraw(false);

                        }
					}
				},
				error : PAX_FUNCTION.errorHandler
			});
		}
	});

	// 表单验证自动注册
	/*
	 * 此方将将验证的条件注入到页面中 通过获取元素属性，自动添加Validform验证
	 */
	$this.find("form.ajax-form.submit").each(function(index, element) {
		var $this = $(element);
		var datatype = {};
			
		// 文件域更改自动验证
		$this.find("input:file").change(function() {
			$(this).blur();
		});
		// 屏蔽回车提交表单数据
		$this.keydown(function(e) {
			if (e.keyCode == 13) {
				return false;
			}
		});
		// TODO：考虑级联验证，数据之间的依赖性验证
		$.each(element.elements,function(index, element) {
			var $this = $(element);

			if ($this.is("input:file[datatype][regfile]")) {
				// TODO:考虑区分图片和普通文件的验证
				if ($this.attr("datatype").indexOf(",") != -1) {
					var vname = $this.attr("datatype").split(",").pop();// 只取最后一个
				} else if ($this.attr("datatype").indexOf("|") != -1) {
					var vname = $this.attr("datatype").split("|").pop();// 只取最后一个
				} else {
					var vname = $this.attr("datatype");
				}

				datatype[vname] = function(gets, obj, curform, regxp) {
					// TODO：优化文件大小的设置方法，比如可以字节设置3M,
					// 1K等
					var max_size = obj.attr("data-maxsize") == undefined ? (3 * 1024 * 1024): obj.attr("data-maxsize");
					var suffix = obj.attr("suffix") == undefined ? [] : obj.attr("suffix").toLowerCase().split("|");

					// 验证文件大小
					var files = obj.prop('files');
					if (files.length == 0)
						return false;
					var size = files[0].size;
					if (size > max_size) {
						obj.attr('errormsg', $.i18n.prop('upload_img_tip') + rsize(max_size));// 上传文件过大！最大:
						return false;
					}

					// 验证文件类型，后缀名
					if (suffix.length > 0) {
						var tail = gets.toLowerCase().split(".").pop();//返回文件后缀名
						if (suffix.indexOf(tail) == -1) {
							obj.attr('errormsg', $.i18n.prop('upload_file_tip') + suffix.join(','));// 该文件格式不支持！支持格式:
							return false;
						}
					}

					// TODO：其他参数验证
					return true;
				};

			} else if ($this.is("[datatype][regexp]")) {
				// 获取正则表达式匹配列表
				// 格式参考,name1|reg1,name2|reg2
				// ...
				// TODO：正则列表的格式，拆分规则优化
				
				var reg = $this.attr("regexp");
				var reg_arr = reg.match(/([^\|,]*?)\|(\/.*?\/[ig]?)+?/g);
				if (reg_arr) {
					// 拆分成二维数组
					$.each(reg_arr,function(index, element) {
						reg_arr[index] = element.match(/([^\|]+)|(\/.*?\/[ig]?)+?/g);
						if (reg_arr && reg_arr[index].length == 2)
							datatype[reg_arr[index][0]] = eval(reg_arr[index][1]);
					});
				}
			}
		});

		// 预定义的公共验证方法，可以在此处添加
		var default_datatype = {
			"tel" : /^0\d{2,3}-?\d{7,8}$/,// 座机号码
			"id" : /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/,// 身份证
			"zh2-16" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,16}$/,
			"img" : function(gets, obj, curform, regxp) {
				var max_size = obj.attr("data-maxsize") == undefined ? (3 * 1024 * 1024) : obj.attr("data-maxsize");
				var suffix = obj.attr("suffix") == undefined ? ["png", "gif", "jpg", "bmp", "jpeg"] : obj.attr("suffix").toLowerCase().split("|");

				// 验证文件大小
				var files = obj.prop('files');
				if (files.length == 0)
					return false;
				var size = files[0].size;
				if (size > max_size) {
					obj.attr('errormsg', $.i18n.prop('upload_img_tip') + rsize(max_size));
					return false;
				}

				// 验证文件类型，后缀名
				if (suffix.length > 0) {
					var tail = gets.toLowerCase().split(".").pop();
					if (suffix.indexOf(tail) == -1) {
						obj.attr('errormsg', $.i18n.prop('upload_file_tip') + suffix.join(','));
						return false;
					}
				}

				return true;
			}
		};
		datatype = $.extend({}, default_datatype, datatype);

		$this.Validform({
			tiptype : 2,
			datatype : datatype,
			showAllError : true,
			postonce : true,
			ignoreHidden : true,
			ajaxPost : true
		});
		
	});

	// 下拉框数据自动初始化
	// 具有rely-dialog属性的下拉框将在对话框打开的时候初始化，此处不处理，以加快页面初次打开的速度
	$this.find("select.ajax-select:not([rely-dialog])").each(function(index, element) {
		var $this = $(element);
		if ($this.is("select.ajax-select[rely-cascade]"))
			return;
		$this.initSelect();
	});

	// 表格数据自动初始化
	$this.find("table.ajax-table[autoload]").each(function(index, element) {
		$(element).initAjaxTable();
	});

	// 远端加载的弹出框的初始化
	$this.find("[data-toggle=modal][data-target][data-remote],[data-toggle=modal][data-target][href]")
		.each(function(index, element) {
			$($(element).attr("data-target")).on('loaded.bs.modal', function() {
				$(this).initCommonUI()
					// loaded.bs.modal事件不会再出发show.bs.modal,这里提出来单独处理
					.find("select.ajax-select[rely-dialog]:not([rely-cascade])")
					.initSelect();
			});
	});

	// input级联对象
	$this.find("input:text[cascade-target]").blur(function() {
		var $this = $(this);
		var $cascade = $($(this).attr("cascade-target"));
		if ($cascade.length > 0) {
			var action = $cascade.attr("data-action");
			if (action != undefined) {
				var param = $this.attr("cascade-param");
				if (param != undefined)
					param = getSelectorsValue2(param);
				else
					param = {};
				PAX_FUNCTION.ajaxPost(action, param, null, function(data) {
					if (data.status == "y") {
						if ($cascade.is("input:text")) {
							$cascade.val(data[$cascade.attr("data-rel")]);
						}
					} else {
						if ($cascade.is("input:text")) {
							$cascade.val("");
						}
					}
				});
			}
		}
	});

	// ajax自动填充表单
	function fillForm($obj/* 表单jquery对象 */, data/* JSON */, supper) {
		if ($obj.length == 0)
			return;
		// 遍历JAVA返回的数据对象
		$.each(data, function(name, value) {
			/*
			 * 根据数据名称，查找对应的表单元素 要求JAVA返回的数据的名称与页面上的对应表单元素的名称保持一致
			 * 建议：所有元素按照数据库设计的字段名称命名,包含下划线的字段名称可根据JAVA的需求转驼峰格式
			 */
			// d1.d2.d3 ... name="d1.d2"
			if (typeof data[name] == "object" && !(data[name] instanceof Array)) {
				var s = supper ? (supper + "." + name) : name;
				fillForm($obj, data[name], s);
				return;
			}
			var $item = $obj.find("[name='" + (supper ? (supper + "." + name) : name) + "']");
			// 兼容带前缀的表单元素
			/*
			 * $item = $item.length == 0 ? $obj.find("[name$='." +
			 * name + "']") : $item;
			 */

			if ($item.length > 0) {
				// 选择的表单元素可能存在多个，每个都需要赋值
				var value=_T(value);
				if (name == "status")
					var value = statusText(value);
				else if (name == "auditstatus")
					var value = auditText(value);
				else if (name == "builddatetime" || name == "modifydatetime" || name == "auditdatetime")
					var value = dateFormat(value);





				var pre_echo = $item.attr("pre-echo");
				if(pre_echo){
					var value = window[pre_echo]($item,value);
				}

			// add on 20170612  自定义函数放在after-echo属性里面
                var after_echo = $item.attr("after-echo");
                if(after_echo){
                    window[after_echo]($obj, $item, value);
                }


				$item.each(function(index, element) {
					var $this = $(element);
					// 根据不同的表单元素类型，可能存在不同的赋值方法
					if ($this.is('input[type=text]') || $this.is('input[type=hidden]') || $this.is('textarea')) {
						$this.val(value);
					} else if ($this.is('select')) {
						// TODO:联动数据的获取，是否存在有的下拉列表需要先通过AJAX获取数据进行初始化
						if ($this.is("select.ajax-select:not([rely-cascade])")) {
							// 级联对象的初始化由父对象发起
							$this.initSelect(null, data);
						} else {
							$this.find("option[value='" + value + "']").prop('selected', true);
						}
					} else if ($this.is("input[type=checkbox]")) {
						// checkbox value=0 未选中，其他选中
						$this.prop('checked', value == "0" ? false : true);
					} else if ($this.is("input[type=radio][value='" + value + "']")) {
						$this.prop('checked', true);
					} else if ($this.is('img')) {
						$this.attr("data-imageid", value);
					} else if ($this.is('div.img-list')) {
						// 图片列表
						$this.empty().show();
						if (value == "") {
							$this.append("<div class='img-preview-box'><span class='i18n-flag' i18n-data='no_uploade'>未上传</span></div>");
						} else {
							$.each(value.split(","), function(index, v) {
								$("<div class='img-preview-box'><img class='img-preview' src='' data-imageid='" + v + "'/></div>")
									.appendTo($this);
							});
						}
					} else {
						// TODO:其他插件赋值，包括自定义的插件，图片显示，最好是通过class来指定
						$this.text(value);
					}
				})



			}
		});
	}

	// 文件大小的可读化，size:字节
	function rsize(size) {
		var units = [ "字节", "K字节", "M字节", "G字节" ];

		while (Math.floor(size / 1024)) {
			units.splice(0, 1);
			size /= 1024;
		}
		// 保留一位小数显示
		return (size.toString().indexOf(".") == -1 ? size : size.toFixed(1)) + units[0];
	}

	return $this;
};
function convertDateTime(timeStr) {
	if (timeStr) {
		var str = timeStr.substr(0, 4) + '-' + timeStr.substr(4, 2) + '-'
			+ timeStr.substr(6, 2) + ' ' + timeStr.substr(8, 2) + ':'
			+ timeStr.substr(10, 2) + ':' + timeStr.substr(12, 2);
		return str;
	} else {
		return "";
	}
}
// 展示隐藏切换
function toggleShow(obj1,obj2,obj3) {
    $(obj1).hide();
    $(obj2).show("1",function () {
        if(!$.fn.DataTable.isDataTable($(obj3))){
            $(obj3).initAjaxTable();
        }
    });
}

function setInputValue(fileId) {
    $("#"+fileId).val(I("choose_import_file"));
}



