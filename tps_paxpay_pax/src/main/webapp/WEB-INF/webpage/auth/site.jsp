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
									<h3 class="box-title i18n-flag" i18n-data="site_mgt_full">站点管理</h3>
							</div>
	
							<div class="box-body">
									<div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
											<div id="bankCheckList" class="row">
													<form  class="search-form" for-table="siteTable">
															<div class="row">
																	<div class="col-md-6">
																			<div class="input-group col-md-12">
																					<div class="col-md-4">
																	<span class="input-group-addon i18n-flag" i18n-data="site_name"
																		style="text-align: left;">站点名称</span>
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
																									type="button" style="min-width: 40px;">
																						<i class="glyphicon glyphicon-zoom-in "></i>
																						<span class="i18n-flag" i18n-data="authority_query">查询</span>
																					</button>
																					<button class="btn btn-primary rkibtn_reset btn-sm reset"
																									type="button" style="min-width: 40px;"><span
																									class="glyphicon glyphicon-asterisk i18n-flag"
																									i18n-data="authority_reset">重置</span>
																					</button>
																					<shiro:hasPermission name="site:save">
																					<button id="modal_add_site_open" class="btn btn-primary rkibtn_add btn-sm"
																									data-toggle="modal" data-target="#modal_add_site" type="button" table-rel="#siteTable"><span class="glyphicon glyphicon-plus"></span><span class="i18n-flag" i18n-data="authority_add" >新增</span>
																					</button>
																					</shiro:hasPermission>
																					<shiro:hasPermission name="site:batch:delete">
																					<button id="delete_all_site" class="btn btn-primary btn-sm  rkibtn_batchdel ajax-button i18n-flag" action-type="batchdel" action="/site/delete.do" table-rel="#siteTable" param-name="ids" confirmmsg=main_confirm_remove type="button" >
																						<span class="glyphicon glyphicon-plus"></span><span class="i18n-flag" i18n-data="delete_all">批量删除</span>
																					</button>
																					</shiro:hasPermission>
																			</div>
																	</div>
															</div>
													</form>
	
											</div>
									</div>
									<!-- 操作栏 -->
									<table id="siteTable" width="100%"
												 class="pax-datatable-cls table table-responsive table-condensed ajax-table"
									data-action="/site/list.do" autoload optionfn="siteTableOption">
	
											<thead>
											<tr>
													<th width:="5%" class="dataTable-td">
															<input type="checkbox" class="checkall">
													</th>
													<th width="8%" class="dataTable-td">ID
													</th>
													<th width="25%" class="dataTable-td i18n-flag" i18n-data="site_name">
															站点名称
													</th>
													<th width="20%" class="dataTable-td i18n-flag" i18n-data="home_page">
															首页
													</th>
													<th width="10%" class="dataTable-td i18n-flag" i18n-data="tag">
															标识
													</th>
													<th width="10%;" class="dataTable-td i18n-flag"
															i18n-data="start_using">状态
													</th>
													<th width="25%;" class="dataTable-td i18n-flag"
															i18n-data="authority_operation">操作
													</th>
											</tr>
											</thead>
											<tbody>
	
											</tbody>
									</table>
	
									<%--新增站点开始--%>
									<div id="modal_add_site" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
											 data-backdrop="static">
											<div class="modal-dialog">
													<div class="modal-content self-modal-content" style="">
															<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" id=""
																					aria-label="Close"><span
																					aria-hidden="true">&times;</span></button>
																	<h4 class="modal-title i18n-flag" i18n-data="add_site"><span>新增站点</span></h4>
															</div>
															<form class="form-horizontal ajax-form submit" action="/site/save.do" method="post"
																		id="site-add-form" role="form">
																	<div class="modal-body">
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span><span class="i18n-flag" i18n-data="site_name">站点名称</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="name"
																													class="form-control i18n-flag" datatype="name" maxlength="30"
																													placeholder="ph_tps_name">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span>
																										<span class="i18n-flag" i18n-data="home_page">首页</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="url"
																												 class="form-control i18n-flag"
																												 placeholder="ph_merchant_url" datatype="*" maxlength="100">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
																			</div>
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span><span class="i18n-flag" i18n-data="tag">标识</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="tag" class="form-control i18n-flag"
																										   placeholder="ph_merchant_cap" datatype="/^[A-Z]{1}$/" maxlength="1" errormsg="main_format_error">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
	
																			</div>
	
																	</div>
	
																	<div class="modal-footer">
																			<button type="button" formtarget="site-add-form" id="site-add-save"
																							class="btn btn-primary rkibtn_save i18n-flag ajax-submit" i18n-data="authority_save" >保存
																			</button>
																			<button type="button" class="btn btn-default my-primary i18n-flag" id="site-add-cancel"
																							data-dismiss="modal" i18n-data="cp_cancel">取消
																			</button>
																	</div>
															</form>
	
													</div>
													<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
									</div>
									<%--新增功能结束--%>
	
									<%--修改站点开始--%>
									<div id="modal_update_site" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
											 data-backdrop="static"  action="/site/detail.do">
											<div class="modal-dialog">
													<div class="modal-content self-modal-content" style="">
															<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal"
																					aria-label="Close"><span
																					aria-hidden="true">&times;</span></button>
																	<h4 class="modal-title i18n-flag" i18n-data="mod_site"><span>修改站点</span></h4>
															</div>
															<form class="form-horizontal ajax-form submit" action="/site/update.do" method="post"
																		id="modify-site-form" role="form">
																	<div class="modal-body">
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span><span class="i18n-flag" i18n-data="site_name">站点名称</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="name"
																												 class="form-control i18n-flag" datatype="name"
																												 placeholder="ph_tps_name" maxlength="30">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span><span class="i18n-flag" i18n-data="">ID</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="id"
																												 class="form-control i18n-flag"
																													readonly id="site_update_id">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
	
																			</div>
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span>
																										<span class="i18n-flag" i18n-data="home_page">首页</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="url" maxlength="100"
																												 class="form-control i18n-flag" datatype="*"
																												 placeholder="ph_merchant_url">
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="hint">*</span><span class="i18n-flag" i18n-data="tag">标识</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="tag"
																												 class="form-control i18n-flag" placeholder="" readonly>
																							</div>
																							<div class="col-sm-8 hint-color"></div>
																					</div>
	
																			</div>
	
																	</div>
	
																	<div class="modal-footer">
																			<button type="button" formtarget="modify-site-form" id="modify-site-save"
																							class="btn btn-primary rkibtn_save i18n-flag ajax-submit" i18n-data="authority_save">保存
																			</button>
																			<button type="button" class="btn btn-default my-primary i18n-flag"
																							data-dismiss="modal" i18n-data="cp_cancel">取消
																			</button>
																	</div>
															</form>
	
													</div>
													<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
									</div>
									<%--修改站点结束--%>
	
									<%--查看站点开始--%>
									<div id="modal_view_site" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
											 data-backdrop="static"  action="/site/detail.do">
											<div class="modal-dialog">
													<div class="modal-content self-modal-content" style="">
															<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal"
																					aria-label="Close"><span
																					aria-hidden="true">&times;</span></button>
																	<h4 class="modal-title i18n-flag" i18n-data="view_site"><span>查看站点</span></h4>
															</div>
															<form class="form-horizontal ajax-form" role="form" id="view-site-form">
																	<div class="modal-body">
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="">ID</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="id"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="site_name">站点名称</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="name"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
																			</div>
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="home_page">首页</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="url"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="tag">标识</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="tag"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
	
																			</div>
	
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="start_using">状态</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="status"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="authority_creator">创建人</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="buildoper"
																												 class="form-control i18n-flag"  readonly>
																							</div>
																					</div>
	
	
																			</div>
	
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="authority_create_time">创建时间</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="builddatetime"
																												 class="form-control i18n-flag"  readonly>
																							</div>
																					</div>
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="mod_person">修改人</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="modifyoper"
																												 class="form-control i18n-flag" readonly>
																							</div>
																					</div>
	
																			</div>
																			<div class="row form-group mt20">
																					<div class="col-sm-6">
																							<div class="col-sm-3 trt  mt5">
																									<div class="row form-group ">
																											<span class="i18n-flag" i18n-data="authority_modify_time">修改时间</span>:
																									</div>
																							</div>
																							<div class="col-sm-8">
																									<input type="text" name="modifydatetime"
																												 class="form-control i18n-flag"  readonly>
																							</div>
																					</div>
	
	
																			</div>
	
																	</div>
	
																	<div class="modal-footer">
																			<button type="button" class="btn btn-primary my-primary i18n-flag"
																							data-dismiss="modal" i18n-data="authority_close">关闭
																			</button>
																	</div>
															</form>
	
													</div>
													<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
									</div>
									<%--查看站点结束--%>
	
							</div>
					</div>
			</div>
	</div>


