<%-- 
    Document   : ViewOrders
    Created on : Aug 11, 2018, 7:28:44 AM
    Author     : chama
--%>

<%@page import="java.util.List"%>
<%@page import="DB.Invoice"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Session s3 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs3 = request.getSession();

    Criteria c3 = s3.createCriteria(Invoice.class);
    c3.add(Restrictions.eq("st", "3"));

    List<Invoice> l3 = c3.list();
%>

<br>
        <h3>Delivered Orders</h3>
        <br>
        <br>
        <div class="col-sm-12">
            <table class="table table-responsive text-center">
                <tr class="text-center">
                    <th class="text-center">Invoice ID</th>
                    <th class="text-center">Date</th>
                    <th class="text-center">User</th>
                    <th class="text-center">Quantity</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Status</th>
                    <th class="text-center">Undo</th>
                </tr>
                <%
                    if (l3 != null) {

                        for (Invoice p3 : l3) {
                %>
                <tr>
                    <td><%=p3.getIdinvoice() %></td>
                    <td><%=p3.getIdate() %></td>
                    <td><%=p3.getUser().getFname() + " "+p3.getUser().getLname() %></td>
                    <td><%=p3.getTqty() %></td>
                    <td><%=p3.getTprice() %></td>
                    <td><% 
                        
                        String st=p3.getSt();
                        if (st.equalsIgnoreCase("1")) {
                                out.print("Pending");
                            }
                        if (st.equalsIgnoreCase("2")) {
                                out.print("Approved");
                            }
                        if (st.equalsIgnoreCase("3")) {
                                out.print("Delivered");
                            }
                        %></td>
                         <td><button class="btn btn-danger" onclick="changeStatus('5','admin/orders/ViewDelevered.jsp','2','o','<%=p3.getIdinvoice().toString() %>')">Undo</button></td>
               
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
