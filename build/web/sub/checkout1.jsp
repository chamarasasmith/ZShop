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
    <h1 class="text-center">Checkout</h1>
    <br>
    <table class="table table-bordered text-center">
        <tr>
            <th class="text-center">Images</th>
            <th class="text-center">Product Name</th>
            <th class="text-center">Quantity</th>
            <th class="text-center">Unit Price</th>
            <th class="text-center">Total Price</th>
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
        </tr>

        <%
            }
        %>
        <tr>
            <td colspan="4"><h3 style="font-weight: bold">Total Price</h3></td>
            <td><h3 style="font-weight: bold"><%=net + ""%></h3></td>
        </tr>
        <tr>
            <td colspan="4"></td>
            <td><button type="button" class="btn btn-success btn-block" onclick="completeCheckout()">Checkout</button></td>
        </tr>
    </table>



</div>
<script>

    function completeCheckout() {
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
