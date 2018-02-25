var list_oTable;
$(function() {
	list_oTable = null;
	list_oTable = initTable();

	// Enable check and uncheck all functionality
	$("#sel_checkall").click(function() {
		var obj = this;
		$("input[name='checkList_list']").each(function(i) {
			this.checked = obj.checked;
		});
	});

	$("#sel_search").click(function() {
		tb_search(1);
	});

	$("#sel_reset").click(function() {
		$("#mid_sel").val("");
		$("#mcr_sel").val("");
		$("#name_sel").val("");
	});

	$("#selCposMer_addBnt").click(function() {
		var str = $("#selCposMer_dt1 tbody input[type='radio']:checked").val();
		$('#modal_sel_cposMer').modal('hide');
		$('#lmcr_add').val(str.split('-')[0]);
		$('#lmcr_name_add').val(str.split('-')[1]);
		$('#lmid_add').val(str.split('-')[2]).trigger("blur");
	});

	// $("#mcr_sel").append("<option value=''>"+I('cposmer_choose')+"</option>");
	// PAX_FUNCTION.getCommons("#mcr_sel", contextPath + "/common/getBid?type=C", {},
	// 		"id", "name");
});

/**
 * 表格初始化
 * 
 * @returns {*|jQuery}
 */
function initTable() {//表格初始化
	var option = $
			.extend(
					PAX_OBJECT.DataTable.option,
					{
						"sAjaxSource" : contextPath + "/cposMer/list?search_status=2&search_mcr="
								+ window.mcr,
						"sScrollY" : "180px",
						"sScrollX" : "100%",
						"aoColumns" : [
								{
									"mDataProp" : null,
									"sClass" : "center2",
									"bSortable" : false,
									"fnCreatedCell" : function(nTd, sData,
											oData, iRow, iCol) {
										$(nTd).html(
												"<input type='radio' name='checkList_list' value='"
														+ oData.mcr.id + '-'
														+ oData.mcr.name + '-'
														+ oData.mid + "'>");

									}
								}, {
									"mDataProp" : "mcrName",
									"sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                $(nTd).text(_T(sData));
            }
								}, {
									"mDataProp" : "mid",
									"sClass" : "center"
								}, {
									"mDataProp" : "name",
									"sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                $(nTd).text(_T(sData));
            }
								}

						],
						"fnDrawCallback" : function() {
							// PAX_AUTH_FUNCTION.operationFilter();
							var totalRecords = arguments[0]._iRecordsTotal;
							var displaylength = arguments[0]._iDisplayLength == 0 ? 1
									: arguments[0]._iDisplayLength;
							var totalpage = Math.ceil(totalRecords
									/ displaylength);
							PAX_OBJECT.DataTable.Pageno("selCposMer_dt1");
							PAX_OBJECT.DataTable.Pagego("selCposMer_dt1",
									totalpage, tb_search);
						}
					});
	var table = $("#selCposMer_dt1").dataTable(option);
	return table;
}

function tb_search(pageno) {//查询

	var oSettings = list_oTable.fnSettings();
	oSettings._iDisplayStart = oSettings._iDisplayLength * (pageno - 1);

	oSettings["sAjaxSource"] = contextPath + "/cposMer/list?search_mcr=" + window.mcr + "&search_mid="
			+ $("#mid_sel").val() + "&search_name=" + $("#name_sel").val()
			+ "&search_status=2";
	list_oTable.fnDraw(false);
}
