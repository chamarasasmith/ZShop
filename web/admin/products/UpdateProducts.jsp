<%@page import="org.json.JSONObject"%>
<%@page import="DB.Products"%>
<%@page import="DB.Category"%>
<%@page import="java.util.List"%>
<%@page import="DB.Brand"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>

<%
    Session s2 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs2 = request.getSession();

    Object o1 = request.getAttribute("jp");

    List<Brand> b = s2.createCriteria(Brand.class).list();
    List<Category> c = s2.createCriteria(Category.class).list();
    List<Products> p1 = s2.createCriteria(Products.class).list();
%>
<form id="pform1">
    <br>
    <br>
    <br>
    <div class="row">
        <div class="col-sm-3">

        </div>
        <div class="col-sm-6">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <input class="form-control" placeholder="Search" id="pu_text1">
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-success btn-block" onclick="loadProductList()">Filter</button>
                </div>
                <br>
                <br>
            </div>
            <div class="col-sm-12">
                <select class="form-control" onchange="setProduct_edit()" id="pp_1">
                    <option value="0">Select Product</option>
                    <%
                        for (Products pp1 : p1) {
                    %>
                    <option value="<%=pp1.getIdproducts()%>"><%=pp1.getPname()%></option>
                    <%
                        }
                    %>
                </select>    
            </div>
        </div>
        <div class="col-sm-3">

        </div>
    </div>


    <div id="upview1"></div>






    <div>
        <br>
        <div id="alert_product" class="text-center"></div>
        <br>
    </div>


</form>

<script>

    function loadProductList() {
        var t1= $("#pu_text1").val();
        $.ajax({
            url: "Load_Product_List",
            type: "POST",
            data: {pid: t1},
            success: function (html) {
                $('#pp_1').html(html);
            }
        });
    }

    function setProduct_edit() {
        var pid = $('#pp_1').val();
        $.ajax({
            url: "Web_Load_Update_Products",
            type: "POST",
            data: {pid: pid},
            success: function (html) {
                $('#upview1').html(html);
            }
        });

    }
</script>

<%
    s2.close();
%>