/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.db.dao;

import com.service.logica.mapeos.Cliente;
import com.service.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * 
 */
public class ClienteDao{  
    
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

    public int guardaCliente(Cliente cliente) throws HibernateException  { 
       
         int id = 0;  
         try 
        { 
           iniciaOperacion();
           id= (Integer)sesion.save(cliente); 
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

    public void actualizacliente(Cliente cliente) throws HibernateException { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(cliente); 
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

    public void eliminaCliente(Cliente cliente)throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(cliente); 
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

    public Cliente obtenCliente(int id) throws HibernateException 
    { 
        Cliente cliente = null;  
        try 
        { 
            iniciaOperacion(); 
            cliente = (Cliente) sesion.get(Cliente.class, id); 
        } finally 
        { 
            sesion.close(); 
        }  

        return cliente; 
    }  

    public List<Cliente> obtenListaClientes() throws HibernateException 
    { 
        List<Cliente> listaClientes = null;  

        try 
        { 
            iniciaOperacion(); 
            listaClientes = sesion.createQuery("from Cliente").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
    }  
    public static void main(String[] args) {
        ClienteDao dao = new ClienteDao();
      // Borrar 
      //  dao.eliminaCliente(new Cliente(1077852654, null, null, null));
      // actualizar
      // dao.actualizacliente(new Cliente(1077852654, "Juana", "F", "Pera"));
      // guardar
        //dao.guardaCliente(new Cliente(108554, "Pedro llll","M", "Manzana"));
       dao.obtenListaClientes();
    }
}
