$(document).ready(function(){
    //checkout页面的js开始
    var deal_flow_1=$("#deal_flow_1").text();
    var deal_flow_2=$("#deal_flow_2").text();
    var alipay_plat_click=$("#alipay_plat_click").text();
    var wechat_plat_click=$("#wechat_plat_click").text();
    var day_flow_ali_1=$("#day_flow_ali_1").text();
    var day_flow_ali_2=$("#day_flow_ali_2").text();
    var day_flow_wechat_1=$("#day_flow_wechat_1").text();
    var day_flow_wechat_2=$("#day_flow_wechat_2").text();
    $("#alipay_plat_click").on("click",function(){
        $("#deal_flow_1").text(alipay_plat_click);
    });
    $("#wechat_plat_click").on("click",function(){
        $("#deal_flow_1").text(wechat_plat_click);
    });
    $("#day_flow_ali_1").on("click",function(){
        $("#deal_flow_2").text(day_flow_ali_1);
    });
    $("#day_flow_ali_2").on("click",function(){
        $("#deal_flow_2").text(day_flow_ali_2);
    });
    $("#day_flow_wechat_1").on("click",function(){
        $("#deal_flow_2").text(day_flow_wechat_1);
    });
    $("#day_flow_wechat_2").on("click",function(){
        $("#deal_flow_2").text(day_flow_wechat_2);
    });
    //checkout页面js结束
    //operation_admin页面js开始
    var add_shop=$("#add_shop").text();
    var modify_shop=$("#modify_shop").text();
    var remove_shop=$("#remove_shop").text();
    var add_oper=$("#add_oper").text();
    var modify_oper=$("#modify_oper").text();
    var remove_oper=$("#remove_oper").text();
    var freeze_oper=$("#freeze_oper").text();
    $("#add_shop").on("click",function(){
        $("#current_oper_1").text(add_shop);
    });
    $("#modify_shop").on("click",function(){
        $("#current_oper_1").text(modify_shop);
    });
    $("#remove_shop").on("click",function(){
        $("#current_oper_1").text(remove_shop);
    });
    $("#add_oper").on("click",function(){
        //e.preventDefault();
        $("#current_oper").text(add_oper);

    });
    $("#modify_oper").on("click",function(e){
        e.preventDefault();
        $("#current_oper").text(modify_oper);
    });
    $("#remove_oper").on("click",function(e){
        e.preventDefault();
        $("#current_oper").text(remove_oper);
    });
    $("#freeze_oper").on("click",function(e){
        e.preventDefault();
        var c=$("#current_oper").text(freeze_oper);
    });
    //新增操作员弹出框提交
    $("#add_operator_submit").on("click",function(){
        var oper_name=$("#oper_name").val();
        var oper_num=$("#oper_num").val();
        var oper_init_password=$("#oper_init_password").val();
        var oper_reset_password=$("#oper_reset_password").val();
        var distr_stores=$("#distr_stores").find("option:selected").text();

            if(oper_name&&oper_num&&oper_init_password&&oper_reset_password&&distr_stores){
                $.ajax({
                    url:'dataurl.com',// 跳转到 action
                    data:{
                        oper_name : oper_name,//5
                        oper_num : oper_num,//1
                        oper_init_password : oper_init_password, //2
                        oper_reset_password:oper_reset_password,//3
                        distr_stores : distr_stores,//4
                    },
                    type:"post",
                    dataTape:"json",
                });
            }

                });
    //operation_admin页面js结束
})
