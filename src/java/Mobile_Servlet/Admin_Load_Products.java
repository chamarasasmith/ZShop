/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Products;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;
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
public class Admin_Load_Products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        /* TODO output your page here. You may use following sample code. */
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

            Criteria c1 = s1.createCriteria(Products.class);

            List<Products> list = c1.list();
            JSONObject obj = new JSONObject();
            int i1 = 1;

            if (list != null) {

                for (Products p1 : list) {
                    JSONObject obj2 = new JSONObject();
                    obj2.put("pid", p1.getIdproducts());
                    obj2.put("pname", p1.getPname());
                    obj2.put("qty", p1.getQty());
                    obj2.put("sp", p1.getSprice());
                    obj2.put("cat", p1.getCategory().getCname());
                    obj2.put("bname", p1.getBrand().getBname());
                    obj2.put("img1", p1.getMimg());
                    obj2.put("des", p1.getDes());
                    obj2.put("min", p1.getMin());
                    obj2.put("st", p1.getSt());
                    obj.put(i1 + "", obj2);
                    ++i1;
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
