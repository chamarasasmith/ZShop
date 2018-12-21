/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Brand;
import DB.Category;
import DB.Invoice;
import DB.Login;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chama
 */
public class ChangeStatus extends HttpServlet {

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

            Session s = NewHibernateUtil.getSessionFactory().openSession();

            try {
                String st = request.getParameter("st");
                String num = request.getParameter("num");
                String id1 = request.getParameter("id1");
//                out.print(num.trim().equalsIgnoreCase("p"));
                if (num.trim().equalsIgnoreCase("p")) {
                    Criteria c1 = s.createCriteria(Products.class);
                    c1.add(Restrictions.eq("idproducts", Integer.parseInt(id1)));
                    Products p1 = (Products) c1.uniqueResult();
                    p1.setSt(st.trim());
                    s.update(p1);
                    s.beginTransaction().commit();
                    out.print("Success");

                }
                if (num.trim().equalsIgnoreCase("b")) {
                    Criteria c1 = s.createCriteria(Brand.class);
                    c1.add(Restrictions.eq("idbrand", Integer.parseInt(id1)));
                    Brand p1 = (Brand) c1.uniqueResult();
                    p1.setSt(st.trim());
                    s.update(p1);
                    s.beginTransaction().commit();
                    out.print("Success");
                }
                if (num.trim().equalsIgnoreCase("c")) {
                    Criteria c1 = s.createCriteria(Category.class);
                    c1.add(Restrictions.eq("idcategory", Integer.parseInt(id1)));
                    Category p1 = (Category) c1.uniqueResult();
                    p1.setSt(st.trim());
                    s.update(p1);
                    s.beginTransaction().commit();
                    out.print("Success");
                }
                if (num.trim().equalsIgnoreCase("u")) {
                    Criteria c1 = s.createCriteria(Login.class);
                    c1.add(Restrictions.eq("idlogin", Integer.parseInt(id1)));
                    Login p1 = (Login) c1.uniqueResult();
                    p1.setSt(st.trim());
                    s.update(p1);
                    s.beginTransaction().commit();
                    out.print("Success");
                }
                
                if (num.trim().equalsIgnoreCase("o")) {
                    Criteria c1 = s.createCriteria(Invoice.class);
                    c1.add(Restrictions.eq("idinvoice", Integer.parseInt(id1)));
                    Invoice p1 = (Invoice) c1.uniqueResult();
                    p1.setSt(st.trim());
                    s.update(p1);
                    s.beginTransaction().commit();
                    out.print("Success");
                }

            } catch (Exception e) {

            } finally {
                
                s.close();
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
