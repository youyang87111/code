<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>
<style type="">
    .text-r {
        text-align: right;
    }
    #filetxt{
        background-color: rgb(154, 216, 235);
        text-align: center;
        border-radius: 17px !important;
        color:white;
        border: 1px solid rgb(154, 216, 235);
        box-shadow: 0px 2px 2px rgba(154, 216, 235, 0.47);
        padding-left: 24px !important;
    }
</style>
<section class="" style="background-color: #fff;">
    <div class="row">
        <div class="col-lg-12 col-xs-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title i18n-flag" i18n-data="roll_out_merchant_full"></h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <form class="search-form" for-table="rMerTable">
                        <div style="">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="cp_merch_from"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <select id="mcr_s" class="form-control input-sm ajax-select" name-rel="name" value-rel="id" name="mcr" data-action="/common/listMcr?type=R">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_no"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="mid_s" name="mid" class="form-control input-sm" maxlength="15">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="cp_check_state"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <select id="astatus_s" name="auditstatus" class="form-control input-sm">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose"></option>
                                                <option value="0" class="i18n-flag" i18n-data="main_auditing"></option>
                                                <option value="1" class="i18n-flag" i18n-data="cp_no_pass"></option>
                                                <option value="2" class="i18n-flag" i18n-data="cp_pass"></option>
                                            </select></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_name"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="name_s" name="name" class="form-control input-sm" maxlength="30"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="start_using"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <select id="status_s" name="status" class="form-control input-sm">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose"></option>
                                                <option value="0" class="i18n-flag" i18n-data="main_none_use"></option>
                                                <option value="1" class="i18n-flag" i18n-data="main_freeze"></option>
                                                <option value="2" class="i18n-flag" i18n-data="main_in_use"></option>
                                            </select></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group col-md-12">
                                        <div class="col-md-4">
                                            <span class="input-group-addon i18n-flag" i18n-data="org"></span>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="rMerOrgId" name="orgId" class="form-control input-sm"
                                                   style="display: none"/>
                                            <span>
											<button type="button" id="rMerOrgBtn"
                                                    style="background:white; border-color:#d2d6de;"
                                                    class="btn btn-maroon dropdown-toggle form-control input-sm"
                                                    data-toggle="dropdown" aria-expanded="false">
												<span class="fa fa-caret-down"></span>
												<span class="i18n-flag" i18n-data="cposmer_choose"></span>
											</button>
											<ul class="dropdown-menu">
												<li>
													<ul id="rMerOrgTree" class="ztree"
                                                        style="width: 330px;height: 400px;overflow:auto;overflow-x:hidden;"></ul>
												</li>
											</ul>
										</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                            <!--<button id="checkall" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>-->
                            <div class="btn-group" id="myBtnBox">
                            	<shiro:hasPermission name="rMer:list">
	                                <button id="search" class="btn btn-primary btn-sm rkibtn_search search" type="button"><i
	                                        class="fa fa-search"></i><span class="i18n-flag" i18n-data="authority_query"></span>
	                                </button>
                            	</shiro:hasPermission>
                                <button class="btn btn-primary btn-sm rkibtn_reset reset" type="button">
                                	<i class="fa fa-power-off"></i>
                                	<span class="i18n-flag" i18n-data="authority_reset"></span>
                                </button>
                                <shiro:hasPermission name="rMer:save">
	                                <button id="add"  class="btn btn-primary btn-sm rkibtn_add" data-toggle="modal"
	                                        data-target="#modal_new" table-rel="#rMerTable" type="button">
	                                    <i class="fa fa-plus-square"></i>
	                                    <span class="i18n-flag" i18n-data="authority_add"></span>
	                                </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="rMer:import">
	                                <button id="import" class="btn btn-primary btn-sm rkibtn_import" data-toggle="modal"
	                                        data-target="#modal_import" table-rel="#rMerTable" type="button" onclick="setInputValue('filetxt')">
	                                    <i class="fa fa-plus-square"></i>
	                                    <span class="i18n-flag" i18n-data="import"></span>
	                                </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="rMer:delete">
	                                <button id="batchDel" class="btn btn-primary btn-sm rkibtn_batchdel i18n-flag ajax-button" aria-hidden="true" type="button"
	                                        action="/rMer/delete"  table-rel="#rMerTable" param-name="ids" confirmmsg=main_confirm_remove  action-type="batchdel">
	                                    <i class="fa fa-trash-o"></i>
	                                    <span class="i18n-flag" i18n-data="delete_all"></span>
	                                </button>
                                </shiro:hasPermission>
                            </div><!-- /.btn-group -->
                        </div>
                    </form>
                    <table id="rMerTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" data-action="/rMer/list" autoload optionfn="rMerTableOption">
                        <thead>
                        <tr>
                            <th width="20px"><input type="checkbox" id="checkall"></th>
                            <th width="120px" class="i18n-flag" i18n-data="cp_merch_from"></th>
                            <th width="150px" class="i18n-flag" i18n-data="cposmer_merch_name"></th>
                            <th width="150px" class="i18n-flag" i18n-data="cposmer_merch_no"></th>
                            <th width="100px" class="i18n-flag" i18n-data="org"></th>
                            <th width="100px" class="i18n-flag" i18n-data="start_using"></th>
                            <th width="100px" class="i18n-flag" i18n-data="cp_check_state"></th>
                            <th width="200px" class="i18n-flag" i18n-data="authority_operation"></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                    <!----------------新增窗口---------------------->
                    <div id="modal_new" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
                        <div class="modal-dialog pax-modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><span class="i18n-flag" i18n-data="authority_add"></span></h4>
                                </div>
                                <form class="form-horizontal ajax-form submit" role="form" id="addForm" action="/rMer/save">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                                 i18n-data="cp_merch_from"></span>:<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-7">
                                                <select id="mcr_s" class="form-control input-sm ajax-select" datatype="*" name-rel="name" value-rel="id" name="mcr" data-action="/common/listMcr?type=R">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 text-r"><span class="i18n-flag" i18n-data="cposmer_merch_no"></span>:<font color="red">*</font></label>
                                            <div class="col-sm-7">
                                                <input type="text" name="mid" class="form-control i18n-flag" maxlength=15 placeholder="double_click_choose1" title=cposmer_merch_no datatype="mername"/>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="cposmer_merch_name"></span>:<font color="red">*</font></label>
                                            <div class="col-sm-7">
                                                <input type="text" name="name" class="form-control i18n-flag"
                                                       maxlength=30 placeholder="ph_tps_name"  datatype="name"/>
                                                <%--<span class="i18n-flag" i18n-data="ph_tps_name">格式：2-40位，只能包括中文字、数字、字母、小括号</span>--%>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
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

                    <!----------------导入窗口---------------------->
                    <div id="modal_import" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                         data-backdrop="static">
                        <div class="modal-dialog pax-modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><span id="" class="i18n-flag"
                                                                  i18n-data="import"></span></h4>
                                </div>
                                <form class="form-horizontal ajax-form submit" role="form" id="importForm" action="/rMer/import">
                                    <div class="modal-body">
                                        <div class="row callout callout-info">
                                            <h5 class="i18n-flag" i18n-data="error_data_hint">
                                                <h5>
                                                    <p class="i18n-flag" i18n-data="first_time_download">
                                                        </p>
                                                    2.<a href="batchTemplate/MerTemplate.xls"><b class="i18n-flag" i18n-data="template_download"></b><i class="fa fa-download"></i></a>
                                                </h5>
                                            </h5>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                                 i18n-data="cp_merch_from"></span>:<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-6 ">
                                                <select name="mcr" id="mcr_add" class="form-control input-sm ajax-select" datatype="*"
                                                        rely-dialog data-refresh name-rel="name" value-rel="id"
                                                        data-action="/common/listMcr?type=R">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                                 i18n-data="file"></span>:<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-offset-1 col-sm-4 up-file-box">
                                                <span class="glyphicon glyphicon-circle-arrow-up" style="position: absolute;font-size: 40px;left: 13%;top: -24%;" ></span>
                                                <input id="filetxt" class="form-control "  type="text"
                                                       value="" style=""/>
                                                <input type="file" id="file_import" name="file" datatype="*"
                                                       onchange="upFile(this)" class="form-control up-file"
                                                       oninvalid="checkFile(this)" oninput="checkCancel(this)"
                                                       required="true" accept=".xls"/>
                                            </div>
                                            <div class="col-sm-offset-4  col-sm-7"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit" id="importBnt"
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

                    <!-- --------------详情窗口-------------------------------- -->
                    <div id="modal_detail" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog"
                         aria-hidden="true" data-backdrop="static" action="/rMer/detail">
                        <div class="modal-dialog" style="word-wrap:break-word;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><span  class="i18n-flag"
                                                                  i18n-data="authority_details_query"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal ajax-form" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="cp_merch_from"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail" >
                                                <span id="mcr" name="mcrName"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="cposmer_merch_no"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail" >
                                                <span id="mid" name="mid"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="cposmer_merch_name"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail" >
                                                <span id="name" name="name"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="org"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="org" name="orgName"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="start_using"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="statusName" name="status"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="cp_check_state"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="astatusName" name="auditstatus"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="authority_creator"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="buildoper" name="buildoper"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag" i18n-data="authority_create_time"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="builddatetime" name="builddatetime"></span>
                                            </div>
                                            <div class="col-sm-8"></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal" i18n-data="authority_close"></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- -------详情窗口----------- -->

                    <!-- --------------审核窗口-------------------------------- -->
                    <div id="modal_audit" class="modal fade modal-primary" tabindex="-1" role="dialog" aria-hidden="true"
                         data-backdrop="static">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><span class="i18n-flag" i18n-data="main_audit"></span>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="cp_merch_from"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="mcr"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="cposmer_merch_no"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="mid"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="cposmer_merch_name"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="name"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="org"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="org"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="start_using"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="statusName"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="cp_check_state"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="astatusName"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="authority_creator"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="buildoper"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="authority_create_time"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="builddatetime"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                    </form>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary rkibtn_audit_yes i18n-flag" id="audPassBnt"
                                            i18n-data="cp_pass">通过
                                    </button>
                                    <button type="button" class="btn btn-primary rkibtn_audit_no i18n-flag"
                                            id="audNotPassBnt" i18n-data="cp_no_pass">
                                    </button>
                                    <button type="button" class="btn btn-primary rkibtn_cancel i18n-flag"
                                            data-dismiss="modal" i18n-data="authority_close">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- -------审核窗口----------- -->

                    <!-- --------------修改窗口-------------------------------- -->
                    <div id="modal_update" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                         data-backdrop="static">
                        <div class="modal-dialog" style="word-wrap:break-word;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><span class="i18n-flag"
                                                                  i18n-data="main_modify"></span></h4>
                                </div>
                                <form class="form-horizontal ajax-form" role="form" id="updateForm">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" id="id"/>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label">
                                                <span class="i18n-flag" i18n-data="cp_merch_from"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="mcr" name="mcr"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label">
                                                <span class="i18n-flag" i18n-data="cposmer_merch_no"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="mid" name="mid"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="cposmer_merch_name"></span>:<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <input type="text" name="name" id="name" class="form-control i18n-flag" placeholder="ph_tps_name" maxlength=30 title=cposmer_merch_name datatype="name"/>
                                                <%--<span class="i18n-flag"--%>
                                                      <%--i18n-data="ph_tps_name">格式：2-40位，只能包括中文字、数字、字母、小括号</span>--%>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="org"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="org" name="orgName"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="authority_creator"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="buildoper" name="buildoper"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                        i18n-data="authority_create_time"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="builddatetime" name="builddatetime"></span>
                                            </div>
                                            <div class="col-sm-7"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary rkibtn_save i18n-flag" id="updateBnt"
                                                i18n-data="authority_save">保存
                                        </button>
                                        <button type="button" class="btn btn-primary my-primary i18n-flag"
                                                data-dismiss="modal" i18n-data="authority_close">
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- --------------修改窗口-------------------------------- -->


                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</section>
