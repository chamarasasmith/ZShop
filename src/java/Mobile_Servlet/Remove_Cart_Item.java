/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Cart;
import DB.CartHasProducts;
import DB.Login;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chama
 */
public class Remove_Cart_Item extends HttpServlet {

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

                String id1 = request.getParameter("id1");
                String qty = request.getParameter("qty");

                int id2 = Integer.parseInt(id1);
                int qty2 = Integer.parseInt(qty);

                Criteria c5 = s1.createCriteria(Login.class);
                c5.add(Restrictions.eq("idlogin", 3));
                Login l = (Login) c5.uniqueResult();

                if (l == null) {
                    out.print("0");
                } else {

                    Criteria c2 = s1.createCriteria(Cart.class);
                    c2.add(Restrictions.eq("user", l.getUser()));

                    Criteria c6 = s1.createCriteria(Products.class);
                    c6.add(Restrictions.eq("idproducts", id2));
                    Products p1 = (Products) c6.uniqueResult();
                    
                    Cart c = (Cart) c2.uniqueResult();

                    if (c == null) {
                        out.print("0");
                    } else {

                        Double tprice = c.getTprice();
                        Integer tqty = c.getTqty();

                        Criteria c4 = s1.createCriteria(CartHasProducts.class);
                        c4.add(Restrictions.eq("cart", c));
                        c4.add(Restrictions.eq("products", p1));

                        CartHasProducts chp2 = (CartHasProducts) c4.uniqueResult();

                        if (chp2 == null) {
                            out.print("0");
                        } else {

                            Integer qty1 = chp2.getQty();
                            if (qty1 > qty2) {

                                chp2.setQty(qty1 - qty2);

                                double d2 = p1.getSprice() * qty2;

                                chp2.setPrice(chp2.getPrice() - d2);

                                c.setTprice(c.getTprice() - d2);

                                c.setTqty(c.getTqty() - qty2);

                                s1.update(c);
                                s1.update(chp2);
                            } else {

                            }

                        }

                        s1.beginTransaction().commit();

                    }
                    out.print("1");
                }

            } catch (Exception e) {
                out.print("0");
                System.out.println(e.getMessage());
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
