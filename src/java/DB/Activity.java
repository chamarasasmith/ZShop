package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1



/**
 * Activity generated by hbm2java
 */
public class Activity  implements java.io.Serializable {


     private Integer idactivity;
     private Loginlog loginlog;
     private String des;

    public Activity() {
    }

	
    public Activity(Loginlog loginlog) {
        this.loginlog = loginlog;
    }
    public Activity(Loginlog loginlog, String des) {
       this.loginlog = loginlog;
       this.des = des;
    }
   
    public Integer getIdactivity() {
        return this.idactivity;
    }
    
    public void setIdactivity(Integer idactivity) {
        this.idactivity = idactivity;
    }
    public Loginlog getLoginlog() {
        return this.loginlog;
    }
    
    public void setLoginlog(Loginlog loginlog) {
        this.loginlog = loginlog;
    }
    public String getDes() {
        return this.des;
    }
    
    public void setDes(String des) {
        this.des = des;
    }




}

