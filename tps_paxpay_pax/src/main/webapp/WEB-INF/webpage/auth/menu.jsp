<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>

<section class="" style="background-color: #fff;">
<div class="row">
    <div class="col-md-12">
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title i18n-flag" i18n-data="menu_mgt_full"></h3>
            </div>
            <div class="box-body">
                <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                    <div class="row">
                        <form role="form"  class="search-form" for-table="">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                        <span class="input-group-addon i18n-flag" i18n-data="site"
                                              style="text-align: left;"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <select  class="form-control input-sm ajax-select" name-rel="name" value-rel="id" name="site_id" data-action="/site/listAll.do" id="search_menu_id">
                                                
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row pt10">
                                <div class="col-md-12">
                                    <div class="btn-group" style="margin-left:15px;">
                                        <button id="query-menu-btn" class="btn btn-primary rkibtn_search btn-sm"
                                                type="button" style="min-width: 40px;"><span
                                                class="glyphicon glyphicon-zoom-in i18n-flag"
                                                i18n-data="authority_query"></span>
                                        </button>
                                        <button id="reset-merchant-btn" class="btn btn-primary rkibtn_reset btn-sm reset"
                                                type="button" style="min-width: 40px;"><span
                                                class="glyphicon glyphicon-asterisk i18n-flag"
                                                i18n-data="authority_reset"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                    <div class="">
                        <div class="col-md-12 mt20">
                            <div class="col-md-6 menu-tree">
                                <ul id="menuTree" class="ztree"></ul>
                            </div>
                            <div class="col-md-6 menu-view">
                                <div class="menu-title">
                                    <span class="i18n-flag" i18n-data="menu_mgt"></span>
                                </div>
                                <form role="form" id="menu-update-form" action="/menu/update" class="ajax-form submit" callback="reloadMenuTree">
                                    <div class="row">
                                        <div class="col-md-11 mt10">
                                            <div class="input-group col-md-12">
                                                <div class="col-md-4">
                                                    <span class="i18n-flag" i18n-data=""
                                                        style="text-align: left;">ID</span><span>:</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input  class="form-control input-sm" type="text"
                                                             placeholder="" name="id" id="menu_id" readonly>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-11 mt10">
                                            <div class="input-group col-md-12">
                                                <div class="col-md-4">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="menu_name"
                                                          style="text-align: left;"></span><span>:</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input  class="form-control input-sm i18n-flag" type="text" placeholder="ph_tps_name" name="name" id="menu_name" datatype="name" maxlength="30" >
                                                </div>
                                                <div class="col-sm-8 hint-color col-md-offset-4"></div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-11 mt10">
                                            <div class="input-group col-md-12">
                                                <div class="col-md-4">
                                                    <span class="i18n-flag" i18n-data="url"
                                                          style="text-align: left;"></span><span>:</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input  class="form-control input-sm i18n-flag" type="text"
                                                            placeholder="ph_merchant_url" name="url" id="menu_url" datatype="*"  ignore="ignore" maxlength="100">
                                                </div>
                                                <div class="col-sm-8 hint-color col-md-offset-4"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-11 mt10">
                                            <div class="input-group col-md-12">
                                                <div class="col-md-4">
                                                    <span class="hint">*</span>
                                                    <span class="i18n-flag" i18n-data="in_order"
                                                          style="text-align: left;"></span><span>:</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input  class="form-control input-sm i18n-flag" type="text"
                                                      name="orderno" id="menu_order" errormsg="main_format_error" maxlength="2"
                                                            placeholder="ph_merchant_num" datatype="order" regexp="order|/^[1-9]\d?$/">
                                                </div>
                                                <div class="col-sm-8 hint-color col-md-offset-4"></div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row pt10">
                                        <div class="col-md-8 col-md-offset-4">
                                            <div class="btn-group" style="margin-left:15px;">

                                                <shiro:hasPermission name="menu:update">
                                                <button id="menu_save_btn" class="btn btn-primary rkibtn_save ajax-submit i18n-flag"
                                                        type="button" style="min-width: 40px;" i18n-data="authority_save">
                                                </button></shiro:hasPermission>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>

