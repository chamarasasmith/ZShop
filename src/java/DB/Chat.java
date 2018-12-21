package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Chat generated by hbm2java
 */
public class Chat  implements java.io.Serializable {


     private Integer chatid;
     private User userByUserIduser;
     private User userByUserIduser1;
     private String st;
     private Set<Chatdes> chatdeses = new HashSet<Chatdes>(0);

    public Chat() {
    }

    public Chat(User userByUserIduser, User userByUserIduser1, String st, Set<Chatdes> chatdeses) {
       this.userByUserIduser = userByUserIduser;
       this.userByUserIduser1 = userByUserIduser1;
       this.st = st;
       this.chatdeses = chatdeses;
    }
   
    public Integer getChatid() {
        return this.chatid;
    }
    
    public void setChatid(Integer chatid) {
        this.chatid = chatid;
    }
    public User getUserByUserIduser() {
        return this.userByUserIduser;
    }
    
    public void setUserByUserIduser(User userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
    public User getUserByUserIduser1() {
        return this.userByUserIduser1;
    }
    
    public void setUserByUserIduser1(User userByUserIduser1) {
        this.userByUserIduser1 = userByUserIduser1;
    }
    public String getSt() {
        return this.st;
    }
    
    public void setSt(String st) {
        this.st = st;
    }
    public Set<Chatdes> getChatdeses() {
        return this.chatdeses;
    }
    
    public void setChatdeses(Set<Chatdes> chatdeses) {
        this.chatdeses = chatdeses;
    }




}


