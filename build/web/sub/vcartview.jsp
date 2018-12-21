<%-- 
    Document   : checkout1
    Created on : Jul 28, 2018, 5:28:15 PM
    Author     : chama
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="col-sm-12">
    <br>
    <br>
    <br>
    <br>
    <h1 class="text-center">Cart</h1>
    <br>
    <table class="table table-bordered text-center">
        <tr>
            <th class="text-center">Images</th>
            <th class="text-center">Product Name</th>
            <th class="text-center">Quantity</th>
            <th class="text-center">Unit Price</th>
            <th class="text-center">Total Price</th>
            <th class="text-center">Remove</th>
        </tr>

        <%
            JSONObject j1 = (JSONObject) request.getAttribute("j_check");

            double net = 0;

            for (int i = 1; i <= j1.length(); i++) {
                JSONObject j2 = (JSONObject) j1.get(i + "");

                double sp1 = Double.parseDouble(j2.getString("sp"));
                double qty1 = Double.parseDouble(j2.getString("qty"));
                double tp = sp1 * qty1;
                net += tp;
        %>

        <tr>
            <td><img src="<%=j2.getString("img1")%>" style="width:80px ; height:60px" ></td>
            <td><h3><%=j2.getString("pname")%></h3></td>
            <td><h3><%=j2.getString("qty")%></h3></td>
            <td><h3><%=j2.getString("sp")%></h3></td>
            <td><h3><%=tp + ""%></h3></td>
            <td><button type="button" class="btn btn-danger" onclick="removeFromCart4('<%=j2.get("pid")+"" %>')">Remove</button></td>
        </tr>

        <%
            }
        %>
        <tr>
            <td colspan="4"><h3 style="font-weight: bold">Total Price</h3></td>
            <td><h3 style="font-weight: bold"><%=net + ""%></h3></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5"></td>
            <!--<td><button type="button" class="btn btn-success btn-block" onclick="ViewCheckout()">Checkout</button></td>-->
            <td><button type="button" class="btn btn-success btn-block" onclick="ViewPayment1()">Checkout</button></td>
        </tr>
    </table>



</div>
<script>
    
    function ViewPayment1(){
        $("#main1").load("sub/payment.jsp");
        $("#main1").hide().appendTo("#main1").fadeIn();
        $("#micon1").slideUp();
        $("#slider1").slideUp();
    }
    
    function ViewCart2() {
        $("#main1").load("VCartView");
        $("#main1").hide().appendTo("#main1").fadeIn();
        $("#micon1").slideUp();
        $("#slider1").slideUp();
    }
    
    function removeFromCart4(id4) {
        $.ajax({
            url: "Web_Remove_From_Cart",
            type: "POST",
            data: {id1: id4},
            success: function (html) {
                alert(html);
                ViewCart2();
            }
        });
    }

    function ViewPayment() {
        $("#main1").load("Web_Checkout");
        $("#main1").hide().appendTo("#main1").fadeIn();
        $("#micon1").slideUp();
        $("#slider1").slideUp();
    }


</script>
