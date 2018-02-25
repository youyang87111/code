/**
 * Created by Lina on 2017/5/2.
 */

$(function () {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: orgTreeClick
        }
    };

    // 获取根节点（业务情况根节点只有一个）
    function getRootNode(){
        var treeObj = $.fn.zTree.getZTreeObj("mOrgTree");
        //返回根节点集合
        var root = treeObj.getNodesByFilter(function (node) {
            return node.level == 0;
        },true);
        return root;
    }

    // 生成机构的树形结构
    function selOrg() {
        PAX_FUNCTION.ajaxPost(contextPath+"/organization/list.do",{"site_id":$("#role_sel_site").val()},function(result){
            zNodes = result.data;
            // $.each(zNodes, function(index, element){
            //     element.name=_T(element.name);
            // });
            $.fn.zTree.init($("#mOrgTree"), setting, zNodes);
            // 默认点击根节点
            orgTreeClick(null,null,getRootNode(),null);
        });
    }

    function selOrgAdd() {
        PAX_FUNCTION.ajaxPost(contextPath+"/organization/list.do",{"site_id":$("#role_add_site").val()},function(result){
            zNodes = result.data;
            $.each(zNodes, function(index, element){
                element.name=_T(element.name);
            });
            $.fn.zTree.init($("#mOrgTreeAdd"), setting, zNodes);
            // 默认点击根节点
            orgTreeClick(null,null,getRootNode(),null);
        });
    }



    // 机构树点击事件
    function orgTreeClick(event, treeId, treeNode){
        if(treeId=='mOrgTree'){
            $("#organizationId_s").val(treeNode.id);
            $("#orgBtn").text(_T(treeNode.name));
            $("#orgBtn").attr("title",_T(treeNode.name));
        }
        if(treeId=='mOrgTreeAdd'){
            $("#organizationId_add").val(treeNode.id);
            $("#orgBtnAdd").text(_T(treeNode.name));
            $("#orgBtnAdd").attr("title",_T(treeNode.name));
        }
    }

    $("#orgBtn").click(function () { //查询
        if($("#role_sel_site").val()){
            selOrg();
        }else{
            PAX_OBJECT.Messenger.alert("error", I("choose_site"));
            $("#organizationId_s").val("");
            $("#orgBtn").text(I("cposmer_choose"));
            return false;
        }
    });

    $("#orgBtnAdd").click(function () {  //新增
        if($("#role_add_site").val()){
            selOrgAdd();
        }else{
            PAX_OBJECT.Messenger.alert("error", I("choose_site"));
            $("#organizationId_add").val("");
            $("#orgBtnAdd").text(I("cposmer_choose"));
            return false;
        }
    })


});

// 将列表行的角色id赋值给隐藏域保存起来，供后面使用
function changeForRoleId(id,site_id){
    $("#for_modal_auth").val(id);
    $('#site_id_for_modal_auth').val(site_id);
}

