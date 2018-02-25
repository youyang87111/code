var PAX_PLUGINS = {};

// 统一message框样式
Messenger.options = {
	extraClasses : 'messenger-fixed messenger-theme-flat messenger-on-center',
	maxMessages : 1,
	messageDefaults : {
		hideAfter : 0
	}
}

PAX_OBJECT.DataTable.option = {
	"iDisplayLength" : 10,
	'bPaginate' : true,
	"bRetrieve" : true,
	"bDestroy" : true,
	"bProcessing" : true,
	"bServerSide" : true,
	"bFilter" : false,
	"sScrollX" : "100%",
	"sScrollY" : "300",
	"oLanguage" : {
		"sLengthMenu" : I('Pagenation_PerPage'),
		"sZeroRecords" : I('Pagenation_ZeroPage'),
		"sInfo" : I('Pagenation_DispRange'),
		"sInfoEmpty" : I('Pagenation_NonePage'),
		"sInfoFiltered" : I('Pagenation_MaxRecords'),
		"sProcessing" :"" ,
		"sSearch" : I('Pagenation_Keyword'),
		"sUrl" : "",
		"oPaginate" : {
			"sFirst" : I('Pagenation_FirstPage'),
			"sPrevious" : I('Pagenation_PrevPage'),
			"sNext" : I('Pagenation_NextPage'),
			"sLast" : I('Pagenation_LastPage')
		}
	},
	"aLengthMenu" : [ 5, 10, 20, 50, 100 ],
	"info" : true,
	"autoWidth" : false,
	"fnServerData" : function(sSource, aDataSet, fnCallback) {
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : aDataSet,
			"success" : fnCallback,
			"error" : PAX_FUNCTION.errorHandler
		});
	},
	"fnDrawCallback" : function() {
		// PAX_AUTH_FUNCTION.operationFilter();
	}
}
// Datatable加入页码跳转控件
PAX_OBJECT.DataTable.Pageno = function(tableId) {
	var pageno_html = "<li class='paginate_button redirect'>"
			+ "<div class='input-group' style='float:left;'><input id='"
			+ tableId
			+ "_pageno' type='text' maxlength='5' class='form-control' size='5'>"
			+ "</div>"
			+ "<a href='#' aria-controls='"+tableId+"' data-dt-idx='11' tabindex='0' class='i18n-flag' i18n-data='go_to_page'>"+I("go_to_page")+"</a>"
			+ "</li>";
	$("#" + tableId + "_paginate ul").append(pageno_html);
}
/**
 * 页码跳转函数
 *
 * @param {string}
 *            tableId Datatable的标签id
 * @param {number}
 *            totalpage 总页数
 * @param {function}
 *            callback 执行页码跳转的回调函数，并给该函数传入参数pageno
 */
PAX_OBJECT.DataTable.Pagego = function(tableId, totalpage, callback) {
	$("#" + tableId + "_pageno").parent().siblings("a").click(
			function() {
				var pageno = $("#" + tableId + "_pageno").val();
				var r = /^[0-9]+$/;
				if (pageno != null && pageno != "") {
					if (!r.test(pageno)) {
						PAX_OBJECT.Messenger.alert("error", $.i18n
								.prop('page_number'));// 页码只能为数字
					} else if (pageno > totalpage) {
						PAX_OBJECT.Messenger.alert("error", $.i18n
								.prop('ph_merchant_add_ttfiveth'));// 已超过最大页数
					} else if (pageno <= 0) {
						PAX_OBJECT.Messenger.alert("error", $.i18n
								.prop('ph_merchant_add_ttsixth'));// 页码需大于0
					} else {
						callback.call(this, pageno);
					}
				} else {
					PAX_OBJECT.Messenger.alert("error", $.i18n
							.prop('ph_merchant_add_ttsevth'));// 请输入页码
				}
			});
}
/*
 * PAX_OBJECT.DataTable.PagegoEnter=function(tableId,totalpage,callback,flag){
 * if(flag){ $("#"+tableId+"_pageno").keyup(function(e){ if(e.keyCode == 13)
 * PAX_OBJECT.DataTable.Pagego("dt1",totalpage,tb_search); }); }
 *  }
 */

PAX_PLUGINS.showAlert = function(msg) {
	var msg = Messenger().post({
		message : msg,
		height : '300px',
		width : '500px',
		type : 'info'
	});
	return msg;
}

PAX_PLUGINS.showAlertWithType = function(msg, type) {
	var msg = Messenger().post({
		message : msg,
		type : type,
		actions : {
			retry : {
				label : I('error_data_comfirm'),// 确定
				action : function() {
					msg.hide();
				}
			}

		}

	});

	return msg
}

PAX_PLUGINS.showAlertWithOkCancel = function(msg, url, data) {
	var msg = Messenger().post({
		message : msg,
		type : "info",
		actions : {
			retry : {
				label : I('error_data_comfirm'),
				action : function() {

					PAX_FUNCTION.ajaxPost(url, data, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "success");
						oTable.fnDraw(false);
					}, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "error");
						oTable.fnDraw(false);
					});

					msg.hide();
				}
			},
			cancel : {
				label : I('cp_cancel'),
				action : function() {
					msg.hide();
				}
			}
		}
	});

	return msg;
}

PAX_PLUGINS.showAlertWithTwoOk = function(msg, label1, url1, data1, label2,
		url2, data2) {
	var msg = Messenger().post({
		message : msg,
		type : "info",
		actions : {
			retry : {
				label : I("exp_all"),
				action : function() {
					PAX_FUNCTION.ajaxPost(url1, data1, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "success");
						// oTable.fnDraw(false);
					}, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "error");
						// oTable.fnDraw(false);
					});

					msg.hide();
				}
			},
			retry2 : {
				label : I("exp_part"),
				action : function() {
					PAX_FUNCTION.ajaxPost(url2, data2, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "success");
						oTable.fnDraw(false);
					}, function(data) {
						PAX_PLUGINS.showAlertWithType(data.msg, "error");
						oTable.fnDraw(false);
					});

					msg.hide();
				}
			},
			cancel : {
				label : I('cp_cancel'),
				action : function() {
					msg.hide();
				}
			}
		}
	});

	return msg;
}

PAX_PLUGINS.afterSubmit = function(modalObj, alertMsger, resultMsg, resultFlag,
		tableObj) {
	modalObj.modal('hide');
	alertMsger.hide();
	PAX_PLUGINS.showAlertWithType(resultMsg.replace(/\n/g, "<br>"), resultFlag);
	tableObj.fnDraw(false);
}
PAX_PLUGINS.submitSuc = function(modalObj, resultMsg, tableObj) {
	modalObj.modal('hide');
	layer.closeAll('loading');
	// layer.alert(resultMsg.replace(/\n/g,"<br>"), {icon: "1"});
	PAX_OBJECT.Messenger.alert('success', resultMsg.replace(/\n/g, "<br>"));
	tableObj.fnDraw(false);
}

PAX_PLUGINS.submitFail = function(resultMsg) {
	layer.closeAll('loading');
	// layer.alert(resultMsg.replace(/\n/g,"<br>"), {icon: "5"});
	//PAX_OBJECT.Messenger.alert('error', resultMsg.replace(/\n/g, "<br>"));
	if(isArray(resultMsg)){
        var msg = '';
        $.each(resultMsg, function(key, val){
        	msg = msg+val+"<br>";
        })
        PAX_OBJECT.Messenger.alert('error', msg);
	}else{
        PAX_OBJECT.Messenger.alert('error', resultMsg);
	}
}