package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1



/**
 * Login generated by hbm2java
 */
public class Login  implements java.io.Serializable {


     private Integer idlogin;
     private Role role;
     private User user;
     private String username;
     private String password;
     private String st;

    public Login() {
    }

	
    public Login(Role role, User user) {
        this.role = role;
        this.user = user;
    }
    public Login(Role role, User user, String username, String password, String st) {
       this.role = role;
       this.user = user;
       this.username = username;
       this.password = password;
       this.st = st;
    }
   
    public Integer getIdlogin() {
        return this.idlogin;
    }
    
    public void setIdlogin(Integer idlogin) {
        this.idlogin = idlogin;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSt() {
        return this.st;
    }
    
    public void setSt(String st) {
        this.st = st;
    }




}

