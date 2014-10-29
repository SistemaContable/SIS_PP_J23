/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _Pruebas;

/**
 *
 * @author Wilfo
 */
public class CLS_ITEM_CONTA {

    public String Id;
    public String Idparent;
    public String Descripcion;
    public String CodCuenta;
    public int Orden;


    public CLS_ITEM_CONTA(String Id, String Idparent,String Descrip,String CodCuenta,int Orden) {
        this.Id = Id;
        this.Idparent=Idparent;
        this.Descripcion = Descrip;
        this.CodCuenta=CodCuenta;
        this.Orden=Orden;
    }

    public String getId() {
        return Id;
    }


    public String getIdparent() {
        return Idparent;
    }

    public String getDescrip() {
        return Descripcion;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

     public void setIdparent(String Idparent) {
        this.Idparent = Idparent;
    }

    public void setDescrip(String Descrip) {
        this.Descripcion = Descrip;
    }

    public String getCodCuenta(){
     return CodCuenta;
    }
    public int getOrden(){
     return  Orden;
    }

    public String toString(){
        return  Id +"- "+Descripcion;
    }
}
