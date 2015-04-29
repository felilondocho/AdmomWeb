<%-- 
    Document   : Consumejsp
    Created on : Apr 26, 2015, 4:29:06 PM
    Author     : felipelondono
--%>

<%@page import="java.util.Enumeration"%>
<%
    Enumeration enumeration = request.getParameterNames();
    String channel = null;
    while (enumeration.hasMoreElements()) {
        String parameterName = (String) enumeration.nextElement();
            channel = request.getParameter(parameterName);
    } 
    Cookie[] cookies = request.getCookies();
    Cookie channelcookie = null;
    boolean existe = false;
    for(int i=0;i<cookies.length;i++){            
        if(cookies[i].getName().toString().equals("channelcookie")){
            channelcookie = cookies[i];
            existe = true;
        }
    }
    if(existe){
        channelcookie.setValue(channel);
        channelcookie.setMaxAge(60*60*24);
        response.addCookie(channelcookie);
    }else{
        channelcookie = new Cookie("channelcookie",channel);
        channelcookie.setMaxAge(60*60*24);
        response.addCookie(channelcookie);
    }
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script>
            $(document).ready(
                function() {  
                    var lsmensajes = [];
                    var i = 0;
                    var aux;
                    setInterval(function() {        
                        $.get('ConsumeServlet',function(responseText) {
                            aux = responseText;
                            if(aux != lsmensajes[lsmensajes.length - 1]){
                                lsmensajes.push(aux);
                            }
                            document.getElementById("show").innerHTML = lsmensajes;      
                        });
                    },3000);
                 });
        </script>
        <title>Messages</title>
    </head>
    <body>
        <h1>Messages received: </h1><br>
        <div id="show" align="center"></div>
    </body>
</html>
