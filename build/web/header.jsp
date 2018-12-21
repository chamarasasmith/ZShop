<%@page import="DB.User"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="DB.Login"%>
<%@page import="Servlet1.Set_Logs"%>
<%@page import="Servlet1.Check_Access"%>
<%@page import="org.hibernate.Session"%>

<%
    Session hader_s = NewHibernateUtil.getSessionFactory().openSession();
    HttpSession hs = request.getSession();

    Login lo = null;

    Object login = hs.getAttribute("login");
    Object u = hs.getAttribute("userid");
    if (u != null) {
        Criteria c3 = hader_s.createCriteria(User.class);
        c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
        User u1 = (User) c3.uniqueResult();

        Criteria c4 = hader_s.createCriteria(Login.class);
        c4.add(Restrictions.eq("user", u1));
        lo = (Login) c4.uniqueResult();
    } else {
        lo = null;
    }

%>

<style>

    .ml:hover{
        font-weight: bolder;
    }

</style>

<nav class="navbar navbar-default navbar-inverse navbar-fixed-top" style="font-weight: bold">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" onclick="setHome()" style="cursor: pointer">ZShopping</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a onclick="setHome()" style="cursor: pointer">Home<span class="sr-only">(current)</span></a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">                            
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" onclick="ViewCart0()">Cart <span class="glyphicon glyphicon-shopping-cart"></span><span class="caret"></span></a>
                    <ul class="dropdown-menu" style="width: 470px; background-color: black; overflow: auto; max-height: 470px; overflow-x: hidden">
                        <li id="cartview1">

                        </li>
                    </ul>
                </li>


                <%                            boolean b1 = Check_Access.Check1(lo, "View");

                    if (!b1) {
                %>
                <li class="dropdown">                            
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sign In<span class="caret"></span></a>
                    <ul class="dropdown-menu" style="width: 300px; background-color: black;">
                        <li class="text-center"><span><img id="vimg2" src="img/dprofile.png" class="img-circle" style="margin: auto" width="100" height="100"></span><br></li>
                        <li>
                            <br>
                            <form id="sinform1">
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="un2" type="text" class="form-control" name="un2" placeholder="Username">
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="pw2" type="password" class="form-control" name="pw2" placeholder="Password">
                                    </div>
                                    <br>
                                    <button type="button" class="btn btn-info btn-block" onclick="signIn()">Sign In</button>    
                                    <br>
                                    <div id="alert_sin" class="text-center"></div>
                                    <br>
                                </div>
                                <div class="col-sm-1"></div>
                            </form>
                        </li>
                    </ul>
                </li>




                <li class="dropdown">                            
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sign Up<span class="caret"></span></a>
                    <ul class="dropdown-menu" style="width: 300px; background-color: black;">
                        <li><span><img src="img/dprofile.png" id="vimg1" class="img-circle img-responsive" style="margin: auto; cursor: pointer" width="100" height="100" onclick="document.getElementById('suimg1').click()"></span><br></li>
                        <li>
                            <form id="supform1">
                                <div class="col-sm-1"></div>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <input type="file" name="suimg1" id="suimg1" style="display: none" onchange="setImg1()">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                        <input id="fn1" type="text" class="form-control" name="fn1" placeholder="First Name">
                                        <span class="input-group-addon"><i id="vv3" class="glyphicon glyphicon-remove"></i></span>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                        <input id="ln1" type="text" class="form-control" name="ln1" placeholder="Last Name">
                                        <span class="input-group-addon"><i id="vv4" class="glyphicon glyphicon-remove"></i></span>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input type="text" class="form-control" name="un1" id="un1" placeholder="Username">
                                        <span class="input-group-addon"><i id="vv1" class="glyphicon glyphicon-remove"></i></span>
                                    </div>
                                    <br><div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input  id="pw1" type="password" class="form-control" name="pw1" placeholder="Password">
                                        <span class="input-group-addon"><i id="vv5" class="glyphicon glyphicon-remove"></i></span>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                        <input type="email" class="form-control" name="email1" id="email1" placeholder="Email">
                                        <span class="input-group-addon"><i id="vv2" class="glyphicon glyphicon-remove"></i></span>
                                    </div>
                                    <br>
                                    <button id="supbtn1" type="button" class="btn btn-info btn-block" onclick="signUp()">Sign Up</button>    
                                    <br>
                                    <div id="alert_sup" class="text-center"></div>
                                    <br>
                                </div>
                                <div class="col-sm-1"></div>
                            </form>
                        </li>
                    </ul>
                </li>

                <%
                    }
                %>

                <%
                    boolean b2 = Check_Access.Check1(lo, "View");

                    if (b2) {
                %>
                <li class="dropdown">                            
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profile<span class="caret"></span></a>
                    <ul class="dropdown-menu"  style="width: 300px; background-color: black">

                        <%
                            if (lo != null) {
                                if (lo.getUser().getImg() == null) {
                        %>
                        <li><span><img src="img/dprofile.png" class="img-circle img-responsive" style="margin: auto" width="100" height="100"></span><br></li>
                                <%
                                } else {
                                %>
                        <li><span><img src="<%=lo.getUser().getImg()%>" class="img-circle img-responsive" style="margin: auto" width="100" height="100"></span><br></li>
                                <%
                                    }
                                } else {
                                %>
                        <li><span><img src="img/dprofile.png" class="img-circle img-responsive" style="margin: auto" width="100" height="100"></span><br></li>

                        <%
                            }
                        %>

                        <li role="separator" class="divider"></li>

                        <li class="list-group-item ml" style="cursor: pointer; background-color: transparent; border: none; color: white" onclick="ViewCart1()">
                            <span class="badge">View</span>
                            <span class="glyphicon glyphicon-flag"> </span>
                            Cart
                        </li>
                        <li class="list-group-item ml" style="cursor: pointer; background-color: transparent; border: none; color: white" onclick="ViewOrder()">
                            <span class="badge">View</span>
                            <span class="glyphicon glyphicon-briefcase"> </span>
                            Orders
                        </li>

                        <li role="separator" class="divider"></li>

                        <%
                            boolean b3 = Check_Access.Check1(lo, "Admin");

                            if (b3) {
                        %>
                        <li class="list-group-item ml" style="cursor: pointer; background-color: transparent; border: none; color: white" onclick="setAdmin()">
                            <span class="badge">Edit</span>
                            <span class="glyphicon glyphicon-wrench"></span>
                            Admin
                        </li>
                        <%
                            }
                        %>
                        <li role="separator" class="divider"></li>

                        <li class="list-group-item ml" style="cursor: pointer; background-color: transparent; border: none; color: white" onclick="signOut()">
                            <span class="badge">User</span>
                            <span class="glyphicon glyphicon-log-out"></span>
                            Sign Out
                        </li>

                    </ul>
                </li>

                <%
                    }
                %>

            </ul>

            <!--                    <form class="navbar-form navbar-right">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Search">
                                    </div>
                                    <button type="button" class="btn btn-info">Search</button>
                                </form>-->

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div style="height: 50px"></div>

<script>

    function ViewCart0() {
        $.ajax({
            url: "Web_Load_Cart",
            type: "POST",
            success: function (html) {
                $("#cartview1").html(html);
            }
        });
    }

    function ViewCart1() {
        $("#main1").load("VCartView");
        $("#main1").hide().appendTo("#main1").fadeIn();
        $("#micon1").slideUp();
        $("#slider1").slideUp();
    }
</script>

<%
    hader_s.close();
%>