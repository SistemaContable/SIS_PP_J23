/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Contabilidad;

import Clases_Auxiliares.Conexion;
import Clases_Auxiliares.Fechas;
import Clases_Auxiliares.Validaciones;
import Interface.GUI_Principal;
import Objetos.Usuario;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Manolo
 */
public class GUI_Imprimir_Mayor extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUI_A_Prod
     */
    private Conexion r_con;
    private Usuario usr;
    
    private final String nameTable = "plan_cuentas";
    private final String orden_por_CPC = "pc_codigo_plan_cuenta";
    private final String orden_por_cro_C = "pc_nro_cuenta";
    private final String nombre_reporte = "mayor.jrxml";
    private final String id_modulo_imp = "8";
    private String minCPC,maxCPC,minNC,maxNC;
    private String fechaDiario,fechaCierre,fechaInicio;
    private boolean aceptada=false;
    
    
    public GUI_Imprimir_Mayor(Usuario u, Conexion con){
        usr = u;
        r_con=con;              
        initComponents();  
        minCPC="";
        maxCPC="";
        minNC="";
        maxCPC="";
        
        minimosYmaximos();        
        
        r_con.Connection();        
        ResultSet rs=r_con.Consultar("select pc_fecha_cierre,pc_fecha_impresion_diario,pc_fecha_inicio from parametros_contables");
        try {
            rs.next();
            Fechas fechas=new Fechas();
            fechaCierre=fechas.parseFecha(rs.getDate(1));
            fechaDiario=fechas.parseFecha(rs.getDate(2));
            fechaInicio=fechas.parseFecha(rs.getDate(3));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Imprimir_Mayor.class.getName()).log(Level.SEVERE, null, ex);
        }                  
        campoFecha.setText(fechaInicio);                
        campoFecha1.setText(fechaCierre);
        
        r_con.ActualizarSinCartel("delete from libro_mayor");        
        r_con.cierraConexion();
    }

    public BigDecimal[] saldoAnterior(int numCuenta){
        try {
            r_con.Connection();
            BigDecimal[] bd=new BigDecimal[3];
            bd[0]=new BigDecimal(0);
            bd[1]=new BigDecimal(0);
            bd[2]=new BigDecimal(0);                                                
            String fechaDesde=campoFecha.getText();                                 
            int numAsiento=-1;
            ResultSet rs=r_con.Consultar("select * from asientos where as_nro_cuenta="+numCuenta+" and as_fecha_contabilidad<'"+fechaDesde+"' order by as_fecha_contabilidad");
            BigDecimal d=new BigDecimal(0);
            BigDecimal h=new BigDecimal(0);
            BigDecimal saldo=new BigDecimal(0);
            while(rs.next()){
                numAsiento=rs.getInt(1);
                saldo=sumarBigDecimal(saldo+"","-"+rs.getFloat(11));
                saldo=sumarBigDecimal(saldo+"",+rs.getFloat(10)+"");
                d=sumarBigDecimal(d+"",rs.getFloat(10)+"");
                h=sumarBigDecimal(h+"",rs.getFloat(11)+"");                
            }                                              
            if(numAsiento!=-1){
                
                String cadena=numAsiento+","+0+",' ',' ',"+numCuenta+",' ',' ',' ','Saldo anterior',"+d.floatValue()+","+h.floatValue()+","+saldo.floatValue();
                r_con.ActualizarSinCartel("insert into libro_mayor values("+cadena+")");               
                bd[0]=d;bd[1]=h;bd[2]=saldo;                
            }
            else{
                rs=r_con.Consultar("select * from asientos where as_nro_cuenta="+numCuenta);
                if(rs.next()){                    
                    numAsiento=rs.getInt(1);                    
                    String cadena=numAsiento+","+0+",' ',' ',"+numCuenta+",' ',' ',' ','Saldo anterior',"+d.floatValue()+","+h.floatValue()+","+saldo.floatValue();
                    r_con.ActualizarSinCartel("insert into libro_mayor values("+cadena+")");               
                }
            }
            r_con.cierraConexion();
            return bd;            
        } catch (SQLException ex) {
            r_con.cierraConexion();
            Logger.getLogger(GUI_Imprimir_Mayor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                
    }
    
    public void generarCuenta(int numCuenta){
        try {            
            BigDecimal[]bd=saldoAnterior(numCuenta);            
            r_con.Connection();
            String fechaDesde=campoFecha.getText();
            String fechaHasta=campoFecha1.getText();                        
            ResultSet rs=r_con.Consultar("select * from asientos where as_nro_cuenta="+numCuenta+" and as_fecha_contabilidad>='"+fechaDesde+"' and as_fecha_contabilidad<='"+fechaHasta+"' order by as_fecha_contabilidad");
            BigDecimal d=bd[0];
            BigDecimal h=bd[1];
            BigDecimal saldo=bd[2];            
            while(rs.next()){
                saldo=sumarBigDecimal(saldo+"","-"+rs.getFloat(11));
                saldo=sumarBigDecimal(saldo+"",+rs.getFloat(10)+"");
                String cadena=rs.getInt(1)+","+rs.getInt(2)+",'"+rs.getDate(3)+"','"+rs.getString(4)+"',"+rs.getInt(5)+",'"+rs.getDate(6)+"','"+rs.getDate(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"',"+rs.getFloat(10)+","+rs.getFloat(11)+","+saldo.floatValue();
                r_con.Insertar("insert into libro_mayor values("+cadena+")");               
                d=sumarBigDecimal(d+"",rs.getFloat(10)+"");
                h=sumarBigDecimal(h+"",rs.getFloat(11)+"");                
            }                                                                      
            r_con.cierraConexion();
        } catch (SQLException ex) {
            r_con.cierraConexion();
            Logger.getLogger(GUI_Imprimir_Mayor.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        campoFecha = new javax.swing.JFormattedTextField();
        campoFecha1 = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Filtro Reporte Mayor");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        setInheritsPopupMenu(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Preferencias:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("desde Cuenta:");

        jTextField1.setToolTipText("");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/previsualizar.png"))); // NOI18N
        jButton2.setText("Visualizar Reporte");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("hasta Cuenta:");

        jTextField2.setToolTipText("");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("desde Fecha:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("hasta Fecha:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("  ");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/print.png"))); // NOI18N
        jButton3.setText("Imprimir Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        try {
            campoFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoFechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoFechaFocusLost(evt);
            }
        });

        try {
            campoFecha1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFecha1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoFecha1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoFecha1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(185, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(157, 157, 157))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoFecha))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(campoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void generarTabla(){
        r_con.Connection();
        r_con.ActualizarSinCartel("delete from libro_mayor");        
        r_con.cierraConexion();
        int cuentaDesde=Integer.parseInt(jTextField1.getText());
        int cuentaHasta=Integer.parseInt(jTextField2.getText());
        for(int i=cuentaDesde;i<cuentaHasta;i++)
            generarCuenta(i);                        
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        r_con.cierraConexion();
        //generarTabla();   
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (validarDatos()){
            try {
                  generarTabla(); 
                  //cargo Parametros del Reporte
                   Map parametros = new HashMap();
                   parametros.put("name_empresa", r_con.getRazon_social());
                   
                  
                    //localizo el reporte para usarlo
                    JasperReport report = JasperCompileManager.compileReport("src/Reportes/"+nombre_reporte);
                    
                    r_con.Connection();
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, r_con.getConn());
                    
                    System.out.println(print.getPages().size());
                    //creo un objeto Visor del Reporte
                    JasperViewer jviewer = new JasperViewer(print,false);
                    jviewer.setTitle("Reporte Plan de Cuentas."); 
            
                    //quito el boton de imprimir del Visor
                    JRootPane JRP = (JRootPane) jviewer.getComponent(0);           
                    JLayeredPane JLP = (JLayeredPane) JRP.getComponent(1);
                    JPanel JP = (JPanel) JLP.getComponent(0);
                    JPanel JP2 = (JPanel) JP.getComponent(0);
                    JRViewer JRV = (JRViewer) JP2.getComponent(0);
                    JPanel JP3 = (JPanel) JRV.getComponent(0);            
                    //COMPONENTE 0 es el Boton Guardar, el 1 el es de Imprimir
                    JP3.getComponent(1).setEnabled(false);
                    
                    jviewer.setVisible(true);
            
            
            } catch (Exception e) {
                r_con.cierraConexion();
                System.out.println(e.getMessage());
            } 
            finally {
                      r_con.cierraConexion();
            }    
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {            
            //cargo Parametros del Reporte
            generarTabla(); 
            Map parametros = new HashMap();
            parametros.put("name_empresa", r_con.getRazon_social());
            
            //localizo el reporte para usarlo
            JasperReport report = JasperCompileManager.compileReport("src/Reportes/"+nombre_reporte);
            
            r_con.Connection();
            //cargo los datos
            JasperPrint print = JasperFillManager.fillReport(report, parametros, r_con.getConn());            
            System.out.println(print.getPages().size());
            //vector con las impresoras del modulo de la base de datos
            Vector<Vector<String>>v = r_con.getContenidoTabla("SELECT * FROM impresoras WHERE imp_id_modulo = "+id_modulo_imp);
            //total impresoras disponibles
            PrintService [] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
            //vector con las impresoras del modulo como objeto impresora (PrintService)
            Vector<PrintService>impresoras_modulo = new Vector();
            //objeto impresora en el que se imprime
            PrintService impresora = null;

            if (v.size()>0){
                String nombre_imp;
                
                //caso en que haya mas de una impresora por modulo
                if (v.size()>=1){
                    //localizo con el simple nombre de la base de dato, el objeto impresora y los cargo
                    for (int i = 0; i < v.size(); i++) {
                        nombre_imp=v.elementAt(i).firstElement();
                        AttributeSet aset = new HashAttributeSet();
                        aset.add(new PrinterName(nombre_imp, null));
                        impresoras = PrintServiceLookup.lookupPrintServices(null, aset);
                        impresora = impresoras[0];
                        impresoras_modulo.add(impresora);
                    }
                    //paso las impresoras del modulo a un arreglo para poder mostrarlo en el Dialog
                    PrintService [] listado_impresoras = new PrintService[impresoras_modulo.size()];
                    for (int i = 0; i < impresoras_modulo.size(); i++) {
                        listado_impresoras[i]=impresoras_modulo.elementAt(i);
                    }
                    //muestro el listado de impresoras como objeto y se la asigno a la impresora a imprimir
                    impresora = (PrintService) JOptionPane.showInputDialog(null, "Seleccione una impresora asignada a este módulo:",
                        "Imprimir Reporte", JOptionPane.QUESTION_MESSAGE, null, listado_impresoras, listado_impresoras[0]);
                }

                //mando a imprimir el reporte en la impresora
                if (impresora != null){
                    JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                    jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, print );
                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora );
                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.TRUE);
                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    jrprintServiceExporter.exportReport();                            
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No hay Impresoras asignadas a este Modulo, "
                    + "\npóngase en contacto con el Administrador de Impresoras.","Atención",JOptionPane.WARNING_MESSAGE);
            }
            r_con.cierraConexion();
        } catch (JRException ex) {
            
            Logger.getLogger(GUI_Imprimir_Mayor.class.getName()).log(Level.SEVERE, null, ex);
            r_con.cierraConexion();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void campoFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFechaFocusLost
        // TODO add your handling code here:
        Fechas fecha=new Fechas();
            if (fecha.isFechaValida(campoFecha.getText())){                
                if(fecha.fechaEntreFechas(campoFecha.getText(), fechaInicio, fechaCierre)){
                    mensajeError(" ");                         
                    campoFecha1.requestFocus();
                    aceptada=true;
                }
                else
                {                    
                    mensajeError("La Fecha ingresada debe ser mayor a "+fechaInicio+" y menor que "+fechaCierre);
                    campoFecha.requestFocus();
                }
            }
            else
            {
                mensajeError("La Fecha ingresada no se reconoce como valida.");
                campoFecha.requestFocus();
            }                    
    }//GEN-LAST:event_campoFechaFocusLost

    private void campoFecha1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFecha1FocusGained
        
    }//GEN-LAST:event_campoFecha1FocusGained

    private void campoFecha1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFecha1FocusLost
        Fechas fecha=new Fechas();
        if(aceptada){    
            if (fecha.isFechaValida(campoFecha1.getText())){
                if(fecha.fechaEntreFechas(campoFecha1.getText(), campoFecha.getText(), fechaCierre)){                                                       
                    mensajeError(" ");                    
                }
                else{
                    campoFecha1.requestFocus();
                    mensajeError("La fecha ingresada debe ser mayor que "+campoFecha.getText()+" y menor que "+fechaCierre);
                }
            }
            else{
                mensajeError("La Fecha ingresada no se reconoce como valida.");
                campoFecha1.requestFocus();
            }                                    
        }
    }//GEN-LAST:event_campoFecha1FocusLost

    private void campoFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoFechaFocusGained
        // TODO add your handling code here:
        aceptada=false;
    }//GEN-LAST:event_campoFechaFocusGained

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F1){            
            generarAyuda();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F1){            
            generarAyuda();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
   
    public void generarAyuda(){
        mensajeError(" ");
        //GUI_Ayuda_PC np=new GUI_Ayuda_PC(usuario,r_con,jTextField3,jTextField4,this); 
        GUI_Ayuda_PC np=new GUI_Ayuda_PC(usr,r_con,this);
        //lo centro respecto a x
        int x = (this.getDesktopPane().getWidth() / 2) - np.getWidth() / 2;
        int y = (this.getDesktopPane().getHeight() / 2) - np.getHeight() / 2;
        np.setLocation(x, y);        
        //lo hago visible, lo agrego al DesktopPanel, hago foco.
        np.setVisible(true);
        this.getDesktopPane().add(np);
        try {                
            np.setSelected(true);            
         } 
        catch (PropertyVetoException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        np.moveToFront();
        np.requestFocus();         
    }
    
    private void minimosYmaximos (){
        
        try {
            r_con.Connection();
            
            ResultSet res = r_con.Consultar("SELECT MIN(pc_codigo_plan_cuenta),MAX(pc_codigo_plan_cuenta),MIN(pc_nro_cuenta),MAX(pc_nro_cuenta) FROM "+nameTable+" WHERE pc_codigo_plan_cuenta<>'0'");
            res.next();
            minCPC=res.getString(1);
            maxCPC=res.getString(2);
            minNC=res.getString(3);
            maxNC=res.getString(4);
            
            jTextField1.setText(minNC);
            jTextField2.setText(maxNC);
//            jTextField3.setText(minCPC);
//            jTextField4.setText(maxCPC);
            
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Imprimir_Mayor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
               
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField campoFecha;
    private javax.swing.JFormattedTextField campoFecha1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

public void setTitleLabel (String t){
        this.jLabel1.setText(t);
}

    private boolean validarDatos() {
        //unicamente valido el numero de cuenta
        boolean valido = false;
        Fechas fecha = new Fechas();
        Validaciones val = new Validaciones();
        
        if (jTextField1.getText().equals("") || jTextField2.getText().equals("")){
            mensajeError("Ingrese un valor para Cuenta Desde.. Hasta.");
        }
        else{
            if (!val.isInt(jTextField1.getText()) || !val.isInt(jTextField2.getText())){
                mensajeError("Ingrese un valor NUMERICO para Cuenta Desde.. Hasta.");
            }
            else{
                if (!fecha.isFechaValida(campoFecha.getText()) || (!fecha.isFechaValida(campoFecha1.getText()))){
                        mensajeError("Ingrese Fechas Validas por favor.");
                }
                else{                    
                    valido = true;
                    mensajeError(" ");                        
                }  
                    
            }  
        }         
        return valido;
    }

    
    private void mensajeError(String msj) {
        jLabel7.setText(msj);        
    }
    
        private BigDecimal sumarBigDecimal(String num1,String num2){        
        float num1Float=Float.parseFloat(num1);
        float num2Float=Float.parseFloat(num2);
        String formato1=new DecimalFormat("0.00").format(num1Float);
        String formato2=new DecimalFormat("0.00").format(num2Float);
        formato1=formato1.replace(',', '.');
        formato2=formato2.replace(',', '.');        
        BigDecimal num1BigD=new BigDecimal(formato1);
        BigDecimal num2BigD=new BigDecimal(formato2);
        BigDecimal suma=num1BigD.add(num2BigD);        
        return suma;
    }
    
    private BigDecimal convertirEnBigDecimal(String num){
        float num1=Float.parseFloat(num);
        String formato=new DecimalFormat("0.00").format(num1);
        formato=formato.replace(',','.');
        BigDecimal bigNum=new BigDecimal(formato);
        return bigNum;
    }
 
    
}
