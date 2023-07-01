$(function (){
    var INDEX=0;
    $("#chat-submit").click(function (e) {
        e.preventDefault();
        var msg=$("#chat-input").val();
        if(msg.trim()==''){
            return false;
        }
    })
    $(document).delegate(".chat-btn","click",function (){
        var value=$(this).attr("chat-value");
        $("#chat-input").attr("disabled",false);
    })
    $("#chat-circle").click(function (){
        $("#chat-circle").toggle('scale');
        $(".chat-box").toggle('scale');
    })
    $(".chat-box-toggle").click(function () {
        $("#chat-circle").toggle('scale');
        $(".chat-box").toggle('scale');
    })
}
)