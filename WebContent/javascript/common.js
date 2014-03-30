APPLICATION_CONTEXT = "/plus/";
APPLICATION_CONTROLLER = "do.htm";
APPLICATION_DOMAIN = "";
APPLICATION_PAGE_OBJECT = "";
UserEditCheckBox = [];
CONFIRM_STATUS = false;
sidebarcontent = [{"name": "Home", "link": "#", "title": "Home", "icon": "glyphicon glyphicon-home"}, {"name": "Profile", "link": "#", "title": "Profile", "icon": "glyphicon glyphicon-user"}, {"name": "Messages", "link": "#", "title": "Messages", "icon": "glyphicon glyphicon-envelope"}, {"name": "Settings", "link": "#", "title": "Settings", "icon": "glyphicon glyphicon-cog"}, {"name": "Privacy", "link": "#", "title": "Privacy", "icon": "glyphicon glyphicon-lock"}];
var AJAX_PREFIX = "requesttype=AJAX&";
var AJAX_DATA;
var xmlObject;
function getAJAXObject() {
    var ajaxObject;
    try {
        ajaxObject = new XMLHttpRequest();
    } catch (e) {
        try {
            ajaxObject = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                ajaxObject = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
            }
        }
    }
    return ajaxObject;
}

function sendAJAXRequest(method, query, bingo) {
    xmlObject = getAJAXObject();
    var currentPath = window.location.toString();
    APPLICATION_DOMAIN = currentPath.substring(0, currentPath.indexOf("/", 8));
    xmlObject.onreadystatechange = function() {
        if (xmlObject.readyState === 4) {
            var data = xmlObject.responseText;
            //redirect(JSON.parse(data));
            console.log(data);
            var fn = window[bingo];
            //fn(data);
        }
    };
    if (method === "GET") {
        var url = encodeURI(APPLICATION_DOMAIN + APPLICATION_CONTEXT + APPLICATION_CONTROLLER + "?" + AJAX_PREFIX + query);
        xmlObject.open(method, url, true);
        xmlObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlObject.send();
    } else if (method === "POST") {
        xmlObject.open(method, APPLICATION_DOMAIN + APPLICATION_CONTEXT + APPLICATION_CONTROLLER, true);
        xmlObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlObject.send(AJAX_PREFIX + query);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    renderSideBar();
    document.getElementById("bingo").onclick = function() {
        showPopup({title: "Nishith Shah", message: "This is a testing for an architecture."});
    };
//    $('#myModal').on('show.bs.modal', function(e) {
////        $('#modelbody').html("Holla");
//        $('#modelbody').html("www.google.com");
//    });
//    $('#myModal').modal();
});

function renderSideBar() {
    var sidebar = document.getElementById("sidebar");
    sidebar.innerHTML = "";
    var key;
    for (key in sidebarcontent) {
        var li;
        var link;
        var span;
        li = document.createElement("li");
        link = document.createElement("a");
        if (sidebarcontent[key].icon) {
            span = document.createElement("span");
            span.className = sidebarcontent[key].icon;
            link.appendChild(span);
        }
        link.href = sidebarcontent[key].link;
        li.onclick = function() {
            clearSidebarSelection(sidebar);
            this.className = "active";
        };
        link.appendChild(document.createTextNode(" " + sidebarcontent[key].name));
        li.appendChild(link);
        sidebar.appendChild(li);
    }
}

function clearSidebarSelection(sidebar) {
    var nodes = sidebar.children;
    for (key in nodes) {
        nodes[key].className = "";
    }
}
/**
 * @author Nishith Shah
 * @argument {Object} obj This argument holds the necessary parameters need to display the dialog.
 * @example <b>title:</b> to set the title of dialog. <br><b>message:</b> to set the message of the dialog.<br><b>type:</b> type of the dialog.
 * @description This method is useful to display the message dialog.<br> <b>Dialog Types</b><ul><li>Error</li><li>Confirmation</li><li>Information</li><li>Warning</li></ul>
 * */
function showPopup(obj) {

    $('#myModal').on('show.bs.modal', function(e) {
//        $('#modelbody').html("Holla");
        $('#myModalLabel').html(obj.title);
        $('#modalbody').html(obj.message);
    });
    $('#myModal').modal();
    return true;
}