<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>
<!-- Content Header (Page header) -->
<section class="" style="background-color: #fff;">
		<div class="row">
				<div class="col-lg-12 col-xs-12">
						<div class="box">
								<div class="box-header with-border">
										<h3 class="box-title i18n-flag" i18n-data="role_mol_mgt_full"></h3>
								</div><!-- /.box-header -->
								<div class="box-body">
										<form class="search-form" for-table="roleTable">
												<div style="padding-top: 5px;">
														<div class="row">
																<div class="col-md-6">
																		<div class="input-group col-md-12">
																				<div class="col-md-3">
																						<span class="input-group-addon i18n-flag" i18n-data="site"></span>
																				</div>
																				<div class="col-md-8">
																						<select name="site_id" data-action="/site/listAll.do" id="role_sel_site"
																									 name-rel="name" value-rel="id" class="form-control input-sm ajax-select">
																								<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
																						</select>
																				</div>
																		</div>
																</div>
														</div>
												</div>

												<div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
														<div class="btn-group" id="myBtnBox">
																<button id="auth_search" type="button" class="btn btn-primary btn-sm rkibtn_search search"><i
																				class="fa fa-search"></i><span class="i18n-flag"
																																			 i18n-data="authority_query"></span>
																</button>
																<button id="auth_reset" type="button" class="btn btn-primary btn-sm  rkibtn_reset reset"><i
																				class="fa fa-power-off"></i><span class="i18n-flag"
																																					i18n-data="authority_reset"></span>
																</button>
																<shiro:hasPermission name="rolemodel:save">
																<button id="auth_add" style="display:block" type="button"
																				class="btn btn-primary btn-sm  rkibtn_add" data-toggle="modal" table-rel="#roleTable"
																				data-target="#modal_new"><span class="glyphicon glyphicon-plus"></span><span
																				class="i18n-flag" i18n-data="authority_add"></span></button>
																</shiro:hasPermission>
														</div><!-- /.btn-group -->
												</div>
										</form>
										<table id="roleTable" class="table table-bordered table-hover pax-datatable-cls ajax-table"
													 data-action="/rolemodel/list.do" autoload optionfn="roleTableOption">
												<thead>
												<tr>
														<th width="60px">ID</th>
														<th width="100px" class="i18n-flag" i18n-data="name_propertity"></th>
														<th width="100px" class="i18n-flag" i18n-data="site"></th>
														<th width="100px" class="i18n-flag" i18n-data="tag"></th>
														<th width="150px;" class="i18n-flag" i18n-data="authority_operation"></th>
												</tr>
												</thead>
												<tbody>
												</tbody>
										</table>


										<div id="modal_new" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
												 aria-hidden="true" data-backdrop="static" >
												<div class="modal-dialog pax-modal">
														<div class="modal-content self-modal-content">
																<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">&times;</span></button>
																		<h4 class="modal-title"><span id="modalnew_title" class=" i18n-flag"
																																	i18n-data="role_add_role">新增角色模板</span>
																		</h4>
																</div>
																<form class="form-horizontal ajax-form submit" action="/rolemodel/save.do" role="form" id="addForm">
																		<div class="modal-body" style="min-height: 400px">
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="hint">*</span><span class="i18n-flag" i18n-data="site">站点</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<select name="site_id" class="form-control ajax-select" id="role_add_site" data-action="/site/listAll.do" name-rel="name" value-rel="id"
																														rely-dialog data-refresh  datatype="*">
																												<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed>--请选择--</option>
																										</select>
																								</div>
																								<div class="col-sm-8 hint-color"></div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="hint">*</span><span class="i18n-flag" i18n-data="rolemodel_name">角色模板名称</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="name" placeholder="ph_tps_name"
																													 class="form-control i18n-flag" datatype="name"
																											   maxlength="30" placeholder="请输入1-30位任意字符！">
																								</div>
																								<div class="col-sm-8 hint-color"></div>
																						</div>
																				</div>
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="hint">*</span>
																												<span class="i18n-flag" i18n-data="tag">标识</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																								<input type="text" name="tag"
																									class="form-control i18n-flag" placeholder="ph_merchant_cap" datatype="tag" maxlength="30"  regexp="tag|/^[A-Z]{1}$/">
																										</select>
																															
																								</div>
																								<div class="col-sm-8 hint-color"></div>
																						</div>
																				</div>

																		</div>
																		<div class="modal-footer">
																				<button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit"
																								i18n-data="authority_save">保存
																				</button>
																				<button type="button" class="btn btn-primary my-primary i18n-flag"
																								data-dismiss="modal" i18n-data="authority_close">取消
																				</button>

																		</div>
																</form>
														</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
										</div><!-- /.modal -->

										<%--修改权限开始--%>
										<div id="modal_update" class="modal fade modal-primary ajax-modal" action="/rolemodel/detail.do" tabindex="-1" role="dialog"
												 aria-hidden="true" data-backdrop="static" >
												<div class="modal-dialog pax-modal">
														<div class="modal-content self-modal-content">
																<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">&times;</span></button>
																		<h4 class="modal-title"><span class=" i18n-flag" i18n-data="modify_role">修改角色</span>
																		</h4>
																</div>
																<form class="form-horizontal ajax-form submit" action="/rolemodel/update.do" role="form" id="updateForm">
																		<div class="modal-body">
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																											<span class="hint">*</span>
																											<span class="i18n-flag" i18n-data="site">站点</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="site.name" class="form-control" disabled>
																								</div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												ID:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text"  name="id" class="form-control " readonly>
																								</div>
																						</div>
																				</div>
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="hint">*</span><span class="i18n-flag" i18n-data="rolemodel_name">角色模板名称</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="name" class="form-control" datatype="name" placeholder="ph_tps_name">
																								</div>
																								<div class="col-sm-8 hint-color"></div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																											<span class="hint">*</span>
																											<span class="i18n-flag" i18n-data="tag">标识</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="tag" class="form-control" disabled>
																								</div>
																								<div class="col-sm-8 hint-color"></div>
																						</div>
																				</div>
																		</div>
																		<div class="modal-footer">
																				<button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit"
																								i18n-data="authority_save">保存
																				</button>
																				<button type="button" class="btn btn-primary my-primary i18n-flag"
																								data-dismiss="modal" i18n-data="authority_close">取消
																				</button>

																		</div>
																</form>
														</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
										</div><!-- /.modal -->
										<%--修改角色权限--%>

										<%--分配权限开始--%>
										<div id="modal_auth" class="modal fade modal-primary " tabindex="-1" role="dialog"
												 aria-hidden="true" data-backdrop="static">
												<div class="modal-dialog pax-modal">
														<div class="modal-content self-modal-content">
																<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">&times;</span></button>
																		<h4 class="modal-title">
																				<span class=" i18n-flag" i18n-data="authority_assign_privilege">分配权限</span>
																		</h4>
																</div>
																<form action="" id="role_auth_form">
																		<div class="modal-body" style="max-height: 500px;overflow-y: scroll;overflow-x: hidden;">
																				<input type="hidden" id="for_modal_auth" name="id">
																				<input type="hidden" name="site_id" id="site_id_for_modal_auth"/>
																				<div class="col-md-12">
																						<div class="col-md-6">
																								<ul id="auth_tree" class="ztree"></ul>
																						</div>
																				</div>
																		</div>
																		<div class="modal-footer">
																				<button type="submit" class="btn btn-primary rkibtn_save i18n-flag "
																								i18n-data="authority_save" >保存
																				</button>
																				<button type="button" class="btn btn-primary my-primary i18n-flag"
																								data-dismiss="modal" i18n-data="authority_close">取消
																				</button>
																		</div>
																</form>
														</div>
														<!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
										</div><!-- /.modal -->
										<%--分配权限结束--%>


										<%--查看角色开始--%>
										<div id="modal_view" class="modal fade modal-primary ajax-modal" action="/rolemodel/detail.do" tabindex="-1" role="dialog"
												 aria-hidden="true" data-backdrop="static">
												<div class="modal-dialog pax-modal">
														<div class="modal-content self-modal-content">
																<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">&times;</span></button>
																		<h4 class="modal-title"><span class=" i18n-flag" i18n-data="role_view">查看角色</span>
																		</h4>
																</div>
																<form class="form-horizontal ajax-form" role="form" id="viewForm">
																		<div class="modal-body">
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="site">站点</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="site.name" disabled class="form-control ">
																								</div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												ID:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="id" disabled class="form-control ">
																								</div>
																						</div>
																				</div>
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="rolemodel_name">角色模板名称</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="name" disabled class="form-control ">
																								</div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="tag">tag</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="tag" class="form-control "
																													 disabled>
																								</div>
																						</div>
																				</div>

																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="authority_creator">创建人</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="buildoper" disabled
																													 class="form-control ">
																								</div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="authority_create_time">创建时间</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="builddatetime" disabled class="form-control ">
																								</div>
																						</div>
																				</div>
																				<div class="row form-group mt20">
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="mod_person">修改人</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="modifyoper" disabled class="form-control " >
																								</div>
																						</div>
																						<div class="col-sm-6">
																								<div class="col-sm-3 trt  mt5">
																										<div class="row form-group ">
																												<span class="i18n-flag" i18n-data="authority_modify_time">修改时间</span>:
																										</div>
																								</div>
																								<div class="col-sm-8">
																										<input type="text" name="modifydatetime" disabled
																													 class="form-control ">
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
														</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
										</div><!-- /.modal -->
										<%--查看角色结束--%>

								</div><!-- /.box-body -->
						</div><!-- /.box -->
				</div><!-- /.col -->
		</div><!-- /.row -->
		<script src="${ctx}/js/auth/roleModel.js"></script>

