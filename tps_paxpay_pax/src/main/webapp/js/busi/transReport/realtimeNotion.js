/**
 * Created by oo on 2017/10/13.
 */
function retransmission(no) {//重发
    PAX_OBJECT.Messenger.confirm(I("confirm_retrainsmission"),function(){
        PAX_FUNCTION.ajaxGet(contextPath+"/realtimenotion/resend",{"transactionNo":no},function(data){
            PAX_OBJECT.Messenger.alert("success",data.msg);
            $("#rtNotionTable").dataTable().fnDraw(false);
        },function(data){
            PAX_OBJECT.Messenger.alert("error",data.msg);
        });
    })
}
// $("#export").click(function(){//导出
//     var _load = new Loading();
//     _load.init();
//     _load.start();
//     var transdateb_s=$("#transdateb_s").val();
//     var transdatee_s=$("#transdatee_s").val();
//     var reqData={"search_bid":$("#bid_s").val(),
//         "search_mid":$("#mid_s").val(),
//         "search_tid":$("#tid_s").val(),
//         "search_rbid":$("#rbid_s").val(),
//         "search_rmid":$("#rmid_s").val(),
//         "search_rtid":$("#rtid_s").val(),
//         "search_innid":$("#innid_s").val(),
//         "search_refno_s":$("#refno_s").val(),
//         "search_orgId":$("#cposhistransOrgId").val(),
//         "search_transdateb":transdateb_s,
//         "search_transdatee":transdatee_s,
//         "search_amountb":$("#amountb_s").val(),
//         "search_amounte":$("#amounte_s").val()
//     }
//
//     PAX_FUNCTION.ajaxGet(contextPath+"/realtimenotion/",reqData,
//         function(data){
//             if(data.msg>65535){
//                 _load.stop();
//                 PAX_OBJECT.Messenger.alert('error', I("data_over"));
//                 return false;
//             }
//
//             window.location = contextPath+"/cposhistrans/export?search_bid="+$("#bid_s").val()+"&search_mid="+$("#mid_s").val()+"&search_tid="+$("#tid_s").val()+"&search_rbid="+$("#rbid_s").val()+"&search_rmid="+$("#rmid_s").val()+"&search_rtid="+$("#rtid_s").val()+"&search_innid="+$("#innid_s").val()+"&search_refno="+$("#refno_s").val()+"&search_orgId="+$("#cposhistransOrgId").val()+"&search_transdateb="+transdateb_s+"&search_transdatee="+transdatee_s+"&search_amountb="+$("#amountb_s").val()+"&search_amounte="+$("#amounte_s").val();
//             setTimeout(_load.stop(),5000);
//
//         },
//         null);
//     // window.location = contextPath+"/cposhistrans/export?search_bid="+$("#bid_s").val()+"&search_mid="+$("#mid_s").val()+"&search_tid="+$("#tid_s").val()+"&search_rbid="+$("#rbid_s").val()+"&search_rmid="+$("#rmid_s").val()+"&search_rtid="+$("#rtid_s").val()+"&search_innid="+$("#innid_s").val()+"&search_refno="+$("#refno_s").val()+"&search_orgId="+$("#cposhistransOrgId").val()+"&search_transdateb="+transdateb_s+"&search_transdatee="+transdatee_s+"&search_amountb="+$("#amountb_s").val()+"&search_amounte="+$("#amounte_s").val();
// });