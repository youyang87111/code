<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>
<div class="row">
    <div class="col-lg-12 col-xs-12">
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title i18n-flag" i18n-data="terminal_map_man_full"></h3>
            </div><!-- /.box-header -->
            <div class="box-body">
                <form class="search-form" for-table="termMapTable">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4 pad-left">
                                    <span class="input-group-addon i18n-flag" i18n-data="map_type"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="tmf_s" class="form-control input-sm ajax-select"
                                            name="tmf"
                                            datatype="*" data-action="/common/listTmf" data-refresh
                                            name-rel="name" value-rel="id">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="branch_in_channel"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="lbid_s" class="form-control input-sm ajax-select" name="lbid"
                                            data-action="/common/listBranch?type=C"  data-refresh
                                            name-rel="name" value-rel="id">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4 pad-left">
                                    <span class="input-group-addon i18n-flag"
                                          i18n-data="error_data_in_merch_no"></span>
                                </div>
                                <div class="col-md-8">
                                    <input id="lmid_s" class="form-control input-sm" maxlength="15" name="lmid">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4 ">
                                    <span class="input-group-addon i18n-flag" i18n-data="branch_out_channel"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="rbid_s" class="form-control input-sm ajax-select" name="rbid"
                                            data-action="/common/listBranch?type=R"  data-refresh
                                            name-rel="name" value-rel="id">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4 pad-left">
                                    <span class="input-group-addon i18n-flag" i18n-data="errordata_export_merch_no"></span>
                                </div>
                                <div class="col-md-8">
                                    <input id="rmid_s" class="form-control input-sm" maxlength="15" name="rmid">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="start_using" name="status"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="status_s" class="form-control input-sm" name="status">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose"></option>
                                        <option value="0" class="i18n-flag" i18n-data="main_none_use"></option>
                                        <option value="1" class="i18n-flag" i18n-data="main_freeze"></option>
                                        <option value="2" class="i18n-flag" i18n-data="main_in_use"></option>
                                    </select></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4 pad-left">
                                    <span class="input-group-addon i18n-flag" i18n-data="cp_check_state" ></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="astatus_s" class="form-control input-sm" name="auditstatus">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose"></option>
                                        <option value="0" class="i18n-flag" i18n-data="main_auditing"></option>
                                        <option value="1" class="i18n-flag" i18n-data="cp_no_pass"></option>
                                        <option value="2" class="i18n-flag" i18n-data="cp_pass"></option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                        <div class="btn-group" id="myBtnBox">
                            <button id="search2" class="btn btn-primary btn-sm rkibtn_search search" type="button">
                                <i class="fa fa-search"></i>
                                <span class="i18n-flag" i18n-data="authority_query"></span>
                            </button>
                            <button  class="btn btn-primary btn-sm rkibtn_reset reset" type="button">
                                <i class="fa fa-power-off"></i>
                                <span class="i18n-flag" i18n-data="authority_reset"></span>
                            </button>
                            <shiro:hasPermission name="termMap:save">
                            <button id="add" type="button" class="btn btn-primary btn-sm rkibtn_add" data-toggle="modal" data-target="#modal_new" table-rel="#termMapTable">
                                <i class="fa fa-plus-square"></i>
                                <span class="i18n-flag" i18n-data="authority_add"></span>
                            </button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="termMap:delete">
                            <button id="batchDel" type="button" class="btn btn-primary btn-sm rkibtn_batchdel i18n-flag ajax-button" action="/termMap/delete"
                                    param-name="ids" confirmmsg=main_confirm_remove  action-type="batchdel" table-rel="#termMapTable"
                                    aria-hidden="true">
                                <i class="fa fa-trash-o"></i>
                                <span class="i18n-flag" i18n-data="delete_all"></span>
                            </button>
                            </shiro:hasPermission>
                        </div><!-- /.btn-group -->
                    </div>
                </form>

                <shiro:hasPermission name="termMap:list">
                <table id="termMapTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" data-action="/termMap/list" autoload optionfn="termMapTableOption">
                    <thead>
                    <tr>
                        <th width="20px"><input type="checkbox" id="checkall"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_in_channel"></th>
                        <th width="100px" class="i18n-flag" i18n-data="join_merchant" title="join_merchant"></th>
                        <th width="80px" class="i18n-flag" i18n-data="error_data_in_terminal_no"
                            title="error_data_in_terminal_no">
                        </th>
                        <th width="150px" class="i18n-flag" i18n-data="map_type"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_out_channel"></th>
                        <th width="100px" class="i18n-flag" i18n-data="error_data_out_merch"
                            title="error_data_out_merch">
                        </th>
                        <th width="80px" class="i18n-flag" i18n-data="error_data_out_terminal"
                            title="error_data_out_terminal">
                        </th>
                        <th width="100px" class="i18n-flag" i18n-data="start_using"></th>
                        <th width="100px" class="i18n-flag" i18n-data="cp_check_state"></th>
                        <th width="200px" class="i18n-flag" i18n-data="authority_operation"></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                </shiro:hasPermission>

                <!----------------新增窗口---------------------->
                <div id="modal_new" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span class="i18n-flag" i18n-data="add_terminal_map"></span>
                                </h4>
                            </div>
                            <form class="form-horizontal ajax-form submit" role="form" id="addForm" action="/termMap/save">
                                <div class="modal-body self-modal-body-1">
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r">
                                            <span class="i18n-flag" i18n-data="branch_in_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="lbid_add" class="form-control ajax-select" name="lbid"
                                                   datatype="*"
                                                    <%--data-action="/common/listBranch?type=C"  rely-dialog data-refresh--%>
                                                    <%--name-rel="name" value-rel="id"--%>
                                            >
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="lmid_add_div">
                                        <label class="col-sm-4 text-r">
                                            <span class="i18n-flag" i18n-data="error_data_in_merch_no"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <input id="lmid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="" name="lmid" maxlength="15"
                                                   datatype="mername"  title=error_data_in_merch_no>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag" i18n-data="map_pattern"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="tmf_add" class="form-control ajax-select"
                                                    name="tmf"
                                                    datatype="*" data-action="/common/listTmf" rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="branch_out_channel"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add" class="form-control ajax-select" name="rbid"
                                                    datatype="*"    
                                                    >
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_out_merch_no"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <input id="rmid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="" name="rmid" maxlength="15"
                                                   datatype="mername" title=error_data_out_merch_no>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <!-- 一一映射 -->
                                    <div class="form-group" id="ltid_add_div" style="display: none;">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_in_terminal_no"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <input id="ltid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="" name="ltid" maxlength="15"
                                                   datatype="ternum" title=error_data_in_terminal_no>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group" id="rtid_add_div" style="display: none;">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="errordata_export_terminal_no"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <input id="rtid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="" name="rtid" maxlength="15"
                                                   datatype="ternum" title=errordata_export_terminal_no>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <input type="hidden" name="cur_id_of_rmid" id="cur_id_of_rmid">
                                    <input type="hidden" name="cur_id_of_sel" id="cur_id_of_sel">


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
                <!----------------新增窗口---------------------->

                <!-- --------------详情窗口-------------------------------- -->
                <div id="modal_detail" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static" action="/termMap/detail">
                    <div class="modal-dialog" style="word-wrap:break-word;">
                        <div class="modal-content" style=" ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span  class="i18n-flag"
                                                              i18n-data="authority_details_query"></span></h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal ajax-form" role="form">
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="branch_in_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lbid" name="lbid_name" after-echo="optionShow"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_in_merch_no"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lmid" name="lmid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="map_type"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="tmf" name="tmf_name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="branch_out_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rbid" name="rbid_name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_out_merch"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rmid" name="rmid"></span>
                                        </div>
                                    </div>

                                    <div class="form-group option-show" >
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_in_terminal_no"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="ltid" name="ltid" class="option-show-text" after-echo="optionShow"></span>
                                        </div>
                                    </div>

                                    <div class="form-group option-show" >
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_out_terminal"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span  name="rtid" class="option-show-text" after-echo="optionShow"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag" i18n-data="start_using"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="statusName" name="status"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="cp_check_state"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="astatusName" name="auditstatus"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="authority_creator"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="buildoper" name="buildoper"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="authority_create_time"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="builddatetime" name="builddatetime"></span>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- -------详情窗口----------- -->

                <!-- --------------审核窗口-------------------------------- -->
                <div id="modal_audit" class="modal fade modal-primary" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style=" ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span  class="i18n-flag" i18n-data="main_audit"></span>
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="branch_in_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lbid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_in_merch_no"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lmid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="map_type"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="tmf"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="branch_out_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rbid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_out_merch"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rmid"></span>
                                        </div>
                                    </div>

                                    <div class="form-group" id="ltid_audit_div">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_in_terminal_no"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="ltid"></span>
                                        </div>
                                    </div>

                                    <div class="form-group" id="rtid_audit_div">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="error_data_out_terminal"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rtid"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag" i18n-data="start_using"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="statusName"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="cp_check_state"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="astatusName"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="authority_creator"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="buildoper"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 text-r"><span class="i18n-flag"
                                                                             i18n-data="authority_create_time"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="builddatetime"></span>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary rkibtn_audit_yes i18n-flag" id="audPassBnt"
                                        i18n-data="cp_pass">
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
                        <div class="modal-content " style=" ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="authority_modify"></span>
                                </h4>
                            </div>
                            <form id="" class="foupdateFormrm-horizontal ajax-form " role="form">
                                <div class="modal-body self-modal-body-1" style="overflow: hidden">
                                        <div class="form-group" style="display:none;">
                                            <label class="col-sm-4 text-r"><span class="i18n-flag" i18n-data="hidden_key"></span>:</label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <input class="form-control" id="id_update" name="id" type="text"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="branch_in_channel"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="lbid"></span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="error_data_in_merch_no"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="lmid"></span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="trade_type"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="cls"></span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="map_type"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="bmf"></span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="branch_out_channel"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="rbid"></span>
                                            </div>
                                        </div>

                                        <!-- 一一映射 -->
                                        <div id="o2o_update_div" style="display:none;">
                                            <div class="form-group row">
                                                <label class="col-sm-4 text-r">
                                                    <span class="i18n-flag"
                                                          i18n-data="error_data_out_merch_no"></span>
                                                    <font color="red">*</font>
                                                </label>
                                                <div class="col-sm-8">
                                                    <input id="rmid_update" class="form-control" type="text" datatype="mername"
                                                           placeholder="" name="updateInput.rmid" maxlength="15">
                                                </div>
                                                <div class="col-sm-offset-4 col-sm-8"></div>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="buildoper"></span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-4 text-r">
                                                <span class="i18n-flag" i18n-data="authority_create_time"></span>:
                                            </label>
                                            <div class="col-sm-8 pax-div-detail">
                                                <span id="builddatetime"></span>
                                            </div>
                                        </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit" id="updateBnt"
                                            i18n-data="authority_save">
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

                <!----------------选择窗口---------------------->
                <div id="modal_sel_cposMer" style="left: -500px" class="modal fade modal-primary" tabindex="-1"
                     role="dialog" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="access_merch"></span>
                                </h4>
                            </div>
                            <div id="modal_sel_cposMer_body" class="modal-body">
                                <jsp:include page="/dispatcher/selCposMer"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary rkibtn_save i18n-flag"
                                        id="selCposMer_addBnt" i18n-data="error_data_comfirm">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <!----------------选择窗口---------------------->

                <!----------------选择转出商户窗口---------------------->
                <div id="modal_rMer_sel" style="left: -500px" class="modal fade modal-primary" tabindex="-1"
                     role="dialog" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="sel_out_merchant"></span>
                                </h4>
                            </div>
                            <div id="modal_rMer_sel_body" class="modal-body">
                                <jsp:include page="/dispatcher/selRMer"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary rkibtn_save i18n-flag" id="sel_rmer_addBnt"
                                        i18n-data="error_data_comfirm">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <!----------------选择转出商户窗口---------------------->

                <!----------------选择接入终端窗口---------------------->
                <div id="modal_sel_lterm" style="left: -500px" class="modal fade modal-primary" tabindex="-1" role="dialog"
                     aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="error_data_in_terminal_no"></span>
                                </h4>
                            </div>
                            <div id="modal_selLTerm_body" class="modal-body">
                                <%--<jsp:include page="/dispatcher/selLTerm"/>--%>
                                    <div class="box-body">
                                        <div style="padding: 5px; border-bottom: 1px solid #F4F4F4;">
                                            <div class="row">
                                                <form class="search-form" for-table="sel_lterm_dt1">
                                                    <div class="col-md-4">
                                                        <div class="input-group">
                                                            <span class="input-group-addon i18n-flag" i18n-data="cp_terminal_no"></span>
                                                            <input id="tid_lterm_sel" class="form-control input-sm" maxlength="8" name="tid">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="btn-group">
                                                            <button id="sel_lterm_search" class="btn btn-primary btn-sm rkibtn_search " type="button">
                                                                <i class="fa fa-search"></i><span class="i18n-flag" i18n-data="authority_query">查询</span>
                                                            </button>
                                                            <button id="sel_lterm_reset" class="btn btn-primary btn-sm rkibtn_reset " type="button">
                                                                <i class="fa fa-power-off"></i><span class="i18n-flag" i18n-data="authority_reset">重置 </span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>

                                            </div>
                                        </div>

                                        <table id="sel_lterm_dt1" class="table table-bordered table-hover pax-datatable-cls " >
                                            <thead>
                                            <tr>
                                                <th width="20px">
                                                    <!-- <input type="checkbox" id="sel_checkall"> -->
                                                </th>
                                                <th width="150px" class="i18n-flag" i18n-data="branch_in_channel">
                                                    
                                                </th>
                                                <th width="150px" class="i18n-flag" i18n-data="cposmer_merch_no">
                                                   
                                                </th>
                                                <th width="200px" class="i18n-flag" i18n-data="cposmer_merch_name">
                                                    
                                                </th>
                                                <th width="100px" class="i18n-flag" i18n-data="cp_terminal_no">
                                                   
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary rkibtn_save i18n-flag" id="sel_addLTermBnt"
                                        i18n-data="error_data_comfirm">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <!----------------选择接入终端窗口---------------------->

                <!----------------选择窗口---------------------->
                <div id="modal_sel" style="left: -500px" class="modal fade modal-primary" tabindex="-1" role="dialog"
                     aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="errordata_export_terminal_no"></span>
                                </h4>
                            </div>
                            <div id="modal_sel_body" class="modal-body">
                                <jsp:include page="/dispatcher/selRTerm"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary rkibtn_save i18n-flag" id="sel_addBnt"
                                        i18n-data="error_data_comfirm">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <!----------------选择窗口---------------------->



            </div><!-- /.box-body -->
        </div><!-- /.box -->
    </div><!-- /.col -->
