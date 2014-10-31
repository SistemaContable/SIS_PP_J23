/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facturacion;

import Contabilidad.*;
import Clases_Auxiliares.Conexion;
import Clases_Auxiliares.Fechas;
import Clases_Auxiliares.JTextFieldFilter;
import static Clases_Auxiliares.NumberToLetterConverter.convertNumberToLetter;
import Clases_Auxiliares.Validaciones;
import Objetos.Cliente;
import Objetos.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/**
 *
 * @author Manolo
 */

public class IGUI_Facturar extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUI_A_Prod
     */
    private Conexion r_con;    
    private Usuario usuario;    
    private int renglon,modificar;
    private DefaultTableModel modelo,modeloRecibo;    
    private boolean esCarga=false;        
    private int numeroControl=-1;
    private int ultimoNumeroFactura=-1;
    //libreria de manejo de fechas
    private Fechas fecha = new Fechas ();    
    private Cliente cliente_factura;
    private boolean reciboExiste=false;    
    
    //atributos de impresion 
    private String id_modulo_imp = "9";
    private String reporte_seleccionado;
    
    public IGUI_Facturar(Usuario usr,Conexion con) {        
        initComponents();
        ordenarFoco();
        r_con=con;
        usuario=usr;
        renglon=0;
        modificar=-1;
        
        //r_con.Connection();
        inicializarTabla();
        inicializarIvas();
        
        this.habilitarPanel1(true);
        this.habilitarPanel2(false);     
        
        jButton1.setText("Cancelar"); 
        jButton2.setEnabled(false);        
        btn_confirmar_encabezado.setEnabled(false);
        jTable1.setEnabled(false);
        fecha_factura.setText(fecha.getHoy()); 
        actualizarLabelIva();
        ordenarFoco();
        field_tipo_comprobante.requestFocusInWindow();
        r_con.Connection();
        r_con.ActualizarSinCartel("delete from encabezado_factura where ef_confirmado=0");
        r_con.ActualizarSinCartel("delete from renglon_factura where rf_confirmado=0");
    }
    
    /**
     * Establece en cero todos los campos correspondientes al panel del IVA
     */
    private void inicializarIvas(){        
        BigDecimal cero=new BigDecimal(0);
        field_iva_general.setText(cero+"");
        field_tasa_diferencial.setText(cero+"");
        field_tasa_reducida.setText(cero+"");
        field_exento.setText(cero+"");
        field_sobretasa.setText(cero+"");
        field_impuesto_interno.setText(cero+"");
        field_no_gravado.setText(cero+"");
        field_total_iva.setText(cero+"");
        jTextField10.setText(cero+"");
        jTextField11.setText(cero+"");        
    }    
    
    /**
     * Vacia los campos con los datos del cliente y la factura
     */
    private void vaciar_panel_datos(){
        field_tipo_comprobante.requestFocusInWindow();
        this.vaciar_cliente();
        fecha_factura.setText(fecha.getHoy());
        field_tipo_comprobante.setText("");
        field_punto_venta.setText("");
        field_nro_cliente.setText("");
        label_numero_factura.setText(" ");
    }
    
    private void habilitarConfirmar1(){
        btn_confirmar_encabezado.setEnabled(false);
        if(!field_tipo_comprobante.getText().equals(""))
            if(!field_punto_venta.getText().equals(""))
                if(!field_nro_cliente.getText().equals(""))
                    if(!fecha_factura.getText().equals(""))
                        btn_confirmar_encabezado.setEnabled(true);                                        
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
        panel_datos_factura = new javax.swing.JPanel();
        field_tipo_comprobante = new javax.swing.JTextField();
        field_punto_venta = new javax.swing.JTextField();
        field_nro_cliente = new javax.swing.JTextField();
        fecha_factura = new javax.swing.JFormattedTextField();
        field_nombre = new javax.swing.JTextField();
        field_cuil_1 = new javax.swing.JTextField();
        field_cuil_2 = new javax.swing.JTextField();
        field_cuil_3 = new javax.swing.JTextField();
        field_direccion_calle = new javax.swing.JTextField();
        field_direccion_nro = new javax.swing.JTextField();
        field_localidad = new javax.swing.JTextField();
        field_situacion_IVA = new javax.swing.JTextField();
        btn_confirmar_encabezado = new javax.swing.JButton();
        label_tipo_comprobante = new javax.swing.JLabel();
        label_numero_factura = new javax.swing.JLabel();
        label_situacion = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        boton7 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        field_exento = new javax.swing.JTextField();
        field_impuesto_interno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        field_tasa_diferencial = new javax.swing.JTextField();
        field_tasa_reducida = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        field_iva_general = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        field_sobretasa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        field_no_gravado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        field_total_iva = new javax.swing.JTextField();
        label_mensaje = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de Facturacion");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        setInheritsPopupMenu(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Facturación:");

        panel_datos_factura.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_datos_factura.setFocusCycleRoot(true);

        field_tipo_comprobante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                field_tipo_comprobanteFocusLost(evt);
            }
        });
        field_tipo_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_tipo_comprobanteKeyPressed(evt);
            }
        });

        field_punto_venta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                field_punto_ventaFocusLost(evt);
            }
        });
        field_punto_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_punto_ventaKeyPressed(evt);
            }
        });

        field_nro_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                field_nro_clienteFocusLost(evt);
            }
        });
        field_nro_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_nro_clienteKeyPressed(evt);
            }
        });

        try {
            fecha_factura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fecha_factura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fecha_facturaFocusLost(evt);
            }
        });

        field_nombre.setEditable(false);

        field_cuil_1.setEditable(false);

        field_cuil_2.setEditable(false);

        field_cuil_3.setEditable(false);

        field_direccion_calle.setEditable(false);

        field_direccion_nro.setEditable(false);

        field_localidad.setEditable(false);

        field_situacion_IVA.setEditable(false);

        btn_confirmar_encabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        btn_confirmar_encabezado.setText("Confirmar");
        btn_confirmar_encabezado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmar_encabezadoActionPerformed(evt);
            }
        });

        label_tipo_comprobante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_tipo_comprobante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_tipo_comprobante.setText("TIPO COMPROBANTE:");

        label_numero_factura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_numero_factura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_numero_factura.setText(" ");

        label_situacion.setForeground(new java.awt.Color(0, 153, 51));
        label_situacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_situacion.setText(" ");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Tipo Comprobante:");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Pto. Venta:");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Nro. Cliente:");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Sr./Sra.:");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("CUIL:");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("-");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("-");

        jLabel16.setText("Fecha Operación");

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Direccion");

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Localidad:");

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Sit. IVA");

        javax.swing.GroupLayout panel_datos_facturaLayout = new javax.swing.GroupLayout(panel_datos_factura);
        panel_datos_factura.setLayout(panel_datos_facturaLayout);
        panel_datos_facturaLayout.setHorizontalGroup(
            panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                .addComponent(label_tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_numero_factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_tipo_comprobante, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha_factura))
                .addGap(24, 24, 24)
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field_punto_venta))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                                .addComponent(field_direccion_calle, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_direccion_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_localidad)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(field_situacion_IVA)))
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_nro_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                                .addComponent(field_cuil_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_cuil_2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_cuil_3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_confirmar_encabezado))
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_situacion, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panel_datos_facturaLayout.setVerticalGroup(
            panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_facturaLayout.createSequentialGroup()
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_tipo_comprobante)
                    .addComponent(label_numero_factura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                                    .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel27))
                                    .addGap(33, 33, 33))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_facturaLayout.createSequentialGroup()
                                    .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24)
                                        .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(field_nro_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(field_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(field_cuil_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel29)
                                                .addComponent(field_cuil_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel30)
                                                .addComponent(field_cuil_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(field_punto_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(field_tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn_confirmar_encabezado))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(33, 33, 33)))
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33)
                            .addComponent(jLabel16)
                            .addComponent(jLabel32)))
                    .addGroup(panel_datos_facturaLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(field_direccion_calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(field_direccion_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_datos_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(field_situacion_IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_situacion))
                            .addComponent(field_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setFocusCycleRoot(true);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Código:");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Descripción:");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cantidad:");

        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });

        jTextField8.setNextFocusableComponent(jButton4);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        jButton4.setText("Confirmar");
        jButton4.setNextFocusableComponent(boton7);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        boton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eliminar.png"))); // NOI18N
        boton7.setText("Borrar");
        boton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton7ActionPerformed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Precio Venta:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField8)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Subtotal:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("TOTAL:");

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        field_exento.setEditable(false);
        field_exento.setBackground(new java.awt.Color(255, 255, 255));

        field_impuesto_interno.setEditable(false);
        field_impuesto_interno.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Exento:");

        jLabel8.setText("No Gravado:");

        field_tasa_diferencial.setEditable(false);
        field_tasa_diferencial.setBackground(new java.awt.Color(255, 255, 255));

        field_tasa_reducida.setEditable(false);
        field_tasa_reducida.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("IVA 10.5%");

        jLabel4.setText("IVA 27%");

        field_iva_general.setEditable(false);
        field_iva_general.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("IVA 21%");

        jLabel10.setText("Sobretasa:");

        field_sobretasa.setEditable(false);
        field_sobretasa.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Impuesto Interno:");

        field_no_gravado.setEditable(false);
        field_no_gravado.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Total IVA:");

        field_total_iva.setEditable(false);
        field_total_iva.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_exento)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(field_tasa_reducida, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(field_iva_general)
                    .addComponent(field_tasa_diferencial, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_total_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_sobretasa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_impuesto_interno)
                            .addComponent(field_no_gravado))))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_iva_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(field_total_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_tasa_diferencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(field_sobretasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(field_tasa_reducida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_no_gravado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_impuesto_interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(field_exento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_mensaje.setForeground(new java.awt.Color(255, 0, 0));
        label_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_mensaje.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_datos_factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_datos_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_mensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jTable1.getModel().getRowCount()>0){
            int rta=JOptionPane.showConfirmDialog(null,"La factura será eliminada. ¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);                            
            if (rta==JOptionPane.YES_OPTION){                
                r_con.Connection();
                r_con.ActualizarSinCartel("delete from renglon_factura where rf_confirmado=0");
                r_con.ActualizarSinCartel("delete from renglon_factura where rf_encabezado_factura_id="+numeroControl);
                r_con.ActualizarSinCartel("delete from encabezado_factura where ef_confirmado=0");
                r_con.ActualizarSinCartel("delete from encabezado_factura where ef_encabezado_factura_id="+numeroControl);
               
                this.dispose();
            }                    
        }
        else{
            r_con.Connection();
            r_con.ActualizarSinCartel("delete from encabezado_factura where ef_confirmado=0");
           // r_con.ActualizarSinCartel("delete from encabezado_factura where ef_encabezado_factura_id="+numeroControl);
            dispose();
        }
        r_con.cierraConexion();
    }//GEN-LAST:event_jButton1ActionPerformed
                                              
    
    private void borrarCamposBasicos(){
        jTextField6.setText("");
        jTextField12.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField7.setEnabled(true);
        jTextField8.setEnabled(true); 
    }
    
   private int getIdNuevoEncabezadoFactura(){
        r_con.Connection();
        int id=-1;
        try{
            ResultSet rs=r_con.Consultar("select max(ef_encabezado_factura_id) from encabezado_factura");
            if(rs.next()){
                id=rs.getInt(1);           
            }
            id++;
        } catch(Exception e){       
        } finally {r_con.cierraConexion();}   
        return id;
   }
    
    
    private void btn_confirmar_encabezadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmar_encabezadoActionPerformed
        numeroControl=getIdNuevoEncabezadoFactura();  
        r_con.Connection();
        int tipoComprobante=Integer.parseInt(field_tipo_comprobante.getText());
        int puntoVenta=Integer.parseInt(field_punto_venta.getText()); 
        int numCliente=Integer.parseInt(field_nro_cliente.getText());       
        String fec=fecha_factura.getText();                
        
        ResultSet rsAux=r_con.Consultar("select max(vxc_numero) from ptoventa_x_tipocomprobante where vxc_id_pto_venta="+puntoVenta +" and vxc_id_tipo_comprobante="+tipoComprobante);
        try{                    
            if(rsAux.next())
                ultimoNumeroFactura=rsAux.getInt(1)+1;        
        }catch(Exception e){}        
        
        String sql="insert into encabezado_factura values("+numeroControl+","+tipoComprobante+","+puntoVenta+","+ultimoNumeroFactura+
                ",(select pf_numero_control+1 from parametros_facturacion),'"+numCliente+"','"+fec+"',0,0,0,0,0,0,0,0,0,0,0,0)";        
        r_con.InsertarSinCartel(sql);        
        r_con.ActualizarSinCartel("update parametros_facturacion set pf_numero_control="+numeroControl);
                                                
        if(cliente_factura.getCodigo_situacion_IVA_cliente()!=1){
            jLabel2.setVisible(false);
            jTextField10.setVisible(false);            
        }            
        else{
            jLabel2.setVisible(true);
            jTextField10.setVisible(true);            
        }
        btn_confirmar_encabezado.setEnabled(false);
        r_con.cierraConexion();
        jButton2.setEnabled(true);                    
        habilitarPanel1(false);
        habilitarPanel2(true);
        boton7.setEnabled(false);
        jButton4.setEnabled(false);
        jTable1.setEnabled(true);
        jTextField6.requestFocusInWindow();
        actualizarLabelIva();
        esRecibo();
    }//GEN-LAST:event_btn_confirmar_encabezadoActionPerformed

    public void esRecibo(){
        int tipoComprobante= Integer.parseInt(field_tipo_comprobante.getText());
        if((tipoComprobante==4)||(tipoComprobante==9)||(tipoComprobante==15)){
            reciboExiste=true;            
            modeloRecibo=new DefaultTableModel();
            String [] columnas= {"Descripcion","Importe"};
            modeloRecibo.setColumnIdentifiers(columnas);             
            jTable1.setModel(modeloRecibo); 
            jTable1.setEnabled(false);
            boton7.setEnabled(true);
            habilitarPanelRecibo(true);
            jTextField10.setVisible(false);jLabel2.setVisible(false);
            jTextField12.requestFocusInWindow();
        }
        else{
            habilitarPanelRecibo(false);
            jTextField12.setEnabled(true);
            jTextField7.setEnabled(true);
            if(tipoComprobante==1){
                jTextField10.setVisible(true);jLabel2.setVisible(true);//subtotal
            }
        }
    }
        
    private void habilitarPanelRecibo(boolean valor){
        jTextField6.setVisible(!valor);jTextField6.setFocusable(!valor);jLabel6.setVisible(!valor);
        jTextField8.setVisible(!valor);jTextField8.setFocusable(!valor);jLabel13.setVisible(!valor);        
        jTextField12.setEditable(valor);jTextField12.setEnabled(valor);jTextField12.setFocusable(valor);
        jTextField7.setEditable(valor);jTextField7.setEnabled(valor);jTextField7.setFocusable(valor);
        
    }
    
    private void habilitarConfirmar2(){
        if((!jTextField6.getText().equals(""))&&(!jTextField8.getText().equals("")))
            jButton4.setEnabled(true);
        else
            jButton4.setEnabled(false);
    }
    
    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        habilitarConfirmar2();
    }//GEN-LAST:event_jTextField8FocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.imprimir();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:        
        if(jTable1.isEnabled()){
            if(evt.getClickCount()==2){            
                int fila=jTable1.getSelectedRow();            
                modificar=fila;                               
                String codProducto=(String)modelo.getValueAt(fila,0);
                String cantidad=modelo.getValueAt(fila, 2)+"";
                String leyenda=(String)modelo.getValueAt(fila, 1);
                BigDecimal precioVenta=convertirEnBigDecimal((String)modelo.getValueAt(fila, 3));                
                jTextField6.setText(codProducto);
                jTextField12.setText(leyenda);
                jTextField7.setText(precioVenta+"");
                jTextField8.setText(cantidad);            
                BigDecimal iva=calcularIva(jTextField6.getText(),precioVenta);
                habilitarPanel2(true);
                boton7.setEnabled(true);
                jButton4.setText("Modificar");
            }               
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jButton4.setEnabled(false);
        if(!reciboExiste)
            boton7.setEnabled(false);
        if(jButton4.getText().equals("Confirmar")){
            if(controlarCampos()){
                jButton2.setEnabled(true);
                jButton1.setText("Abandonar Factura");
                if(reciboExiste)
                    cargarRecibo();
                else
                    cargarTabla();
                jTextField6.requestFocusInWindow();
                borrarCamposBasicos();
                mensajeError(" ");
            }
            else{
                mensajeError("Debe completar todos los campos");
            }
        }
        else{
            if(controlarCampos()){
                jButton4.setText("Confirmar");                               
                actualizarTabla();
                mensajeError(" ");
                borrarCamposBasicos();                
            }
            else
                mensajeError("Debe completar todos los campos");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void boton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton7ActionPerformed
        // TODO add your handling code here:
        if(!reciboExiste){
            boton7.setEnabled(false);
            int rta=JOptionPane.showConfirmDialog(null,"El renglon sera eliminado. ¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);                            
                if (rta==JOptionPane.YES_OPTION){                                
                    modelo.removeRow(modificar);                
                    borrarRenglon();
                    borrarCamposBasicos();
                    r_con.Connection();                
                    actualizar();                
                }
                else
                    boton7.setEnabled(true);
            jButton4.setText("Confirmar");
            modificar=-1;
        }
        else{ // si es recibo borro el ultimo renglon
            this.borrarUltimoProductoRecibo();
        }
    }//GEN-LAST:event_boton7ActionPerformed

    private void borrarRenglon(){
        r_con.Connection();
        r_con.ActualizarSinCartel("delete from renglon_factura where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
        ResultSet rs=r_con.Consultar("select * from renglon_factura order by rf_num_renglon");
        renglon--;
        try{
            if(modificar<jTable1.getModel().getRowCount()){                
                int i=0;
                int posBuscada=modificar+1;
                while(rs.next()){
                    //System.out.println(i+" "+rs.getInt("rf_num_renglon")+" "+posBuscada);
                    if(i!=rs.getInt("rf_num_renglon")){
                        r_con.ActualizarSinCartel("update renglon_factura set rf_num_renglon="+i+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+posBuscada);
                        posBuscada++;
                    }                
                    i++;
                }
            }
        } catch(Exception e){
        } finally {r_con.cierraConexion();} 
    }
    
    private void field_tipo_comprobanteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_field_tipo_comprobanteFocusLost
        boolean es_componente=false;
        //voy a preguntar si la componente que me saco el foco es algun campo del panel de datos de asientos
        if (evt.getOppositeComponent()!=fecha_factura){
            int i=0;        
            Component[] components = panel_datos_factura.getComponents();
            while ((!es_componente)&&(i<components.length)){
                if (components[i]==evt.getOppositeComponent()){
                    es_componente=true;
                }
                i++;
            }

            if((es_componente)&&(field_tipo_comprobante.isEditable())){        
                if (!field_tipo_comprobante.getText().equals("")){
                    String detalle = get_TipoComprobante(field_tipo_comprobante.getText());
                    if (!detalle.equals("")){
                        label_tipo_comprobante.setText(detalle+" Nº: ");
                        mensajeError(" ");
                    }
                    else{
                        field_tipo_comprobante.requestFocusInWindow();
                        label_tipo_comprobante.setText("TIPO COMPROBANTE:");
                        generarAyuda_Tipo_Comprobante();
                    }
                }
                else{
                    label_tipo_comprobante.setText("TIPO COMPROBANTE:");
                    mensajeError("Por favor, seleccione un tipo de comprobante");
                }
            }
        }        
    }//GEN-LAST:event_field_tipo_comprobanteFocusLost
    
    private void field_punto_ventaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_field_punto_ventaFocusLost
        // TODO add your handling code here:
        boolean es_componente=false;
        if (evt.getOppositeComponent()!=field_tipo_comprobante){
            //voy a preguntar si la componente que me saco el foco es algun campo del panel de datos de asientos
            int i=0;        
            Component[] components = panel_datos_factura.getComponents();
            while ((!es_componente)&&(i<components.length)){
                if (components[i]==evt.getOppositeComponent()){
                    es_componente=true;
                }
                i++;
            }

            if((es_componente)&&(!field_punto_venta.getText().equals(""))){
                if (isPuntoVenta(field_punto_venta.getText())){
                    String detalle = get_NumeroComprobante(field_punto_venta.getText(),field_tipo_comprobante.getText());
                    if (!detalle.equals("")){
                        String numero_com="00";
                        String numero_pto="0000";
                        if ((!field_tipo_comprobante.getText().equals(""))&&(!field_punto_venta.getText().equals(""))){
                            numero_com = String.format("%02d", Integer.parseInt(field_tipo_comprobante.getText()));
                            numero_pto = String.format("%04d", Integer.parseInt(field_punto_venta.getText()));
                        }                
                        String numero_fac = String.format("%08d", Integer.parseInt(detalle)+1);
                        label_numero_factura.setText(numero_com+"-"+numero_pto+"-"+numero_fac);
                        mensajeError(" ");
                    }
                    else{
                        label_numero_factura.setText(" ");
                        field_punto_venta.requestFocusInWindow();
                        mensajeError("El tipo de comprobante "+field_tipo_comprobante.getText()+" no esta disponible para el punto de venta "+field_punto_venta.getText());
                    }
                }
                else {
                    field_punto_venta.requestFocusInWindow();
                    generarAyuda_Punto_Venta();   
                }
            }
            else{
                label_numero_factura.setText(" ");
                mensajeError("Por favor, seleccione un punto de venta");
            }
        }
    }//GEN-LAST:event_field_punto_ventaFocusLost

    private void field_tipo_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_tipo_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F1){            
            generarAyuda_Tipo_Comprobante();
        }
    }//GEN-LAST:event_field_tipo_comprobanteKeyPressed

    private void field_punto_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_punto_ventaKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_F1){            
            generarAyuda_Punto_Venta();
        }
    }//GEN-LAST:event_field_punto_ventaKeyPressed

    private void fecha_facturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fecha_facturaFocusLost

        boolean es_componente=false;
        if (evt.getOppositeComponent() != field_nro_cliente){
            //voy a preguntar si la componente que me saco el foco es algun campo del panel de datos de asientos
            int i=0;        
            Component[] components = panel_datos_factura.getComponents();
            while ((!es_componente)&&(i<components.length)){
                if (components[i]==evt.getOppositeComponent()){
                    es_componente=true;
                }
                i++;
            }

            if (es_componente){
                if (!fecha.isFechaValida(fecha_factura.getText())){
                    fecha_factura.requestFocusInWindow();    
                    mensajeError("La Fecha ingresada no se reconoce como valida.");    
                }
                else{
                    mensajeError(" ");
                }
            }
            r_con.Connection();
            try{                
                String fechaFacturacion="";
                ResultSet rs=r_con.Consultar("select pf_fecha_ultima_factura from parametros_facturacion");
                if(rs.next())
                    fechaFacturacion=rs.getString("pf_fecha_ultima_factura");
                fechaFacturacion=fecha.convertirBarras(fechaFacturacion);
                if((fecha.menorFechas(fecha_factura.getText(),fechaFacturacion)==2)||(fecha.menorFechas(fecha_factura.getText(),fechaFacturacion)==0)){
                    btn_confirmar_encabezado.setEnabled(true);
                    btn_confirmar_encabezado.requestFocus();
                    habilitarConfirmar1();
                }
                else{
                    btn_confirmar_encabezado.setEnabled(false);
                    mensajeError("La Fecha ingresada debe ser superior a la fecha de la ultima factura: "+fechaFacturacion);    
                }
            }
            catch(Exception e){
            r_con.cierraConexion();
            }
            r_con.cierraConexion();
            actualizarLabelIva();
        }
    }//GEN-LAST:event_fecha_facturaFocusLost

    private void field_nro_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_field_nro_clienteFocusLost
        // TODO add your handling code here:
        habilitarConfirmar1();
        boolean es_componente=false;
        if (evt.getOppositeComponent()!= field_punto_venta){
            //voy a preguntar si la componente que me saco el foco es algun campo del panel de datos de asientos
            int i=0;        
            Component[] components = panel_datos_factura.getComponents();
            while ((!es_componente)&&(i<components.length)){
                if (components[i]==evt.getOppositeComponent()){
                    es_componente=true;
                }
                i++;
            }
            if((es_componente)){
                if (!field_nro_cliente.getText().equals("")){
                    Cliente cli = get_Cliente (field_nro_cliente.getText());
                    if (cli.getCodigo_cliente()!=0){
                        if ((!field_tipo_comprobante.equals(""))&&(validar_Comprobante_Cliente (cli,field_tipo_comprobante.getText()))) {
                            cliente_factura = cli;
                            field_nombre.setText(cli.getNombre_cliente()+" "+cli.getApellido_cliente());
                            field_cuil_1.setText(cli.getCuil_prefijo_cliente());
                            field_cuil_2.setText(cli.getCuil_dni_cliente());
                            field_cuil_3.setText(cli.getCuil_digito_cliente());
                            field_direccion_calle.setText(cli.getCalle_cliente());
                            field_direccion_nro.setText(cli.getNumero_calle_cliente());
                            field_localidad.setText(cli.getLocalidad_cliente());
                            field_situacion_IVA.setText(""+cli.getCodigo_situacion_IVA_cliente());
                            label_situacion.setText(cli.getDescripcion_situacion_IVA_cliente());               
                            mensajeError(" ");
                        }
                        else{
                            field_nro_cliente.requestFocusInWindow();
                            mensajeError("El tipo de comprobante seleccionado no esta permitido para la situacion frente al IVA del Cliente");
                        }
                    }
                    else{
                        vaciar_cliente ();
                        field_nro_cliente.requestFocusInWindow();
                        generarAyuda_Cliente();      
                    }
                }
                else{
                    vaciar_cliente ();
                    field_nro_cliente.requestFocusInWindow();
                    mensajeError("Por favor, ingrese un cliente");
                }
            }
        }
    }//GEN-LAST:event_field_nro_clienteFocusLost

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1){            
           generarAyuda_Producto();
        }        
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){            
           jTextField6.setFocusable(false);
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        boolean es_componente=false;        
        int i=0;        
        Component[] components = jPanel3.getComponents();
        while ((!es_componente)&&(i<components.length)){
            if (components[i]==evt.getOppositeComponent()){
                es_componente=true;
            }
            i++;
        }        

        if(es_componente){   
            r_con.Connection();
            jButton4.setEnabled(true);
            try{
                if(!jTextField6.getText().equals("")){
                    ResultSet rs=r_con.Consultar("select * from productos where prod_codigo="+jTextField6.getText());
                    if(rs.next()){
                        jTextField12.setText(rs.getString(2));
                        jTextField7.setText(rs.getString(5));                
                    }
                    else{
                        jTextField6.requestFocusInWindow();
                    }
                }
                else{
                    jTextField6.requestFocusInWindow();
                }
            } catch(Exception e){            
            } finally {r_con.cierraConexion();}        
        }        
    }//GEN-LAST:event_jTextField6FocusLost

    private void field_nro_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_nro_clienteKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_F1){            
            generarAyuda_Cliente();
        }        
    }//GEN-LAST:event_field_nro_clienteKeyPressed

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        if(evt.getClickCount()==1){
            jTextField6.setFocusable(true);
        }
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        // TODO add your handling code here:
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jTextField7FocusLost
    
    public void generarAyuda_Producto(){
        GUI_Ayuda_Producto np=new GUI_Ayuda_Producto(r_con,this);
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
            Logger.getLogger(IGUI_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        np.moveToFront(); 
    }
    
    private boolean validar_Comprobante_Cliente (Cliente cli, String codigo_comprobante){
        boolean existe = false;
        r_con.Connection();
        try {            
            String sql = (
                        "SELECT * "+
                        " FROM situacion_x_tipocomprobante "+
                        " WHERE sfi_id = "+cli.getCodigo_situacion_IVA_cliente()+" AND tc_codigo = "+codigo_comprobante);           
            
            ResultSet res = r_con.Consultar(sql);
            
            while(res.next()){
                existe = true;
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(IGUI_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {r_con.cierraConexion();}
        
        return existe;    
    }
    
    private void vaciar_cliente (){
        field_nombre.setText("");
        field_cuil_1.setText("");
        field_cuil_2.setText("");
        field_cuil_3.setText("");
        field_direccion_calle.setText("");
        field_direccion_nro.setText("");
        field_localidad.setText("");
        field_situacion_IVA.setText("");
        label_situacion.setText(" ");
    }
    
    private String get_TipoComprobante (String codigo){
        String detalle = "";
        r_con.Connection();
        try {   
            String sql = ( "SELECT * "+
                    " FROM tipo_comprobante"+
                    " WHERE tc_codigo = "+codigo);
            
            ResultSet res = r_con.Consultar(sql);
            while(res.next()){
                detalle = res.getString(2);
            }
            res.close();           
        } catch (SQLException ex) {
            Logger.getLogger(IGUI_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {r_con.cierraConexion();}
        
        return detalle;
    }
    
    private String get_NumeroComprobante (String pto, String codigo){
        String detalle = "";
        r_con.Connection();
        try {       
            String sql = ( "SELECT * "+
                    " FROM ptoventa_x_tipocomprobante"+
                    " WHERE vxc_id_pto_venta = "+pto+" AND vxc_id_tipo_comprobante = "+codigo);
            
            ResultSet res = r_con.Consultar(sql);
            while(res.next()){
                detalle = res.getString(3);
            }
            res.close();           
        } catch (SQLException ex) {
            Logger.getLogger(IGUI_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {r_con.cierraConexion();}
        
        return detalle;
    }
    
    private boolean isPuntoVenta (String codigo){
        boolean existe = false;
        try {
            r_con.Connection();
            String sql = (
                        "SELECT * "+
                        " FROM punto_venta"+
                        " WHERE pv_codigo = "+codigo);           
            
            ResultSet res = r_con.Consultar(sql);
            
            while(res.next()){
                existe = true;
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(IGUI_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {r_con.cierraConexion();}
        
        return existe;
    }
    
    private Cliente get_Cliente (String codigo){
        String detalle = "";
        Cliente cli = new Cliente();
        r_con.Connection();
        try {            
            String sql = ( "SELECT cli_codigo, cli_nombre, cli_apellido, cli_fecha_nac, cli_cuit, loc_descripcion, cli_direccion, cli_calle, cli_sit_frente_iva, sfi_descripcion " +
                           "FROM clientes, localidades, situacion_frente_iva " +
                           "WHERE (cli_codigo = "+codigo+") AND (cli_localidad = loc_id) AND (cli_sit_frente_iva = sfi_id)");
            
            ResultSet res = r_con.Consultar(sql);           
            
            while(res.next()){
                cli.setCodigo_cliente(Integer.parseInt(res.getString(1)));
                cli.setNombre_cliente(res.getString(2));
                cli.setApellido_cliente(res.getString(3));
                
                String fechaAux=res.getString(4);
                if((fechaAux!=null)&&(!fechaAux.equals("")))
                    fechaAux = fecha.convertirBarras(res.getString(4));                
                else
                    fechaAux="";
                cli.setFecha_nacimiento_cliente(fechaAux);
                
                String cuil = res.getString(5);
                if((cuil!=null)&&(!cuil.equals(""))){
                    cli.setCuil_prefijo_cliente(cuil.substring(0,2));
                    cli.setCuil_dni_cliente(cuil.substring(2,cuil.length()-1));
                    cli.setCuil_digito_cliente(cuil.substring(cuil.length()-1,cuil.length()));
                }
                else{
                    cli.setCuil_prefijo_cliente("");
                    cli.setCuil_dni_cliente("");
                    cli.setCuil_digito_cliente("");
                }
                
                cli.setLocalidad_cliente(res.getString(6));
                cli.setCalle_cliente(res.getString(7));
                cli.setNumero_calle_cliente(res.getString(8));
                cli.setCodigo_situacion_IVA_cliente(Integer.parseInt(res.getString(9)));
                cli.setDescripcion_situacion_IVA_cliente(res.getString(10));
            }
            res.close();           
        } catch (SQLException ex) {
            Logger.getLogger(IGUI_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {r_con.cierraConexion();}

        return cli;
    }
    
    public void generarAyuda_Tipo_Comprobante(){
        GUI_Ayuda_Tipo_Comprobante np = new GUI_Ayuda_Tipo_Comprobante(r_con,this);
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
            Logger.getLogger(IGUI_Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        np.moveToFront();        
    }
    
     public void generarAyuda_Punto_Venta(){
        GUI_Ayuda_Punto_Venta np = new GUI_Ayuda_Punto_Venta(r_con,this);
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
            Logger.getLogger(IGUI_Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        np.moveToFront();        
    }
     
     public void generarAyuda_Cliente(){
        GUI_Ayuda_Cliente np = new GUI_Ayuda_Cliente(r_con,this);
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
            Logger.getLogger(IGUI_Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        np.moveToFront();       
    }
    
     private void actualizarTotalRecibo(){
         BigDecimal total=new BigDecimal(0);
         for(int i=0;i<jTable1.getModel().getRowCount();i++){
             total=sumarBigDecimal(total+"",convertirEnBigDecimal((String)jTable1.getModel().getValueAt(i,1))+"");
         }
         jTextField11.setText(total+"");
     }
     
     private void borrarUltimoProductoRecibo(){                      
        BigDecimal importe=convertirEnBigDecimal(jTable1.getModel().getValueAt(jTable1.getModel().getRowCount()-1, 1)+"");
        jTextField11.setText(sumarBigDecimal(convertirEnBigDecimal(jTextField11.getText())+"","-"+importe)+"");
        modeloRecibo.removeRow(--renglon);
        jTable1.setModel(modeloRecibo);
        r_con.Connection();
        r_con.ActualizarSinCartel("delete from renglon_factura where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+renglon);                
     }
     
     private void actualizarTabla(){
        if(modificar!=-1){            
            int numRenglon=modificar;                        
            
            modelo.setValueAt(jTextField6.getText(),numRenglon , 0);
            modelo.setValueAt(jTextField12.getText(),numRenglon , 1);
            int cantidad=Integer.parseInt(jTextField8.getText());
            modelo.setValueAt(cantidad,numRenglon , 2);
            BigDecimal precioVenta=convertirEnBigDecimal(jTextField7.getText());
            modelo.setValueAt(precioVenta.floatValue()+"",numRenglon , 3);
                        
            BigDecimal importe=precioVenta.multiply(new BigDecimal(cantidad));
            BigDecimal iva=calcularIva(jTextField6.getText(),importe);
            BigDecimal impInterno=calcularImpInterno(jTextField6.getText(),importe,new BigDecimal(cantidad));
            
            modelo.setValueAt(importe.floatValue()+"",numRenglon , 4);            
            r_con.Connection();
            r_con.ActualizarSinCartel("update renglon_factura set rf_codigo_producto="+jTextField6.getText()+",rf_cantidad="+cantidad+", rf_no_gravado="+importe.floatValue()+",rf_impuesto_interno="+impInterno.floatValue() +", rf_importe="+importe.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            String tipoIva=tipoIvaProducto(jTextField6.getText());                        
            // IVA'S
            if((tipoIva.equals("00"))||(tipoIva.equals("0"))){
                r_con.ActualizarSinCartel("update renglon_factura set rf_exento="+importe.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
            if((tipoIva.equals("01"))||(tipoIva.equals("1"))){
                r_con.ActualizarSinCartel("update renglon_factura set rf_iva_general="+iva.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
            if((tipoIva.equals("02"))||(tipoIva.equals("2"))){
                r_con.ActualizarSinCartel("update renglon_factura set rf_tasa_diferencial="+iva.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
            if((tipoIva.equals("03"))||(tipoIva.equals("3"))){
                r_con.ActualizarSinCartel("update renglon_factura set rf_tasa_reducida="+iva.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
            
            int sitFrenteIva=Integer.parseInt(field_situacion_IVA.getText());
            if(sitFrenteIva==2){
                BigDecimal sobretasa=calcularSobreTasa(jTextField6.getText(),importe);                
                r_con.ActualizarSinCartel("update renglon_factura set rf_sobretasa="+sobretasa.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
            if(sitFrenteIva==7){
                BigDecimal importeConIva=sumarBigDecimal(importe+"",iva+"");
                BigDecimal sobretasa=calcularSobreTasa(jTextField6.getText(),importeConIva);               
               r_con.ActualizarSinCartel("update renglon_factura set rf_sobretasa="+sobretasa.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+modificar);
            }
                                            
            actualizar();
            modificar=-1;
        }
    }         
   
    
    private void habilitarPanel1(boolean valor){
        field_nombre.setFocusable(false);
        field_cuil_1.setFocusable(false);
        field_cuil_2.setFocusable(false);
        field_cuil_3.setFocusable(false);
        field_direccion_calle.setFocusable(false);
        field_direccion_nro.setFocusable(false);
        field_localidad.setFocusable(false);
        field_situacion_IVA.setFocusable(false);
        field_tipo_comprobante.setFocusable(valor);
        field_tipo_comprobante.setEditable(valor);        
        field_punto_venta.setFocusable(valor);
        field_punto_venta.setEditable(valor);
        field_nro_cliente.setFocusable(valor);
        field_nro_cliente.setEditable(valor);
        fecha_factura.setFocusable(valor);
        fecha_factura.setEditable(valor);
        //btn_confirmar_encabezado.setEnabled(valor);        
    }
    
    private void habilitarPanel2(boolean valor){        
        jTextField7.setFocusable(false);
        jTextField12.setFocusable(false);
        jTextField6.setEnabled(valor);      
        jTextField8.setEnabled(valor);
        jTextField12.setEnabled(valor);
        jTextField7.setEnabled(valor);
        jTextField7.setEditable(false);
        jTextField12.setEditable(false);
        boton7.setEnabled(valor);
        jButton4.setEnabled(valor);
    }
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton7;
    private javax.swing.JButton btn_confirmar_encabezado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField fecha_factura;
    private javax.swing.JTextField field_cuil_1;
    private javax.swing.JTextField field_cuil_2;
    private javax.swing.JTextField field_cuil_3;
    private javax.swing.JTextField field_direccion_calle;
    private javax.swing.JTextField field_direccion_nro;
    private javax.swing.JTextField field_exento;
    private javax.swing.JTextField field_impuesto_interno;
    private javax.swing.JTextField field_iva_general;
    private javax.swing.JTextField field_localidad;
    private javax.swing.JTextField field_no_gravado;
    private javax.swing.JTextField field_nombre;
    private javax.swing.JTextField field_nro_cliente;
    private javax.swing.JTextField field_punto_venta;
    private javax.swing.JTextField field_situacion_IVA;
    private javax.swing.JTextField field_sobretasa;
    private javax.swing.JTextField field_tasa_diferencial;
    private javax.swing.JTextField field_tasa_reducida;
    private javax.swing.JTextField field_tipo_comprobante;
    private javax.swing.JTextField field_total_iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel label_mensaje;
    private javax.swing.JLabel label_numero_factura;
    private javax.swing.JLabel label_situacion;
    private javax.swing.JLabel label_tipo_comprobante;
    private javax.swing.JPanel panel_datos_factura;
    // End of variables declaration//GEN-END:variables



    private void inicializarTabla() {        
        modelo=new DefaultTableModelAsientos();
        String [] columnas= {"Codigo Producto","Descripcion","Cantidad","Precio","Importe"};
        modelo.setColumnIdentifiers(columnas); 
        jTable1.setModel(modelo);        
    }

    private void mensajeError(String msj) {
        label_mensaje.setForeground(Color.red);
        label_mensaje.setText(msj);        
    }
    
    private void cargarRecibo(){
        String descripcionProducto=jTextField12.getText();                       
        float preVenta=Float.parseFloat(jTextField7.getText());
        BigDecimal precioVenta=convertirEnBigDecimal(preVenta+"");
        String sql="insert into renglon_factura values("+numeroControl+","+renglon+",0,0,0,0,0,0,0,0,0,"+precioVenta.floatValue()+",0,'"+descripcionProducto+"')";
        r_con.Connection();
        r_con.InsertarSinCartel(sql);
        String []aux_modelo={descripcionProducto,precioVenta+""};
        r_con.cierraConexion();
        modeloRecibo.addRow(aux_modelo);  
        actualizarTotal();
        jTable1.setModel(modeloRecibo);
        
        renglon++;
    }
    
    
    private void cargarTabla(){
        String descripcionProducto=jTextField12.getText();                       
        float preVenta=Float.parseFloat(jTextField7.getText());
        BigDecimal precioVenta=convertirEnBigDecimal(preVenta+"");
        int cantidad=Integer.parseInt(jTextField8.getText());        
        String cod_prod=jTextField6.getText();                              
        BigDecimal subSinIva=precioVenta.multiply(new BigDecimal(cantidad));                                
        String pre_venta=jTextField7.getText();        
        BigDecimal iva=calcularIva(cod_prod,subSinIva);
        
        r_con.Connection();
        BigDecimal sub=analizarTipoCliente(cod_prod,iva,subSinIva,new BigDecimal(cantidad));
                
        String sql="insert into renglon_factura values("+numeroControl+","+renglon+","+cod_prod+","+cantidad+",0,0,0,0,0,0,0,"+sub+",0,'')";
        r_con.InsertarSinCartel(sql);
        actualizarIvas(cod_prod,iva,subSinIva,renglon,new BigDecimal(cantidad));
        
        BigDecimal impInterno=calcularImpInterno(cod_prod,subSinIva,new BigDecimal(cantidad));
        BigDecimal anterior=convertirEnBigDecimal(field_impuesto_interno.getText());
        anterior=sumarBigDecimal(anterior+"",impInterno+"");
        field_impuesto_interno.setText(anterior+"");                        
        
        String []aux_modelo={cod_prod,descripcionProducto,cantidad+"",pre_venta,sub+""};
        modelo.addRow(aux_modelo);  
        actualizarTotal();
        jTable1.setModel(modelo);
        
        renglon++;
    }
    
    
    
    private BigDecimal calcularImpInterno(String codProducto,BigDecimal sub,BigDecimal cantidad){
        r_con.Connection();
        ResultSet rs=r_con.Consultar("select prod_impuesto_porcentaje,prod_impuesto_valor from productos where prod_codigo="+codProducto);
        BigDecimal impuestoInterno=new BigDecimal(0);
        try{
            if(rs.next()){
                if(rs.getFloat(1)!=0){
                    impuestoInterno=sub.multiply(new BigDecimal(rs.getFloat(1))).divide(new BigDecimal(100));
                }
                if(rs.getFloat(2)!=0){
                    impuestoInterno=cantidad.multiply(new BigDecimal(rs.getFloat(2)));
                }
           
            }                 
        }
        catch(Exception e){}
        return impuestoInterno;
    }
    
    
 private BigDecimal calcularSobreTasa(String codProducto, BigDecimal sub){
        BigDecimal bigAux=new BigDecimal(0);
        try{        
            float iva=0;                  
            ResultSet rs=r_con.Consultar("select tasa_sobretasa " +
                                         "from tasas_iva,productos "+
                                         "where tasa_tipo=prod_tasa_iva and prod_codigo="+codProducto+" and ('"+fecha_factura.getText()+"' between tasa_desde and tasa_hasta)");      
            if(rs.next()){
                iva=rs.getFloat(1);
            }        
            bigAux=sub.multiply(new BigDecimal(iva)).divide(new BigDecimal(100));        
        }catch(Exception e){}
        r_con.ActualizarSinCartel("update renglon_factura set rf_sobretasa="+bigAux.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+renglon);
        
        return bigAux;
    }
    
    private BigDecimal analizarTipoCliente(String codProducto,BigDecimal iva,BigDecimal sub,BigDecimal cantidad){
        int sitFrenteIva=Integer.parseInt(field_situacion_IVA.getText());        
        BigDecimal impuestoInterno=calcularImpInterno(codProducto,sub,cantidad);
                
        if((sitFrenteIva==3)||(sitFrenteIva==4)||(sitFrenteIva==5)||(sitFrenteIva==6)){ //MONOTRIBUTISTA o exento o no responsable o consumidor final
            sub=sumarBigDecimal(iva+"",sub+"");            
            sub=sumarBigDecimal(sub+"",impuestoInterno+"");   
        }
        if(sitFrenteIva==2){
            BigDecimal sobretasa=calcularSobreTasa(codProducto,sub);
            sub=sumarBigDecimal(iva+"",sub+"");            
            sub=sumarBigDecimal(sub+"",impuestoInterno+"");
            sub=sumarBigDecimal(sub+"",sobretasa+"");
            String labSobretasa=field_sobretasa.getText();
            field_sobretasa.setText(sumarBigDecimal(labSobretasa,sobretasa+"")+"");
        }
        if(sitFrenteIva==7){
            //neto + iva * sobretasa
            BigDecimal netoConIva=sumarBigDecimal(iva+"",sub+"");
            BigDecimal sobretasa=calcularSobreTasa(codProducto,netoConIva);
                        
            sub=sumarBigDecimal(netoConIva+"",impuestoInterno+"");
            sub=sumarBigDecimal(sub+"",sobretasa+"");
            String labSobretasa=field_sobretasa.getText();
            field_sobretasa.setText(sumarBigDecimal(labSobretasa,sobretasa+"")+"");
        }                
    return sub;
    }
    
    private boolean esExento(String codProducto){
       String tipoIva=tipoIvaProducto(codProducto);
       if((tipoIva.equals("0"))||(tipoIva.equals("00")))        
            return true;
       return false;           
    }  
    
    
    private void actualizarTotal(){
        if(!reciboExiste){
            BigDecimal subtotal=new BigDecimal(0);        
            for(int i=0;i<jTable1.getModel().getRowCount();i++){
                if(!esExento(modelo.getValueAt(i,0).toString()))
                    subtotal=this.sumarBigDecimal(subtotal+"", modelo.getValueAt(i,4).toString());
            }
            int sitFrenteIva=Integer.parseInt(field_situacion_IVA.getText());
            BigDecimal total=new BigDecimal(0);        
            BigDecimal totalIva=new BigDecimal(0);
            totalIva=this.sumarBigDecimal(totalIva+"", field_iva_general.getText());
            totalIva=this.sumarBigDecimal(totalIva+"", field_tasa_diferencial.getText());
            totalIva=this.sumarBigDecimal(totalIva+"", field_tasa_reducida.getText());            
            field_total_iva.setText(totalIva+"");
            if(sitFrenteIva==1){  // si es RESPONSABLE INSCRIPTO
                total=this.sumarBigDecimal(total+"", convertirEnBigDecimal(field_exento.getText())+"");
                total=this.sumarBigDecimal(total+"", convertirEnBigDecimal(field_no_gravado.getText())+"");
                total=this.sumarBigDecimal(total+"", convertirEnBigDecimal(field_total_iva.getText())+"");            
                total=this.sumarBigDecimal(total+"", convertirEnBigDecimal(field_sobretasa.getText())+"");
                total=this.sumarBigDecimal(total+"", convertirEnBigDecimal(field_impuesto_interno.getText())+"");                        
                jTextField11.setText(total+"");
                jTextField10.setText(field_no_gravado.getText()+"");        
            }        
            else
            {
                total=this.sumarBigDecimal(subtotal+"", convertirEnBigDecimal(field_exento.getText())+"");
                jTextField11.setText(total+"");
                jTextField10.setText(field_no_gravado.getText()+"");
            }                
        }
        else  // en caso de que sea un recibo
        {
            BigDecimal subtotal=new BigDecimal(0);
            for(int i=0;i<jTable1.getModel().getRowCount();i++){
                subtotal=this.sumarBigDecimal(subtotal+"", jTable1.getModel().getValueAt(i,1).toString());
            }
            jTextField11.setText(subtotal+"");
        }
    }
    
    private String tipoIvaProducto(String codProducto){
        r_con.Connection();
        ResultSet rs=r_con.Consultar("select prod_tasa_iva from productos where prod_codigo="+codProducto);
        String aux="-1";
        try{
            if(rs.next()){
                aux=rs.getString(1);
            }
        }
        catch(Exception e){}
        return aux;
    }

    
    private void actualizar(){        
        inicializarIvas();
        int sitFrenteIva=Integer.parseInt(field_situacion_IVA.getText());
        for(int i=0;i<jTable1.getModel().getRowCount();i++){
            //"Codigo Producto","Descripcion","Cantidad","Precio","Importe"
            String codProducto=(String)modelo.getValueAt(i, 0);
            BigDecimal cantidad=convertirEnBigDecimal(modelo.getValueAt(i, 2)+"");
                        
            BigDecimal importe=convertirEnBigDecimal((String)modelo.getValueAt(i,4));
            String tipoIva=tipoIvaProducto(codProducto);            
            BigDecimal iva=calcularIva(codProducto,importe);
            // IVA'S
            if((tipoIva.equals("00"))||(tipoIva.equals("0"))){
                BigDecimal auxEx=sumarBigDecimal(field_exento.getText(),importe+"");
                field_exento.setText(auxEx+"");
            }
            if((tipoIva.equals("01"))||(tipoIva.equals("1"))){
                BigDecimal aux=sumarBigDecimal(field_iva_general.getText(),iva+"");
                field_iva_general.setText(aux+"");
            }
            if((tipoIva.equals("02"))||(tipoIva.equals("2"))){
                BigDecimal aux=sumarBigDecimal(field_tasa_diferencial.getText(),iva+"");
                field_tasa_diferencial.setText(aux+"");
            }
            if((tipoIva.equals("03"))||(tipoIva.equals("3"))){
                BigDecimal aux=sumarBigDecimal(field_tasa_reducida.getText(),iva+"");
                field_tasa_reducida.setText(aux+"");
            }
            // SOBRETASA        
            if(sitFrenteIva==2){
                BigDecimal sobretasa=calcularSobreTasa(codProducto,importe);
                BigDecimal aux=sumarBigDecimal(field_sobretasa.getText(),sobretasa+"");
                field_sobretasa.setText(aux+"");
            }
            if(sitFrenteIva==7){
                BigDecimal importeConIva=sumarBigDecimal(importe+"",iva+"");
                BigDecimal sobretasa=calcularSobreTasa(codProducto,importeConIva);
                BigDecimal aux=sumarBigDecimal(field_sobretasa.getText(),sobretasa+"");
                field_sobretasa.setText(aux+"");
            }            
            // NO GRAVADO
            if(!esExento(codProducto)){
                BigDecimal aux=sumarBigDecimal(field_no_gravado.getText(),importe+"");
                field_no_gravado.setText(aux+"");
            }
            // IMPUESTO INTERNO
            BigDecimal impInterno=calcularImpInterno(codProducto,importe,cantidad);
            BigDecimal anterior=convertirEnBigDecimal(field_impuesto_interno.getText());
            anterior=sumarBigDecimal(anterior+"",impInterno+"");
            field_impuesto_interno.setText(anterior+"");
            
        }   
        // totales
        this.actualizarTotal();
    }
    
    
    private void actualizarIvas(String codProducto, BigDecimal iva,BigDecimal sub,int reng,BigDecimal cantidad){
        r_con.Connection();
        ResultSet rs=r_con.Consultar("select prod_tasa_iva from productos where prod_codigo="+codProducto);
        try{
        if(rs.next()){
            String tipoIva=rs.getString(1);
            if((tipoIva.equals("00"))||(tipoIva.equals("0"))){
                BigDecimal ex=convertirEnBigDecimal(field_exento.getText());                
                ex=this.sumarBigDecimal(ex+"", sub+"");                
                field_exento.setText(ex+"");
                r_con.ActualizarSinCartel("update renglon_factura set rf_exento="+iva+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+reng);
            }
            if((tipoIva.equals("01"))||(tipoIva.equals("1"))){
                BigDecimal aux=convertirEnBigDecimal(field_iva_general.getText());            
                aux=this.sumarBigDecimal(aux+"", iva+"");                
                field_iva_general.setText(aux+"");
                r_con.ActualizarSinCartel("update renglon_factura set rf_iva_general="+iva+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+reng);
            }     
           if((tipoIva.equals("02"))||(tipoIva.equals("2"))){
                BigDecimal aux=convertirEnBigDecimal(field_tasa_diferencial.getText());                
                aux=this.sumarBigDecimal(aux+"", iva+"");                
                field_tasa_diferencial.setText(aux+"");
                r_con.ActualizarSinCartel("update renglon_factura set rf_tasa_diferencial="+iva+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+reng);
           }
           if((tipoIva.equals("03"))||(tipoIva.equals("3"))){
                BigDecimal aux=convertirEnBigDecimal(field_tasa_reducida.getText());                
                aux=this.sumarBigDecimal(aux+"", iva+"");                
                field_tasa_reducida.setText(aux+"");
                r_con.ActualizarSinCartel("update renglon_factura set rf_tasa_reducida="+iva+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+reng);
           }                      
        
            if((!tipoIva.equals("00"))&&(!tipoIva.equals("0"))){
                BigDecimal aux=convertirEnBigDecimal(field_no_gravado.getText());
                aux=this.sumarBigDecimal(aux+"", sub+"");
                field_no_gravado.setText(aux+"");
                BigDecimal impInterno=calcularImpInterno(codProducto,sub,cantidad);
                r_con.ActualizarSinCartel("update renglon_factura set rf_no_gravado="+sub+",rf_impuesto_interno="+impInterno.floatValue()+" where rf_encabezado_factura_id="+numeroControl+" and rf_num_renglon="+reng);
            }
            }
        }catch(Exception e){r_con.cierraConexion();}
        r_con.cierraConexion();
    }

    /**
     * Calcula el IVA segun el Tipo de Tasa de Iva del importe pasado por parametro
     * @param codigo_prod Codigo del Producto 
     * @param importe 
     * @return 
     */
    private BigDecimal calcularIva(String codigo_prod,BigDecimal importe){
        r_con.Connection();
        BigDecimal bigAux=new BigDecimal(0);
        try{        
            float iva=0;                  
            ResultSet rs=r_con.Consultar("select tasa_tasa " +
                                         "from tasas_iva,productos "+
                                         "where tasa_tipo=prod_tasa_iva and prod_codigo="+codigo_prod+" and ('"+fecha_factura.getText()+"' between tasa_desde and tasa_hasta)");      
            if(rs.next()){
                iva=rs.getFloat(1);
            }        
            bigAux=importe.multiply(new BigDecimal(iva)).divide(new BigDecimal(100));        
        } catch(Exception e){
            return null;
        } finally {
            r_con.cierraConexion();
        }
        return bigAux;
    }
    
    
    /**
     * Nos controla los campos de Leyenda y Comprobante
     * @return true en caso de que ambos esten completos false de lo contrario
     */
    private boolean controlarCampos(){        
        try{
            if(reciboExiste){
                Float.parseFloat(jTextField7.getText());
                if(!jTextField12.getText().equals(""))
                    return true;
                else
                    return false;
            }
            else{
                if((!jTextField6.getText().equals(""))&&(!jTextField8.getText().equals("")))
                    return true;
                else
                    return false;
            }
        }
        catch(Exception e){e.getMessage();return false;}
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
    
     private void ordenarFoco() {    
        field_tipo_comprobante.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,3));
        field_punto_venta.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,4));
        field_nro_cliente.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,5));
        jTextField6.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,6));
        jTextField8.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,6)); 
        
        jTextField7.setDocument(new JTextFieldFilter(JTextFieldFilter.REAL,10));
        jTextField12.setDocument(new JTextFieldFilter(60));
        
        field_tipo_comprobante.setNextFocusableComponent(field_punto_venta);
        field_punto_venta.setNextFocusableComponent(field_nro_cliente);
        field_nro_cliente.setNextFocusableComponent(fecha_factura);
        fecha_factura.setNextFocusableComponent(btn_confirmar_encabezado);
        btn_confirmar_encabezado.setNextFocusableComponent(field_tipo_comprobante);               
    }
            
    private void actualizarLabelIva(){
        r_con.Connection();
        ResultSet rs=r_con.Consultar("select * from tasas_iva where '"+fecha_factura.getText()+"' between tasa_desde and tasa_hasta");
        try{            
            while(rs.next()){
                if(rs.getString("tasa_tipo").equals("01")){                    
                    jLabel3.setText("IVA "+rs.getFloat("tasa_tasa")+"%");
                }
                if(rs.getString("tasa_tipo").equals("02")){                    
                    jLabel4.setText("IVA "+rs.getFloat("tasa_tasa")+"%");
                }
                if(rs.getString("tasa_tipo").equals("03")){                    
                    jLabel5.setText("IVA "+rs.getFloat("tasa_tasa")+"%");
                }
            }
        }
        
        catch(Exception e){}
        finally{r_con.cierraConexion();}
    }
            
    private void esNegativo(){
        int tipoComprobante= Integer.parseInt(field_tipo_comprobante.getText());
        r_con.Connection();
        ResultSet rs=r_con.Consultar("select tc_activo from tipo_comprobante where tc_codigo="+tipoComprobante);
        try{
            if(rs.next()){
                if(!rs.getBoolean(1)){
                    r_con.ActualizarSinCartel("update renglon_factura set rf_iva_general=(-1*rf_iva_general),rf_tasa_diferencial=(-1*rf_tasa_diferencial),rf_sobretasa=(-1*rf_sobretasa),rf_exento=(-1*rf_exento), rf_tasa_reducida=(-1*rf_tasa_reducida),rf_no_gravado=(-1*rf_no_gravado),rf_impuesto_interno=(-1*rf_impuesto_interno),rf_importe=(-1*rf_importe) where rf_encabezado_factura_id="+numeroControl);
                    r_con.ActualizarSinCartel("update encabezado_factura set ef_iva_general=(-1*ef_iva_general),ef_tasa_diferencial=(-1*ef_tasa_diferencial),ef_sobretasa=(-1*ef_sobretasa),ef_exento=(-1*ef_exento),ef_tasa_reducida=(-1*ef_tasa_reducida),ef_no_gravado=(-1*ef_no_gravado),ef_impuesto_interno=(-1*ef_impuesto_interno),ef_total=(-1*ef_total),ef_subtotal=(-1*ef_subtotal) where ef_encabezado_factura_id="+numeroControl);
                }                    
            }
        }catch(Exception e){}
        finally{r_con.cierraConexion();}
    }
    
    private void guardarDatos(){               
        if(jTable1.getModel().getRowCount()>0){            
            String ivaGeneral=field_iva_general.getText();
            String tasaDiferencial=field_tasa_diferencial.getText();
            String tasaReducida=field_tasa_reducida.getText();
            String exento=field_exento.getText();
            String sobretasa=field_sobretasa.getText();
            String noGravado=field_no_gravado.getText();
            String total=jTextField11.getText();
            String subtotal=jTextField10.getText();
            String impuestoInterno=field_impuesto_interno.getText();
            String totalIva=field_total_iva.getText();
            
            r_con.Connection();
            r_con.ActualizarSinCartel("update encabezado_factura set ef_iva_general="+ivaGeneral+",ef_tasa_diferencial="+tasaDiferencial+",ef_sobretasa="+sobretasa+",ef_exento="+exento+",ef_tasa_reducida="+tasaReducida+",ef_no_gravado="+noGravado+",ef_impuesto_interno="+impuestoInterno+",ef_subtotal="+subtotal+",ef_total="+total+",ef_total_iva="+totalIva+",ef_confirmado=1 where ef_encabezado_factura_id="+numeroControl);
            r_con.ActualizarSinCartel("update renglon_factura set rf_confirmado=1 where rf_encabezado_factura_id="+numeroControl);
            int ptoVenta=Integer.parseInt(field_punto_venta.getText());
            int tipoComprobante=Integer.parseInt(field_tipo_comprobante.getText());
            
            r_con.ActualizarSinCartel("update ptoventa_x_tipocomprobante set vxc_numero="+ultimoNumeroFactura+" where vxc_id_pto_venta="+ptoVenta+" and vxc_id_tipo_comprobante="+tipoComprobante);
            esNegativo();                                   
            r_con.cierraConexion();
        }        
            label_mensaje.setText("La factura no contiene ningun renglon");
        
    }
    
    
    private void identificarReporte (int nro){        
        switch(nro) {
            case 1: 
                reporte_seleccionado = "modelo_factura_A.jrxml";
                break;
            case 2: 
                reporte_seleccionado = "modelo_factura_A.jrxml";
                break;
            case 3: 
                reporte_seleccionado = "modelo_factura_A.jrxml";
                break;
            case 4: 
                reporte_seleccionado = "modelo_recibo.jrxml";
                break;
            case 5: 
                reporte_seleccionado = "modelo_factura_A.jrxml";
                break;
            case 6: 
                reporte_seleccionado = "modelo_factura_B.jrxml";
                break;
            case 7: 
                reporte_seleccionado = "modelo_factura_B.jrxml";
                break;
            case 8: 
                reporte_seleccionado = "modelo_factura_B.jrxml";
                break;
            case 9: 
                reporte_seleccionado = "modelo_recibo.jrxml";
                break;
            case 10: 
                reporte_seleccionado = "modelo_factura_B.jrxml";
                break;
            default: 
                reporte_seleccionado = null;
                break;
        }
    }
    
    private void imprimir () {   
        r_con.Connection();
        identificarReporte(Integer.parseInt(field_tipo_comprobante.getText()));          
        try {
            if (reporte_seleccionado != null){
                String message = "Esta a punto de imprimir su Comprobantes, ¿Está seguro?:" ;
                int rta = JOptionPane.showConfirmDialog(null, message, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (rta==JOptionPane.YES_OPTION){                    
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

                        //si hay impresora para el modulo
                        if (impresora != null){                            
                            
                            r_con.Connection();
                            //cargo Parametros del Reporte
                            Map parametros = new HashMap();
                            //localizo el reporte para usarlo                            
                             JasperReport report = JasperCompileManager.compileReport("src/Reportes/"+reporte_seleccionado);
                            //cargo los datos al reporte                            
                            //JasperPrint print = JasperFillManager.fillReport(report, parametros, r_con.getConn());

                            
                            String [] separar = fecha_factura.getText().split("/");
                            parametros.put("dia", separar[0]);
                            parametros.put("mes", separar[1]);
                            parametros.put("anio", separar[2]);
                            parametros.put("nombre", cliente_factura.getApellido_cliente()+", "+cliente_factura.getNombre_cliente());
                            parametros.put("cuil", cliente_factura.getCuil());
                            parametros.put("domicilio", cliente_factura.getDireccion());
                            parametros.put("iva", cliente_factura.getDescripcion_situacion_IVA_cliente());
                            parametros.put("control", Integer.toString(numeroControl));
                            parametros.put("total", jTextField11.getText());                             

                            if (reporte_seleccionado.equals("modelo_factura_A.jrxml")){
                                if ((!field_tasa_reducida.getText().equals("")) && (!field_tasa_reducida.getText().equals("0"))){
                                    separar = jLabel5.getText().split(" ");
                                    parametros.put("tasa_1", separar[1].trim());
                                    parametros.put("valor_tasa_1",field_tasa_reducida.getText());
                                }
                                if ((!field_iva_general.getText().equals("")) && (!field_iva_general.getText().equals("0"))){
                                    separar = jLabel3.getText().split(" ");
                                    parametros.put("tasa_2", separar[1].trim());
                                    parametros.put("valor_tasa_2",field_iva_general.getText());
                                }
                                if ((!field_tasa_diferencial.getText().equals("")) && (!field_tasa_diferencial.getText().equals("0"))){
                                    separar = jLabel4.getText().split(" ");
                                    parametros.put("tasa_3", separar[1].trim());
                                    parametros.put("valor_tasa_3",field_tasa_diferencial.getText());
                                }                                
                                parametros.put("total_iva", field_total_iva.getText());
                                parametros.put("exento", field_exento.getText());
                                parametros.put("sobretasa", field_sobretasa.getText());
                                parametros.put("conceptos_no_gravados", field_impuesto_interno.getText());
                                parametros.put("neto", field_no_gravado.getText());
                            }
                            else{
                                if (reporte_seleccionado.equals("modelo_recibo.jrxml")){
                                    String enletra = convertNumberToLetter(jTextField11.getText());                                    
                                    parametros.put("valor_letra", enletra);
                                }
                            }
                            
                            JasperPrint print = JasperFillManager.fillReport(report, parametros, r_con.getConn());
                            JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                            jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, print );
                            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora );
                            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                            
                            if (print.getPages().size()>0){
                                jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, print );
                                jrprintServiceExporter.exportReport();                                
                                guardarDatos();
                                r_con.Connection();
                                r_con.ActualizarSinCartel("update parametros_facturacion set pf_fecha_ultima_factura='"+fecha_factura.getText()+"'");
                                this.inicializarTabla();
                                this.inicializarIvas();
                                this.vaciar_panel_datos();
                                this.habilitarPanel1(true);
                                this.habilitarPanel2(false);
                                label_mensaje.setForeground(Color.green);
                                label_mensaje.setText("La factura fue cargada correctamente");
                                field_tipo_comprobante.requestFocusInWindow();
                                renglon=0;reciboExiste=false;                                
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "El Reporte está vacío.","Atención",JOptionPane.WARNING_MESSAGE);
                            }                            
                        }                                          
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No hay Impresoras asignadas a este Modulo, "
                            + "\npóngase en contacto con el Administrador de Impresoras.","Atención",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No hay Reporte asignado a este Comprobante, "
                           + "\npóngase en contacto con el Administrador.","Atención",JOptionPane.WARNING_MESSAGE);
            }
        } catch (JRException ex) {
            ex.printStackTrace();
        } finally {            
            r_con.cierraConexion();
        }   
     }        
    
}
