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
import DB.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
public class Add_to_Cart extends HttpServlet {

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

            Object o = in.readObject();

            if (o instanceof HashMap) {
                System.out.println("is hashmap");
                HashMap hm = (HashMap) o;

                String pid = hm.get("pid").toString();
                String uid = hm.get("uid").toString();
                String qty = hm.get("qty1").toString();

                System.out.println(uid);
                
                int pid2 = Integer.parseInt(pid);
                int uid2 = Integer.parseInt(uid);
                int qty2 = Integer.parseInt(qty);

                Criteria c3 = s1.createCriteria(User.class);
                c3.add(Restrictions.eq("iduser", uid2));
                User u = (User) c3.uniqueResult();

                Criteria c2 = s1.createCriteria(DB.Cart.class);
                c2.add(Restrictions.eq("user", u));

                Criteria c4 = s1.createCriteria(Products.class);
                c4.add(Restrictions.eq("idproducts", pid2));
                Products p1 = (Products) c4.uniqueResult();

                if (p1.getQty() >= qty2) {
                
                    Cart c = (Cart) c2.uniqueResult();

                if (c == null) {

                    System.out.println("cart null");
                    Cart cart = new Cart();
                    cart.setUser(u);
                    cart.setSt("1");
                    cart.setTprice(p1.getSprice() * qty2);
                    cart.setTqty(qty2);

                    s1.save(cart);

                    CartHasProducts chp = new CartHasProducts();
                    chp.setCart(cart);
                    chp.setPrice(p1.getSprice() * qty2);
                    chp.setProducts(p1);
                    chp.setQty(qty2);

                    s1.save(chp);
                    s1.beginTransaction().commit();

                } else {

                    System.out.println("cart not null");
                    Double tprice = c.getTprice();
                    Integer tqty = c.getTqty();

                    Criteria c5 = s1.createCriteria(CartHasProducts.class);
                    c5.add(Restrictions.eq("cart", c));
                    c5.add(Restrictions.eq("products", p1));

                    CartHasProducts chp2 = (CartHasProducts) c5.uniqueResult();

                    if (chp2 == null) {

                        System.out.println("chp null");
                        CartHasProducts chp3 = new CartHasProducts();
                        chp3.setCart(c);
                        double d1 = p1.getSprice() * qty2;
                        chp3.setPrice(d1);
                        chp3.setProducts(p1);
                        chp3.setQty(qty2);
                        c.setTprice(tprice + d1);
                        c.setTqty(tqty + qty2);
                        s1.update(c);
                        s1.save(chp3);

                    } else {

                        System.out.println("chp not null");
                        chp2.setQty(chp2.getQty() + qty2);
                        double d2 = p1.getSprice() * qty2;
                        chp2.setPrice(chp2.getPrice() + d2);
                        c.setTprice(c.getTprice() + d2);
                        c.setTqty(c.getTqty() + qty2);
                        s1.update(c);
                        s1.update(chp2);
                        s1.beginTransaction().commit();
                        
                    }
                }
               out.writeObject("s");
                System.out.println("s");
                
                }else{
                out.writeObject("Available Quantity is " + p1.getQty());
                }
                
                
            } else {
                out.writeObject("sw");
            }

        } catch (Exception e) {
            out.writeObject("e");
            System.out.println(e);
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
