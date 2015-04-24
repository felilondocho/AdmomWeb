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
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script>
                     $(document).ready(
                        function() {  
                            var lsmensajes = [];
                            setInterval(function() {        
                            $.get('ConsumeServlet',function(responseText) { 
                                lsmensajes.push(responseText);
                                document.getElementById("show").innerHTML = lsmensajes;
                                //for(var i=0;i<lsmensajes.length;i++){
                                    //$('#show').text(lsmensajes.pop(i));
                                //}
                                //$('#show').text(responseText);         
                                });
                            },3000);
                        });
        </script>
        
        <title>Send</title>
    </head>
    <body>
        <h1>Insert channel and message to send</h1>
        
        <form action="Produce" method="post">
            <input type="text" name="InputCanal" placeholder="Channel"><br>
            <input type="text" name="InputMessage" placeholder="Message"><br>
            <input type="submit" name="Enviar"><br>
        </form>
        
        <form action="ConsumerTest" method="post">
            <input type="submit">
        </form>
        
        <div id="show" align="center"></div>

    </body>
</html>
