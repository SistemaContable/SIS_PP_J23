/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases_Auxiliares;

/**
 *
 * @author Manolo
 */
public class Validaciones {
    
    public Validaciones (){
    }
    
     /*
     * Limpia espacios redundantes, tanto al princicio, al final, como intermedio
     */
    public String limpiarEspacios (String texto){
        texto=texto.trim();    
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(texto);
        texto = "";
        //evitar espacios redundantes intermedios
        while(tokens.hasMoreTokens()){
            texto += " "+tokens.nextToken();
        }
        texto = texto.toString();
        texto = texto.trim();
        return (texto);
    }
    
    /*
     * Convierte una cadena en Palabras donde la 1º letra es solo mayuscula
     * ej: pepe ARGENTO >>> Pepe Argento
     */    
    public String soloPrimerMayus (String texto){
        //paso la primer letra a matuscula y el resto a minuscula   
        if(texto.length()>0){
                char primero=Character.toUpperCase(texto.charAt(0));               
                texto=primero+(texto.substring(1, texto.length()).toLowerCase());                    
        }
        //convierto del resto de la cadena la primer letra de cada palabra a mayuscula 
        int i=0;
        while (i < texto.length()){             
            if (texto.charAt(i) == ' '){
                i++;
                char carac=Character.toUpperCase(texto.charAt(i));
                texto=texto.substring(0,i)+carac+texto.substring(i+1,texto.length());
             }
             i++;
        }
        return(texto);
    }
    
public  boolean isInt(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
 
public boolean isFloat (String cadena){
	try {
		Float.parseFloat(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}

 
public  boolean isLong(String cadena){
	try {
		Long.parseLong(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}   
}
