package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1



/**
 * Urating generated by hbm2java
 */
public class Urating  implements java.io.Serializable {


     private Integer idurating;
     private User user;
     private Integer rate;

    public Urating() {
    }

	
    public Urating(User user) {
        this.user = user;
    }
    public Urating(User user, Integer rate) {
       this.user = user;
       this.rate = rate;
    }
   
    public Integer getIdurating() {
        return this.idurating;
    }
    
    public void setIdurating(Integer idurating) {
        this.idurating = idurating;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getRate() {
        return this.rate;
    }
    
    public void setRate(Integer rate) {
        this.rate = rate;
    }




}


