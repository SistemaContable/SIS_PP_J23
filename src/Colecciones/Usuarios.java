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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Banegas Rodrigo
 */
public class Usuarios {
    private String tabla;
    private Conexion r_con;
    
    public Usuarios(Conexion con){
        r_con=con;
        r_con.Connection();
        tabla="usuario";
    }
    
    public boolean existe(String usr){
        try {
            String consulta="select * from "+tabla+" where usr_nombre_usuario='"+usr+"'";
            Statement stmt=r_con.getStatement();
            ResultSet rs;
            rs=stmt.executeQuery(consulta);
            while(rs.next())
                return true;
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);return false;
        }
    }

    public Usuario getUsuario(String usr){
        Usuario u=new Usuario();
        try {
            String consulta="select * from "+tabla+" where usr_nombre_usuario='"+usr+"'";
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
            else
                return null;
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);return null;
        }
        return u;
    }
    
    public void eliminar(String usr){
        try {
            String consulta="update "+tabla+" set usr_existe=0 where usr_nombre_usuario='"+usr+"'";
            Statement stmt=r_con.getStatement();            
            stmt.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(Usuario u){
        try {
            PreparedStatement consultaAlta;
            String alta="insert into "+tabla+" values (?,?,?,?,?,?)";
            consultaAlta=r_con.getConn().prepareStatement(alta);
            
            consultaAlta.setString(1, u.getUsuario());
            consultaAlta.setString(2, u.getNombre());
            consultaAlta.setString(3, u.getApellido());
            consultaAlta.setString(4, u.getContraseña());
            consultaAlta.setInt(5, u.getIdPerfil().getId());
            consultaAlta.setBoolean(6, u.getExiste());
            consultaAlta.executeUpdate();// insert update delete
                                            
        
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificar(Usuario u){
        try {
            PreparedStatement consultaModificar;
            String mod="update "+tabla+" set usr_nombre_usuario=?,usr_nombre=?,usr_apellido=?,usr_contraseña=?,usr_id_perfil=?,usr_existe=? where usr_nombre_usuario='"+u.getUsuario()+"'";
            consultaModificar=r_con.getConn().prepareStatement(mod);
            
            consultaModificar.setString(1, u.getUsuario());
            consultaModificar.setString(2, u.getNombre());
            consultaModificar.setString(3, u.getApellido());
            consultaModificar.setString(4, u.getContraseña());
            consultaModificar.setInt(5, u.getIdPerfil().getId());
            consultaModificar.setBoolean(6, u.getExiste());
            consultaModificar.executeUpdate();// insert update delete
                                            
        
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
