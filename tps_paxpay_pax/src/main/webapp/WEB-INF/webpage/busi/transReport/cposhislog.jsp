<%--
  Created by IntelliJ IDEA.
  User: oo
  Date: 2017/10/13
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
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
                <h3 class="box-title i18n-flag" i18n-data="trans_log"></h3>
            </div><!-- /.box-header -->
            <div class="box-body">
                <form class="search-form" for-table="cposhislogTable" before-search="beforeSearch('transdateb_s','transdatee_s','amountb_s','amounte_s')">
                    <div style="">
                        <div class="row">
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="branch_in_channel"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <select id="bid_s" class="form-control input-sm ajax-select" name-rel="name" value-rel="id" name="bid" data-action="/common/listBranch?type=C">
                                            <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="error_data_in_merch_no_access"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="mid_s" class="form-control input-sm" maxlength="15" name="mid">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="error_data_in_terminal_no_access"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="tid_s" class="form-control input-sm" maxlength="8" name="tid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="branch_out_channel"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <select id="rbid_s" class="form-control input-sm ajax-select" name="rbid" name-rel="name" value-rel="id"  data-action="/common/listBranch?type=R">
                                            <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="error_data_out_merch_no"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="rmid_s" class="form-control input-sm" maxlength="15" name="rmid">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="errordata_export_terminal_no"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="rtid_s" class="form-control input-sm" maxlength="8" name="rtid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="cp_deal_name"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <select id="innid_s" class="form-control input-sm ajax-select" name="innid" name-rel="name" value-rel="id"  data-action="/common/listInntransdef?classgroup=01" name="innid">
                                            <option value="" class="i18n-flag" i18n-data="cposmer_choose" fixed></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="trade_system_id"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="sysid_s" class="form-control input-sm" maxlength="18" name="sysid">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="organization_message"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="cposhistransOrgId" name="orgId" class="form-control input-sm" name="orgId" style="display: none">
                                        <span>
										<button type="button" id="cposhistransOrgBtn" style="background:white; border-color:#d2d6de;" class="btn btn-maroon dropdown-toggle form-control input-sm" data-toggle="dropdown" aria-expanded="false">
											<span class="fa fa-caret-down"></span>
											<span class="i18n-flag" i18n-data="cposmer_choose"></span>
										</button>
										<ul class="dropdown-menu">
											<li>
												<ul id="cposhistransOrgTree" class="ztree" style="width: 330px;height: 400px;overflow:auto;overflow-x:hidden;"></ul>
											</li>
										</ul>
									</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="trade_money_range" style="line-height:58px;"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="amountb_s" class="form-control input-sm" maxlength="10" name="amountb"><input id="amounte_s" class="form-control input-sm" maxlength="10" name="amounte">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="trade_date_range" style="line-height:58px;"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="transdateb_s" readonly="readonly" class="form-control input-sm" name="transdateb" maxlength="7" data-date-end-date="0d">
                                        <input id="transdatee_s" name="transdatee" readonly="readonly" class="form-control input-sm" maxlength="7" data-date-end-date="0d">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                        <!--<button id="checkall" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>-->
                        <div class="btn-group" id="myBtnBox">
                            <shiro:hasPermission name="cposhistrans:list">
                                <button id="search" class="btn btn-primary btn-sm rkibtn_search search" type="button">
                                    <i class="fa fa-search"></i>
                                    <span class="i18n-flag" i18n-data="authority_query"></span>
                                </button>
                            </shiro:hasPermission>
                            <button class="btn btn-primary btn-sm rkibtn_reset reset" type="button" not-reset="notReset">
                                <i class="fa fa-power-off"></i>
                                <span class="i18n-flag" i18n-data="authority_reset"></span>
                            </button>
                            <shiro:hasPermission name="cposhistrans:export">
                                <button id="export" class="btn btn-primary btn-sm rkibtn_export export" type="button">
                                    <i class="glyphicon glyphicon-export"></i>
                                    <span class="i18n-flag" i18n-data="main_export"></span>
                                </button>
                            </shiro:hasPermission>
                        </div><!-- /.btn-group -->
                    </div>
                </form>
            </div>
            <table id="cposhislogTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" autoload data-action="/cposhislog/list" optionfn="cposhislogTableOption">
                <thead>
                <tr>
                    
                    <th width="120px" class="i18n-flag" i18n-data="trade_system_id"></th>
                       <th width="100px" class="i18n-flag" i18n-data="branch_in_channel"></th>
                       <th width="120px" class="i18n-flag" i18n-data="error_data_in_merch_no_access"></th>
                       <th width="100px" class="i18n-flag" i18n-data="error_data_in_terminal_no_access"></th>
                       <th width="120px" class="i18n-flag" i18n-data="cp_deal_name"></th>
                       <th width="60px" class="i18n-flag" i18n-data="currency_type"></th>
                       <th width="90px" class="i18n-flag" i18n-data="money_else"></th>
                       <th width="80px" class="i18n-flag" i18n-data="main_batch_num"></th>
                       <th width="80px" class="i18n-flag" i18n-data="main_flow_num"></th>
                       <th width="150px" class="i18n-flag" i18n-data="main_merch_order_num"></th>
                       <th width="150px" class="i18n-flag" i18n-data="main_pay_order_num"></th>
                       <th width="120px" class="i18n-flag" i18n-data="cp_deal_time"></th>
                       <th width="80px" class="i18n-flag" i18n-data="organization_message"></th>
                       <th width="150px" class="i18n-flag" i18n-data="branch_out_channel"></th>
                       <th width="150px" class="i18n-flag" i18n-data="error_data_out_merch_no"></th>
                       <th width="120px" class="i18n-flag" i18n-data="error_data_out_terminal"></th>
                   	<th width="230px" class="i18n-flag" i18n-data="main_trade_result"></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div><!-- /.box-body -->
    </div><!-- /.box -->
</div><!-- /.col -->
</div><!-- /.row -->
<script src="${ctx}/js/busi/transReport/cposhislog.js" type="text/javascript"></script>
<script src="${ctx}/js/plugin/zTree/jquery.ztree.all-3.5.min.js"></script>
<script>
    function notReset() {
        var setDate=formatYes();
        $("#transdateb_s,#transdatee_s").val(setDate);
        $("#search").trigger("click");
    }
    notReset();
    function cposhislogTableOption(){
        return {
            "aoColumns": [
                {"mDataProp": "sysid","sClass":"center"},
                {"mDataProp": "lbid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "mid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "tid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "innidName","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "currency","sClass":"center"},
                {"mDataProp": "amount","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "batchno","sClass":"center"},
                {"mDataProp": "traceno","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "notes1","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "notes2","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": null,"sClass":"center","fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    $(nTd).html(oData.transdate + "  "+oData.transtime)
                }},
//                {"mDataProp": "transtime","sClass":"center"},
                {"mDataProp": "cposMer.orgName","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rbid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rmid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "rtid","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }},
                {"mDataProp": "notes8","sClass":"center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }}
            ]
        }
    }
</script>