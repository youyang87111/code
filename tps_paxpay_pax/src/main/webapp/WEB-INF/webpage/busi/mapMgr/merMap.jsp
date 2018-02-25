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
                <h3 class="box-title i18n-flag" i18n-data="merchant_map_man_full"></h3>
            </div><!-- /.box-header -->
            <div class="box-body">
                <form class="search-form" for-table="merMapTable">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="map_type"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="mmf_s" class="form-control input-sm ajax-select"
                                            name="mmf" data-action="/common/listMmf" data-refresh
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
                                            data-action="/common/listBranch?type=C" data-refresh
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
                                <div class="col-md-4">
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
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="trade_type"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="cls_s" class="form-control input-sm ajax-select" name="cls"
                                            data-action="/common/listAppClass?classgroup=01" data-refresh
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
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="branch_out_channel"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="rbid_s" class="form-control input-sm ajax-select" name="rbid"
                                            data-action="/common/listBranch?type=R" data-refresh
                                            name-rel="name" value-rel="id">
                                        <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="start_using"></span>
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
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="cp_check_state"></span>
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
                        <button class="btn btn-primary btn-sm rkibtn_reset reset" type="button">
                            <i class="fa fa-power-off"></i>
                            <span class="i18n-flag" i18n-data="authority_reset"></span>
                        </button>
                        <shiro:hasPermission name="merMap:save">
                        <button id="add"  class="btn btn-primary btn-sm rkibtn_add" type="button" data-toggle="modal" data-target="#modal_new" table-rel="#merMapTable">
                            <i class="fa fa-plus-square"></i>
                            <span class="i18n-flag" i18n-data="authority_add"></span>
                        </button>

                        </shiro:hasPermission>
                        <shiro:hasPermission name="merMap:delete">
                        <button id="batchDel" type="button" class="btn btn-primary btn-sm rkibtn_batchdel i18n-flag ajax-button" action="/merMap/delete"
                                param-name="ids" confirmmsg=main_confirm_remove  action-type="batchdel" table-rel="#merMapTable"
                                aria-hidden="true">
                            <i class="fa fa-trash-o"></i>
                            <span class="i18n-flag" i18n-data="delete_all"></span>
                        </button>
                        </shiro:hasPermission>
                    </div><!-- /.btn-group -->
                </div>
                </form>
                <shiro:hasPermission name="merMap:list">
                <table id="merMapTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" data-action="/merMap/list" autoload optionfn="merMapTableOption">
                    <thead>
                    <tr>
                        <th width="20px"><input type="checkbox" id="checkall"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_in_channel"></th>
                        <th width="100px" class="i18n-flag" i18n-data="join_merchant" title="join_merchant"></th>
                        <th width="80px" class="i18n-flag" i18n-data="trade_type" title="trade_type"></th>
                        <th width="150px" class="i18n-flag" i18n-data="map_type"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_out_channel"></th>
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
                     data-backdrop="static" style="overflow-x: auto; overflow-y: auto;">
                    <div class="modal-dialog" >
                        <div class="modal-content " style="width: 1000px;left: -200px;">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">
                                    <span class="i18n-flag" i18n-data="add_merchant_map"></span>
                                </h4>
                            </div>
                            <form class="form-horizontal ajax-form submit" role="form" id="addForm" action="/merMap/save">
                                <div class="modal-body self-modal-body-1">
                                    <div class="form-group">
                                        <label class="col-sm-3 text-r">
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
                                        <div class="col-sm-offset-3 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="lmid_add_div">
                                        <label class="col-sm-3 text-r">
                                            <span class="i18n-flag" i18n-data="error_data_in_merch_no"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <input id="lmid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="double_click_choose" name="lmid" maxlength="15" datatype="mername"
                                                    title=error_data_in_merch_no>
                                        </div>
                                        <div class="col-sm-offset-3 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="trade_type">交易类别</span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="cls_add" class="form-control ajax-select" name="cls"  datatype="*"
                                                    data-action="/common/listAppClass?classgroup=01" rely-dialog data-refresh
                                                    name-rel="name" value-rel="id" >
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-3 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="map_pattern">模式</span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="mmf_add" class="form-control ajax-select"
                                                    datatype="*" name="mmf" data-action="/common/listMmf" data-refresh  rely-dialog
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-3 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 text-r">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add" class="form-control ajax-select" name="rbid" datatype="*"
                                              >
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-3 col-sm-7 " ></div>
                                    </div>
                                    <!-- 一一 -->
                                    <div id="o2o_add_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag"
                                                                                 i18n-data="errordata_export_merch_no"></span><font
                                                    color="red">*</font></label>
                                            <div class="col-sm-7">
                                                <input id="rmid_add" class="form-control i18n-flag" type="text"
                                                       placeholder="errordata_export_merch_no" name="rmid" maxlength="15"
                                                       datatype="mername" >
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-7 " ></div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记 -->
                                    <div id="cardtype_add_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r">
                                                <span class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag ajax-select" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="c_cardType_add1" name="c_cardType1" loaded="false" datatype="*"
                                                        style="width: 100px;height: 30px" class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_add1" name="c_rmid1" type="text"
                                                       class="i18n-flag" placeholder="errordata_export_merch_no" maxlength="15"
                                                       style="width: 150px;height: 30px" datatype="mername"
                                                       >
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="c_cardType_add2" name="c_cardType2" ignore="ignore" datatype="*"
                                                        style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_add2" name="c_rmid2" type="text" datatype="mername"
                                                       class="i18n-flag" placeholder="" maxlength="15" ignore="ignore"
                                                       style="width: 150px;height: 30px"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="c_cardType_add3" name="c_cardType3" ignore="ignore" datatype="*"
                                                        style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_add3" name="c_rmid3" type="text" ignore="ignore"
                                                       class="i18n-flag" placeholder="" maxlength="15" datatype="mername"
                                                       style="width: 150px;height: 30px"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="c_cardType_add4" name="c_cardType4" ignore="ignore" datatype="*"
                                                        style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_add4" name="c_rmid4" type="text"
                                                       class="i18n-flag" placeholder="" maxlength="15" datatype="mername"
                                                       style="width: 150px;height: 30px"  ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="c_cardType_add5" name="c_cardType5"  ignore="ignore" datatype="*"
                                                        style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_add5" name="c_rmid5" class="i18n-flag"
                                                       type="text" placeholder="" maxlength="15" datatype="mername"
                                                       style="width: 150px;height: 30px"  ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                    </div>

                                    <!-- 金额分段 -->
                                    <div id="amt_add_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="a_amt1_add1" name="a_amt11" type="text" datatype="n1-7"
                                                       placeholder="main_start_range" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_add1" name="a_amt12" type="text" datatype="n1-7"
                                                       placeholder="main_end_range" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_add1" name="a_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag" datatype="mername"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="a_amt1_add2" name="a_amt21" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_start_range" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_add2" name="a_amt22" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_end_range" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_add2" name="a_rmid2" type="text" datatype="mername" ignore="ignore"
                                                       placeholder="errordata_export_merch_no" maxlength="15" style="width: 130px;height: 30px"
                                                       class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="a_amt1_add3" name="a_amt31" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_start_range" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_add3" name="a_amt32" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_end_range" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_add3" name="a_rmid3" type="text" datatype="mername" ignore="ignore"
                                                       placeholder="errordata_export_merch_no" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="a_amt1_add4" name="a_amt41" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_start_range" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_add4" name="a_amt42" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_end_range" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_add4" name="a_rmid4" type="text" datatype="mername" ignore="ignore"
                                                       placeholder="errordata_export_merch_no" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 text-r"><span class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-9 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="a_amt1_add5" name="a_amt51" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_start_range" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_add5" name="a_amt52" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="main_end_range" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_add5" name="a_rmid5" type="text" datatype="mername" ignore="ignore"
                                                       placeholder="errordata_export_merch_no" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-3 col-sm-9 " ></div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记+金额分段 -->
                                    <div id="cardtype_amt_add_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ca_cardType_add1" name="ca_cardType1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_add1" name="ca_amt11" type="text" datatype="n1-7"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_add1" name="ca_amt12" type="text" datatype="n1-7"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_add1" name="ca_rmid1" type="text" datatype="mername"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>

                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ca_cardType_add2" name="ca_cardType2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_add2" name="ca_amt21" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" datatype="n1-7" ignore="ignore"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_add2" name="ca_amt22" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" datatype="n1-7" ignore="ignore"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_add2" name="ca_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername" ignore="ignore"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>

                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ca_cardType_add3" name="ca_cardType3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_add3" name="ca_amt31" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_add3" name="ca_amt32" type="text" datatype="n1-7" ignore="ignore"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_add3" name="ca_rmid3" type="text" datatype="mername" ignore="ignore"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ca_cardType_add4" name="ca_cardType4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_add4" name="ca_amt41" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" datatype="n1-7" ignore="ignore"
                                                        class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_add4" name="ca_amt42" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" datatype="n1-7" ignore="ignore"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_add4" name="ca_rmid4" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ca_cardType_add5" name="ca_cardType5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_add5" name="ca_amt51" type="text" datatype="n1-7"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" ignore="ignore"
                                                        class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_add5" name="ca_amt52" type="text" datatype="n1-7"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" ignore="ignore"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_add5" name="ca_rmid5" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                    </div>
                                    <!-- +卡借贷记 -->
                                    <div id="issuer_cardtype_add_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label "><span class="i18n-flag"
                                                                                    i18n-data="ph_tps_tips_thirth"></span></label>
                                                <select id="ic_issuerid_add1" name="ic_issuerid1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ic_cardType_add1" name="ic_cardType1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_add1" name="ic_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                        class="i18n-flag" datatype="mername"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_thirth"></span></label>
                                                <select id="ic_issuerid_add2" name="ic_issuerid2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ic_cardType_add2" name="ic_cardType2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_add2" name="ic_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_add3" name="ic_issuerid3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ic_cardType_add3" name="ic_cardType3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_add3" name="ic_rmid3" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"><span class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_add4" name="ic_issuerid4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ic_cardType_add4" name="ic_cardType4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_add4" name="ic_rmid4" type="text" ignore="ignore"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 text-r"> <span class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_add5" name="ic_issuerid5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <select id="ic_cardType_add5" name="ic_cardType5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"  rely-dialog data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_add5" name="ic_rmid5" type="text"
                                                       placeholder="errordata_export_merch_no" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                        class="i18n-flag" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-9 " ></div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="cur_id_of_rmid" id="cur_id_of_rmid">

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
                <div id="modal_detail" class="modal fade modal-primary" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static">
                    <div class="modal-dialog" style="word-wrap:break-word;">
                        <div class="modal-content" style="width: 1000px;left:-200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span id="modal_title" class="i18n-flag"
                                                              i18n-data="authority_details_query">详情查看</span></h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="branch_in_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lbid" name="lbid.name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="join_merchant"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lmid" name="lmid.name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="trade_type">交易类别</span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="cls" name="cls_name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="map_type"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="bmf" name="bmf.name"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rbid" name="rbid.name"></span>
                                        </div>
                                    </div>

                                    <!-- 一一 -->
                                    <div class="form-group" id="o2o_detail_div" style="display:none;">
                                    	<label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="errordata_export_merch_no"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rmid" name="rmid"></span>
                                        </div>
                                    </div>

                                    <!-- 卡借贷记 -->
                                    <div id="cardtype_detail_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType1" name="cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid1" name="rmid1"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType2" name="cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid2" name="rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType3" name="cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid3" name="rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType4" name="cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid4" name="rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType5" name="cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid5" name="rmid5"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 金额分段 -->
                                    <div id="amt_detail_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="a_amt11" name="amt11"></span>
                                                -
                                                <span id="a_amt21" name="amt21"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid1" name="rmid"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="a_amt12" name="amt12"></span>
                                                -
                                                <span id="a_amt22" name="amt22"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid2" name="rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="a_amt13" name="amt13"></span>
                                                -
                                                <span id="a_amt23" name="amt23"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid3" name="rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="a_amt14" name="amt14"></span>
                                                -
                                                <span id="a_amt24" name="amt24"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid4" name="rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="a_amt15" name="amt15"></span>
                                                -
                                                <span id="a_amt25" name="amt25"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid5" name="rmid5"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记+金额分段 -->
                                    <div id="cardtype_amt_detail_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType1" name="cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="ca_amt11" name="amt11"></span>
                                                -
                                                <span id="ca_amt21" name="amt21"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid1" name="rmid1"></span>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType2" name="cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="ca_amt12" name="amt12"></span>
                                                -
                                                <span id="ca_amt22" name="amt22"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid2" name="rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType3" name="cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="ca_amt13" name="amt13"></span>
                                                -
                                                <span id="ca_amt23" name="amt23"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid3" name="rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType4" name="cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="ca_amt14" name="amt14"></span>
                                                -
                                                <span id="ca_amt24" name="amt24"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid4" name="rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType5" name="cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <span id="ca_amt15" name="amt15"></span>
                                                -
                                                <span id="ca_amt25" name="amt25"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid5" name="rmid5"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- +卡借贷记 -->
                                    <div id="issuer_cardtype_detail_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid1" name="issuserid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType1" name="cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid1" name="rmid1"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid2" name="issuserid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType2" name="cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid2" name="rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid3" name="issuserid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType3" name="cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid3" name="rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1  control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid4" name="issuserid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType4" name="cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid4" name="rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid5" name="issuserid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType5" name="cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid5" name="rmid5"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="start_using"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="statusName" name="status"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="cp_check_state"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="astatusName" name="auditstatus"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="authority_creator"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="buildoper" name="buildoper"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
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
                        <div class="modal-content" style="width: 1000px;left:-200px">
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
                                                                                                    i18n-data="branch_in_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lbid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="join_merchant"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lmid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="trade_type"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="cls"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="map_type"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="bmf"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="branch_out_channel"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rbid"></span>
                                        </div>
                                    </div>

                                    <!-- 一一 -->
                                    <div class="form-group" id="o2o_audit_div" style="display:none;">
                                        <label class="col-sm-3 col-sm-offset-1 control-label i18n-flag"
                                               i18n-data="errordata_export_merch_no"></label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rmid"></span>
                                        </div>
                                    </div>

                                    <!-- 卡借贷记 -->
                                    <div id="cardtype_audit_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid1"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label "><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="c_cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="c_rmid5"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 金额分段 -->
                                    <div id="amt_audit_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="a_amt11"></span>
                                                -
                                                <span id="a_amt21"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid1"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="a_amt12"></span>
                                                -
                                                <span id="a_amt22"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="a_amt13"></span>
                                                -
                                                <span id="a_amt23"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="a_amt14"></span>
                                                -
                                                <span id="a_amt24"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="a_amt15"></span>
                                                -
                                                <span id="a_amt25"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="a_rmid5"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记+金额分段 -->
                                    <div id="cardtype_amt_audit_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="ca_amt11"></span>
                                                -
                                                <span id="ca_amt21"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid1"></span>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="ca_amt12"></span>
                                                -
                                                <span id="ca_amt22"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="ca_amt13"></span>
                                                -
                                                <span id="ca_amt23"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="ca_amt14"></span>
                                                -
                                                <span id="ca_amt24"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ca_cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <span id="ca_amt15"></span>
                                                -
                                                <span id="ca_amt25"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ca_rmid5"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- +卡借贷记 -->
                                    <div id="issuer_cardtype_audit_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType1"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid1"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType2"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid2"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType3"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid3"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType4"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid4"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <span id="ic_issuerid5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_sevth1"></label>
                                                <span id="ic_cardType5"></span>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <span id="ic_rmid5"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="start_using"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="statusName"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="cp_check_state"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="astatusName"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="buildoper"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="authority_create_time"></span>:
                                        </label>
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
                     data-backdrop="static" style="overflow-x: auto; overflow-y: auto;">
                    <div class="modal-dialog " style="word-wrap:break-word;">
                        <div class="modal-content " style="width: 1000px;left:-200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span  class="i18n-flag"
                                                              i18n-data="authority_modify"></span></h4>
                            </div>
                            <form id="updateForm" class="form-horizontal ajax-form " >
                                <div class="modal-body self-modal-body-1">

                                    <div class="form-group" style="display:none;">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="hidden_key"></span>:</label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <input class="form-control" id="id_update" name="id" type="text"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_in_channel"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lbid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="join_merchant"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="lmid"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="trade_type">交易类别</span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="cls"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="map_type"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="bmf"></span>
                                            <input id="bmf_id" name="bmf_id" type="hidden">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="rbid"></span>
                                            <input id="rbid_update" name="rmcr" style="display: none;">
                                        </div>
                                    </div>

                                    <!-- 一一 -->
                                    <div id="o2o_update_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="errordata_export_merch_no"></span><font
                                                    color="red">*</font></label>
                                            <div class="col-sm-5">
                                                <input id="rmid_update" class="form-control i18n-flag" type="text"
                                                       placeholder="" name="rmid" maxlength="15" datatype="mername"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-7"></div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记 -->
                                    <div id="cardtype_update_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="c_cardType_update1" name="c_cardType1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_update1" name="c_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 150px;height: 30px"
                                                        class="i18n-flag" datatype="mername"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="c_cardType_update2" name="c_cardType2" datatype="*" ignore="ignore"
                                                        style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_update2" name="c_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 150px;height: 30px"
                                                        class="i18n-flag" datatype="mername" ignore="ignore"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="c_cardType_update3" name="c_cardType3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_update3" name="c_rmid3" type="text"
                                                       placeholder="" maxlength="15" style="width: 150px;height: 30px" datatype="mername" ignore="ignore"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="c_cardType_update4" name="c_cardType4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_update4" name="c_rmid4" type="text"
                                                       placeholder="" maxlength="15" style="width: 150px;height: 30px" datatype="mername" ignore="ignore"
                                                       class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="c_cardType_update5" name="c_cardType5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="c_rmid_update5" name="c_rmid5" type="text"
                                                       placeholder="" maxlength="15" style="width: 150px;height: 30px" datatype="mername" ignore="ignore"
                                                        class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                    </div>

                                    <!-- 金额分段 -->
                                    <div id="amt_update_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label" i18n-data="terri_money"></label>
                                                <input id="a_amt1_update1" name="a_amt11" type="text" datatype="n1-7"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_update1" name="a_amt12" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px" datatype="n1-7"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_update1" name="a_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px" datatype="mername"
                                                       pattern="^[A-Za-z0-9]{15}$" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="a_amt1_update2" name="a_amt21" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore"
                                                       class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_update2" name="a_amt22" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore"
                                                        class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_update2" name="a_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="a_amt1_update3" name="a_amt31" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_update3" name="a_amt32" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_update3" name="a_rmid3" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="a_amt1_update4" name="a_amt41" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_update4" name="a_amt42" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_update4" name="a_rmid4" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 col-sm-offset-1 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-8 form-group">
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="a_amt1_update5" name="a_amt51" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="a_amt2_update5" name="a_amt52" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="a_rmid_update5" name="a_rmid5" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                    </div>
                                    <!-- 卡借贷记+金额分段 -->
                                    <div id="cardtype_amt_update_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ca_cardType_update1" name="ca_cardType1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="ca_amt1_update1" name="ca_amt11" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_update1" name="ca_amt12" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_update1" name="ca_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" class="i18n-flag"
                                                       title=errordata_export_merch_no>

                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ca_cardType_update2" name="ca_cardType2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="ca_amt1_update2" name="ca_amt21" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_update2" name="ca_amt22" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_update2" name="ca_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ca_cardType_update3" name="ca_cardType3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="ca_amt1_update3" name="ca_amt31" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_update3" name="ca_amt32" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_update3" name="ca_rmid3" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ca_cardType_update4" name="ca_cardType4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money"></label>
                                                <input id="ca_amt1_update4" name="ca_amt41" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_update4" name="ca_amt42" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_update4" name="ca_rmid4" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-4 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ca_cardType_update5" name="ca_cardType5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="terri_money">金额</label>
                                                <input id="ca_amt1_update5" name="ca_amt51" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_start_range>
                                                <label class="control-label">-</label>
                                                <input id="ca_amt2_update5" name="ca_amt52" type="text"
                                                       placeholder="" maxlength="7" style="width: 70px;height: 30px"
                                                       datatype="n1-7" ignore="ignore" class="i18n-flag" title=main_end_range>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ca_rmid_update5" name="ca_rmid5" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                    </div>
                                    <!-- +卡借贷记 -->
                                    <div id="issuer_cardtype_update_div" style="display:none;">
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>1<font
                                                    color="red">*</font></label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_update1" name="ic_issuerid1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ic_cardType_update1" name="ic_cardType1" datatype="*"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_update1" name="ic_rmid1" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>2</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_update2" name="ic_issuerid2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ic_cardType_update2" name="ic_cardType2" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_update2" name="ic_rmid2" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>3</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_update3" name="ic_issuerid3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ic_cardType_update3" name="ic_cardType3" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_update3" name="ic_rmid3" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>4</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_update4" name="ic_issuerid4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ic_cardType_update4" name="ic_cardType4" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_update4" name="ic_rmid4" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2  control-label"><span
                                                    class="i18n-flag" i18n-data="the_map"></span>5</label>
                                            <div class="col-sm-10 form-group">
                                                <label class="control-label i18n-flag" i18n-data="ph_tps_tips_thirth"></label>
                                                <select id="ic_issuerid_update5" name="ic_issuerid5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listBankInfo"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label"><span class="i18n-flag"
                                                                                   i18n-data="ph_tps_tips_sevth1"></span></label>
                                                <select id="ic_cardType_update5" name="ic_cardType5" datatype="*" ignore="ignore"
                                                        loaded="false" style="width: 100px;height: 30px"
                                                        class="ajax-select"
                                                        data-action="/common/listCardType"   data-refresh
                                                        name-rel="name" value-rel="id">
                                                    <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                </select>
                                                &nbsp;&nbsp;&nbsp;
                                                <label class="control-label i18n-flag"
                                                       i18n-data="errordata_export_merch_no"></label>
                                                <input id="ic_rmid_update5" name="ic_rmid5" type="text"
                                                       placeholder="" maxlength="15" style="width: 130px;height: 30px"
                                                       datatype="mername" ignore="ignore" class="i18n-flag"
                                                       title=errordata_export_merch_no>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-8"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="buildoper"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="authority_create_time"></span>:
                                        </label>
                                        <div class="col-sm-8 pax-div-detail">
                                            <span id="builddatetime"></span>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary rkibtn_save i18n-flag "
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

                <!----------------窗口---------------------->
                <div id="modal_sel_cposMer" style="left: -500px" class="modal fade modal-primary" tabindex="-1"
                     role="dialog" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span  class="i18n-flag"
                                                              i18n-data="access_merch"></span></h4>
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
                <!----------------窗口---------------------->

                <!----------------窗口---------------------->
                <div id="modal_rMer_sel" style="left: -500px" class="modal fade modal-primary" tabindex="-1"
                     role="dialog" aria-hidden="true" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 1200px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"><span  class="i18n-flag"
                                                              i18n-data="sel_out_merchant"></span></h4>
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
                <!----------------窗口---------------------->

            </div><!-- /.box-body -->
        </div><!-- /.box -->
    </div><!-- /.col -->
