/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases_Auxiliares;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Manolo
 */
public class Fechas {

    public Fechas() {
    
    } 
    
    /**
     * validar si una fecha que cumple con el formato dd/MM/yyyy (dia/mes/año) es Valida
     * @param fecha Dia/Mes/Año
     * @return 
     */
    public boolean isFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    /**
     * Este metodo es utilizado para comprobar que la fecha contable este dentro de la fecha de inicio y cierre
     * de la empresa
     * @param fecha que deseamos ingresar
     * @param fechaI fecha Inicio contabilidad
     * @param fechaC fecha Cierre contabilidad
     * @return true si pertenece al intevalo de fecha de inicio y cierre de la empresa
     */
    public boolean fechaEntreFechas(String fecha,String fechaI,String fechaC){
        if((menorFechas(fecha,fechaI)==2)||(menorFechas(fecha,fechaI)==0)){
            if((menorFechas(fecha,fechaC)==1)||(menorFechas(fecha,fechaC)==0)){
                    return true;
            }
        }
        return false;
    }
    
    
    
    /**
     * obtener el dia de hoy en un String con formato dd/MM/yyyy (dia/mes/año)
     * @return 
     */
    public String getHoy (){
        Date hoy = new Date();
        SimpleDateFormat formatEntrada = new SimpleDateFormat("dd/MM/yyyy"); 
        String fecha_hoy = formatEntrada.format(hoy);
        return fecha_hoy;
    }
    
    /**
    * Permite convertir un String en fecha (Date).
    * @param fecha Cadena de fecha dd/MM/yyyy
    * @return Objeto Date
    */
    public Date parseFecha (String fecha){
       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       Date fechaDate = null;
       try {
           fechaDate = formato.parse(fecha);
       } 
       catch (ParseException ex) 
       {
           System.out.println(ex);
       }
       return fechaDate;
   }
    
    /**
    * Permite convertir una fecha (Date) en un String dd/MM/yyyy.
    * @param dat objeto Date
    * @return String Fecha dd/MM/yyyy
    */
    public String parseFecha (Date dat){
       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       String fecha_hoy = formato.format(dat);
       return fecha_hoy;
   }
    
    
    /** 
    * Agrega o quita minutos a una fecha dada. Para quitar minutos hay que 
    * sumarle valores negativos. 
    * 
    * @param date 
    * @param minutes 
    * @return 
    */  
   public Date addMinutesToDate(Date date, int minutes) {  
       Calendar calendarDate = Calendar.getInstance();  
       calendarDate.setTime(date);  
       calendarDate.add(Calendar.MINUTE, minutes);  
       return calendarDate.getTime();  
   } 
   
   
   /** 
    * Agrega o quita dias a una fecha dada. Para quitar dias hay que sumarle 
    * valores negativos. 
    * 
    * @param date 
    * @param days 
    * @return 
    */  
   public Date addDaysToDate(Date date, int days) {  
       return addMinutesToDate(date, 60 * 24 * days);  
   } 
   
  
   /** 
    * Agrega o quita dias a una fecha dada en String dd/MM/yyyy. Para quitar dias
    * hay que sumarle valores negativos. 
    * 
    * @param fecha  String dd/MM/yyyy
    * @param days 
    * @return fecha nueva en String dd/MM/yyyy
    */ 
   public String addDaysToDate (String fecha, int days){
       //pequeño truco utilizando la misma libreria
       Date fecha_date = this.parseFecha(fecha);            
       Date fecha_nueva = this.addDaysToDate(fecha_date, days);       
       return this.parseFecha(fecha_nueva);       
   }
   
   
   /**
    * Compara y dice la menor de 2 fechas pasadas como String con formato dd/MM/yyyy
    * @param fecha1 String dd/MM/yyyy
    * @param fecha2 String dd/MM/yyyy
    * @return devuelve 1 si la primera es la menor, 2 si la segunda y 0 si son iguales
    */
   public int menorFechas (String fecha1, String fecha2) {  
       int menor = -1;
       try {
            /**Obtenemos las fechas enviadas en el formato a comparar*/
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fecha2);           

            if (fechaDate1.before(fechaDate2) ){
               menor = 1;
            }
            else{
                if (fechaDate2.before(fechaDate1) ){
                   menor= 2;
                }
                else{
                   menor = 0;
                } 
            }
        } catch (ParseException e) {
            System.out.println("Se Produjo un Error!!!  "+e.getMessage());
        }
       
       return menor;
   }
   
   public String primerDiadelAño (){
       Date hoy = new Date();
       SimpleDateFormat formato = new SimpleDateFormat("yyyy");
       String year = formato.format(hoy);
       return ("01/01/"+year);       
   } 
   
   public String ultimoDiadelAño (){
       Date hoy = new Date();
       SimpleDateFormat formato = new SimpleDateFormat("yyyy");
       String year = formato.format(hoy);
       return ("31/12/"+year);       
   } 
       
   

   
}
