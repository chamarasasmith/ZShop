<%-- 
    Document   : products
    Created on : Mar 17, 2017, 5:41:46 PM
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
        <h1 class="h1 text-center">Business Panel</h1>
        <br>
        <br>
        <br>
        <br>
        
       <center>
            <div>
             <!-- Nav tabs -->
  <ul class="nav nav-tabs nav-justified" role="tablist">
    <li role="presentation" class="active"><a href="#addbusiness" role="tab" data-toggle="tab">Add Business</a></li>
    <li role="presentation"><a href="#mybusiness" role="tab" data-toggle="tab">My Business</a></li>
    <li role="presentation"><a href="#allbusiness" role="tab" data-toggle="tab">All Business</a></li>
    <li role="presentation"><a href="#blockedbusiness" role="tab" data-toggle="tab">Blocked Business</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="addbusiness">
        <jsp:include page="add.jsp"></jsp:include>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="mybusiness">gfhgaaaaaaaaa</div>
    <div role="tabpanel" class="tab-pane fade" id="allbusiness">bbbbbbb</div>
    <div role="tabpanel" class="tab-pane fade" id="blockedbusiness">cccccccccccccc</div>
  </div>
            </div>
    
        </center>
</body>
</html>
