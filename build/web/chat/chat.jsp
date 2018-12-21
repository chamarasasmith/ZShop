<%-- 
    Document   : chat
    Created on : Apr 5, 2017, 11:07:06 AM
    Author     : chama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <br>
        <div class="container-fluid">
            <div class="col-sm-4"><jsp:include page="umenu.jsp"></jsp:include></div>
            <div class="col-sm-6"><jsp:include page="chatbox.jsp"></jsp:include></div>
            <div class="col-sm-2"><jsp:include page="online.jsp"></jsp:include></div>
            
        </div>
    </body>
</html>
