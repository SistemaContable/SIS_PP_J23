package Clases_Auxiliares;

/**
 * @author Manolo
 * La siguiente clase modula la conexion con la base de datos,
 * guarda el url, el usuario, la contraseña, etc.
 * Los datos son almacenados en un archivo de texto plano, por lo
 * que seria conveniente usar una sola conexion, abrirla y cerrarla.
 */

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class Conexion{
 
    
    private String jdbc;
    private String url;
    private String port;
    private boolean seguridad_integrada;
    private String usuario;
    private String clave;    
    private String driverClassName;
    private String base_datos;
    private String razon_social;
    
    private Connection conn = null;
    private Statement stnt;
    private ResultSet rslset;
    private String nombre_archivo = "dat_conexion.dat";
 
//CONSTRUCTORES:
    
    public Conexion() {
        //asigno parametros basicos, ya que los valores los leo del archivo        
        this.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
        this.jdbc = "jdbc:sqlserver://";
        this.port = "1433";
        this.url = "";
        this.seguridad_integrada = true;
        this.usuario="";
        this.clave="";
        this.base_datos="";
        this.razon_social="";
    }      
    
//GETTERS:
    
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
    public String getBase_datos() {
        return base_datos;
    }
    public String getRazon_social() {
        return razon_social;
    }
    public String getUrl_Conexion_Sistema (){
        String urlConexion = jdbc+url+":"+port+";";       
              
        if (seguridad_integrada){
            urlConexion +="integratedSecurity=true;";
        }
        else{
            urlConexion +="user="+usuario+";password="+clave+";";
         }
       
        return urlConexion;       
    }
    
//SETTERS:
    
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
    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }
    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }
 
