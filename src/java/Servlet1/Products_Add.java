/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Brand;
import DB.Category;
import DB.Products;
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
import javax.servlet.http.HttpSession;
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
public class Products_Add extends HttpServlet {

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
                
                Products p = new Products();
                
                int i1=0;
                
                for (Object o : list) {
                        
                        FileItem fileItem = (FileItem) o;
             
                        if (fileItem.isFormField()) {
                            
                            if (fileItem.getFieldName().equals("pname1")) {    
                                String t1 = fileItem.getString().trim();
                                if (!t1.isEmpty()) {
                                    i1++;
                                    p.setPname(fileItem.getString());
                                }
                            }
                            if (fileItem.getFieldName().equals("brname1")) {    
                                String t1 = fileItem.getString().split(" : ")[0];
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                    Criteria c4 = s1.createCriteria(Brand.class);
                                c4.add(Restrictions.eq("idbrand", Integer.parseInt(t1)));
                                Brand b = (Brand) c4.uniqueResult();
                                p.setBrand(b);
                                }
                            }
                            if (fileItem.getFieldName().equals("cp1")) { 
                                String t1 = fileItem.getString();
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                p.setCprice(Double.parseDouble(t1));    
                                }
                                
                            }
                            if (fileItem.getFieldName().equals("pdes1")) {    
                                p.setDes(fileItem.getString());
                            }
                            if (fileItem.getFieldName().equals("pcat1")) {    
                                String t1 = fileItem.getString().split(" : ")[0];
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                Criteria c4 = s1.createCriteria(Category.class);
                                c4.add(Restrictions.eq("idcategory", Integer.parseInt(t1)));
                                Category c = (Category) c4.uniqueResult();
                                p.setCategory(c);
                                }
                            }
                            if (fileItem.getFieldName().equals("pqty1")) {    
                                String t1 = fileItem.getString();
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                p.setQty(Integer.parseInt(t1));
                                }
                            }
                            if (fileItem.getFieldName().equals("psp1")) {    
                                String t1 = fileItem.getString();
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                p.setSprice(Double.parseDouble(t1));
                                }
                            }
                            if (fileItem.getFieldName().equals("pmin1")) {    
                                String t1 = fileItem.getString();
                                boolean isNumeric = t1.chars().allMatch( Character::isDigit );
                                if (isNumeric) {
                                    i1++;
                                p.setMin(Integer.parseInt(t1));
                                }
                            }
                            
                        } else {
                            if (fileItem.getFieldName().equals("proimg1")) {
                                String thumb = "";
                                
                                Date date = new Date();
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
                                String s= sdf.format(date);
                                
                                if (fileItem.getName().isEmpty()) {
                                    thumb = "img\\uploaded\\p_icon2.png";
                                } else {
                                    int len= fileItem.getName().length();
                                    String name1 = fileItem.getName().substring(len-4);
                                    thumb = "img\\uploaded\\"+s+name1;
                                    File f = new File(request.getServletContext().getRealPath("/") + thumb);
                                    fileItem.write(f);
                                }
                                p.setMimg(thumb);
                                System.out.println(thumb);
                            }
                        }
                    }
                if (i1==7) {
                p.setSt("1");
                s1.save(p);
                s1.beginTransaction().commit();
                out.println("<center>Success</center>");    
                }else{
                out.println("<center>Unsuccess</center>");
                }
                
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
