
package Interface;

import java.awt.Container;
import java.awt.Toolkit;

/**
 *
 * @author Manolo
 */
public class GUI_Principal extends javax.swing.JFrame {

    private Container cont;
   
    /**
     * Creates new form GUI_Principal
     */
    public GUI_Principal() {
        cont = getContentPane();        
        initComponents();
        
        //Frame tome el tamaño de la pantalla al 95% y comienze maximizado
        float escalar = 0.80F;
        int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
        int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
        this.setSize(ancho,alto);
        
        setLocationRelativeTo (null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/auxiliares.png"))); // NOI18N
        jMenu1.setMnemonic('1');
        jMenu1.setText("Auxiliares");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenu3.setText("Tasas de IVA");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem7.setText("Alta");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem1.setText("Baja");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem8.setText("Listado");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        jMenu2.setMnemonic('2');
        jMenu2.setText("Artículos");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem2.setText("Alta");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem3.setText("Baja");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem4.setText("Modificación");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem5.setText("Consulta");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem6.setText("Listado");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        jMenu4.setText("Usuarios");

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem9.setText("Nuevo");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //creo el InternalFrame;
        GUI_A_Articulo np = new GUI_A_Articulo();       
        np.setTitle("Alta Artículo.");
        np.setTitleLabel("Alta Artículo:");
        np.buttonAceptar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - np.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - np.getHeight() / 2;
        np.setLocation(x, np.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        np.setVisible(true);
        this.jDesktopPane1.add(np);
        np.moveToFront();
        np.requestFocus();
        np.nextFocus();       
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //creo el InternalFrame;
        GUI_A_Articulo bp = new GUI_A_Articulo();       
        bp.form_onlySearch();
        bp.setTitle("Baja Artículo.");
        bp.setTitleLabel("Baja Artículo:");
        bp.buttonBuscar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - bp.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        bp.setLocation(x, bp.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        bp.setVisible(true);
        this.jDesktopPane1.add(bp);
        bp.moveToFront();
        bp.requestFocus();
        bp.nextFocus();  
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling codGUI_A_Articulo
        GUI_A_Articulo bp = new GUI_A_Articulo();
        bp.form_onlySearch();
        bp.setTitle("Modificación Artículo.");
        bp.setTitleLabel("Modificación Artículo:");
        bp.buttonBuscar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - bp.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        bp.setLocation(x, bp.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        bp.setVisible(true);
        this.jDesktopPane1.add(bp);
        bp.moveToFront();
        bp.requestFocus();
        bp.nextFocus();  
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handGUI_A_Articulohere:
        GUI_A_Articulo cp = new GUI_A_Articulo();       
        cp.form_onlySearch();
        cp.setTitle("Consulta Artículo.");
        cp.setTitleLabel("Consulta Artículo:");
        cp.buttonBuscar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - cp.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        cp.setLocation(x, cp.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        cp.setVisible(true);
        this.jDesktopPane1.add(cp);
        cp.moveToFront();
        cp.requestFocus();
        cp.nextFocus();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
         //creo el InternalFrame;
        GUI_A_IVA np = new GUI_A_IVA();       
        np.setTitle("Alta Tasa IVA.");
        np.setTitleLabel("Alta Tasa IVA:");
        np.buttonAceptar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - np.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - np.getHeight() / 2;
        np.setLocation(x, np.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        np.setVisible(true);
        this.jDesktopPane1.add(np);
        np.moveToFront();
        np.requestFocus();
        np.nextFocus();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //creo el InternalFrame;
        GUI_A_IVA bp = new GUI_A_IVA();       
        bp.form_onlySearch();
        bp.setTitle("Baja Tasa de IVA.");
        bp.setTitleLabel("Baja Tasa de IVA:");
        bp.buttonBuscar();
        //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - bp.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        bp.setLocation(x, bp.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        bp.setVisible(true);
        this.jDesktopPane1.add(bp);
        bp.moveToFront();
        bp.requestFocus();
        bp.nextFocus();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        GUI_Listado l = new GUI_Listado();
        l.Listado_Articulos();
         //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - l.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        l.setLocation(x, l.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        l.setVisible(true);
        this.jDesktopPane1.add(l);
        l.moveToFront();
        l.requestFocus();
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        GUI_Listado l = new GUI_Listado();
        l.Listado_Tasas();
         //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - l.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        l.setLocation(x, l.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        l.setVisible(true);
        this.jDesktopPane1.add(l);
        l.moveToFront();
        l.requestFocus();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        GUI_Usuario guiU=new GUI_Usuario();                
        //l.Listado_Articulos();
         //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - guiU.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        guiU.setLocation(x, guiU.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        guiU.setVisible(true);
        this.jDesktopPane1.add(guiU);
        guiU.moveToFront();
        guiU.requestFocus();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
