<%-- 
    Document   : User
    Created on : Apr 26, 2015, 4:28:54 PM
    Author     : felipelondono
--%>


<%@page import="admom.DBConnect"%>
<%
    Cookie[] cookies = request.getCookies();
    Cookie username = null;
    boolean existe = false;
    boolean existeconexion = false;
    Cookie conection = null;
    DBConnect db = new DBConnect();
    for(int i=0;i<cookies.length;i++){
        if(cookies[i].getName().toString().equals("username")){
            username = cookies[i];
            existe = true;
        }
        
    }
    if(existe){
        if(request.getParameter("username") != null){
            username.setValue(request.getParameter("username"));
            username.setMaxAge(60*60*24);
            response.addCookie(username);
            db.CreateUser(username.getValue().toString());
        }
    }else{
        username = new Cookie("username",request.getParameter("username"));
        username.setMaxAge(60*60*24);
        response.addCookie(username);

        db.CreateUser(username.getValue().toString());
    }

   //String name = request.getParameter("username");
   //session.setAttribute("username", name);

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= username.getValue() %></title>
    </head>
    <body>
        <h1>Welcome, <%= username.getValue() %></h1>
        <a href="Producerjsp.jsp"><input type="button" value="Send a Message"></a>
        <a href="ConsumerMenu.jsp"><input type="button" value="View messages"></a>
        <a href="ChannelMenu.jsp"><input type="button" value="View Channels"></a>
    </body>
</html>
