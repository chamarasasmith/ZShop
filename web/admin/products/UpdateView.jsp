<%-- 
    Document   : UpdateView
    Created on : Aug 12, 2018, 10:11:08 AM
    Author     : chama
--%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.json.JSONObject"%>
<%@page import="DB.Products"%>
<%@page import="DB.Category"%>
<%@page import="java.util.List"%>
<%@page import="DB.Brand"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s2 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs2 = request.getSession();

    Object o1 = request.getAttribute("jp");

    List<Brand> b = s2.createCriteria(Brand.class).list();
    List<Category> c = s2.createCriteria(Category.class).list();
    List<Products> p1 = s2.createCriteria(Products.class).list();
        if (o1 != null) {
            JSONObject j1 = (JSONObject) o1;



    %>
    <form id="pform2">
    <div class="row">
        <br>
        <input type="hidden" value="<%=j1.getInt("pid") %>" id="uppid" name="uppid">
        <img src="<%=j1.getString("img1") %>" id="vproimg2" name="vproimg2" style="width: 200px;height: 200px" onclick="document.getElementById('proimg2').click()">
        <input type="file" id="proimg2" name="proimg2" style="visibility: hidden"  onchange="setPImg2()">
    </div>

    <div class="row">
        <br>
        <br>
        <div class="col-sm-1"></div>
        <div class="col-sm-5">

            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pname2" value="<%=j1.getString("pname") %>" name="pname2" placeholder="Product Name">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <select class="form-control" id="brname2" name="brname2">
                    <%                        for (Brand b1 : b) {
                    %>
                    <option <%
                        
if (b1.getIdbrand()==j1.getInt("bname")) {
        %>
        selected
                        <%
    }
                        
                        %> value="<%=b1.getIdbrand()%>"><%=b1.getBname()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="cp2" name="cp2" value="<%=j1.getDouble("cp") %>" placeholder="Cost Price">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pdes2" name="pdes2" value="<%=j1.getString("des") %>" placeholder="Description">
            </div>
            <br>

        </div> 

        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <select class="form-control" id="pcat2" name="pcat2">
                    <%
                        for (Category c1 : c) {
                    %>
                    <option <%
                        
if (c1.getIdcategory()==j1.getInt("cat")) {
        %>
        selected
                        <%
    }
                        
                        %>value="<%=c1.getIdcategory()%>"><%=c1.getCname()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="pqty2" name="pqty2"  value="<%=j1.getInt("qty") %>" placeholder="Quantity">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="psp2" name="psp2"  value="<%=j1.getDouble("sp") %>" placeholder="Sale Price">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pmin2" name="pmin2"  value="<%=j1.getInt("min") %>" placeholder="Minimum Count">
            </div>
            <br>
            <button type="button" class="btn btn-info btn-block" onclick="updateProduct1()">Update</button>
        </div>
        <div class="col-sm-1"></div>


    </div>
</form>
                
                <script>
                    
    function setPImg2() {
        $("#vproimg2").fadeIn("fast").attr('src', URL.createObjectURL(event.target.files[0]));
    }          
                    function updateProduct1() {
//    progressBar("alert_product");
    var data = new FormData(document.getElementById("pform2"));
    $.ajax({
        url: "Product_Update",
        type: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'html',
        success: function (html) {
//            $('#pform2').trigger("reset");
//            $("#alert_product").html(html);
//            $("#vproimg1").attr("src","img/p_icon2.png");
            alert(html);
        }
    });
}
                </script>
<%
        
        }
    %>
