/**
 * 公共函数对象
 */
var PAX_FUNCTION = {};
/**
 * 公共参数对象
 */
var PAX_OBJECT = {
	DataTable : {}
};

// 简化i18n名称2
function I(key) {
	return $.i18n.prop(key)
}
// 数据库数据,i18n转换
function _T(key) {
	if(parseInt(key)){
		return key;
	}
	return key ? key.replace(/\[\[.*?\]\]/g, function(w) {
		return I(w.replace(/(^\[\[)|(\]\]$)/g, ""))
	}) : "";
}

var _I18N_init = false;
initI18NPage();

function initI18NPage() {
	if(true == _I18N_init) return;
	
	// TODO:Cookie禁用的情况
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
			 _I18N_init = true;
		}
	});

}

/**
 * 判断table中是否勾选了一行或多行，'是'返回true,'否'返回false
 * 
 * @param {String}
 *            table_id 所选table的id
 */
PAX_FUNCTION.TableIsChecked = function(table_id) {
	var result = false;
	var check_obj = $("#" + table_id + " tbody input[type='checkbox']");
	for (var i = 0; i < check_obj.length; i++) {
		if (check_obj[i].checked == true) {
			return true;
		}
	}
	return result;
};
/**
 * 判断table中是否只勾选了一行数据，'是'返回true,'否'返回false
 * 
 * @param {String}
 *            table_id 所选table的id
 */
PAX_FUNCTION.TableCheckOne = function(table_id) {
	var count = 0;
	var check_obj = $("#" + table_id + " tbody input[type='checkbox']");
	for (var i = 0; i < check_obj.length; i++) {
		if (check_obj[i].checked == true) {
			count++;
		}
	}
	if (count == 1) {
		return true;
	} else {
		return false;
	}
};
/**
 * 获取选中行的id集合，返回以逗号间隔的字符串
 * 
 * @param {String}
 *            table_id 所选table的id
 */
PAX_FUNCTION.GetCheckIdStr = function(table_id) {
	var str = '';
	$("#" + table_id + " tbody input[type='checkbox']:checked").each(
			function(i, o) {
				str += $(this).val();
				str += ",";
			});
	if (str.length > 0) {
		str = str.substr(0, str.length - 1);
	}

	return str;
};
/**
 * 检查某个功能是否是登录用户所拥有的
 * 
 * @param {Array}
 *            opers 登录用户拥有的某菜单下的功能权限集合
 * @param {String}
 *            tagId 某个功能的标签id，用于两者比较的字段
 */
PAX_FUNCTION.CheckOpers = function(opers, tagId) {
	var check = false;
	for (var i = 0; i < opers.length; i++) {
		if (opers[i].tagId == tagId) {
			check = true;
			break;
		}
	}
	return check;
};
/**
 * 显示登录用户所拥有的某个功能标签
 * 
 * @param {Array}
 *            opers 登录用户拥有的某菜单下的功能权限集合
 * @param {String}
 *            tagId 某个功能的标签id，若该功能是登录用户所有的，则显示该功能标签
 */
PAX_FUNCTION.ShowOpers = function(opers, tagId) {
	if (PAX_FUNCTION.CheckOpers(opers, tagId)) {
		$('#' + tagId).show();
	}
};
/**
 * 获取get请求参数对象(纯前端使用)
 * 
 */
PAX_FUNCTION.GetRequest = function() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = {};
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
		}
	}
	return theRequest;
};
/**
 * 获取常量信息，并生成下拉框列表
 * 
 * @param {String}
 *            selector 下拉框标识（选择器）
 * @param {String}
 *            url 请求路径
 * @param {Object}
 *            param 请求参数
 * @param {String}
 *            ovalue 下拉框option的value值
 * @param {String}
 *            otext 下拉框option的text值
 */
