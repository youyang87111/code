$(function () {

    //Enable check and uncheck all functionality
    $("#checkall").click(function () {//全选或者全不选
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    }); 

    PAX_FUNCTION.ajaxGet(contextPath+"/common/listBranch",{type:"C"},function (resdata) {//获取接入渠道下拉框
        var data=resdata.data;
        if(resdata.success=="true"){
            for(var i = 0; i < data.length; i++){
                $("#lbid_add").append('<option value="' + data[i]["id"] + '" mcr="' + data[i]["mcr"] + '">' + _T(data[i]['name']) + '</option>');
            }
        }
    },null)
    
     PAX_FUNCTION.ajaxGet(contextPath+"/common/listBranch",{type:"R"},function (resdata) {
        var data=resdata.data;
        if(resdata.success=="true"){
            for(var i = 0; i < data.length; i++){
                $("#rbid_add").append('<option value="' + data[i]["id"] + '" mcr="' + data[i]["mcr"] + '">' + _T(data[i]['name']) + '</option>');
            }
        }
    },null)


    $("#lbid_add").change(function() {//接入渠道change时，mcr更新
		$("#lmid_add").val('');
		$("#ltid_add").val('');
        window.mcr_lmid=$(this).find("option:selected").attr("mcr");
     });
     
     $("#lmid_add").change(function() {
		$("#ltid_add").val('');
     }); 
    
    $("#lmid_add").dblclick(function(){//接入商户号
    	
    	var val = $("#lbid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fourth"));
    		return;
    	}
    	$('#modal_sel_cposMer').modal();
        $("#selCposMer_dt1").dataTable().fnDestroy(false);
        initCposTable();
    });
    
    $("#rbid_add").change(function() {//转出渠道号
		$("#rmid_add").val('');
		$("#rtid_add").val('');
     });
     
    $("#rmid_add").change(function() {
		$("#rtid_add").val('');
     });  
    	
	$("#rmid_add").dblclick(function(){//转出商户号
		
		var val = $("#rbid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fifth"));
    		return;
    	}
    	window.mcr = $("#rbid_add").find("option:selected").attr("mcr");
		$("#cur_id_of_rmid").val($(this).attr("id"));
		$('#modal_rMer_sel').modal();
        $("#sel_rmer_dt1").dataTable().fnDestroy(false);
        initRmerTable();
	});
	
	$("#ltid_add").dblclick(function(){//接入终端号
		var mcr = $("#lbid_add").find("option:selected").attr("mcr");
		window.mcr = mcr;
		var val = $("#lmid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fourth1"));
    		return;
    	}
    	window.mid = val;
		$("#cur_id_of_sel").val($(this).attr("id"));
		$('#modal_sel_lterm').modal();
        $("#sel_lterm_dt1").dataTable().fnDestroy(false);
        initLTermTable();
	});
	
	$("#rtid_add").dblclick(function(){//转出终端号
		
//		var mcr = $("#rbid_add").val();
//		window.mcr = mcr;
		window.mcr = $("#rbid_add").find("option:selected").attr("mcr");
		var val = $("#rmid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fifth2"));
    		return;
    	}
    	window.mid = val;
		$("#cur_id_of_sel").val($(this).attr("id"));
		$('#modal_sel').modal();
        $("#sel_rterm_dt1").dataTable().fnDestroy(false);
        console.log("mid:"+window.mid);
        console.log("mcr:"+window.mcr);
        initRTermTable();
        // $("#sel_rterm_dt1").dataTable().fnDraw(false);
	});

    $("#batchDel").click(function(){
    	
    	var str = PAX_FUNCTION.GetCheckIdStr("termMapTable");
    	if (str.length > 0) {
    		PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
    			PAX_FUNCTION.ajax(contextPath+"/termMap/batchDel",{"ids":str},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
    	}else{
    		//layer.alert("没有选中任何记录", {icon: "5"});
    		PAX_OBJECT.Messenger.alert('error', I("main_choose_record"));
    	}
    });

     
     $("#tmf_add").change(function() {
    	var val = $("#tmf_add").val();
		if(val){
			
			if(val=='01'){
				$("#ltid_add_div").show();
				$("#rtid_add_div").show();
			}else{
				$("#ltid_add_div").hide();
				$("#rtid_add_div").hide();
			}
		}else{
			$("#ltid_add_div").hide();
			$("#rtid_add_div").hide();
		}
     });
    
 });

/**
* 表格初始化
* @returns {*|jQuery}
*/

 
/**
* 查看
* @param id
* @private
*/


function audit(id) {//审核
	layer.load();
	PAX_FUNCTION.ajaxGet(contextPath+"/termMap/detail",{"id":id},function(data){
		layer.closeAll('loading');
		var odata=data.data;
		if(odata.auditstatus=='2'){
			PAX_OBJECT.Messenger.alert('error', I("main_record_have_audit"));
			return;
		}
		
		var val = data.data.tmf;
		if(val){
			if(val=='01'){
				$("#ltid_audit_div").show();
				$("#rtid_audit_div").show();
			}else if(val=='06'){
				$("#ltid_audit_div").hide();
				$("#rtid_audit_div").hide();
			}
		}
		
		$("#modal_audit #lbid").html(_T(odata.lbid_name));
		$("#modal_audit #lmid").html(odata.lmid);
	    $("#modal_audit #tmf").html(_T(odata.tmf_name));
	    $("#modal_audit #rbid").html(_T(odata.rbid_name));
	    $("#modal_audit #rmid").html(odata.rmid);
	    $("#modal_audit #ltid").html(odata.ltid);
	    $("#modal_audit #rtid").html(odata.rtid);
	    $("#modal_audit #statusName").html(_T(statusText(odata.status)));
	    $("#modal_audit #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_audit #buildoper").html(odata.buildoper);
	    $("#modal_audit #builddatetime").html(odata.builddatetime);
	    $("#modal_audit #rmid").html(odata.rmid);
	    
		$('#modal_audit').modal();
		
		$('#audPassBnt').unbind("click").bind("click",function(){//审核通过
			layer.load();
			PAX_FUNCTION.ajaxGet(contextPath+"/termMap/audit",{"ids":id,"passStatu":"1"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#termMapTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});	
		});
		
		$('#audNotPassBnt').unbind("click").bind("click",function(){//审核不通过
			if(odata.auditstatus=='1'){
				PAX_OBJECT.Messenger.alert('error', I("main_record_have_no_audit"));
				return;
			}
			layer.load();
				
			PAX_FUNCTION.ajaxGet(contextPath+"/termMap/audit",{"ids":id,"passStatu":"0"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#termMapTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});	
		});
	},null);
}

function frozen(id) {//冻结
	 PAX_OBJECT.Messenger.confirm(I("confirm_freeze"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/termMap/frozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function unfrozen(id) {//解冻
	PAX_OBJECT.Messenger.confirm(I("confirm_thaw"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/termMap/unfrozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function del(id) {//删除
	PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/termMap/delete",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
};


