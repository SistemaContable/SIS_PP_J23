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
        r_con=con;
        r_con.Connection();
    }
    
    /**
     * Devuelve un string con el nombre del perfil
     * @param perfil Clave de la tabla perfil
     * @return Devuelve un string con el nombre del perfil
     */
    public String getDescripcion(int perfil){
        try {
            String res="";
            Statement stmt=r_con.getStatement();
            String consulta="select prf_descripcion from "+tabla+" where prf_id_perfil="+perfil;
            ResultSet rs=stmt.executeQuery(consulta);
            while(rs.next())
                res=rs.getString(1);
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);return null;
        }        
    }
    
    public Vector<Vector<String>> getTablaPerfiles(){        
         Vector<Vector<String>>v = new Vector();         
         try{
            String consulta="select * from "+tabla;
            Statement st = r_con.getStatement();
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
    
    public void insertar(Perfil p){
                try {
            PreparedStatement consultaAlta;
            String alta="insert into "+tabla+" values (?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);            
            consultaAlta.setInt(1, p.getId());
            consultaAlta.setString(2, p.getDescripcion());            
            consultaAlta.executeUpdate();// insert update delete
                                                    
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Perfil getPerfil(int id_perfil){
        Perfil p=new Perfil();
        try{
            String consulta="select * from "+tabla+" where prf_id_perfil="+id_perfil;
            Statement stmt=r_con.getStatement();
            ResultSet rs;
            rs=stmt.executeQuery(consulta);
            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setDescripcion(rs.getString(2));                
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);return null;
        }        
        return p;
    }
    
    
}
