package com.service.logica.mapeos;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1



/**
 * CrediGasId generated by hbm2java
 */
public class CrediGasId  implements java.io.Serializable {


     private int contratoGas;
     private int EGasNReciboGas;

    public CrediGasId() {
    }

    public CrediGasId(int contratoGas, int EGasNReciboGas) {
       this.contratoGas = contratoGas;
       this.EGasNReciboGas = EGasNReciboGas;
    }
   
    public int getContratoGas() {
        return this.contratoGas;
    }
    
    public void setContratoGas(int contratoGas) {
        this.contratoGas = contratoGas;
    }
    public int getEGasNReciboGas() {
        return this.EGasNReciboGas;
    }
    
    public void setEGasNReciboGas(int EGasNReciboGas) {
        this.EGasNReciboGas = EGasNReciboGas;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CrediGasId) ) return false;
		 CrediGasId castOther = ( CrediGasId ) other; 
         
		 return (this.getContratoGas()==castOther.getContratoGas())
 && (this.getEGasNReciboGas()==castOther.getEGasNReciboGas());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getContratoGas();
         result = 37 * result + this.getEGasNReciboGas();
         return result;
   }   


}

