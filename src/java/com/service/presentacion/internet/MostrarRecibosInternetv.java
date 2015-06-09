/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.internet;

import com.service.db.dao.BancoDao;
import com.service.db.dao.InternetvDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.InternetTv;
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
public class MostrarRecibosInternetv {
    
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 10;
    private List<InternetTv> allItems = null;
   // private List<InventoryVendorList> inventoryVendorLists = null;
    private int currentInternetIndex;
    private int page = 1;
    private int clientRows;
 
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        InternetTv internet = allItems.get(currentInternetIndex);
        allItems.remove(internet);
        InternetvDao daog = new InternetvDao();
        daog.eliminaInternetv(internet);
    }
     public void pagar() {
        InternetTv internetTv = allItems.get(currentInternetIndex);
         BancoDao daoB = new BancoDao();
         Banco bank = daoB.obtenBancoByCliente(Comun.getCedula());
        if(bank.getSaldo() >= internetTv.getValorInternet()){
            bank.setSaldo(bank.getSaldo() - internetTv.getValorInternet());
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
    public List<InternetTv> getAllItems() {
        synchronized (this) {
            if (allItems == null) {
                InternetvDao daoi = new InternetvDao();
                allItems = daoi.obtenListaInternetv(Comun.getCedula());
                
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
    public MostrarRecibosInternetv() {
    }

    public int getCurrentInternetIndex() {
        return currentInternetIndex;
    }

    public void setCurrentInternetIndex(int currentInternetIndex) {
        this.currentInternetIndex = currentInternetIndex;
    }

    
}