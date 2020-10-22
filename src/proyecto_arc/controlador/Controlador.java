/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_arc.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import proyecto_arc.vista.Vista;
import proyecto_arc.modelo.Modelo;

/**
 *
 * @author megag
 */
public class Controlador {
    Modelo modelo;
    Vista vis;
    public Controlador(Modelo m, Vista v){
        modelo = m;
        vis = v;
        
        vis.addMyActionListener(new MyActionListener());
        vis.addMyFocusListener(new MyFocusListener());
    }


    class MyActionListener implements ActionListener {
        String s;
        @Override
        public void actionPerformed(ActionEvent ae) {
            s = ae.getActionCommand();
            switch(s){
                case "CrearClientes":
                    modelo.CrearDatosPersona();
                    ArrayList<String> ids = new ArrayList<String>();
                    for(int i = 0; i < modelo.getIds().size(); i++){
                        ids.add(Integer.toString(modelo.getIds().get(i)));
                    }
                    
                    vis.agregarLista(ids);
                    modelo.ConectarConElServidor();
            }
        }
    }
    
    class MyFocusListener implements FocusListener {
        String s;
        @Override
        public void focusGained(FocusEvent fe) {
        }

        @Override
        public void focusLost(FocusEvent fe) {
            s = ((JTextField)fe.getSource()).getText();
            Integer.parseInt(s);
            System.out.println("hola");
        }
        
    }
    
    
}