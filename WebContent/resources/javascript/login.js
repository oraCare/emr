document.addEventListener('DOMContentLoaded', function() {
    var height = (window.innerHeight - 150) / 2;
    document.getElementById("loginbox").style.marginTop = height + "px";
    document.getElementById("login").onclick = function() {
        var user = document.getElementById("user").value;
        var pwd = document.getElementById("pwd").value;
        if(!user.trim() && !pwd.trim()){
            alert("Please Enter Username or Password");
        }else{
            var json = new Object();
            json.username = user;
            json.password = pwd;
            sendAJAXRequest("GET", "requestid=1&requesttype=2&json="+JSON.stringify(json), "bingo");
        }
    };
});

function bingo(data) {
}