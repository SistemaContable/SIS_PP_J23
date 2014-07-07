/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Colecciones;

import Clases_Auxiliares.Conexion;
import Objetos.Tarea;
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
public class Tareas {
    private String tabla="tarea";
    private Conexion r_con;    
    
    public Tareas(Conexion con){
        r_con=con;
    }
    
    public Vector<Vector<String>> getDescripcionTareas(){        
        Vector<Vector<String>>v = new Vector();         
         try{
            r_con.Connection();
            String consulta="SELECT tar_descripcion FROM "+tabla;
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
             System.out.println("Error Interno: Tareas - getDescripcionTareas");
             r_con.cierraConexion();
             return null;
         }
         return v;             
    }
    
    
    public Vector<Vector<String>> getTablaTareas(){        
        Vector<Vector<String>>v = new Vector();         
         try{
            r_con.Connection();
            String consulta="SELECT * FROM "+tabla;
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
            st.close();
            rs.close();
            r_con.cierraConexion();
         }
         catch(Exception e){
            System.out.println("Error Interno: Tareas - getTablaTareas");
            r_con.cierraConexion();
            return null;
         }
         return v;             
    }
    
    
    public void insertar(Tarea t){
        try {
            r_con.Connection();
            PreparedStatement consultaAlta;
            String alta="INSERT INTO "+tabla+" VALUES (?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);
            
            consultaAlta.setInt(1, t.getId());
            consultaAlta.setString(2, t.gerDescripcion());
            
            consultaAlta.executeUpdate();
            
            consultaAlta.close();
            r_con.cierraConexion();                                            
        
        } catch (SQLException ex) {
            System.out.println("Error Interno: Tareas - insertar");
            r_con.cierraConexion();   
        }
    }
    
}
