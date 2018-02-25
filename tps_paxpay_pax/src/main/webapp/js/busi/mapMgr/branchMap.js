var oTable;
$(function () {

    //Enable check and uncheck all functionality
    $("#checkall").click(function () {//全选或者全不选
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    });

    $("#updateForm").Validform({//修改表单校验
        tiptype: 2,
        datatype: {

        },
        showAllError: true,
        postonce: true,
        ignoreHidden: true,
    });
    


    $("#updateForm").on("submit", function(e) {//提交修改表单
        var load = new Loading();
        load.init();
        load.start();
        $("#updateForm input").trigger("blur");
        $("#updateForm select").trigger("blur");
        if($("#updateForm .Validform_wrong:visible").length>0){
            load.stop();
            PAX_OBJECT.Messenger.alert("error", I('main_improve_info'));//请完善信息再提交
            return;
        }
        //加载层-默认风格
        e.preventDefault();
        PAX_FUNCTION.ajaxPost(contextPath+"/branchMap/update",$("#updateForm").serialize(),
            function(data){
                load.stop();
                PAX_PLUGINS.submitSuc($('#modal_update'),data.msg,$("#branchMapTable").dataTable());
            },
            function(data){
                load.stop();
                PAX_PLUGINS.submitFail(data.msg);
            });
    });

	 
    
     
     $("#lmid_add").dblclick(function(){//双击弹出接入渠道弹出框
     	
     	var val = $("#lbid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fourth"));
    		return;
    	}

    	$('#modal_sel_cposMer').modal();
    	$("#selCposMer_dt1").dataTable().fnDestroy(false);
         initCposTable();
    });
     
    $("#merPersonalized_add").change(function() {//是否个性化
    	var val = $("#merPersonalized_add").val();
		if(val){
			if(val=='1'){
				$("#lmid_add").val('');
				$("#lmid_add_div").hide();
			}else if(val=='2'){
				$("#lmid_add_div").show();
			}
		}
     });
     
     $("#bmf_add").change(function() {//映射模式切换
    	var val = $("#bmf_add").val();
		if(val){
			if(val=='01'){
				$("#o2o_add_div").show();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
				$("#issuer_add_div").hide();
			}else if(val=='02'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").show();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
				$("#issuer_add_div").hide();
			}else if(val=='03'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").show();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
				$("#issuer_add_div").hide();
			}else if(val=='04'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").show();
				$("#issuer_cardtype_add_div").hide();
				$("#issuer_add_div").hide();
			}else if(val=='05'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").show();
				$("#issuer_add_div").hide();
			}else if(val=='06'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
				$("#issuer_add_div").show();
			}
		}else{
			$("#o2o_add_div").hide();
			$("#cardtype_add_div").hide();
			$("#amt_add_div").hide();
			$("#cardtype_amt_add_div").hide();
			$("#issuer_cardtype_add_div").hide();
			$("#issuer_add_div").hide();
		}
     });
    
 });

function audit(id) {//审核
	layer.load();
	PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/detail",{"id":id},function(data){
		layer.closeAll('loading');
		var odata=data.data;
		if(odata.auditstatus=='2'){
			PAX_OBJECT.Messenger.alert('error', I("main_record_have_audit"));
			return;
		}
		var val = data.data.bmf;
		if(val){
			if(val=='01'){
				$("#cardType_audit_div").hide();
				$("#amt1_audit_div").hide();
				$("#amt2_audit_div").hide();
				$("#issuerid_audit_div").hide();
			}else if(val=='02'){
				$("#cardType_audit_div").show();
				$("#amt1_audit_div").hide();
				$("#amt2_audit_div").hide();
				$("#issuerid_audit_div").hide();
			}else if(val=='03'){
				$("#cardType_audit_div").hide();
				$("#amt1_audit_div").show();
				$("#amt2_audit_div").show();
				$("#issuerid_audit_div").hide();
			}else if(val=='04'){
				$("#cardType_audit_div").show();
				$("#amt1_audit_div").show();
				$("#amt2_audit_div").show();
				$("#issuerid_audit_div").hide();
			}else if(val=='06'){
				$("#cardType_audit_div").hide();
				$("#amt1_audit_div").hide();
				$("#amt2_audit_div").hide();
				$("#issuerid_audit_div").show();
			}else if(val=='05'){
				$("#cardType_audit_div").show();
				$("#amt1_audit_div").hide();
				$("#amt2_audit_div").hide();
				$("#issuerid_audit_div").show();
			}else if(val=='07'){
				$("#cardType_audit_div").hide();
				$("#amt1_audit_div").hide();
				$("#amt2_audit_div").hide();
				$("#issuerid_detail_div").hide();
			}
		}

        if(odata.lmid){
            $("#modal_audit #lmidBox").show();
            $("#modal_audit #lmid").html(odata.lmid);
        }else{
            $("#modal_audit #lmidBox").hide();
        }

		$("#modal_audit #lbid").html(_T(odata.lbid_name));
	    $("#modal_audit #cls").html(_T(odata.cls_name));
	    $("#modal_audit #bmf").html(_T(odata.bmf_name));
	    $("#modal_audit #rbid").html(_T(odata.rbid_name));
	    $("#modal_audit #issuerid").html(_T(odata.issuerid_name));
	    $("#modal_audit #cardType").html(_T(odata.cardtype_name));
	    $("#modal_audit #amt1").html(odata.amt1);
	    $("#modal_audit #amt2").html(odata.amt2);
	    // $("#modal_audit #lmid").html(odata.lmid);


	    $("#modal_audit #statusName").html(_T(statusText(odata.status)));
	    $("#modal_audit #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_audit #buildoper").html(odata.buildoper);
	    $("#modal_audit #builddatetime").html(odata.builddatetime);

		$('#modal_audit').modal();

		$('#audPassBnt').unbind("click").bind("click",function(){
			layer.load();

			PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/audit",{"ids":id,"passStatu":"1"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#branchMapTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});
		});

		$('#audNotPassBnt').unbind("click").bind("click",function(){
			if(odata.auditstatus=='1'){
				PAX_OBJECT.Messenger.alert('error',  I("main_record_have_no_audit"));
				return;
			}
			layer.load();

			PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/audit",{"ids":id,"passStatu":"0"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#branchMapTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});
		});
	},null);
}

