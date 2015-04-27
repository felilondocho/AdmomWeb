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
        <!--
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
        -->
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
