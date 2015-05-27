/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.BancoId;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author user
 */
public class BancoDao{  
    
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

    public BancoId guardaBanco(Banco banco) throws HibernateException  { 
       
        BancoId id ;  

        try 
        { 
            iniciaOperacion();
           id= (BancoId)sesion.save(banco); 
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

    public void actualizaBanco(Banco banco) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(banco); 
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

    public void eliminaBanco(Banco banco)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(banco); 
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

    public Banco obtenBanco(BancoId id) throws HibernateException 
    { 
        Banco banco = null;  
        try 
        { 
            iniciaOperacion(); 
            banco = (Banco) sesion.get(Banco.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return banco; 
    }  

    public List<Banco> obtenListaBanco() throws HibernateException 
    { 
        List<Banco> listaBanco = null;  

        try 
        { 
            iniciaOperacion(); 
            listaBanco = sesion.createQuery("from Banco").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaBanco; 
    }  

       public static void main(String[] args) {
        
          BancoDao daob = new BancoDao();
          ClienteDao daoc = new ClienteDao();
          daob.guardaBanco(new Banco(new BancoId(7845692,daoc.obtenCliente(108796548).getCedula()), 80000,"Bancolombia"));
       
    }
}