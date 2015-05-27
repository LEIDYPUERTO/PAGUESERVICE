/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.CrediGas;
import com.service.logica.mapeos.CrediGasId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class CrediGasDao{  
    
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

    public CrediGasId guardaCrediGas(CrediGas crediGas) throws HibernateException  { 
       
        CrediGasId id ;  

        try 
        { 
            iniciaOperacion();
           id= (CrediGasId)sesion.save(crediGas); 
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

    public void actualizaCrediGas(CrediGas crediGas) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(crediGas); 
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

    public void eliminaCrediGas(CrediGas crediGas)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(crediGas); 
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

    public CrediGas obtenCrediGas(CrediGasId id) throws HibernateException 
    { 
        CrediGas crediGas = null;  
        try 
        { 
            iniciaOperacion(); 
            crediGas = (CrediGas) sesion.get(CrediGas.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return crediGas; 
    }  

    public List<CrediGas> obtenListaCrediGas() throws HibernateException 
    { 
        List<CrediGas> listaCrediGas = null;  

        try 
        { 
            iniciaOperacion(); 
            listaCrediGas = sesion.createQuery("from Credi_Gas").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaCrediGas; 
    }  

       public static void main(String[] args) {
     
          GasDao daog = new GasDao();
          CrediGasDao daocg = new CrediGasDao();
        // Agregar  
        //  daocg.guardaCrediGas(new CrediGas(new CrediGasId(8612035, 98745852), 450000, "Chimenea"));
        // Actualizar
        //  daocg.actualizaCrediGas(new CrediGas(new CrediGasId(8612035, 98745852), 1200000,"chimenea a gas natural"));
        // Eliminar
        //  daocg.eliminaCrediGas(new CrediGas(new CrediGasId(8612035, 98745852), 0, null));
    }
}
