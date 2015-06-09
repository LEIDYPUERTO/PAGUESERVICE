/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.DetalleCamara;
import com.service.logica.mapeos.DetalleCamaraId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author user
 */
public class DetalleCamaraDao{  
    
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

    public DetalleCamaraId guardaDetalleCamara(DetalleCamara detalleCamara) throws HibernateException  { 
       
        DetalleCamaraId id ;  

        try 
        { 
            iniciaOperacion();
           id= (DetalleCamaraId)sesion.save(detalleCamara); 
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

    public void actualizaDetalleComercio(DetalleCamara detalleCamara) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(detalleCamara); 
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

    public void eliminaDetalleCamara(DetalleCamara detalleCamara)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(detalleCamara); 
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

    public DetalleCamara obtenDetalleComercio(DetalleCamaraId id) throws HibernateException 
    { 
        DetalleCamara detalleCamara = null;  
        try 
        { 
            iniciaOperacion(); 
            detalleCamara = (DetalleCamara) sesion.get(DetalleCamara.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return detalleCamara; 
    }  

    public List<DetalleCamara> obtenListaDetalleComercio() throws HibernateException 
    { 
        List<DetalleCamara> listaDetalleComercio = null;  

        try 
        { 
            iniciaOperacion(); 
            listaDetalleComercio = sesion.createQuery("from Detalle_Camara").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaDetalleComercio; 
    }  

       public static void main(String[] args) {
           
        DetalleCamaraDao daodc = new DetalleCamaraDao();
        ClienteDao daoc = new ClienteDao();
        SoatDao daos = new SoatDao();
        // Guardar
        //daodc.guardaDetalleCamara(new DetalleCamara(new DetalleCamaraId(108796548, 987426523), "Licoreia"));
        // Actualizar
        //daodc.actualizaDetalleComercio(new DetalleCamara(new DetalleCamaraId(108796548, 987426523), "Bar Aula Maxima"));
        //Borrar
        daodc.eliminaDetalleCamara(new DetalleCamara(new DetalleCamaraId(108796548, 987426523), null));
    }
}