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
import DB.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
public class Web_Checkout extends HttpServlet {

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

                JSONObject obj = new JSONObject();

                HttpSession hs = request.getSession();

                Object login = hs.getAttribute("login");

                if (login != null) {

                    Login l = (Login) login;
                    Criteria c1 = s1.createCriteria(Cart.class);

                    c1.add(Restrictions.eq("user", l.getUser()));
                    Cart cart1 = (Cart) c1.uniqueResult();
                    obj = new JSONObject();

                    int i1 = 1;

                    if (cart1 != null) {

                        for (CartHasProducts p1 : cart1.getCartHasProductses()) {
                            JSONObject obj2 = new JSONObject();
                            obj2.put("pid", p1.getProducts().getIdproducts());
                            obj2.put("pname", p1.getProducts().getPname());
                            obj2.put("qty", p1.getQty() + "");
                            obj2.put("sp", p1.getProducts().getSprice() + "");
                            obj2.put("cat", p1.getProducts().getCategory().getCname());
                            obj2.put("bname", p1.getProducts().getBrand().getBname());
                            obj2.put("img1", p1.getProducts().getMimg());
                            obj.put(i1 + "", obj2);
                            ++i1;
                        }
                        if (cart1.getCartHasProductses().size() == 0) {
                            out.print("1");
                        } else {
                            out.print(obj);
                        }
                    } else {
                        out.print("1");
                    }

                } else {

                    Object o1 = hs.getAttribute("s_cart");
                    obj = new JSONObject();
                    int i1 = 1;
                    if (o1 != null) {
                        HashMap<Integer, Integer> s = (HashMap<Integer, Integer>) o1;
                        for (Map.Entry<Integer, Integer> entry : s.entrySet()) {
                            Integer key = entry.getKey();
                            Integer value1 = entry.getValue();

                            Criteria c4 = s1.createCriteria(Products.class);
                            c4.add(Restrictions.eq("idproducts", key));
                            Products p1 = (Products) c4.uniqueResult();

                            JSONObject obj2 = new JSONObject();
                            obj2.put("pid", p1.getIdproducts() + "");
                            obj2.put("pname", p1.getPname());
                            obj2.put("qty", value1 + "");
                            obj2.put("sp", p1.getSprice() + "");
                            obj2.put("cat", p1.getCategory().getCname());
                            obj2.put("bname", p1.getBrand().getBname());
                            obj2.put("img1", p1.getMimg());
                            obj.put(i1 + "", obj2);
                            ++i1;
                        }
                        out.print(obj);
                    } else {
                        out.print("1");
                    }

                }

                request.setAttribute("j_check", obj);

                RequestDispatcher rd = request.getRequestDispatcher("sub/checkout1.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
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
