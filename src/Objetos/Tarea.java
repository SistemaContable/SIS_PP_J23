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
public class Tarea {
    private String descripcion;
    private int id;
    
    public Tarea(){ 
        this.descripcion="";
        this.id=-1;
    }
    
    public void setId(int i){
        id=i;
    }
    public void setDescripcion(String d){
        descripcion=d;
    }
    public int getId(){
        return id;
    }
    public String gerDescripcion(){
        return descripcion;
    }
    
}
