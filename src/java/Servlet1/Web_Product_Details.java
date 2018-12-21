/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

/**
 *
 * @author chama
 */
public class Web_Product_Details extends HttpServlet {

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

                String p1 = request.getParameter("pid");

                int pid = Integer.parseInt(p1);

                JSONObject obj = new JSONObject();

                Criteria c4 = s.createCriteria(Products.class);
                c4.add(Restrictions.eq("idproducts", pid));
                Products p = (Products) c4.uniqueResult();

                obj.put("pid", p.getIdproducts());
                obj.put("pname", p.getPname());
                obj.put("qty", p.getQty() + "");
                obj.put("cp", p.getCprice());
                obj.put("sp", p.getSprice());
                obj.put("des", p.getDes());
                obj.put("min", p.getMin());
                obj.put("cat", p.getCategory().getCname());
                obj.put("bname", p.getBrand().getBname());
                obj.put("img1", p.getMimg());

                out.print(obj);

            } catch (Exception e) {
                out.print("e");
                System.out.println(e);
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