</div><!-- /.row -->


<script src="${ctx}/js/busi/mapMgr/merMap.js"></script>
<script>
    function merMapTableOption(){
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
                {"mDataProp": "cls_name","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "mmf_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rbid_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
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
                        $(nTd).html('<shiro:hasPermission name="merMap:detail"><div name="detail" class="oper_bg" style="display: block;" ><a title='+I("main_view")+' href="javascript:detail(\''+oData.id+'\')" class="oper oper_view"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="merMap:update"><div name="update" class="oper_bg" style="display: block;"><a title='+I("authority_modify")+' href="javascript:update(\''+oData.id+'\')" class="oper oper_modify"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="merMap:audit"><div name="audit" class="oper_bg" style="display: block;"><a title='+I("main_audit")+' href="javascript:audit(\''+oData.id+'\')" class="oper oper_audit"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="merMap:frozen"><div name="frozen" class="oper_bg" style="display: block;"><a title='+I("main_freeze")+' href="javascript:void(0)" class="oper oper_lock ajax-button" action-type="lock" action="/merMap/frozen" table-rel="#merMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_freeze")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="merMap:unfrozen"><div name="unfrozen" class="oper_bg" style="display: block;"><a title='+I("thaw")+' href="javascript:void(0)" class="oper oper_unlock ajax-button" action-type="unlock" action="/merMap/unfrozen" table-rel="#merMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_thaw")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="merMap:delete"><div name="del" class="oper_bg" style="display: block;"><a title='+I("authority_batch_delete")+' href="javascript:void(0)" class="oper oper_delete ajax-button" action-type="delete" action="/merMap/delete" table-rel="#merMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div></shiro:hasPermission>'
                        );

                    }
                }
            ]
        }
    }
</script>

