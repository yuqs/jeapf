<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Jeapf</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
    <a href="<%=basePath%>/security/user">用户管理</a>
    <a href="<%=basePath%>/security/org">部门管理</a>
    <a href="<%=basePath%>/security/role">角色管理</a>
    <a href="<%=basePath%>/security/authority">权限管理</a>
    <a href="<%=basePath%>/security/resource">资源管理</a>
    <a href="<%=basePath%>/security/menu">菜单管理</a>
  </body>
</html>
