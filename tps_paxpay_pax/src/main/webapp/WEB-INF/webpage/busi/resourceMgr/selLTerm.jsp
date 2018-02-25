<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>
<div class="box">
	<div class="box-body">
		<div style="padding: 5px; border-bottom: 1px solid #F4F4F4;">
			<div class="row">

				<!--
				<div class="col-md-3">
	                 <div class="input-group">
	                 	<span class="input-group-addon">接入渠道</span>
	                 	<select id="mcr_lterm_sel" class="form-control input-sm"></select>
	                 </div>
                </div>
                <div class="col-md-4">
	                 <div class="input-group">
	                 	<span class="input-group-addon">商户号</span>
	                 	<input id="mid_lterm_sel" class="form-control input-sm" maxlength="15">
	                 </div>
                </div>
                <div class="col-md-3">
	                 <div class="input-group">
	                 	<span class="input-group-addon">商户名称</span>
	                 	<input id="name_lterm_sel" class="form-control input-sm" maxlength="25">
	                 </div>
                </div> -->
				<form class="search-form" for-table="sel_lterm_dt1">
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon i18n-flag" i18n-data="cp_terminal_no"></span>
							<input id="tid_lterm_sel" class="form-control input-sm" maxlength="8" name="tid">
						</div>
					</div>
					<div class="col-md-2">
						<div class="btn-group">
							<button id="sel_lterm_search" class="btn btn-primary btn-sm rkibtn_search search">
								<i class="fa fa-search"></i><span class="i18n-flag" i18n-data="authority_query"></span>
							</button>
							<button id="sel_lterm_reset" class="btn btn-primary btn-sm rkibtn_reset reset">
								<i class="fa fa-power-off"></i><span class="i18n-flag" i18n-data="authority_reset"></span>
							</button>
						</div>
					</div>
				</form>

			</div>
		</div>

		<table id="sel_lterm_dt1" class="table table-bordered table-hover pax-datatable-cls ajax-select" rely-dialog optionfn="selLTermTableOption">
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

<script>
    function selLTermTableOption(){
        return{
            "aoColumns" : [
                {
                    "mDataProp" : null,
                    "sClass" : "center2",
                    "bSortable" : false,
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).html(
                            "<input type='radio' name='checkList_list' value='"
                            + oData.tid + "'>");
                    }
                }, {
                    "mDataProp" : "mcrName",
                    "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).text(_T(sData));
                    }
                }, {
                    "mDataProp" : "mer.mid",
                    "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).text(_T(sData));
                    }
                }, {
                    "mDataProp" : "mer.name",
                    "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).text(_T(sData));
                    }
                }, {
                    "mDataProp" : "tid",
                    "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).text(_T(sData));
                    }
                }

            ]
		}
	}
</script>


<%--<script src="${ctx}/js/busi/mapMgr/selLTermxx.js" type="text/javascript"></script>--%>