PAX_FUNCTION.serializeJson = function(frm) {
	// 将form中的值转换为键值对。
	var o = {};
	var a = $(frm).serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

/**
 * ajax共通
 * 
 * @param {String}
 *            url 请求路径
 * @param {Object}
 *            param 请求参数
 * @param {function}
 *            callback_ok 请求成功状态的回调函数
 * @param {function}
 *            callback_no 请求不成功状态的回调函数
 */
PAX_FUNCTION.ajax = function(url, param, method, async, callback_ok, callback_no) {
	
	if(-1 == url.indexOf(contextPath))
		url = contextPath+url;
		
	$.ajax({
		url :url,
		type : method,
		dataType : "json",
		data : param,
		async : async,
		beforeSend:function () {
        },
		success : function(data) {
			//可能返回的是字符串
			if(typeof data == "string"){
				data = eval("("+data+")");
			}
			if (data.success === "true") {
				if (callback_ok) {
					callback_ok.call(this, data);
				}
			} else {
				//判断返回状态
				//302用户被挤下线返回302 到登录页面
				if(data.status == "302"){
						document.location = contextPath+"/"+data.location;
						return false;
				}

				if (callback_no) {
					callback_no.call(this, data);
				}
			}
		},
		error : function(error, textStatus, errorThrown) {
				PAX_FUNCTION.errorHandler(error, textStatus, errorThrown);
		}
	});
};

PAX_FUNCTION.ajaxPost = function(url, param, callback_ok, callback_no) {//post请求
	return PAX_FUNCTION.ajax(url, param, "post", true, callback_ok, callback_no);
};

PAX_FUNCTION.ajaxGet = function(url, param, callback_ok, callback_no) {//get请求
	return PAX_FUNCTION.ajax(url, param, "get", true, callback_ok, callback_no);
};

PAX_FUNCTION.ajaxSyncPost = function(url, param, callback_ok, callback_no) {//post同步请求
	return PAX_FUNCTION.ajax(url, param, "post", false, callback_ok, callback_no);
};

PAX_FUNCTION.ajaxSyncGet = function(url, param, callback_ok, callback_no) {//get同步请求
	return PAX_FUNCTION.ajax(url, param, "get", false, callback_ok, callback_no);
};

PAX_FUNCTION.getCommons = function(selector, url, param, ovalue, otext, async, type) {//下拉框请求
	
	PAX_FUNCTION.ajax(url, param, type, async, function(data){
		var totalData = data.data;
		$(selector).html('<option value="">'+I("cposmer_choose")+'</option>');
		for (var i = 0; i < totalData.length; i++) {
			$(selector).append(
					"<option value='" + totalData[i][ovalue] + "'>"
							+ _T(totalData[i][otext]) + "</option>");
		}
	});
};

PAX_FUNCTION.getOrgsByUser = function(url, param, async) {
	var orgIds = [];
	PAX_FUNCTION.ajaxSyncPost(url, param, function(data) {
		var datas = eval('(' + data + ')');
		for (var i = 0; i < datas.length; i++) {
			orgIds.push(datas[i]);
		}
	});
	return orgIds;
};

// 获取根节点（业务情况根节点只有一个）
function getRootNode(rootNodeId){
    var treeObj = $.fn.zTree.getZTreeObj(rootNodeId);
    //返回根节点集合
    var root = treeObj.getNodesByFilter(function (node) {
        return node.level == 0;
    },true);
    return root;
}
PAX_FUNCTION.initOrgTree=function (siteId,rootNodeId,btnId,orgInputId) {//加载机构树
    // PAX_FUNCTION.ajaxPost(contextPath+"/organization/list.do",{"site_id":siteId},function(result){
    PAX_FUNCTION.ajaxPost(contextPath+"/common/listOrgTreeByUser",{"site_id":siteId},function(result){
        var zNodes = result.data;
        $.each(zNodes,function (index,ele) {
             ele.name=_T(ele.name);
        });
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: orgTreeClick
            }
        };
        $.fn.zTree.init($("#"+rootNodeId), setting, zNodes);
        // 机构树点击事件
        function orgTreeClick(event, treeId, treeNode){
            if(treeId===rootNodeId){
                $("#"+orgInputId).val(treeNode.orgId);
                $("#"+btnId).text(_T(treeNode.name));
                $("#"+btnId).attr("title",_T(treeNode.name));
                $("#"+orgInputId).attr("orderno",treeNode.orderno).trigger("blur");
            }
        }
        // 默认点击根节点
            orgTreeClick(null,null,getRootNode(rootNodeId));

    });
};

