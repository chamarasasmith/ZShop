<%-- 
    Document   : chatbox
    Created on : Mar 23, 2017, 11:39:36 AM
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
        <div class="text-center" style="background-color: black; padding : 5px; color: white; border-radius: 20px 20px 0px 0px">
            <h3 id="chath1">Chamara Sasmith</h3>
        </div>
      
        <div id="msgview1"  style="overflow-y:auto; max-height: 300px;">    
        <%--<jsp:include page="messages1.jsp"></jsp:include>--%>
        </div>
        

        <div>
            <input type="hidden" id="uid1">
            <input type="hidden" id="toid1">
            <label for="comment">Message:</label>
            <div class="input-group">             
                <textarea class="form-control" rows="2" style="resize:none;font-weight: bold" id="msg1"></textarea>     
                <span class="input-group-addon btn btn-danger" onclick="saveMsg()">Send</span>
            </div>  
            <br>
            <br>
        </div>
        <button class="btn btn-default" type="button" onclick="setMsg()">Refresh</button>
        <button class="btn btn-default" type="button" onclick="scrolToDown1()">New Messages</button>
            
    </body>
</html>
