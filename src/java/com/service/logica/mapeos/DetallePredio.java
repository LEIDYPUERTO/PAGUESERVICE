package com.service.logica.mapeos;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1



/**
 * DetallePredio generated by hbm2java
 */
public class DetallePredio  implements java.io.Serializable {


     private DetallePredioId id;
     private String descripcionPredio;

    public DetallePredio() {
    }

	
    public DetallePredio(DetallePredioId id) {
        this.id = id;
    }
    public DetallePredio(DetallePredioId id, String descripcionPredio) {
       this.id = id;
       this.descripcionPredio = descripcionPredio;
    }
   
    public DetallePredioId getId() {
        return this.id;
    }
    
    public void setId(DetallePredioId id) {
        this.id = id;
    }
    public String getDescripcionPredio() {
        return this.descripcionPredio;
    }
    
    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }




}