//METODOS:
    
    /**
     * El siguiente metodo, tiene por finalidad validar si los parametros pasados
     * sirven para gestionar una conexion a un SGBD. Decuelve true si lo logra.
     * @param n_url url de la conexion ej:localhost
     * @param n_port puerto abierto de conexion
     * @param seg_int true indica que se utilizara seguridad integrada
     * @param n_usu usuario de conexion, en caso se no usar seguridad integrada
     * @param n_cla clave, idem usuario.
     * @return 
     */
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
            conn.close();
        } catch (SQLException ex) {
            //corto la longitud del mensaje y muestro el error
            //para informar por que no puede conectarce a la BD 
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
    
    /**
     * Este metodo se utiliza para grabar en un archivo los parametros de conexion.
     * Se recomienda antes validar la conexion mediante el metodo validarConexion() 
     * @param c objeto de conexion para obtener los parametros
     */
    public void grabarConexion (Conexion c){        
        File archivo = null;
        FileWriter fr = null;
        PrintWriter pw = null;
        try
        {
            archivo = new File (nombre_archivo);
            fr = new FileWriter(archivo);
            pw = new PrintWriter(fr);
            
            pw.println(c.getUrl());
            pw.println(c.getPort());
            pw.println(c.getSeguridad_integrada());
            pw.println(c.getUsuario());
            pw.println(c.getClave());
            
            pw.flush();
            pw.close();
 
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"       ERROR al abrir el archivo de Conexion","Error Grave", JOptionPane.INFORMATION_MESSAGE);
        } 
        finally {
            try {
                if (null != fr)
                    fr.close();
            }catch (Exception e2) {
                System.out.println("Error al cerrar el archivo de Conexion.");
            }
        }       
    }
    
    /**
     * Este metodo lee los parametros desde el archivo para establecer la conexion 
     * y los settea al objeto. Se recomienda antes consultar si existe el archivo 
     * con el metodo existeConexion();
     */
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
           
           this.url=br.readLine();
           this.port=br.readLine();
           this.seguridad_integrada=Boolean.parseBoolean(br.readLine());
           this.usuario=br.readLine();
           this.clave=br.readLine();    
                       
           br.close();           
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null,"       ERROR al cerrar el archivo de Conexion","Error Grave", JOptionPane.INFORMATION_MESSAGE);
        }
        finally{
            try{
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (IOException e2){ 
                System.out.println("Error al cerrar el archivo de Conexion.");
            }
        }
    }
    
    /**
     * metodo que devuelve true si existe un archivo con los parametros de conexion
     * llamado como "nombre_archivo"
     * @return boolean
     */
    public boolean existeConexion (){
        String sFichero = nombre_archivo;
        File fichero = new File(sFichero);
        return fichero.exists();
     }
    
    /**
     * metodo que devuelve true si existe en el SGBD una Base de Datos con el nombre
     * pasado por parametro
     * @param nombre nombre de la Base de Datos a saber si existe
     * @return boolean
     */
    public boolean existeDatabase (String nombre){
        boolean existe = false;
        try {            
            //creo el statament (antes conectar a la bd)
            stnt  = conn.createStatement();
            //consulto, cuenta si existe una bd con ese nombre, devuelve 1 si existe, 0 sino
            rslset = stnt.executeQuery("SELECT COUNT(*) FROM sys.databases " +
                                       "WHERE [NAME] = '"+nombre+"';");
            rslset.next();
            existe = ("1".equals(rslset.getString(1)));
            stnt.close();
            rslset.close();            
        } catch (SQLException ex) {
            System.err.println("1. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());
        } 
        return existe;
    }
    
    /**
     * metodo que genera el String de Conexion necesesario para establecer una
     * conexion con el SGBD.
     * Si detecta que el URL no fue asignado, entonces si existe el archivo de Conexion
     * lee los parametros y arma el URL, caso contrario, avisa que el archivo no existe
     * @return String
     */
    private String getUrlConexion (){        
        String urlConexion = "";
        
        //pregunto si el url es vacio, quiere decir que se intento extablecer una 
        //conexion por primera vez, por eso, leo los parametros del archivo de Conexion
        if ("".equals(url)){
            if  (existeConexion ()) {
                //si existe el achivo ahora si leo los parametros, sino, aviso
                leerConexion();
            }
            else{
                String msj = ("El Sistema no encuentra el archivo con los parametros "
                           + "necesarios para establecer \nuna Conexión con el SGBD, "
                           + "por favor póngase en contacto con el Administrador \npara "
                           + "que el mismo sea generado.");
                JOptionPane.showMessageDialog(null, msj, "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }            
        }
        
        //si url no es vacio, los parametros ya fueron leidos del archivo        
        urlConexion = jdbc+url+":"+port+";";
        
        if (!"".equals(base_datos)){
            urlConexion +="databaseName="+base_datos+";";
        }             
        if (seguridad_integrada){
            urlConexion +="integratedSecurity=true;";
        }
        else{
            urlConexion +="user="+usuario+";password="+clave+";";
         }
        //System.out.println("CONEXION: "+urlConexion);
        return urlConexion;
    }
    
    /**
     * metodo cuya finalidad es la de ejecutar un script sobre la base de datos actual
     * de la conexion. Si desea ejecutarlo sobre una otra base de datos, primero utilize el 
     * setter setBase_datos(base_datos) para cambiar la base de datos.
     * @param ScriptFilePath  ruta del archivo, debe incluir el nombre del mismo ej:...\X.sql
     * @return 
     */
    public boolean executeScripts(String ScriptFilePath) {
        boolean isScriptExecuted = false;
        try {
            File fichero = new File(ScriptFilePath);
            if (fichero.exists()){               
                BufferedReader br = new BufferedReader(new FileReader(ScriptFilePath));
                String str;
                StringBuffer sb;
                sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str + "\n ");
                }
                Statement st = (Statement) this.conn.createStatement();
                st.executeUpdate(sb.toString());
                st.close();
                isScriptExecuted = true;
            }
            else{
                System.out.println("no se encuentra el Script que se quiso ejecutar.");
            }            
        } catch (HeadlessException | IOException | SQLException e) {
            System.err.println("Fallo al ejecutar" + ScriptFilePath +". Error: "+ e.getMessage());
        } 
        return isScriptExecuted;
    }
 
    /**
     * este metodo crea una nueva Base de Datos, en el directorio especificado 
     * con el nombre especificado
     * @param name nombre de la base de datos
     * @param directorio directorio de creacion de la base de datos
     */
    public void crearDatabase_DIR (String name, String directorio){
        try{
            Statement st = (Statement) this.conn.createStatement();
            st.executeUpdate("CREATE DATABASE ["+name+"]" +
                            "ON  PRIMARY" +
                            "( NAME = N'"+name+"', " +
                            "FILENAME = N'"+directorio+"\\"+name+".mdf' , " +
                            "SIZE = 3072KB , " +
                            "FILEGROWTH = 1024KB )" +
                            "LOG ON " +
                            "( NAME = N'"+name+"_log', " +
                            "FILENAME = N'"+directorio+"\\"+name+"_log.ldf' , " +
                            "SIZE = 1024KB , \n" +
                            "FILEGROWTH = 10%)");
            st.close();           
        }
        catch(SQLException ex)
        {            
            if (ex.getErrorCode()==1801) {
                JOptionPane.showMessageDialog(null, "La Base de Datos ya está registrada en el SGBD.","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("2. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());
            }
        }    
    }
    
    /**
     * metodo que elimina una base de datos del sistema
     * @param name nombre de la base de datos a eliminar
     * @return true o false si se pudo eliminar
     */
    public boolean eliminarDatabase (String name){
        try{
            Statement st = (Statement) this.conn.createStatement();
            st.executeUpdate("DROP DATABASE "+name+";");
            st.close();
            return (true);
        }
        catch(SQLException ex)
        {
            if (ex.getErrorCode()==3701){
                System.err.println("La base de datos que se intenta eliminar no existe.");
            }
            else{
                System.err.println("3. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());    
            }   
            return (false);
        }
    }
    
    public int cantidadRegistros (String nametable){
        int elementos = 0;
        try{
            stnt = (Statement) this.conn.createStatement();
            rslset = stnt.executeQuery("SELECT COUNT(*) FROM "+nametable+";");
            rslset.next();
            elementos = Integer.parseInt(rslset.getString(1));
            stnt.close();
            rslset.close();
        }
        catch(SQLException ex)
        {
            System.err.println("4. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
        }
        return (elementos);
    }
        
   //renombrar a abrir_Conexion
   public void Connection ()
    {     
        try
        {                           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl;            
            //connectionUrl = "jdbc:sqlserver://192.168.0.50:1433;databaseName=Sistema;user=SA;password=;";            
            //connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true";
            //connectionUrl = "jdbc:sqlserver://localhost;databaseName=Sistema;integratedSecurity=true";            
            this.conn = DriverManager.getConnection(getUrlConexion()); 
            
            //System.out.println("<<<<<<<<<<<<<<<<< CONECTEEEEEEE!!!!!!!!!!!!!!!! >>>>>>>>>>>>>>");
            
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
    }
   
   /**
    * metodo utilizado para liberar la Conexion con el SGBD
    */
    public void cierraConexion() {
        try {
            //System.out.println("[[[[[[[[[[[[[[[[[[[[[[[ CEEEEERRRREEEE  ]]]]]]]]]]]]]]]]]]]");
            this.conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intenter cerrar la conexion","Atención",JOptionPane.WARNING_MESSAGE);
        }
    }
 
//METODOS PARA TRABAJAR CON LA BASE DE DATOS

    
    public ResultSet Consultar (String consulta) {                
        try 
        {  
            stnt  = conn.createStatement(); 
            rslset = stnt.executeQuery(consulta);
            
        }
        catch (SQLException e)
        {            
            System.err.println("5. Error Codigo: "+e.getErrorCode()+"\nError Mensaje: " +e.getMessage());
        }
        return rslset;         
    }
    
    public boolean Existe (String consulta){
        boolean existe = false;
        try 
        {  
            stnt  = conn.createStatement(); 
            rslset = stnt.executeQuery(consulta);
            stnt.close();
            rslset.next();
            int elementos = Integer.parseInt(rslset.getString(1));
            if (elementos > 0){
                existe = true;
            }
            stnt.close();
            rslset.close();
        }
        catch (SQLException e)
        {            
            System.err.println("6. Error Codigo: "+e.getErrorCode()+"\nError Mensaje: " +e.getMessage());
        }
        return existe;
    }
 
    public boolean ActualizarSinCartel(String actualiza){
            try {
            stnt = (Statement) conn.createStatement();
            stnt.executeUpdate(actualiza);
            stnt.close();            
            return (true);
        } catch (SQLException ex) {                         
            /*if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo Clave ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("7. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
            }  */          
            return (false);
        }
    
    }
    
    public boolean Actualizar(String actualiza)  {
        try {
            stnt = (Statement) conn.createStatement();
            stnt.executeUpdate(actualiza);
            stnt.close();
            JOptionPane.showMessageDialog(null, "Se actualizo Correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
            return (true);
        } catch (SQLException ex) {            
             if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo Clave ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                System.err.println("7. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
            }            
            return (false);
        }
        
    }
 
    public boolean Borrar(String borra) {
        try {
            stnt  = conn.createStatement();
            int numResultado = stnt.executeUpdate(borra);
            stnt.close();
            JOptionPane.showMessageDialog(null, "Se elimino Correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
            return (true);
        } catch (SQLException ex) {
             if (ex.getErrorCode()==547){
                    JOptionPane.showMessageDialog(null, "No se pudo concretar, ya que el Registro que intentanda eliminar es utilizado por Otros!","Atención",JOptionPane.WARNING_MESSAGE);
            }
             else{
                System.err.println("8. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
             }
             return false;
        }       
    }
 
    public boolean Insertar(String inserta) {
        try{
            stnt = (Statement) this.conn.createStatement();
            stnt.executeUpdate(inserta);
            stnt.close();
            //JOptionPane.showMessageDialog(null, "El Registro se dio de alta correctamente.","Informacíon",JOptionPane.INFORMATION_MESSAGE);
            return (true);
        }
        catch(SQLException ex)
        {
            if (ex.getErrorCode()==2627){
                    JOptionPane.showMessageDialog(null, "El campo Clave ya se encuentra registrado!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==8152){
                    JOptionPane.showMessageDialog(null, "Hay Campos que exceden su longitud, verifique!","Atención",JOptionPane.WARNING_MESSAGE);
            }
            if (ex.getErrorCode()==547){
                    JOptionPane.showMessageDialog(null, "Verifique el campo 'Codigo de Tasas de Iva'","Atención",JOptionPane.WARNING_MESSAGE);
            }
            else{
                    System.err.println("9. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
            }   
            return (false);
        }
        
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
            st.close();
            rs.close();
         }
         catch(Exception e){
             System.err.println("11. Error Mensaje: " +e.getMessage());     
             return null;
         }
         return v;
     }       
     
    public Statement getStatement(){
        try { 
            return conn.createStatement();
        } catch (SQLException ex) {
            System.err.println("12. Error Codigo: "+ex.getErrorCode()+"\nError Mensaje: " +ex.getMessage());     
            return null;
        }
    }
          
    
}
 
