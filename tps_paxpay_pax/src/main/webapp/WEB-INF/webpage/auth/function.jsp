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
							<h3 class="box-title i18n-flag" i18n-data="func_mgt_full"></h3>
					</div>

					<div class="box-body">
							<div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
									<div id="bankCheckList" class="row">
											<form role="form"  class="search-form" for-table="functionTable">
													<div class="row">
															<div class="col-md-6">
																	<div class="input-group col-md-12">
																			<div class="col-md-4">
															<span class="input-group-addon i18n-flag" i18n-data="site"
																		style="text-align: left;"></span>
																			</div>
																			<div class="col-md-8">
																					<select  class="form-control input-sm ajax-select" name-rel="name" value-rel="id" name="site_id" data-action="/site/listAll.do" id="function_sel_site">
																					<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed>
																							
																					</option>
																					</select>
																			</div>
																	</div>
															</div>
															<div class="col-md-6">
																	<div class="input-group col-md-12">
																			<div class="col-md-4">
															<span class="input-group-addon i18n-flag" i18n-data="main_func_name"
																		style="text-align: left;"></span>
																			</div>
																			<div class="col-md-8">
																					<input name="name" class="form-control input-sm" type="text"/>
																			</div>
																	</div>
															</div>
															<div class="col-md-6">
																	<div class="input-group col-md-12">
																			<div class="col-md-4">
																					<span class="input-group-addon i18n-flag" i18n-data="belonged_menu"
																						style="text-align: left;"></span>
																			</div>
																			<div class="col-md-8">
																					<input id="menu_search" name="menu_id" class="form-control input-sm" style="display: none">
																					<span>
																							<button type="button" id="menuSearchBtn" style="background:white; border-color:#d2d6de;font-size: 12px;"
																											class="btn btn-maroon dropdown-toggle form-control input-sm self-text-hide"
																											data-toggle="dropdown" aria-expanded="false">
																									<span class="fa fa-caret-down"></span>
																									<span class="drop-span i18n-flag" i18n-data="cposmer_choose"></span>
																							</button>
																							<ul class="dropdown-menu">
																									<li>
																											<ul id="menuSearchTree" class="ztree self-ztree"></ul>
																									</li>
																							</ul>
																					</span>
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
																			<button  class="btn btn-primary rkibtn_reset btn-sm reset"
																							type="button" style="min-width: 40px;"><span
																							class="glyphicon glyphicon-asterisk i18n-flag"
																							i18n-data="authority_reset"></span>
																			</button>
																			<shiro:hasPermission name="func:save">
																			<button id="modal_add_function_open" class="btn btn-primary rkibtn_add btn-sm"
																							data-toggle="modal" data-target="#modal_add_function" type="button" table-rel="#functionTable">
																				<span class="glyphicon glyphicon-plus"></span><span class="i18n-flag" i18n-data="authority_add"></span>
																			</button>
																			</shiro:hasPermission>
																			<shiro:hasPermission name="func:delete">
																			<button id="delete-all-function" class="btn btn-primary btn-sm  rkibtn_batchdel ajax-button i18n-flag"
																							 type="button" action-type="batchdel" action="/function/delete.do" table-rel="#functionTable" param-name="ids" confirmmsg=main_confirm_remove>
																				<span class="fa fa-trash-o"></span><span class="i18n-flag" i18n-data="delete_all"></span>
																			</button>
																			</shiro:hasPermission>
																	</div>
															</div>
													</div>

											</form>

									</div>
							</div>
							<!-- 操作栏 -->
							<table id="functionTable" width="100%"
										 class="pax-datatable-cls table table-responsive table-condensed ajax-table"
										 data-action="/function/list.do" autoload optionfn="functionTableOption">

									<thead>
									<tr>
											<th width="5%" class="dataTable-td">
													<input type="checkbox" class="checkall">
											</th>
											<th width="10%" class="dataTable-td">ID
											</th>
											<th width="20%" class="dataTable-td i18n-flag" i18n-data="main_func_name">
												
											</th>
											<th width="20%" class="dataTable-td i18n-flag" i18n-data="belonged_menu">
												
											</th>
											<th width="17%" class="dataTable-td i18n-flag" i18n-data="site">
													
											</th>
											<th width="23%" class="dataTable-td i18n-flag"
													i18n-data="authority_operation">
											</th>
									</tr>
									</thead>
									<tbody>

									</tbody>
							</table>

							<%--新增功能开始--%>
							<div id="modal_add_function" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
									 data-backdrop="static">
									<div class="modal-dialog">
											<div class="modal-content self-modal-content" style="">
													<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" id=""
																			aria-label="Close"><span
																			aria-hidden="true">&times;</span></button>
															<h4 class="modal-title i18n-flag" i18n-data="add_func"><span></span></h4>
													</div>
													<form class="form-horizontal ajax-form submit" action="/function/save.do" method="post"
																id="function_add_form" role="form"
																onkeydown="if(event.keyCode==13)return false;">
															<div class="modal-body" style="min-height: 400px">
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="hint">*</span><span class="i18n-flag" i18n-data="site"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<select name="site_id" class="form-control ajax-select" data-action="/site/listAll.do" rely-dialog data-refresh  id="site"
																											datatype="*" value-rel="id" name-rel="name">
																									<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
																							</select>
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																								<span class="hint">*</span>
																								<span class="i18n-flag" i18n-data="main_func_name"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="name" maxlength="30"
																										 class="form-control i18n-flag" datatype="name"
																										 placeholder="ph_tps_name" >
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>
																	</div>
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="hint">*</span><span class="i18n-flag" i18n-data="belonged_menu"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input id="add_menu_function" name="menu_id" class="form-control input-sm i18n-flag" style="display: none" datatype="*" nullmsg="main_this_fill">
																							<span>
																									<button type="button" id="addMenuFunctionBtn" style="background:white; border-color:#d2d6de;font-size: 12px;"
																													class="btn btn-maroon dropdown-toggle form-control input-sm self-text-hide"
																													data-toggle="dropdown" aria-expanded="false">
																											<span class="fa fa-caret-down"></span>
																											<span class="drop-span i18n-flag" i18n-data="cposmer_choose"></span>
																									</button>
																									<ul class="dropdown-menu">
																											<li>
																													<ul id="addMenuFunctionTree" class="ztree self-ztree" datatype="*"></ul>
																											</li>
																									</ul>
																							</span>
																					</div>

																					<div class="col-sm-8 hint-color"></div>
																			</div>

																	</div>

															</div>

															<div class="modal-footer">
																	<button type="button" formtarget="function-add-form" id="function-add-save"
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
							<%--新增功能结束--%>

							<%--修改功能开始--%>
							<div id="modal_update_function" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
									 data-backdrop="static" action="/function/detail.do">
									<div class="modal-dialog">
											<div class="modal-content self-modal-content" style="">
													<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																			aria-label="Close"><span
																			aria-hidden="true">&times;</span></button>
															<h4 class="modal-title i18n-flag" i18n-data="mod_func"><span></span></h4>
													</div>
													<form class="form-horizontal ajax-form submit" action="/function/update.do" method="post"
																id="function-update-form" role="form"
																onkeydown="if(event.keyCode==13)return false;">
															<div class="modal-body">
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="hint">*</span><span class="i18n-flag" i18n-data="site">站点</span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="site.name" disabled
																										 class="form-control">
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																								<span class="hint">*</span>
																								<span class="i18n-flag" i18n-data="">ID</span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="id"
																										 class="form-control i18n-flag" readonly>
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>
																	</div>
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="hint">*</span><span class="i18n-flag" i18n-data="main_func_name">功能名称</span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="name" maxlength="30"
																										 class="form-control i18n-flag" placeholder="ph_tps_name" datatype="name" >
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>

																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="hint">*</span><span class="i18n-flag" i18n-data="belonged_menu"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="menu.name"
																										 class="form-control i18n-flag" placeholder="" disabled>
																					</div>
																					<div class="col-sm-8 hint-color"></div>
																			</div>

																	</div>

															</div>

															<div class="modal-footer">
																	<button type="button" formtarget="function-update-form" id="function-modify-save"
																					class="btn btn-primary rkibtn_save i18n-flag ajax-submit" i18n-data="authority_save">
																	</button>
																	<button type="button" class="btn btn-default my-primary i18n-flag" id="function-modify-cancel"
																					data-dismiss="modal" i18n-data="authority_close">
																	</button>
															</div>
													</form>

											</div>
											<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
							</div>
							<%--修改功能结束--%>

							<%--查看功能开始--%>
							<div id="modal_view_function" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
									 data-backdrop="static" action="/function/detail.do">
									<div class="modal-dialog">
											<div class="modal-content self-modal-content" style="">
													<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" id="add_merchant_close"
																			aria-label="Close"><span
																			aria-hidden="true">&times;</span></button>
															<h4 class="modal-title i18n-flag" i18n-data="view_func"><span></span></h4>
													</div>
													<form class="form-horizontal ajax-form" role="form">
															<div class="modal-body">
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="i18n-flag" i18n-data="site"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="site.name" disabled
																										 class="form-control">
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
																										 class="form-control i18n-flag"  readonly>
																					</div>
																			</div>
																	</div>
																	<div class="row form-group mt20">
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="i18n-flag" i18n-data="main_func_name"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="name"
																										 class="form-control i18n-flag" readonly>
																					</div>
																			</div>
																			<div class="col-sm-6">
																					<div class="col-sm-3 trt  mt5">
																							<div class="row form-group ">
																									<span class="i18n-flag" i18n-data="belonged_menu"></span>:
																							</div>
																					</div>
																					<div class="col-sm-8">
																							<input type="text" name="menu.name"
																										 class="form-control i18n-flag" readonly>
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
																										 class="form-control i18n-flag" readonly>
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
																										 class="form-control i18n-flag" readonly>
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
																										 class="form-control i18n-flag" readonly>
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
																										 class="form-control i18n-flag" readonly>
																					</div>
																			</div>

																	</div>

															</div>

															<div class="modal-footer">
																	<button type="button" class="btn btn-primary my-primary i18n-flag"
																					data-dismiss="modal" i18n-data="authority_close">
																	</button>
															</div>
													</form>

											</div>
											<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
							</div>
							<%--新增功能结束--%>

					</div>
			</div>
	</div>
	</div>
