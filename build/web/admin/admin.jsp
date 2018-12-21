<%@page import="DB.Login"%>
<%@page import="Servlet1.Set_Logs"%>
<%@page import="Servlet1.Check_Access"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>

<%
    Session ads=NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs=request.getSession();
   
    Login lo = null;

    Object login = hs.getAttribute("login");

    if (login instanceof Login) {
        Login l = (Login) login;
        lo = (Login) ads.load(Login.class, l.getIdlogin());
    } else {
        lo = null;
    }
    
   boolean b= Check_Access.Check1(lo, "Admin");
    
    if (!b) {        
        out.write("</br></br></br></br></br></br></br></br>");
        out.write("<center><h1>Not Access</h1><center>");
    }else{
    
    Set_Logs.Set(ads, hs, "Opened Admin View");
    
%>

        <div style="height: 50px"></div>
        <div class="col-sm-12">
            <div class="col-sm-3 col-xs-12">
                <jsp:include page="menu.jsp"></jsp:include>
                </div>
                <div class="col-sm-9 col-xs-12" id="adminview1">
                <jsp:include page="dash/dashboard1.jsp"></jsp:include>
            </div>
        </div>
<%
}
ads.close();
%>