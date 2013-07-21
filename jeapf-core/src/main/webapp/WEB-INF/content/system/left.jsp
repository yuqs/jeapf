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
			<div class="subnav">
				<div class="subnav-title">
					<a href="#" class='toggle-subnav'><i class="icon-angle-down"></i><span>安全管理</span></a>
				</div>
				<ul class="subnav-menu">
					<li id="user-tab2"><a href="${ctx}/security/user"
						target="mainFrame" onclick="refreshMenuFocus(this);">用户管理</a></li>
					<li id="org-tab2"><a href="${ctx}/security/org"
						target="mainFrame" onclick="refreshMenuFocus(this);">部门管理</a></li>
					<li id="role-tab2"><a href="${ctx}/security/role"
						target="mainFrame" onclick="refreshMenuFocus(this);">角色管理</a></li>
					<li id="authority-tab2"><a href="${ctx}/security/authority"
						target="mainFrame" onclick="refreshMenuFocus(this);">权限管理</a></li>
					<li id="resource-tab2"><a href="${ctx}/security/resource"
						target="mainFrame" onclick="refreshMenuFocus(this);">资源管理</a></li>
					<li id="menu-tab2"><a href="${ctx}/security/menu"
						target="mainFrame" onclick="refreshMenuFocus(this);">菜单管理</a></li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
    function refreshMenu(arg) {
        discardPreviousFocus();
        arg.parentNode.setAttribute("class", "active");
        document.getElementById("main-title").innerHTML  = "<h4>首  页</h4>";
        var breadcrumbs = document.getElementById("breadcrumbs");
        breadcrumbs.innerHTML ="<li> <a href='javascript:void(0);'>首页</a> <i class='icon-angle-right'></i> </li>"
                + "<li> <a href='javascript:void(0);'>欢迎</a> </li>"
    }

    function refreshMenuFocus(arg) {
        discardPreviousFocus();
        var parentParentClass = arg.parentNode.parentNode.getAttribute('class');
        var idName = arg.parentNode.getAttribute('id');
        var idNameCut2 = idName;
        if(parentParentClass == "subnav-menu"){ var idNameCut2 = idName.substr(0, idName.length -1); }
        focusSelectedMenu(idNameCut2);
        //document.getElementById("main-title").innerHTML  = "<h4>" + arg.innerHTML + "</h4>";
        //setBreadcrumbs(idNameCut2, arg);
    }

    function setBreadcrumbs(idNameCut2, arg) {
        var breadcrumbs = document.getElementById("breadcrumbs");
        breadcrumbs.innerHTML = "";
        var dropdownNavi = document.getElementById(idNameCut2 + "2");
        var subTitle = dropdownNavi.parentNode.parentNode.getElementsByTagName("span").item(0).innerHTML;
        breadcrumbs.innerHTML ="<li> <a href='javascript:void(0);'>" + subTitle + "</a> <i class='icon-angle-right'></i> </li>" +
                "<li> <a href='javascript:void(0);'>" + arg.innerHTML + "</a> </li>";
    }

    function focusSelectedMenu(idName) {
        //var selectedMenu = document.getElementById(idName);
        var selectedMenu2  = document.getElementById(idName + "2");
        //if(selectedMenu != null && selectedMenu != "undefined") { selectedMenu.setAttribute("class", "active"); }
        //selectedMenu.parentNode.parentNode.setAttribute("class", "active");
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