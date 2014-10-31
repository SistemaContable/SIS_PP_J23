/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases_Auxiliares;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldFilter extends PlainDocument {
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMERIC = "0123456789";
    public static final String REAL = ".0123456789";
    public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;
    
    protected int LIMIT = 0;
    protected String acceptedChars = null;
    protected boolean negativeAccepted = false;


    public JTextFieldFilter(String acceptedchars, int max) {
      acceptedChars = acceptedchars;
      LIMIT = max;
    }
    
    public JTextFieldFilter(int max) {
      LIMIT = max;
    }

    public void setNegativeAccepted(boolean negativeaccepted) {
      //if (acceptedChars.equals(ALPHA_NUMERIC)) {
        negativeAccepted = negativeaccepted;
        acceptedChars += "-";
      //}
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        //si es nulo
        if (str == null)
          return;
        
        if (acceptedChars!=null){
            //controlo los caracteres
            for (int i = 0; i < str.length(); i++) {
              if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
                return;
            }
            //si acepta negaticos
            if (negativeAccepted) {
              if (str.indexOf(".") != -1) {
                if (getText(0, getLength()).indexOf(".") != -1) {
                  return;
                }
              }
            }
            //control negativos
            if (negativeAccepted && str.indexOf("-") != -1) {
              if (str.indexOf("-") != 0 || offset != 0) {
                return;
              }
            }
        }
        //controlo la longitud y acepto finalmente
        if ((getLength() + str.length()) <= LIMIT) {
          super.insertString(offset, str, attr);
        }
        //super.insertString(offset, str, attr);
    }
}





