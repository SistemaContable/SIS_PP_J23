/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import Clases_Auxiliares.Conexion;
import Interface.GUI_A_Articulo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Banegas Rodrigo
 */
public class Auditoria {
private String terminal;
private String tablaUsuario="auditoria_usuarios";
private Conexion r_con;

    public Auditoria(Conexion con){
        InetAddress addr;
        try { 
            r_con=con;            
            addr = InetAddress.getLocalHost();
            terminal = addr.getHostName(); 
            } 
        catch (UnknownHostException ex) {
                    Logger.getLogger(GUI_A_Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarArticulo(String usr,String codigo,String descripcion,String proveedor,String precio,String stock,String codTasaIva,int tarea){
                r_con.Connection();
                SimpleDateFormat formatEntrada = new SimpleDateFormat("yyyyMMdd kk:mm:ss.S"); 
                Date fechaEntrada = new Date(); 
                String fecha = formatEntrada.format(fechaEntrada);                 
                Vector<Vector<String>>v = r_con.getContenidoTabla("select * from auditoria_articulo");
                int cant=v.size()+1;                        

                String  sql="insert into auditoria_articulo values("+cant+",'"+usr+"',"+1+","+tarea+",'"+fecha+"','"+terminal+"','"+
                                        codigo+"','"+
                                        descripcion+"','"+
                                        proveedor+"',"
                                        +Float.parseFloat(precio)+","
                                        +Integer.parseInt(stock)+",'"
                                        +codTasaIva+"')"; 
                                                                       
                r_con.Insertar(sql);
                r_con.cierraConexion();
    }
    
    public void insertarUsuario(String usuarioAdmin,Usuario usuarioAux,String tarea){
    try {
        r_con.Connection();
        SimpleDateFormat formatEntrada = new SimpleDateFormat("yyyyMMdd kk:mm:ss.S");
        Date fechaEntrada = new Date();
        String fecha = formatEntrada.format(fechaEntrada); 
        ResultSet rs=r_con.Consultar("select count(auu_id) from "+tablaUsuario);
        int cont=0;
        while(rs.next())cont=rs.getInt(1);
        int existe=0;
        if(usuarioAux.getExiste())existe=1;
        String nuevo=cont+",'"+usuarioAdmin+"','"+usuarioAux.getUsuario()+"','"+usuarioAux.getNombre()+"','"+usuarioAux.getApellido()+"','"+usuarioAux.getIdPerfil().getId()+"',"+existe+",'"+tarea+"','"+fecha+"','"+terminal+"'";
        String datos="insert into "+tablaUsuario+" values("+nuevo+")";
        r_con.Insertar(datos);
        r_con.cierraConexion();
    } catch (SQLException ex) {
        Logger.getLogger(Auditoria.class.getName()).log(Level.SEVERE, null, ex);
        r_con.cierraConexion();
    }
                
    }
    
    
    public void insertarTasaIva(String usr,String t_clave,String tasa_desc,String sigla,String tarea){
        r_con.Connection();
        SimpleDateFormat formatEntrada = new SimpleDateFormat("yyyyMMdd kk:mm:ss.S"); 
                Date fechaEntrada = new Date(); 
                String fecha = formatEntrada.format(fechaEntrada); 
                //Vector<Vector<String>>v = r_con.getContenidoTabla("select * from auditoria_tasa_iva");
                int cant=r_con.cantidadRegistros("auditoria_tasa_iva")+1;                        
                int tasa_clave=Integer.parseInt(t_clave);                
                String  sql="insert into auditoria_tasa_iva values("+cant+",'"+usr+"',"+tasa_clave+",'"+tasa_desc+"','"+sigla+"','"+tarea+"','"+fecha+"','"+terminal+"')";
                                        
                r_con.Insertar(sql);                
                r_con.cierraConexion();
    }
    
    public void insertarPerfiles(String usr,Perfil p,String tarea){
        r_con.Connection();
        SimpleDateFormat formatEntrada = new SimpleDateFormat("yyyyMMdd kk:mm:ss"); 
                Date fechaEntrada = new Date(); 
                String fecha = formatEntrada.format(fechaEntrada);                 
                System.out.println(fecha);
                Vector<Vector<String>>v = r_con.getContenidoTabla("select * from auditoria_perfiles");
                int cant=v.size()+1;                                                        
                String  sql="insert into auditoria_perfiles values("+cant+",'"+usr+"',"+p.getId()+",'"+p.getDescripcion()+"','"+tarea+"','"+fecha+"','"+terminal+"')";                                                
                r_con.Insertar(sql);   
                r_con.cierraConexion();
    }
    
    
}


/*
create table auditoria_perfiles(
aup_id int not null,
aup_usuario varchar(50),
aup_perfil_id int,
aup_perfil_desc varchar(50),
ati_tarea varchar(50),
ati_fecha datetime,
ati_terminal varchar(50),
primary key (aup_id),
foreign key (aup_usuario) references usuario(usr_nombre_usuario)
);

create table auditoria_tasa_iva(
ati_id int not null,
ati_usuario varchar(50),
ati_tasa_clave int,
ati_tasa_desc varchar(50),
ati_tasa_sigla varchar(5),
ati_tarea varchar(50),
ati_fecha datetime,
ati_terminal varchar(50),
primary key (ati_id),
foreign key (ati_usuario) references usuario(usr_nombre_usuario)
);

create table auditoria_usuarios(
auu_id int not null,
auu_usuario_admin varchar(50),
auu_usuario_nuevo varchar(50),
auu_usuario_nombre varchar(50),
auu_usuario_apellido varchar(50),
auu_usuario_perfil int,
auu_usuario_existe bit,
auu_tarea varchar(50),
auu_fecha datetime,
auu_terminal varchar(50),
primary key (auu_id),
foreign key (auu_usuario_admin) references usuario(usr_nombre_usuario)
)
*/
