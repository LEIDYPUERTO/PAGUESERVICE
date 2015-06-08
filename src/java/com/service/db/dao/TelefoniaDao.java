/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.Telefonia;
import com.service.logica.mapeos.TelefoniaId;
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
public class TelefoniaDao{  
    
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

    public TelefoniaId guardaTelefonia(Telefonia telefonia) throws HibernateException  { 
       
        TelefoniaId id ;  

        try 
        { 
            iniciaOperacion();
           id= (TelefoniaId)sesion.save(telefonia); 
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

    public void actualizaTelefonia(Telefonia telefonia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(telefonia); 
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

    public void eliminaTelefonia(Telefonia telefonia)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(telefonia); 
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

    public Telefonia obtenTelefonia(TelefoniaId id) throws HibernateException 
    { 
        Telefonia telefonia = null;  
        try 
        { 
            iniciaOperacion(); 
            telefonia = (Telefonia) sesion.get(Telefonia.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return telefonia; 
    }  

    public List<Telefonia> obtenListaTelefonia(int cedula) throws HibernateException 
    { 
        List<Telefonia> listaTelefonia = null;  

        try 
        { 
            iniciaOperacion(); 
            listaTelefonia = sesion.createQuery("from Telefonia where Cliente_Cedula = "+cedula+"").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTelefonia; 
    }  

       public static void main(String[] args) {
           //mes dia año
        String fecha = "04/23/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
       
          TelefoniaDao daot = new TelefoniaDao();
          ClienteDao daoc = new ClienteDao();
        // Agregar
          daot.guardaTelefonia(new Telefonia(new TelefoniaId(654258, daoc.obtenCliente(108796548).getCedula()), fechaV, 12000,"Prueba"));
        // Actualizar
        //  daot.actualizaTelefonia(new Telefonia(new TelefoniaId(654258, daoc.obtenCliente(108796548).getCedula()), fechaV, 15000, "Comuna 13"));
        // Eliminar
        //  daot.eliminaTelefonia(new Telefonia(new TelefoniaId(654258, daoc.obtenCliente(108796548).getCedula()), fechaV, 0, fecha));
        //  daoi.eliminaInternetv(new InternetTv(new InternetTvId(77585, 108796548), fechaV, 0, fecha));
          
    }
}