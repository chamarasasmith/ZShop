/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Invoice;
import DB.InvoiceHasProducts;
import DB.Products;
import DB.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

/**
 *
 * @author chama
 */
public class Mobile_Load_Order extends HttpServlet {

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

        Session s1 = NewHibernateUtil.getSessionFactory().openSession();
        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        try {

            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
            Object ob = in.readObject();
            
            HashMap hm=(HashMap)ob;
            String uid = hm.get("uid").toString();
            String t1 = hm.get("t1").toString();
            
            Criteria c1 = s1.createCriteria(Invoice.class);
            Criteria c2 = s1.createCriteria(User.class);
            c2.add(Restrictions.eq("iduser", Integer.parseInt(uid)));
            User user1= (User) c2.uniqueResult();
            c1.add(Restrictions.eq("user", user1));
            
            if (t1.isEmpty()) {
                
            }else{
            c1.add(Restrictions.eq("idinvoice", Integer.parseInt(t1)));
            }
            
            
            List<Invoice> list = c1.list();
            JSONObject obj = new JSONObject();
            int i1 = 1;
            System.out.println(list.size());
            if (list != null) {
                
                for (Invoice p1 : list) {
                    
                    obj.put(i1+"", p1.getIdinvoice()+" - "+p1.getIdate()+" - "+p1.getTprice());
                    ++i1;
                    
//                    for (InvoiceHasProducts ihp : p1.getInvoiceHasProductses()) {
//                        
//                        obj.put(i1 + "", ihp.getInvoice().getIdate() + " - " + ihp.getProducts().getPname() + " - " + ihp.getQty());
////                        obj.put(i1+"", i1);
//                        ++i1;
//                    }
                }
                if (list.size() == 0) {
                    out.writeObject("1");
                } else {
                    out.writeObject(obj.toString());
                }
            } else {
                out.writeObject("1");
            }

        } catch (Exception e) {
            System.out.println(e);
            out.writeObject("0");
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
