$(function () {
    $("#login_submit").click(function () {
        if($("#password_input").val()){
            $("#pwdHide").val($.md5($("#password_input").val()));
        }else{
            $("#pwdHide").val("");
        }
        $("#loginForm").submit();
    });

    var param = PAX_FUNCTION.GetRequest();
    var error = PAX_FUNCTION.LoginError();
 
});