<script src="${ctx}/js/busi/resourceMgr/rMer.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/plugin/zTree/jquery.ztree.all-3.5.min.js"></script>--%>
<%--<script src="${ctx}/js/plugin/layer/layer.js"></script>--%>
<%--<script src="${ctx}/js/plugin/layer/extend/layer.ext.js"></script>--%>

<script>
//    function setInputValue() {
//        $("#filetxt").val(I("choose_import_file"));
//    }
    function rMerTableOption(){
        return{
            "aoColumns":[
                {
                    "mDataProp": null,
                    "sClass":"center2",
                    "bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id  + "'>");

                    }
                },
                {"mDataProp": "mcrName","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "mid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "orgName","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "status","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<span title='" + _T(statusText(sData)) + "'>" + _T(statusText(sData)) + "</span>");

                }},
                {"mDataProp": "auditstatus","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<span title='" + _T(auditText(sData)) + "'>" + _T(auditText(sData)) + "</span>");

                }},
                {
                    "mDataProp": null,
                    "sClass":"center2",
                    "bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        //alert(sData+":"+oData+":"+iRow+":"+iCol);
                        $(nTd).html('<shiro:hasPermission name="rMer:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_detail" data-rel="id=' + oData.id + '" ><a title='+I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="rMer:update"><div name="update" class="oper_bg"><a title='+I("authority_modify")+' href="javascript:update(\''+oData.id+'\')" class="oper oper_modify"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="rMer:audit"><div name="audit" class="oper_bg"><a title='+I("main_audit")+' href="javascript:audit(\''+oData.id+'\')" class="oper oper_audit"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="rMer:frozen"><div name="frozen" class="oper_bg"><a title='+I("main_freeze")+' href="javascript:frozen(\''+oData.id+'\')" class="oper oper_lock"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="rMer:unfrozen"><div name="unfrozen" class="oper_bg"><a title='+I("thaw")+' href="javascript:unfrozen(\''+oData.id+'\')" class="oper oper_unlock"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="rMer:delete"><div name="del" class="oper_bg"><a title='+I("authority_batch_delete")+' href="javascript:del(\''+oData.id+'\')" class="oper oper_delete"></a></div></shiro:hasPermission>'
                        );
                        // $(nTd).html('<div class="btn-group">'+
                        //    '<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">'+
                        //     '操作栏 <span class="fa fa-caret-down"></span>'+
                        //   '</button>'+
                        //  '<ul class="dropdown-menu" role="menu" >'+
                        //    '<li name="detail" style="display:none"><a href="javascript:detail(\''+oData.id+'\')">查看</a></li>'+
                        //    '<li name="audit" style="display:none"><a href="javascript:audit(\''+oData.id+'\')">审核</a></li>'+
                        //   '<li name="update" style="display:none"><a href="javascript:update(\''+oData.id+'\')">修改</a></li>'+
                        //   '<li name="frozen" style="display:none"><a href="javascript:frozen(\''+oData.id+'\')">冻结</a></li>'+
                        //   '<li name="unfrozen" style="display:none"><a href="javascript:unfrozen(\''+oData.id+'\')">解冻</a></li>'+
                        //   '<li name="del" style="display:none"><a href="javascript:del(\''+oData.id+'\')">删除</a></li>'+
                        //   '</ul>'+
                        // ' </div>');
                    }
                }
            ]
        }
    }
</script>