<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<title>Top</title>
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
<div id="navigation">
    <div class="container-fluid">
        <a href="#" id="brand">后台管理</a>
        <a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="右边栏">
            <i class="icon-reorder"></i>
        </a>
        <ul class='main-nav'>
<!--             <li class="active">
                <a href="layout.do?method=index" target="mainFrame">
                    <i class="icon-home"></i>
                    <span>首  页</span>
                </a>
            </li> -->
        </ul>
        <div class="user">
            <div class="dropdown asdf">
                <a href="#" class='dropdown-toggle' data-toggle="dropdown">欢迎您, <shiro:principal property="fullname"/>
                    <img src="${ctx }/styles/images/user.png" alt="">
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
