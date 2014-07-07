/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Colecciones;

import Clases_Auxiliares.Conexion;
import Objetos.Perfil;
import Objetos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Banegas Rodrigo
 */
public class Usuarios {
    private String tabla = "usuario";
    private Conexion r_con;
    
    public Usuarios(Conexion con){
        r_con=con;
    }
    
    public boolean existe(String usr){
        boolean exis = false;
        try {
            r_con.Connection();
            String consulta="SELECT * FROM "+tabla+" WHERE usr_nombre_usuario='"+usr+"'";
            Statement stmt=r_con.getStatement();
            ResultSet rs;
            rs=stmt.executeQuery(consulta);
            while(rs.next())
                exis = true;
            stmt.close();
            rs.close();
            r_con.cierraConexion();
        } catch (SQLException ex) {
            System.out.println("Error Interno: Usuarios - existe");
            return false;
        }
        return exis;
    }

    public Usuario getUsuario(String usr){
        Usuario u=new Usuario();
        try {
            r_con.Connection();
            String consulta="SELECT * FROM "+tabla+" WHERE usr_nombre_usuario='"+usr+"'";
            Statement stmt=r_con.getStatement();
            ResultSet rs;
            rs=stmt.executeQuery(consulta);
            if(rs.next()){
                u.setUsuario(rs.getString(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setContraseña(rs.getString(4));
                Perfiles perfiles=new Perfiles(r_con);
                Perfil p=perfiles.getPerfil(rs.getInt(5));
                u.setPerfil(p);                
                u.setExiste(rs.getBoolean(6));
            }
            else{
                stmt.close();
                rs.close();
                r_con.cierraConexion();
                return null;
            }
            stmt.close();
            rs.close();
            r_con.cierraConexion();
            
        } catch (SQLException ex) {
            System.out.println("Error Interno: Usuarios - getUsuario");
            r_con.cierraConexion();
            return null;
        }
        
        return u;
    }
    
    public void eliminar(String usr){
        try {
            r_con.Connection();
            String consulta="UPDATE "+tabla+" SET usr_existe=0 WHERE usr_nombre_usuario='"+usr+"'";
            Statement stmt=r_con.getStatement();            
            stmt.executeUpdate(consulta);
            stmt.close();
            r_con.cierraConexion();
        } catch (SQLException ex) {
            r_con.cierraConexion();
            System.out.println("Error Interno: Usuarios - eliminar");
        }
    }
    
    public boolean insertar(Usuario u){
        boolean inserto = false;
        try {
            r_con.Connection();
            PreparedStatement consultaAlta;
            String alta="INSERT INTO "+tabla+" VALUES (?,?,?,?,?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);
            
            consultaAlta.setString(1, u.getUsuario());
            consultaAlta.setString(2, u.getNombre());
            consultaAlta.setString(3, u.getApellido());
            consultaAlta.setString(4, u.getContraseña());
            consultaAlta.setInt(5, u.getIdPerfil().getId());
            consultaAlta.setBoolean(6, u.getExiste());
            consultaAlta.executeUpdate();
            inserto = true;
            consultaAlta.close();
            r_con.cierraConexion();                                            
        
        } catch (SQLException ex) {
            inserto = false;
            System.out.println("Error Interno: Usuarios - insertar");
            r_con.cierraConexion();
        }
        return inserto;
    }
    
    public void modificar(Usuario u){
        try {
            r_con.Connection();
            PreparedStatement consultaModificar;
            String mod="UPDATE "+tabla+" SET usr_nombre_usuario=?,usr_nombre=?,usr_apellido=?,usr_contraseña=?,usr_id_perfil=?,usr_existe=? WHERE usr_nombre_usuario='"+u.getUsuario()+"'";
            consultaModificar=r_con.getConn().prepareStatement(mod);
            
            consultaModificar.setString(1, u.getUsuario());
            consultaModificar.setString(2, u.getNombre());
            consultaModificar.setString(3, u.getApellido());
            consultaModificar.setString(4, u.getContraseña());
            consultaModificar.setInt(5, u.getIdPerfil().getId());
            consultaModificar.setBoolean(6, u.getExiste());
            consultaModificar.executeUpdate();// insert update delete
            
            consultaModificar.close();
            r_con.cierraConexion();                                       
        
        } catch (SQLException ex) {
            r_con.cierraConexion(); 
            System.out.println("Error Interno: Usuarios - modificar");
        }      
    }
    
    
}
