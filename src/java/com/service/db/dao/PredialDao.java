/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.ImpuestoPredial;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;


/**
 *
 * 
 */
public class PredialDao{  
    
  private Session sesion; 
  private Transaction transaccion; 
    private void iniciaOperacion() throws HibernateException {
        
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        transaccion = sesion.beginTransaction(); 
    }  

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        transaccion.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    }

    public int guardaPredial(ImpuestoPredial impuestoPredial) throws HibernateException  { 
       
         int id =0;  
         try 
        { 
           iniciaOperacion();
           id= (Integer)sesion.save(impuestoPredial); 
           transaccion.commit(); 
           
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            
            if(sesion !=null) {
                sesion.close();
            } 
        }  

        return id; 
    }  

    public void actualizaPredial(ImpuestoPredial impuestoPredial) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(impuestoPredial); 
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

    public void eliminaPredial(ImpuestoPredial impuestoPredial)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(impuestoPredial); 
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

    public ImpuestoPredial obtenPredial(int id) throws HibernateException 
    { 
        ImpuestoPredial impuestoPredial = null;  
        try 
        { 
            iniciaOperacion(); 
            impuestoPredial = (ImpuestoPredial) sesion.get(ImpuestoPredial.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return impuestoPredial; 
    }  

    public List<ImpuestoPredial> obtenListaPredial() throws HibernateException 
    { 
        List<ImpuestoPredial> listaPredial = null;  

        try 
        { 
            iniciaOperacion(); 
            listaPredial = sesion.createQuery("from Impuesto_Predial").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaPredial; 
    }
    public List<ImpuestoPredial> obtenListapredialFechas(Date date)  throws HibernateException {
          List<ImpuestoPredial> listaPredial = null;  

        try 
        { 
            iniciaOperacion(); 
            listaPredial = sesion.createQuery("from ImpuestoPredial where Fecha_Pago_Predial < '"+date+"'").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaPredial; 
    }
    public static void main(String[] args) {
           //mes dia año
        String fecha = "03/22/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
        
        PredialDao daop = new PredialDao();
       // guardar
        daop.guardaPredial(new ImpuestoPredial(846259, fechaV, 900000000, 9000000));
       // actualizar
        daop.actualizaPredial(new ImpuestoPredial(846259, fechaV, 500000000, 5000000));
       // Borrar
       // daop.eliminaPredial(new ImpuestoPredial(846259, fechaV, 0, 0));
     
    }
}
