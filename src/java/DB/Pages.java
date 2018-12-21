package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Pages generated by hbm2java
 */
public class Pages  implements java.io.Serializable {


     private Integer idpages;
     private String pagesname;
     private String url;
     private String st;
     private Set<RoleHasPages> roleHasPageses = new HashSet<RoleHasPages>(0);
     private Set<LoginlogHasPages> loginlogHasPageses = new HashSet<LoginlogHasPages>(0);

    public Pages() {
    }

    public Pages(String pagesname, String url, String st, Set<RoleHasPages> roleHasPageses, Set<LoginlogHasPages> loginlogHasPageses) {
       this.pagesname = pagesname;
       this.url = url;
       this.st = st;
       this.roleHasPageses = roleHasPageses;
       this.loginlogHasPageses = loginlogHasPageses;
    }
   
    public Integer getIdpages() {
        return this.idpages;
    }
    
    public void setIdpages(Integer idpages) {
        this.idpages = idpages;
    }
    public String getPagesname() {
        return this.pagesname;
    }
    
    public void setPagesname(String pagesname) {
        this.pagesname = pagesname;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSt() {
        return this.st;
    }
    
    public void setSt(String st) {
        this.st = st;
    }
    public Set<RoleHasPages> getRoleHasPageses() {
        return this.roleHasPageses;
    }
    
    public void setRoleHasPageses(Set<RoleHasPages> roleHasPageses) {
        this.roleHasPageses = roleHasPageses;
    }
    public Set<LoginlogHasPages> getLoginlogHasPageses() {
        return this.loginlogHasPageses;
    }
    
    public void setLoginlogHasPageses(Set<LoginlogHasPages> loginlogHasPageses) {
        this.loginlogHasPageses = loginlogHasPageses;
    }




}


