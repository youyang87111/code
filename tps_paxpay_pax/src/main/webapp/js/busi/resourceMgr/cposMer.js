var oTable;
$(function () {
    $("#cposMerOrgBtn").click(function () {  //查询条件加载机构树
        initOrgTreeSpecial2(1,"cposMerOrgTree","cposMerOrgBtn","cposMerOrgId");
    });


    $("#checkall").click(function () {//全选，或者全不选
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    }); 
    

    $("#updateForm").Validform({//修改表单验证
        tiptype: 2,
        datatype: {

        },
        showAllError: true,
        postonce: true,
        ignoreHidden: true,
    });

    $("#updateForm").on("submit", function(e) {//修改表单提交
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
        PAX_FUNCTION.ajaxPost(contextPath+"/cposMer/update",$("#updateForm").serialize(),
            function(data){
                load.stop();
                PAX_PLUGINS.submitSuc($('#modal_update'),data.msg,$("#cposMerTable").dataTable());
            },
            function(data){
                load.stop();
                PAX_PLUGINS.submitFail(data.msg);
            });
    });
	
	// $("#importForm").on("submit", function(e) {
    	//
	// 	//加载层-默认风格
	// 	layer.load();
	//
	//     e.preventDefault();
	//
	//     $.ajaxFileUpload({
	// 		url:ctx+"/cposMer/importCposMer?importInput.mcr="+$("#mcr_import").val(),
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
	// 	    		PAX_OBJECT.Messenger.alert('success', data.msg);
	// 	    	}else{
	// 	    		//layer.alert(data.msg, {icon: "5"});
	// 	    		PAX_OBJECT.Messenger.alert('error', data.msg);
	// 	    	}
	// 	    	oTable.fnDraw(false);
     //    	}
	// 	});
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

/**
 * 审核
 * @param id
 * @private
 */
function audit(id) {//审核

	layer.load();

	PAX_FUNCTION.ajaxGet(contextPath+"/cposMer/detail",{"id":id},function(data){

		layer.closeAll('loading');

		var odata=data.data;
		if(odata.auditstatus=='2'){
			//layer.alert("该记录已经是审核通过状态", {icon: "5"});
			PAX_OBJECT.Messenger.alert('error', I("main_record_have_audit"));
			return;
		}
		$("#modal_audit #mcr").html(_T(odata.mcrName));
	    $("#modal_audit #mid").html(odata.mid);
	    $("#modal_audit #name").html(_T(odata.name));
	    $("#modal_audit #org").html(_T(odata.orgName));
	    $("#modal_audit #statusName").html(_T(statusText(odata.status)));
	    $("#modal_audit #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_audit #buildoper").html(odata.buildoper);
	    $("#modal_audit #builddatetime").html(odata.builddatetime);

		$('#modal_audit').modal();

		$('#audPassBnt').unbind("click").bind("click",function(){

			layer.load();
			PAX_FUNCTION.ajaxPost(contextPath+"/cposMer/audit",{"id":id,"passStatu":"1"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#cposMerTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});
		});

		$('#audNotPassBnt').unbind("click").bind("click",function(){
			if(odata.auditstatus=='1'){
				PAX_OBJECT.Messenger.alert('error', I("main_record_have_no_audit"));
				return;
			}
			layer.load();

			PAX_FUNCTION.ajaxPost(contextPath+"/cposMer/audit",{"id":id,"passStatu":"0"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#cposMerTable").dataTable());
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
function update(id) {//弹出修改弹出框

	layer.load();

	PAX_FUNCTION.ajaxGet(contextPath+"/cposMer/detail",{"id":id},function(data){

			layer.closeAll('loading');

			var odata=data.data;
			if(odata.status=='2'){
				PAX_OBJECT.Messenger.alert('error', I("record_on_use"));
				return;
			}
	    	$("#modal_update #id").val(id);
	    	$("#modal_update #mcr").html(_T(odata.mcrName));
		    $("#modal_update #mid").html(odata.mid);
		    $("#modal_update #name").val(_T(odata.name));
		    $("#modal_update #org").html(_T(odata.orgName));
		    $("#modal_update #buildoper").html(odata.buildoper);
	    	$("#modal_update #builddatetime").html(odata.builddatetime);

		    $('#modal_update').modal();
	});
}

function frozen(id) {//冻结
   PAX_OBJECT.Messenger.confirm(I("confirm_freeze"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/cposMer/frozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposMerTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function unfrozen(id) {//解冻
	PAX_OBJECT.Messenger.confirm(I("confirm_thaw"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/cposMer/unfrozen",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposMerTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function del(id) {//删除
	PAX_OBJECT.Messenger.confirm(I("main_confirm_remove"),function(){
    			PAX_FUNCTION.ajaxGet(contextPath+"/cposMer/delete",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
                    $("#cposMerTable").dataTable().fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}
