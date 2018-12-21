<%@page import="java.util.List"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Category"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s3 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = s3.createCriteria(Category.class);
    c3.add(Restrictions.eq("st", "0"));

    List<Category> l3 = c3.list();
%>

<br>
        <h3>Blocked Category</h3>
        <br>
        <br>
        <div class="col-sm-12">
            <table class="table table-responsive text-center">
                <tr class="text-center">
                    <th class="text-center">Category ID</th>
                    <th class="text-center">Category Name</th>
                    <th class="text-center">Active</th>
                </tr>
                <%
                    if (l3 != null) {

                        for (Category p3 : l3) {
                %>
                <tr>
                    <td><%=p3.getIdcategory() %></td>
                    <td><%=p3.getCname() %></td>
                    <td><button class="btn btn-success" onclick="changeStatus('3','admin/cat/BlockedCategory.jsp','1','c','<%=p3.getIdcategory().toString() %>')">Active</button></td>
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
