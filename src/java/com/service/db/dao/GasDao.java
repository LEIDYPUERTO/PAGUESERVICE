/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.EGas;
import com.service.logica.mapeos.EGasId;
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
public class GasDao{  
    
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

    public EGasId guardaGas(EGas eGas) throws HibernateException  { 
       
        EGasId id ;  

        try 
        { 
            iniciaOperacion();
           id= (EGasId)sesion.save(eGas); 
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

    public void actualizaGas(EGas eGas) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(eGas); 
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

    public void eliminaGas(EGas eGas)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(eGas); 
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

    public EGas obtenGas(EGasId id) throws HibernateException 
    { 
        EGas eGas = null;  
        try 
        { 
            iniciaOperacion(); 
            eGas = (EGas) sesion.get(EGas.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return eGas; 
    }  

    public List<EGas> obtenListaGas(int cedula) throws HibernateException 
    { 
        List<EGas> listaGas = null;  

        try 
        { 
            iniciaOperacion(); 
            listaGas = sesion.createQuery("from EGas where Cliente_Cedula = "+cedula+"").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaGas; 
    }  

       public static void main(String[] args) {
           //mes dia año
        String fecha = "03/25/2016";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
       
        //  LuzDao daol = new LuzDao();
          GasDao daog = new GasDao();
          ClienteDao daoc = new ClienteDao();
          daog.guardaGas(new EGas(new EGasId(98745852, daoc.obtenCliente(108796548).getCedula()), fechaV, 120000,"calle 98-98-0"));
        //  daol.guardaLuz(new ELuz(new ELuzId(8745685, daoc.obtenCliente(108796548).getCedula()), fechaV, 50000, "calle 87 # 78-45"));
        
       
    }
}
