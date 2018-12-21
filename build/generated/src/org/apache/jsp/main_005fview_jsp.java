package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Projections;
import DB.Products;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import DB.Category;
import org.hibernate.Criteria;
import DB.Brand;
import Servlet1.Check_Access;
import org.hibernate.Session;
import Connection1.NewHibernateUtil;
import Servlet1.Set_Logs;

public final class main_005fview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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


      out.write("\n");
      out.write("<div style=\"height: 50px\"></div>\n");
      out.write("<div class=\"container-fluid\">\n");
      out.write("    <div class=\"col-sm-12\"></div>\n");
      out.write("    <div class=\"col-sm-3\">\n");
      out.write("        <form>\n");
      out.write("            <br>\n");
      out.write("            <br>\n");
      out.write("            <br>\n");
      out.write("            <br>\n");
      out.write("\n");
      out.write("            <div class=\"input-group\">\n");
      out.write("                <input type=\"text\" class=\"form-control\" placeholder=\"Search\" aria-describedby=\"basic-addon1\" id=\"bsearch1\">\n");
      out.write("                <span id=\"pop1\" class=\"input-group-addon glyphicon glyphicon-question-sign\" data-container=\"body\" data-toggle=\"popover\" data-placement=\"top\" data-content=\"Search Using Products Name\"></span>\n");
      out.write("                <!--<span class=\"input-group-addon glyphicon glyphicon-question-sign\" id=\"basic-addon1\" style=\"cursor: pointer\"></span>-->\n");
      out.write("                <span class=\"input-group-addon\" id=\"basic-addon1\" style=\"cursor: pointer;\" onclick=\"searchItemView('sub/p_grid.jsp', 'c1')\">Search</span>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <br>\n");
      out.write("            <select class=\"form-control\" id=\"pv_cat\">\n");
      out.write("                <option>0:Select Category</option>\n");
      out.write("                ");
                    for (Category cat : c) {
                
      out.write("\n");
      out.write("                <option>");
      out.print(cat.getIdcategory() + ":" + cat.getCname());
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </select>\n");
      out.write("            <br>\n");
      out.write("            <select class=\"form-control\" id=\"pv_brand\">\n");
      out.write("                <option>0:Select Brand</option>\n");
      out.write("                ");

                    for (Brand brand : b) {
                
      out.write("\n");
      out.write("                <option>");
      out.print(brand.getIdbrand() + ":" + brand.getBname());
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </select>\n");
      out.write("\n");
      out.write("            <br>\n");
      out.write("            <input id=\"r1\" type=\"range\" max=\"");
      out.print(max1);
      out.write("\" min=\"");
      out.print(min1);
      out.write("\" value=\"");
      out.print(min1);
      out.write("\" class=\"form-control\" onchange=\"document.getElementById('smin1').value = document.getElementById('r1').value\">\n");
      out.write("            <br>\n");
      out.write("            <div class=\"input-group\">\n");
      out.write("                <span class=\"input-group-addon\">Minimum ($)</span>\n");
      out.write("                <input type=\"number\" class=\"form-control\" value=\"");
      out.print(min1);
      out.write("\" id=\"smin1\">\n");
      out.write("                <span class=\"input-group-addon\">.00</span>\n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("\n");
      out.write("            <input id=\"r2\" type=\"range\" max=\"");
      out.print(max1);
      out.write("\" min=\"");
      out.print(min1);
      out.write("\" value=\"");
      out.print(max1);
      out.write("\" class=\"form-control\" onchange=\"document.getElementById('smax1').value = document.getElementById('r2').value\">\n");
      out.write("            <br>\n");
      out.write("            <div class=\"input-group\">\n");
      out.write("                <span class=\"input-group-addon\">Maximum ($)</span>\n");
      out.write("                <input type=\"number\" class=\"form-control\" value=\"");
      out.print(max1);
      out.write("\" id=\"smax1\">\n");
      out.write("                <span class=\"input-group-addon\">.00</span>\n");
      out.write("            </div>\n");
      out.write("            <br>\n");
      out.write("            <div class=\"text-center\">\n");
      out.write("                <button type=\"button\" class=\"btn btn-info btn-block\" onclick=\"searchItemView('sub/p_grid.jsp', 'c2')\">Search</button>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <div class=\"col-sm-9\">\n");
      out.write("        <div class=\"col-sm-12\">\n");
      out.write("\n");
      out.write("            <!--            <div class=\"btn-group pull-right\" role=\"group\" aria-label=\"...\">\n");
      out.write("                            <button class=\"btn btn-default\" type=\"button\" onclick=\"setAdvSortList()\"><span class=\"glyphicon glyphicon-menu-hamburger\" aria-hidden=\"true\"></span></button>\n");
      out.write("                        </div>\n");
      out.write("            \n");
      out.write("                        <div class=\"btn-group pull-right\" role=\"group\" aria-label=\"...\">\n");
      out.write("                            <button class=\"btn btn-default\" type=\"button\" onclick=\"setAdvSortGrid()\"><span class=\"glyphicon glyphicon-th\" aria-hidden=\"true\"></span></button>\n");
      out.write("                        </div>-->\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <br>\n");
      out.write("        <br>\n");
      out.write("        <br>\n");
      out.write("        <div class=\"col-sm-12\" id=\"adv_view\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
      out.write("\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <br>\n");
      out.write("        <div style=\"text-align: center\">\n");
      out.write("            <nav aria-label=\"Page navigation\">\n");
      out.write("                <ul class=\"pagination\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\" aria-label=\"Previous\">\n");
      out.write("                            <span aria-hidden=\"true\">&laquo;</span>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    <li><a href=\"#\">1</a></li>\n");
      out.write("                    <li><a href=\"#\">2</a></li>\n");
      out.write("                    <li><a href=\"#\">3</a></li>\n");
      out.write("                    <li><a href=\"#\">4</a></li>\n");
      out.write("                    <li><a href=\"#\">5</a></li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\" aria-label=\"Next\">\n");
      out.write("                            <span aria-hidden=\"true\">&raquo;</span>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\n");
      out.write("    $('#pop1').focusout(function () {\n");
      out.write("        $('#pop1').popover('hide');\n");
      out.write("    });\n");
      out.write("    $('#pop1').click(function () {\n");
      out.write("        $('#pop1').popover('show');\n");
      out.write("    });\n");
      out.write("//\n");
      out.write("////    var a1 = document.getElementById('cdrop1').offsetWidth;\n");
      out.write("//    var a2 = document.getElementById('cdrop2').offsetWidth;\n");
      out.write("////    document.getElementById('ccon1').setAttribute(\"style\", \"overflow: scroll; max-height: 300px; overflow-x: hidden; width:\" + a1 + \"px\");\n");
      out.write("//    document.getElementById('ccon2').setAttribute(\"style\", \"overflow: scroll; max-height: 300px; overflow-x: hidden; width:\" + a2 + \"px\");\n");
      out.write("\n");
      out.write("\n");
      out.write("    function setItemView(p) {\n");
      out.write("        $.ajax({\n");
      out.write("            url: \"Web_Load_Products\",\n");
      out.write("            data: {page1: p, text1: \"0\", cat: \"0\", brand: \"0\", p_min: \"0\", p_max: \"0\", min: \"0\", limit1: \"5\"},\n");
      out.write("            type: \"POST\",\n");
      out.write("            success: function (html) {\n");
      out.write("                $(\"#adv_view\").html(html);\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("    function setBuyViewNext() {\n");
      out.write("        var s = $(\"#pnum\").val();\n");
      out.write("        $(\"#pnum\").val(parseInt(s) + 4);\n");
      out.write("        var s2 = $(\"#pnum\").val();\n");
      out.write("        var p = \"sub/protview.jsp?f=\" + s2 + \"&item=4\";\n");
      out.write("        $(\"#adv_view\").load(p);\n");
      out.write("        $(\"#adv_view\").hide().appendTo(\"#adv_view\").fadeIn();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function searchItemView(p, com) {\n");
      out.write("\n");
      out.write("        var t1 = \"0\";\n");
      out.write("        var cat = \"0\";\n");
      out.write("        var brand = \"0\";\n");
      out.write("        var p_min = \"0\";\n");
      out.write("        var p_max = \"0\";\n");
      out.write("        var min = \"0\";\n");
      out.write("        var limit1 = \"0\";\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        if (com == \"c1\") {\n");
      out.write("            t1 = $(\"#bsearch1\").val().toString().replace(\" \", \"_\");\n");
      out.write("        }\n");
      out.write("        if (com == \"c2\") {\n");
      out.write("            cat = $(\"#pv_cat\").val().toString().split(\":\")[0];\n");
      out.write("            brand = $(\"#pv_brand\").val().toString().split(\":\")[0];\n");
      out.write("            p_min = $(\"#smin1\").val().toString();\n");
      out.write("            p_max = $(\"#smax1\").val().toString();\n");
      out.write("\n");
      out.write("        }\n");
      out.write("        if (com == \"c3\") {\n");
      out.write("            cat = $(\"#pv_cat\").val().toString().split(\":\")[0];\n");
      out.write("            brand = $(\"#pv_brand\").val().toString().split(\":\")[0];\n");
      out.write("            p_min = $(\"#smin1\").val().toString();\n");
      out.write("            p_max = $(\"#smax1\").val().toString();\n");
      out.write("            var s = $(\"#pnum\").val();\n");
      out.write("            $(\"#pnum\").val(parseInt(s) - 8);\n");
      out.write("            min = $(\"#pnum\").val();\n");
      out.write("        }\n");
      out.write("        if (com == \"c4\") {\n");
      out.write("            cat = $(\"#pv_cat\").val().toString().split(\":\")[0];\n");
      out.write("            brand = $(\"#pv_brand\").val().toString().split(\":\")[0];\n");
      out.write("            p_min = $(\"#smin1\").val().toString();\n");
      out.write("            p_max = $(\"#smax1\").val().toString();\n");
      out.write("            var s = $(\"#pnum\").val();\n");
      out.write("            $(\"#pnum\").val(parseInt(s) + 8);\n");
      out.write("            min = $(\"#pnum\").val();\n");
      out.write("        }\n");
      out.write("\n");
      out.write("\n");
      out.write("        $.ajax({\n");
      out.write("            url: \"Web_Load_Products\",\n");
      out.write("            data: {p1: p, text1: t1, cat: cat, brand: brand, p_min: p_min, p_max: p_max, min: min, limit1: \"8\"},\n");
      out.write("            type: \"POST\",\n");
      out.write("            success: function (html) {\n");
      out.write("                if (html == \"0\") {\n");
      out.write("                    var s = $(\"#pnum\").val(\"-8\");\n");
      out.write("                    searchItemView('sub/p_grid.jsp', 'c4');\n");
      out.write("                } else {\n");
      out.write("                    $(\"#adv_view\").html(html);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("    searchItemView('sub/p_grid.jsp', 'c2');\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
    s.close();

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
