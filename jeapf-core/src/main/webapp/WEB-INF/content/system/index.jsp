<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
    欢迎您！<shiro:principal/>，所在部门名称：<shiro:principal property="orgName"/>
    <a href="<%=basePath%>/security/user">用户管理</a>
    <a href="<%=basePath%>/security/org">部门管理</a>
    <a href="<%=basePath%>/security/role">角色管理</a>
    <a href="<%=basePath%>/security/authority">权限管理</a>
    <a href="<%=basePath%>/security/resource">资源管理</a>
    <a href="<%=basePath%>/security/menu">菜单管理</a>
    
    <a href="<%=basePath%>/logout">退出</a>
  </body>
</html>
