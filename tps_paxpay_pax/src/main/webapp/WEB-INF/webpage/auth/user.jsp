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
					<h3 class="box-title i18n-flag" i18n-data="user_man_full"></h3>
				</div><!-- /.box-header -->
				<div class="box-body">
					<form class="search-form" for-table="userTable">
						<div style="padding-top: 5px;">
							<div class="row">
								<div class="col-md-6">
										<div class="input-group col-md-12">
											<div class="col-md-3">
												<span class="input-group-addon i18n-flag" i18n-data="site"></span>
											</div>
										<div class="col-md-8">
											<select name="site_id" data-action="/site/listAll.do" id="user_sel_site"
												name-rel="name" value-rel="id" class="form-control input-sm ajax-select">
												<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="input-group col-md-12">
										<div class="col-md-3">
											<span class="input-group-addon i18n-flag" i18n-data="user_name"></span>
										</div>
										<div class="col-md-8">
											<input  type="text" class="form-control i18n-flag input-sm"
													name="name" maxlength="30" placeholder="" />
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="input-group col-md-12">
										<div class="col-md-3">
											<span class="input-group-addon i18n-flag" i18n-data="org"></span>
										</div>
										<div class="col-md-8">
											<input id="organizationId_s" name="org_id" class="form-control input-sm" style="display: none"/>
											<span>
												<button type="button" id="orgBtn" style="background:white; border-color:#d2d6de;font-size: 12px;"
													class="btn btn-maroon dropdown-toggle form-control input-sm self-text-hide"
													data-toggle="dropdown" aria-expanded="false">
													<span class="drop-span i18n-flag" i18n-data=""></span>
													<span class="fa fa-caret-down"></span>
												</button>
												<ul class="dropdown-menu">
													<li>
														<ul id="mOrgTree" class="ztree self-ztree" ></ul>
													</li>
												</ul>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
							<div class="btn-group" id="myBtnBox">
								<button id="auth_search" type="button" class="btn btn-primary btn-sm rkibtn_search search">
									<i class="fa fa-search"></i>
									<span class="i18n-flag" i18n-data="authority_query"></span>
								</button>
								<button id="auth_reset" type="button" class="btn btn-primary btn-sm  rkibtn_reset reset">
									<i class="fa fa-power-off"></i>
									<span class="i18n-flag" i18n-data="authority_reset"></span>
								</button>
								<shiro:hasPermission name="user:save">
									<button id="auth_add" style="display:block" type="button" table-rel="#userTable"
										class="btn btn-primary btn-sm  rkibtn_add" data-toggle="modal"
										data-target="#modal_new">
										<span class="glyphicon glyphicon-plus"></span>
										<span class="i18n-flag" i18n-data="authority_add"></span>
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="user:delete">
									<button id="user_batchdelete" style="display:block" type="button" action="/user/delete.do"  table-rel="#userTable"
										param-name="ids" confirmmsg=main_confirm_remove  action-type="batchdel"
										class="btn btn-primary btn-sm  rkibtn_batchdel ajax-button i18n-flag">
										<i class="fa fa-trash-o"></i>
										<span class="i18n-flag" i18n-data="delete_all"></span>
									</button>
								</shiro:hasPermission>
							</div><!-- /.btn-group -->
						</div>
					</form>
					<table id="userTable" class="table  table-hover pax-datatable-cls ajax-table"
						data-action="/user/list.do" autoload optionfn="userTableOption" >
						<thead>
							<tr>
								<th width="8%" class="dataTable-td"><input type="checkbox" class="checkall"></th>
								<th width="8%" class="dataTable-td">ID</th>
								<th width="17%" class="dataTable-td i18n-flag" i18n-data="user_login_name"></th>
								<th width="17%" class="dataTable-td i18n-flag" i18n-data="user_name"></th>
								<th width="15%" class="dataTable-td i18n-flag" i18n-data="org"></th>
								<th width="12%" class="dataTable-td i18n-flag" i18n-data="tel_number"></th>
								<th width="15%" class="dataTable-td i18n-flag" i18n-data="start_using"></th>
								<th width="32%" class="dataTable-td i18n-flag" i18n-data="authority_operation"></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<%--新增用户开始--%>
					<div id="modal_new" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
						aria-hidden="true" data-backdrop="static" >
						<div class="modal-dialog pax-modal">
							<div class="modal-content self-modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										<span id="modalnew_title" class=" i18n-flag" i18n-data="user_add_user"></span>
									</h4>
								</div>
								<form class="form-horizontal ajax-form submit" action="/user/save.do" role="form" id="addForm">
									<div class="modal-body">
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="site"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<select name="site_id" class="form-control ajax-select" id="user_add_site" name-rel="name"  value-rel="id" data-action="/site/listAll.do" rely-dialog data-refresh datatype="*">
														<option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
													</select>
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="org"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input id="organizationId_add" name="org_id" class="form-control input-sm" style="display: none" datatype="*">
													<span>
														<button type="button" id="orgBtnAdd" style="background:white; border-color:#d2d6de;"
															class="btn btn-maroon dropdown-toggle form-control input-sm self-text-hide"
															data-toggle="dropdown" aria-expanded="false"/>
															<%--<span class="drop-span i18n-flag" i18n-data="cposmer_choose">请选择</span>--%>
															<%--<span class="fa fa-caret-down"></span>--%>

														</button>
														<ul class="dropdown-menu">
															<li>
																<ul id="mOrgTreeAdd" class="ztree self-ztree" ></ul>
															</li>
														</ul>
													</span>
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="user_login_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="loginname"
														class="form-control i18n-flag" datatype="/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{4,20}$/"
														errormsg="main_format_error" maxlength="20" placeholder="ph_tps_logna" />
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="user_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="name"
														class="form-control i18n-flag" datatype="name"
														   maxlength="30" placeholder="ph_tps_name"/>
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="user_login_password"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="password"  class="form-control i18n-flag" datatype="pwd"
														id="pwd" maxlength="20" placeholder="ph_change_pw" />
													<input type="hidden" name="password" id="pwdHide"/>
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
											<div class="col-sm-6">
											<div class="col-sm-3 trt  mt5">
												<div class="row form-group ">
													<span class="hint">*</span>
													<span class="i18n-flag" i18n-data="main_confirm_pwd"></span>:
												</div>
											</div>
											<div class="col-sm-8">
												<input type="password" class="form-control i18n-flag" datatype="pwd,rechk" id="conformPwd" maxlength="20" placeholder="ph_change_pw">
											</div>
											<div class="col-sm-8 hint-color"></div>
										</div>
									</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="tel_number"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" class="form-control i18n-flag" name="phoneno" datatype="tel|m"
														   ignore="ignore" placeholder="ph_merchant_three" errormsg="main_format_error">
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="mail_box"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" class="form-control i18n-flag" name="email" datatype="e"
														   ignore="ignore" placeholder="ph_merchant_add_two">
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
									</div>
									<div class="modal-footer" style="margin-top:60px">
										<button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit"
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
					<%--新增用户结束--%>
					<%--修改用户开始--%>
					<div id="modal_update" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
						aria-hidden="true" data-backdrop="static" action="/user/detail.do" >
						<div class="modal-dialog pax-modal">
							<div class="modal-content self-modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										<span class=" i18n-flag" i18n-data="modify_user"></span>
									</h4>
								</div>
								<form class="form-horizontal ajax-form submit" action="/user/update.do" role="form" id="updateForm">
									<div class="modal-body">
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="site"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="site.name" disabled class="form-control"/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">ID:</div>
												</div>
											<div class="col-sm-8">
												<input type="text" name="id" class="form-control " readonly/>
											</div>
										</div>
									</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="user_login_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="loginname" class="form-control" disabled/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="user_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="name"
																 class="form-control i18n-flag i18n-flag" datatype="name" placeholder="ph_tps_name"
																 maxlength="30"/>
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="hint">*</span>
														<span class="i18n-flag" i18n-data="org"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="org.name" class="form-control " disabled />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="tel_number"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="phoneno" class="form-control i18n-flag" datatype="tel|m"
														   ignore="ignore" placeholder="ph_merchant_three" errormsg="main_format_error">
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="mail_box"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="email" class="form-control i18n-flag" datatype="e"
														   ignore="ignore" placeholder="ph_merchant_add_two">
												</div>
												<div class="col-sm-8 hint-color"></div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit"
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
					<%--修改用户结束--%>

					<%--角色分配开始--%>
					<div id="modal_auth" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
						aria-hidden="true" data-backdrop="static">
						<div class="modal-dialog pax-modal">
							<div class="modal-content self-modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										<span class="i18n-flag" i18n-data="distr_role"></span>
									</h4>
								</div>
								<input type="hidden" id="for_modal_auth" name="id"/>
								<div class="modal-body">
									<form class="search-form" for-table="getRolesByUserTable">
										<div style="padding: 5px;border-bottom: 1px solid #f4f4f4;">
											<div class="row">
												<div class="col-md-6">
													<div class="input-group col-md-12">
														<div class="col-md-3">
															<span class="input-group-addon i18n-flag" i18n-data="role_name"></span>
														</div>
														<div class="col-md-8">
															<input  type="text" class="form-control input-sm" name="name" id="role_name"
																maxlength="20"/>
														</div>
													</div>
												</div>
												<div class="col-sm-4 col-sm-offset-2 btn-group" >
													<button type="button" class="btn btn-primary btn-sm rkibtn_search search">
														<i class="fa fa-search" id="search_role"></i>
														<span class="i18n-flag" i18n-data="authority_query"></span>
													</button>
													<button type="button" class="btn btn-primary btn-sm  rkibtn_reset reset">
														<i class="fa fa-power-off"></i>
														<span class="i18n-flag" i18n-data="authority_reset"></span>
													</button>
												</div>
											</div>
										</div>
									</form>
									<%--<form role="form" id="addRolesForUser">--%>
										<table id="getRolesByUserTable" class="table table-hover pax-datatable-cls ajax-table"
											data-action="/user/getRolesToUseByUser" data-rel="#for_modal_auth" rely-dialog optionfn="getRolesByUserTableOption" >
											<thead>
												<tr>
													<th width="30px" class="dataTable-td"><input type="checkbox" class="checkall"/></th>
													<th width="30px" class="dataTable-td">ID</th>
													<th width="200px" class="dataTable-td i18n-flag" i18n-data="role_name"></th>
													<th width="200px" class="dataTable-td i18n-flag" i18n-data="site"></th>
													<th width="200px" class="dataTable-td i18n-flag" i18n-data="org"></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									<%--</form>--%>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary rkibtn_save i18n-flag "
										i18n-data="authority_save" id="addRolesForUserBtn">
									</button>
									<button type="button" class="btn btn-primary my-primary i18n-flag"
										data-dismiss="modal" i18n-data="authority_close">
									</button>
								</div>
							</div><!-- /.modal-content -->
						</div><!-- /.modal-dialog -->
					</div><!-- /.modal -->
					<%--角色分配结束--%>
					<%--查看用户开始--%>
					<div id="modal_view" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
						 aria-hidden="true" data-backdrop="static" action="/user/detail.do">
						<div class="modal-dialog pax-modal">
							<div class="modal-content self-modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										<span class=" i18n-flag" i18n-data="view_user"></span>
									</h4>
								</div>
								<form class="form-horizontal ajax-form" role="form" id="viewForm">
									<div class="modal-body">
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="site"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="site.name" disabled class="form-control "/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														 ID:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="id" disabled class="form-control "/>
												</div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="user_login_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="loginname" disabled class="form-control "/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="user_name"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="name" class="form-control " disabled/>
												</div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="org"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="org.name" disabled class="form-control "/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="tel_number"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="phoneno" disabled class="form-control " />
												</div>
											</div>
										</div>
										<div class="row form-group mt20">
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="mail_box"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="email" disabled class="form-control "/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="start_using"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="status" disabled class="form-control "/>
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
													<input type="text" name="buildoper" disabled class="form-control "/>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="authority_create_time"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="builddatetime" disabled class="form-control "/>
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
													<input type="text" name="modifyoper" disabled class="form-control " />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="col-sm-3 trt  mt5">
													<div class="row form-group ">
														<span class="i18n-flag" i18n-data="authority_modify_time"></span>:
													</div>
												</div>
												<div class="col-sm-8">
													<input type="text" name="modifydatetime" disabled class="form-control "/>
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
							</div><!-- /.modal-content -->
						</div><!-- /.modal-dialog -->
					</div><!-- /.modal -->
					<%--查看用户结束--%>
				</div><!-- /.box-body -->
			</div><!-- /.box -->
		</div><!-- /.col -->
	</div><!-- /.row -->
	<script src="${ctx}/js/auth/user.js"></script>
