<%-- 
    Document   : Producerjsp
    Created on : Apr 26, 2015, 1:04:06 PM
    Author     : felipelondono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert Channel and Message to send:</h1>
        <form action="Produce" method="post">
            <input type="text" name="InputCanal" placeholder="Channel"><br>
            <input type="text" name="InputMessage" placeholder="Message"><br>
            <input type="submit" name="Enviar"><br>
        </form>
    </body>
</html>
