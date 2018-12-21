<%@page import="java.util.List"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="DB.Category"%>
<%@page import="DB.Brand"%>
<%@page import="org.hibernate.Session"%>

<%
    Session s3 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = s3.createCriteria(Category.class);
    c3.add(Restrictions.eq("st", "1"));

    List<Category> l3 = c3.list();
%>

<br>
        <h3>All Active Category</h3>
        <br>
        <br>
        <div class="col-sm-12">
            <table class="table table-responsive text-center">
                <tr class="text-center">
                    <th class="text-center">Category ID</th>
                    <th class="text-center">Category Name</th>
                    <th class="text-center">Block</th>
                </tr>
                <%
                    if (l3 != null) {

                        for (Category p3 : l3) {
                %>
                <tr>
                    <td><%=p3.getIdcategory() %></td>
                    <td><%=p3.getCname() %></td>
                    <td><button class="btn btn-danger" onclick="changeStatus('3','admin/cat/ViewCategory.jsp','0','c','<%=p3.getIdcategory().toString() %>')">Block</button></td>
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
