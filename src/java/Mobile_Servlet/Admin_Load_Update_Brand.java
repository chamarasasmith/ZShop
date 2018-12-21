/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Brand;
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
public class Admin_Load_Update_Brand extends HttpServlet {

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

            int bid = Integer.parseInt(id);

            Criteria c4 = s.createCriteria(Brand.class);
            c4.add(Restrictions.eq("idbrand", bid));
            Brand b = (Brand) c4.uniqueResult();

            JSONObject jo = new JSONObject();
            if (b != null) {
                jo.put("bid", b.getIdbrand());
                jo.put("bname", b.getBname());
                jo.put("img1", b.getImg());
                jo.put("st", b.getSt());
                out.writeObject(jo + "");
                System.out.println(b.getBname());
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
