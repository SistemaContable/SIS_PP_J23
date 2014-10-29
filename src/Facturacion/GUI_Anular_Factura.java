/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facturacion;

import Interface.*;
import Clases_Auxiliares.Conexion;
import Clases_Auxiliares.Fechas;
import Contabilidad.DefaultTableModelAsientos;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class GUI_Anular_Factura extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUI_A_Prod
     */
    private Conexion r_con;
    private ResultSet rs;
    private Fechas fecha=new Fechas();
    private DefaultTableModel modelo;
    private int facturaActual=1;
    
    
    
    
    public GUI_Anular_Factura(Conexion r) {
        initComponents();
        ocultarMensaje();
        lab_anulada.setText(" ");
        r_con=r;
        r_con.Connection();  
        rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                
        try{
            rs.next();
        }
        catch(Exception e){}        
        cargarFactura();
    }

    private void cargarFactura(){
        try{
            String numControl=rs.getString("ef_encabezado_factura_id");
            cargarDatosCliente(rs.getString("ef_cliente"));                        
            
            String numero_com = String.format("%02d", Integer.parseInt(rs.getString("ef_tipo_comprobante")));
            String numero_pto = String.format("%04d", Integer.parseInt(rs.getString("ef_punto_venta")));                                        
            String numero_fac = String.format("%08d", Integer.parseInt(rs.getString("ef_num_ptoVenta_tipoComp")));
            campoFacturaNum.setText(numero_com+"-"+numero_pto+"-"+numero_fac);
            
            jTextField2.setText(rs.getString("ef_cliente"));
            fecha_factura.setText(fecha.convertirBarras(rs.getString("ef_fecha_facturacion")));
            field_iva_general.setText(rs.getString("ef_iva_general"));
            field_sobretasa.setText(rs.getString("ef_sobretasa"));
            field_tasa_diferencial.setText(rs.getString("ef_tasa_diferencial"));
            field_tasa_reducida.setText(rs.getString("ef_tasa_reducida"));
            field_exento.setText(rs.getString("ef_exento"));    
            field_impuesto_interno.setText(rs.getString("ef_impuesto_interno"));
            field_no_gravado.setText(rs.getString("ef_no_gravado"));
            field_total_iva.setText(rs.getString("ef_total_iva"));
            jTextField10.setText(rs.getString("ef_subtotal"));
            jTextField11.setText(rs.getString("ef_total")); 
            boolean anulada=rs.getBoolean("ef_anulada");
            if(anulada)
                lab_anulada.setText("ANULADA");
            else
                lab_anulada.setText(" ");
            cargarRenglones();
        
        }
        catch(Exception e){System.out.println("Cargar Factura ERROR");}
    }
    
    
    private void cargarDatosCliente(String codCliente){
        try
        {
            ResultSet rsAux=r_con.Consultar("select * from clientes,localidades where cli_localidad=loc_id and cli_codigo='"+codCliente+"'");
            if(rsAux.next()){
                field_nombre.setText(rsAux.getString("cli_apellido")+", "+rsAux.getString("cli_nombre"));
                field_cuil.setText(rsAux.getString("cli_cuit"));
                field_direccion_calle.setText(rsAux.getString("cli_direccion"));
                field_direccion_nro.setText(rsAux.getString("cli_calle"));
                field_localidad.setText(rsAux.getString("loc_descripcion"));
                field_situacion_IVA.setText(rsAux.getString("cli_sit_frente_iva"));                
            }            
        }
        catch(Exception e){System.out.println("Cargar Datos Cliente ERROR");}
    }
    
    private void cargarRenglones(){
        try{
            String numFactura=rs.getString("ef_encabezado_factura_id");
            ResultSet rsAux=r_con.Consultar("select * from renglon_factura,productos where rf_codigo_producto=prod_codigo and rf_encabezado_factura_id="+numFactura);
            inicializarTabla();
            while(rsAux.next()){                
                String cod_prod=rsAux.getString("rf_codigo_producto");
                String descripcionProducto=rsAux.getString("prod_descripcion");
                int cantidad=rsAux.getInt("rf_cantidad");
                String pre_venta=rsAux.getString("prod_precio_neto_venta");
                String sub=rsAux.getString("rf_importe");
                String []aux_modelo={cod_prod,descripcionProducto,cantidad+"",pre_venta,sub+""};
                modelo.addRow(aux_modelo);          
                //tabla.setModel(modelo);                                
            }                        
        }
        catch(Exception e){}
    }
    
    private void inicializarTabla() {        
        modelo=new DefaultTableModel();
        String [] columnas= {"Codigo Producto","Descripcion","Cantidad","Precio","Importe"};
        modelo.setColumnIdentifiers(columnas); 
        tabla.setModel(modelo);        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lab_tipo = new javax.swing.JLabel();
        campoFacturaNum = new javax.swing.JTextField();
        lab_desde = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        lab_hasta = new javax.swing.JLabel();
        fecha_factura = new javax.swing.JFormattedTextField();
        field_nombre = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        field_cuil = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        field_situacion_IVA = new javax.swing.JTextField();
        field_localidad = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        field_direccion_nro = new javax.swing.JTextField();
        field_direccion_calle = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        lab_mensaje = new javax.swing.JLabel();
        panel_ayuda = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
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
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panel_desplazamiento = new javax.swing.JPanel();
        btn_primero = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        btn_proximo = new javax.swing.JButton();
        btn_ultimo = new javax.swing.JButton();
        campo_buscar1 = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        lab_buscar = new javax.swing.JLabel();
        campo_buscar3 = new javax.swing.JTextField();
        campo_buscar2 = new javax.swing.JTextField();
        field_buscar3 = new javax.swing.JTextField();
        lab_buscar1 = new javax.swing.JLabel();
        lab_buscar2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        lab_anulada = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Anular Facturas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/articulo.png"))); // NOI18N
        setInheritsPopupMenu(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lab_tipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lab_tipo.setText("Factura Nº:");

        campoFacturaNum.setEditable(false);

        lab_desde.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lab_desde.setText("Cliente Nº:");

        jTextField2.setEditable(false);

        lab_hasta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lab_hasta.setText("Fecha Operacion:");

        fecha_factura.setEditable(false);
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

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Sr./Sra.:");

        field_cuil.setEditable(false);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("CUIL:");

        field_situacion_IVA.setEditable(false);

        field_localidad.setEditable(false);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Localidad:");

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Sit. IVA");

        field_direccion_nro.setEditable(false);

        field_direccion_calle.setEditable(false);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Direccion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_tipo)
                    .addComponent(lab_hasta)
                    .addComponent(fecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFacturaNum, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(field_direccion_calle, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_direccion_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_localidad)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_situacion_IVA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_desde))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(field_cuil)
                                .addGap(17, 17, 17)))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_desde)
                            .addComponent(lab_tipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_cuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoFacturaNum, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(lab_hasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_direccion_calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_direccion_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_situacion_IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lab_mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lab_mensaje.setForeground(java.awt.Color.red);
        lab_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_mensaje.setText("mensaje");

        panel_ayuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("TOTAL:");

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Subtotal:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(field_iva_general, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(field_tasa_diferencial, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(field_tasa_reducida, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_exento, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(field_sobretasa)
                    .addComponent(field_impuesto_interno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(field_no_gravado, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(field_total_iva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(field_iva_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_exento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(field_total_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(field_tasa_diferencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(field_sobretasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(field_no_gravado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_impuesto_interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(field_tasa_reducida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_desplazamiento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_primero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow-circle-left-2x.png"))); // NOI18N
        btn_primero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_primeroActionPerformed(evt);
            }
        });

        btn_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow-left-2x.png"))); // NOI18N
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });

        btn_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow-right-2x.png"))); // NOI18N
        btn_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proximoActionPerformed(evt);
            }
        });

        btn_ultimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow-circle-right-2x.png"))); // NOI18N
        btn_ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ultimoActionPerformed(evt);
            }
        });

        campo_buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campo_buscar1KeyPressed(evt);
            }
        });

        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/buscar.png"))); // NOI18N
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        lab_buscar.setText("Buscar:");

        campo_buscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campo_buscar3KeyPressed(evt);
            }
        });

        campo_buscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campo_buscar2KeyPressed(evt);
            }
        });

        field_buscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_buscar3KeyPressed(evt);
            }
        });

        lab_buscar1.setText("NºFactura:");

        lab_buscar2.setText("NºControl:");

        javax.swing.GroupLayout panel_desplazamientoLayout = new javax.swing.GroupLayout(panel_desplazamiento);
        panel_desplazamiento.setLayout(panel_desplazamientoLayout);
        panel_desplazamientoLayout.setHorizontalGroup(
            panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_desplazamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lab_buscar))
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addComponent(btn_primero, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_buscar1)
                            .addComponent(lab_buscar2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addComponent(campo_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campo_buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campo_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(field_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_buscar)
                .addGap(37, 37, 37))
        );
        panel_desplazamientoLayout.setVerticalGroup(
            panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addGroup(panel_desplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_buscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addComponent(lab_buscar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_buscar2))
                    .addComponent(btn_primero, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_desplazamientoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_buscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_ayudaLayout = new javax.swing.GroupLayout(panel_ayuda);
        panel_ayuda.setLayout(panel_ayudaLayout);
        panel_ayudaLayout.setHorizontalGroup(
            panel_ayudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ayudaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ayudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_desplazamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_ayudaLayout.setVerticalGroup(
            panel_ayudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ayudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(panel_desplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        btn_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/aceptar.png"))); // NOI18N
        btn_aceptar.setText("ANULAR");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        lab_anulada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lab_anulada.setForeground(java.awt.Color.red);
        lab_anulada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_anulada.setText("ANULADA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lab_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(panel_ayuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(btn_aceptar)
                .addGap(31, 31, 31)
                .addComponent(btn_cancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lab_anulada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lab_anulada, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecha_facturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fecha_facturaFocusLost
      
    }//GEN-LAST:event_fecha_facturaFocusLost

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void btn_primeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_primeroActionPerformed
        try{
            ResultSet rsAux=r_con.Consultar("select min(ef_encabezado_factura_id) from encabezado_factura");                            
            if(rsAux.next()){
                int anterior=facturaActual;
                facturaActual=rsAux.getInt(1);
                rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
                if(rs.next())
                    cargarFactura();
                else
                    facturaActual=anterior;
            }
        }
        catch(Exception e){}
    }//GEN-LAST:event_btn_primeroActionPerformed

    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
try{
            facturaActual--;
            rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
            if(rs.next())
                cargarFactura();
            else
                facturaActual++;
        }
        catch(Exception e){mostrarMensaje("Esta es la primer factura");}
    }//GEN-LAST:event_btn_anteriorActionPerformed

    private void btn_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proximoActionPerformed
        try{
            facturaActual++;
            rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
            if(rs.next())
                cargarFactura();
            else
                facturaActual--;
        }
        catch(Exception e){mostrarMensaje("Esta es la ultima factura");}
        
    }//GEN-LAST:event_btn_proximoActionPerformed

    private void btn_ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ultimoActionPerformed
try{
            ResultSet rsAux=r_con.Consultar("select max(ef_encabezado_factura_id) from encabezado_factura");                            
            if(rsAux.next()){
                int anterior=facturaActual;
                facturaActual=rsAux.getInt(1);
                rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
                if(rs.next())
                    cargarFactura();
                else
                    facturaActual=anterior;
            }
        }
        catch(Exception e){}
    }//GEN-LAST:event_btn_ultimoActionPerformed

    private void campo_buscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_buscar1KeyPressed

    }//GEN-LAST:event_campo_buscar1KeyPressed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        ocultarMensaje();                
        try{            
        // buscar por numero de factura                
            if((!campo_buscar1.getText().equals(""))&&(!campo_buscar1.getText().equals(""))&&(!campo_buscar1.getText().equals(""))){
                int num1,num2,num3;
                num1=Integer.parseInt(campo_buscar1.getText());
                num2=Integer.parseInt(campo_buscar2.getText());
                num3=Integer.parseInt(campo_buscar3.getText());
                ResultSet rsAux1=r_con.Consultar("select ef_encabezado_factura_id from encabezado_factura where ef_tipo_comprobante="+num1+" and ef_punto_venta="+num2+" and ef_num_ptoVenta_tipoComp="+num3);            

                if(rsAux1.next()){
                    facturaActual=rsAux1.getInt(1);
                    rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
                    if(rs.next())
                        cargarFactura();
                }
                else{
                    mostrarMensaje("La factura no existe");
                }
            }                
            // buscar por numero de control
            if(!field_buscar3.getText().equals("")){
                int numControl=Integer.parseInt(field_buscar3.getText());
                ResultSet rsAux=r_con.Consultar("select ef_encabezado_factura_id from encabezado_factura where ef_numero_control="+numControl);            

                if(rsAux.next()){
                    facturaActual=rsAux.getInt(1);
                    rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
                    if(rs.next())
                        cargarFactura();
                }
                else{
                    mostrarMensaje("El numero de control no existe");
                }
            } 
            borrarBuscar();
        }
        catch(Exception e){}                        
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void campo_buscar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_buscar3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_buscar3KeyPressed

    private void campo_buscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_buscar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_buscar2KeyPressed

    private void field_buscar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_buscar3KeyPressed
        
        
    }//GEN-LAST:event_field_buscar3KeyPressed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        int rta=JOptionPane.showConfirmDialog(null,"La factura será Anulada. ¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);                            
        if (rta==JOptionPane.YES_OPTION){    
            r_con.ActualizarSinCartel("update encabezado_factura set ef_anulada=1, ef_subtotal=0, ef_total=0,ef_no_gravado=0,ef_total_iva=0,ef_impuesto_interno=0,ef_tasa_reducida=0,ef_exento=0,ef_sobretasa=0,ef_tasa_diferencial=0,ef_iva_general=0 where ef_encabezado_factura_id="+facturaActual);        
            rs=r_con.Consultar("select * from encabezado_factura where ef_encabezado_factura_id="+facturaActual);                            
            try{
            if(rs.next())
                cargarFactura();
            }
            catch(Exception e){}        
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        r_con.cierraConexion();
        dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    
        
    private boolean camposNecesarios () {
       if ((campoFacturaNum.getText().length()==0)) 
       {
            JOptionPane.showMessageDialog(null, "Complete todos los campos!","Atención",JOptionPane.WARNING_MESSAGE);
            return false;
       }
       return true;      
               
    }
    private void mensaje (Component c){
        KeyEvent ke = new KeyEvent(c, KeyEvent.KEY_PRESSED,
        System.currentTimeMillis(), InputEvent.CTRL_MASK, KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);
        c.dispatchEvent(ke);
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_primero;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_ultimo;
    private javax.swing.JTextField campoFacturaNum;
    private javax.swing.JTextField campo_buscar1;
    private javax.swing.JTextField campo_buscar2;
    private javax.swing.JTextField campo_buscar3;
    private javax.swing.JFormattedTextField fecha_factura;
    private javax.swing.JTextField field_buscar3;
    private javax.swing.JTextField field_cuil;
    private javax.swing.JTextField field_direccion_calle;
    private javax.swing.JTextField field_direccion_nro;
    private javax.swing.JTextField field_exento;
    private javax.swing.JTextField field_impuesto_interno;
    private javax.swing.JTextField field_iva_general;
    private javax.swing.JTextField field_localidad;
    private javax.swing.JTextField field_no_gravado;
    private javax.swing.JTextField field_nombre;
    private javax.swing.JTextField field_situacion_IVA;
    private javax.swing.JTextField field_sobretasa;
    private javax.swing.JTextField field_tasa_diferencial;
    private javax.swing.JTextField field_tasa_reducida;
    private javax.swing.JTextField field_total_iva;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lab_anulada;
    private javax.swing.JLabel lab_buscar;
    private javax.swing.JLabel lab_buscar1;
    private javax.swing.JLabel lab_buscar2;
    private javax.swing.JLabel lab_desde;
    private javax.swing.JLabel lab_hasta;
    private javax.swing.JLabel lab_mensaje;
    private javax.swing.JLabel lab_tipo;
    private javax.swing.JPanel panel_ayuda;
    private javax.swing.JPanel panel_desplazamiento;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables


public void borrarBuscar(){
    campo_buscar1.setText("");
    campo_buscar2.setText("");
    campo_buscar3.setText("");
    field_buscar3.setText("");    
}
    
public void ocultarMensaje(){
    lab_mensaje.setText(" ");
}

public void mostrarMensaje(String text){
    lab_mensaje.setText(text);
}
    
public void form_onlySearch (){
    //this.jTextField2.setEnabled(false);
}

public void form_Complete (){
    //this.jTextField2.setEnabled(true);
}

public void limpiarForm(){
    campoFacturaNum.setText("");
}

}
