<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: Honsu
  Date: 08.02.2016
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Yahoo!!! from JSP</title>
</head>
<body>
<%
  Date date = new Date();
%>
My First JSP!!!
<div>Current date is <%=date%></div>
<form action="/login.do" method="post">
  Name: <input type="text" name="name"/> Password: <input type="password" name="password"/><input type="submit" value="Login">
  <p><font color="red">${errorMessage}</font></p>
</form>
</body>
</html>
