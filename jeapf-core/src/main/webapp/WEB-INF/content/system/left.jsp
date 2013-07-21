<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<title>Left</title>
<%@ include file="/common/meta.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${ctx}/styles/bootstrap/2.2.2/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/styles/css/index.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/styles/js/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script src="${ctx}/styles/bootstrap/2.2.2/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>

<body>
	<div class="container-fluid" id="content">
		<div id="left">
			<frame:menu/>
		</div>
	</div>

	<script type="text/javascript">
    function refreshMenuFocus(arg) {
        discardPreviousFocus();
        var parentParentClass = arg.parentNode.parentNode.getAttribute('class');
        var idName = arg.parentNode.getAttribute('id');
        //var idNameCut2 = idName;
        //if(parentParentClass == "subnav-menu"){ var idNameCut2 = idName.substr(0, idName.length -1); }
        focusSelectedMenu(idName);
    }

    function focusSelectedMenu(idName) {
        var selectedMenu2  = document.getElementById(idName);
        if(selectedMenu2 != null && selectedMenu2 != "undefined") { selectedMenu2.setAttribute("class", "active"); }
    }

    function discardPreviousFocus() {
        if (document.getElementsByClassName) {
            var activeNodes = document.getElementsByClassName("active");
            if(activeNodes.length > 0) {
                activeNodes.item(0).removeAttribute('class');
                discardPreviousFocus();
            }
        } else {
            var allLi = getClass("li", "active");
            for (var i = 0; i < allLi.length; i++) { allLi[i].removeAttribute('class'); }
        }
    }

    function getClass(tagname, className) {
        if (document.getElementsByClassName) { return getElementsByClassName(className); }
        else {
            var tagname = document.getElementsByTagName(tagname);
            var tagnameAll = [];
            for (var i = 0; i < tagname.length; i++) {
                if (tagname[i].className == className) { tagnameAll[tagnameAll.length] = tagname[i]; }
            }
            return tagnameAll;
        }
    }
</script>
</body>
</html>