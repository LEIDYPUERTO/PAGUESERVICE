/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.luz;

import com.service.db.dao.BancoDao;
import com.service.db.dao.LuzDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.ELuz;
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
public class MostrarReciboLuz {
    
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 10;
    private List<ELuz> allItems = null;
   // private List<InventoryVendorList> inventoryVendorLists = null;
    private int currentLuzIndex;
    private int page = 1;
    private int clientRows;
 
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        ELuz luz = allItems.get(currentLuzIndex);
        allItems.remove(luz);
        LuzDao daol = new LuzDao();
        daol.eliminaLuz(luz);
    }
     public void pagar() {
        ELuz luz = allItems.get(currentLuzIndex);
         BancoDao daoB = new BancoDao();
         Banco bank = daoB.obtenBancoByCliente(Comun.getCedula());
        if(bank.getSaldo() >= luz.getValorLuz()){
            bank.setSaldo(bank.getSaldo() - luz.getValorLuz());
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
    public List<ELuz> getAllItems() {
        synchronized (this) {
            if (allItems == null) {
                LuzDao daol = new LuzDao();
                allItems = daol.obtenListaLuz(Comun.getCedula());
                
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
    public MostrarReciboLuz() {
    }

    public int getCurrentLuzIndex() {
        return currentLuzIndex;
    }

    public void setCurrentLuzIndex(int currentLuzIndex) {
        this.currentLuzIndex = currentLuzIndex;
    }
    
}