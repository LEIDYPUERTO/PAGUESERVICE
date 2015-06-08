/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.gas;

import com.service.db.dao.BancoDao;
import com.service.db.dao.GasDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.EGas;
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
public class MostrarReciboGas {
    
    private static final int CLIENT_ROWS_IN_AJAX_MODE = 10;
    private List<EGas> allItems = null;
   // private List<InventoryVendorList> inventoryVendorLists = null;
    private int currentGasIndex;
    private int page = 1;
    private int clientRows;
 
    public void switchAjaxLoading(ValueChangeEvent event) {
        this.clientRows = (Boolean) event.getNewValue() ? CLIENT_ROWS_IN_AJAX_MODE : 0;
    }
 
    public void remove() {
        EGas gas = allItems.get(currentGasIndex);
        allItems.remove(gas);
        GasDao daog = new GasDao();
        daog.eliminaGas(gas);
    }
     public void pagar() {
        EGas gas = allItems.get(currentGasIndex);
         BancoDao daoB = new BancoDao();
         Banco bank = daoB.obtenBancoByCliente(Comun.getCedula());
        if(bank.getSaldo() >= gas.getValorGas()){
            bank.setSaldo(bank.getSaldo() - gas.getValorGas());
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
    public List<EGas> getAllItems() {
        synchronized (this) {
            if (allItems == null) {
                GasDao daog = new GasDao();
                allItems = daog.obtenListaGas(Comun.getCedula());
                
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
    public MostrarReciboGas() {
    }

    public int getCurrentGasIndex() {
        return currentGasIndex;
    }

    public void setCurrentGasIndex(int currentGasIndex) {
        this.currentGasIndex = currentGasIndex;
    }
}
