<%-- 
    Document   : topsales
    Created on : Aug 13, 2018, 11:04:02 AM
    Author     : chama
--%>

<%@page import="DB.Products"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.SQLQuery"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Invoice"%>
<%@page import="org.hibernate.Session"%>
<%@page import="Connection1.NewHibernateUtil"%>
<br>
<br>
<br>
<center>

    <h1 class="text-center">Top Sales Products</h1>

    <div class="col-sm-12">
        <%
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            SQLQuery c1 = s.createSQLQuery("SELECT products_idproducts FROM invoice_has_products GROUP BY products_idproducts ORDER BY SUM(qty) DESC LIMIT 4");
            List l = c1.list();

            for (Object pid : l) {

                Criteria c4 = s.createCriteria(Products.class);
                c4.add(Restrictions.eq("idproducts", Integer.parseInt(pid.toString())));
                Products p = (Products) c4.uniqueResult();


        %>
        <div class="col-sm-6 col-md-3">
            <div class="box" style="height: 250px; width: auto;min-width: 200px;">
                <!--<div class="ribbon"><span>0% Discount</span></div>-->
                <div class="text-center">
                    <br>
                    <div class="col-sm-12" style="height: 90px; vertical-align: auto;">
                        <img src="<%=p.getMimg()%>" alt="..." class="img-rounded"  style="height: 90%; width: 50% ;max-width: 100px; ">
                    </div>

                    <div class="col-sm-12">
                        <p style="color: black; font-size: small;cursor: pointer; font-weight: bold"><span class="glyphicon glyphicon-tag"></span> <%=p.getPname()%></p>
                        <p style="color: black; font-size: small;cursor: pointer"><span class="glyphicon glyphicon-ok"></span> Price : <%=p.getSprice()%></p>
                        <p style="color: green; font-size: small;cursor: pointer" onclick="viewMProduct1('<%=p.getIdproducts() %>')"><span class="glyphicon glyphicon-plus"></span> View Details</p>

                    </div>
                    
                </div>
            </div>
            <br>
        </div>
        <%

            }
            s.close();
        %>
    </div> 
</center>
