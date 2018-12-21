
<%@page import="org.hibernate.criterion.Projections"%>
<%@page import="DB.Products"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="DB.Category"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Brand"%>
<%@page import="Servlet1.Check_Access"%>
<%@page import="org.hibernate.Session"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="Servlet1.Set_Logs"%>

<%
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs = request.getSession();

    Set_Logs.Set(s, hs, "Opened Buy View");

    Criteria c1 = s.createCriteria(Brand.class);
    Criteria c2 = s.createCriteria(Category.class);
    Criteria c3 = s.createCriteria(Products.class);

    c1.add(Restrictions.eq("st", "1"));
    c2.add(Restrictions.eq("st", "1"));

    double max1 = (Double) c3.setProjection(Projections.max("sprice")).uniqueResult();
    double min1 = (Double) c3.setProjection(Projections.min("sprice")).uniqueResult();

    List<Brand> b = c1.list();
    List<Category> c = c2.list();

%>
<div style="height: 50px"></div>
<div class="container-fluid">
    <div class="col-sm-12"></div>
    <div class="col-sm-3">
        <form>
            <br>
            <br>
            <br>
            <br>

            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" aria-describedby="basic-addon1" id="bsearch1">
                <span id="pop1" class="input-group-addon glyphicon glyphicon-question-sign" data-container="body" data-toggle="popover" data-placement="top" data-content="Search Using Products Name"></span>
                <!--<span class="input-group-addon glyphicon glyphicon-question-sign" id="basic-addon1" style="cursor: pointer"></span>-->
                <span class="input-group-addon" id="basic-addon1" style="cursor: pointer;" onclick="searchItemView('sub/p_grid.jsp','c1')">Search</span>
            </div>

            <br>
            <select class="form-control" id="pv_cat">
                <option>0:Select Category</option>
                <%                    for (Category cat : c) {
                %>
                <option><%=cat.getIdcategory() + ":" + cat.getCname()%></option>
                <%
                    }
                %>
            </select>
            <br>
            <select class="form-control" id="pv_brand">
                <option>0:Select Brand</option>
                <%
                    for (Brand brand : b) {
                %>
                <option><%=brand.getIdbrand() + ":" + brand.getBname()%></option>
                <%
                    }
                %>
            </select>

            <br>
            <input id="r1" type="range" max="<%=max1%>" min="<%=min1%>" value="<%=min1%>" class="form-control" onchange="document.getElementById('smin1').value = document.getElementById('r1').value">
            <br>
            <div class="input-group">
                <span class="input-group-addon">Minimum ($)</span>
                <input type="number" class="form-control" value="<%=min1%>" id="smin1">
                <span class="input-group-addon">.00</span>
            </div>
            <br>

            <input id="r2" type="range" max="<%=max1%>" min="<%=min1%>" value="<%=max1%>" class="form-control" onchange="document.getElementById('smax1').value = document.getElementById('r2').value">
            <br>
            <div class="input-group">
                <span class="input-group-addon">Maximum ($)</span>
                <input type="number" class="form-control" value="<%=max1%>" id="smax1">
                <span class="input-group-addon">.00</span>
            </div>
            <br>
            <div class="text-center">
                <button type="button" class="btn btn-info btn-block" onclick="searchItemView('sub/p_grid.jsp','c2')">Search</button>
            </div>

        </form>

    </div>
    <div class="col-sm-9">
        <div class="col-sm-12">

<!--            <div class="btn-group pull-right" role="group" aria-label="...">
                <button class="btn btn-default" type="button" onclick="setAdvSortList()"><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span></button>
            </div>

            <div class="btn-group pull-right" role="group" aria-label="...">
                <button class="btn btn-default" type="button" onclick="setAdvSortGrid()"><span class="glyphicon glyphicon-th" aria-hidden="true"></span></button>
            </div>-->

        </div>
        <br>
        <br>
        <br>
        <div class="col-sm-12" id="adv_view">


            <%--<jsp:include page="sub/protview.jsp?f=0&item=4&t1=0"></jsp:include>--%>
            <%--<jsp:include page="sub/prolview.jsp?f=0&item=4"></jsp:include>--%>



        </div>
        <br>
        <div style="text-align: center">
            <input type="hidden" value="0" id="pnum">
            <button class="btn btn-primary" onclick="searchItemView('sub/p_grid.jsp','c3')">Previous</button>
            <button class="btn btn-primary" onclick="searchItemView('sub/p_grid.jsp','c4')" >Next</button>
        </div>
    </div>
</div>
            
            







<script>

    $('#pop1').focusout(function () {
        $('#pop1').popover('hide');
    });
    $('#pop1').click(function () {
        $('#pop1').popover('show');
    });
//
////    var a1 = document.getElementById('cdrop1').offsetWidth;
//    var a2 = document.getElementById('cdrop2').offsetWidth;
////    document.getElementById('ccon1').setAttribute("style", "overflow: scroll; max-height: 300px; overflow-x: hidden; width:" + a1 + "px");
//    document.getElementById('ccon2').setAttribute("style", "overflow: scroll; max-height: 300px; overflow-x: hidden; width:" + a2 + "px");


    function setItemView(p) {
        $.ajax({
            url: "Web_Load_Products",
            data: {page1: p, text1: "0", cat: "0", brand: "0", p_min: "0", p_max: "0", min: "0", limit1: "5"},
            type: "POST",
            success: function (html) {
                $("#adv_view").html(html);
            }
        });
    }


    function setBuyViewNext() {
        var s = $("#pnum").val();
        $("#pnum").val(parseInt(s) + 4);
        var s2 = $("#pnum").val();
        var p = "sub/protview.jsp?f=" + s2 + "&item=4";
        $("#adv_view").load(p);
        $("#adv_view").hide().appendTo("#adv_view").fadeIn();
    }

    function searchItemView(p, com) {

        var t1 = "0";
        var cat = "0";
        var brand = "0";
        var p_min = "0";
        var p_max = "0";
        var min = "0";
        var limit1 = "0";

       

            if (com == "c1") {
                t1 = $("#bsearch1").val().toString().replace(" ", "_");
            }
            if (com == "c2") {
                cat = $("#pv_cat").val().toString().split(":")[0];
                brand = $("#pv_brand").val().toString().split(":")[0];
                p_min = $("#smin1").val().toString();
                p_max = $("#smax1").val().toString();
                
            }
            if (com == "c3") {
                cat = $("#pv_cat").val().toString().split(":")[0];
                brand = $("#pv_brand").val().toString().split(":")[0];
                p_min = $("#smin1").val().toString();
                p_max = $("#smax1").val().toString();
                var s = $("#pnum").val();
                $("#pnum").val(parseInt(s) - 8);
                min = $("#pnum").val();
            }
            if (com == "c4") {
                cat = $("#pv_cat").val().toString().split(":")[0];
                brand = $("#pv_brand").val().toString().split(":")[0];
                p_min = $("#smin1").val().toString();
                p_max = $("#smax1").val().toString();
                var s = $("#pnum").val();
                $("#pnum").val(parseInt(s) + 8);
                min = $("#pnum").val();
            }
            

            $.ajax({
                url: "Web_Load_Products",
                data: {p1: p, text1: t1, cat: cat, brand: brand, p_min: p_min, p_max: p_max, min: min, limit1: "8"},
                type: "POST",
                success: function (html) {
                    if(html=="0"){
                        var s = $("#pnum").val("-8");
//                        searchItemView('sub/p_grid.jsp','c4');
                    }else{
                    $("#adv_view").html(html);
                }
                }
            });

      



    }


    searchItemView('sub/p_grid.jsp','c2');

</script>

<%    s.close();
%>