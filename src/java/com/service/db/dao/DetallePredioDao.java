/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.DetallePredio;
import com.service.logica.mapeos.DetallePredioId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author user
 */
public class DetallePredioDao{  
    
  private Session sesion; 
  private Transaction transaccion; 
    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        transaccion = sesion.beginTransaction(); 
    }  

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        transaccion.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    }

    public DetallePredioId guardaDetallePredio(DetallePredio detallePredio) throws HibernateException  { 
       
        DetallePredioId id ;  

        try 
        { 
            iniciaOperacion();
           id= (DetallePredioId)sesion.save(detallePredio); 
            transaccion.commit(); 
           
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            
            if(sesion !=null) {
                sesion.close();
            } 
            
        }  

        return id; 
       
    }  

    public void actualizaDetallePredio(DetallePredio detallePredio) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(detallePredio); 
            transaccion.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public void eliminaDetallePredio(DetallePredio detallePredio)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(detallePredio); 
            transaccion.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public DetallePredio obtenDetallePredio(DetallePredioId id) throws HibernateException 
    { 
        DetallePredio detallePredio = null;  
        try 
        { 
            iniciaOperacion(); 
            detallePredio = (DetallePredio) sesion.get(DetallePredio.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return detallePredio; 
    }  

    public List<DetallePredio> obtenListaDetallePredio(int cedula) throws HibernateException 
    { 
        List<DetallePredio> listaDetallePredio = null;  

        try 
        { 
            iniciaOperacion(); 
            listaDetallePredio = sesion.createQuery("from DetallePredio where Cliente_Cedula = "+cedula+"").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaDetallePredio; 
    }  

       public static void main(String[] args) {
           
        DetallePredioDao daodp = new DetallePredioDao();
        ClienteDao daoc = new ClienteDao();
        SoatDao daos = new SoatDao();
        // Guardar
        //daodp.guardaDetallePredio(new DetallePredio(new DetallePredioId(108796548, 846259), "Finca Duitama"));
        // Actualizar
        //daodp.actualizaDetallePredio(new DetallePredio(new DetallePredioId(108796548, 846259), "Finca Sogamoso"));
        //Borrar
        daodp.eliminaDetallePredio(new DetallePredio(new DetallePredioId(108796548, 846259), null));
        //daods.eliminaDetalleSoat(new DetalleSoat(new DetalleSoatId(108796548, "rft458"), null));
    }
}