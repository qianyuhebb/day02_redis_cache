<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/1
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
  <h1>登录页面</h1>
    <form action="/login" method="post">
       username: <input type="text" name="username" ><br>
       password: <input type="text" name="password" ><br>
     <input type="submit" value="登录" >
    </form>
</body>
</html>
