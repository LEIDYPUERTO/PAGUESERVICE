/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.EAgua;
import com.service.logica.mapeos.EAguaId;
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
public class AguaDao{  
    
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

    public EAguaId guardaAgua(EAgua eAgua) throws HibernateException  { 
       
        EAguaId id ;  

        try 
        { 
            iniciaOperacion();
           id= (EAguaId)sesion.save(eAgua); 
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

    public void actualizaAgua(EAgua eAgua) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(eAgua); 
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

    public void eliminaAgua(EAgua eAgua)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(eAgua); 
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

    public EAgua obtenAgua(EAguaId id) throws HibernateException 
    { 
        EAgua eAgua = null;  
        try 
        { 
            iniciaOperacion(); 
            eAgua = (EAgua) sesion.get(EAgua.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return eAgua; 
    }  

    public List<EAgua> obtenListaAgua(int cedula) throws HibernateException 
    { 
        List<EAgua> listaAgua = null;  

        try 
        { 
            iniciaOperacion(); 
            listaAgua = sesion.createQuery("from EAgua where Cliente_Cedula = "+cedula+"").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaAgua; 
    }  
    
    public List<EAgua> obtenListaAguaFechas(Date date)  throws HibernateException {
          List<EAgua> listaAgua = null;  

        try 
        { 
            iniciaOperacion(); 
            listaAgua = sesion.createQuery("from EAgua where Fecha_Pago_Agua < '"+date+"'").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaAgua; 
    }

       public static void main(String[] args) {
           //mes dia año
        String fecha = "03/25/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
       
          AguaDao daoa = new AguaDao();
          ClienteDao daoc = new ClienteDao();
        // Agregar  
         // daoa.guardaAgua(new EAgua(new EAguaId(78756424,daoc.obtenCliente(108796548).getCedula()), fechaV, 7655977, "calle 78 # 13-145"));
       daoa.obtenListaAgua(1049627764);
          // Actualizar
        //  daoa.actualizaAgua(new EAgua(new EAguaId(78756424, daoc.obtenCliente(108796548).getCedula()), fechaV,120000, "calle 78 # 13-145"));
        // Eliminar
        //  daoa.eliminaAgua(new EAgua(new EAguaId(78756424, 108796548), fechaV, 0, fecha));
          
    }
}