</section>
<script>
function roleTableOption($obj){
	return{
		"aoColumns": [
			{
				"mDataProp": "id","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+sData+"</span>");
				}
			},
			{
				"mDataProp": "name","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+sData+"</span>");
				}
			},
			{
				"mDataProp": "site","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" +  sData.name  + "'>"+ sData.name +"</span>");
				}
			},
			{
				"mDataProp": "tag","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+sData+"</span>");
				}
			},
			{
				"mDataProp": null,
				"bSortable": false,
				"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					var btns = '';
					
					/* 查看  */
					btns += ('<shiro:hasPermission name="rolemodel:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_view" data-rel="id='+oData.id+'"><a title='+ I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>');

					/* 修改 */
					btns += ('<shiro:hasPermission name="rolemodel:update"> <div name="update" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_update" data-rel="id='+oData.id+'" table-rel="#roleTable" ><a title='+ I("authority_modify")+' href="javascript:void(0)" class="oper oper_modify"></a></div></shiro:hasPermission>');

//					分配权限
					btns += ('<shiro:hasPermission name="rolemodel:grantAuths"><div name="getauths" class="oper_bg" style="display: block;" data-toggle="modal"  data-target="#modal_auth" data-rel="id='+oData.id+'"><a title='+I("allot_function")+' href="javascript:void(0)" onclick="javascript:changeForRoleId('+oData.id+','+oData.site.id+');getMenuTree(\'' + oData.id + '\');" class="oper oper_getauth"></a></div></shiro:hasPermission>');

					$(nTd).html(btns);
				}
			}
		]
	}
}
</script>




