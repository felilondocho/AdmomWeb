<%-- 
    Document   : ChannelMenu
    Created on : Apr 27, 2015, 10:31:44 PM
    Author     : felipelondono
--%>

<%@page import="admom.DBConnect"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Channel Menu</title>
    </head>
    <body>
        <h1>Here are the available channels, click on which you want to subscribe</h1>
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
           list = db.ListChannels(list);
           out.println("<form action='ChannelServlet' method='post'>");
           for(int i=0;i<list.size();i++){
               String aux = list.get(i);
               if(!(aux.equals("id")) && !(aux.equals("Username"))){
                   out.println("<input type='submit' value='"+aux+"' name='"+aux+"'>");
               }
           }
           out.println("</form>");
        %>
        <br>
        <h1>Or you can create a channel</h1>
        <form action="CreateChannelServlet" method="post">
            <input type="text" name="channelinput" placeholder="Channel name">
            <input type='submit' value='Create Channel' name='create'>
        </form>
        <br>
        <form action="User.jsp">
            <input type='submit' value='GO BACK'>
        </form>
    </body>
</html>