/**
* 修改
* @param id
* @private
*/
function update(id) {//弹出修改窗口弹出框
	
	layer.load();
	
	PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/detail",{"id":id},function(data){
		
			layer.closeAll('loading');
		
			var odata=data.data;
			if(odata.status=='2'){
				PAX_OBJECT.Messenger.alert('error', I("record_on_use"));
				return;
			}
			var val = data.data.bmf;
			if(val){
				if(val=='01'){
					$("#cardType_update_div").hide();
					$("#amt1_update_div").hide();
					$("#amt2_update_div").hide();
					$("#issuerid_update_div").hide();
				}else if(val=='02'){
					$("#cardType_update_div").show();
					$("#amt1_update_div").hide();
					$("#amt2_update_div").hide();
					$("#issuerid_update_div").hide();
				}else if(val=='03'){
					$("#cardType_update_div").hide();
					$("#amt1_update_div").show();
					$("#amt2_update_div").show();
					$("#issuerid_update_div").hide();
				}else if(val=='04'){
					$("#cardType_update_div").show();
					$("#amt1_update_div").show();
					$("#amt2_update_div").show();
					$("#issuerid_update_div").hide();
				}else if(val=='06'){
					$("#cardType_update_div").hide();
					$("#amt1_update_div").hide();
					$("#amt2_update_div").hide();
					$("#issuerid_update_div").show();
				}else if(val=='05'){
					$("#cardType_update_div").show();
					$("#amt1_update_div").hide();
					$("#amt2_update_div").hide();
					$("#issuerid_update_div").show();
				}else if(val=='07'){
					$("#cardType_update_div").hide();
					$("#amt1_update_div").hide();
					$("#amt2_update_div").hide();
					$("#issuerid_detail_div").hide();
				}
			}

			if(odata.lmid){
                $("#modal_update #lmidBox").show();
                $("#modal_update #lmid").html(odata.lmid);
			}else{
                $("#modal_update #lmidBox").hide();
			}
			
			$("#modal_update #id_update").val(odata.id);
			$("#modal_update #lbid").html(_T(odata.lbid_name));
		    $("#modal_update #cls").html(_T(odata.cls_name));
		    $("#modal_update #bmf").html(_T(odata.bmf_name));
		    $("#modal_update #rbid_update").val(odata.rbid);
		    $("#modal_update #issuerid").html(_T(odata.issuerid_name));
		    $("#modal_update #cardType").html(_T(odata.cardtype_name));
		    $("#modal_update #amt1").html(odata.amt1);
		    $("#modal_update #amt2").html(odata.amt2);

		    $("#modal_update #buildoper").html(odata.buildoper);
	    	$("#modal_update #builddatetime").html(odata.builddatetime);
		    $('#modal_update').modal();
	},null);
}

function frozen(id) {//冻结
	PAX_OBJECT.Messenger.confirm(I("confirm_freeze"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/frozen",{"ids":id},function(data){
                    if(data.success=="true"){
                        PAX_OBJECT.Messenger.alert("success",data.msg);
                        $("#branchMapTable").dataTable().fnDraw(false);
                    }else{
                        PAX_OBJECT.Messenger.alert("error",data.msg);
                    }
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		},null)
}

function unfrozen(id) {//解冻
	PAX_OBJECT.Messenger.confirm(I("confirm_thaw"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/unfrozen",{"ids":id},function(data){
    				if(data.success=="true"){
                        PAX_OBJECT.Messenger.alert("success",data.msg);
                        $("#branchMapTable").dataTable().fnDraw(false);
					}else{
                        PAX_OBJECT.Messenger.alert("error",data.msg);
					}

				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				},null);
    		})
}

function del(id) {//删除
	PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/branchMap/delete",{"ids":id},function(data){
                    if(data.success=="true"){
                        PAX_OBJECT.Messenger.alert("success",data.msg);
                        $("#branchMapTable").dataTable().fnDraw(false);
                    }else{
                        PAX_OBJECT.Messenger.alert("error",data.msg);
                    }
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		},null)
}
PAX_FUNCTION.ajaxGet(contextPath+"/common/listBranch",{type:"C"},function (resdata) {//获取接入渠道下拉框
    var data=resdata.data;
    if(resdata.success=="true"){
        for(var i = 0; i < data.length; i++){
            $("#lbid_add").append('<option value="' + data[i]["id"] + '" mcr="' + data[i]["mcr"] + '">' + _T(data[i]['name']) + '</option>');
        }
    }
}, null);
$("#lbid_add").change(function () {//接入渠道change时，mcr重新赋值，弹出接入渠道弹出框时需要
    $("#lmid_add").val('');
    window.mcr_lmid=$(this).find("option:selected").attr("mcr");
});

$("#add").click(function(){
	$("#lmid_add_div").hide();
})


