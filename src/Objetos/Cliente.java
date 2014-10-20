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
public class Cliente {
    
    private int codigo_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private String fecha_nacimiento_cliente;
    private String cuil_prefijo_cliente;
    private String cuil_dni_cliente;
    private String cuil_digito_cliente;
    private String localidad_cliente;
    private String calle_cliente;
    private String numero_calle_cliente;
    private int codigo_situacion_IVA_cliente;
    private String descripcion_situacion_IVA_cliente;

    public Cliente(int codigo_cliente, String nombre_cliente, String apellido_cliente, String fecha_nacimiento_cliente, String cuil_prefijo_cliente, String cuil_dni_cliente, String cuil_digito_cliente, String localidad_cliente, String calle_cliente, String numero_calle_cliente, int codigo_situacion_IVA_cliente, String descripcion_situacion_IVA_cliente) {
        this.codigo_cliente = codigo_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
        this.cuil_prefijo_cliente = cuil_prefijo_cliente;
        this.cuil_dni_cliente = cuil_dni_cliente;
        this.cuil_digito_cliente = cuil_digito_cliente;
        this.localidad_cliente = localidad_cliente;
        this.calle_cliente = calle_cliente;
        this.numero_calle_cliente = numero_calle_cliente;
        this.codigo_situacion_IVA_cliente = codigo_situacion_IVA_cliente;
        this.descripcion_situacion_IVA_cliente = descripcion_situacion_IVA_cliente;
    }

    public Cliente() {
        this.codigo_cliente = 0;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public String getFecha_nacimiento_cliente() {
        return fecha_nacimiento_cliente;
    }

    public String getCuil_prefijo_cliente() {
        return cuil_prefijo_cliente;
    }

    public String getCuil_dni_cliente() {
        return cuil_dni_cliente;
    }

    public String getCuil_digito_cliente() {
        return cuil_digito_cliente;
    }

    public String getLocalidad_cliente() {
        return localidad_cliente;
    }

    public String getCalle_cliente() {
        return calle_cliente;
    }

    public String getNumero_calle_cliente() {
        return numero_calle_cliente;
    }

    public int getCodigo_situacion_IVA_cliente() {
        return codigo_situacion_IVA_cliente;
    }

    public String getDescripcion_situacion_IVA_cliente() {
        return descripcion_situacion_IVA_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public void setFecha_nacimiento_cliente(String fecha_nacimiento_cliente) {
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    public void setCuil_prefijo_cliente(String cuil_prefijo_cliente) {
        this.cuil_prefijo_cliente = cuil_prefijo_cliente;
    }

    public void setCuil_dni_cliente(String cuil_dni_cliente) {
        this.cuil_dni_cliente = cuil_dni_cliente;
    }

    public void setCuil_digito_cliente(String cuil_digito_cliente) {
        this.cuil_digito_cliente = cuil_digito_cliente;
    }

    public void setLocalidad_cliente(String localidad_cliente) {
        this.localidad_cliente = localidad_cliente;
    }

    public void setCalle_cliente(String calle_cliente) {
        this.calle_cliente = calle_cliente;
    }

    public void setNumero_calle_cliente(String numero_calle_cliente) {
        this.numero_calle_cliente = numero_calle_cliente;
    }

    public void setCodigo_situacion_IVA_cliente(int codigo_situacion_IVA_cliente) {
        this.codigo_situacion_IVA_cliente = codigo_situacion_IVA_cliente;
    }

    public void setDescripcion_situacion_IVA_cliente(String descripcion_situacion_IVA_cliente) {
        this.descripcion_situacion_IVA_cliente = descripcion_situacion_IVA_cliente;
    }  

    @Override
    public String toString() {
        return "Cliente{" + "codigo_cliente=" + codigo_cliente + ", nombre_cliente=" + nombre_cliente + ", apellido_cliente=" + apellido_cliente + ", fecha_nacimiento_cliente=" + fecha_nacimiento_cliente + ", cuil_prefijo_cliente=" + cuil_prefijo_cliente + ", cuil_dni_cliente=" + cuil_dni_cliente + ", cuil_digito_cliente=" + cuil_digito_cliente + ", localidad_cliente=" + localidad_cliente + ", calle_cliente=" + calle_cliente + ", numero_calle_cliente=" + numero_calle_cliente + ", codigo_situacion_IVA_cliente=" + codigo_situacion_IVA_cliente + ", descripcion_situacion_IVA_cliente=" + descripcion_situacion_IVA_cliente + '}';
    } 
    
}
