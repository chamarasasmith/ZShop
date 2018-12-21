/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Cart;
import DB.CartHasProducts;
import DB.Products;
import DB.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class Mobile_Remove_Cart_Item extends HttpServlet {

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
        Session s1 = NewHibernateUtil.getSessionFactory().openSession();
ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        try {

            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
            Object ob = in.readObject();

            HashMap hm = (HashMap) ob;
                
            String id = hm.get("pid").toString();

            HttpSession hs = request.getSession();

            Object u = hm.get("uid");

            if (u != null) {

                Criteria c3 = s1.createCriteria(User.class);
                c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
                User user1 = (User) c3.uniqueResult();

                Criteria c4 = s1.createCriteria(Products.class);
                c4.add(Restrictions.eq("idproducts", Integer.parseInt(id)));
                Products p1 = (Products) c4.uniqueResult();

                Criteria c1 = s1.createCriteria(Cart.class);

                c1.add(Restrictions.eq("user", user1));
                Cart cart1 = (Cart) c1.uniqueResult();

                if (cart1 != null) {

                    Criteria c2 = s1.createCriteria(CartHasProducts.class);

                    c2.add(Restrictions.eq("cart", cart1));
                    c2.add(Restrictions.eq("products", p1));

                    CartHasProducts chp2 = (CartHasProducts) c2.uniqueResult();

                    cart1.setTprice(cart1.getTprice() - chp2.getPrice());
                    cart1.setTqty(cart1.getTqty() - chp2.getQty());

                    s1.update(cart1);
                    s1.delete(chp2);
                    s1.beginTransaction().commit();
                    out.writeObject("s");
                } else {
                    out.writeObject("sw");
                }

            } else {

                //session cart
            }

        } catch (Exception e) {
            System.out.println(e);
            out.writeObject("e");
        } finally {
            s1.close();
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
