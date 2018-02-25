$(document).ready(function () {
    var phone_email = $("#phone_email").val();
    var password = $("#password").val();
    var phone_num = $("#phone_num").val();
    var phone_num_verify = $("#phone_num_verify").val();
    var email_num = $("#email_num").val();
    var email_num_verify = $("#email_num_verify").val();
    var company_name = $("#company_name").val();
    var password_first = $("#password_first").val();
    var password_comfirm = $("#password_comfirm").val();
    var picture_verify = $("#picture_verify").val();
    var reg_email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var reg_num = /^1[3|4|5|7|8]\d{9}$/;
    var reg_password = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,20}$/;

    $("#register_submit").on("click", function () {
        phone_num = $("#phone_num").val();
        phone_num_verify = $("#phone_num_verify").val();
        email_num = $("#email_num").val();
        email_num_verify = $("#email_num_verify").val();
        company_name = $("#company_name").val();
        password_first = $("#password_first").val();
        password_comfirm = $("#password_comfirm").val();
        picture_verify = $("#picture_verify").val();
        if (reg_password.test(password_first) && password_first == password_comfirm) {
            if (reg_num.test(phone_num) || reg_email.text(email_num)) {
                $.ajax({
                    url:'productManager_reverseUpdate',// 跳转到 action
                    data:{
                        phone_num : phone_num,
                        phone_num_verify : phone_num_verify,
                        email_num : email_num,
                        email_num_verify:email_num_verify,
                        company_name : company_name,
                        password_first : password_first,
                        password_comfirm : password_comfirm,
                        picture_verify : picture_verify,
                    },
                    type:'post',
                    cache:false,
                    dataType:'json',
                    success:function(data) {
                        if(data.msg =="true" ){
                            alert($.i18n.prop('ph_merchant_add_tteighth'));//注册成功!
                            window.location.reload();
                        }else{
                            view(data.msg);
                        }
                    },
                    error : function() {
                        alert($.i18n.prop('main_system_wrong'));//异常
                    }
                });
            }
        }
    });
    $("#login_submit").on("click", function () {
        phone_email = $("#phone_email").val();
        password = $("#password").val();
        if ((phone_email==phone_num ||phone_email==email_num) && password==password_first) {
            $.ajax({
                url:'productManager_reverseUpdate',// 跳转到 action
                data:{
                    phone_email : phone_email,
                    password : password,
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data.msg =="true" ){
                        alert($.i18n.prop('ph_merchant_add_tteighth'));
                        window.location.reload();
                    }else{
                        view(data.msg);
                    }
                },
                error : function() {
                    alert($.i18n.prop('main_system_wrong'));
                }
            });
        }
    })

})
