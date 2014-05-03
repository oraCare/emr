function getUserInfo(){
	sendAJAXRequest("GET", "requestid=1&requesttype=2&json={\"name\":\"nimesh\"}", "bingo");
}

function bingo(data) {
	document.getElementById("userName").innerHTML= data;
}