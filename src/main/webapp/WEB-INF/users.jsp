<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/2
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>users</h1>


<c:if test="${empty user}">
    标签无效
</c:if>

<c:if test="${empty requestScope.users}">
    找到数据！
</c:if>
<c:if test="${empty requestScope.users3}">
    未能找到数据！
</c:if>

        <c:forEach items="${requestScope.users}" var="User">

             ${User.id}---${User.username}----${User.password}--${User.salt} <br>

        </c:forEach>
<hr>
   <%--<c:forEach items="${requestScope.users}" var="user">
       ${user.username}
   </c:forEach>--%>
</body>
</html>
