/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet1;

import Connection1.NewHibernateUtil;
import DB.Activity;
import DB.Login;
import DB.Loginlog;
import DB.RoleHasPages;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author chama
 */
public class Check_Access {
    
    public static boolean Check1(Login l,String t1){
    
        try {
            
            if (l != null) {
                
                Set<RoleHasPages> rhp = l.getRole().getRoleHasPageses();
                for (RoleHasPages roleHasPages : rhp) {
                    if (roleHasPages.getPages().getPagesname().equalsIgnoreCase(t1) && roleHasPages.getPages().getSt().equalsIgnoreCase("1")) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        return false;
        }finally{
        }
        return false;
    }
    
}
