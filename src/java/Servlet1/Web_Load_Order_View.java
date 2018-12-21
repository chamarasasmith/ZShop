/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Invoice;
import DB.InvoiceHasProducts;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
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
public class Web_Load_Order_View extends HttpServlet {

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
                String id = request.getParameter("oid");

                Criteria c4 = s1.createCriteria(Invoice.class);
                c4.add(Restrictions.eq("idinvoice", Integer.parseInt(id)));
                Invoice i1 = (Invoice) c4.uniqueResult();

                Set<InvoiceHasProducts> ihps = i1.getInvoiceHasProductses();

                out.println("<table  class='table table-bordered text-center' >");
                out.println("<tr>");
                out.println("<th class='text-center'>Product</th>");
                out.println("<th class='text-center'>Quantity</th>");
                out.println("<th class='text-center'>Unit Price</th>");
                out.println("<th class='text-center'>Price</th>");
                out.println("</tr>");
                for (InvoiceHasProducts ihp : ihps) {
                    out.println("<tr>");
                    out.println("<td>" + ihp.getProducts().getPname() + "</td>");
                    out.println("<td>" + ihp.getQty() + "</td>");
                    out.println("<td>" + ihp.getProducts().getSprice() + "</td>");
                    out.println("<td>" + ihp.getPrice() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (Exception e) {
                System.out.println(e);
            } finally {

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
