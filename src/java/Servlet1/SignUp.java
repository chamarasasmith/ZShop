/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import DB.Login;
import DB.Rating;
import DB.Role;
import DB.Urating;
import DB.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chama
 */
public class SignUp extends HttpServlet {

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
                
                Login login = new Login();
                User user = new User();
                
                for (Object o : list) {
                        
                        FileItem fileItem = (FileItem) o;
                        
                        if (fileItem.isFormField()) {
                            
                            if (fileItem.getFieldName().equals("fn1")) {    
                                user.setFname(fileItem.getString());
                            }
                            if (fileItem.getFieldName().equals("ln1")) {
                               user.setLname(fileItem.getString());
                            }
                            if (fileItem.getFieldName().equals("un1")) {
                                login.setUsername(fileItem.getString());
                            }
                            if (fileItem.getFieldName().equals("pw1")) {
                                login.setPassword(fileItem.getString());
                            }
                            if (fileItem.getFieldName().equals("email1")) {
                                user.setEmail(fileItem.getString());
                            }
                            
                        } else {
                            
                            if (fileItem.getFieldName().equals("suimg1")) {
                                String thumb = "";
                                
                                if (fileItem.getName().isEmpty()) {
                                    thumb = "img\\uploaded\\dprofile.png";
                                } else {
                                    thumb = "img\\uploaded\\" + Math.random() + fileItem.getName();
                                    File f = new File(request.getServletContext().getRealPath("/") + thumb);
                                    fileItem.write(f);
                                    
                                }
                                
                                user.setImg(thumb);
                                System.out.println(thumb);
                            }
                            
                            
                         
                        }
                        
                        
                        
                    }
                
                Criteria c1 = s1.createCriteria(Role.class);
                Role r= (Role) c1.add(Restrictions.eq("rname", "User")).uniqueResult();
                
                if (r==null) {
                    Role role = new Role();
                    role.setRname("User");
                    role.setSt("1");
                    login.setRole(role);
                    s1.save(role);
                    
                }else{
                    login.setRole(r);
                }
                
                Role role = new Role();
                
                
                user.setJdate(new Date());
                user.setSt("1");
                s1.save(user);
                login.setUser(user);
                login.setSt("1");
                s1.save(login);
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
