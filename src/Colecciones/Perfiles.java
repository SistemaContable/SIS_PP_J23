/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Colecciones;

import Clases_Auxiliares.Conexion;
import Objetos.Perfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Banegas Rodrigo
 */
public class Perfiles {
    private String tabla="perfil";
    private Conexion r_con;

    public Perfiles(Conexion con){
        System.out.println("entre en perfiles");
        r_con=con;
        //r_con.Connection();
    }
    
    /**
     * Devuelve un string con el nombre del perfil
     * @param perfil Clave de la tabla perfil
     * @return Devuelve un string con el nombre del perfil
     */
    public String getDescripcion(int perfil){
        r_con.Connection();
        String res="";
        try {            
            Statement stmt=r_con.getStatement();
            String consulta="select prf_descripcion from "+tabla+" where prf_id_perfil="+perfil;
            ResultSet rs=stmt.executeQuery(consulta);
            while(rs.next())
                res=rs.getString(1);
            
            stmt.close();
            rs.close();            
            r_con.cierraConexion();           
        } catch (SQLException ex) {
            System.out.println("OJO");
            r_con.cierraConexion();            
        }
         return res;
    }
    
    public Vector<Vector<String>> getTablaPerfiles(){        
        System.out.println("3");
        Vector<Vector<String>>v = new Vector();         
         try{
            r_con.Connection();
            String consulta="select * from "+tabla;
            System.out.println("st");
            //Statement st = r_con.getStatement();
            ResultSet rs=r_con.Consultar(consulta);
            System.out.println("st fin");
            System.out.println("rs");
           // ResultSet rs = st.executeQuery(consulta);
            System.out.println("rs fin");
            System.out.println("meta");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount(); 
            System.out.println("meta fin");
            while(rs.next()){
                Vector<String> arregloAux=new Vector();
                for(int i=0;i<numeroColumnas;i++){
                    arregloAux.add(rs.getString(i+1));                                        
                }
                v.add(arregloAux);                
            }  
          //  st.close();
            rs.close();
            r_con.cierraConexion();
         }
         catch(Exception e){
             System.out.println("OJO 2");
             r_con.cierraConexion();
             return null;
         }
         System.out.println("3 fin");
         return v;             
    }
    
    public void insertar(Perfil p){
        try {
            r_con.Connection();
            PreparedStatement consultaAlta;
            String alta="insert into "+tabla+" values (?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);            
            consultaAlta.setInt(1, p.getId());
            consultaAlta.setString(2, p.getDescripcion());            
            consultaAlta.executeUpdate();// insert update delete
            consultaAlta.close();
            r_con.cierraConexion();
                                                    
        } catch (SQLException ex) {
            System.out.println("OJO 3");
        }
    }
    
    public Perfil getPerfil(int id_perfil){
        Perfil p=new Perfil();
        try{
            r_con.Connection();
            String consulta="select * from "+tabla+" where prf_id_perfil="+id_perfil;
            Statement stmt=r_con.getStatement();
            ResultSet rs;
            rs=stmt.executeQuery(consulta);
            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setDescripcion(rs.getString(2));                
            }
            stmt.close();
            r_con.cierraConexion();
        } 
        catch (SQLException ex) 
        {
            r_con.cierraConexion();
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);return null;
        }        
        return p;
    }
    
    
}
