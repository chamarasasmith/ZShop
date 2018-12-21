<%-- 
    Document   : p_grid
    Created on : Jul 28, 2018, 11:38:27 AM
    Author     : chama
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<center>
    <div class="col-sm-12">
        <%
            JSONObject j1 = (JSONObject) request.getAttribute("j_pro");
            
            String s1="0";
            
            if (request.getAttribute("tcount")!=null) {
             s1=request.getAttribute("tcount").toString();       
                }
            

            for (int i = 1; i <= j1.length(); i++) {
                JSONObject j2 = (JSONObject) j1.get(i + "");

        %>
        <div class="col-sm-6 col-md-3">
            <div class="box" style="height: 250px; width: auto;min-width: 200px;">
                <!--<div class="ribbon"><span>0% Discount</span></div>-->
                <div class="text-center">
                    <br>
                    <div class="col-sm-12" style="height: 90px; vertical-align: auto;">
                        <input type="hidden" id="pcount1" value="<%=s1%>">
                        <img src="<%=j2.get("img")%>" alt="..." class="img-rounded"  style="height: 90%; width: 50% ;max-width: 100px; ">
                    </div>

                    <div class="col-sm-12">
                        <p style="color: black; font-size: small;cursor: pointer; font-weight: bold"><span class="glyphicon glyphicon-tag"></span> <%=j2.get("pname")%></p>
                        <p style="color: black; font-size: small;cursor: pointer"><span class="glyphicon glyphicon-ok"></span> Price : <%=j2.get("sp")%></p>
                        <p style="color: green; font-size: small;cursor: pointer" onclick="viewMProduct1('<%=j2.get("pid") %>')"><span class="glyphicon glyphicon-eye-open"></span> View Details</p>

                    </div>
                    <div class="col-sm-12">

                        <div class="input-group">
                            <input type="number" class="form-control input-sm" id="<%="protqty" + j2.get("pid")%>" placeholder="Qty" value="1">

                            <span class="input-group-btn">
                                <button class="btn btn-info btn-sm" type="button" onclick="addToCart('<%=j2.get("pid")%>')">Add Cart</button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </div>
        <%

            }
        %>
    </div>


    


</center>
