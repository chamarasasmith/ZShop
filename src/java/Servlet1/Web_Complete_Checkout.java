/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Cart;
import DB.CartHasProducts;
import DB.Invoice;
import DB.InvoiceHasProducts;
import DB.Login;
import DB.Products;
import DB.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;
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
public class Web_Complete_Checkout extends HttpServlet {

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

                HttpSession hs = request.getSession();

                Object u = hs.getAttribute("userid");

                if (u != null) {
//                    System.out.println("Login Not Null");

                    Criteria c3 = s1.createCriteria(User.class);
                    c3.add(Restrictions.eq("iduser", Integer.parseInt(u.toString())));
                    User user1 = (User) c3.uniqueResult();

                    Criteria c1 = s1.createCriteria(Cart.class);

                    c1.add(Restrictions.eq("user", user1));

                    Cart cart = (Cart) c1.uniqueResult();

                    if (cart != null) {

                        Set<CartHasProducts> chp1 = cart.getCartHasProductses();

                        Invoice invoice = new Invoice();

                        int qty1 = 0;
                        double sp1 = 0;
                        String s = "Success..!\n";
                        for (CartHasProducts chp2 : chp1) {

                            Criteria c4 = s1.createCriteria(Products.class);
                            c4.add(Restrictions.eq("idproducts", chp2.getProducts().getIdproducts()));
                            Products p1 = (Products) c4.uniqueResult();

                            if (p1.getQty() >= chp2.getQty()) {
                                InvoiceHasProducts ihp = new InvoiceHasProducts();
                                ihp.setInvoice(invoice);
                                ihp.setPrice(chp2.getPrice());
                                ihp.setProducts(chp2.getProducts());
                                ihp.setQty(chp2.getQty());
                                sp1 += chp2.getPrice();
                                qty1 += chp2.getQty();
                                p1.setQty(p1.getQty() - chp2.getQty());

                                s1.update(p1);
                                s1.save(ihp);
                                s1.delete(chp2);
                            } else {
                                s += p1.getPname() + " Available Qty is " + p1.getQty() + "\n";
                                InvoiceHasProducts ihp = new InvoiceHasProducts();
                                ihp.setInvoice(invoice);
                                ihp.setPrice(p1.getQty() * p1.getSprice());
                                ihp.setProducts(chp2.getProducts());
                                ihp.setQty(p1.getQty());
                                sp1 += p1.getQty() * p1.getSprice();
                                qty1 += p1.getQty();
                                p1.setQty(0);
                                s1.update(p1);
                                s1.save(ihp);
                                s1.delete(chp2);

                            }

                        }

                        invoice.setSt("1");
                        invoice.setTprice(sp1);
                        invoice.setTqty(qty1);
                        invoice.setUser(cart.getUser());
                        invoice.setIdate(new Date());
                        s1.save(invoice);

                        s1.delete(cart);
                        s1.beginTransaction().commit();
                        out.print(s);
                    } else {
                        out.print("0");
                    }
                } else {
                    Object o1 = hs.getAttribute("s_cart");
                    if (o1 != null) {
                        out.print("1");
                    } else {
                        out.print("0");
                    }

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
