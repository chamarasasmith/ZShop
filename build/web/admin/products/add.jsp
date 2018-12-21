<%@page import="DB.Category"%>
<%@page import="java.util.List"%>
<%@page import="DB.Brand"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>

<%
    Session s2 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs2 = request.getSession();

    List<Brand> b = s2.createCriteria(Brand.class).list();
    List<Category> c = s2.createCriteria(Category.class).list();
%>
<form id="pform1">
    <div class="row">
        <br>
        <img src="img/icon/product1.png" id="vproimg1" name="vproimg1" style="width: 200px;height: 200px" onclick="document.getElementById('proimg1').click()">
        <input type="file" id="proimg1" name="proimg1" style="visibility: hidden"  onchange="setPImg1()">
    </div>

    <div class="row">
        <br>
        <br>
        <div class="col-sm-1"></div>
        <div class="col-sm-5">

            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pname1" name="pname1" placeholder="Product Name">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <select class="form-control" id="brname1" name="brname1">
                    <%
                        for (Brand b1 : b) {
                    %>
                    <option><%=b1.getIdbrand()%> : <%=b1.getBname()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="cp1" name="cp1" placeholder="Cost Price">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pdes1" name="pdes1" placeholder="Description">
            </div>
            <br>

        </div> 

        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <select class="form-control" id="pcat1" name="pcat1">
                    <%
                        for (Category c1 : c) {
                    %>
                    <option><%=c1.getIdcategory()%> : <%=c1.getCname()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="pqty1" name="pqty1" placeholder="Quantity">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="number" class="form-control" id="psp1" name="psp1" placeholder="Sale Price">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" class="form-control" id="pmin1" name="pmin1" placeholder="Minimum Count">
            </div>
            <br>
            <button type="button" class="btn btn-info btn-block" onclick="addProduct()">Add</button>
        </div>
        <div class="col-sm-1"></div>


    </div>
    <div>
        <br>
        <div id="alert_product" class="text-center"></div>
        <br>
    </div>


</form>
<script>
    function setPImg1() {
        $("#vproimg1").fadeIn("fast").attr('src', URL.createObjectURL(event.target.files[0]));
    }
</script>
<%
    s2.close();
%>