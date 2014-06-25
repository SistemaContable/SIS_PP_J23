package Clases_Auxiliares;

/**
 * @author Manolo
 * La siguiente clase modula la conexion con la base de datos,
 * guarda el url, el usuario, la contraseña, etc.
 * Los datos son almacenados en un archivo de texto plano, por lo
 * que seria conveniente usar una sola conexion, abrirla y cerrarla.
 */

import Interface.GUI_Conexion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
 
public class Conexion{
 
    
    private String jdbc;
    private String url;
    private String port;
    private boolean seguridad_integrada;
    private String usuario;
    private String clave;    
    private String driverClassName;
    
    private Connection conn = null;
    private Statement stnt;
    private ResultSet rslset;
    private String nombre_archivo = "dat_conexion.dat";
 
//CONSTRUCTORES
     
    public Conexion() {
        this.jdbc = "jdbc:sqlserver://";
        this.url = "localhost";
        this.port = ":1433";
        this.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";        
    }
 
    //metodos para recuperar los datos de conexion
    public String getUrl() {
        return url;
    }
    
    public String getPort() {
        return port;
    }   

    public boolean getSeguridad_integrada() {
        return seguridad_integrada;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public String getClave() {
        return clave;
    }    
 
    public Connection getConn() {
        return conn;
    }
 
    public String getDriverClassName() {
        return driverClassName;
    }
 
    //metodos para establecer los valores de conexion 
    public void setUrl(String url) {
        this.url = url;
    }
    
     public void setPort(String port) {
        this.port = port;
    }
     
    public void setSeguridad_integrada(boolean seguridad_integrada) {
        this.seguridad_integrada = seguridad_integrada;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
 
    public void setConn(Connection conn) {
        this.conn = conn;
    }
 
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
 
//la conexion propiamente dicha
    
    public boolean validarConexion (String n_url, String n_port, boolean seg_int, String n_usu, String n_cla){
        boolean valida = false;
        
        if (! n_port.equals("")){
            port = n_port;
        }
        else{
            port = "1433";
        }
        
        String urlConexion = jdbc+n_url+":"+port+";";
        
        if (seg_int){
            urlConexion +="integratedSecurity=true;";
        }
        else{
             urlConexion +="user="+n_usu+";password="+n_cla+";";
        }
        
        try {
            conn = DriverManager.getConnection(urlConexion);
            valida = true;
        } catch (SQLException ex) {
            //corto el mensaje del error
            String error = "";            
            String[] palabras = ex.getMessage().split(" ");
            int i = 0;
            int j = 0;
            while (i < palabras.length){
                while ((i < palabras.length) && (j <= 11)){
                    error+=" "+palabras[i];
                    i=i+1;
                    j=j+1;
                }
                j=0;
                error+="\n                                        ";
            }            
            JOptionPane.showMessageDialog(null,"Código del ERROR: "+ex.getErrorCode()+"\nMensaje del ERROR: "+ error, "Error al intentar establecer la Conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        return valida;
    }
    
    public void grabarConexion (Conexion c){        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombre_archivo);
            pw = new PrintWriter(fichero);
            
            pw.println(c.getUrl());
            pw.println(c.getPort());
            pw.println(c.getSeguridad_integrada());
            pw.println(c.getUsuario());
            pw.println(c.getClave());
 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"       ERROR al abrir el archivo Conexion","Error Grave", JOptionPane.INFORMATION_MESSAGE);
        } finally {
           try {
           // Nuevamente aprovechamos el finally para  asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }       
    }
    
    public boolean existeConexion (){
        String sFichero = nombre_archivo;
        File fichero = new File(sFichero);
        if (fichero.exists())
            return true;
        else
            return false;
     }
    
    public void leerConexion () {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (nombre_archivo);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           
           this.url=br.readLine();
           this.port=br.readLine();
           this.seguridad_integrada=Boolean.parseBoolean(br.readLine());
           this.usuario=br.readLine();
           this.clave=br.readLine();
           
           //armo el url
           
        }
        catch(Exception e){
            //si no existe el fichero llamo a la interface responsable
            //GUI_Conexion gui = new GUI_Conexion();
            //gui.setVisible(true);
            e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
    } 
    
    private String getUrlConexion (){        
        String urlConexion = jdbc+url+":"+port+";databaseName=Sistema;";        
        if (seguridad_integrada){
            urlConexion +="integratedSecurity=true;";
        }
        else{
             urlConexion +="user="+usuario+";password="+clave+";";
        }
        return urlConexion;
    }
        
   //renombrar a abrir_Conexion
   public void Connection ()
    {     
        try
        {   
            leerConexion();            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl;            
            //connectionUrl = "jdbc:sqlserver://192.168.0.50:1433;databaseName=Sistema;user=SA;password=;";
            
            //connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true";
            //connectionUrl = "jdbc:sqlserver://localhost;databaseName=Sistema;integratedSecurity=true";            
            System.out.println(getUrlConexion());
            conn = DriverManager.getConnection(getUrlConexion());            
            //JOptionPane.showMessageDialog(null,"CONEXIÓN ESTABLECIDA CON SQL-SERVER!", "Conexión Exitosa", JOptionPane.INFORMATION_MESSAGE);           
        }
        catch(ClassNotFoundException ex)
        {
            
            JOptionPane.showMessageDialog(null, ex, "Error Tipo 1 en la Conexión con la BD: "+"\n"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conn=null;
        }
        catch(SQLException ex)
        {
            
            JOptionPane.showMessageDialog(null, ex, "Error Tipo 2 en la Conexión con la BD: "+"\n"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conn=null;
        }
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog(null, ex, "Error Tipo 3 en la Conexión con la BD: "+"\n"+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conn=null;
        }
    }
      
    //Cerrar la conexion
 
    public void cierraConexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intenter cerrar la conexion","Atención",JOptionPane.WARNING_MESSAGE);
        }
    }
 
//METODOS PARA TRABAJAR CON LA BASE DE DATOS
 
    //public ResultSet consulta(String consulta) throws SQLException {
        //this.estancia = (Statement) conn.createStatement();
        //return this.estancia.executeQuery(consulta);
    //}
    
    public ResultSet Consultar (String consulta) {        
        try
        {
            stnt  = conn.createStatement(); 
        }
        catch (Exception e)
        {  
            JOptionPane.showMessageDialog(null," Error: createStatement: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }  
        
        try 
        { 
            // Mostramos por pantalla todos las asignaturas de la tabla  
            rslset = stnt.executeQuery(consulta);  

        }
        catch (Exception e)
        {  
           
            System.err.println("Warning: " + e.getMessage());   
        }
         return rslset;         
    }
 
    public boolean Actualizar(String actualiza)  {
        try {
            this.stnt = (Statement) conn.createStatement();
            stnt.executeUpdate(actualiza);
            JOptionPane.showMessageDialog(null, "El Registro se actualizo correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
            return (true);
        } catch (SQLException ex) {            
             if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo clave del Registro ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("Warning: " + ex.getMessage()+" - "+ex.getErrorCode());
            }   
            return (false);
        }
        
    }
 
    public void Borrar(String borra) {
        Statement st;
        try {
            st = (Statement) this.conn.createStatement();
            int numResultado = st.executeUpdate(borra);
            JOptionPane.showMessageDialog(null, "El Registro se elimino correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
             if (ex.getErrorCode()==547){
                    JOptionPane.showMessageDialog(null, "Integridad Refencial: Esta intentando eliminar un Registro que es utilizado por otros!","Atención",JOptionPane.WARNING_MESSAGE);
            }
             else{
                System.err.println("Warning: " + ex.getMessage()+" code "+ex.getErrorCode());
             }
        }
    }
 
    public boolean Insertar(String inserta) {
        try{
            Statement st = (Statement) this.conn.createStatement();
            st.executeUpdate(inserta);
            //JOptionPane.showMessageDialog(null, "El Registro se dio de alta correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
            return (true);
        }
        catch(SQLException ex)
        {
            if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo clave del Registro ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==547){
                    JOptionPane.showMessageDialog(null, "Verifique el campo 'Codigo de Tasas de Iva'","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("Warning: " + ex.getMessage()+" - "+ex.getErrorCode());
            }   
            return (false);
        }
        
    }
    
    public boolean InsertarSinCartel(String inserta) {
        try{
            Statement st = (Statement) this.conn.createStatement();
            st.executeUpdate(inserta);            
            return (true);
        }
        catch(SQLException ex)
        {
            if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo clave del Registro ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==547){
                    JOptionPane.showMessageDialog(null, "Verifique el campo 'Codigo de Tasas de Iva'","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("Warning: " + ex.getMessage()+" - "+ex.getErrorCode());
            }   
            return (false);
        }
        
    }
    
    public String [] getIdentificadoresColumnas(String tabla){
         String []id_columnas;
         try{
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");             
            //con= DriverManager.getConnection(con1);
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from "+tabla);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();
            id_columnas=new String[numeroColumnas];
            for (int i = 1; i <= numeroColumnas; i++) {                
                id_columnas[i-1]=rsmd.getColumnName(i);                        
            }                      
         }
         catch(Exception e){JOptionPane.showMessageDialog(null,e.getMessage());
                            e.printStackTrace();
                            return null;
         }         
         return id_columnas;
     }
     
     public Vector<Vector<String>> getContenidoTabla(String consulta){
         Vector<Vector<String>>v = new Vector();         
         try{
            //lass.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");             
            //conn= DriverManager.getConnection(con1);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();                                                
            while(rs.next()){
                Vector<String> arregloAux=new Vector();
                for(int i=0;i<numeroColumnas;i++){
                    arregloAux.add(rs.getString(i+1));                                        
                }
                v.add(arregloAux);                
            }                                  
         }
         catch(Exception e){System.out.println(e.getMessage());
                            e.printStackTrace();
                            return null;
         }
         return v;
     }
    
         public Vector<Vector<String>> getContenidoTablaPermisos(int perfil){
         Vector<Vector<String>>v=new Vector();         
         try{            
            Statement st = conn.createStatement();
            // distinct lo que hace es sacar los modulos repetidos en el campo mod_descripcion 
            ResultSet rs = st.executeQuery(""
          + "select distinct mod_descripcion "
          + "from permiso,modulo "+
            "where per_id_modulo=mod_id_modulo and per_id_perfil="+perfil);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();
            while(rs.next()){                
                Vector<String> arregloAux=new Vector();
                for(int i=0;i<numeroColumnas;i++){
                    arregloAux.add(rs.getString(i+1));                                        
                }
                v.add(arregloAux);                
            }                                  
         }
         catch(Exception e){System.out.println(e.getMessage());
                            e.printStackTrace();
                            return null;
         }
         return v;
     }
     
          
    
}
 
