/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.agua;

import com.service.db.dao.AguaDao;
import com.service.db.dao.BancoDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.EAgua;
import com.service.presentacion.cliente.Comun;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author YEFFERSON FABIAN
 */
@ManagedBean
@RequestScoped
public class MostrarReciboAgua {
    
    private static final int DECIMALS = 1;
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 10;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    private List<EAgua> allItems = null;
   // private List<InventoryVendorList> inventoryVendorLists = null;
    private int currentAguaIndex;
    //private InventoryItem edite;
    private EAgua edite;
    private int page = 1;
    private int clientRows;
 
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        EAgua agua = allItems.get(currentAguaIndex);
        allItems.remove(agua);
        AguaDao dao = new AguaDao();
        dao.eliminaAgua(agua);
    }
     public void pagar() {
        EAgua agua = allItems.get(currentAguaIndex);
         BancoDao daoB = new BancoDao();
         Banco bank = daoB.obtenBancoByCliente(Comun.getCedula());
        if(bank.getSaldo() >= agua.getValorAgua()){
            bank.setSaldo(bank.getSaldo() - agua.getValorAgua());
            daoB.actualizaBanco(bank);
            
        }
        
    }
    public void store() {
        allItems.set(currentAguaIndex, edite);
    }

   
    public void setAllItems(List<EAgua> allItems) {
        this.allItems = allItems;
    }

   

    public EAgua getEdite() {
        return edite;
    }

    public void setEdite(EAgua edite) {
        this.edite = edite;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrentAguaIndex() {
        return currentAguaIndex;
    }

    public void setCurrentAguaIndex(int currentAguaIndex) {
        this.currentAguaIndex = currentAguaIndex;
    }
    
    public int getClientRows() {
        return clientRows;
    }

    public void setClientRows(int clientRows) {
        this.clientRows = clientRows;
    }
    public List<EAgua> getAllItems() {
        synchronized (this) {
            if (allItems == null) {
                AguaDao dao = new AguaDao();
                allItems = dao.obtenListaAgua(Comun.getCedula());
                
            }
            
        }
        return allItems;
    }
    public static void main(String[] args) {
        MostrarReciboAgua agua = new MostrarReciboAgua();
        Comun.setCedula(1049627764);
        System.out.println(agua.getAllItems().size());
        
    }
    /**
     * Creates a new instance of MostrarReciboAgua
     */
    public MostrarReciboAgua() {
    }
    
}