$.extend(PAX_OBJECT, {
	Messenger : {
		/**
		 * 实现基于bootstrap的alert
		 * 
		 * @param {String}
		 *            type 标示alert框的样式,如info,success,error等
		 * @param {String}
		 *            message 具体显示的消息内容
		 */
		 
		alert : function(type, message) {
			var alert = Messenger().post({
				message : message,
				type : type,
				actions : {
					retry : {
						label : I("error_data_comfirm"),// "确定"
						action : function() {
							alert.hide();
						}
					}
				}
			});
		},
		/**
		 * 实现基于bootstrap的confirm
		 * 
		 * @param {String}
		 *            message 具体显示的消息内容
		 * @param {function}
		 *            callback 点击确定时的处理函数
		 */
		confirm : function(message, callback) {
			var confirm = Messenger().post({
				message : message,
				type : "info",
				actions : {
					retry : {
						label : I("error_data_comfirm"),// "确定"
						action : function() {
							callback.call(this);
							confirm.hide();
						}
					},
					cancel : {
						label :I("cp_cancel"),// "取消"
						action : function() {
							confirm.hide();
						}
					}
				}

			});
		},

        confirm2 : function(message,callback,callback2){
            var confirm = Messenger().post({
                message : message,
                type : "info",
                actions : {
                    retry : {
                        label : I('cp_pass'),
                        action : function(){
                            callback.call(this);
                            confirm.hide();
                        }
                    },
                    retry2 : {
                        label : I('cp_no_pass'),
                        action : function(){
                            callback2.call(this);
                            confirm.hide();
                        }
                    },
                    cancel : {
                        label : I('cp_cancel'),
                        action : function(){
                            confirm.hide();
                        }
                    }
                }

            });
        },
        confirm3 : function(message, callback) {
            var confirm = Messenger().post({
                message : message,
                type : "alert",
                actions : {
                    retry : {
                        label : "确定",// "确定"
                        action : function() {
                            callback.call(this);
                            confirm.hide();
                        }
                    },
                    cancel : {
                        label :"取消",// "取消"
                        action : function() {
                            confirm.hide();
                        }
                    }
                }

            });
        },
	}
});


PAX_FUNCTION.errorHandler = function(error, textStatus, errorThrown) {
	if(error.status == 200){
		window.location.href = contextPath + '/';
		return false;
	}
	var inx_session_timeout = error.responseText.indexOf('session-timeout');
	var inx_autherror = error.responseText.indexOf('autherror');

	if (inx_session_timeout != -1) {
		window.location.href = contextPath + '/login.jsp?type=1';
	}

	if (inx_autherror != -1) {
		window.location.href = contextPath + '/login.jsp?type=2';
	}
};
// 判断登录出错或超时*************
PAX_FUNCTION.LoginError = function() {
	var param = PAX_FUNCTION.GetRequest();
};

// 审核冻结与启用
function auditStatus(status) {
	return (status == 1) || (status == 0) ? 'oper_unlock' : 'oper_lock';
}

function auditLock(status) {
	return (status == 1) || (status == 0) ? 0 : 1;
}

function statusNode(status) {
	var s = [ I("main_in_use"), I("main_in_use"), I("main_freeze") ];
	return s[status];
}

function statusText(status) {//启用状态 0未启用 1冻结 2启用 3注销
	var s = [ I("main_none_use"), I("main_freeze"), I("main_in_use"), I("main_cancle")];
	return s[status];
}
function activeText(status) {//激活状态 0未激活 1激活失败 2激活成功
    var s = [ I("not_active"), I("active_fail"), I("active_success")];
    return s[status];
}

function auditText(status){//审核状态 0审核中 1审核不通过 2审核通过 3重新审核
	var s = [ I("main_auditing"), I("cp_checking_no_pass"), I("cp_check_pass"), I("main_audit_again") ];
	return s[status];
}

function auditColor(status){//审核通过为绿色，否则为红色
	return status!=2?'red':'green';
}

function decimal(s,d,div){
	return isNaN(Number(s))?(0).toFixed(d):(div?Number(s)/div:Number(s)).toFixed(d);
}