</section>

<script>
function siteTableOption($obj) {
	return {
		"aoColumns" : [
			{
				"sWidth":"5%",
				"mDataProp" : null,
				"bSortable" : false,
				"sClass" : "center2",
				"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<input type='checkbox' name='checkList' value='"+ oData.id + "'>");
					}
				},
				{
					"sWidth":"8%",
					"mDataProp" : "id",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<span title='" + sData + "'>" + sData+ "</span>");
					}
				},
				{
          "sWidth":"16%",
					"mDataProp" : "name",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
					}
				},
				{
          "sWidth":"16%",
					"mDataProp" : "url",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
					}
				},
				{
					"sWidth":"16%",
					"mDataProp" : "tag",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
					}
				},
				{
					"sWidth":"16%",
					"mDataProp" : "status",
					"sClass" : "center",
					"bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						$(nTd).html("<span title='" + _T(statusText(sData)) + "'>" + _T(statusText(sData)) + "</span>");
					}
				},
				{
					"sWidth":"16%",
					"mDataProp" : null,
					"bSortable" : false,
					"sClass" : "center2",
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
						//$("body").data("tableData" + iRow, sData);
						
						var btns = '';
						
						/* 查看  */
						btns += ('<shiro:hasPermission name="site:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_view_site" data-rel="id=' + oData.id + '"><a title='+ I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>');

						/* 修改  */
						btns += ('<shiro:hasPermission name="site:update"><div name="update" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_update_site" data-rel="id='+oData.id + '" table-rel="#siteTable"><a title='+ I("authority_modify")+'  href="javascript:void(0)" class="oper oper_modify"></a></div></shiro:hasPermission>');

//						if(auditLock(oData.status)==1){
							/* 冻结  */
							btns += ('<shiro:hasPermission name="site:frozen"><div name="frozen" class="oper_bg" style="display: block;"><a title='+I("main_freeze")+' href="javascript:void(0);" class="oper oper_lock ajax-button " action-type="lock" action="/site/frozen.do" table-rel="#siteTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_freeze")+'"></a></div></shiro:hasPermission>');
//						}else{
							/* 解冻  */
							btns += ('<shiro:hasPermission name="site:unfrozen"><div name="frozen" class="oper_bg" style="display: block;"><a title='+I("thaw")+' href="javascript:void(0);" class="oper oper_unlock ajax-button" action-type="unlock" action="/site/unfrozen.do" table-rel="#siteTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_thaw")+'"></a></div></shiro:hasPermission>');
//						}
						/* 删除 */
						btns += ('<shiro:hasPermission name="site:delete"> <div name="delete" class="oper_bg" style="display: block;" table-rel="#siteTable"><a title='+ I("authority_batch_delete")+' href="javascript:void(0);" class="oper oper_delete ajax-button" action-type="delete" action="/site/delete.do" table-rel="#siteTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div></shiro:hasPermission>');

						$(nTd).html(btns);
					}
				} 
		]
	}
}
</script>