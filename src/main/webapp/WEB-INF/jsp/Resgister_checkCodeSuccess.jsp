<%@ page import="com.javaee6.cgret.model.Client" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/28 0028
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client client = (Client)request.getSession().getAttribute("clientInfo");
%>
<html>
<head>
    <title>账号激活成功</title>
</head>
<body>
    <h1  style="text-align: center; color: black; font-size: 35px">
         激活成功！您现在可以随意使用您的账号<%=client.getClientName()%>了！
    </h1>
</body>
</html>