//分配权限
function getMenuTree(id){
    var setting = {
        data: {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: { "Y": "p", "N": "s" },
                // radioType: "level"
            },
            simpleData: {//简单数据模式
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onNodeCreated: authTreeOnNodeCreated
        }
    };

    var zNodes =[];

    $.ajax({
        async:false,//是否异步
        cache:false,//是否使用缓存
        type:'POST',//请求方式：post
        dataType:'json',//数据传输格式：json
        url:contextPath + '/role/getAuthsToUseByRole.do?',//请求的action路径
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
                var znodesAuths=zNodes[index].auths;
                $(znodesAuths).each(function (i,ele) {
                    ele.name=_T(ele.name)
                })
            });
            $.fn.zTree.init($("#auth_tree"), setting, zNodes);
            var treeObj = $.fn.zTree.getZTreeObj("auth_tree");
            var allNodes= treeObj.getNodes();
            addFun(allNodes[0]);

            PAX_FUNCTION.ajaxPost(contextPath + "/role/getAuthsByRole.do",{
                "id":id
            },function(data){
                if(data.data.length > 0){
                    $.each(data.data,function (index,ele) {
                        $("#auth_tree input:checkbox[value="+data.data[index].id+"]").prop("checked", true);
                    });
										
                    //初始状态设置
                    initCheckStatus(allNodes[0]);
                }
            },function(data){
                PAX_OBJECT.Messenger.alert('error', data.msg);
            });


            function addFun(treeNode){
                if(typeof treeNode.auths != "undefined") {
                    treeObj.addNodes(treeNode, treeNode.auths);
                }
                if(typeof treeNode.children != "undefined"){
                    $.each(treeNode.children, function(index, ele){
                        addFun(ele);
                    })
                }
            }
            $("#auth_tree a").removeAttr("href");

						//设置选择事件
						$("#auth_tree input:checkbox").click(function(){
							var tId = this.id.replace(/_checkbox$/, "");
							var treeNode = treeObj.getNodeByTId(tId);
							checkChildren(treeNode, $(this).prop("checked"));
							if($(this).prop("checked") == false){
								unCheckParent(treeNode);
							}else{
								checkParent(treeNode);
							}
							
						});
						
						
						function unCheckParent(treeNode){
							var parentNode = treeNode.getParentNode();
							if(parentNode)
							{
								$("#"+parentNode.tId+"_checkbox").prop("checked", false);
								unCheckParent(parentNode);
							}
						}
						
						function checkParent(treeNode){
							var parentNode = treeNode.getParentNode();
							if(parentNode){
								var check = true;
								$("#"+parentNode.tId+"_ul input:checkbox").each(function(index,ele){
									if($(ele).prop("checked") == false)
										check = false;
								});
								
								$("#"+parentNode.tId+"_checkbox").prop("checked", check);
								checkParent(parentNode);
							}
						}
						
						function checkChildren(treeNode, checked){
							if(treeNode.children){
								$.each(treeNode.children, function(index, ele){
									$("#"+ele.tId+"_checkbox").prop("checked", checked);
										if(ele.children && ele.children.length>0){
											checkChildren(ele, checked);
										}
								});
							}
						}
						
						function initCheckStatus(treeNode){
							var checkall = true;
							if(treeNode.children){
								$.each(treeNode.children,function(index, ele){
									if(ele.children){
										if(initCheckStatus(ele) == true){
											$("#"+ele.tId+"_checkbox").prop("checked", true);
										}else{
											checkall = false;
										}
									}else{
										if($("#"+ele.tId+"_checkbox").prop("checked") == false)
											checkall = false;
									}
								})
							}
							
							if(checkall == true){
								$("#"+treeNode.tId+"_checkbox").prop("checked", true);
							}
							
							return checkall;
						}
        }
    });
}

function authTreeOnNodeCreated(event, treeId, treeNode) {
		var $obj = $("#"+treeNode.tId+"_ico");

    if(typeof treeNode.children == "undefined" && typeof treeNode.auths == "undefined"){
			$obj.after("<input type='checkbox' id='"+treeNode.tId+"_checkbox' name='auths' value='"+treeNode.id+"'/>");
			$obj.remove();
		}
        // else{
		//	$obj.after("<input type='checkbox' id='"+treeNode.tId+"_checkbox' name='menus' value='"+treeNode.id+"'/>");
		//}

}
$(function(){

    $("#role_auth_form").on("submit", function(e) {
        e.preventDefault();
        var arr=[];
        $("#auth_tree input:checkbox:checked[name=auths]").each(function () {
            arr.push($(this).val());
        });
        PAX_FUNCTION.ajaxPost(contextPath + "/role/grantAuths.do",{
            "id":$("#for_modal_auth").val(),
            "auths":arr.join(",")
        },function(data){
            PAX_OBJECT.Messenger.alert('success', data.msg);
            $("#modal_auth").modal("hide");
        },function(data){
            PAX_OBJECT.Messenger.alert('error', data.msg);
        });
    });
		
		$("#role_sel_site").change(function(){
			$("#organizationId_s").val("");
			$("#orgBtn").text(I("cposmer_choose"));
			$("#mOrgTree").empty();
		});
		
		$("#role_add_site").change(function(){
			$("#organizationId_add").val("");
			$("#orgBtnAdd").text(I("cposmer_choose"));
			$("#mOrgTreeAdd").empty();
		});
});