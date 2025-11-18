/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.controlador;

import org.zabalburu.daw1.trabajosjardineria.servicio.JardineriaServicio;
import org.zabalburu.daw1.trabajosjardineria.vistas.JardineriaVista;

/**
 *
 * @author ichueca
 */
public class JardineriaControlador {
    private static JardineriaServicio servicio = new JardineriaServicio();
    private static JardineriaVista vista = new JardineriaVista();
    
    public void iniciar(){
        int opc = 0;
        do {
            opc = vista.mostrarMenu();
            switch (opc) {
                case 1:
                    nuevoJardinero();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (opc != 7);
    }

    private void nuevoJardinero() {
        
    }
    
}
