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
                <div class="col-md-4">
                    <div class="input-group">
                        <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_no" ></span>
                        <input id="mid_rmer_sel" class="form-control input-sm" maxlength="15">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="input-group">
                        <span class="input-group-addon i18n-flag" i18n-data="cposmer_merch_name"></span>
                        <input id="name_rmer_sel" class="form-control input-sm" maxlength="25">
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="btn-group">
                        <button id="sel_rmer_search" class="btn btn-primary btn-sm rkibtn_search">
                            <i class="fa fa-search"></i><span class="i18n-flag" i18n-data="authority_query"></span>
                        </button>
                        <button id="sel_rmer_reset" class="btn btn-primary btn-sm rkibtn_reset">
                            <i class="fa fa-power-off"></i><span class="i18n-flag" i18n-data="authority_reset"></span>
                        </button>
                    </div>
                </div>
                <%--<div class="col-md-4">--%>
                    <%--<div class="input-group">--%>
                        <%--<span class="input-group-addon">商户号</span>--%>
                        <%--<input id="mid_rmer_sel" class="form-control input-sm" maxlength="15">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-3">--%>
                    <%--<div class="input-group">--%>
                        <%--<span class="input-group-addon">商户名称</span>--%>
                        <%--<input id="name_rmer_sel" class="form-control input-sm" maxlength="25">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-2">--%>
                    <%--<div class="btn-group">--%>
                        <%--<button id="sel_rmer_search" class="btn btn-primary btn-sm rkibtn_search">--%>
                            <%--<i class="fa fa-search"></i>查询--%>
                        <%--</button>--%>
                        <%--<button id="sel_rmer_reset" class="btn btn-primary btn-sm rkibtn_reset">--%>
                            <%--<i class="fa fa-power-off"></i>重置--%>
                        <%--</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>

        <table id="sel_rmer_dt1" class="table table-bordered table-hover pax-datatable-cls">
            <thead>
            <tr>
                <th width="20px">
                    <!-- <input type="checkbox" id="sel_checkall"> -->
                </th>
                <th width="160px" class="i18n-flag" i18n-data="cp_merch_from">
                    
                </th>
                <th width="300px" class="i18n-flag" i18n-data="cposmer_merch_no">
                   
                </th>
                <th width="300px" class="i18n-flag" i18n-data="cposmer_merch_name">
                   
                </th>
                <%--<th width="160px" class="i18n-flag" i18n-data="cp_merch_from">--%>
                    <%--商户来源--%>
                <%--</th>--%>
                <%--<th width="300px" class="i18n-flag" i18n-data="cposmer_merch_no">--%>
                    <%--商户号--%>
                <%--</th>--%>
                <%--<th width="300px" class="i18n-flag" i18n-data="cposmer_merch_name">--%>
                    <%--商户名称--%>
                <%--</th>--%>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script src="${ctx}/js/busi/resourceMgr/selRMer2.js" type="text/javascript"></script>