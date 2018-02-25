/**
 * Created by moon on 2017/4/28.
 */

/**
 * 加载树形结构数据
 */
$(function(){

		var setting = {
				data: {
						simpleData: {//简单数据模式
								enable:true,
								idKey: "id",
								pIdKey: "pId",
								rootPId: ""
						}
				},
				callback: {
						onClick: menuTreeClick
				}
		};

		function selFunctionTree() {
				var zNodes = [];
				PAX_FUNCTION.ajaxPost(contextPath+'/menu/listAll.do',{"site_id":$("#function_sel_site").val()},function(data){
						zNodes = data.data;
						zNodes.sort(function(a,b){return a.orderno-b.orderno});
						$.fn.zTree.init($("#menuSearchTree"), setting, zNodes);
						$("#menuSearchTree a").removeAttr("href");
						// 默认点击根节点
						menuTreeClick(null,null,getRootNode(),null);
				});
		}

		function addFunctionTree() {
				var zNodes = [];
				PAX_FUNCTION.ajaxPost(contextPath+'/menu/listAll.do',{"site_id":$("#site").val()},function(data){
						zNodes = data.data;
						$(zNodes).each(function (index,element) {
                            element.name=_T(element.name)
                        })
						zNodes.sort(function(a,b){return a.orderno-b.orderno});
						$.fn.zTree.init($("#addMenuFunctionTree"), setting, zNodes);
						$("#addMenuFunctionTree a").removeAttr("href");
						// 默认点击根节点
						menuTreeClick(null,null,getRootNode(),null);
				});
		}


		// 获取根节点（业务情况根节点只有一个）
		function getRootNode(){
				var treeObj = $.fn.zTree.getZTreeObj("menuSearchTree");
				//返回根节点集合
				var root = treeObj.getNodesByFilter(function (node) {
						return node.level == 0;
				},true);
				return root;
		}

		// 机构树点击事件
		function menuTreeClick(event, treeId, treeNode){
				if(treeId=='menuSearchTree'){
						$("#menu_search").val(treeNode.id);
						$("#menuSearchBtn").text(_T(treeNode.name));
                    	$("#menuSearchBtn").attr("title",_T(treeNode.name));
				}
				if(treeId=='addMenuFunctionTree'){
						$("#add_menu_function").val(treeNode.id);
						$("#addMenuFunctionBtn").text(_T(treeNode.name));
                    	$("#addMenuFunctionBtn").attr("title",_T(treeNode.name));
				}

		}

		$("#menuSearchBtn").click(function () {
				if($("#function_sel_site").val()){
						selFunctionTree();
				}else{
						PAX_OBJECT.Messenger.alert("error", I("choose_site"));
						return false;
				}
		});

		$("#addMenuFunctionBtn").click(function () {
				if($("#site").val()){
						addFunctionTree();
				}else{
						PAX_OBJECT.Messenger.alert("error", I("choose_site"));
						return false;
				}
		});
		
		$("#function_sel_site").change(function(){
			$("#menu_search").val("");
			$("#menuSearchBtn").text(I("cposmer_choose"));
			$("#menuSearchTree").empty();
		});
		
		$("#site").change(function(){
			$("#add_menu_function").val("");
			$("#addMenuFunctionBtn").text(I("cposmer_choose"));
			$("#addMenuFunctionTree").empty();
		});

})







