/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Brand;
import DB.Category;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

/**
 *
 * @author chama
 */
public class Web_Load_Products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Session s1 = NewHibernateUtil.getSessionFactory().openSession();
            try {

                String page1 = request.getParameter("p1");
                String min = request.getParameter("min");
                String limit1 = request.getParameter("limit1");
                String text1 = request.getParameter("text1");
                String cat = request.getParameter("cat");
                String brand = request.getParameter("brand");
                String p_min = request.getParameter("p_min");
                String p_max = request.getParameter("p_max");

                Criteria c1 = s1.createCriteria(Products.class);
                c1.add(Restrictions.eq("st", "1"));

                if ((!text1.equalsIgnoreCase("0"))) {
                    c1.add(Restrictions.like("pname", "%"+text1+"%"));
                }
                if ((!cat.equalsIgnoreCase("0"))) {
                    Criteria c4 = s1.createCriteria(Category.class);
                    c4.add(Restrictions.eq("idcategory", Integer.parseInt(cat)));
                    Category cat2 = (Category) c4.uniqueResult();
                    c1.add(Restrictions.eq("category", cat2));
                }
                if ((!brand.equalsIgnoreCase("0"))) {
                    Criteria c4 = s1.createCriteria(Brand.class);
                    c4.add(Restrictions.eq("idbrand", Integer.parseInt(brand)));
                    Brand b2 = (Brand) c4.uniqueResult();
                    c1.add(Restrictions.eq("brand", b2));
                }

                if ((!p_max.equalsIgnoreCase("0"))) {
                    c1.add(Restrictions.between("sprice", Double.parseDouble(p_min), Double.parseDouble(p_max)));
                }
                
                request.setAttribute("tcount", c1.list().size());
                
                if ((!limit1.equalsIgnoreCase("0")) && limit1 != null) {
                
                    c1.setFirstResult(Integer.parseInt(min));
                    c1.setMaxResults(Integer.parseInt(limit1));
                }

                List<Products> list = c1.list();
                JSONObject jsono = new JSONObject();

                if (list.size()==0) {
                    out.print("0");
                    return;
                }
                
                if (Integer.parseInt(min)<0) {
                    out.print("0");
                    return;
                }
                
                int i = 1;

                for (Products products : list) {
                    JSONObject jsono1 = new JSONObject();
                    jsono1.put("pid", products.getIdproducts());
                    jsono1.put("pname", products.getPname());
                    jsono1.put("sp", products.getSprice());
                    jsono1.put("bname", products.getBrand().getBname());
                    jsono1.put("cname", products.getCategory().getCname());
                    jsono1.put("img", products.getMimg());
                    jsono1.put("des", products.getDes());
                    jsono1.put("qty", products.getQty());
                    jsono1.put("min", products.getMin());
                    jsono.put(i + "", jsono1);
                    ++i;
                }

                request.setAttribute("j_pro", jsono);

                RequestDispatcher rd = request.getRequestDispatcher(page1);
                rd.forward(request, response);

//                out.print(jsono);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                out.print("0");
            } finally {
                s1.close();
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
