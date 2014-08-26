package _Pruebas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Wilfo
 */
public class Cuenta2 {

    public String Id;
    public String Idparent;
    public String Descripcion;
    public String CodCuenta;
    public int Orden;


    public Cuenta2(String Id, String Idparent,String Descrip,String CodCuenta,int Orden) {
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
        return CodCuenta+"-"+Descripcion;
    }
}
