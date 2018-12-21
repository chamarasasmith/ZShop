/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_Servlet;

import Connection1.NewHibernateUtil;
import DB.Cart;
import DB.CartHasProducts;
import DB.Products;
import DB.User;
import java.io.IOException;
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
public class Load_Cart extends HttpServlet {

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
            Session s1=NewHibernateUtil.getSessionFactory().openSession();
            
            try {
                
                String si=request.getParameter("si");
                System.out.println(si);
                int a=Integer.parseInt(si);
                
                Criteria c1 = s1.createCriteria(Cart.class);
                
                Criteria c3 = s1.createCriteria(User.class);
                c3.add(Restrictions.eq("iduser", Integer.parseInt(si)));
                User c2 = (User) c3.uniqueResult();
                
                if (c2==null) {
                    out.print("1");
                    return;
                }
                
                c1.add(Restrictions.eq("user", c2));
                Cart cart1 = (Cart) c1.uniqueResult();
                JSONObject obj = new JSONObject();
                
                int i1=1;
                
                if (cart1!=null) {
                    
                    for (CartHasProducts p1 : cart1.getCartHasProductses()) {
                        JSONObject obj2 = new JSONObject();
                        obj2.put("pid", p1.getProducts().getIdproducts());
                        obj2.put("pname", p1.getProducts().getPname());
                        obj2.put("qty", p1.getQty());
                        obj2.put("sp", p1.getPrice());
                        obj2.put("cat", p1.getProducts().getCategory().getCname());
                        obj2.put("bname", p1.getProducts().getBrand().getBname());
                        obj2.put("img1", p1.getProducts().getMimg());
                        obj2.put("des", p1.getProducts().getDes());
                        obj2.put("min", p1.getProducts().getMin());
                        obj2.put("st", p1.getProducts().getSt());
                        obj.put(i1+"", obj2);
                        ++i1;
                    }
                    if (cart1.getCartHasProductses().size()==0) {
                    out.print("1");
                    }else{
                    out.print(obj);
                    }
                }else{
                out.print("1");
                }
                
                
            } catch (Exception e) {
            System.out.println(e);
            out.print("0");
            }finally{
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
