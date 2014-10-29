/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _Pruebas;
import Clases_Auxiliares.Conexion;
import Objetos.Cuenta;
import Objetos.Usuario;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Wilfo
 */
public class CLS_CONTABILIDAD{

   
    private Conexion r_con;
    private Usuario usr;

    public CLS_CONTABILIDAD(Usuario u, Conexion con) {
        usr = u;
        r_con=con;
    }
   //Metodos para cargar las cuentas contables

/**
 * True if get some children
 * False is there is no children
 * @param Idparent
 * @return
 */
    public boolean getchildC(String Id) {
        boolean bt = false;
        try {
            r_con.Connection();
            System.out.println("sadsajudhbuasssssssssssssssssssssssssssssssssssssssss");
            ResultSet res = r_con.Consultar("Select (count(1)+1) from plan_cuentas where pc_id_padre=" + Id);
            res.next();
            int numhijos = res.getInt(1)-1;
            if (numhijos != 0) {
                bt = true;
            } else {
                bt = false;
            }

            res.close();
        } catch (SQLException e) {
            r_con.cierraConexion();
            System.out.println(e);
        }
        r_con.cierraConexion();
        return bt;
    }

    /**
     * Return an vector of my data order.
     * Id,Idparent,Descripcion,Order
     * @param Id
     * @return
     */
    public Vector getChildrenC(String Id) {
        Vector<CLS_ITEM_CONTA> data = new Vector<CLS_ITEM_CONTA>();
        try {
            r_con.Connection();
            ResultSet res = r_con.Consultar("select id_cta,idpadre_cta,nombre_cta,cod_cta,orden_cta from sys_cuenta where idpadre_cta="+Id);

            while (res.next()) {
                data.addElement(new CLS_ITEM_CONTA (res.getString(1), res.getString(2), res.getString(3),res.getString(4),res.getInt(5)));
            }
            res.close();
        } catch (SQLException e) {
            r_con.cierraConexion();
            System.out.println(e);
        }
        r_con.cierraConexion();
        return data;
    }
}
