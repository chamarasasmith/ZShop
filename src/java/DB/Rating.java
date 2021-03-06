package DB;
// Generated Aug 18, 2018 5:14:28 PM by Hibernate Tools 4.3.1



/**
 * Rating generated by hbm2java
 */
public class Rating  implements java.io.Serializable {


     private Integer idrating;
     private Products products;
     private Integer rate;
     private String st;

    public Rating() {
    }

	
    public Rating(Products products) {
        this.products = products;
    }
    public Rating(Products products, Integer rate, String st) {
       this.products = products;
       this.rate = rate;
       this.st = st;
    }
   
    public Integer getIdrating() {
        return this.idrating;
    }
    
    public void setIdrating(Integer idrating) {
        this.idrating = idrating;
    }
    public Products getProducts() {
        return this.products;
    }
    
    public void setProducts(Products products) {
        this.products = products;
    }
    public Integer getRate() {
        return this.rate;
    }
    
    public void setRate(Integer rate) {
        this.rate = rate;
    }
    public String getSt() {
        return this.st;
    }
    
    public void setSt(String st) {
        this.st = st;
    }




}


