<%@page import="org.json.JSONObject"%>
<%@page import="DB.CartHasProducts"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Cart"%>
<%@page import="DB.Login"%>
<%@page import="DB.Products"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<div class="col-sm-12">

    <br>
    <div class="text-center">
        <div class="row">
            <div class="col-sm-6">
                <p id="cv_tqty" style="font-weight: bold; color: white">Total Qty : 15</p>        
                <p id="cv_tsp" style="font-weight: bold; color: white">Total Price : 1568898</p>
            </div>
            <div class="col-sm-6">
                <button class="btn btn-info" onclick="ViewPayment2()">Proceed to Checkout</button>
            </div>
        </div>
    </div>
    <br>
    <%
        JSONObject j1 = (JSONObject) request.getAttribute("j_cart");

        int tqty = 0;
        double tsp = 0;

        for (int i = 1; i <= j1.length(); i++) {
            JSONObject j2 = (JSONObject) j1.get(i + "");

            tqty += j2.getInt("qty");
            tsp += j2.getDouble("sp");

    %>

    <div class="panel panel-default">
        <div  style="text-align: right">
            <p style="margin-right: 13px;margin-top:5px ;font-size: large;cursor: pointer; font-weight: bold" onclick="removeFromCart3('<%=j2.getString("pid")%>')">x</p>
        </div>
        <div class="panel-body">
            <div class="col-sm-12">
                <div class="col-sm-4"  style="height: 50px; vertical-align: auto">
                    <img src="<%=j2.getString("img1")%>" alt="..." style="height: 100%; width: 80% ;"><br>
                    <p style="font-weight: bold"><%=j2.getString("pname")%></p>

                </div>
                <div class="col-sm-5" style="vertical-align: auto">

                    <div class="col-sm-12">
                        <input type="number" id="<%="cvqty1" + j2.getString("pid")%>" class="form-control input-sm text-center" value="1" placeholder="Qty">
                    </div>

                    <div class="col-sm-12">
                        <h6><%="Qty : " + j2.getInt("qty")%></h6>
                        <h6><%="Price : " + j2.getDouble("sp")%></h6>
                    </div>

                </div>
                <div class="col-sm-3">
                    <br>
                    <button type="button" class="btn btn-info btn-block btn-xs" onclick="addToCart3('<%=j2.getString("pid")%>')">Add</button>
                    <!--<button type="button" class="btn btn-info btn-block btn-xs">Remove</button>-->
                </div>
            </div>
        </div>
    </div>

    <%
        }

    %>
    <input type="hidden" id="cv_h_tqty" value="<%=tqty%>">
    <input type="hidden" id="cv_h_tsp" value="<%=tsp%>">
    <%

    %>

    <script>

        $('#cv_tqty').html("Quantity : " + $('#cv_h_tqty').val());
        $('#cv_tsp').html("Total Price : " + $('#cv_h_tsp').val());

        function ViewPayment2() {
            $("#main1").load("sub/payment.jsp");
            $("#main1").hide().appendTo("#main1").fadeIn();
            $("#micon1").slideUp();
            $("#slider1").slideUp();
        }

        function addToCart3(id3) {
            var qty3 = $("#cvqty1" + id3).val();
            $.ajax({
                url: "Add_Cart",
                type: "POST",
                data: {id1: id3, qty: qty3},
                success: function (html) {
                    alert(html);
                }
            });
        }

        function removeFromCart3(id4) {
            $.ajax({
                url: "Web_Remove_From_Cart",
                type: "POST",
                data: {id1: id4},
                success: function (html) {
                    if (html.toString().equals("s")) {
                        alert("Success..!!!");
                    } else {
                        alert("Unsuccess...!!!");
                    }

                }
            });
        }
    </script>


</div>
