<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Products"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>

<%
    Session vps1 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = vps1.createCriteria(Products.class);
    c3.add(Restrictions.eq("st", "1"));

    List<Products> l3 = c3.list();
%>
<br>
<h3>All Active Products</h3>
<br>
<br>
<div>
    <div class="col-sm-4"></div>
    <div class="col-sm-4"></div>
    <div class="col-sm-4">

        <input type="text" class="form-control input-lg" placeholder="Search by Product Name" id="myInput2" onkeyup="sfun2()">
        <br>
    </div>
</div>
<br>

<div class="col-sm-12" style="overflow: auto; height: 500px">
    <table class="table table-responsive text-center" id="mtb2">
        <tr class="text-center">
            <th class="text-center">Product Name</th>
            <th class="text-center">Cost Price</th>
            <th class="text-center">Sale Price</th>
            <th class="text-center">Quantity</th>
            <th class="text-center">Block</th>
        </tr>
        <%
            if (l3 != null) {

                for (Products p3 : l3) {
        %>
        <tr>
            <td><%=p3.getPname()%></td>
            <td><%=p3.getCprice()%></td>
            <td><%=p3.getSprice()%></td>
            <td><%=p3.getQty()%></td>
            <td><button type="button" class="btn btn-danger" onclick="changeStatus('1', 'admin/products/ViewProducts.jsp', '0', 'p', '<%=p3.getIdproducts().toString()%>')">Block</button></td>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>
<script>
    function sfun2() {
        // Declare variables 
        var input, filter, table, tr, td1, td2, i;
        input = document.getElementById("myInput2");
        filter = input.value.toUpperCase();
        table = document.getElementById("mtb2");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td1 = tr[i].getElementsByTagName("td")[0];

            if (td1) {
                if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {

                    tr[i].style.display = "none";

                }
            }
        }
    }
</script>

<%
    vps1.close();
%>