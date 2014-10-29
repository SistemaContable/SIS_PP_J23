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

/**
 *
 * @author Banegas Rodrigo
 */
public class Perfiles {
    private String tabla="perfil";
    private Conexion r_con;

    public Perfiles(Conexion con){
        r_con = con;
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
            String consulta="SELECT prf_descripcion FROM "+tabla+" WHERE prf_id_perfil="+perfil;
            ResultSet rs=stmt.executeQuery(consulta);
            while(rs.next())
                res=rs.getString(1);            
            stmt.close();
            rs.close();            
            r_con.cierraConexion();           
        } catch (SQLException ex) {
            System.out.println("Error Interno: Perfiles - getDescripcion");
            r_con.cierraConexion();            
        }
        return res;
    }
    
    public Vector<Vector<String>> getTablaPerfiles(){        
        Vector<Vector<String>>v = new Vector();         
         try{
            r_con.Connection();
            String consulta="SELECT * FROM "+tabla;
            ResultSet rs=r_con.Consultar(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount(); 
            while(rs.next()){
                Vector<String> arregloAux=new Vector();
                for(int i=0;i<numeroColumnas;i++){
                    arregloAux.add(rs.getString(i+1));                                        
                }
                v.add(arregloAux);                
            }  
            rs.close();
            r_con.cierraConexion();
         }
         catch(Exception e){
             System.out.println("Error Interno: Perfiles - getTablaPerfiles");
             r_con.cierraConexion();
             return null;
         }
         return v;             
    }
    
    public void insertar(Perfil p){
        try {
            r_con.Connection();
            PreparedStatement consultaAlta;
            String alta="INSERT INTO "+tabla+" VALUES (?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);            
            consultaAlta.setInt(1, p.getId());
            consultaAlta.setString(2, p.getDescripcion());            
            consultaAlta.executeUpdate();
            consultaAlta.close();
            r_con.cierraConexion();
                                                    
        } catch (SQLException ex) {
            r_con.cierraConexion();
            System.out.println("Error Interno: Perfiles - insertar");           
        }
    }
    
    public Perfil getPerfil(int id_perfil){
        Perfil p=new Perfil();
        try{
            r_con.Connection();
            String consulta="SELECT * FROM "+tabla+" WHERE prf_id_perfil="+id_perfil;
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
            System.out.println("Error Interno: Perfiles - insertar");
        }        
        return p;
    }
    
    
}
