/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_arc;

import proyecto_arc.controlador.Controlador;
import proyecto_arc.modelo.Modelo;
import proyecto_arc.vista.Vista;

/**
 *
 * @author megag
 */
public class Proyecto_ARC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modelo model = new Modelo();
        Vista vista = new Vista(model);
        Controlador controlador = new Controlador(model, vista);
    }
}
