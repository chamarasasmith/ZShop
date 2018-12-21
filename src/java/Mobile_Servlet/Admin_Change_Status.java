/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Brand;
import DB.Category;
import DB.Login;
import DB.Products;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class Admin_Change_Status extends HttpServlet {

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
        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        Session s1 = NewHibernateUtil.getSessionFactory().openSession();
        try {
            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
            Object ob = in.readObject();

            if (ob instanceof HashMap) {
                HashMap hm = (HashMap) ob;

                String tb = hm.get("tb").toString();
                String st = hm.get("st").toString();
                int id1 = Integer.parseInt(hm.get("id1").toString());

                if (tb.equalsIgnoreCase("p")) {

                    Criteria c4 = s1.createCriteria(Products.class);
                    c4.add(Restrictions.eq("idproducts", id1));
                    Products p = (Products) c4.uniqueResult();

                    p.setSt(st);

                    s1.update(p);
                    s1.beginTransaction().commit();
                    out.writeObject("s");
                }

                if (tb.equalsIgnoreCase("a")) {
                    
                    Criteria c4 = s1.createCriteria(Login.class);
                    c4.add(Restrictions.eq("idlogin", id1));
                    Login p = (Login) c4.uniqueResult();

                    p.setSt(st);

                    s1.update(p);
                    s1.beginTransaction().commit();
                    out.writeObject("s");
                }

                if (tb.equalsIgnoreCase("b")) {
                    Criteria c4 = s1.createCriteria(Brand.class);
                    c4.add(Restrictions.eq("idbrand", id1));
                    Brand p = (Brand) c4.uniqueResult();

                    p.setSt(st);

                    s1.update(p);
                    s1.beginTransaction().commit();
                    out.writeObject("s");
                }
                if (tb.equalsIgnoreCase("c")) {
                    Criteria c4 = s1.createCriteria(Category.class);
                    c4.add(Restrictions.eq("idcategory", id1));
                    Category p = (Category) c4.uniqueResult();

                    p.setSt(st);

                    s1.update(p);
                    s1.beginTransaction().commit();
                    out.writeObject("s");
                }

            } else {
                out.writeObject("sw");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.writeObject("e");
        } finally {
            s1.close();
            out.close();
        }

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
