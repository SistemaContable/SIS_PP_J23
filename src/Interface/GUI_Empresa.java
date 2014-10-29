/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Clases_Auxiliares.Conexion;
import Clases_Auxiliares.Fechas;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manolo
 */

public class GUI_Empresa extends javax.swing.JFrame {

    private Conexion r_con;
    private final String nombre_BD_Sistema = "BD_Sistema";
    private Fechas control_f = new Fechas();
    
    public GUI_Empresa(Conexion con) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        r_con = con;
        campoFecha.setText(control_f.primerDiadelAño());
        campoFecha1.setText(control_f.ultimoDiadelAño());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        campoFecha = new javax.swing.JFormattedTextField();
        campoFecha1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Empreas del Sistema");
        setIconImage(Toolkit.getDefaultToolkit().getImage("_icono.png"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empresa.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Razon Social:");

        jTextField3.setToolTipText("");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        try {
            campoFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        try {
            campoFecha1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha1.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Usuario:");

        jTextField4.setToolTipText("");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cree un Usuario Administrador para dicha empresa:");

        jTextField5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Contraseña:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        jButton5.setText("Crear Empresa");
        jButton5.setToolTipText("");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(" ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Fecha Apertura Ejercicio:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Fecha Cierre Ejercicio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoFecha1)
                                .addComponent(campoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         
        if (this.validarCampos()){
            r_con.setBase_datos(nombre_BD_Sistema);
            r_con.Connection();
            if (r_con.existeDatabase(jTextField3.getText())){
                this.msj_Error("Razón Social ya Registrada.");
                jTextField3.requestFocus();
            }
            else{                                       
                try {
                    ResultSet rsl = r_con.Consultar("SELECT * FROM Directorios"); 
                    rsl.next();
                    String directorio = rsl.getString(2);
                    String nuevadatabase = jTextField3.getText();                        
                    rsl.close();
                    rsl = r_con.Consultar("SELECT COUNT (*) FROM Empresas");
                    rsl.next();
                    String nameinterno = "Empresa_"+(rsl.getInt(1)+1);
                    r_con.Insertar("INSERT INTO Empresas VALUES ('"+nuevadatabase+"',"
                                    + "'"+nameinterno+"');");

                    r_con.crearDatabase_DIR(nameinterno, directorio);
                    //paso del Sistema a la BD de la empresa
                    r_con.setBase_datos(nameinterno);
                    r_con.cierraConexion();
                    r_con.Connection();           

                    r_con.executeScripts("SQLQuery_Load_DB_Empresa.sql");
                    r_con.Insertar("INSERT INTO usuario VALUES ('"+
                                    jTextField4.getText()+"','Administrador_Inicial',"
                                    + "'Administrador_Inicial','"+
                                    jTextField5.getText()+"',1,1);");

                    rsl.close();
                  	
                    String valores = "'"+campoFecha.getText()+"','"+campoFecha1.getText()+"',0,'"+campoFecha.getText()+"',0,0,0";
                    String sql = "INSERT INTO parametros_contables values ("+valores+");";
                    r_con.Insertar(sql);
                    r_con.cierraConexion();
                    this.dispose();
                    GUI_Inicio_Sesion gui = new GUI_Inicio_Sesion(r_con);
                    gui.setVisible(true);                       
                } 
                catch (SQLException ex) {
                    r_con.cierraConexion();
                    Logger.getLogger(GUI_Empresa.class.getName()).log(Level.SEVERE, null, ex);
                }
                    r_con.cierraConexion();
            }  
        }          
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        if (jTextField3.getText().equals("")){
            msj_Error("Ingrese una Razon Social.");
            jTextField3.requestFocus();
        }
        else{
            this.vaciarMensaje();
        }
    }//GEN-LAST:event_jTextField3FocusLost
    
    private boolean validarCampos (){
        boolean validos = true;
        
        if (jTextField3.getText().equals("")){
            jTextField3.requestFocus();
            msj_Error("Ingrese una Razon Social.");
            validos=false;
        }
        else{
            if (!control_f .isFechaValida(campoFecha.getText())){
                msj_Error("La Fecha de Apertura no es valida.");
                campoFecha.requestFocus();
                validos=false;
            } 
            else{
                if (!control_f .isFechaValida(campoFecha1.getText())){
                    msj_Error("La Fecha de Cierre no es valida.");
                    campoFecha1.requestFocus();
                    validos=false;
                }
                else{
                    if (control_f.menorFechas(campoFecha.getText(), campoFecha1.getText())==2){
                            msj_Error("La Fecha de Apertura es mayor a la de Cierre.");
                            campoFecha.requestFocus();
                            validos=false;
                    }                     
                    else{
                        if (jTextField4.getText().equals("")){
                            msj_Error("Ingrese un Usuario Administrador.");
                            jTextField4.requestFocus();
                            validos=false;
                        }
                        else{
                            this.vaciarMensaje();
                        }
                    }
                }
            }
        }
        
        return validos;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField campoFecha;
    private javax.swing.JFormattedTextField campoFecha1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void vaciarMensaje() {
        jLabel19.setText(" ");
    }
    
    private void msj_Exito(String msj) {
        jLabel19.setText(msj);
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
    }
    
    private void msj_Error(String msj) {
        jLabel19.setText(msj);
        jLabel19.setForeground(Color.RED);
    }

}