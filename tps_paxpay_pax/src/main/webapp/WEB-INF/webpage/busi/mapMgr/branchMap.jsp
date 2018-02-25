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
                <h3 class="box-title i18n-flag" i18n-data="branch_map_man_full"></h3>
            </div><!-- /.box-header -->
            <div class="box-body">
                <form class="search-form" for-table="branchMapTable">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="input-group col-md-12">
                                <div class="col-md-4">
                                    <span class="input-group-addon i18n-flag" i18n-data="map_type"></span>
                                </div>
                                <div class="col-md-8">
                                    <select id="bmf_s" class="form-control input-sm ajax-select"
                                            name="bmf" data-action="/common/listBmf" data-refresh
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
                                    <select id="cls_s" class="form-control input-sm ajax-select"
                                            name="cls"
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
                                    </select>
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
                            <button type="button" class="btn btn-primary btn-sm  rkibtn_reset reset">
                                <i class="fa fa-power-off"></i>
                                <span class="i18n-flag" i18n-data="authority_reset"></span>
                            </button>
                            <shiro:hasPermission name="branchMap:save">
                            <button id="add" class="btn btn-primary btn-sm rkibtn_add" type="button" data-toggle="modal" data-target="#modal_new" table-rel="#branchMapTable">
                                <i class="fa fa-plus-square"></i>
                                <span class="i18n-flag" i18n-data="authority_add" ></span>
                            </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="branchMap:delete">
                            <button id="batchDel" class="btn btn-primary btn-sm rkibtn_batchdel i18n-flag ajax-button" type="button" aria-hidden="true" action="/branchMap/delete"
                                    param-name="ids" confirmmsg=main_confirm_remove  action-type="batchdel" table-rel="#branchMapTable">
                                <i class="fa fa-trash-o"></i>
                                <span class="i18n-flag" i18n-data="delete_all"></span>
                            </button>
                            </shiro:hasPermission>
                        </div><!-- /.btn-group -->
                    </div>
                </form>
                <shiro:hasPermission name="branchMap:list">
                <table id="branchMapTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" data-action="/branchMap/list" autoload optionfn="branchMapTableOption">
                    <thead>
                    <tr>
                        <th width="20px"><input type="checkbox" id="checkall"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_in_channel"></th>
                        <th width="100px" class="i18n-flag" i18n-data="join_merchant" title="join_merchant"></th>
                        <th width="80px" class="i18n-flag" i18n-data="trade_type" title="trade_type"></th>
                        <th width="150px" class="i18n-flag" i18n-data="map_type"></th>
                        <th width="120px" class="i18n-flag" i18n-data="branch_out_channel"></th>
                        <th width="80px" class="i18n-flag" i18n-data="card_agency"></th>
                        <th width="80px" class="i18n-flag" i18n-data="ph_tps_tips_sevth1"></th>
                        <th width="80px" class="i18n-flag" i18n-data="main_start_range" title="main_start_range">
                        </th>
                        <th width="80px" class="i18n-flag" i18n-data="main_end_range" title="main_end_range"></th>
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
                <div id="modal_new" class="modal fade modal-primary  ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                     data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content " >
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">
                                    <span  class="i18n-flag" i18n-data="add_branch_map"></span>
                                </h4>
                            </div>
                            <form class="form-horizontal ajax-form submit" role="form" id="addForm" action="/branchMap/save">
                                <div class="modal-body self-modal-body-1">
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_in_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <%--<input type="hidden" id="mcrForMerch">--%>
                                        <div class="col-sm-7">
                                            <select id="lbid_add" class="form-control ajax-select" name="lbid" datatype="*"
                                                    <%--data-action="/common/listBranch?type=C"  rely-dialog data-refresh--%>
                                                    <%--name-rel="name" value-rel="id"--%>
                                            >
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed>--</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label i18n-flag"
                                               i18n-data="special_merchant"></label>
                                        <div class="col-sm-7">
                                            <select id="merPersonalized_add" name="merPersonalized" datatype="*"
                                                    class="form-control" loaded="false">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                                <option value="1" class="i18n-flag" i18n-data="cpomer_no"></option>
                                                <option value="2" class="i18n-flag" i18n-data="cpomer_yes"></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="lmid_add_div" style="display:none;">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="error_data_in_merch_no"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <input id="lmid_add" class="form-control i18n-flag" type="text"
                                                   placeholder="double_click_choose" name="lmid" maxlength="15"
                                                   datatype="mername" title=error_data_in_merch_no>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="trade_type"></span><font
                                                color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="cls_add" class="form-control ajax-select" name="cls"
                                                datatype="*"  data-action="/common/listAppClass?classgroup=01" rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="map_pattern"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="bmf_add"  class="form-control ajax-select" name="bmf"
                                                    datatype="*" data-action="/common/listBmf" rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>

                                    <!-- 一一映射 -->
                                    <div id="o2o_add_div" style="display:none;">
                                        <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                    i18n-data="branch_out_channel"></span><font
                                                color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_01"  name="rbid_01" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                </div>
                                <!-- 卡借贷记映射 -->
                                <div id="cardtype_add_div" style="display:none;">
                                    <div class="form-group" id="cardType_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="ph_tps_tips_sevth1"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="cardType_add_02"  datatype="*"
                                                    name="cardType_02"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listCardType"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_02" name="rbid_02" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                </div>
                                <!-- 映射 -->
                                <div id="amt_add_div" style="display:none;">
                                    <div class="form-group" id="amt_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="main_range_div"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <input class="col-sm-5 i18n-flag" id="amt1_add_03"
                                                   type="text"
                                                   placeholder="ph_tps_tips_sev" name="amt1_03" maxlength="7"
                                                   datatype="n1-7"  title=main_start_range>
                                            <label class="col-sm-1">-</label>
                                            <input class="col-sm-5 i18n-flag" id="amt2_add_03"
                                                   type="text"
                                                   placeholder=ph_tps_tips_sev name="amt2_03" maxlength="7"
                                                   datatype="n1-7" title=main_end_range>

                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>

                                    </div>
                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_03"  name="rbid_03" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                </div>
                                <!-- 卡借贷记+映射 -->
                                <div id="cardtype_amt_add_div" style="display:none;">
                                    <div class="form-group" id="cardType_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="ph_tps_tips_sevth1"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="cardType_add_04" datatype="*"
                                                    name="cardType_04"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listCardType"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="amt_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="main_range_div"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <input class="col-sm-5 i18n-flag" id="amt1_add_04"
                                                   type="text" datatype="n1-7"
                                                   placeholder="ph_tps_tips_sev" name="amt1_04" maxlength="7"
                                                    title=main_start_range >
                                            <label class="col-sm-1">-</label>
                                            <input class="col-sm-5 i18n-flag" id="amt2_add_04"
                                                   type="text" datatype="n1-7"
                                                   placeholder="ph_tps_tips_sev" name="amt2_04" maxlength="7"
                                                    title=main_end_range >

                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7 " ></div>
                                    </div>
                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_04" name="rbid_04" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>
                                </div>
                                <!-- +卡借贷记映射 -->
                                <div id="issuer_cardtype_add_div" style="display:none;">
                                    <div class="form-group" id="issuerid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="ph_tps_tips_thirth"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="issuerid_add_05" datatype="*"
                                                    name="issuerid_05"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>
                                    <div class="form-group" id="cardType_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="ph_tps_tips_sevth1"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="cardType_add_05" datatype="*"
                                                    name="cardType_05"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listCardType"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>
                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font></label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_05" name="rbid_05" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>
                                </div>

                                <!--  -->
                                <div id="issuer_add_div" style="display:none;">
                                    <div class="form-group" id="issuerid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="ph_tps_tips_thirth"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="issuerid_add_06" datatype="*"
                                                    name="issuerid_06"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBankInfo"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>

                                    <div class="form-group" id="rbid_add_div">
                                        <label class="col-sm-3 col-sm-offset-1 control-label">
                                            <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                            <font color="red">*</font>
                                        </label>
                                        <div class="col-sm-7">
                                            <select id="rbid_add_06" name="rbid_06" datatype="*"
                                                    class="form-control ajax-select"
                                                    data-action="/common/listBranch?type=R"  rely-dialog data-refresh
                                                    name-rel="name" value-rel="id">
                                                <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-offset-4 col-sm-7"></div>
                                    </div>
                                </div>
                        </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary rkibtn_save i18n-flag ajax-submit"
                                            i18n-data="authority_save">
                                    </button>
                                    <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                            i18n-data="authority_close">
                                    </button>
                                </div>
                            </form>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <!----------------新增窗口---------------------->

            <!-- --------------详情窗口-------------------------------- -->
            <div id="modal_detail" class="modal fade modal-primary  ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                 data-backdrop="static" action="/branchMap/detail">
                <div class="modal-dialog" style="word-wrap:break-word;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">
                                <span class="i18n-flag" i18n-data="authority_details_query"></span>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal ajax-form" role="form" >
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="branch_in_channel"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lbid" name="lbid_name" after-echo="optionShow"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="error_data_in_merch_no"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lmid" name="lmid" class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="trade_type"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="cls" name="cls_name"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="map_type"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="bmf" name="bmf_name"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="branch_out_channel"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="rbid" name="rbid_name"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="issuerid_detail_div" >
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="card_agency"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="issuerid" name="issuerid_name" class="option-show-text" ></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="cardType_detail_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="ph_tps_tips_sevth1"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="cardType" name="cardtype_name" class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="amt1_detail_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="main_start_range"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt1" name="amt1" class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="amt2_detail_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="main_end_range"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt2" name="amt2" class="option-show-text"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="start_using"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="statusName" name="status"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="cp_check_state"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="astatusName" name="auditstatus"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="buildoper" name="buildoper"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="authority_create_time"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="builddatetime" name="builddatetime" ></span>
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
            <div id="modal_audit" class="modal fade modal-primary " tabindex="-1" role="dialog" aria-hidden="true"
                 data-backdrop="static" >
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><span class="i18n-flag"
                                                          i18n-data="main_audit"></span></h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal ajax-form" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="branch_in_channel"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lbid" name="lbid.name"></span>
                                    </div>
                                </div>
                                <div class="form-group" id="lmidBox">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="error_data_in_merch_no"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lmid" name="lmid"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="trade_type"></span>:</label>
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
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="branch_out_channel"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="rbid" name="rbid.name"></span>
                                    </div>
                                </div>
                                <div class="form-group" id="issuerid_audit_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="card_agency"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="issuerid" name="issuerid.name"></span>
                                    </div>
                                </div>
                                <div class="form-group" id="cardType_audit_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="ph_tps_tips_sevth1"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="cardType" name="cardType.name"></span>
                                    </div>
                                </div>
                                <div class="form-group" id="amt1_audit_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="main_start_range"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt1" name="amt1"></span>
                                    </div>
                                </div>
                                <div class="form-group" id="amt2_audit_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label"><span class="i18n-flag"
                                                                                                i18n-data="main_end_range"></span>:</label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt2" name="amt2"></span>
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
                            <button type="button" class="btn btn-primary rkibtn_audit_yes i18n-flag" id="audPassBnt"
                                    i18n-data="cp_pass">
                            </button>
                            <button type="button" class="btn btn-primary rkibtn_audit_no i18n-flag" id="audNotPassBnt"
                                    i18n-data="cp_no_pass">
                            </button>
                            <button type="button" class="btn btn-primary rkibtn_cancel i18n-flag" data-dismiss="modal"
                                    i18n-data="authority_close">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- -------审核窗口----------- -->

            <!-- --------------修改窗口-------------------------------- -->
            <div id="modal_update" class="modal fade modal-primary ajax-modal" tabindex="-1" role="dialog" aria-hidden="true"
                 data-backdrop="static" >
                <div class="modal-dialog" style="word-wrap:break-word;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><span  class="i18n-flag"
                                                          i18n-data="authority_modify"></span></h4>
                        </div>
                        <form id="updateForm" class="form-horizontal ajax-form " role="form" >
                            <div class="modal-body">
                                <div class="form-group" style="display:none;">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="hidden_key"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <input class="form-control" id="id_update" name="id" type="text" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="branch_in_channel"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lbid" name="lbid.name" after-echo="optionShow"></span>
                                    </div>
                                </div>
                                <div class="form-group " id="lmidBox">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="error_data_in_merch_no"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="lmid" name="lmid"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="trade_type"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="cls" name="cls_name"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="map_type"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="bmf" name="bmf.name"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="branch_out_channel"></span>
                                        <font color="red">*</font>:
                                    </label>
                                    <div class="col-sm-6 pax-div-detail">
                                        <select id="rbid_update" class="form-control ajax-select" name="rbid" datatype="*"
                                                data-action="/common/listBranch?type=R" data-refresh dialog-rely
                                                name-rel="name" value-rel="id">
                                            <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                        </select>
                                    </div>
                                    <div class="col-sm-offset-4 col-sm-7 " ></div>
                                </div>
                                <div class="form-group option-show" id="issuerid_update_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="card_agency"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="issuerid" name="issuerid.name"  class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="cardType_update_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="ph_tps_tips_sevth1"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="cardType" name="cardType.name" class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="amt1_update_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="main_start_range"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt1" name="amt1" class="option-show-text"></span>
                                    </div>
                                </div>
                                <div class="form-group option-show" id="amt2_update_div">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="main_end_range"></span>:
                                    </label>
                                    <div class="col-sm-8 pax-div-detail">
                                        <span id="amt2" name="amt2" class="option-show-text"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="authority_creator"></span>:
                                    </label>
                                    <div class="col-sm-6 pax-div-detail">
                                        <span id="buildoper" name="buildoper"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 col-sm-offset-1 control-label">
                                        <span class="i18n-flag" i18n-data="authority_create_time"></span>:
                                    </label>
                                    <div class="col-sm-6 pax-div-detail">
                                        <span id="builddatetime" name="builddatetime"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary rkibtn_save i18n-flag"
                                        i18n-data="authority_save">
                                </button>
                                <button type="button" class="btn btn-primary my-primary i18n-flag" data-dismiss="modal"
                                        i18n-data="authority_close">
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
                            <h4 class="modal-title">
                                <span  class="i18n-flag" i18n-data="access_merch"></span>
                            </h4>
                        </div>
                        <div id="modal_sel_cposMer_body" class="modal-body">
                            <jsp:include page="/dispatcher/selCposMer"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary rkibtn_save i18n-flag" id="selCposMer_addBnt"
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


