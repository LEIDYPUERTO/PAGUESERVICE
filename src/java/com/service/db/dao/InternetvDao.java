/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.InternetTv;
import com.service.logica.mapeos.InternetTvId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;

/**
 *
 * @author user
 */
public class InternetvDao{  
    
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

    public InternetTvId guardaInternetv(InternetTv internetTv) throws HibernateException  { 
       
        InternetTvId id ;  

        try 
        { 
            iniciaOperacion();
           id= (InternetTvId)sesion.save(internetTv); 
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

    public void actualizaInternetv(InternetTv internetTv) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(internetTv); 
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

    public void eliminaInternetv(InternetTv internetTv)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(internetTv); 
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

    public InternetTv obtenInternetv(InternetTvId id) throws HibernateException 
    { 
        InternetTv internetTv = null;  
        try 
        { 
            iniciaOperacion(); 
            internetTv = (InternetTv) sesion.get(InternetTv.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return internetTv; 
    }  

    public List<InternetTv> obtenListaInternetv(int cedula) throws HibernateException 
    { 
        List<InternetTv> listaInternetv = null;  

        try 
        { 
            iniciaOperacion(); 
            listaInternetv = sesion.createQuery("from InternetTv where Cliente_Cedula = "+cedula+"").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaInternetv; 
    }
    public List<InternetTv> obtenListaInternetFechas(Date date)  throws HibernateException {
          List<InternetTv> listaInternet = null;  

        try 
        { 
            iniciaOperacion(); 
            listaInternet = sesion.createQuery("from InternetTv where Fecha_Pago_Internet < '"+date+"'").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaInternet; 
    }

       public static void main(String[] args) {
           //mes dia año
        String fecha = "03/25/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
       
          InternetvDao daoi = new InternetvDao();
          ClienteDao daoc = new ClienteDao();
        // Agregar  
          daoi.guardaInternetv(new InternetTv(new InternetTvId(77585,daoc.obtenCliente(108796548).getCedula()), fechaV, 35000, "calle sur 45-48"));
        // Actualizar
        //  daoi.actualizaInternetv(new InternetTv(new InternetTvId(77585, daoc.obtenCliente(108796548).getCedula()), fechaV, 12000, "comuna 12 3-85"));
        // Eliminar
        //  daoi.eliminaInternetv(new InternetTv(new InternetTvId(77585, 108796548), fechaV, 0, fecha));
          
    }
}