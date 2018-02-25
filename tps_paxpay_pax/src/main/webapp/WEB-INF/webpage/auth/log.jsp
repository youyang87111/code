<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    var contextPath='${pageContext.request.contextPath}'
</script>
<section class="" style="background-color: #fff;">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title i18n-flag" i18n-data="log_man_full"></h3>
                </div>

                <div class="box-body">
                    <div style="padding: 5px;border-bottom: 1px solid #F4F4F4;">
                        <div id="bankCheckList" class="row">
                            <form  class="search-form" for-table="logTable">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="input-group col-md-12">
                                            <div class="col-md-4">
                                                <span class="input-group-addon i18n-flag" i18n-data="name_propertity" style="text-align: left;"></span>
                                            </div>
                                            <div class="col-md-8">
                                                <input class="form-control input-sm" type="text"
                                                       name="name"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group col-md-12">
                                            <div class="col-md-4">
                                                <span class="input-group-addon i18n-flag" i18n-data="role_oper" style="text-align: left;">操作员</span>
                                            </div>
                                            <div class="col-md-8">
                                                <input class="form-control input-sm" type="text"
                                                       name="operator"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group col-md-12">
                                            <div class="col-md-4">
                                                <span class="input-group-addon i18n-flag" i18n-data="role_oper_time"
                                                      style="text-align: left;"></span>
                                            </div>
                                            <div class="col-md-8">
                                                <input class="form-control input-sm" type="text" data-date-end-date="0d" id="logTime"
                                                       name="operatetime" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
										<div class="input-group col-md-12">
											<div class="col-md-4">
												<span class="input-group-addon i18n-flag" i18n-data="role_oper_result"></span>
											</div>
											<div class="col-md-8">
												<select id="flag_s" name="flag" class="form-control input-sm" >
													<option value="" class="i18n-flag" i18n-data="cposmer_choose"></option>
													<option value="0" class="i18n-flag" i18n-data="role_oper_succ"></option>
													<option value="1" class="i18n-flag" i18n-data="role_oper_fail"></option>
												</select>
											</div>
										</div>
									</div>
                                </div>
                                <div class="row pt10">
                                    <div class="col-md-12">
                                        <div class="btn-group" style="margin-left:15px;">
                                            <button class="btn btn-primary rkibtn_search btn-sm search"
                                                    type="button" style="min-width: 40px;">
                                                <i class="glyphicon glyphicon-zoom-in "></i>
                                                <span class="i18n-flag" i18n-data="authority_query"></span>
                                            </button>
                                            <button class="btn btn-primary rkibtn_reset btn-sm reset"
                                                    type="button" style="min-width: 40px;"><span
                                                    class="glyphicon glyphicon-asterisk i18n-flag"
                                                    i18n-data="authority_reset"></span>
                                            </button>

                                            <%--<shiro:hasPermission name="log:delete">--%>
                                                <%--<button id="delete_all_site" class="btn btn-primary btn-sm  rkibtn_batchdel ajax-button i18n-flag" action-type="batchdel" action="/log/delete" table-rel="#logTable" param-name="ids" confirmmsg=main_confirm_remove type="button" >--%>
                                                    <%--<span class="glyphicon glyphicon-plus"></span><span class="i18n-flag" i18n-data="delete_all">批量删除</span>--%>
                                                <%--</button>--%>
                                            <%--</shiro:hasPermission>--%>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <!-- 操作栏 -->
                    <table id="logTable" width="100%"
                           class="pax-datatable-cls table table-responsive table-condensed ajax-table"
                           data-action="/log/list" autoload optionfn="logTableOption">
                        <thead>
                        <tr>
                            <th width:="5%" class="dataTable-td">
                                <input type="checkbox" class="checkall">
                            </th>
                            <th width="8%" class="dataTable-td">ID
                            </th>
                            <th width="20%" class="dataTable-td i18n-flag" i18n-data="role_oper">
                                操作员
                            </th>
                            <th width="20%" class="dataTable-td i18n-flag" i18n-data="role_oper_time">
                                操作时间
                            </th>
                            <th width="10%" class="dataTable-td i18n-flag" i18n-data="name_propertity">
                                名称
                            </th>
                            <th width="10%" class="dataTable-td i18n-flag" i18n-data="branch_description">
                                描述
                            </th>
                            <th width="15%" class="dataTable-td i18n-flag" i18n-data="role_oper_result">
                                操作结果
                            </th>
                            <th width="15%;" class="dataTable-td i18n-flag"
                                i18n-data="authority_operation">操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>




                    <%--查看站点开始--%>
                    <div id="modal_ref_data" class="modal fade modal-primary " tabindex="-1" role="dialog" aria-hidden="true"
                         data-backdrop="static"  >
                        <div class="modal-dialog">
                            <div class="modal-content " style="width: 1000px;left: -200px;">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title i18n-flag" i18n-data="ref_detail">日志参数详情</h4>
                                </div>
                                    <div class="modal-body" >
                                        <div class="row" id="logRefDataContent">

                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary my-primary i18n-flag"
                                                data-dismiss="modal" i18n-data="authority_close">关闭
                                        </button>
                                    </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    <%--查看站点结束--%>

                </div>
            </div>
        </div>
    </div>


