/**
 * Created by Lina on 2017/5/2.
 */

$("#addForm").Validform({//修改表单校验
    tiptype: 2,
    datatype: {

        rechk:function (get) {
			if($("#pwd").val()!=get){
				return I("tip_third")
			}
			return true;
        }
    },
    showAllError: true,
    postonce: true,
    ignoreHidden: false
});


// 将列表行的用户id赋值给隐藏域保存起来，供后面使用
function changeForUserId(id) {
	$("#for_modal_auth").val(id);
}

// 通过用户查询角色列表
function getRolesByUserTableOption($obj) {
	return {
		'bPaginate' : false,
		"aoColumns" : [
				{
					"mDataProp" : null,
					"bSortable" : false,
					"sClass" : "center2",
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html(
								"<input type='checkbox' name='checkList' value='"
										+ oData.id + "'>");
					}
				},
				{
					"mDataProp" : "id",
					"sClass" : "center",
					"bSortable" : false
				},
				{
					"mDataProp" : "name",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html(
								"<span title='" + sData + "'>" + _T(sData) + "</span>");
					}
				},
				{
					"mDataProp" : "site",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html(
								"<span title='" + sData.name + "'>" + _T(sData.name) + "</span>");
					}
				},
				{
					"mDataProp" : "org",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html(
								"<span title='" + sData.name + "'>" + _T(sData.name) + "</span>");
					}
				},
			],
		"fnDrawCallback" : function(id) {
			// 从用户拥有的权限中查询，有的默认选中
			PAX_FUNCTION.ajaxPost(contextPath + "/user/getRolesByUser.do", {
				"id" : $("#for_modal_auth").val()
			}, function(data) {
				var data = data.data;
				var checklist = $("#getRolesByUserTable").find(
						'input[name=checkList][type=checkbox]');
				for (var i = 0; i < checklist.length; i++) {
					$(data).each(function(index, ele) {
						if (ele.id == $(checklist[i]).val()) {
							$(checklist[i]).attr("checked", "checked");
						}
					})
				}
			}, function(data) {
				PAX_OBJECT.Messenger.alert("error", data.msg);
			})
		}
	}

}

// 用户分配角色提交
$("#addRolesForUserBtn").click(
		function() {
			var roleLength = $("#getRolesByUserTable").find(
					"input[name=checkList][type=checkbox]:checked");
			var arr = [];
			if (roleLength.length == 0) {
				PAX_OBJECT.Messenger.alert("error", I("least_choose_one"));

			} else {
				$(roleLength).each(function() {
					arr.push($(this).val());
				});
				PAX_FUNCTION.ajaxPost(contextPath + "/user/grantRoles.do", {
					"userId" : $("#for_modal_auth").val(),
					"roles" : arr.join(",")
				}, function(data) {
					$("#modal_auth").modal("hide");
					PAX_OBJECT.Messenger.alert("success", data.msg);
				}, function(data) {
					PAX_OBJECT.Messenger.alert("error", data.msg);
				})
			}
		});

// 重置密码
function resetKey(id) {
	// 确认重置密码？
	PAX_OBJECT.Messenger.confirm(I("sure_reset_key"), function() {
		PAX_FUNCTION.ajaxPost(contextPath + "/user/resetPwd.do", {
			"id" : id
		}, function(data) {
			PAX_OBJECT.Messenger.alert("success", data.msg + ","+I("commonUI_new_password")
					+ data.data);
		}, function(data) {
			PAX_OBJECT.Messenger.alert("error", data.msg);
		})
	});
}

$(function() {
	$("#pwd").blur(function() {
		$("#pwdHide").val($.md5($(this).val()));
	});

	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : orgTreeClick
		}
	};

	// 获取根节点（业务情况根节点只有一个）
	function getRootNode() {
		var treeObj = $.fn.zTree.getZTreeObj("mOrgTree");
		// 返回根节点集合
		var root = treeObj.getNodesByFilter(function(node) {
			return node.level == 0;
		}, true);
		return root;
	}

	// 生成机构的树形结构

	function selOrg() {
        var a = $("#user_sel_site").val();
        console.log(typeof(a));
		PAX_FUNCTION.ajaxPost(contextPath + "/organization/list", {
			"site_id" : $("#user_sel_site").val()
		}, function(result) {
			zNodes = result.data;
			$.each(zNodes, function(index, element){
			element.name=_T(element.name);
			});
			$.fn.zTree.init($("#mOrgTree"), setting, zNodes);
			$.fn.zTree.init($("#mOrgTree2"), setting, zNodes);
			$.fn.zTree.init($("#mOrgTree3"), setting, zNodes);
			// 默认点击根节点
			orgTreeClick(null, null, getRootNode(), null);
		});
	}

	function selOrgAdd() {
		PAX_FUNCTION.ajaxPost(contextPath + "/organization/list", {
			"site_id" : $("#user_add_site").val()
		}, function(result) {
			zNodes = result.data;
			$.each(zNodes, function(index, element){
			element.name=_T(element.name);
			});
			$.fn.zTree.init($("#mOrgTreeAdd"), setting, zNodes);
			$.fn.zTree.init($("#mOrgTreeAdd2"), setting, zNodes);
			$.fn.zTree.init($("#mOrgTreeAdd3"), setting, zNodes);
			// 默认点击根节点
			orgTreeClick(null, null, getRootNode(), null);
		});
	}

	// 机构树点击事件
	function orgTreeClick(event, treeId, treeNode) {
		if (treeId == 'mOrgTree') {
			$("#organizationId_s").val(treeNode.id);
			$("#orgBtn").text(_T(treeNode.name));
			$("#orgBtn").attr("title",_T(treeNode.name))
		}
		if (treeId == 'mOrgTreeAdd') {
			$("#organizationId_add").val(treeNode.id).trigger("blur");
			console.log($("#organizationId_add").val());
			$("#orgBtnAdd").text(_T(treeNode.name));
			$("#orgBtnAdd").attr("title",_T(treeNode.name));
		}
	}

	$("#orgBtn").click(function() {
		if ($("#user_sel_site").val()) {
			selOrg();
		} else {
			PAX_OBJECT.Messenger.alert("error", I("choose_site"));
			$("#organizationId_s").val("");
			$("#orgBtn").text(I("cposmer_choose"));
			return false;
		}
	});

	$("#orgBtnAdd").click(function() {

		if ($("#user_add_site").val()) {
			selOrgAdd();
		} else {
			PAX_OBJECT.Messenger.alert("error", I("choose_site"));
			$("#organizationId_add").val("").trigger("blur");
			$("#orgBtnAdd").text(I("cposmer_choose"));
			return false;
		}
	});
	
	$("#user_sel_site").change(function(){
			$("#organizationId_s").val("");
			$("#orgBtn").text(I("cposmer_choose"));
			$("#mOrgTree").empty();
	});
		
	$("#user_add_site").change(function(){
		$("#organizationId_add").val("");
		$("#orgBtnAdd").text(I("cposmer_choose"));
		$("#mOrgTreeAdd").empty();
	});
});