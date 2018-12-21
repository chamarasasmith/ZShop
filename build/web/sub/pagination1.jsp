
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.Products"%>
<%@page import="Connection1.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%
Session sss1=NewHibernateUtil.getSessionFactory().openSession();
int ii=0;
%>
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li onclick="searchItemView2('sub/p_grid.jsp','c3','<%=ii%>')">
        <a aria-label="Previous" style="cursor: pointer">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <%
    Criteria ccc1= sss1.createCriteria(Products.class);
    ccc1.add(Restrictions.eq("st", "1"));
    List<Products> ppp=ccc1.list();

    int psize=0;
    if (request.getParameter("pc")!=null) {
           psize= (Integer.parseInt(request.getParameter("pc"))/8)+1;
        }else{
    psize=(ppp.size()/8)+1;
    }
    
    
    
    
    
    
    for (int iii = 1; iii <= psize; iii++) {
            
    %>
    <li><a style="cursor: pointer" onclick="searchItemView2('sub/p_grid.jsp','c3','<%=ii%>')"><%=iii%></a></li>
    <%
        ii+=8;
    }
    %>
    <li onclick="searchItemView2('sub/p_grid.jsp','c3','<%=ii-8%>')">
      <a aria-label="Next"  style="cursor: pointer">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
    
    <script>
        
        function searchItemView2(p, com,min1) {

        var t1 = "0";
        var cat = "0";
        var brand = "0";
        var p_min = "0";
        var p_max = "0";
        var limit1 = "0";
        var min=min1;
       

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
                
            $("#pnum").val(min);
                
            }
            if (com == "c4") {
                cat = $("#pv_cat").val().toString().split(":")[0];
                brand = $("#pv_brand").val().toString().split(":")[0];
                p_min = $("#smin1").val().toString();
                p_max = $("#smax1").val().toString();
              
              $("#pnum").val(min);
            }
            

            $.ajax({
                url: "Web_Load_Products2",
                data: {p1: p, text1: t1, cat: cat, brand: brand, p_min: p_min, p_max: p_max, min: min, limit1: "8"},
                type: "POST",
                success: function (html) {
                    if(html=="0"){
                        var s = $("#pnum").val("-8");
                        searchItemView('sub/p_grid.jsp','c4');
                    }else{
                    $("#adv_view").html(html);
                    loadPagination1();
                }
                }
            });
        }
        
        
        function loadPagination1() {
            var t1=$("#pcount1").val();
    $("#pagiview1").load("sub/pagination1.jsp?pc="+t1);
    $("#pagiview1").hide().appendTo("#pagiview1").fadeIn();
    
        }
        
    </script>
    
</nav>
    <%
    sss1.close();
    %>
