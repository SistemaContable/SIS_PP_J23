/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Clases_Auxiliares.Conexion;
import Colecciones.Usuarios;
import Objetos.Usuario;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manolo
 */
public class GUI_Inicio_Sesion extends javax.swing.JFrame {

    private Conexion r_con;
    private final String nombre_BD_Sistema = "BD_Sistema";
    private ResultSet rsl;
    
    public GUI_Inicio_Sesion(Conexion con) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        r_con = con;
        r_con.setBase_datos(nombre_BD_Sistema);
        //r_con.Connection();
        cargarComboBox();
        jComboBox2.setSelectedIndex(0);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        //jTextField3.requestFocus();
        jComboBox1.requestFocusInWindow();
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
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesion");
        setIconImage(Toolkit.getDefaultToolkit().getImage("_icono.png"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/u_emp.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Usuario:");

        jTextField3.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Contraseña:");

        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Empresa:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        jButton5.setText("Iniciar Sesión");
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/configuracion.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Tipo Usuario:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuario de Empresa", "Usuario del Sistema" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/botonemp.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jTextField3))
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(184, 184, 184))))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_jButton5ActionPerformed

    public void iniciarSesion(){
        if ("".equals(jTextField3.getText())){
            msj_usuario_Error("Ingrese un Usuario, por favor.");
            jTextField3.requestFocus();
        }
        else{        
            this.vaciarMensaje();
            if (jComboBox2.getSelectedIndex()==1){
                try {                   
                    r_con.setBase_datos(nombre_BD_Sistema);
                    r_con.Connection();
                    rsl = r_con.Consultar("SELECT COUNT(*) FROM Usuarios WHERE usr_nombre_usuario = '"+
                                    jTextField3.getText()+"' AND usr_contrasenia = '"+
                                    jPasswordField1.getText()+"';");
                    rsl.next();
                    int existe = Integer.parseInt(rsl.getString(1));
                    rsl.close();
                    if (existe > 0){
                        jTextField3.setEnabled(false);
                        jPasswordField1.setEnabled(false);
                        jButton5.setEnabled(false);
                        jButton2.setEnabled(true);
                        jButton3.setEnabled(true);                        
                    }
                    else{
                        msj_usuario_Error("Usuario del Sistema o Contraseña INCORRECTOS.");
                        jTextField3.requestFocus();
                    }
                    r_con.cierraConexion();
                } 
                catch (SQLException ex) {
                    Logger.getLogger(GUI_Inicio_Sesion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    if (jComboBox1.getItemCount() > 0){
                        r_con.setBase_datos(nombre_BD_Sistema);
                        r_con.Connection();
                        String empresa = jComboBox1.getSelectedItem().toString();                   
                        rsl = r_con.Consultar("SELECT * FROM Empresas WHERE razon_social = '"+
                                            ""+empresa+"';");
                        rsl.next();       
                            String razon_social = rsl.getString(1);    
                            String nameinterno = rsl.getString(2);
                        rsl.close();
                    
                        r_con.cierraConexion();
                        r_con.setBase_datos(nameinterno);
                        r_con.setRazon_social(razon_social);
                        r_con.Connection();                     
                        
                        String usuario="";
                        usuario=jTextField3.getText();
                        String pass="";
                        pass=jPasswordField1.getText();
                        
                        if((!usuario.equals(""))&&(!pass.equals(""))){
                            Usuarios usuarios=new Usuarios(r_con);
                            Usuario u=usuarios.getUsuario(usuario);
                            if(u!=null){
                                if(!u.getExiste())
                                {
                                    msj_usuario_Error("El Usuario de la Empresa esta dado de Baja.");
                                }
                                else
                                {
                                    if(u.getContraseña().equals(pass)){                                        
                                        this.dispose();                                         
                                        //r_con.setUsuario(u.getUsuario());                                                                                
                                        new GUI_Principal(u,r_con).setVisible(true);
                                        
                                        dispose();
                                    }
                                    else
                                    {
                                        msj_usuario_Error("La Contraseña no es Correcta.");
                                    }
                                }
                            }                                                
                            else
                                msj_usuario_Error("El Usuario ingresado no Existe.");
                        }
                          
                    }                           
                    else{                                
                        msj_usuario_Error("No hay Empresas Registradas.");
                    }
                    r_con.cierraConexion();
                } 
                catch (SQLException ex) {
                    Logger.getLogger(GUI_Inicio_Sesion.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    r_con.cierraConexion();
                }
            }
        }
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ("".equals(jTextField3.getText())){
            msj_usuario_Error("Ingrese un Usuario del Sistema, por favor.");
            jTextField3.requestFocus();
        }
        else{        
            try {
                this.vaciarMensaje();
                r_con.setBase_datos(nombre_BD_Sistema);
                r_con.Connection();
                rsl = r_con.Consultar("SELECT COUNT(*) FROM Usuarios WHERE usr_nombre_usuario = '"+
                            jTextField3.getText()+"' AND usr_contrasenia = '"+
                            jPasswordField1.getText()+"';");
                rsl.next();
                int existe = Integer.parseInt(rsl.getString(1));
                rsl.close();
                if (existe > 0){
                    this.dispose();
                    GUI_Conexion gui = new GUI_Conexion();
                    gui.GUI_configuracion();
                    gui.setVisible(true);
                }
                else{
                    msj_usuario_Error("Usuario del Sistema o Contraseña INCORRECTOS.");
                    jTextField3.requestFocus();
                }
                r_con.cierraConexion();
            } catch (SQLException ex) {
                Logger.getLogger(GUI_Inicio_Sesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        if (jComboBox2.getSelectedIndex()==0){
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/u_emp.png")));
            jComboBox1.setEnabled(true);
            jButton3.setEnabled(false);
            jButton2.setEnabled(false);
        }
        else{            
            jTextField3.requestFocus();
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/u_sis.png")));
            jComboBox1.setEnabled(false);
        }
        jTextField3.setText("");
        jPasswordField1.setText("");
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        GUI_Empresa gui = new GUI_Empresa(this.r_con);                    
        gui.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
            iniciarSesion();        
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        // TODO add your handling code here:
        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        jTextField3.requestFocus();
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    
    private void cargarComboBox(){
        r_con.Connection();
        jComboBox1.removeAllItems();
        Vector<Vector<String>> v = r_con.getContenidoTabla("SELECT * FROM Empresas");
        for(Vector<String>a:v){
            jComboBox1.addItem(a.elementAt(0));
        }    
        //jComboBox1.addItem("< Nueva_Empresa >");
        v=null;
        r_con.cierraConexion();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void vaciarMensaje() {
        jLabel19.setText(" ");
    }
    
    private void msj_usuario_Exito(String msj) {
        jLabel19.setText(msj);
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
    }
    
    private void msj_usuario_Error(String msj) {
        jLabel19.setText(msj);
        jLabel19.setForeground(Color.RED);
    }
}