</div><!-- /.row -->


<script src="${ctx}/js/busi/mapMgr/termMap.js"></script>
<script src="${ctx}/js/busi/mapMgr/selLTerm.js" type="text/javascript"></script>
<script>
    function termMapTableOption(){
        return{
            "aoColumns": [
                {
                    "mDataProp": null,
                    "sClass":"center2",
                    "bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id  + "'>");
                    }
                },
                {"mDataProp": "lbid_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "lmid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "ltid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "tmf_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rbid_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rmid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rtid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
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
                        $(nTd).html('<shiro:hasPermission name="termMap:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_detail" data-rel="id=' + oData.id + '"><a title='+I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>'+
//                            '<div name="update" class="oper_bg" style="display: block;"><a title='+I("authority_modify")+' href="javascript:update(\''+oData.id+'\')" class="oper oper_modify"></a></div>'+
                            '<shiro:hasPermission name="termMap:audit"><div name="audit" class="oper_bg" style="display: block;"><a title='+I("main_audit")+' href="javascript:audit(\''+oData.id+'\')" class="oper oper_audit"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="termMap:frozen"><div name="frozen" class="oper_bg" style="display: block;"><a title='+I("main_freeze")+' href="javascript:void(0)" class="oper oper_lock ajax-button" action-type="lock" action="/termMap/frozen" table-rel="#termMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_freeze")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="termMap:unfrozen"><div name="unfrozen" class="oper_bg" style="display: block;"><a title='+I("thaw")+' href="javascript:void(0)" class="oper oper_unlock ajax-button" action-type="unlock" action="/termMap/unfrozen" table-rel="#termMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_thaw")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="termMap:delete"><div name="del" class="oper_bg" style="display: block;"><a title='+I("authority_batch_delete")+' action-type="delete" action="/termMap/delete" table-rel="#termMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'" class="oper oper_delete ajax-button"></a></div></shiro:hasPermission>'
                        );

                    }
                }
            ]
        }
    }
</script>