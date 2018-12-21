<%-- 
    Document   : orders1
    Created on : Jul 29, 2018, 9:34:28 AM
    Author     : chama
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">
    <br>
    <br>
    <br>
    <br>
    <h1 class="text-center">Orders</h1>
    <br>
    <div class="col-sm-12">
        <div class="col-sm-4"></div>
        <div class="col-sm-4"></div>
        <div class="col-sm-4">

            <input type="text" class="form-control input-lg" placeholder="Search by ID or Date" id="myInput" onkeyup="sfun()">
            <br>
        </div>
    </div>
    <br>
    <br>
    <div style="overflow: auto; max-height: 500px; overflow-x: hidden" class="col-sm-12">
    <table class="table table-bordered text-center" id="mtb1" >
        <tr>
            <th class="text-center"><h3>Invoice ID</h3></th>
            <th class="text-center"><h3>Total Quentity</h3></th>
            <th class="text-center"><h3>Total Price</h3></th>
            <th class="text-center"><h3>Date</h3></th>
            <th class="text-center"><h3>Status</h3></th>
            <th class="text-center"><h3>View</h3></th>
        </tr>

        <%
            JSONObject j1 = (JSONObject) request.getAttribute("j_order");

            for (int i = 1; i <= j1.length(); i++) {
                JSONObject j2 = (JSONObject) j1.get(i + "");

        %>

        <tr>
            <td><h3><%=j2.getString("iid")%></h3></td>
            <td><h3><%=j2.getString("tqty")%></h3></td>
            <td><h3><%=j2.getString("tp")%></h3></td>
            <td><h3><%=j2.getString("idate")%></h3></td>
            <td><h3><%

                String st = j2.getString("st");

                if (st.toString().equalsIgnoreCase("1")) {
                    out.print("Pending");
                }
                if (st.toString().equalsIgnoreCase("2")) {
                    out.print("Approved");
                }
                if (st.toString().equalsIgnoreCase("3")) {
                    out.print("Delivered");
                }
                    %></h3></td>
            <td><button type="button" class="btn btn-success" onclick="viewMOrder1('<%=j2.getString("iid")%>')">View</button></td>
        </tr>

        <%
            }
        %>

    </table>
        
        
         <div id="m_o1" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Order Details</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row" id="m_o1_body">
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        
        
</div>
    <script>
        
        function viewMOrder1(oid1) {
                $.ajax({
                    url: 'Web_Load_Order_View',
                    type: "POST",
                    data: {oid: oid1},
                    success: function (html) {
                        $('#m_o1_body').html(html);
                        $('#m_o1').modal('show');
                    }
                });


            }
        
        
        
        function sfun() {
            // Declare variables 
            var input, filter, table, tr, td1, td2, i;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("mtb1");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td1 = tr[i].getElementsByTagName("td")[0];
                td2 = tr[i].getElementsByTagName("td")[3];
                if (td1) {
                    if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        if (td2) {
                            if (td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = "none";
                            }
                        }
                    }
                }
            }
        }
    </script>

</div>
