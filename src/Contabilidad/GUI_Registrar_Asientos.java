/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Contabilidad;

import Interface.*;
import Clases_Auxiliares.Conexion;
import Clases_Auxiliares.Fechas;
import Objetos.Usuario;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Manolo
 */
public class GUI_Registrar_Asientos extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUI_A_Prod
     */
    private Conexion r_con;
    private Usuario usr;
    private Fechas fecha=new Fechas();
    private boolean fecha1Correcta;
    
    public GUI_Registrar_Asientos(Usuario u,Conexion c) {
        initComponents();
        r_con=c;
        usr=u;
        cargarCampos();
        campoFecha1.setEnabled(false);
        campoFecha2.setEnabled(false);
        jLabel6.setText(" ");
        fecha1Correcta=false;
    }

    
    private void cargarCampos(){
        try {
            r_con.Connection();
            ResultSet rs=r_con.Consultar("select max(ba_nro_asiento) from borrador_asientos");
            if(rs.next()){
                jTextField2.setText(rs.getInt(1)+"");                        
            }
            rs=r_con.Consultar("select min(ba_nro_asiento) from borrador_asientos");
            if(rs.next()){
                jTextField1.setText(rs.getInt(1)+"");                        
            }
            
            r_con.cierraConexion();
        } catch (SQLException ex) {
            r_con.cierraConexion();
            Logger.getLogger(GUI_Registrar_Asientos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        campoFecha1 = new javax.swing.JFormattedTextField();
        campoFecha2 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registrar Asientos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        setInheritsPopupMenu(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar Asientos:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Fecha desde:");

        jTextField1.setToolTipText("");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        jButton2.setText("Aceptar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Fecha hasta:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Nº Asiento desde:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Nº Asiento hasta:");

        jTextField2.setToolTipText("");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        try {
            campoFecha1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoFecha1FocusLost(evt);
            }
        });

        try {
            campoFecha2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoFecha2FocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("-");

        jCheckBox1.setText("Activar Fechas");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(campoFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel6)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        r_con.cierraConexion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoFecha1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFecha1FocusLost
        // TODO add your handling code here:
        if (fecha.isFechaValida(campoFecha1.getText())){
            mensajeError(" ");
            fecha1Correcta=true;
        }
        else{
            if(jCheckBox1.isSelected()){
                mensajeError("La Fecha ingresada no se reconoce como valida.");
                campoFecha1.requestFocus();
            }
        }
    }//GEN-LAST:event_campoFecha1FocusLost

    private void campoFecha2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFecha2FocusLost
        // TODO add your handling code here:
        if(fecha1Correcta){
            if (fecha.isFechaValida(campoFecha2.getText())){
                if(fecha.menorFechas(campoFecha1.getText(), campoFecha2.getText())!=2){
                    mensajeError(" ");
                }
                else{
                    mensajeError("La fecha ingresada debe ser menor que la fecha desde");
                }
            }
            else{
                if(jCheckBox1.isSelected()){
                    mensajeError("La Fecha ingresada no se reconoce como valida.");                        
                    campoFecha2.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_campoFecha2FocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        r_con.Connection();                
        int asientoDesde=Integer.parseInt(jTextField1.getText());
        int asientoHasta=Integer.parseInt(jTextField2.getText());
        String fechaDesde=campoFecha1.getText();
        String fechaHasta=campoFecha2.getText();
        
            if(jCheckBox1.isSelected()){
                
                r_con.Insertar("insert into asientos(as_nro_asiento,as_nro_renglon,as_fecha_contabilidad,as_tipo,as_nro_cuenta,as_fecha_operacion,as_fecha_vencimiento,as_nro_comprobante,as_leyenda,as_debe,as_haber,as_ok_carga,as_ok_registrado) "+
                               " select * from borrador_asientos where ba_ok_carga=1 and ba_ok_registrado=0 and ba_nro_asiento>="+asientoDesde+" and ba_nro_asiento<="+asientoHasta+" and ba_fecha_contabilidad>='"+fechaDesde+"' and ba_fecha_contabilidad<='"+fechaHasta+"'");                
                r_con.ActualizarSinCartel("update borrador_asientos set ba_ok_registrado=1 where ba_ok_carga=1 and ba_nro_asiento>="+asientoDesde+" and ba_nro_asiento<="+asientoHasta
                                 +" and ba_fecha_contabilidad>='"+fechaDesde+"' and ba_fecha_contabilidad<='"+fechaHasta+"'");
                r_con.ActualizarSinCartel("update asientos set as_ok_registrado=1");
                
            }
            else
            {
                
                r_con.Insertar("insert into asientos(as_nro_asiento,as_nro_renglon,as_fecha_contabilidad,as_tipo,as_nro_cuenta,as_fecha_operacion,as_fecha_vencimiento,as_nro_comprobante,as_leyenda,as_debe,as_haber,as_ok_carga,as_ok_registrado) "+
                " select * from borrador_asientos where ba_ok_carga=1 and ba_ok_registrado=0 and ba_nro_asiento>="+asientoDesde+" and ba_nro_asiento<="+asientoHasta);
                r_con.ActualizarSinCartel("update borrador_asientos set ba_ok_registrado=1 where ba_ok_carga=1 and ba_nro_asiento>="+asientoDesde+" and ba_nro_asiento<="+asientoHasta);                              
                r_con.ActualizarSinCartel("update asientos set as_ok_registrado=1");
                
            }
            JOptionPane.showMessageDialog(null,"Los asientos fueron registrados correctamente");
            dispose();
            r_con.cierraConexion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            campoFecha1.setEnabled(true);
            campoFecha2.setEnabled(true);
            campoFecha1.requestFocus();
        }
        else
        {
            campoFecha1.setEnabled(false);
            campoFecha2.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        if(!validarCampo(jTextField1.getText())){
            mensajeError("Debe ingresar un numero entero correspondiente a un numero de Asiento");
            jTextField1.requestFocus();
        }
        else{
            mensajeError(" ");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        if(jLabel6.getText().equals(" ")){
            if(!validarCampo(jTextField2.getText())){
                mensajeError("Debe ingresar un numero entero correspondiente a un Asiento");
                jTextField2.requestFocus();
            }
            else{
                mensajeError(" ");
            }
        }
    }//GEN-LAST:event_jTextField2FocusLost
    
    private boolean camposNecesarios () {
       if ((jTextField1.getText().length()==0)) 
       {
            JOptionPane.showMessageDialog(null, "Complete todos los campos!","Atención",JOptionPane.WARNING_MESSAGE);
            return false;
       }
       return true;      
               
    }
    private void mostrarMSSG (Component c){
        KeyEvent ke = new KeyEvent(c, KeyEvent.KEY_PRESSED,
        System.currentTimeMillis(), InputEvent.CTRL_MASK, KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
        c.dispatchEvent(ke);
    }
    
    public void mensajeError(String msj){
        jLabel6.setText(msj);
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField campoFecha1;
    private javax.swing.JFormattedTextField campoFecha2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

public void setTitleLabel (String t){
        this.jLabel1.setText(t);
}

public void buttonBuscar (){
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png")));
    jButton2.setText("Buscar");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
}

public void buttonAceptar (){
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png")));
    jButton2.setText("Aceptar");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    
}

public void buttonEliminar (){
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eliminar.png")));
    jButton2.setText("Eliminar");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
}

public void buttonModificar (){
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modificar.png")));
    jButton2.setText("Modificar");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
}

public void buttonNuevaConsulta (){
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png")));
    jButton2.setText("Nueva Consulta");
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    
}

public void form_onlySearch (){
    //this.jTextField2.setEnabled(false);
}

public void form_Complete (){
    //this.jTextField2.setEnabled(true);
}

public void limpiarForm(){
    jTextField1.setText("");
    
    
    
    
}

public boolean validarCampo(String num){    
    try{
        int n=Integer.parseInt(num);
        return true;
    }
    catch(Exception e){
        return false;
    }    
}

}
