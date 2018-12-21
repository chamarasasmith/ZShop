/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Brand;
import DB.Category;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author chama
 */
public class Admin_Add_Cat extends HttpServlet {

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
        
        
        ObjectOutputStream out = null;
        Session s1=NewHibernateUtil.getSessionFactory().openSession();
        
        try {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String s = sdf.format(date);

            String thumb = "img\\uploaded\\" + s + ".png";

            ObjectInputStream in = new ObjectInputStream(request.getInputStream());

            System.out.println(request.getServletContext().getRealPath("/") + thumb);

            Object o = in.readObject();

            if (o instanceof HashMap) {
                HashMap hm = (HashMap) o;

                Object img1 = hm.get("img1");
                String bname= hm.get("cname").toString();
                
                Category c1=new Category();
                c1.setCname(bname);
                c1.setSt("1");
                
                byte[] bytearray = (byte[]) img1;

                ByteArrayInputStream bis = new ByteArrayInputStream(bytearray);

                BufferedImage bimage = ImageIO.read(bis);

                ImageIO.write(bimage, "png", new File(request.getServletContext().getRealPath("/") + thumb));

                out = new ObjectOutputStream(response.getOutputStream());
                
                
                c1.setImg(thumb);

                s1.save(c1);
                s1.beginTransaction().commit();
                
                out.writeObject("s");
                
            } else {
                out.writeObject("sw");
            }

        } catch (Exception e) {
            System.out.println(e);
            out.writeObject("e");
        } finally {
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
