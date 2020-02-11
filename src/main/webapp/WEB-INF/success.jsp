<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/2
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录成功</h1>
<%--已经登录了的--%>
欢迎您:
<shiro:user>
   <shiro:principal/>
</shiro:user>
<shiro:notAuthenticated>
    <a href="/user/login">请登录</a>
</shiro:notAuthenticated>
<shiro:guest>
    游客~~~
</shiro:guest>
</body>
</html>
