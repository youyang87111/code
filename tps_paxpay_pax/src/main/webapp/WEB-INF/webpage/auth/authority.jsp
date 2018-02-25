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
                <h3 class="box-title i18n-flag" i18n-data="authority_man_full"></h3>
            </div>

            <div class="box-body">
                <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                    <div id="bankCheckList" class="row">
                        <form role="form"  class="search-form" for-table="authorityTable">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="site"
                                                  style="text-align: left;">站点</span>
                                        </div>
                                        <div class="col-md-8">
                                            <select name="site_id" data-action="/site/listAll.do" id="role_sel_site"
                                                    name-rel="name" value-rel="id" class="form-control input-sm ajax-select">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="authority_name"
                                                  style="text-align: left;"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <input class="form-control input-sm" type="text"
                                                   name="name"/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row pt10">
                                <div class="col-md-12">
                                    <div class="btn-group" style="margin-left:15px;">
                                        <button class="btn btn-primary rkibtn_search btn-sm search"
                                                type="button" style="min-width: 40px;"><span
                                                class="glyphicon glyphicon-zoom-in i18n-flag"
                                                i18n-data="authority_query"></span>
                                        </button>
                                        <button class="btn btn-primary rkibtn_reset btn-sm reset"
                                                type="button" style="min-width: 40px;"><span
                                                class="glyphicon glyphicon-asterisk i18n-flag"
                                                i18n-data="authority_reset"></span>
                                        </button>
																				<shiro:hasPermission name="auth:save">
                                        <button id="" class="btn btn-primary rkibtn_add btn-sm" table-rel="#authorityTable"
                                                data-toggle="modal" data-target="#modal_add" type="button"><span class="glyphicon glyphicon-plus"></span><span class="i18n-flag" i18n-data="authority_add"></span>
                                        </button>
																				</shiro:hasPermission>
																				<shiro:hasPermission name="auth:delete">
                                        <button id="auth_batchdelete" style="display:block" type="button" action="/authority/delete.do"  table-rel="#authorityTable"
                                                param-name="ids" confirmmsg=main_confirm_remove action-type="batchdel"
                                                class="btn btn-primary btn-sm  rkibtn_batchdel i18n-flag ajax-button"><i
                                                class="fa fa-trash-o"></i><span class="i18n-flag" i18n-data="delete_all"></span>
                                        </button>
																				</shiro:hasPermission>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- 操作栏 -->
                <table id="authorityTable" width="100%"
                       class="pax-datatable-cls table table-responsive table-condensed ajax-table"
                       data-action="/authority/list.do" autoload optionfn="authorityTableOption">
                    <thead>
                    <tr>
                        <th width="5%"><input type="checkbox" class="checkall"></th>
                        <th width="8%" class="dataTable-td">ID
                        </th>
                        <th width="20%" class="dataTable-td i18n-flag" i18n-data="name_propertity">
                            名称
                        </th>
                        <th width="20%" class="dataTable-td i18n-flag" i18n-data="tag">
                            标识
                        </th>
                        <th width="17%" class="dataTable-td i18n-flag" i18n-data="site">
                            站点
                        </th>
                        <th width="23%" class="dataTable-td i18n-flag"
                            i18n-data="authority_operation">
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>

                <%--新增权限开始--%>
                <div id="modal_add" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static" style="z-index:2000;">
                    <div class="modal-dialog">
                        <div class="modal-content self-modal-content" style="">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title i18n-flag" i18n-data="authority_add_privilege_info"><span></span></h4>
                            </div>
                            <form class="form-horizontal ajax-form submit" action="/authority/save.do" method="post"
                                  id="authority-add-form" role="form"
                                 >
                                <div class="modal-body">
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="site"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <select name="site_id" class="form-control ajax-select" id="role_add_site" data-action="/site/listAll.do" name-rel="name" value-rel="id"
                                                        rely-dialog data-refresh  datatype="*">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag"
                                                                                     i18n-data="authority_name"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="name"
                                                       class="form-control i18n-flag" datatype="name"
                                                        maxlength="30" placeholder="ph_tps_name">
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>
                                    </div>
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="tag"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="tag" maxlength="30" placeholder="ph_merchant_tag"
                                                       class="form-control i18n-flag"  datatype="tag">
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>

                                    </div>

                                </div>

                                <div class="modal-footer">
                                    <button type="button" formtarget="bankmerchant-add-form" id="site-add-save"
                                            class="btn btn-primary rkibtn_save i18n-flag ajax-submit" i18n-data="authority_save">
                                    </button>
                                    <button type="button" class="btn btn-default my-primary i18n-flag" id="site-add-cancel"
                                            data-dismiss="modal" i18n-data="authority_close">
                                    </button>
                                </div>
                            </form>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <%--新增权限结束--%>

                <%--修改权限开始--%>
                <div id="modal_update" class="modal fade modal-primary ajax-modal" action="/authority/detail.do" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static" style="z-index:2000;">
                    <div class="modal-dialog">
                        <div class="modal-content self-modal-content" style="">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title i18n-flag" i18n-data="authority_modify_privilege_info"><span></span></h4>
                            </div>
                            <form class="form-horizontal ajax-form submit" method="post"
                                  id="modify-site-form" role="form" action="/authority/update.do">
                                <div class="modal-body">
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="site"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="site.name" class="form-control i18n-flag" disabled >
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="">ID</span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="id" class="form-control i18n-flag" readonly>
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>
                                    </div>
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="authority_name"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="name" maxlength="30"
                                                       class="form-control i18n-flag" datatype="name" placeholder="ph_tps_name" >
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="hint">*</span><span class="i18n-flag" i18n-data="tag"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="tag" maxlength="30" placeholder="ph_merchant_tag"
                                                       class="form-control i18n-flag" datatype="tag" >
                                            </div>
                                            <div class="col-sm-8 hint-color"></div>
                                        </div>

                                    </div>

                                </div>

                                <div class="modal-footer">
                                    <button type="button"
                                            class="btn btn-primary rkibtn_save i18n-flag ajax-submit" i18n-data="authority_save">
                                    </button>
                                    <button type="button" class="btn btn-default my-primary i18n-flag"
                                            data-dismiss="modal" i18n-data="authority_close">
                                    </button>
                                </div>
                            </form>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <%--修改权限结束--%>

                <%--查看权限开始--%>
                <div id="modal_view" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static" style="z-index:2000;" action="/authority/detail.do">
                    <div class="modal-dialog">
                        <div class="modal-content self-modal-content" style="">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title i18n-flag" i18n-data="view_auth"><span></span></h4>
                            </div>
                            <form class="form-horizontal ajax-form " role="form">
                                <div class="modal-body">
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="site"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="site.name"
                                                       class="form-control i18n-flag" disabled>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="">ID</span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="id"
                                                       class="form-control i18n-flag" disabled>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="authority_name"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="name"
                                                       class="form-control i18n-flag" disabled>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="tag"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="tag"
                                                       class="form-control i18n-flag" disabled>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="buildoper"
                                                       class="form-control i18n-flag"  disabled>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="authority_create_time"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="builddatetime"
                                                       class="form-control i18n-flag"  disabled>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group mt20">
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="mod_person"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="modifyoper"
                                                       class="form-control i18n-flag" disabled>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="col-sm-3 trt  mt5">
                                                <div class="row form-group ">
                                                    <span class="i18n-flag" i18n-data="authority_modify_time"></span>:
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="text" name="modifydatetime"
                                                       class="form-control i18n-flag"  disabled>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">

                                    <button type="button" class="btn btn-default my-primary my-primary i18n-flag"
                                            data-dismiss="modal" i18n-data="authority_close">
                                    </button>
                                </div>
                            </form>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <%--查看权限结束--%>


                <%--分配功能开始--%>
                <div id="modal_fun" class="modal fade modal-primary" tabindex="-1" role="dialog"
                     aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog pax-modal">
                        <div class="modal-content self-modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span class=" i18n-flag"
                                                              i18n-data="allot_function"></span>
                                </h4>
                            </div>
                            <form role="form" id="auth_fun_form">
                            <input hidden="" id="for_modal_funcs"/>
                            <div class="modal-body" style="max-height: 500px;overflow-y: scroll;overflow-x: hidden;">
                                <div class="col-md-12">
                                    <div class="col-md-6">
                                        <ul id="func_tree" class="ztree"></ul>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary rkibtn_save i18n-flag"
                                        i18n-data="authority_save">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag"
                                        data-dismiss="modal" i18n-data="authority_close">
                                </button>
                            </div>
                            </form>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <%--分配功能结束--%>

            </div>
        </div>
    </div>
