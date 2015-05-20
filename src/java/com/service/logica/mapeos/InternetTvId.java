package com.service.logica.mapeos;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1



/**
 * InternetTvId generated by hbm2java
 */
public class InternetTvId  implements java.io.Serializable {


     private int reciboInternet;
     private int clienteCedula;

    public InternetTvId() {
    }

    public InternetTvId(int reciboInternet, int clienteCedula) {
       this.reciboInternet = reciboInternet;
       this.clienteCedula = clienteCedula;
    }
   
    public int getReciboInternet() {
        return this.reciboInternet;
    }
    
    public void setReciboInternet(int reciboInternet) {
        this.reciboInternet = reciboInternet;
    }
    public int getClienteCedula() {
        return this.clienteCedula;
    }
    
    public void setClienteCedula(int clienteCedula) {
        this.clienteCedula = clienteCedula;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InternetTvId) ) return false;
		 InternetTvId castOther = ( InternetTvId ) other; 
         
		 return (this.getReciboInternet()==castOther.getReciboInternet())
 && (this.getClienteCedula()==castOther.getClienteCedula());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getReciboInternet();
         result = 37 * result + this.getClienteCedula();
         return result;
   }   


}