</section>

<script src="${ctx}/js/auth/function.js"></script>
<script>
function functionTableOption($obj){
	return{
		"aoColumns": [
			{
				"mDataProp": null,
				"bSortable": false,
				"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id + "'>");
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
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "menu.name","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "site.name","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": null,
				"bSortable": false,
				"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
						//$("body").data("tableData"+iRow,sData);
						
						var btns = '';
						
						/* 详情  */
						btns += ('<shiro:hasPermission name="func:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_view_function" data-rel="id=' + oData.id + '"><a title='+ I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>');

						/* 修改 */
						btns += ('<shiro:hasPermission name="func:update"><div name="update" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_update_function" data-rel="id='+ oData.id + '" table-rel="#functionTable"><a title='+ I("authority_modify")+' href="javascript:void(0)" class="oper oper_modify"></a></div></shiro:hasPermission>');

						/* 删除 */
						btns += ('<shiro:hasPermission name="func:delete"> <div name="delete" class="oper_bg" style="display: block;"><a title='+ I("authority_batch_delete")+' href="javascript:void(0);" class="oper oper_delete ajax-button" action-type="delete" action="/function/delete.do" table-rel="#functionTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div></shiro:hasPermission>');

						$(nTd).html(btns);
				}
			}
        ]
    }
}
</script>




