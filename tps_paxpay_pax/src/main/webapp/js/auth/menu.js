/**
 * Created by moon on 2017/5/5.
 */
$(function(){//查询
    $("#query-menu-btn").click(function(){
        var CurrentId = $("#search_menu_id").val();
        onLoadZTree(CurrentId);
    });
});

/**
 * 加载树形结构数据
 */
function onLoadZTree(siteId){//加载机构树
    var setting = {
        view: {
            addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
            removeHoverDom: removeHoverDom, //离开节点时的操作
        },
        data: {
            simpleData: {//简单数据模式
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onClick: nodeClick
        }
    };

    var zNodes =[];

    $.ajax({
        async:false,//是否异步
        cache:false,//是否使用缓存
        type:'POST',//请求方式：post
        dataType:'json',//数据传输格式：json
        url:contextPath + '/menu/listAll.do?',//请求的action路径
        data:{
            site_id:siteId,
        },
        error:function(){
            //请求失败处理函数
            PAX_OBJECT.Messenger.alert('error', data.msg);
        },
        success:function(data){
            zNodes =data.data;
            zNodes.sort(function(a,b){return a.orderno-b.orderno});
            $.each(zNodes,function (index,ele) {
                return ele.name=_T(ele.name);
            })

            $.fn.zTree.init($("#menuTree"), setting, zNodes);

            $("#menuTree a").removeAttr("href");
            // 默认点击根节点
						$("#menuTree_1_a").click();
        }
    });
}


function reloadMenuTree(data){//重新加载机构树
	if(data.success == "true") {
		var CurrentId = $("#search_menu_id").val();
		onLoadZTree(CurrentId);
		$("#menu-update-form").Validform().resetForm();
		$("#menuTree_1_a").click();	
	}
}

// 树节点点击事件
function nodeClick(event,treeId,treeNode,clickFlag){
    // 将节点名设置到右侧输入框
		$("#add_pid").val(treeNode.id);
    $("#menu_id").val(treeNode.id);
    $("#menu_name").val(_T(treeNode.name));
    $("#menu_url").val(treeNode.url);
    $("#menu_order").val(treeNode.orderno);
    //$("#menu-update-form").Validform().resetForm();
}

