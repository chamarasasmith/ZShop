<%@page import="DB.Category"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Login"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>

<%
    Session s3 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = s3.createCriteria(Login.class);
    c3.add(Restrictions.eq("st", "1"));

    List<Login> l3 = c3.list();
%>

<br>
        <h3>All Active Users</h3>
        <br>
        <br>
        <div class="col-sm-12">
            <table class="table table-responsive text-center">
                <tr class="text-center">
                    <th class="text-center">First Name</th>
                    <th class="text-center">Last Name</th>
                    <th class="text-center">Join Date</th>
                    <th class="text-center">Username</th>
                    <th class="text-center">User Role</th>
                    <th class="text-center">Block</th>
                </tr>
                <%
                    if (l3 != null) {

                        for (Login p3 : l3) {
                %>
                <tr>
                    <td><%=p3.getUser().getFname() %></td>
                    <td><%=p3.getUser().getLname() %></td>
                    <td><%=p3.getUser().getJdate() %></td>
                    <td><%=p3.getUsername() %></td>
                    <td><%=p3.getRole().getRname() %></td>
                    <td><button class="btn btn-danger" onclick="changeStatus('4','admin/user/ViewUsers.jsp','0','u','<%=p3.getIdlogin().toString() %>')">Block</button></td>
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
