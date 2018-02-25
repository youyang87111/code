<%@ page language="java" import="org.apache.shiro.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ page import="org.apache.shiro.subject.Subject"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@page import="org.apache.shiro.session.Session"%>

<%
	Subject subject = SecurityUtils.getSubject();
	String orgs = (String)subject.getSession().getAttribute("orgs");
%>

<div class="row">

	<c:set var="ctx" value="${pageContext.request.contextPath}"/>

	<script>
        var contextPath='${pageContext.request.contextPath}'
        var orgs = "<%=orgs%>";
	</script>



	<style type="text/css">
		table tbody tr:nth-child(even) td {background-color: #F2F5F6;}
		table tbody tr:hover td{
			background-color: #eafdff;
		}
		td{ border:solid 1px white}
	</style>


	<script type="text/javascript">

        var arrayQueue = new Array();

        var arraySize = 20;
        

        //初始话WebSocket
        function initWebSocket() {
            if (window.WebSocket) {
            		
                var hr = document.location.href;
                var path =  'ws://'+document.location.host+contextPath+'/transMonitor/cpos/'+orgs;
                if(hr.indexOf("https")!= -1){
                    path =  'wss://'+document.location.host+contextPath+'/transMonitor/cpos/'+orgs;
                }
                
				if(typeof websocket != "undefined"){
					websocket.close();
				}
				
				websocket = new WebSocket(encodeURI(path));
				
                websocket.onopen = function() {
                }
                
                websocket.onerror = function(e) {
                    //alert("连接失败");
                }
                websocket.onclose = function(e) {
                    //连接断开
                    websocket.onclose = null;
                    websocket.onmessage = null;
                    arrayQueue = new Array();
                    initWebSocket();
                }

                //消息接收
                websocket.onmessage = function(message) {

                    var message = JSON.parse(message.data);

                    //message里面最新的交易排在前面 321
                    for(var i = message.length-1 ; i >= 0; i--){
                        if (arrayQueue.length == arraySize) {
                            arrayQueue.shift();//删除并返回数组的第一个元素 123
                        }

                        arrayQueue.push(message[i]);//向数组的末尾添加一个或更多元素，并返回新的长度。123
                    }
                    
                    var locale = $.cookie("locale");
                    
                    var dtable='';

                    for ( var i = arrayQueue.length-1; i >= 0 ; i--) {

                        var RSPCODE = arrayQueue[i]['RSPCODE'];
                        if(RSPCODE=='00'){
                            dtable+='<tr align="center">';
                        }else if(RSPCODE=='PW'){
                            dtable+='<tr align="center" style="color: blue;">';
                        }else{
                            dtable+='<tr align="center" style="color: red;">';
                        }
                        
                        var RSPINFO = arrayQueue[i]['RSPINFO'];
                        if(locale == 'en_US'){
                        	RSPINFO = arrayQueue[i]['RSPINFO_EN'];
                        }
                        
                        var TRNAME = arrayQueue[i]['TRNAME'];
                        if(locale == 'en_US'){
                        	TRNAME = arrayQueue[i]['TRNAME_EN'];
                        }

                        dtable +='<td nowrap>'+arrayQueue[i]['MID']+'&nbsp' + '</td>'
                            + '<td nowrap>'+arrayQueue[i]['TID']+'&nbsp' + '</td>'
                            + '<td nowrap>'+TRNAME+'&nbsp' + '</td>'
                            + '<td nowrap>'+arrayQueue[i]['CURRENCY']+'&nbsp' + ' </td>'
                            + '<td nowrap>'+arrayQueue[i]['AMT']+'&nbsp' + '</td>'
                            + '<td nowrap>'+arrayQueue[i]['BATCHNO']+'&nbsp' + '</td>'
                            + '<td nowrap>'+arrayQueue[i]['TRACENO'] +'&nbsp'+ '</td>'
                            + '<td nowrap>'+arrayQueue[i]['OUT_TRADE_NO'] +'&nbsp'+ '</td>'
                            + '<td nowrap>'+arrayQueue[i]['DATETIME'] +'&nbsp'+ '</td>'
                            + '<td nowrap>'+arrayQueue[i]['CONSUME'] +'&nbsp'+ '</td>'
                            + '<td nowrap>'+arrayQueue[i]['RSPCODE'] +'&nbsp'+ '</td>'
                            + '<td nowrap>'+RSPINFO+'&nbsp' + '</td>';

                        dtable+='</tr>';

                    }
                    

                    $("#EVENT").html(dtable);
                }
            }else{
                alert(I("not_support"));
            }

        };

        $(function(){
            initWebSocket();
        })

	</script>

	<div class="col-lg-12 col-xs-12">
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title i18n-flag" i18n-data="join_terminal_trade"></h3>
			</div><!-- /.box-header -->
			<div class="box-body" style="overflow-x: auto; overflow-y: auto;min-height: 450px">
				<table border="1" cellpadding="3" cellspacing="1" width="100%" style="border-color: #74DCEF;">
					<thead style="background-color: #74DCEF;font-size: 15px;color: white;">
					<tr align="center">
						<td nowrap width="150px" class="i18n-flag" i18n-data="cposmer_merch_no"></td>
						<td nowrap width="120px" class="i18n-flag" i18n-data="cp_terminal_no"></td>
						<td nowrap width="150px" class="i18n-flag" i18n-data="cp_deal_name"></td>
						<td nowrap width="80px" class="i18n-flag" i18n-data="monitor_currency"></td>
						<td nowrap width="120px" class="i18n-flag" i18n-data="money_else"></td>
						<td nowrap width="120px" class="i18n-flag" i18n-data="main_batch_num"></td>
						<td nowrap width="120px" class="i18n-flag" i18n-data="main_flow_num"></td>
						<td nowrap width="150px" class="i18n-flag" i18n-data="main_merch_order_num"></td>
						<td nowrap width="150px" class="i18n-flag" i18n-data="cp_deal_time"></td>
						<td nowrap width="200px" class="i18n-flag" i18n-data="cost_time"></td>
						<td nowrap width="150px" class="i18n-flag" i18n-data="reponse_num"></td>
						<td nowrap width="350px" class="i18n-flag" i18n-data="reponse_info"></td>
					</tr>
					</thead>
					<tbody id="EVENT">

					</tbody>
				</table>
			</div><!-- /.box-body -->
		</div><!-- /.box -->
	</div><!-- /.col -->

</div><!-- /.row -->
