/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.DetalleSoat;
import com.service.logica.mapeos.DetalleSoatId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author user
 */
public class DetalleSoatDao{  
    
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

    public DetalleSoatId guardaDetalleSoat(DetalleSoat detalleSoat) throws HibernateException  { 
       
        DetalleSoatId id ;  

        try 
        { 
            iniciaOperacion();
           id= (DetalleSoatId)sesion.save(detalleSoat); 
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

    public void actualizaDetalleSoat(DetalleSoat detalleSoat) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(detalleSoat); 
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

    public void eliminaDetalleSoat(DetalleSoat detalleSoat)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(detalleSoat); 
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

    public DetalleSoat obtenDetalleSoat(DetalleSoatId id) throws HibernateException 
    { 
        DetalleSoat detalleSoat = null;  
        try 
        { 
            iniciaOperacion(); 
            detalleSoat = (DetalleSoat) sesion.get(DetalleSoat.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return detalleSoat; 
    }  

    public List<DetalleSoat> obtenListaDetalleSoat() throws HibernateException 
    { 
        List<DetalleSoat> listaDetalleSoat = null;  

        try 
        { 
            iniciaOperacion(); 
            listaDetalleSoat = sesion.createQuery("from Detalle_Soat").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaDetalleSoat; 
    }  

       public static void main(String[] args) {
           
        DetalleSoatDao daods = new DetalleSoatDao();
        ClienteDao daoc = new ClienteDao();
        SoatDao daos = new SoatDao();
        // Guardar
        //daods.guardaDetalleSoat(new DetalleSoat(new DetalleSoatId(108796548, "rft458"),"Carro"));
        // Actualizar
        //daods.actualizaDetalleSoat(new DetalleSoat(new DetalleSoatId(108796548, "rft458"), "Ferrary Modelo 2014"));
        //Borrar
        //daods.eliminaDetalleSoat(new DetalleSoat(new DetalleSoatId(108796548, "rft458"), null));
    }
}