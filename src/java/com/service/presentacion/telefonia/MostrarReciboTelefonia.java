/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.telefonia;

import com.service.db.dao.BancoDao;
import com.service.db.dao.TelefoniaDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.Telefonia;
import com.service.presentacion.cliente.Comun;
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
public class MostrarReciboTelefonia {
    
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 10;
    private List<Telefonia> allItems = null;
   // private List<InventoryVendorList> inventoryVendorLists = null;
    private int currentTelefoniaIndex;
    private int page = 1;
    private int clientRows;
 
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        Telefonia telefonia = allItems.get(currentTelefoniaIndex);
        allItems.remove(telefonia);
        TelefoniaDao daot = new TelefoniaDao();
        daot.eliminaTelefonia(telefonia);
    }
     public void pagar() {
        Telefonia telefonia = allItems.get(currentTelefoniaIndex);
         BancoDao daoB = new BancoDao();
         Banco bank = daoB.obtenBancoByCliente(Comun.getCedula());
        if(bank.getSaldo() >= telefonia.getValorTelefonia()){
            bank.setSaldo(bank.getSaldo() - telefonia.getValorTelefonia());
            daoB.actualizaBanco(bank);
            
        }
        
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    public int getClientRows() {
        return clientRows;
    }

    public void setClientRows(int clientRows) {
        this.clientRows = clientRows;
    }
    public List<Telefonia> getAllItems() {
        synchronized (this) {
            if (allItems == null) {
                TelefoniaDao daot = new TelefoniaDao();
                allItems = daot.obtenListaTelefonia(Comun.getCedula());
                
            }
            
        }
        return allItems;
    }
//    public static void main(String[] args) {
//        MostrarReciboAgua agua = new MostrarReciboAgua();
//        Comun.setCedula(1049627764);
//        System.out.println(agua.getAllItems().size());
//        
//    }
    /**
     * Creates a new instance of MostrarReciboAgua
     */
    public MostrarReciboTelefonia() {
    }

    public int getCurrentTelefoniaIndex() {
        return currentTelefoniaIndex;
    }

    public void setCurrentTelefoniaIndex(int currentTelefoniaIndex) {
        this.currentTelefoniaIndex = currentTelefoniaIndex;
    }
    
    
    
}
