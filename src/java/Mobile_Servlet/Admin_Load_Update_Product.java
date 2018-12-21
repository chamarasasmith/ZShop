/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Products;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
public class Admin_Load_Update_Product extends HttpServlet {

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

        Session s = NewHibernateUtil.getSessionFactory().openSession();
        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        try {
            ObjectInputStream in = new ObjectInputStream(request.getInputStream());
            String ob = in.readObject().toString();
            String id = ob.split(" - ")[0];

            int pid = Integer.parseInt(id);

            Criteria c4 = s.createCriteria(Products.class);
            c4.add(Restrictions.eq("idproducts", pid));
            Products p = (Products) c4.uniqueResult();

            JSONObject jo = new JSONObject();
            if (p != null) {
                jo.put("pid", p.getIdproducts());
                jo.put("pname", p.getPname());
                jo.put("qty", p.getQty());
                jo.put("sp", p.getSprice());
                jo.put("cp", p.getCprice());
                jo.put("cat", p.getCategory().getIdcategory() + " - " + p.getCategory().getCname());
                jo.put("bname", p.getBrand().getIdbrand() + " - " + p.getBrand().getBname());
                jo.put("img1", p.getMimg());
                jo.put("des", p.getDes());
                jo.put("min", p.getMin());
                jo.put("st", p.getSt());
                out.writeObject(jo + "");
                System.out.println(p.getPname());
            } else {
                out.writeObject("sw");
            }

        } catch (Exception e) {
            System.out.println(e);
            out.writeObject("e");
        } finally {
            s.close();
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
