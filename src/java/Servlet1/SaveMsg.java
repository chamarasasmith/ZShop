/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Chat;
import DB.Chatdes;
import DB.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chama
 */
public class SaveMsg extends HttpServlet {

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
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            try {

                HttpSession hs = request.getSession();

                String msg = request.getParameter("msg");
                String to = request.getParameter("to");

                Object uid = hs.getAttribute("userid");

                if (uid == null) {
                    out.print("Unsuccess");
                } else {
                    Criteria c3 = s.createCriteria(User.class);
                    c3.add(Restrictions.eq("iduser", Integer.parseInt(uid.toString())));
                    User u = (User) c3.uniqueResult();

//                    System.out.println(u);
                    Criteria c4 = s.createCriteria(User.class);
                    c4.add(Restrictions.eq("iduser", Integer.parseInt(to)));
                    User u2 = (User) c4.uniqueResult();

                    Criteria c1 = s.createCriteria(Chat.class);
                    c1.add(Restrictions.or(Restrictions.and(Restrictions.eq("userByUserIduser", u), Restrictions.eq("userByUserIduser1", u2)), Restrictions.and(Restrictions.eq("userByUserIduser1", u), Restrictions.eq("userByUserIduser", u2))));

                    Chat c = (Chat) c1.uniqueResult();

                    if (c != null) {
                        Chatdes chatdes = new Chatdes();
                        chatdes.setChat(c);
                        chatdes.setMdate(new Date());
                        chatdes.setMtime(new Date());
                        chatdes.setMsg(msg);
                        chatdes.setSt("1");
                        s.save(chatdes);
                        s.beginTransaction().commit();

                    } else {

                        Chat chat = new Chat();
                        chat.setUserByUserIduser(u);
                        chat.setUserByUserIduser1(u2);
                        chat.setSt("1");

                        s.save(chat);

                        Chatdes chatdes = new Chatdes();
                        chatdes.setChat(chat);
                        chatdes.setMdate(new Date());
                        chatdes.setMtime(new Date());
                        chatdes.setMsg(msg);
                        chatdes.setSt("1");

                        s.save(chatdes);
                        s.beginTransaction().commit();
                    }

                    out.print("Success");
                }

            } catch (Exception e) {
                out.print("Unsuccess");
                System.out.println(e);
            } finally {
                s.close();
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
