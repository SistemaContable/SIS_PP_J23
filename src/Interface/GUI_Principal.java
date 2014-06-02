
package Interface;

import Clases_Auxiliares.Conexion;
import java.awt.Component;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;

/**
 *
 * @author Manolo
 */
public class GUI_Principal extends javax.swing.JFrame {

 
    private Conexion r_con=new Conexion();
    private int perfil;
    private int usuario; 
    
    /**
     * Creates new form GUI_Principal
     */
    public GUI_Principal() {      
        initComponents();                
        //Frame tome el tamaño de la pantalla al 95% y comienze maximizado        
        float escalar = 0.80F;
        int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
        int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
        
        this.setSize(ancho,alto); 
        setLocationRelativeTo (null);        
        crearLogin();
    }
    
    public GUI_Principal(int per,int id_usuario) {
           
        initComponents();
        perfil=per;
        //Frame tome el tamaño de la pantalla al 95% y comienze maximizado
        float escalar = 0.80F;
        int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width*escalar);
        int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height*escalar);
   
        this.setSize(ancho,alto);        
        setLocationRelativeTo (null);        
        habilitarMenu(true);
        habilitarFunciones(per);
        usuario=id_usuario;
    }
    
    private void crearLogin(){            
        GUI_Login lo = new GUI_Login(this);               
        lo.setTitle("Login");
        lo.setTitleLabel("Login");       
        
        this.jDesktopPane1.add(lo);
        lo.setVisible(true);
       
        int x = (this.getWidth() / 2) - (lo.getWidth() / 2);
        int y = (this.getHeight() / 2) - (lo.getHeight() / 2);
        
        lo.setLocation(x,y);        
        lo.moveToFront();
        lo.requestFocus();
        lo.focusPrincipal();      
        
        habilitarMenu(false);                
    }
    
    private void habilitarMenu(boolean valor){
        Component[] components = jMenuBar1.getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(valor);
            JMenu jaux=(JMenu)components[i];  
            for(int ff=0;ff<jaux.getItemCount();ff++){
                jaux.getItem(ff).setEnabled(valor);                
                
             /*   if(jaux.getItem(ff) instanceof JMenu){
                    JMenu jaux2=(JMenu)jaux.getItem(ff);                
                    for(int fff=0;fff<jaux2.getItemCount();fff++)
                        jaux2.getItem(fff).setEnabled(valor);                
                }*/
            }
        }    
    }                   
    // EJ---> 
    // Vector<Vector<Integer>>v(Vectro<modulo Tarea,...,...)    
    //Vector=(modulo,tarea,tarea,tarea)
    public void habilitarFunciones(int perfil){                           
        String consulta="select per_id_perfil,per_id_modulo,per_id_tarea from permiso where per_id_perfil="+perfil;
        r_con.Connection();
        ResultSet rs=r_con.Consultar(consulta);
        Vector<Vector<Integer>>modulosTarea=new Vector();
        boolean existe=false;
        int i=0;int pos=-1;
        try {
            while(rs.next()){                                                
                int aux=rs.getInt(2);//obtengo el modulo
                for(Vector<Integer>v1:modulosTarea){
                    if(v1.get(0)==aux){
                        existe=true;                        
                        pos=i;
                    }
                    i++;
                }
                if(!existe){
                    Vector<Integer>v=new Vector();
                    v.add(aux);v.add(rs.getInt(3));
                    modulosTarea.add(v);
                }
                else{
                    modulosTarea.get(pos).add(rs.getInt(3));
                }
                existe=false;
                i=0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarMenuPerfil(modulosTarea);
    }    
   
    /**
     * Para que funcione este metodo es necesario que el nombre de cada jMenu
     * se corresponda con el mod_id_modulo en la base de datos
     * @param moduloTarea 
     */
    private void habilitarMenuPerfil(Vector<Vector<Integer>>moduloTarea){        
        habilitarMenu(false);
        for(Component cMenu:jMenuBar1.getComponents()){
            for(Vector<Integer>modulo:moduloTarea){ 
                if(cMenu.getName().equals("M"+modulo.get(0))){                    
                    cMenu.setEnabled(true);
                }                                    
            }
            JMenu maux=(JMenu)cMenu;
            for(int i=0;i<maux.getItemCount();i++){
                for(Vector<Integer>modulo:moduloTarea){                                
                    if(maux.getItem(i).getName().equals("M"+modulo.get(0))){                    
                        maux.getItem(i).setEnabled(true);
                    }
                    for(int z=1;z<modulo.size();z++){                        
                        if(maux.getItem(i).getName().equals("T"+modulo.get(0)+modulo.get(z)))
                            maux.getItem(i).setEnabled(true);
                    }
                }
                // menu interno que tiene menu                                                    
            }            
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
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

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
            .addGap(0, 577, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/auxiliares.png"))); // NOI18N
        jMenu1.setMnemonic('1');
        jMenu1.setText("Auxiliares");
        jMenu1.setName("M5"); // NOI18N

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenu3.setText("Tasas de IVA");
        jMenu3.setName("M2"); // NOI18N

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem7.setText("Alta");
        jMenuItem7.setName("T21"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem1.setText("Baja");
        jMenuItem1.setName("T22"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem8.setText("Listado");
        jMenuItem8.setName("T25"); // NOI18N
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
        jMenu2.setName("M1"); // NOI18N

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem2.setText("Alta");
        jMenuItem2.setName("T11"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem3.setText("Baja");
        jMenuItem3.setName("T12"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem4.setText("Modificación");
        jMenuItem4.setName("T13"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem5.setText("Consulta");
        jMenuItem5.setName("T14"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem6.setText("Listado");
        jMenuItem6.setName("T15"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N
        jMenu4.setText("Usuarios");
        jMenu4.setName("M3"); // NOI18N

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem9.setText("Alta");
        jMenuItem9.setName("T31"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/print.png"))); // NOI18N
        jMenu5.setText("Impresoras");
        jMenu5.setName("M4"); // NOI18N

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/item.png"))); // NOI18N
        jMenuItem10.setText("Alta");
        jMenuItem10.setName("T41"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N
        jMenu6.setText("Auditoria");
        jMenu6.setName("M6"); // NOI18N

        jMenuItem11.setText("Listado");
        jMenuItem11.setName("T61"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

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
        GUI_A_Articulo np = new GUI_A_Articulo(usuario);       
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
        GUI_A_Articulo bp = new GUI_A_Articulo(usuario);       
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
        GUI_A_Articulo bp = new GUI_A_Articulo(usuario);
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
        GUI_A_Articulo cp = new GUI_A_Articulo(usuario);       
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
          l.setTitulo("Articulos");
        l.setCampo_clave("art_codigo");
        l.setNombre_tabla("Articulos");
        l.setNombre_reporte("rep_articulo.jrxml");
        String [] nombre_columnas = {"Codigo Articulo","Descripcion Articulo","Proveedor Articulo","Precio Articulo","Stock Articulo","Codigo Tasa IVA"};
        l.setNombre_columnas(nombre_columnas);
        l.setId_modulo_imp("1");
        l.Cargar_Tabla(l.getConsultaTodosElementos());        
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
        l.setTitulo("Tasas de IVA");
        l.setCampo_clave("tasa_clave");
        l.setNombre_tabla("Tasas_IVA");
        l.setNombre_reporte("rep_tasas_iva.jrxml");
        String [] nombre_columnas = {"Tasa Clave","Tasa Descripcion","Tasa Sigla"};
        l.setNombre_columnas(nombre_columnas);
        l.setId_modulo_imp("2");
        l.Cargar_Tabla(l.getConsultaTodosElementos());
       
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

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        GUI_Impresoras guiI = new GUI_Impresoras();                
        //l.Listado_Articulos();
         //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - guiI.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        guiI.setLocation(x, guiI.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        guiI.setVisible(true);
        this.jDesktopPane1.add(guiI);
        guiI.moveToFront();
        guiI.requestFocus();
        
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        GUI_Listado l = new GUI_Listado();
        l.setTitulo("Auditoria Articulos");
        l.setCampo_clave("aud_art_codigo");
        l.setNombre_tabla("auditoria_articulo");
        l.setNombre_reporte("rep_audi_articulos.jrxml");
        String [] nombre_columnas = {"ID","Usuario","Modulo","Tarea","Fecha","Terminal","Articulo Codigo","Articulo Descripcion","Articulo Proveedor","Precio","Stock","Tasa de IVA"};
        l.setNombre_columnas(nombre_columnas);
        l.setId_modulo_imp("6");
        l.Cargar_Tabla("select aud_id_auditoria,usr_nombre_usuario,mod_descripcion,tar_descripcion,aud_fecha,aud_terminal,aud_art_codigo,aud_art_desc,aud_art_proveedor,aud_art_precio,aud_art_stock,aud_art_cod_tasa_iva from auditoria_articulo,modulo,tarea,usuario where aud_id_modulo=mod_id_modulo and aud_id_tarea=tar_id_tarea and usr_id_usuario=aud_id_usuario");
       
         //lo centro respecto a x
        int x = (jDesktopPane1.getWidth() / 2) - l.getWidth() / 2;
        //int y = (jDesktopPane1.getHeight() / 2) - bp.getHeight() / 2;
        l.setLocation(x, l.getLocation().y);
        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        l.setVisible(true);
        this.jDesktopPane1.add(l);
        l.moveToFront();
        l.requestFocus();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

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
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
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




/**
    if(maux.getItem(i) instanceof JMenu){
                    System.out.println("PAJERO");
                    JMenu menuInterno=(JMenu)maux.getItem(i);
                    System.out.println(menuInterno.getComponentCount());
                    for(int contMI=0;contMI<menuInterno.getComponentCount();contMI++){
                        for(Vector<Integer>vInterno:moduloTarea)
                            for(int z=1;z<vInterno.size();z++){           
                                System.out.println(menuInterno.getItem(contMI).getName()+" --- "+"T"+vInterno.get(0)+vInterno.get(z));
                                if(menuInterno.getItem(contMI).getName().equals("T"+vInterno.get(0)+vInterno.get(z)))
                                    menuInterno.getItem(contMI).setEnabled(true);
                            }
                    }
                }
    
    */
