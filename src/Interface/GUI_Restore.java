/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;



import Clases_Auxiliares.Conexion;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Manolo
 */
public class GUI_Restore extends javax.swing.JFrame  {

    /**
     * Creates new form GUI_A_Prod
     */
    
    private ResultSet rslset;
    private String direccion = "";
    private final String name_interno_BD;
    private final String razon_social;
    private final String url_conexion_dat_sis;
    
    public GUI_Restore(String bd_name_int, String bd_razon_soc, String url) {
        initComponents();
        this.name_interno_BD=bd_name_int;
        this.razon_social=bd_razon_soc;
        this.url_conexion_dat_sis = url;
        setLocationRelativeTo (null);
        jButton2.setEnabled(false);
        generarCaptcha ();        
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("Restauración");
        setBackground(new java.awt.Color(204, 204, 204));
        setIconImage(Toolkit.getDefaultToolkit().getImage("_icono.png") );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Restauración de Información:");

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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ruta.png"))); // NOI18N
        jButton3.setText(" ...");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Seleccione la ruta del Back Up:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CÓDIGO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ingrese el código que de Seguridad:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(105, 105, 105))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton3))
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void generarCaptcha (){
        String Captcha = "";
        String [] abecedario = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (int i = 0; i < 4; i++) {
            int numRandon = (int) Math.round(Math.random()*25 ) ;
            Captcha += abecedario[numRandon];
        }
        jLabel2.setText(Captcha);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser directorio = new JFileChooser();
        directorio.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = directorio.showDialog(null,"Seleccione la ruta");
        String ruta = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            ruta = directorio.getSelectedFile().getPath();
            //habilitarOpciones(true);
        }
        System.out.println(ruta);
        if (ruta!=null){
            direccion=ruta;
            jButton2.setEnabled(true);       
            jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jLabel5.setText(" ");
        
        if ((jTextField1.getText().toUpperCase()).equals(jLabel2.getText())){
            if (perteneBackUp()){
                restoreBD();
                System.out.println("Exito al restaurar la BD "+razon_social);
                this.dispose();
                Conexion r_con = new Conexion();
                GUI_Inicio_Sesion gui = new GUI_Inicio_Sesion(r_con);
                gui.setVisible(true);
            }
            else{
                jLabel5.setText("el BackUp que intenta Restaurar no pertenece a su Empresa!.");
            }
        }
        else{
            jTextField1.requestFocus();
            jTextField1.selectAll();
            if (jTextField1.getText().equalsIgnoreCase("")){
                jLabel5.setText("por favor, ingrese el código de seguridad.");
            }
            else{
                jLabel5.setText("el código ingresado no coincide.");
            }
        }              
    }//GEN-LAST:event_jButton2ActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public void setTitleLabel (String t){
        this.jLabel1.setText(t);
    }

    private boolean perteneBackUp (){
        boolean pertenece = false;
        try 
        {
            Connection conn = null;                     
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println(url_conexion_dat_sis);
            String connectionUrl = this.url_conexion_dat_sis;
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;integratedSecurity=true");            
            
            Statement st =conn.createStatement();
            
            rslset = st.executeQuery("RESTORE HEADERONLY FROM DISK = '"+direccion+"'");
            rslset.next();

            String empresa_real = rslset.getString("BackupDescription");

            if (empresa_real.equals(this.razon_social)){
                pertenece = true;
            }
            st.close();
            this.rslset.close();
            conn.close();
            conn=null;                       
         
        } catch (SQLException ex) {
            System.err.println("1. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return(pertenece);
    }

    public void restoreBD(){
        try 
        {
            Connection conn = null;                     
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String connectionUrl = this.url_conexion_dat_sis;
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;integratedSecurity=true");            
            Statement st =conn.createStatement();
            
            String consulta="RESTORE DATABASE ["+this.name_interno_BD+"]"+
                    "FROM DISK = N'"+direccion+"'" +
                    "WITH RECOVERY, FILE = 1, NOUNLOAD, REPLACE, STATS = 10";
            
            String consulta2="DROP DATABASE "+ this.name_interno_BD;
            
            st.executeUpdate(consulta2);
            st.executeUpdate(consulta);
            st.close();
         
        } catch (SQLException ex) {
            System.err.println("1. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
         

}

/**
    sql = "alter database" + db_name + "set offline with rollback immediate;"; 
    sql += "restore database" + db_name + "from disk = '" + path + bk_name + "'"; 
    sql += "with replace";
    sql += "alter database" + db_name + "set onLine with rollback immediate;"; 
    stmt = conn.prepareStatement (sql); 
    stmt.executeUpdate (); 
    result = true; 
 **/
