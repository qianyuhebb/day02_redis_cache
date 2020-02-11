<%@page pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>

<shiro:hasPermission name="user:query">
    可以查询~~~~~~
</shiro:hasPermission>

<shiro:hasRole name="banzhang">
    是班长
</shiro:hasRole>
<shiro:lacksRole name="banzhang">
    不是班长
</shiro:lacksRole>
</body>
</html>
