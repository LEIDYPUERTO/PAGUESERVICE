/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.Soat;
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
public class SoatDao{  
    
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

    public String guardaSoat(Soat soat) throws HibernateException  { 
       
         String id ="";  
         try 
        { 
           iniciaOperacion();
           id= (String)sesion.save(soat); 
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

    public void actualizaSoat(Soat soat) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(soat); 
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

    public void eliminaSoat(Soat soat)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(soat); 
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

    public Soat obtenSoat(String id) throws HibernateException 
    { 
        Soat soat = null;  
        try 
        { 
            iniciaOperacion(); 
            soat = (Soat) sesion.get(Soat.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return soat; 
    }  

    public List<Soat> obtenListaSoat() throws HibernateException 
    { 
        List<Soat> listaSoat = null;  

        try 
        { 
            iniciaOperacion(); 
            listaSoat = sesion.createQuery("from Soat").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaSoat; 
    }  
    public static void main(String[] args) {
           //mes dia año
        String fecha = "04/05/2016";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
        
        SoatDao daos = new SoatDao();
       // guardar
       //   daos.guardaSoat(new Soat("rft458", 150000000, fechaV, 1500000));
       // actualizar
       //   daos.actualizaSoat(new Soat("rft458", 200000000, fechaV, 2000000));
       // Borrar
       //   daos.eliminaSoat(new Soat("rft458", 0, fechaV, 0));
     
    }
}
