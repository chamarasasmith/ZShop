package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Chatdes generated by hbm2java
 */
public class Chatdes  implements java.io.Serializable {


     private Integer idchatdes;
     private Chat chat;
     private String msg;
     private Date mdate;
     private Date mtime;
     private String st;

    public Chatdes() {
    }

	
    public Chatdes(Chat chat) {
        this.chat = chat;
    }
    public Chatdes(Chat chat, String msg, Date mdate, Date mtime, String st) {
       this.chat = chat;
       this.msg = msg;
       this.mdate = mdate;
       this.mtime = mtime;
       this.st = st;
    }
   
    public Integer getIdchatdes() {
        return this.idchatdes;
    }
    
    public void setIdchatdes(Integer idchatdes) {
        this.idchatdes = idchatdes;
    }
    public Chat getChat() {
        return this.chat;
    }
    
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Date getMdate() {
        return this.mdate;
    }
    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    public Date getMtime() {
        return this.mtime;
    }
    
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    public String getSt() {
        return this.st;
    }
    
    public void setSt(String st) {
        this.st = st;
    }




}


