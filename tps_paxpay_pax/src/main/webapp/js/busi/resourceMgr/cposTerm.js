// var oTable;
$(function () {
    $("#orgBtn").click(function () {  //查询条件加载机构树
        initOrgTreeSpecial2(1,"mOrgTree","orgBtn","organizationId_s");
    });

    $("#checkall").click(function () {//全选或者全不选
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    }); 

     


    $("#mcr_add").change(function () {
        $("#lmid_add").val('');
        window.mcr=$(this).val();
    });

    $("#lmid_add").dblclick(function(){
        var val = $("#mcr_add").val();
        if(val == null || val == undefined || val == ''){
             PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fourth"));
            return;
        }

        $('#modal_sel_cposMer').modal();
        $("#selCposMer_dt1").dataTable().fnDestroy(false);
        initTable();
    });


    //  $("#mcr_add").change(function() {
		// $("#mcr_add").val('');
    //  });
    //
    //  $("#lmid_add").dblclick(function(){
    //
    //  	var val = $("#mcr_add").val();
    // 	if(val == null || val == undefined || val == ''){
    // 		//layer.alert('请先选择商户来源', {icon: "5"});
    // 		PAX_OBJECT.Messenger.alert('error', R("choose_merch_source"));
    // 		return;
    // 	}
    // 	window.mcr = val;
    //
    //
    // 	$.ajax({
		// 	url:'selCposMer2.html',
		// 	type: "GET",
		// 	success:function(data){
		// 		var ele=$("#modal_sel_cposMer_body");
		// 			ele.html(data);
		// 			i18nText(ele);
		// 		}
		// });
    // 	$('#modal_sel_cposMer').modal();
    // });
    
	// $("#add").click(function(){
	// 	$('#addForm')[0].reset();
	// 	$('#modal_new').modal();
	// });
	
	// $("#import").click(function(){
	// 	$('#importForm')[0].reset();
	// 	$('#modal_import').modal();
	// });
    
    // $("#addForm").on("submit", function(e) {
    	//
	// 	//加载层-默认风格
	// 	layer.load();
	//
	//     e.preventDefault();
	//
	//     PAX_FUNCTION.ajax(ctx+"/cposTerm/add",$("#addForm").serialize(),
	// 		function(data){
	// 			PAX_PLUGINS.submitSuc($('#modal_new'),data.msg,oTable);
	// 		},
	// 		function(data){
	// 			PAX_PLUGINS.submitFail(data.msg);
	// 	});
	// });
	
	// $("#importForm").on("submit", function(e) {
    	//
	// 	//加载层-默认风格
	// 	layer.load();
	//
	//     e.preventDefault();
	//
	//     $.ajaxFileUpload({
	// 		url:ctx+"/cposTerm/importCposTerm?importInput.mcr="+$("#mcr_import").val(),
	// 		type:"post",
	// 		secureuri : false,//一般设置为false
	// 		fileElementId : 'file_import',//文件上传控件的id属性
     //    	dataType : 'json',//返回值类型 一般设置为json
     //    	success : function(data, status) {
     //
     //    		layer.closeAll('loading');
     //
     //    		if(data.success=="true"){
     //    			$('#modal_import').modal('hide');
	// 	    		//layer.alert(data.msg, {icon: "1"});
	// 	    		PAX_OBJECT.Messenger.alert('error', data.msg);
	// 	    	}else{
	// 	    		//layer.alert(data.msg, {icon: "5"});
	// 	    		PAX_OBJECT.Messenger.alert('error', data.msg);
	// 	    	}
	// 	    	oTable.fnDraw(false);
     //    	}
	// 	});
	// });
    
    // $("#batchDel").click(function(){
    //
    // 	var str = PAX_FUNCTION.GetCheckIdStr("cposTermTable");
    // 	if (str.length > 0) {
    // 		//PAX_PLUGINS.prompt("您确定要删除数据吗?",ctx+"/cposTerm/batchDel",{"ids":str},oTable);
    // 		PAX_OBJECT.Messenger.confirm(R("confirm_delete_data"),function(){
    // 			PAX_FUNCTION.ajax(ctx+"/cposTerm/batchDel",{"ids":str},function(data){
		// 			PAX_OBJECT.Messenger.alert("success",data.msg);
		// 			oTable.fnDraw(false);
		// 		},function(data){
		// 			PAX_OBJECT.Messenger.alert("error",data.msg);
		// 		});
    // 		})
    //
    // 	}else{
    // 		//layer.alert("没有选中任何记录", {icon: "5"});
    // 		PAX_OBJECT.Messenger.alert('error', R("main_choose_record"));
    // 	}
    //
    //
    // });
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

	PAX_FUNCTION.ajaxGet(contextPath+"/cposTerm/detail",{"id":id},function(data){

		layer.closeAll('loading');

		var odata=data.data;
		if(odata.auditstatus=='2'){
			PAX_OBJECT.Messenger.alert('error', I("main_record_have_audit"));
			return;
		}
		$("#modal_audit #mcr").html(_T(odata.mcrName));
	    $("#modal_audit #mid").html(odata.mer.mid);
	    $("#modal_audit #name").html(odata.mer.name);
	    $("#modal_audit #tid").html(odata.tid);
	    $("#modal_audit #org").html(_T(odata.orgName));
	    $("#modal_audit #statusName").html(_T(statusText(odata.status)));
	    $("#modal_audit #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_audit #buildoper").html(odata.buildoper);
	    $("#modal_audit #builddatetime").html(odata.builddatetime);

		$('#modal_audit').modal();

		$('#audPassBnt').unbind("click").bind("click",function(){//审核通过
			layer.load();

			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/audit",{"id":id,"passStatu":"1"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#cposTermTable").dataTable());
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

			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/audit",{"id":id,"passStatu":"0"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#cposTermTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});
		});
	});
}

/**
* 修改
* @param id
* @private
*/
function resetKey(id) {//重置密钥
	PAX_OBJECT.Messenger.confirm(I("sure_reset_key"),function(){
    			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/resetKey",{"id":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposTermTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function frozen(id) {//冻结
    PAX_OBJECT.Messenger.confirm(I("confirm_freeze"),function(){
    			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/frozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposTermTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function unfrozen(id) {//解冻
	PAX_OBJECT.Messenger.confirm(I("confirm_thaw"),function(){
    			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/unfrozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposTermTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function del(id) {//删除
	PAX_OBJECT.Messenger.confirm(I("main_confirm_remove"),function(){
    			PAX_FUNCTION.ajaxPost(contextPath+"/cposTerm/delete",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposTermTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}
$("#exportKey").click(function(){//导出密钥
    var checkList=$("#cposTermTable").find('input[name=checkList][type=checkbox]:checked');
    console.log(checkList.length);
    var arrList=[];
    $(checkList).each(function (index,ele) {
        arrList.push($(this).val());
    })
	console.log(arrList);
    var msg = Messenger().post({
        message : I("sure_exp"),
        type : "info",
        actions : {
            retry : {
                label : I("exp_all"),
                action : function() {
                    window.location=contextPath+"/cposTerm/exportKey";
                    msg.hide();
                }
            },
            retry2 : {
                label : I("exp_part"),
                action : function() {
                	if(arrList.length==0){
                        PAX_OBJECT.Messenger.alert('error', I("main_choose_record"));
                        return false;
					}
                    window.location=contextPath+"/cposTerm/exportKey?ids="+arrList;
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
});


