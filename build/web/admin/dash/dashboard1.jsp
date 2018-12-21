
<%@page import="java.util.Date"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.criterion.Projections"%>
<%@page import="DB.Invoice"%>
<%@page import="DB.User"%>
<%@page import="DB.Products"%>
<%@page import="DB.Category"%>
<%@page import="DB.Brand"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs = request.getSession();

    Date d=new Date();
    
    Criteria c1 = s.createCriteria(Brand.class);
    Criteria c2 = s.createCriteria(Category.class);
    Criteria c3 = s.createCriteria(Products.class);
    Criteria c4 = s.createCriteria(User.class);
    Criteria c5 = s.createCriteria(Invoice.class);
    Criteria c6 = s.createCriteria(Invoice.class);
    Criteria c7 = s.createCriteria(Invoice.class);
    Criteria c8 = s.createCriteria(Invoice.class);
    
    c5.add(Restrictions.sqlRestriction("MONTH(CURDATE())=MONTH(idate)"));
    c6.add(Restrictions.sqlRestriction("MONTH(CURDATE())=MONTH(idate)"));
    c7.add(Restrictions.eq("st", "1"));
    c8.add(Restrictions.eq("st", "2"));
    
    int bc = c1.list().size();
    int cc = c2.list().size();
    int pc = c3.list().size();
    int uc = c4.list().size();
    c5.setProjection(Projections.sum("tprice"));
    c6.setProjection(Projections.sum("tqty"));
    
    int ii1=c7.list().size();
    int ii2=c8.list().size();
    String ms= c5.uniqueResult().toString();
    String ms2= c6.uniqueResult().toString();

%>
<div>
    <br>
    <br>
    <h1 class="text-center">Dashboard</h1>
    <br>
    <br>
    <br>
</div>
<div>
    <div class="row">
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/users.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">User : <%=uc%></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/flyers.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Brand : <%=bc%></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/tasks.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Category : <%=cc%></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/shopping.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Products : <%=pc%></h6>
                </div>
            </div>
        </div>
    </div>



    <div class="row">
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/money-bag.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Month Sale : <%=ms %></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/countdown.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Month Sold Count : <%=ms2 %></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/checklist.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Pending Orders : <%=ii1 %></h6>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="img/dash_icons/stamp.png" alt="..." style="width: 50%; height: 50%">
                <div class="caption">
                    <h6 class="text-center">Approved Orders : <%=ii2 %></h6>
                </div>
            </div>
        </div>
    </div>



</div>

