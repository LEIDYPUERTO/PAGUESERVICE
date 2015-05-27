/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.CreditoLuz;
import com.service.logica.mapeos.CreditoLuzId;
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
public class CrediLuzDao{  
    
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

    public CreditoLuzId guardaCrediLuz(CreditoLuz creditoLuz) throws HibernateException  { 
       
        CreditoLuzId id ;  

        try 
        { 
            iniciaOperacion();
           id= (CreditoLuzId)sesion.save(creditoLuz); 
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

    public void actualizaCrediLuz(CreditoLuz creditoLuz) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(creditoLuz); 
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

    public void eliminaCrediLuz(CreditoLuz creditoLuz)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(creditoLuz); 
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

    public CreditoLuz obtenCrediLuz(CreditoLuzId id) throws HibernateException 
    { 
        CreditoLuz creditoLuz = null;  
        try 
        { 
            iniciaOperacion(); 
            creditoLuz = (CreditoLuz) sesion.get(CreditoLuz.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return creditoLuz; 
    }  

    public List<CreditoLuz> obtenListaCrediLuz() throws HibernateException 
    { 
        List<CreditoLuz> listaCrediLuz = null;  

        try 
        { 
            iniciaOperacion(); 
            listaCrediLuz = sesion.createQuery("from Credito_Luz").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaCrediLuz; 
    }  

       public static void main(String[] args) {
        
          LuzDao daol = new LuzDao();
          CrediLuzDao daocr = new CrediLuzDao();
        // Agregar  
        //  daocr.guardaCrediLuz(new CreditoLuz(new CreditoLuzId(6235847, 8745685), 800000, "televisor"));
        // Actualizar
        //  daocr.actualizaCrediLuz(new CreditoLuz(new CreditoLuzId(6235847, 8745685), 950000, "Televisor LCD"));
        // Eliminar
        //  daocr.eliminaCrediLuz(new CreditoLuz(new CreditoLuzId(6235847, 8745685), 0, fecha));
    }
}