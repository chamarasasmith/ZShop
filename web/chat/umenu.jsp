<%-- 
    Document   : umenu
    Created on : Apr 4, 2017, 9:41:57 PM
    Author     : chama
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Chat"%>
<%@page import="sun.security.krb5.internal.crypto.crc32"%>
<%@page import="org.hibernate.Session"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="DB.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>

            .list1{
                background-color: #007bfd; 
                height: 60px; 
                border-color: black; 
                border-width: 4px; 
                border-radius: 10px; 
                font-weight: bold; 
                color: yellow; 
                font-size: medium;
                display:block;
                width:100%;
            }
            .list1:hover{
                background-color: #0054fd; 
            }
            .list1:focus{
                background-color: #0054fd;
            }


        </style>
    </head>
    <body>
        <div class="list-group">

            <%
                Session s = NewHibernateUtil.getSessionFactory().openSession();
                HttpSession hs = request.getSession();

                Object u = hs.getAttribute("userid");

                if (u != null) {

                    Criteria c3 = s.createCriteria(User.class);
                    c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
                    User user1 = (User) c3.uniqueResult();

                    Criteria c1 = s.createCriteria(Chat.class);

                    c1.add(Restrictions.or(Restrictions.eq("userByUserIduser", u), Restrictions.eq("userByUserIduser1", u)));

                    List<Chat> l = c1.list();

                    for (Chat c : l) {

                        User u1 = c.getUserByUserIduser();
                        User u2 = c.getUserByUserIduser1();

                        if (u1.getIduser() == user1.getIduser()) {
            %>
            <button onclick="setMsgView(<%=c.getChatid()%>,<%=u2.getIduser()%>)" type="button" class="list1 btn-block"><img class="img-circle" src="<%=u2.getImg()%>" width="40" height="40"> <%=u2.getFname() + " " + u2.getLname()%> <span class="badge" style="background-color: black">14</span></button>

            <%
                }
                if (u2.getIduser() == user1.getIduser()) {
            %>
            <button onclick="setMsgView(<%=c.getChatid()%>,<%=u1.getIduser()%>)" type="button" class="list1 btn-block"><img class="img-circle" src="<%=u1.getImg()%>" width="40" height="40"> <%=u1.getFname() + " " + u1.getLname()%> <span class="badge" style="background-color: black">14</span></button>

            <%
                        }
                    }
                }
            %>

        </div>
    </body>
</html>