</section>

<script>

    //日期插件中英文转换
    var locale = $.cookie("locale");
    var locaLan="zh-CN";
    if(locale="en_US"){
        locaLan="en-US";
    }else{
        locaLan="zh-CN";
    }
    $("#logTime").datepicker({format:"yyyy-mm-dd",language:locaLan});//调用日期插件

    function logTableOption($obj) {//日志管理列表
        return {
            "aoColumns" : [
                {
                    "mDataProp": null,
                    "sClass":"center2",
                    "bSortable": false,
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<input type='checkbox' name='checkList' value='" + oData.id  + "'>");
                    }
                },
                {
                    "sWidth":"8%",
                    "mDataProp" : "id",
                    "sClass" : "center",
                    "bSortable" : false,
                },
                {
                    "sWidth":"12%",
                    "mDataProp" : "operator",
                    "sClass" : "center",
                    "bSortable" : false,
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
                    }
                },
                {
                    "sWidth":"16%",
                    "mDataProp" : "operatetime",
                    "sClass" : "center",
                    "bSortable" : false,
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<span title='" + dateFormat(sData) + "'>" + dateFormat(sData) + "</span>");
                    }
                },
                {
                    "sWidth":"16%",
                    "mDataProp" : "name",
                    "sClass" : "center",
                    "bSortable" : false,
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
                    }
                },
                {
                    "sWidth":"16%",
                    "mDataProp" : "description",
                    "sClass" : "center",
                    "bSortable" : false,
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<span title='" + sData + "'>" + _T(sData) + "</span>");
                    }
                },
                {
                    "sWidth":"16%",
                    "mDataProp" : "flag",
                    "sClass" : "center",
                    "bSortable" : false,
                },
                {
                    "sWidth":"10%",
                    "mDataProp" : null,
                    "bSortable" : false,
                    "sClass" : "center2",
                    "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                        var btns = '';
                        /* 查看日志参数详情 */
                        btns += ('<shiro:hasPermission name="log:getParamsById"> <div name="detail" class="oper_bg" style="display: block;"  data-rel="id=' + oData.id + '"><a title='+ I("main_ref_detail")+' href="javascript:detail_ref_data(\''+oData.id+'\')" class="oper oper_view"></a></div></shiro:hasPermission> ');
                        <%--/* 删除 <shiro:hasPermission name="log:delete"> */--%>
                        <%--btns += ('<div name="delete" class="oper_bg" style="display: block;" table-rel="#logTable"><a title='+ I("authority_batch_delete")+' href="javascript:void(0);" class="oper oper_delete ajax-button" action-type="delete" action="/log/delete.do" table-rel="#siteTable" param-name="ids" param-value="'+oData.id+'" confirmmsg="'+I("main_confirm_remove")+'"></a></div>');--%>
                        <%--/*  </shiro:hasPermission> */--%>
                        $(nTd).html(btns);
                    }
                }
            ]
        }
    }

    function detail_ref_data(id) {//查看日志参数详情
        var load = new Loading();
        load.init();
        load.start();
        PAX_FUNCTION.ajaxGet(contextPath+"/log/getParamsById",{"search_id":id},function(data){
            load.stop();
            var odata=data.aaData;
            var  innerHtml='';
            $(odata).each(function (index,ele) {
            	innerHtml+='<div class="col-sm-12"><div class="col-sm-6">'+I("ref_name")+'：'+ele.name+'</div><div class="col-sm-6" style="width:50%;word-wrap:break-word;" >'+I("ref_val")+'：'+ele.val+'</div></div>'
            });
            $("#logRefDataContent").html(innerHtml);
            $('#modal_ref_data').modal();
        },function (data) {
            load.stop();
            PAX_OBJECT.Messenger.alert('error', data.msg);
        });
    }
</script>