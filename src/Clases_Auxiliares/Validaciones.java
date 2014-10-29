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


public boolean isCuit(String t,String num,String DV){
    int tipo=Integer.parseInt(t);
    int numero=Integer.parseInt(num);        
    int totalTipo=(tipo%10)*4;
    tipo=tipo/10;
    totalTipo+=((tipo%10)*5);                        
    int totalNum=0;
    for(int i=2;i<8;i++){
        totalNum+=((numero%10)*i);
        numero=numero/10;
    }       
    totalNum+=((numero%10)*2);
    numero=numero/10;
    totalNum+=((numero%10)*3);        
        
    int suma=totalNum+totalTipo;
    int resto=suma%11;     
    int dv=-1;
    if(resto==0){
        dv=0;
    }
    else
    {
        int complemento=11-resto;            
        if(complemento==10){
            dv=9;                
        }
        else
        {
            dv=complemento;                
        }
    }        
    int auxDev=Integer.parseInt(DV);
    return dv==auxDev;   
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