<script src="${ctx}/js/busi/mapMgr/branchMap.js"></script>
<script>
    function branchMapTableOption($obj) {
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
                {"mDataProp": "lmid","sClass":"center","bSortable": false},
                {"mDataProp": "cls_name","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "bmf_name","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rbid_name","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "issuerid_name","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "cardtype_name","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "amt1","sClass":"center"},
                {"mDataProp": "amt2","sClass":"center"},
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
                        $(nTd).html('<shiro:hasPermission name="branchMap:detail"><div name="detail" class="oper_bg" style="display: block;" data-toggle="modal" data-target="#modal_detail" data-rel="id=' + oData.id + '" ><a title='+I("main_view")+' href="javascript:void(0)" class="oper oper_view"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="branchMap:update"><div name="update" class="oper_bg" style="display: block;"><a title='+I("authority_modify")+' href="javascript:update(\''+oData.id+'\')" class="oper oper_modify"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="branchMap:audit"><div name="audit" class="oper_bg" style="display: block;" ><a title='+I("main_audit")+' href="javascript:audit(\''+oData.id+'\')" class="oper oper_audit"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="branchMap:frozen"><div name="frozen" class="oper_bg" style="display: block;"><a title='+I("main_freeze")+' href="javascript:void(0)" class="oper oper_lock ajax-button" action-type="lock" action="/branchMap/frozen" table-rel="#branchMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_freeze")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="branchMap:unfrozen"><div name="unfrozen" class="oper_bg" style="display: block;"><a title='+I("thaw")+' href="javascript:void(0)" class="oper oper_unlock ajax-button" action-type="unlock" action="/branchMap/unfrozen" table-rel="#branchMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("confirm_thaw")+'"></a></div></shiro:hasPermission>'+
                            '<shiro:hasPermission name="branchMap:delete"><div name="del" class="oper_bg" style="display: block;"><a title='+I("authority_batch_delete")+' href="javascript:void(0)" class="oper oper_delete ajax-button" action-type="delete" action="/branchMap/delete" table-rel="#branchMapTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div></shiro:hasPermission>'
                        );
                    }
                }
            ]
        }
    }
</script>