/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Contabilidad;

/**
 *
 * @author Banegas Rodrigo
 */
import javax.swing.table.DefaultTableModel;  
  
/** 
 * 
 * @author whyem 
 */  
public class DefaultTableModelAsientos extends DefaultTableModel{  
    /** 
     * Sobreescribe el método isCellEditable de DefaultTableModel, 
     * para que las celdas no sean editables. 
     *  
     * @param row 
     * @param column 
     * @return 
     */  
    @Override  
    public boolean isCellEditable (int row, int column)  
    {         
        return false;  
    }  
   
  
}  
