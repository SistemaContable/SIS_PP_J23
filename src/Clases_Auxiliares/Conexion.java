/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases_Auxiliares;

/**
 *
 * @author Manolo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class Conexion{
 
 
//variables miembro
 
    private String usuario;
    private String clave;
    private String url;
    private String driverClassName;
    private Connection conn = null;
    private Statement stnt;
    private ResultSet rslset;
 
//CONSTRUCTORES
 
    //Constructor que toma los datos de conexion por medio de parametros
    public Conexion(String usuario, String clave, String url, String driverClassName) {
        this.usuario = usuario;
        this.clave = clave;
        this.url = url;
        this.driverClassName = driverClassName;
    }
 
    //Constructor que crea la conexion sin parametros con unos definidos en la clase
    //(meter los datos correspondientes)
    public Conexion() {
        //poner los datos apropiados
        this.usuario = "usuario";
        this.clave = "clave";
        this.url = "xxxx:xxxx://url:puerto/lugar";
        this.driverClassName = "el.driver.de.la.base.datos";
    }
 
    //metodos para recuperar los datos de conexion
    public String getClave() {
        return clave;
    }
 
    public String getUrl() {
        return url;
    }
 
    public String getUsuario() {
        return usuario;
    }
 
    public Connection getConn() {
        return conn;
    }
 
    public String getDriverClassName() {
        return driverClassName;
    }
 
    //metodos para establecer los valores de conexion
    public void setClave(String clave) {
        this.clave = clave;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    public void setUsuario(String usuario) throws SQLException {
        this.usuario = usuario;
    }
 
    public void setConn(Connection conn) {
        this.conn = conn;
    }
 
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
 
//la conexion propiamente dicha
 
   public void Connection ()
    {     
        try
        {           
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl;            
            //connectionUrl = "jdbc:sqlserver://10.10.10.230:1433;databaseName=Sistema;user=sa;password=sa;";
            
            //connectionUrl = "jdbc:sqlserver://localhost;integratedSecurity=true";
            connectionUrl = "jdbc:sqlserver://localhost;databaseName=Sistema;integratedSecurity=true";            
            conn = DriverManager.getConnection(connectionUrl);            
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
 
