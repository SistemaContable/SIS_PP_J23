package Clases_Auxiliares;

/**
 * @author Manolo
 * La siguiente clase modula la conexion con la base de datos,
 * guarda el url, el usuario, la contraseña, etc.
 * Los datos son almacenados en un archivo de texto plano, por lo
 * que seria conveniente usar una sola conexion, abrirla y cerrarla.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
             JOptionPane.showMessageDialog(null,"       ERROR al abrir el archivo Conexion","Error Grave", JOptionPane.INFORMATION_MESSAGE);
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
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"       ERROR al cerrar el archivo Conexion","Error Grave", JOptionPane.INFORMATION_MESSAGE);
        }
        finally{
            try{
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
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
        if (fichero.exists())
            return true;
        else
            return false;
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
            System.out.println("    existe Sistema_DB en el SGDB: "+existe);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
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
        if ("".equals(this.url)){
            if  (existeConexion ()) {
                //si existe el achivo ahora si leo los parametros, sino, aviso
                System.out.println("    !!! lei los parametros desde el archivo");
                leerConexion();
            }
            else{
                String msj = ("El Sistema no encuentra el archivo con los parametros "
                           + "necesarios para establecer una Conexión con el SGBD, "
                           + "por favor póngase en contacto con el administrador para "
                           + "que el mismo sea generado.");
                JOptionPane.showMessageDialog(null, "Error de Conexión",msj, JOptionPane.ERROR_MESSAGE);
            }            
        }
        
        //si url no es vacio, los parametros ya fueron leidos del archivo        
        urlConexion = jdbc+url+":"+port+";databaseName=Sistema;";        
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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl;            
            //connectionUrl = "jdbc:sqlserver://192.168.0.50:1433;databaseName=Sistema;user=SA;password=;";            
            //connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true";
            //connectionUrl = "jdbc:sqlserver://localhost;databaseName=Sistema;integratedSecurity=true";            
            conn = DriverManager.getConnection(getUrlConexion());            
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
      
   
   public void ejecutarScript () {
        try {
            String line;
            Process p = Runtime.getRuntime().exec
              ("psql -U username -d dbname -h serverhost -f scripfile.sql");
            BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
              System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
              err.printStackTrace();
        }
   }
   
   /**
    * metodo utilizado para liberar la Conexion con el SGBD
    */
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
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "OCURRIO UN PROBLEMA, CODIGO: "+ex.getErrorCode(),"Atención",JOptionPane.WARNING_MESSAGE);
            if (ex.getErrorCode()==1801) {
                JOptionPane.showMessageDialog(null, "La Base de Datos ya está registrada en el SGBD.","Atención",JOptionPane.WARNING_MESSAGE);
            }
        }
    
    }
    
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
 
