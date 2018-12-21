/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Cart;
import DB.CartHasProducts;
import DB.Login;
import DB.Loginlog;
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class SignIn extends HttpServlet {

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

                HttpSession hs = request.getSession();

                String un = request.getParameter("un");
                String pw = request.getParameter("pw");

                Criteria c1 = s.createCriteria(Login.class);

                c1.add(Restrictions.and(Restrictions.eq("username", un), Restrictions.eq("password", pw)));
                c1.add(Restrictions.eq("st", "1"));
                Login l = (Login) c1.uniqueResult();

                if (l != null) {
                    Date date = new Date();
                    Loginlog l1 = new Loginlog();
                    l1.setLogintime(date);
                    l1.setLogindate(date);
                    l1.setLogouttime(date);
                    l1.setLogoutdate(date);
                    l1.setSt("1");
                    l1.setUser(l.getUser());

                    s.save(l1);
                    s.beginTransaction().commit();

                    hs.setAttribute("login", l);
                    hs.setAttribute("userid", l.getUser().getIduser());
                    hs.setAttribute("log", l1);

                    Set_Logs.Set(s, hs, "Login");

                    Object o1 = hs.getAttribute("s_cart");

                    if (o1 != null) {
                        HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) o1;

                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            Integer id2 = entry.getKey();
                            Integer qty2 = entry.getValue();

                            Criteria c2 = s.createCriteria(Cart.class);
                            c2.add(Restrictions.eq("user", l.getUser()));

                            Criteria c5 = s.createCriteria(Products.class);
                            c5.add(Restrictions.eq("idproducts", id2));
                            Products p1 = (Products) c5.uniqueResult();

                            Cart c = (Cart) c2.uniqueResult();

                            if (c == null) {
                                Cart cart = new Cart();
                                cart.setUser(l.getUser());
                                cart.setSt("1");
                                cart.setTprice(p1.getSprice() * qty2);
                                cart.setTqty(qty2);

                                s.save(cart);

                                CartHasProducts chp = new CartHasProducts();
                                chp.setCart(cart);
                                chp.setPrice(p1.getSprice() * qty2);
                                chp.setProducts(p1);
                                chp.setQty(qty2);

                                s.save(chp);
                                s.beginTransaction().commit();
                                out.print("Add New DB Cart");
                            } else {

                                Double tprice = c.getTprice();
                                Integer tqty = c.getTqty();

                                Criteria c4 = s.createCriteria(CartHasProducts.class);
                                c4.add(Restrictions.eq("cart", c));
                                c4.add(Restrictions.eq("products", p1));

                                CartHasProducts chp2 = (CartHasProducts) c4.uniqueResult();

                                if (chp2 == null) {
                                    CartHasProducts chp3 = new CartHasProducts();
                                    chp3.setCart(c);
                                    double d1 = p1.getSprice() * qty2;
                                    chp3.setPrice(d1);
                                    chp3.setProducts(p1);
                                    chp3.setQty(qty2);
                                    c.setTprice(tprice + d1);
                                    c.setTqty(tqty + qty2);
                                    s.update(c);
                                    s.save(chp3);
                                    out.print("Add Item to Cart");
                                } else {

                                    chp2.setQty(chp2.getQty() + qty2);
                                    double d2 = p1.getSprice() * qty2;
                                    chp2.setPrice(chp2.getPrice() + d2);
                                    c.setTprice(c.getTprice() + d2);
                                    c.setTqty(c.getTqty() + qty2);
                                    s.update(c);
                                    s.update(chp2);
                                    out.print("Update DB Cart Item");
                                }

                                s.beginTransaction().commit();

                            }

                        }

                    }

                    out.print("Success");
                } else {
                    out.print("Unsuccess");
                }

            } catch (Exception e) {
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
