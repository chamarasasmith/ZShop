<%-- 
    Document   : index
    Created on : Mar 17, 2017, 4:04:17 PM
    Author     : chama
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="#">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/ribbon.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.1.1.js" type="text/javascript"></script>
        <script src="js/navi_js.js" type="text/javascript"></script>
        <script src="js/sign.js" type="text/javascript"></script>
        <script src="js/chat.js" type="text/javascript"></script>
        <script src="js/main1.js" type="text/javascript"></script>
        <script src="js/cart_js.js" type="text/javascript"></script>

    </head>
    <body id="mbody1">
        <div id="header1">
            <%@ include file="header.jsp"%> 
        </div>
        <div id="slider1">
            <%@ include file="slider.jsp"%>
        </div>

        <div id="micon1">
            <!--<div class="container">-->
            <%--<jsp:include page="advanced_search.jsp"></jsp:include>--%>
            <jsp:include page="main_view.jsp"></jsp:include>

            <jsp:include page="sub/topsales.jsp"></jsp:include>



            <!--</div>-->
        </div>

        <div id="main1">

        </div>

        <div id="modal_view1">

        </div>




        <div id="m_p1" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Products Details</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-5">
                                <img src="img/uploaded/20180811095716.jpg" style="width: 100%" id="mv_img1">
                                <h4 class="text-center" style="font-weight: bold" id="mv_pname1"></h4>
                            </div>
                            <div class="col-sm-5">
                                <br>
                                <br>
                                <h5 class="text-center" style="font-weight: bold" id="mv_cat1"></h5>
                                <h5 class="text-center" style="font-weight: bold" id="mv_brand1"></h5>
                                <h5 class="text-center" style="font-weight: bold" id="mv_qty1"></h5>
                                <h5 class="text-center" style="font-weight: bold" id="mv_sp1"></h5>
                                <div class="row">
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-4">
                                        <input type="hidden" id="mv_hpid1">
                                        <input type="number" class="form-control input-sm text-center" placeholder="Quantity" id="mv_gqty" value="1">
                                    </div>
                                    <div class="col-sm-4"></div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-12 text-center">
                                        <button class="btn btn-info btn-sm btn-block" data-dismiss="modal" onclick="addToCart2()">Add to Cart</button>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <br>
                        <div class="row">
                            <h5 class="text-center" style="font-weight: bold" id="mv_des1"></h5>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <script>

            var pid = "";
            var pname = "";
            var qty = "";
            var cp = "";
            var sp = "";
            var des = "";
            var min = "";
            var cat = "";
            var bname = "";
            var img1 = "";

            function viewMProduct1(pid1) {

                $.ajax({
                    url: 'Web_Product_Details',
                    type: "POST",
                    data: {pid: pid1},
                    success: function (html) {

                        var obj = JSON.parse(html);
                        pid = obj.pid;
                        pname = obj.pname;
                        qty = obj.qty;
                        cp = obj.cp;
                        sp = obj.sp;
                        des = obj.des;
                        cat = obj.cat;
                        bname = obj.bname;
                        img1 = obj.img1;
                        $('#m_p1').modal('show');
                    }
                });


            }

            $('#m_p1').on('show.bs.modal', function (e) {
                $('#mv_img1').attr("src", img1);
                $('#mv_hpid1').val(pid);
                $('#mv_pname1').html(pname);
                $('#mv_cat1').html("Category : " + cat);
                $('#mv_brand1').html("Brand : " + bname);
                $('#mv_qty1').html("Available Quantity : " + qty);
                $('#mv_sp1').html("Sale Price : " + sp);
                $('#mv_des1').html(des);
            });


            function addToCart2() {
                var qty2 = $("#mv_gqty").val();
                var id1 = $("#mv_hpid1").val();
                
                if(qty2===null || id1===null || qty2.toString()=="null" || id1.toString()=="null"){
                    addToCart2();
                }else{
                $.ajax({
                    url: "Add_Cart",
                    type: "POST",
                    data: {id1: id1, qty: qty2},
                    success: function (html) {
                        alert(html);
                    }
                });    
                }
                
                
            }

        </script>


    </body>

</html>