<%--新增菜单开始--%>
<div id="modal_add_menu" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content self-modal-content" style="">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" id=""
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title i18n-flag" i18n-data="add_child_menu"><span>新增下级菜单</span></h4>
            </div>
            <form class="form-horizontal ajax-form submit" action="/menu/save.do" id="menu-add-form" role="form" callback="reloadMenuTree">
							<input type="hidden" name="pid" id="add_pid"/>
                <div class="modal-body">
                    <div class="row form-group mt20">
                        <div class="col-sm-6">
                            <div class="col-sm-3 trt  mt5">
                                <div class="row form-group ">
                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="menu_name">菜单名称</span>:
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <input type="text" name="name"
                                       class="form-control i18n-flag" datatype="name" errormsg="main_format_error" regexp="zh1-30|/^[\u4E00-\u9FA5\uF900-\uFA2DA-Za-z0-9_（）\(\)\-]{1,30}$/"
                                       placeholder="ph_merchant_tag" id="add_new_menu" maxlength="30">
                            </div>
                            <div class="col-sm-8 hint-color"></div>
                        </div>
                        <div class="col-sm-6">
                            <div class="col-sm-3 trt  mt5">
                                <div class="row form-group ">
                                    <span class="i18n-flag" i18n-data="url">路径</span>:
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <input type="text" name="url"
                                       class="form-control i18n-flag"
                                       placeholder="ph_merchant_url" datatype="*" id="add_new_menu_url" ignore="ignore">
                            </div>
                            <div class="col-sm-8 hint-color"></div>
                        </div>
                        <div class="col-sm-6">
                            <div class="col-sm-3 trt  mt5">
                                <div class="row form-group ">
                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="in_order">排序</span>:
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <input type="text" name="orderno"
                                       class="form-control i18n-flag" errormsg="main_format_error"
                                       placeholder="ph_merchant_num" datatype="order" id="add_new_menu_order" regexp="order|/^[1-9]\d?$/">
                            </div>
                            <div class="col-sm-8 hint-color"></div>
                        </div>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" id="menu-add-save"
                            class="btn btn-primary rkibtn_save ajax-submit i18n-flag" i18n-data="authority_save" >保存
                    </button>
                    <button type="button" class="btn btn-primary my-primary i18n-flag" id="menu-add-cancel"
                            data-dismiss="modal" i18n-data="authority_close">取消
                    </button>
                </div>
            </form>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--新增功能结束--%>
</section>
<script src="${ctx}/js/auth/menu.js"></script>
<script src="${ctx}/js/plugin/validform/js/Validform_v5.3.2.js"></script>
<script>
$(document).ready(function(e) {
	onLoadZTree(1);
});
//增加添加和删除菜单按钮
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;

    var addStr = "";
		/* 新增 */
		addStr += ("<shiro:hasPermission name="menu:save"><span class='button add' id='addBtn_" + treeNode.tId + "' title=\'"+I('add_node')+"\' data-toggle='modal' data-target='#modal_add_menu'></span></shiro:hasPermission> ");
		/* 删除  */
		addStr += ("<shiro:hasPermission name="menu:delete"><span class='button remove' id='removeBtn_" + treeNode.tId + "' title=\'"+I('remo_node')+"\' ></span></shiro:hasPermission> "); //定义添加,删除按钮
    sObj.after(addStr); //加载添加和删除按钮

//绑定添加事件，并定义添加操作
		/* 新增*/ <shiro:hasPermission name="menu:save">
    var addBtn = $("#addBtn_"+treeNode.tId);
    if (addBtn) {
        addBtn.bind("click", function(){
            var zTree = $.fn.zTree.getZTreeObj("menuTree");
            $("#modal_add_menu").Validform().resetForm();
        });
    }
		 </shiro:hasPermission>

//绑定删除菜单事件
		/* 删除*/ <shiro:hasPermission name="menu:delete">
    var removeBtn = $("#removeBtn_"+treeNode.tId);
    if (removeBtn) {
        removeBtn.bind("click", function(){
            PAX_OBJECT.Messenger.confirm(I("confirm_delete_data"),function(){
                var zTree = $.fn.zTree.getZTreeObj("menuTree");
                if(treeNode.id == 0){
                    PAX_OBJECT.Messenger.alert('error', I("root_not_remove"));
                    return;
                }
                PAX_FUNCTION.ajaxPost(contextPath+"/menu/delete.do",{"id":treeNode.id},
                        function(data){
                            if(data.success == "true"){
                                PAX_OBJECT.Messenger.alert('success', data.msg);
                                $(this).parent().parent().remove();
                                var CurrentId = $("#search_menu_id").val();
                                onLoadZTree(CurrentId);
                            }else{
                                PAX_OBJECT.Messenger.alert('error', data.msg);
                            }

                        },function(data){
                            PAX_OBJECT.Messenger.alert('error', data.msg);
                        });

            });



        });
    }
		 </shiro:hasPermission>
};

function removeHoverDom(treeId, treeNode) {
	/* 新增 */<shiro:hasPermission name="menu:save">
    $("#addBtn_"+treeNode.tId).unbind().remove();
	  </shiro:hasPermission>
	/* 删除 */<shiro:hasPermission name="menu:delete">
	$("#removeBtn_"+treeNode.tId).unbind().remove();
	 </shiro:hasPermission>
};
</script>