</section>
<!-- 专有JS -->
<script type="text/javascript">

// 用户列表
function userTableOption($obj){
	return{
		"aoColumns": [
			{
				"mDataProp": null,"bSortable": false,"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id+ "'>");
				}
			},
			{"mDataProp": "id","sClass":"center","bSortable": false},
			{
				"mDataProp": "loginname","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "name","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "org","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData.name + "'>"+_T(sData.name)+"</span>");
				}
			},
			{
				"mDataProp": "phoneno","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + sData + "'>"+_T(sData)+"</span>");
				}
			},
			{
				"mDataProp": "status","sClass":"center","bSortable": false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(nTd).html("<span title='" + statusText(sData) + "'>"+statusText(sData)+"</span>");
				}
			},
			{
				"mDataProp": null,"bSortable": false,"sClass":"center2",
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					//$("body").data("tableData"+iRow,sData);
					var btns = '';
					
					/* 查看  */
					btns += ('<shiro:hasPermission name="user:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_view" data-rel="id=' + oData.id + '"><a title='+ I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>');

					/* 修改 */
					btns += ('<shiro:hasPermission name="user:update"> <div name="update" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_update" data-rel="id='+oData.id + '" table-rel="#userTable"><a title="'+I("authority_modify")+'"  href="javascript:void(0)" class="oper oper_modify"></a></div></shiro:hasPermission>');

						/* 冻结  */
						btns += ('<shiro:hasPermission name="user:frozen"><div name="frozen" class="oper_bg" style="display: block;"><a title="'+I("main_freeze")+'" href="javascript:void(0);" class="oper oper_lock ajax-button" action-type="lock" action="/user/frozen.do" table-rel="#userTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_freeze")+'"></a></div></shiro:hasPermission>');
						/* 解冻 */
						btns += ('<shiro:hasPermission name="user:unfrozen"><div name="frozen" class="oper_bg" style="display: block;"><a title="'+I("main_in_use")+'" href="javascript:void(0);" class="oper oper_unlock ajax-button" action-type="unlock" action="/user/unfrozen.do" table-rel="#userTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_thaw")+'"></a></div></shiro:hasPermission>');

					/* 分配角色  */
					btns += ('<shiro:hasPermission name="user:grantRoles"><div name="getauths" class="oper_bg" style="display: block;" data-toggle="modal"  data-target="#modal_auth" ><a title='+I("distr_role")+' href="javascript:void(0)" onclick="changeForUserId('+oData.id+')"  class="oper oper_getauth"></a></div></shiro:hasPermission>');

					/* 重置密码  */
					btns += ('<shiro:hasPermission name="user:resetPwd"><div name="reset" class="oper_bg" style="display: block;"  data-rel="id='+oData.id+'" table-rel="#getRolesByUserTable"><a title='+I("reset_pwd")+' href="javascript:void(0)" onclick="resetKey('+oData.id+')" class="oper oper_reset"></a></div></shiro:hasPermission>');

					/* 删除  */
					btns += ('<shiro:hasPermission name="user:delete"><div name="delete" class="oper_bg" style="display: block;" table-rel="#userTable"><a title="'+I("authority_batch_delete")+'" href="javascript:void(0);" class="oper oper_delete ajax-button" action-type="delete" action="/user/delete.do" table-rel="#userTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div></shiro:hasPermission>');
					$(nTd).html(btns);
				}
			}
		]
	}
}

</script>     
        
