<%-- 
    Document   : ConsumerMenu
    Created on : Apr 27, 2015, 10:12:52 PM
    Author     : felipelondono
--%>

<%@page import="admom.DBConnect"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        <h1>These are the channels to which you have subscribed</h1>
        <h1>Please Select the one you want to receive messages from:</h1>
        <% 
            Cookie[] cookies = request.getCookies();
           Cookie cookie = null;
           for(int i=0;i<cookies.length;i++){            
               if(cookies[i].getName().toString().equals("username")){
                   cookie = cookies[i];
               }
           }
           ArrayList<String> list = new ArrayList();
           DBConnect db = new DBConnect();
           list = db.ListChannelsUser(list, cookie.getValue().toString());
           out.println("<form action='Consumejsp.jsp' method='post'>");
           for(int i=0;i<list.size();i++){
               String aux = list.get(i);
               if(!(aux.equals("id")) && !(aux.equals("Username"))){
                   out.println("<input type='submit' value='"+aux+"' name='"+aux+"'>");
               }
           }
           out.println("</form>");
        %>
        <br>
        <form action="User.jsp">
            <input type='submit' value='GO BACK'>
        </form>
    </body>
</html>