function paddingZero(s, n){
	return (Array(n).join(0) + s).slice(-n);
}

function isArray(o){//判断是否为数组
    return Object.prototype.toString.call(o)=='[object Array]';
}

function dateFormat(d){//时间转换
	return d.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/, '$1-$2-$3 $4:$5:$6');
}
function initOrgTreeSpecial (siteId,rootNodeId,btnId,orgInputId) {//加载机构树
    PAX_FUNCTION.ajaxPost(contextPath+"/organization/list.do",{"site_id":siteId},function(result){
		var zNodes = result.data;
        $.each(zNodes,function (index,ele) {
            return ele.name=_T(ele.name);
        });
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: orgTreeClick,
				onNodeCreated: function(e,treeId,treeNode){
					$("#"+treeNode.tId+"_switch").remove();
					$("#"+treeNode.tId+"_a.level0").hide();
				}
			}
		};
		$.fn.zTree.init($("#"+rootNodeId), setting, zNodes);
		//var treeObj = $.fn.zTree.getZTreeObj("root_org_customer_add_ul");
		//var nodes = treeObj.getNodes();
		//var node = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
		//treeObj.hideNodes(nodes[0].children);
		// 机构树点击事件
		function orgTreeClick(event, treeId, treeNode){
			if(treeId===rootNodeId){
				$("#"+orgInputId).val(treeNode.orgId);
				$("#"+btnId).text(_T(treeNode.name));
			}
		}

	});
}
function initOrgTreeSpecial2 (siteId,rootNodeId,btnId,orgInputId) {//加载机构树
    PAX_FUNCTION.ajaxPost(contextPath+"/organization/list",{"site_id":siteId},function(result){
		var zNodes = result.data;
        $.each(zNodes,function (index,ele) {
            return ele.name=_T(ele.name);
        });
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: orgTreeClick,
				onNodeCreated: function(e,treeId,treeNode){
					$("#"+treeNode.tId+"_switch").remove();
				//	$("#"+treeNode.tId+"_a.level0").hide();
				}
			}
		};
		$.fn.zTree.init($("#"+rootNodeId), setting, zNodes);
		//var treeObj = $.fn.zTree.getZTreeObj("root_org_customer_add_ul");
		//var nodes = treeObj.getNodes();
		//var node = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
		//treeObj.hideNodes(nodes[0].children);
		// 机构树点击事件
		function orgTreeClick(event, treeId, treeNode){
			if(treeId===rootNodeId){
				$("#"+orgInputId).val(treeNode.orgId);
				$("#"+btnId).text(_T(treeNode.name));
			}
		}

	});
}


// 时间戳装换成时间
function add0(m){return m<10?'0'+m:m }
function format(time)
{
//time是整数，否则要parseInt转换
    var time = new Date(time*1000);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function formatYes()
{
//time是整数，否则要parseInt转换
    var time = new Date();
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d);
}




// 自动赋值后的回调，有值展示，没值隐藏
function optionShow($form, $item, value) {
    if(value){
        $($item).parents(".option-show").show();
	}else{
        $($item).parents(".option-show").hide();
	}
}

function beforeSearch(data){
	var sel1=$("#"+data.split(',')[0].replace('(','').replace(/'/g,'')).val().replace(/-/g,'');
	var sel2=$("#"+data.split(',')[1].replace(/'/g,'')).val().replace(/-/g,'');
	var sel3=$("#"+data.split(',')[2].replace(/'/g,'')).val().replace(/-/g,'');
	var sel4=$("#"+data.split(',')[3].replace(')','').replace(/'/g,'')).val().replace(/-/g,'');
	console.log(sel1);
	console.log(sel2);
	console.log(parseFloat(sel3));
	console.log(parseFloat(sel4));
	if(sel1==""||sel2==""){
		PAX_OBJECT.Messenger.alert("error", I("choose_date"));
		return false;
	}
	if(sel1>sel2){
    	PAX_OBJECT.Messenger.alert('error', I("begin_end"));
    	return false;
    }
	if(parseFloat(sel3)>parseFloat(sel4)){
		PAX_OBJECT.Messenger.alert('error', I("start_less_end"));
		return false;
	}
	return "1";
}

