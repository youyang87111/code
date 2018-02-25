var list_rmer_oTable;
$(function() {
    list_rmer_oTable = null;
    list_rmer_oTable = initRmerTable();

    // Enable check and uncheck all functionality
    $("#sel_checkall").click(function() {
        var obj = this;
        $("input[name='checkList_list']").each(function(i) {
            this.checked = obj.checked;
        });
    });

    $("#sel_rmer_search").click(function() {
        tb_rmer_search(1);
    });

    $("#sel_rmer_reset").click(function() {
        $("#mid_rmer_sel").val("");
        $("#mcr_rmer_sel").val("");
        $("#name_rmer_sel").val("");
    });

    $("#sel_rmer_addBnt").click(function() {
        var str = $("#sel_rmer_dt1 tbody input[type='radio']:checked").val();
        $('#modal_rMer_sel').modal('hide');

        var old = $('#rmid_add').val();
        if (old != str) {
            $("#rtid_add").val('');
        }

        $("#" + $("#cur_id_of_rmid").val()).val(str).trigger("blur");

    });

    // $("#mcr_rmer_sel").append("<option value=''>"+I('cposmer_choose')+"</option>");
    // PAX_FUNCTION.getCommons("#mcr_rmer_sel", contextPath + "/common/getBid?type=R", {},
    // 		"id", "name");

});

/**
 * 表格初始化
 *
 * @returns {*|jQuery}
 */
function initRmerTable() {
    var option = $.extend(PAX_OBJECT.DataTable.option, {
        "sAjaxSource" : contextPath + "/rMer/list?search_status=2&search_mcr=" + window.mcr,
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
                        + oData.mid + "'>");

                }
            }, {
                "mDataProp" : "mcrName",
                "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }
            }, {
                "mDataProp" : "mid",
                "sClass" : "center","fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).text(_T(sData));
                }
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
            var totalpage = Math.ceil(totalRecords / displaylength);
            PAX_OBJECT.DataTable.Pageno("sel_rmer_dt1");
            PAX_OBJECT.DataTable.Pagego("sel_rmer_dt1", totalpage,
                tb_rmer_search);
        }
    });
    var table = $("#sel_rmer_dt1").dataTable(option);
    return table;
}

function tb_rmer_search(pageno) {

    var oSettings = list_rmer_oTable.fnSettings();
    oSettings._iDisplayStart = oSettings._iDisplayLength * (pageno - 1);

    oSettings["sAjaxSource"] = contextPath + "/rMer/list?search_mcr=" + window.mcr + "&search_mid="
        + $("#mid_rmer_sel").val() + "&search_name=" + $("#name_rmer_sel").val()
        + "&search_status=2";
    list_rmer_oTable.fnDraw(false);
}
