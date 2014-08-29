/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import Clases_Auxiliares.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manolo
 */
public class Asiento {
    private int num_asiento;
    private int num_renglon;
    private String fecha_contable,fechaOperacion,fechaVencimiento;
    private String tipo;
    private int num_cuenta;
    private String comprobante;
    private String leyenda;
    private float debe,haber;
    private boolean okCarga,okRegistrado;

    public Asiento(int num_asiento, int num_renglon, String fecha_contable, String fechaOperacion, String fechaVencimiento, String tipo, int num_cuenta, String comprobante, String leyenda, float debe, float haber, boolean okCarga, boolean okRegistrado) {
        this.num_asiento = num_asiento;
        this.num_renglon = num_renglon;
        this.fecha_contable = fecha_contable;
        this.fechaOperacion = fechaOperacion;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.num_cuenta = num_cuenta;
        this.comprobante = comprobante;
        this.leyenda = leyenda;
        this.debe = debe;
        this.haber = haber;
        this.okCarga = okCarga;
        this.okRegistrado = okRegistrado;
    }

    public Asiento(){    
    }
    
    public void setNum_asiento(int num_asiento) {
        this.num_asiento = num_asiento;
    }

    public void setNum_renglon(int num_renglon) {
        this.num_renglon = num_renglon;
    }

    public void setFecha_contable(String fecha_contable) {
        this.fecha_contable = fecha_contable;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNum_cuenta(int num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public void setDebe(float debe) {
        this.debe = debe;
    }

    public void setHaber(float haber) {
        this.haber = haber;
    }

    public void setOkCarga(boolean okCarga) {
        this.okCarga = okCarga;
    }

    public void setOkRegistrado(boolean okRegistrado) {
        this.okRegistrado = okRegistrado;
    }
    
    
    public int getNum_asiento() {
        return num_asiento;
    }

    public int getNum_renglon() {
        return num_renglon;
    }

    public String getFecha_contable() {
        return fecha_contable;
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNum_cuenta() {
        return num_cuenta;
    }

    public String getComprobante() {
        return comprobante;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public float getDebe() {
        return debe;
    }

    public float getHaber() {
        return haber;
    }

    public boolean isOkCarga() {
        return okCarga;
    }

    public boolean isOkRegistrado() {
        return okRegistrado;
    }
    
    public void insertar(Conexion r_con){
        r_con.Connection();
        int reg=0;int carga=0;
        if(okRegistrado)
            reg=1;       
        if(okCarga)
            carga=1;
        String cadena=num_asiento+","+num_renglon+",'"+fecha_contable+"','"+tipo+"',"+num_cuenta+",'"+
                fechaOperacion+"','"+fechaVencimiento+"','"+comprobante+"','"+leyenda+"',"+debe+","+haber+","+
                carga+","+reg;        
        r_con.Insertar("insert into borrador_asientos values ("+cadena+")");
        r_con.cierraConexion();
    }
    
    public boolean isBalanceo(int numAsiento,Conexion r_con){
        try {
            r_con.Connection();
            ResultSet rs=r_con.Consultar("select ba_debe,ba_haber from borrador_asientos where ba_nro_asiento="+numAsiento);
            float debe=0;
            float haber=0;
            while(rs.next()){
                debe+=rs.getFloat(1);
                haber+=rs.getFloat(2);
            }
            if(debe-haber==0)
                return true;
            r_con.cierraConexion();
        } catch (SQLException ex) {
            r_con.cierraConexion();
            Logger.getLogger(Asiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
}
