/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.trabajosjardineria;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.zabalburu.daw1.trabajosjardineria.controlador.JardineriaControlador;
import org.zabalburu.daw1.trabajosjardineria.modelo.Jardinero;
import org.zabalburu.daw1.trabajosjardineria.modelo.TipoTrabajo;
import org.zabalburu.daw1.trabajosjardineria.modelo.Trabajo;

/**
 *
 * @author ichueca
 */
public class TrabajosJardineria {

    public static void main(String[] args) {
        JardineriaControlador controlador = new JardineriaControlador();
        controlador.iniciar();
    }
}