</div>

<script src="${ctx}/js/plugin/zTree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/js/auth/authority.js"></script>
</section>
<script>
function authorityTableOption($obj){
	return{
		"aoColumns": [
			{
				"mDataProp": null,
				"bSortable": false,
				"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id+"'>");
				}
			},
			{
				"mDataProp": "id","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+sData+"</span>");
				}
			},
			{
				"mDataProp": "name","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + _T(sData) + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "tag","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" +  sData  + "'>"+ sData +"</span>");
				}
			},
			{
				"mDataProp": "site","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData.name + "' class='site_name'>"+_T(sData.name)+"</span>");
				}
			},
			{
				"mDataProp": null,
				"bSortable": false,
				"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					//$("body").data("tableData"+iRow,sData);
					var btns = '';
					
					/* 查看 */<shiro:hasPermission name="auth:detail">
                    btns += ('<div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_view" data-rel="id='+oData.id+'"><a title='+ I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div>');
					</shiro:hasPermission>
					
					/* 修改*/ <shiro:hasPermission name="auth:update">
					btns += ('<div name="update" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_update" data-rel="id='+oData.id+'" table-rel="#authorityTable" ><a title='+ I("authority_modify")+' href="javascript:void(0)" class="oper oper_modify"></a></div>');
					 </shiro:hasPermission>
					
					/* 分配功能*/ <shiro:hasPermission name="auth:grantFuncs">
					btns += ('<div name="getfunction" class="oper_bg" style="display: block;" data-toggle="modal"  data-target="#modal_fun" data-rel="id='+oData.id+'"><a title='+I("allot_function")+' class="oper oper_getauth" onclick="javascript:changeForAuthId('+oData.id+','+oData.site.id+');getTree(\'' + oData.id + '\');"></a></div>');
					 </shiro:hasPermission>
					
					/* 删除 */<shiro:hasPermission name="auth:delete">
					btns += ('<div name="delete" class="oper_bg" style="display: block;"><a title='+ I("authority_batch_delete")+' href="javascript:void(0)" class="oper oper_delete ajax-button" action-type="delete" action="/authority/delete.do" table-rel="#authorityTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div>');
					</shiro:hasPermission>
					
					$(nTd).html(btns);
				}
			}
		]
	}
}
</script>