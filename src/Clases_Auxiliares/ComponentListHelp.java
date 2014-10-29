/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases_Auxiliares;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Manolo
 */
public class ComponentListHelp {
    
    //Contructor
    public ComponentListHelp () {    
    }

    private  boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private  void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public  void convertirComponente (JTextField txtInput) {
        //Model que contiene los datos del JCombobox 
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        JComboBox cbInput = new JComboBox(model) {
            //Parte del Codigo que toma el tamaño del TextField enviado por parametro
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        
        setAdjusting(cbInput, false);
        //Setteo elemento por defecto y no selecciono ninguno
        model.addElement(" ");
        cbInput.setSelectedItem(null);        
        
        //Evento de seleccion de item
       cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        String [] separada; 
                        separada = cbInput.getSelectedItem().toString().split("-");                                              
                        txtInput.setText(separada[0].trim());                        
                    }
                }
            }
        });
      
        //Eventos para el manejo de la lista
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String [] separada; 
                        separada = cbInput.getSelectedItem().toString().split("-");                        
                        txtInput.setText(separada[0].trim());                      
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                    //txtInput.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_F1){
                    mostrarLista(txtInput);
                }
                if (e.getKeyCode() == KeyEvent.VK_TAB){
                         //txtInput.requestFocus();
                }
                setAdjusting(cbInput, false);
            }
        });      
        //Agrego el ComboBox al Sur del TextField
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);       
    }
    
    public void mostrarLista (JTextField txtInput) {
        Component C [];        
        C = txtInput.getComponents();
        //txtInput.requestFocus();
        if (C[0] instanceof JComboBox){
            ((JComboBox)C[0]).hidePopup();
            ((JComboBox)C[0]).setPopupVisible(true);
        }   
  
    }
    
    
    public void asignarLista (JTextField txtInput, ArrayList<String> items) {
        Component C [];        
        C = txtInput.getComponents();

        if (C[0] instanceof JComboBox){
            setAdjusting(((JComboBox)C[0]), true);
            DefaultComboBoxModel mode = (DefaultComboBoxModel)((JComboBox)C[0]).getModel();
            mode.removeAllElements();
            for (String item : items) {
                mode.addElement(item);
            }                
            //Quitar comentario si quiero que este metodo tambien depliegue la ayuda
            //((JComboBox)C[0]).hidePopup();
            //((JComboBox)C[0]).setPopupVisible(true);
            setAdjusting(((JComboBox)C[0]), false);
        }        
    }

}
