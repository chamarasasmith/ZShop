<%@page import="org.hibernate.Criteria"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Brand"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s3 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = s3.createCriteria(Brand.class);
    c3.add(Restrictions.eq("st", "0"));

    List<Brand> l3 = c3.list();
%>
        <br>
        <h3>Blocked Brand</h3>
        <br>
        <br>
        <div class="col-sm-12">
            <table class="table table-responsive text-center">
                <tr class="text-center">
                    <th class="text-center">Brand ID</th>
                    <th class="text-center">Brand Name</th>
                    <th class="text-center">Active</th>
                </tr>
                <%
                    if (l3 != null) {

                        for (Brand p3 : l3) {
                %>
                <tr>
                    <td><%=p3.getIdbrand() %></td>
                    <td><%=p3.getBname() %></td>
                    <td><button class="btn btn-success" onclick="changeStatus('2','admin/brand1/BlockedBrands.jsp','1','b','<%=p3.getIdbrand().toString() %>')">Active</button></td>
                </tr>
                <%
                        }
                    }
                %>

            </table>
        </div>

<%
    s3.close();
%>
