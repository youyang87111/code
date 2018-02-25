var list_lterm_oTable;
$(function() {
    list_lterm_oTable = null;
    list_lterm_oTable = initLTermTable();

    // Enable check and uncheck all functionality
    $("#sel_checkall").click(function() {
        var obj = this;
        $("input[name='checkList_list']").each(function(i) {
            this.checked = obj.checked;
        });
    });

    $("#sel_lterm_search").click(function() {
        tb_lterm_search(1);
    });

    $("#sel_lterm_reset").click(function() {
        // $("#mid_rterm_sel").val("");
        // $("#mcr_rterm_sel").val("");
        // $("#name_rterm_sel").val("");
        $("#tid_lterm_sel").val("");
    });

    $("#sel_addLTermBnt").click(function() {
        var str = $("#sel_lterm_dt1 tbody input[type='radio']:checked").val();
        $('#modal_sel_lterm').modal('hide');
        $("#" + $("#cur_id_of_sel").val()).val(str).trigger("blur");

    });

    // $("#mcr_rterm_sel").append("<option value=''>"+R('cposmer_choose')+"</option>");
    // PAX_FUNCTION.getCommons("#mcr_rterm_sel", ctx + "/common/getBid?type=R",
    // 		{}, "id", "name");

});

/**
 * 表格初始化
 *
 * @returns {*|jQuery}
 */
function initLTermTable() {
    var option = $.extend(PAX_OBJECT.DataTable.option, {
        "sAjaxSource" : contextPath + "/cposTerm/list?search_status=2&search_mcr=" + window.mcr + "&search_mid="
        + window.mid,
        "sScrollY" : "180px",
        "sScrollX" : "100%",
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

        ],
        "fnDrawCallback" : function() {
            // PAX_AUTH_FUNCTION.operationFilter();
            var totalRecords = arguments[0]._iRecordsTotal;
            var displaylength = arguments[0]._iDisplayLength == 0 ? 1
                : arguments[0]._iDisplayLength;
            var totalpage = Math.ceil(totalRecords / displaylength);
            PAX_OBJECT.DataTable.Pageno("sel_lterm_dt1");
            PAX_OBJECT.DataTable.Pagego("sel_lterm_dt1", totalpage,
                tb_lterm_search);
        }
    });
    var table = $("#sel_lterm_dt1").dataTable(option);
    return table;
}

function tb_lterm_search(pageno) {
    console.log($("#tid_lterm_sel").val());
    var oSettings = list_lterm_oTable.fnSettings();
    oSettings._iDisplayStart = oSettings._iDisplayLength * (pageno - 1);
    oSettings["sAjaxSource"] = contextPath + "/cposTerm/list?search_mcr=" + window.mcr + "&search_mid="
        + window.mid + "&search_tid=" + $("#tid_lterm_sel").val() + "&search_status=2";
    list_lterm_oTable.fnDraw(false);
}
