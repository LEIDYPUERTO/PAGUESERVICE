/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.CamaraComercio;
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
public class ComercioDao{  
    
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

    public int guardaComercio(CamaraComercio camaraComercio) throws HibernateException  { 
       
         int id =0;  
         try 
        { 
           iniciaOperacion();
           id= (Integer)sesion.save(camaraComercio); 
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

    public void actualizaComercio(CamaraComercio camaraComercio) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(camaraComercio); 
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

    public void eliminaComercio(CamaraComercio camaraComercio)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(camaraComercio); 
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

    public CamaraComercio obtenComercio(int id) throws HibernateException 
    { 
        CamaraComercio camaraComercio = null;  
        try 
        { 
            iniciaOperacion(); 
            camaraComercio = (CamaraComercio) sesion.get(CamaraComercio.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return camaraComercio; 
    }  

    public List<CamaraComercio> obtenListaComercio() throws HibernateException 
    { 
        List<CamaraComercio> listaComercio = null;  

        try 
        { 
            iniciaOperacion(); 
            listaComercio = sesion.createQuery("from Camara_Comercio").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaComercio; 
    }  
    public static void main(String[] args) {
           //mes dia año
        String fecha = "03/22/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
        
        ComercioDao daoco = new ComercioDao();
       // guardar
       // daoco.guardaComercio(new CamaraComercio(987426523, 70000000, fechaV, 35000));
       // actualizar
       // daoco.actualizaComercio(new CamaraComercio(987426523, 70000000, fechaV, 350000));
       // Borrar
       // daoco.eliminaComercio(new CamaraComercio(987426523, 0, fechaV, 350000));
      
    }
}