var oTable;
$(function () {
    $("#cposhistransOrgBtn").click(function () {  //查询条件加载机构树
        initOrgTreeSpecial2(1,"cposhistransOrgTree","cposhistransOrgBtn","cposhistransOrgId");
    })


	// oTable = initTable();

    //Enable check and uncheck all functionality
    $("#checkall").click(function () {
    	var obj=this;
    	 $("input[name='checkList']").each(function(i){
     		this.checked = obj.checked;
		 });
    });

    //日期控件中英文切换
    var locale = $.cookie("locale");
    var locaLan="zh-CN";
    if(locale="en_US"){
        locaLan="en-US";
    }else{
        locaLan="zh-CN";
    }
    $("#transdateb_s,#transdatee_s").datepicker({format:"yyyy-mm-dd",language:locaLan});
    // $("#transdatee_s").datepicker({format:"yyyy-mm-dd",language:locaLan});
    
    // $("#bid_s").append("<option value=''>"+I('cposmer_choose')+"</option>");
    // PAX_FUNCTION.getCommons("#bid_s",contextPath+"/common/getBid?type=C",{},"id","name");
    // $("#rbid_s").append("<option value=''>"+I('cposmer_choose')+"</option>");
    // PAX_FUNCTION.getCommons("#rbid_s",contextPath+"/common/getBid?type=R",{},"id","name");
    // $("#innid_s").append("<option value=''>"+I('cposmer_choose')+"</option>");
    // PAX_FUNCTION.getCommons("#innid_s",contextPath+"/common/getInnid?classGroup=01",{},"id","name");
    

     
     $("#reset").click(function(){
		$("#cposhistransOrgBtn").text(I('cposmer_choose'));
		
     });
     
     $("#export").click(function(){//导出
    	 var _load = new Loading();
         _load.init();
         _load.start();
         var transdateb_s=$("#transdateb_s").val();
         var transdatee_s=$("#transdatee_s").val();
         var transdateb_="";
         var transdatee_="";
         if(transdateb_s){
             transdateb_=transdateb_s.split("-").join("");
         }
         if(transdatee_s){
             transdatee_=transdatee_s.split("-").join("");
         }

        var reqData={"search_bid":$("#bid_s").val(),
             "search_mid":$("#mid_s").val(),
             "search_tid":$("#tid_s").val(),
             "search_rbid":$("#rbid_s").val(),
             "search_rmid":$("#rmid_s").val(),
             "search_rtid":$("#rtid_s").val(),
             "search_innid":$("#innid_s").val(),
             "search_sysid":$("#sysid_s").val(),
             "search_orgId":$("#cposhistransOrgId").val(),
             "search_transdateb":transdateb_s,
             "search_transdatee":transdatee_s,
             "search_amountb":$("#amountb_s").val(),
             "search_amounte":$("#amounte_s").val()
         };
        if(reqData.search_transdateb=="" || reqData.search_transdatee==""){
        	PAX_OBJECT.Messenger.alert('error', I("choose_date"));
        	_load.stop();
        	return;
        }
        console.log(transdateb_);
        console.log(transdatee_);
        if(transdateb_ > transdatee_){
        	PAX_OBJECT.Messenger.alert('error', I("begin_end"));
        	_load.stop();
        	return false;
        }

         PAX_FUNCTION.ajaxGet(contextPath+"/cposhislog/exportCount",reqData,
             function(data){
                 if(data.msg>65535){
                     _load.stop();
                     PAX_OBJECT.Messenger.alert('error', I("data_over"));
                     return false;
                 }
                 
                 window.location = contextPath+"/cposhislog/export?search_bid="+$("#bid_s").val()+"&search_mid="+$("#mid_s").val()+"&search_tid="+$("#tid_s").val()+"&search_rbid="+$("#rbid_s").val()+"&search_rmid="+$("#rmid_s").val()+"&search_rtid="+$("#rtid_s").val()+"&search_innid="+$("#innid_s").val()+"&search_sysid="+$("#sysid_s").val()+"&search_orgId="+$("#cposhistransOrgId").val()+"&search_transdateb="+transdateb_s+"&search_transdatee="+transdatee_s+"&search_amountb="+$("#amountb_s").val()+"&search_amounte="+$("#amounte_s").val();
                 setTimeout(_load.stop(),5000);
                 
             },
             null);

     	// window.location = contextPath+"/cposhistrans/export?search_bid="+$("#bid_s").val()+"&search_mid="+$("#mid_s").val()+"&search_tid="+$("#tid_s").val()+"&search_rbid="+$("#rbid_s").val()+"&search_rmid="+$("#rmid_s").val()+"&search_rtid="+$("#rtid_s").val()+"&search_innid="+$("#innid_s").val()+"&search_refno="+$("#refno_s").val()+"&search_orgId="+$("#cposhistransOrgId").val()+"&search_transdateb="+transdateb_s+"&search_transdatee="+transdatee_s+"&search_amountb="+$("#amountb_s").val()+"&search_amounte="+$("#amounte_s").val();

     });
 });
 
function isPositiveNum(s){//是否为正整数  
    var re = /^(([0-9]{1,7})|([0-9]{1,7}\.[0-9]{1}[0-9]{0,1}))$/;
    return re.test(s); 
} 

/**
* 表格初始化
* @returns {*|jQuery}
*/

 
/**
* 查看
* @param id
* @private
*/
function detail(id) {//详情
	PAX_FUNCTION.ajax(contextPath+"/cposhistrans/detail",{"id":id},function(data){
		var odata=data.data;
		$('#modal_detail').modal();
	});
}

//js日期比较(yyyy-mm-dd)
 function duibi(a, b) {
    var arr = a.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes > lktimes) {

        return false;
    }
    else
        return true;

}

