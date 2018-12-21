    
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Cart"%>
<%@page import="DB.Invoice"%>
<%@page import="DB.User"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%
    Session s = NewHibernateUtil.getSessionFactory().openSession();

    HttpSession hs = request.getSession();
    
        Object o= hs.getAttribute("userid");
        
if (o!=null) {
        
   Criteria c1= s.createCriteria(User.class);
   c1.add(Restrictions.eq("iduser", Integer.parseInt(o.toString())));
   User u=(User)c1.uniqueResult();
   Criteria c2= s.createCriteria(Cart.class);
   c2.add(Restrictions.eq("user", u));

   Cart cart=(Cart) c2.uniqueResult();

    if (cart!=null) {
 
%>
<div class="col-sm-12">
    <br>
    <br>
    <br>
    <br>
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <div class="row">
            <h4>Name</h4>
            <input type="text" class="form-control">
        </div>
        <div class="row">
            <h4>Account Number</h4>
            <input type="number" class="form-control">
        </div>
        <div class="row">
            <h4>Expire Date</h4>
            <input type="month" class="form-control">
        </div>
        <div class="row">
            <h4>CVV</h4>
            <input type="number" class="form-control">
        </div>
        <div class="row">
            <h4>Total Amount</h4>
            <input type="number" value="<%=cart.getTprice() %>" class="form-control" disabled>
        </div>
        <div class="row">
            <h4>Payment Method</h4>
            <div class="col-sm-4 text-center">
                <img src="img/icon/visa.png" style="width: 50%"><br>
                <input type="radio" name="r1"> Visa
            </div>
            <div class="col-sm-4 text-center">
                <img src="img/icon/mastercard.png" style="width: 50%"><br>
                <input type="radio" name="r1"> Master
            </div>
            <div class="col-sm-4 text-center">
                <img src="img/icon/paypal.png" style="width: 50%"><br>
                <input type="radio" name="r1"> Paypal
            </div>
        </div>
        <div class="row">
            <h4>Process</h4>
            <button class="btn btn-success btn-block" onclick="completeCheckout2()">Process</button>
            <br>
            <br>
            <br>
            <br>
        </div>

    </div>
    <div class="col-sm-4"></div>

    <script>

        function completeCheckout2() {
            $.ajax({
                url: "Web_Complete_Checkout",
                type: "POST",
                success: function (html) {

                    if (html == "2") {
                        setHome();
                    } else if (html == "1") {
                        alert("Login and Try Again...!");
                        setHome();
                    } else if (html == "0") {
                        alert("Something Wrong...!");
                        setHome();
                    } else {
                        alert(html);
                        setHome();
                    }
                }
            });
        }

    </script>

</div>
<%
}
}else{
%>
<script>
    alert("Login and Try Again..!!");
</script>
<%
}
%>