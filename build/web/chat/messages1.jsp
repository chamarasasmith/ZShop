<%-- 
    Document   : messages1
    Created on : Apr 5, 2017, 11:48:21 AM
    Author     : chama
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Order"%>
<%@page import="java.util.Collections"%>
<%@page import="DB.Chatdes"%>
<%@page import="java.util.Set"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Chat"%>
<%@page import="org.hibernate.Session"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="DB.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%

            Session s = NewHibernateUtil.getSessionFactory().openSession();
            HttpSession hs = request.getSession();

            Object u = hs.getAttribute("userid");

            if (u != null) {

                Criteria c3 = s.createCriteria(User.class);
                c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
                User user1 = (User) c3.uniqueResult();
                
                String to = request.getParameter("to");
                String cid = request.getParameter("cid");
                
                Criteria c4 = s.createCriteria(User.class);
                c4.add(Restrictions.eq("iduser", Integer.parseInt(to)));
                User u2 = (User) c4.uniqueResult();
                
                Criteria c5 = s.createCriteria(Chat.class);
                c5.add(Restrictions.eq("chatid", Integer.parseInt(cid)));
                Chat chat = (Chat) c5.uniqueResult();
                
//                Criteria c1 = s.createCriteria(Chat.class);

//                c1.add(Restrictions.or(Restrictions.eq("userByUserIduser", u), Restrictions.eq("userByUserIduser1", u)));
//                Chat chat = (Chat) c1.uniqueResult();
                if (chat != null) {
                    Criteria c2 = s.createCriteria(Chatdes.class);
                    c2.add(Restrictions.eq("chat", chat));
                    c2.addOrder(Order.asc("idchatdes"));

                    List<Chatdes> l = c2.list();


        %>


        <br>
        <br>
        <div>
            <input type="hidden" value="<%=l.size()%>" id="msgcount">

            <%                for (Chatdes chatdese : l) {

                    if (true) {


            %>

            <div class="col-sm-12" style="text-align: right">            
                <label style="background-color: black; padding: 20px; border-radius: 50px; color: white"></label><br>
            </div>

            <%            } else {


            %>

            <div class="col-sm-12" style="text-align: left">            
                <label style="background-color: black; padding: 20px; border-radius: 50px; color: white"></label><br>
            </div>

            <%                    }
                        }
                    }
                    s.close();
                }
            %>
        </div>
        <br>
        <br>
    </body>
</html>
