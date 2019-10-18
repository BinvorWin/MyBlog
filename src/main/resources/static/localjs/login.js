layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
    $("#login").click(function () {
        var account=$("#userName").val();
        var pwd=$("#password").val();
        $.ajax({
            type: "post",
            url: "/doLogin",
            datatype:"json",
            synchronized:true,
            data: {account: account, pwd: pwd},
            success: function (result) {
                console.log(result);
                var replyCode = $(result).find("code").text();
                /*if(replyCode=="0"){
                    window.location.href = 'admin/index';
                }*/
                if(replyCode=="1"){
                    alert("重新登陆");
                    layer.alert($(result).find("msg").text());
                }
        },
            error:function () {
                layer.alert("失败");
            }
        })
    })
})
