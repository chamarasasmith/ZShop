/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Invoice;
import DB.Login;
import DB.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

/**
 *
 * @author chama
 */
public class Web_Order_View extends HttpServlet {

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

                HttpSession hs = request.getSession(true);

                Object u = hs.getAttribute("userid");

                if (u != null) {

                    Criteria c3 = s1.createCriteria(User.class);
                    c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
                    User user = (User) c3.uniqueResult();

                    Criteria c1 = s1.createCriteria(Invoice.class);

                    c1.add(Restrictions.eq("user", user));

                    List<Invoice> list = c1.list();

                    JSONObject jsono = new JSONObject();
                    int ii1 = 1;
                    for (Invoice invoice : list) {
                        JSONObject js1 = new JSONObject();
                        js1.put("iid", invoice.getIdinvoice() + "");
                        js1.put("tp", invoice.getTprice() + "");
                        js1.put("tqty", invoice.getTqty() + "");
                        js1.put("idate", invoice.getIdate() + "");
                        js1.put("st", invoice.getSt() + "");
                        jsono.put("" + ii1, js1);
                        ++ii1;
                    }

                    request.setAttribute("j_order", jsono);

                    RequestDispatcher rd = request.getRequestDispatcher("sub/orders1.jsp");
                    rd.forward(request, response);

                } else {
                    out.print("1");
                }

            } catch (Exception e) {
                System.out.println(e);
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
