<%-- 
    Document   : Consumejsp
    Created on : Apr 26, 2015, 4:29:06 PM
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
        <title>Page</title>
    </head>
    <body>
        <div id="show" align="center"></div>
    </body>
</html>
