/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

/**
 *
 * @author Banegas Rodrigo
 */
public class Permiso {
    private int perfil,modulo,tarea;
    
    public Permiso(int p,int m,int t){
        perfil=p;modulo=m;tarea=t;
    }
    
    public int getPerfil(){
        return perfil;
    }
    public int getTarea(){
        return tarea;
    }
    public int getModulo(){
        return modulo;
    }
        
    
}
