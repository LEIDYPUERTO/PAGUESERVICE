/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.agua;

import java.sql.Date;
import java.util.Calendar;


/**
 *
 * @author YEFFERSON FABIAN
 */
public class Fecha {
    
    public static Date fecha(int suma){
        Calendar sumfecha = Calendar.getInstance();
        int dia=sumfecha.get(Calendar.DAY_OF_MONTH);
        int mes=sumfecha.get(Calendar.MONTH)+1;
        int ano=sumfecha.get(Calendar.YEAR);
        System.out.println(dia +" "+mes+" "+ano);
        dia+= suma; 
        System.out.println(dia);
        if(mes == 2  ){
            if(ano % 4 == 0 ){
                if(dia > 29){
                    dia -= 29;
                    mes++;
                }
            }else{
                 if(dia > 28){
                    dia -= 28;
                    mes++;
                }
            }
        }else{
            
                if(mes == 1 || mes == 3 || mes ==5 || mes == 7 || mes == 8 || mes == 10 || mes ==12){
                    if(dia > 31){
                        dia-= 31;
                        mes++;
                        if(mes >12){
                            mes = 1;
                            ano += 1;
                        }
                    }
                }else{
                      if(dia > 30){
                        dia-= 30;
                        mes++;
                    }
                }
            
        }
        
        System.out.println(dia +" "+mes+" "+ano);
        
        
        return new Date(ano -1900,mes- 1,dia);
        
    }
    
}
