// var oTable;
$(function () {
	// oTable = initTable();

    //Enable check and uncheck all functionality
    $("#checkall").click(function () {
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    });
    $("#updateForm").Validform({
        tiptype: 2,
        datatype: {

        },
        showAllError: true,
        postonce: true,
        ignoreHidden: true,
    });


    PAX_FUNCTION.ajaxGet(contextPath+"/common/listBranch",{type:"C"},function (resdata) {
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



    $("#lbid_add").change(function () {
        $("#lmid_add").val('');
        window.mcr_lmid=$(this).find("option:selected").attr("mcr");
    })
     
    $("#lmid_add").dblclick(function(){
    	
    	var val = $("#lbid_add").val();
    	if(val == null || val == undefined || val == ''){
    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fourth"));
    		return;
    	}

    	$('#modal_sel_cposMer').modal();
        $("#selCposMer_dt1").dataTable().fnDestroy(false);
        initCposTable();
    });
    
     $("#rmid_add").dblclick(function(){
     	
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
    
    $("#rmid_update").dblclick(function(){
    	
    	window.mcr = $("#rbid_update").val();
    	
     	$("#cur_id_of_rmid").val($(this).attr("id"));
    	$('#modal_rMer_sel').modal();
        $("#sel_rmer_dt1").dataTable().fnDestroy(false);
        initRmerTable();
    });
    
     //新增初始化
    for(var i=1;i<=5;i++){

    	$("#c_rmid_add"+i).dblclick(function(){
    		
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
		
		$("#a_rmid_add"+i).dblclick(function(){
			
			var val = $("#rbid_add").val();
	    	if(val == null || val == undefined || val == ''){
	    		PAX_OBJECT.Messenger.alert('error',I("ph_tps_tips_fifth"));
	    		return;
	    	}
	    	window.mcr = $("#rbid_add").find("option:selected").attr("mcr");
    	
			$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
		
		$("#ca_rmid_add"+i).dblclick(function(){
			
			var val = $("#rbid_add").val();
	    	if(val == null || val == undefined || val == ''){
	    		//layer.alert('请先选择转出渠道', {icon: "5"});
	    		PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_fifth"));
	    		return;
	    	}
	    	window.mcr = $("#rbid_add").find("option:selected").attr("mcr");
    	
			$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
		
		$("#ic_rmid_add"+i).dblclick(function(){
			
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
    	
    }
    
    //修改初始化
    for(var i=1;i<=5;i++){

    	$("#c_rmid_update"+i).dblclick(function(){
    		
    		window.mcr = $("#rbid_update").val();
    		$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
		
		$("#a_rmid_update"+i).dblclick(function(){
			
			window.mcr = $("#rbid_update").val();
			$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
		
		$("#ca_rmid_update"+i).dblclick(function(){
			
			window.mcr = $("#rbid_update").val();
    	
			$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
		
		$("#ic_rmid_update"+i).dblclick(function(){
			
			window.mcr = $("#rbid_update").val();
    	
			$("#cur_id_of_rmid").val($(this).attr("id"));
			$('#modal_rMer_sel').modal();
            $("#sel_rmer_dt1").dataTable().fnDestroy(false);
            initRmerTable();
		});
    	
    }
    
    
    
    // $("#rbid_add").append("<option value=''>"+I('cposmer_choose')+"</option>");
    //
    // $("#rbid_update").append("<option value=''>"+I('cposmer_choose')+"</option>");
    //
    // PAX_FUNCTION.getCommons("#mmf_s",contextPath+"/common/getMmf",{},"id","name");
    // PAX_FUNCTION.getCommons("#lbid_s",contextPath+"/common/getBid?type=C",{},"id","name");
    // PAX_FUNCTION.getCommons("#cls_s",contextPath+"/common/getAppClass?classGroup=01",{},"id","name");
    // PAX_FUNCTION.getCommons("#rbid_s",contextPath+"/common/getBid?type=R",{},"id","name");
    //
    // PAX_FUNCTION.getCommons("#lbid_add",contextPath+"/common/getBid?type=C",{},"id","name");
    // PAX_FUNCTION.getCommons("#cls_add",contextPath+"/common/getAppClass?classGroup=01",{},"id","name");
    // PAX_FUNCTION.getCommons("#mmf_add",contextPath+"/common/getMmf",{},"id","name");
    //
    // PAX_FUNCTION.getCommons("#rbid_add",contextPath+"/common/getBid?type=R",{},"id","name");
    //
    // PAX_FUNCTION.getCommons("#rbid_update",contextPath+"/common/getBid?type=R",{},"id","name");
    
    // $("#search2").click(function(){
		//  tb_search2(1);
    //  });
     
     // $("#reset").click(function(){
		// $("#mmf_s").val("");
		// $("#lbid_s").val("");
		// $("#cls_s").val("");
		// $("#rbid_s").val("");
		// $("#lmid_s").val("");
		// $("#status_s").val("");
		// $("#astatus_s").val("");
     // });
    
	// $("#add").click(function(){
	// 	//$('#addForm')[0].reset();
	// 	$('#modal_new').modal();
	// });
	
	// $("#addForm").on("submit", function(e) {
	//
	// 	var mmf_add = $("#mmf_add").val();
	// 	if(mmf_add){
	// 		if(mmf_add=='01'){
	// 			var rmid_add = $("#rmid_add").val();
	// 			if (rmid_add == null || rmid_add == undefined || rmid_add == '') {
	// 		    	//layer.alert("转出商户号不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_eigth1"));
	// 		    	return false;
	// 		    }
	// 		}else if(mmf_add=='02'){
	// 			var c_cardType_add1 = $("#c_cardType_add1").val();
	// 			if (c_cardType_add1 == null || c_cardType_add1 == undefined || c_cardType_add1 == '') {
	// 		    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
	// 		    	return false;
	// 		    }
	// 			var c_rmid_add1 = $("#c_rmid_add1").val();
	// 			if (c_rmid_add1 == null || c_rmid_add1 == undefined || c_rmid_add1 == '') {
	// 		    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_eigth"));
	// 		    	return false;
	// 		    }
	//
	// 		}else if(mmf_add=='03'){
	// 			var a_amt1_add1 = $("#a_amt1_add1").val();
	// 			var a_amt2_add1 = $("#a_amt2_add1").val();
	// 			if (a_amt1_add1 == null || a_amt1_add1 == undefined || a_amt1_add1 == '') {
	// 		    	//layer.alert("映射1中的开始金额不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_nineth"));
	// 		    	return false;
	// 		    }
	// 		    if (a_amt2_add1 == null || a_amt2_add1 == undefined || a_amt2_add1 == '') {
	// 		    	//layer.alert("映射1中的结束金额不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("js_tips_one"));
	// 		    	return false;
	// 		    }
	// 			var a_rmid_add1 = $("#a_rmid_add1").val();
	// 			if (a_rmid_add1 == null || a_rmid_add1 == undefined || a_rmid_add1 == '') {
	// 		    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_eigth"));
	// 		    	return false;
	// 		    }
	// 		}else if(mmf_add=='04'){
	// 			var ca_cardType_add1 = $("#ca_cardType_add1").val();
	// 			if (ca_cardType_add1 == null || ca_cardType_add1 == undefined || ca_cardType_add1 == '') {
	// 		    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
	// 		    	return false;
	// 		    }
	// 		    var ca_amt1_add1 = $("#ca_amt1_add1").val();
	// 			var ca_amt2_add1 = $("#ca_amt2_add1").val();
	// 			if (ca_amt1_add1 == null || ca_amt1_add1 == undefined || ca_amt1_add1 == '') {
	// 		    	//layer.alert("映射1中的开始金额不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_nineth"));
	// 		    	return false;
	// 		    }
	// 		    if (ca_amt2_add1 == null || ca_amt2_add1 == undefined || ca_amt2_add1 == '') {
	// 		    	//layer.alert("映射1中的结束金额不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("js_tips_one"));
	// 		    	return false;
	// 		    }
	// 			var ca_rmid_add1 = $("#ca_rmid_add1").val();
	// 			if (ca_rmid_add1 == null || ca_rmid_add1 == undefined || ca_rmid_add1 == '') {
	// 		    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error',  I("ph_tps_tips_eigth"));
	// 		    	return false;
	// 		    }
	// 		}else if(mmf_add=='05'){
	//
	// 			var ic_issuerid_add1 = $("#ic_issuerid_add1").val();
	// 			if (ic_issuerid_add1 == null || ic_issuerid_add1 == undefined || ic_issuerid_add1 == '') {
	// 		    	//layer.alert("映射1中的发卡行不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("js_tips_two"));
	// 		    	return false;
	// 		    }
	//
	// 			var ic_cardType_add1 = $("#ic_cardType_add1").val();
	// 			if (ic_cardType_add1 == null || ic_cardType_add1 == undefined || ic_cardType_add1 == '') {
	// 		    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
	// 		    	return false;
	// 		    }
	//
	// 			var ic_rmid_add1 = $("#ic_rmid_add1").val();
	// 			if (ic_rmid_add1 == null || ic_rmid_add1 == undefined || ic_rmid_add1 == '') {
	// 		    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
	// 		    	PAX_OBJECT.Messenger.alert('error',  R("ph_tps_tips_eigth"));
	// 		    	return false;
	// 		    }
	// 		}else if(mmf_add=='06'){
	//
	// 		}
	//
	// 	}
    	//
	// 	//加载层-默认风格
	// 	layer.load();
	//
	//     e.preventDefault();
	//
	//     PAX_FUNCTION.ajax(contextPath+"/merMap/add",$("#addForm").serialize(),
	// 		function(data){
	// 			PAX_PLUGINS.submitSuc($('#modal_new'),data.msg,oTable);
	// 			$('#addForm')[0].reset();
	// 			$("#o2o_add_div").hide();
	// 			$("#cardtype_add_div").hide();
	// 			$("#amt_add_div").hide();
	// 			$("#cardtype_amt_add_div").hide();
	// 			$("#issuer_cardtype_add_div").hide();
	// 		},
	// 		function(data){
	// 			PAX_PLUGINS.submitFail(data.msg);
	// 	});
	// });
	
			
	$("#updateForm").on("submit", function(e) {
		
		// var mmf_update = $("#modal_update #bmf_id").val();
		// if(mmf_update){
		// 	if(mmf_update=='01'){
		// 		var rmid_update = $("#rmid_update").val();
		// 		if (rmid_update == null || rmid_update == undefined || rmid_update == '') {
		// 	    	//layer.alert("转出商户号不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_eigth1"));
		// 	    	return false;
		// 	    }
		// 	}else if(mmf_update=='02'){
		// 		var c_cardType_update1 = $("#c_cardType_update1").val();
		// 		if (c_cardType_update1 == null || c_cardType_update1 == undefined || c_cardType_update1 == '') {
		// 	    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
		// 	    	return false;
		// 	    }
		// 		var c_rmid_update1 = $("#c_rmid_update1").val();
		// 		if (c_rmid_update1 == null || c_rmid_update1 == undefined || c_rmid_update1 == '') {
		// 	    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error',  I("ph_tps_tips_eigth"));
		// 	    	return false;
		// 	    }
		//
		// 	}else if(mmf_update=='03'){
		// 		var a_amt1_update1 = $("#a_amt1_update1").val();
		// 		var a_amt2_update1 = $("#a_amt2_update1").val();
		// 		if (a_amt1_update1 == null || a_amt1_update1 == undefined || a_amt1_update1 == '') {
		// 	    	//layer.alert("映射1中的开始金额不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_nineth"));
		// 	    	return false;
		// 	    }
		// 	    if (a_amt2_update1 == null || a_amt2_update1 == undefined || a_amt2_update1 == '') {
		// 	    	//layer.alert("映射1中的结束金额不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("js_tips_one"));
		// 	    	return false;
		// 	    }
		// 		var a_rmid_update1 = $("#a_rmid_update1").val();
		// 		if (a_rmid_update1 == null || a_rmid_update1 == undefined || a_rmid_update1 == '') {
		// 	    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error',  I("ph_tps_tips_eigth"));
		// 	    	return false;
		// 	    }
		// 	}else if(mmf_update=='04'){
		// 		var ca_cardType_update1 = $("#ca_cardType_update1").val();
		// 		if (ca_cardType_update1 == null || ca_cardType_update1 == undefined || ca_cardType_update1 == '') {
		// 	    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
		// 	    	return false;
		// 	    }
		// 	    var ca_amt1_update1 = $("#ca_amt1_update1").val();
		// 		var ca_amt2_update1 = $("#ca_amt2_update1").val();
		// 		if (ca_amt1_update1 == null || ca_amt1_update1 == undefined || ca_amt1_update1 == '') {
		// 	    	//layer.alert("映射1中的开始金额不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_nineth"));
		// 	    	return false;
		// 	    }
		// 	    if (ca_amt2_update1 == null || ca_amt2_update1 == undefined || ca_amt2_update1 == '') {
		// 	    	//layer.alert("映射1中的结束金额不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("js_tips_one"));
		// 	    	return false;
		// 	    }
		// 		var ca_rmid_update1 = $("#ca_rmid_update1").val();
		// 		if (ca_rmid_update1 == null || ca_rmid_update1 == undefined || ca_rmid_update1 == '') {
		// 	    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error',  I("ph_tps_tips_eigth"));
		// 	    	return false;
		// 	    }
		// 	}else if(mmf_update=='05'){
		//
		// 		var ic_issuerid_update1 = $("#ic_issuerid_update1").val();
		// 		if (ic_issuerid_update1 == null || ic_issuerid_update1 == undefined || ic_issuerid_update1 == '') {
		// 	    	//layer.alert("映射1中的发卡行不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("js_tips_two"));
		// 	    	return false;
		// 	    }
		//
		// 		var ic_cardType_update1 = $("#ic_cardType_update1").val();
		// 		if (ic_cardType_update1 == null || ic_cardType_update1 == undefined || ic_cardType_update1 == '') {
		// 	    	//layer.alert("映射1中的卡片类型不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error', I("ph_tps_tips_sevth"));
		// 	    	return false;
		// 	    }
		//
		// 		var ic_rmid_update1 = $("#ic_rmid_update1").val();
		// 		if (ic_rmid_update1 == null || ic_rmid_update1 == undefined || ic_rmid_update1 == '') {
		// 	    	//layer.alert("映射1中的转出商户号不能为空", {icon: "5"});
		// 	    	PAX_OBJECT.Messenger.alert('error',  I("ph_tps_tips_eigth"));
		// 	    	return false;
		// 	    }
		// 	}else if(mmf_update=='06'){
		//
		// 	}
		//
		// }

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
		layer.load();
		
	    e.preventDefault();
	    
	    PAX_FUNCTION.ajaxGet(contextPath+"/merMap/update",$("#updateForm").serialize(),
			function(data){
                load.stop();
				PAX_PLUGINS.submitSuc($('#modal_update'),data.msg,$("#merMapTable").dataTable());
			},
			function(data){
                load.stop();
				PAX_PLUGINS.submitFail(data.msg);
		});
	});		
	 
    $("#batchDel").click(function(){
    	
    	var str = PAX_FUNCTION.GetCheckIdStr("merMapTable");
    	if (str.length > 0) {
    		//PAX_PLUGINS.prompt("您确定要删除数据吗?",contextPath+"/merMap/batchDel",{"ids":str},oTable);
    		PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
    			PAX_FUNCTION.ajax(contextPath+"/merMap/batchDel",{"ids":str},function(data){
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
    
     
     $("#mmf_add").change(function() {
    	var val = $("#mmf_add").val();
		if(val){
			if(val=='01'){
				$("#o2o_add_div").show();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
			}else if(val=='02'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").show();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
			}else if(val=='03'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").show();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").hide();
			}else if(val=='04'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").show();
				$("#issuer_cardtype_add_div").hide();
			}else if(val=='05'){
				$("#o2o_add_div").hide();
				$("#cardtype_add_div").hide();
				$("#amt_add_div").hide();
				$("#cardtype_amt_add_div").hide();
				$("#issuer_cardtype_add_div").show();
			}
			
		}else{
			$("#o2o_add_div").hide();
			$("#cardtype_add_div").hide();
			$("#amt_add_div").hide();
			$("#cardtype_amt_add_div").hide();
			$("#issuer_cardtype_add_div").hide();
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
function detail(id) {//查看详情
	layer.load();
	PAX_FUNCTION.ajaxGet(contextPath+"/merMap/detail",{"id":id},function(data){

		layer.closeAll('loading');

		var odata=data.data;

		var val = data.data.mmf;
		if(val){
			if(val=='01'){
				$("#o2o_detail_div").show();
				$("#cardtype_detail_div").hide();
				$("#amt_detail_div").hide();
				$("#cardtype_amt_detail_div").hide();
				$("#issuer_cardtype_detail_div").hide();
			}else if(val=='02'){
				$("#o2o_detail_div").hide();
				$("#cardtype_detail_div").show();
				$("#amt_detail_div").hide();
				$("#cardtype_amt_detail_div").hide();
				$("#issuer_cardtype_detail_div").hide();
			}else if(val=='03'){
				$("#o2o_detail_div").hide();
				$("#cardtype_detail_div").hide();
				$("#amt_detail_div").show();
				$("#cardtype_amt_detail_div").hide();
				$("#issuer_cardtype_detail_div").hide();
			}else if(val=='04'){
				$("#o2o_detail_div").hide();
				$("#cardtype_detail_div").hide();
				$("#amt_detail_div").hide();
				$("#cardtype_amt_detail_div").show();
				$("#issuer_cardtype_detail_div").hide();
			}else if(val=='05'){
				$("#o2o_detail_div").hide();
				$("#cardtype_detail_div").hide();
				$("#amt_detail_div").hide();
				$("#cardtype_amt_detail_div").hide();
				$("#issuer_cardtype_detail_div").show();
			}
		}

		$("#modal_detail #lbid").html(_T(odata.lbid_name));
	    $("#modal_detail #cls").html(_T(odata.cls_name));
	    $("#modal_detail #bmf").html(_T(odata.mmf_name));
	    $("#modal_detail #rbid").html(_T(odata.rbid_name));
	    $("#modal_detail #lmid").html(odata.lmid);
	    $("#modal_detail #statusName").html(_T(statusText(odata.status)));
	    $("#modal_detail #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_detail #buildoper").html(odata.buildoper);
	    $("#modal_detail #builddatetime").html(odata.builddatetime);

	    $("#modal_detail #rmid").html(odata.rmid);

	     for(var i=1;i<=5;i++){
	     	var cardType = "odata.cardtype"+i+"_name";
	     	var amt1 = 'odata.amt'+i+"1";
	     	var amt2 = 'odata.amt'+i+"2";
	     	var issuerid = 'odata.issuerid'+i+"_name";
	     	var rmid = 'odata.rmid'+i;

	     	$("#modal_detail #c_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_detail #c_rmid"+i).html(eval(rmid));

	     	$("#modal_detail #a_amt1"+i).html(eval(amt1));
	     	$("#modal_detail #a_amt2"+i).html(eval(amt2));
	     	$("#modal_detail #a_rmid"+i).html(eval(rmid));

	     	$("#modal_detail #ca_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_detail #ca_amt1"+i).html(eval(amt1));
	     	$("#modal_detail #ca_amt2"+i).html(eval(amt2));
	     	$("#modal_detail #ca_rmid"+i).html(eval(rmid));

	     	$("#modal_detail #ic_issuerid"+i).html(eval(issuerid));
	     	$("#modal_detail #ic_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_detail #ic_rmid"+i).html(eval(rmid));

	     }

		$('#modal_detail').modal();
	},null);
}

function audit(id) {//审核
	layer.load();
	PAX_FUNCTION.ajaxGet(contextPath+"/merMap/detail",{"id":id},function(data){
		layer.closeAll('loading');
		var odata=data.data;
		if(odata.auditstatus=='2'){
			PAX_OBJECT.Messenger.alert('error', I("main_record_have_audit"));
			return;
		}
		var val = data.data.mmf;
		if(val){
			if(val=='01'){
				$("#o2o_audit_div").show();
				$("#cardtype_audit_div").hide();
				$("#amt_audit_div").hide();
				$("#cardtype_amt_audit_div").hide();
				$("#issuer_cardtype_audit_div").hide();
			}else if(val=='02'){
				$("#o2o_audit_div").hide();
				$("#cardtype_audit_div").show();
				$("#amt_audit_div").hide();
				$("#cardtype_amt_audit_div").hide();
				$("#issuer_cardtype_audit_div").hide();
			}else if(val=='03'){
				$("#o2o_audit_div").hide();
				$("#cardtype_audit_div").hide();
				$("#amt_audit_div").show();
				$("#cardtype_amt_audit_div").hide();
				$("#issuer_cardtype_audit_div").hide();
			}else if(val=='04'){
				$("#o2o_audit_div").hide();
				$("#cardtype_audit_div").hide();
				$("#amt_audit_div").hide();
				$("#cardtype_amt_audit_div").show();
				$("#issuer_cardtype_audit_div").hide();
			}else if(val=='05'){
				$("#o2o_audit_div").hide();
				$("#cardtype_audit_div").hide();
				$("#amt_audit_div").hide();
				$("#cardtype_amt_audit_div").hide();
				$("#issuer_cardtype_audit_div").show();
			}
		}
		
		$("#modal_audit #lbid").html(_T(odata.lbid_name));
	    $("#modal_audit #cls").html(_T(odata.cls_name));
	    $("#modal_audit #bmf").html(_T(odata.mmf_name));
	    $("#modal_audit #rbid").html(_T(odata.rbid_name));
	    $("#modal_audit #lmid").html(odata.lmid);
	    $("#modal_audit #statusName").html(_T(statusText(odata.status)));
	    $("#modal_audit #astatusName").html(_T(auditText(odata.auditstatus)));
	    $("#modal_audit #buildoper").html(odata.buildoper);
	    $("#modal_audit #builddatetime").html(odata.builddatetime);
	    $("#modal_audit #rmid").html(odata.rmid);
	    
	     for(var i=1;i<=5;i++){
	     	var cardType = 'odata.cardtype'+i+"_name";
	     	var amt1 = 'odata.amt'+i+"1";
	     	var amt2 = 'odata.amt'+i+"2";
	     	var issuerid = 'odata.issuerid'+i+"_name";
	     	var rmid = 'odata.rmid'+i;
	     	$("#modal_audit #c_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_audit #c_rmid"+i).html(eval(rmid));
	     	$("#modal_audit #a_amt1"+i).html(eval(amt1));
	     	$("#modal_audit #a_amt2"+i).html(eval(amt2));
	     	$("#modal_audit #a_rmid"+i).html(eval(rmid));
	     	$("#modal_audit #ca_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_audit #ca_amt1"+i).html(eval(amt1));
	     	$("#modal_audit #ca_amt2"+i).html(eval(amt2));
	     	$("#modal_audit #ca_rmid"+i).html(eval(rmid));
	     	$("#modal_audit #ic_issuerid"+i).html(eval(issuerid));
	     	$("#modal_audit #ic_cardType"+i).html(_T(eval(cardType)));
	     	$("#modal_audit #ic_rmid"+i).html(eval(rmid));
	     	
	     }
	
		$('#modal_audit').modal();
		
		$('#audPassBnt').unbind("click").bind("click",function(){//审核通过
			layer.load();
				
			PAX_FUNCTION.ajaxGet(contextPath+"/merMap/audit",{"ids":id,"passStatu":"1"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#merMapTable").dataTable());
				},
				function(data){
					PAX_PLUGINS.submitFail(data.msg);
			});	
		});
		
		$('#audNotPassBnt').unbind("click").bind("click",function(){//审核不通过
			if(odata.auditstatus=='1'){
				PAX_OBJECT.Messenger.alert('error',  I("main_record_have_no_audit"));
				return;
			}
			layer.load();
				
			PAX_FUNCTION.ajaxGet(contextPath+"/merMap/audit",{"ids":id,"passStatu":"0"},
				function(data){
					PAX_PLUGINS.submitSuc($('#modal_audit'),data.msg,$("#merMapTable").dataTable());
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
	PAX_FUNCTION.ajaxGet(contextPath+"/merMap/detail",{"id":id},function(data){
			layer.closeAll('loading');
			var odata=data.data;
			if(odata.status=='2'){
				PAX_OBJECT.Messenger.alert('error', I("record_on_use"));
				return;
			}
			
			var val = data.data.mmf;
			if(val){
				if(val=='01'){
					$("#o2o_update_div").show();
					$("#cardtype_update_div").hide();
					$("#amt_update_div").hide();
					$("#cardtype_amt_update_div").hide();
					$("#issuer_cardtype_update_div").hide();
				}else if(val=='02'){
					$("#o2o_update_div").hide();
					$("#cardtype_update_div").show();
					$("#amt_update_div").hide();
					$("#cardtype_amt_update_div").hide();
					$("#issuer_cardtype_update_div").hide();
				}else if(val=='03'){
					$("#o2o_update_div").hide();
					$("#cardtype_update_div").hide();
					$("#amt_update_div").show();
					$("#cardtype_amt_update_div").hide();
					$("#issuer_cardtype_update_div").hide();
				}else if(val=='04'){
					$("#o2o_update_div").hide();
					$("#cardtype_update_div").hide();
					$("#amt_update_div").hide();
					$("#cardtype_amt_update_div").show();
					$("#issuer_cardtype_update_div").hide();
				}else if(val=='05'){
					$("#o2o_update_div").hide();
					$("#cardtype_update_div").hide();
					$("#amt_update_div").hide();
					$("#cardtype_amt_update_div").hide();
					$("#issuer_cardtype_update_div").show();
				}
			}
			
			$("#modal_update #lbid").html(_T(odata.lbid_name));
		    $("#modal_update #cls").html(_T(odata.cls_name));
		    $("#modal_update #bmf").html(_T(odata.mmf_name));
		    $("#modal_update #bmf_id").val(odata.mmf);
		    $("#modal_update #rbid").html(_T(odata.rbid_name));
		    $("#modal_update #rbid_update").val(odata.rmcr);
		    $("#modal_update #lmid").html(odata.lmid);
		    $("#modal_update #rmid_update").val(odata.rmid);
		    $("#modal_update #id_update").val(odata.id);
	    
	    	
		    $("#modal_update #buildoper").html(odata.buildoper);
	    	$("#modal_update #builddatetime").html(odata.builddatetime);
	    	
	    	for(var i=1;i<=5;i++){
		     	var cardType = 'odata.cardtype'+i;
		     	var amt1 = 'odata.amt'+i+"1";
		     	var amt2 = 'odata.amt'+i+"2";
		     	var issuerid = 'odata.issuerid'+i;
		     	var rmid = 'odata.rmid'+i;
		     	
		     	$("#modal_update #c_cardType_update"+i).val(eval(cardType));
		     	$("#modal_update #c_rmid_update"+i).val(eval(rmid));
		     	
		     	$("#modal_update #a_amt1_update"+i).val(eval(amt1));
		     	$("#modal_update #a_amt2_update"+i).val(eval(amt2));
		     	$("#modal_update #a_rmid_update"+i).val(eval(rmid));
		     	
		     	$("#modal_update #ca_cardType_update"+i).val(eval(cardType));
		     	$("#modal_update #ca_amt1_update"+i).val(eval(amt1));
		     	$("#modal_update #ca_amt2_update"+i).val(eval(amt2));
		     	$("#modal_update #ca_rmid_update"+i).val(eval(rmid));
		     	
		     	$("#modal_update #ic_issuerid_update"+i).val(eval(issuerid));
		     	$("#modal_update #ic_cardType_update"+i).val(eval(cardType));
		     	$("#modal_update #ic_rmid_update"+i).val(eval(rmid));
		     	
		     }
		    
		    $('#modal_update').modal();
			
	},null);
}

function frozen(id) {//冻结
	PAX_OBJECT.Messenger.confirm(I("confirm_freeze"),function(){
    			PAX_FUNCTION.ajax(contextPath+"/merMap/frozen",{"id":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function unfrozen(id) {//解冻
	PAX_OBJECT.Messenger.confirm(I("confirm_thaw"),function(){
    			PAX_FUNCTION.ajax(contextPath+"/merMap/unfrozen",{"id":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}

function del(id) {//删除
	PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
    			PAX_FUNCTION.ajax(contextPath+"/cposTerm/batchDel",{"ids":id},function(data){
					PAX_OBJECT.Messenger.alert("success",data.msg);
					oTable.fnDraw(false);
				},function(data){
					PAX_OBJECT.Messenger.alert("error",data.msg);
				});
    		})
}


