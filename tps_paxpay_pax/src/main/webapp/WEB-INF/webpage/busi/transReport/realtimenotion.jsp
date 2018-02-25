<%--
  Created by IntelliJ IDEA.
  User: oo
  Date: 2017/10/13
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
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
                <h3 class="box-title i18n-flag" i18n-data="trans_syn_status"></h3>
            </div><!-- /.box-header -->
            <div class="box-body self-box-body">
                <form class="search-form" for-table="rtNotionTable">
                    <div style="">
                        <div class="row">
	                        <div class="col-md-6 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="trans_oder_num"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="sysid_s" class="form-control input-sm" maxlength="32" name="transactionNo">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_no"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input class="form-control input-sm" maxlength="15" name="mchId">
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <div class="row">
                        	<div class="col-md-6 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_name"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input class="form-control input-sm" maxlength="30" name="mchName">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="cp_terminal_no"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <input id="rmid_s" class="form-control input-sm" maxlength="15" name="terId">
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <div class="row">
                        	<div class="col-md-6 pad-left">
                                <div class="input-group col-md-12">
                                    <div class="col-md-6">
                                        <span class="input-group-addon i18n-flag" i18n-data="status_now"></span>
                                    </div>
                                    <div class="col-md-6 data-list-sty">
                                        <select class="form-control input-sm ajax-select" name="status">
                                            <option value="1" class="i18n-flag" i18n-data="sync_succ"></option>
                                            <option value="-1" class="i18n-flag" i18n-data="sync_fail"></option>
                                            <option value="0" class="i18n-flag" i18n-data="not_sync"></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                        <div class="btn-group">
                            <%--<shiro:hasPermission name="RealtimeNotion:list">--%>
                            <button id="search" class="btn btn-primary btn-sm rkibtn_search search" type="button">
                                <i class="fa fa-search"></i>
                                <span class="i18n-flag" i18n-data="authority_query"></span>
                            </button>
                            <%--</shiro:hasPermission>--%>
                            <button class="btn btn-primary btn-sm rkibtn_reset reset" type="button" not-reset="notReset">
                                <i class="fa fa-power-off"></i>
                                <span class="i18n-flag" i18n-data="authority_reset"></span>
                            </button>
                            <%--<shiro:hasPermission name="cposhistrans:export">--%>
                            <%--<button id="export" class="btn btn-primary btn-sm rkibtn_export export" type="button">--%>
                                <%--<i class="glyphicon glyphicon-export"></i>--%>
                                <%--<span class="i18n-flag" i18n-data="main_export">导出</span>--%>
                            <%--</button>--%>
                            <%--</shiro:hasPermission>--%>
                        </div><!-- /.btn-group -->
                    </div>
                </form>
            </div>
            <table id="rtNotionTable" class="table table-bordered table-hover pax-datatable-cls ajax-table" autoload data-action="/realtimenotion/list" optionfn="rtNotionTableOption">
                <thead>
                <tr>
                    <th width="130px" class="i18n-flag" i18n-data="trans_oder_num"></th>
                    <th width="120px" class="i18n-flag" i18n-data="cposmer_merch_no"></th>
                    <th width="150px" class="i18n-flag" i18n-data="cposmer_merch_name"></th>
                    <th width="100px" class="i18n-flag" i18n-data="cp_terminal_no"></th>
                    <th width="100px" class="i18n-flag" i18n-data="terri_money"></th>
                    <th width="120px" class="i18n-flag" i18n-data="payment_time"></th>
                    <th width="120px" class="i18n-flag" i18n-data="trans_type"></th>
                    <th width="90px" class="i18n-flag" i18n-data="retransmission_num"></th>
                    <th width="120px" class="i18n-flag" i18n-data="notify_status"></th>
                    <th width="150px" class="i18n-flag" i18n-data="last_time_msg"></th>
                    <th width="80px" class="i18n-flag" i18n-data="authority_operation"></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div><!-- /.box-body -->
    </div><!-- /.box -->
</div><!-- /.col -->
</div><!-- /.row -->
<script src="${ctx}/js/busi/transReport/realtimeNotion.js" type="text/javascript"></script>
<script src="${ctx}/js/plugin/zTree/jquery.ztree.all-3.5.min.js"></script>
<script>
    function notReset() {
        var setDate=formatYes();
        $("#transdateb_s,#transdatee_s").val(setDate);
        $("#search").trigger("click");
    }
    notReset();
    function rtNotionTableOption(){
        return {
            "aoColumns": [
                {"mDataProp": "transactionNo","sClass":"center","bSortable": false},
                {"mDataProp": "mchId","sClass":"center","bSortable": false},
                {"mDataProp": "mchName","sClass":"center","bSortable": false},
                {"mDataProp": "terId","sClass":"center","bSortable": false},
                {"mDataProp": "totalAmount","sClass":"center","bSortable": false},
                {"mDataProp": "paymentTime","sClass":"center","bSortable": false},
                {"mDataProp": "tradeType","sClass":"center","bSortable": false},
                {"mDataProp": "sendTimes","sClass":"center","bSortable": false},
                {"mDataProp": null,"sClass":"center","bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        var status = oData.status1;
                        if(status==""){
                            $(nTd).text(I("sync_succ"));
                        }else if(status==1){
                            $(nTd).text(I("sync_fail"));
                        }
                        else{
                            $(nTd).text(I("not_sync"));
                        }
                    }
                },
                {"mDataProp": "msg","sClass":"center","bSortable": false,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<span title='" + sData + "'>" + sData + "</span>");
                }},
                {"mDataProp": null,"sClass":"center","bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var status = oData.status1;
                        if(status==""){
                            $(nTd).html('');
                        }else{
                            $(nTd).html('<div name="resetKey" class="oper_bg"><a title='+I("retrainsmission")+' href="javascript:retransmission(\''+oData.transactionNo+'\')" class="oper oper_reset"></a></div>');
//                            $(nTd).html('<div name="frozen" class="oper_bg" style="display: block;"><a title='+I("retrainsmission")+' href="javascript:retransmission(\''+oData.transactionNo+'\')" class="oper oper_reset"></a></div>');
                        }
                    }
                }
            ]
        }
    }
</script>
