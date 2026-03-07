$(document).ajaxSend(function(e, xhr, opt){
    var token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_header_token", token);
});

function getUserInfo(url){
    $.ajax({
        type: "get",
        url: url,
        success:function(result){
            if(result.code=="SUCCESS" && result.data!=null){
                var userInfo = result.data;
                $(".left .card h3").text(userInfo.userName);
                $(".left .card a").attr("href", userInfo.githubUrl);
            }else{
                alert(result.errMsg);
            }
        },
        error:function(error){
            if(error!=null && error.status==401){
                location.href = "blog_login.html";
            }
        }
    });
}
function logout(){
    //删除token
    localStorage.removeItem("user_token");
    location.href = "blog_login.html";
}