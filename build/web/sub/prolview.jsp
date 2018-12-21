<%@page import="org.hibernate.Criteria"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Products"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s2 = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs2 = request.getSession();
%>


<center>
        <div class="col-sm-12">

            <%
                
                String f= request.getParameter("f");
                String item= request.getParameter("item");
                
                int if1=Integer.parseInt(f);
                int iitem=Integer.parseInt(item);
                
                Criteria c1 = s2.createCriteria(Products.class).setMaxResults(iitem).setFirstResult(if1);
                c1.add(Restrictions.eq("st", "1"));
                List<Products> p = c1.list();

                for (Products pr : p) {

            %>
            
            <div class="lbox">
                <div class="ribbon"><span>Special Offer</span></div>
                <div class="text-center">
                    <div class="col-sm-3"  style="height: 80px; vertical-align: auto"><img src="<%=pr.getMimg() %>" alt="..." class="img-circle" style="height: 100%; width: 100%"></div>
                    <div class="col-sm-3">
                        <p style="color: black; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-tag"></span> Product : <%=pr.getPname().toString().trim() %></p>
                        <p style="color: black; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-bookmark"></span> Category : <%=pr.getCategory().getCname().toString().trim() %></p>
                        <p style="color: black; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-briefcase"></span> Brand Name: <%=pr.getBrand().getBname().trim() %></p>
                    </div>
                    <div class="col-sm-3">
                        <p style="color: green; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-plus"></span> Add to Wishlist</p>
                        <p style="color: black; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-ok"></span> Available: <%=pr.getQty().toString().trim() %></p>
                        <p style="color: black; font-weight: bold; cursor: pointer"><span class="glyphicon glyphicon-user"></span> Seller : ZShop</p>
                    </div>
                    <div class="col-sm-3"> 
                        <div class="input-group">
                            <input type="number" class="form-control input-sm" placeholder="Qty">
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-sm" type="button">Add Cart</button>
                            </span>
                        </div>
                        <br>
                        <div class="input-group">
                            <input type="number" class="form-control input-sm" placeholder="Qty">
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-sm" type="button">Buy Now</button>
                            </span>
                        </div>

                    </div>

                </div>
            </div>
            <br>
        
            
            
            <%                }
            %>
            
        </div>    

    </center>
