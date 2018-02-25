/**
 * Created by Lina on 2017/5/2.
 */

// 将列表行的用户id赋值给隐藏域保存起来，供后面使用
function changeForAuthId(id,site_id){
    $("#for_modal_funcs").val(id);
		$('#site_id_for_modal_resource').val(site_id);
		
}

//分配功能
function getTree(id){
    var setting = {
        data: {
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "level"
            },
            simpleData: {//简单数据模式
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onNodeCreated: zTreeOnNodeCreated
        }
    };

    var zNodes =[];

    $.ajax({
        async:false,//是否异步
        cache:false,//是否使用缓存
        type:'POST',//请求方式：post
        dataType:'json',//数据传输格式：json
        url:contextPath + '/authority/getFuncsToUseByAuth.do?',//请求的action路径
        data:{
            id:id
        },
        error:function(){
            //请求失败处理函数
            PAX_OBJECT.Messenger.alert('error', data.msg);
        },
        success:function(data){
            zNodes =data.data;
            zNodes.sort(function(a,b){return a.orderno-b.orderno});
            $.each(zNodes,function (index,element) {
                element.name=_T(element.name);
                var znodesFuncs=zNodes[index].funcs;
                $(znodesFuncs).each(function (i,ele) {
                    ele.name=_T(ele.name)
                })
            });
           $.fn.zTree.init($("#func_tree"), setting, zNodes);
            var treeObj = $.fn.zTree.getZTreeObj("func_tree");
            var allNodes= treeObj.getNodes();
            addFun(allNodes[0]);
						//checkbox单选
						$("#func_tree").find("input:checkbox").click(function(){
							if($(this).prop("checked") == true)
								$("#func_tree").find("input:checkbox").not($(this)).prop("checked", false);
						});
						
						PAX_FUNCTION.ajaxPost(contextPath + "/authority/getFuncsByAuth.do",{
								"id":id
						},function(data){
								if(data.data.length > 0){
										$("#func_tree input:checkbox[value="+data.data[0].id+"]").prop("checked", true);
								}
						},function(data){
								PAX_OBJECT.Messenger.alert('error', data.msg);
						});
						

            function addFun(treeNode){
                if(typeof treeNode.funcs != "undefined") {
                    treeObj.addNodes(treeNode, treeNode.funcs);
                }
                if(typeof treeNode.children != "undefined"){
                    $.each(treeNode.children, function(index, ele){
                        addFun(ele);
                    })
                }
            }
            $("#func_tree a").removeAttr("href");

        }
    });
}

function zTreeOnNodeCreated(event, treeId, treeNode) {
    if(typeof treeNode.children == "undefined" && typeof treeNode.funcs == "undefined"){
        var $obj = $("#"+treeNode.tId+"_ico");
        $obj.after("<input type='checkbox' id='"+treeNode.tId+"_checkbox' name='funcs' value='"+treeNode.id+"'/>");
        $obj.remove();
    }

}
$(function(){
    $("#auth_fun_form").on("submit", function(e) {
				e.preventDefault();
        PAX_FUNCTION.ajaxPost(contextPath + "/authority/grantFuncs.do",{
            "id":$("#for_modal_funcs").val(),
            "funcs":$("#func_tree input:checkbox:checked").val()
        },function(data){
            PAX_OBJECT.Messenger.alert('success', data.msg);
						$("#modal_fun").modal("hide");
        },function(data){
            PAX_OBJECT.Messenger.alert('error', data.msg);
        });
    });
});









