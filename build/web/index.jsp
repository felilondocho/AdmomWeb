<%-- 
    Document   : index
    Created on : Apr 20, 2015, 9:19:06 PM
    Author     : felipelondono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send</title>
    </head>
    <body>
        <h1>Welcome! please insert your name</h1>
        <form action="User.jsp" method="post">
            <input type="text" placeholder="Name" name="username"><br>
            <input type="submit" value="Enter">
        </form>
    </body>
</html>
