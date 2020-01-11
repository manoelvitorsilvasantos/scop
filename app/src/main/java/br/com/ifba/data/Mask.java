/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifba.data;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


/**
 *
 * @author mvictor
 */
public abstract class Mask {
    
    public static String unmask(String s){
    
        return s.replaceAll("[.]","").replaceAll("[-]", "").
                replaceAll("[/]", "").replaceAll("[(]", "").
                replaceAll("[)]", "");
    }
    /**
     *  Método que irá criar a mascára para o EditText.
     *  @param mask - O caractere que irá criar a maskara.
     *  @param editTxt - o EditText necessariamente irá ser mascarado.
     *  @return TextWatcher insert(final String mask, final EditText editTxt) - Retorna um set da mascara para widgets EditText.
     *  @see 
     */
    public static TextWatcher insert(final String mask, final EditText editTxt){
        return new TextWatcher(){
            boolean isUpdating;
            String old ="";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                String str = Mask.unmask(s.toString());
                String mascara= "";
                if(isUpdating){
                    old = str;
                    isUpdating = false;
                }
                int i = 0;
                for(char m: mask.toCharArray())
                {
                    if(m != '#' && str.length() > old.length()){
                        mascara += m;
                        continue;
                    }
                    
                    try{
                        mascara += str.charAt(i); 
                    }
                    catch(Exception e){
                        break;
                    }
                    i++;
                    isUpdating = true;
                    editTxt.setText(mascara);
                    editTxt.setSelection(mascara.length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void afterTextChanged(Editable edtbl) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
}
