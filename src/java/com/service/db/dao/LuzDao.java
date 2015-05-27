/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.ELuz;
import com.service.logica.mapeos.ELuzId;
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
public class LuzDao{  
    
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

    public ELuzId guardaLuz(ELuz eLuz) throws HibernateException  { 
       
        ELuzId id ;  

        try 
        { 
            iniciaOperacion();
           id= (ELuzId)sesion.save(eLuz); 
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

    public void actualizaLuz(ELuz eLuz) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(eLuz); 
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

    public void eliminaLuz(ELuz eLuz)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(eLuz); 
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

    public ELuz obtenLuz(ELuzId id) throws HibernateException 
    { 
        ELuz eLuz = null;  
        try 
        { 
            iniciaOperacion(); 
            eLuz = (ELuz) sesion.get(ELuz.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return eLuz; 
    }  

    public List<ELuz> obtenListaLuz() throws HibernateException 
    { 
        List<ELuz> listaLuz = null;  

        try 
        { 
            iniciaOperacion(); 
            listaLuz = sesion.createQuery("from E_Luz").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaLuz; 
    }  

       public static void main(String[] args) {
           //mes dia año
        String fecha = "03/25/2016";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
       
          //AguaDao daoa = new AguaDao();
          LuzDao daol = new LuzDao();
          ClienteDao daoc = new ClienteDao();
          daol.guardaLuz(new ELuz(new ELuzId(8745685, daoc.obtenCliente(108796548).getCedula()), fechaV, 50000, "calle 87 # 78-45"));
          //daoa.guardaAgua(new EAgua(new EAguaId(98756424,daoc.obtenCliente(108796548).getCedula()), fechaV, 7655977, "calle 5 # 3-45"));
        
       
    }
}