/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Activity;
import DB.Loginlog;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author chama
 */
public class Set_Logs {

    public static boolean Set(Session s, HttpSession hs, String t1) {

        try {
            Object l = hs.getAttribute("log");

            if (l != null) {
                Loginlog log = (Loginlog) l;
                Activity activity = new Activity();
                activity.setDes(t1);
                activity.setLoginlog(log);
                s.save(activity);
                s.beginTransaction().commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        } finally {
        }
    }

}
