<%-- 
    Document   : User
    Created on : Apr 26, 2015, 4:28:54 PM
    Author     : felipelondono
--%>

<%
   String name = request.getParameter("username");
   session.setAttribute("username", name);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= session.getAttribute("username") %></title>
    </head>
    <body>
        <h1>Welcome, <%= session.getAttribute("username") %></h1>
        <a href="Producerjsp.jsp"><input type="button" value="Send a Message"></a>
        <a href="Consumejsp.jsp"><input type="button" value="View messages"></a>
    </body>
</html>
