/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import Clases_Auxiliares.Conexion;

/**
 *
 * @author Banegas Rodrigo
 */
public class Usuario {
    private String nombre,apellido,usuario,contraseña;
    private Perfil perfil;    
    private boolean existe;
    
    public Usuario(){        
        this.nombre="";
        this.apellido="";
        this.usuario="";
        this.contraseña="";
        this.existe=true;        
    }
    
    public void setNombre(String n){
        nombre=n;
    }
    public void setApellido(String a){
        apellido=a;
    }
    public void setUsuario(String u){
        usuario=u;
    }
    public void setContraseña(String c){
        contraseña=c;
    }
    public void setPerfil(Perfil p){
        perfil=p;
    }
    public void setExiste(boolean e){
        existe=e;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getUsuario(){
        return usuario;
    }
    public String getContraseña(){
        return contraseña;
    }
    public Perfil getIdPerfil(){
        return perfil;
    }
    public boolean getExiste(){
        return existe;
    }                 
    
}
