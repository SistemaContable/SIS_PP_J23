/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

/**
 *
 * @author Manolo
 */
public class Cuenta {

    private String codigo_PC;
    private int numero_C;
    private String nombre_C;
    private boolean imputable_C;
    private int numero_padre_C;

    public Cuenta(){
    }

    public Cuenta(String codigo_PC, int numero_C, String nombre_C, boolean imputable_C, int numero_padre_C) {
        this.codigo_PC = codigo_PC;
        this.numero_C = numero_C;
        this.nombre_C = nombre_C;
        this.imputable_C = imputable_C;
        this.numero_padre_C = numero_padre_C;
    }
    
  

    public String getCodigo_PC() {
        return codigo_PC;
    }

    public int getNumero_C() {
        return numero_C;
    }

    public String getNombre_C() {
        return nombre_C;
    }

    public boolean isImputable_C() {
        return imputable_C;
    }

    public int getNumero_Padre_C() {
        return numero_padre_C;
    }    

    public void setCodigo_PC(String codigo_PC) {
        this.codigo_PC = codigo_PC;
    }

    public void setNumero_C(int numero_C) {
        this.numero_C = numero_C;
    }

    public void setNombre_C(String nombre_C) {
        this.nombre_C = nombre_C;
    }

    public void setImputable_C(boolean imputable_C) {
        this.imputable_C = imputable_C;
    }

    public void setNumero_Padre_C(int numero_padre_C) {
        this.numero_padre_C = numero_padre_C;
    }
    
    
    @Override
    public String toString() {
        return (codigo_PC + " - " + nombre_C);
    }
    
    
    
}
