/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import DB.Brand;
import DB.Login;
import DB.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;

/**
 *
 * @author chama
 */
public class Brand_Add extends HttpServlet {

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
   
        Session s1 = Connection1.NewHibernateUtil.getSessionFactory().openSession();

            try {

                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                List list = upload.parseRequest(request);
                
                Brand b = new Brand();
                
                for (Object o : list) {
                        
                        FileItem fileItem = (FileItem) o;
                        
                        
                        if (fileItem.isFormField()) {
                            
                            if (fileItem.getFieldName().equals("bname1")) {    
                                b.setBname(fileItem.getString());
                            }
                             
                            
                        } else {
                            
                            if (fileItem.getFieldName().equals("bimg1")) {
                                String thumb = "";
                                
                                Date date = new Date();
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
                                String s= sdf.format(date);
                                
                                if (fileItem.getName().isEmpty()) {
                                    thumb = "img\\uploaded\\dprofile.png";
                                } else {
                                    int len= fileItem.getName().length();
                                    String name1 = fileItem.getName().substring(len-4);
                                    thumb = "img\\uploaded\\"+s+name1;
                                    File f = new File(request.getServletContext().getRealPath("/") + thumb);
                                    fileItem.write(f);
                                    
                                }
                                
                                b.setImg(thumb);
                                System.out.println(thumb);
                            }
                            
                            
                         
                        }
                        
                        
                        
                    }
                
                b.setSt("1");
                s1.save(b);
                s1.beginTransaction().commit();
                out.println("<center>Success</center>");
            } catch (Exception e) {
                System.out.println(e);
                out.println("<center>Unsuccess</center>");
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
