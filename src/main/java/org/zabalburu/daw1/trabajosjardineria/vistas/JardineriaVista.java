/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.vistas;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ichueca
 */
public class JardineriaVista {
    
    public int mostrarMenu(){
        String resp = JOptionPane.showInputDialog(
        """
        Jardinería Garden
        =================
        1. Nuevo Jardinero
        2. NUevo Trabajo
        3. Asignar Trabajo
        4. Desasignar Trabajo
        5. Finalizar Trabajo
        6. Mostrar Información Jardinero
        7. Salir
        
        Opción[1-7]:
        """
        );
        return Integer.parseInt(resp);
    }
    
    public String pedirCadena(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    
    public int pedirEntero(String mensaje){
        return Integer.parseInt(pedirCadena(mensaje));
    }
    
    public double pedirDouble(String mensaje) throws ParseException{
        NumberFormat nf = NumberFormat.getInstance();
        String resp = pedirCadena(mensaje);
        Number n = nf.parse(resp);
        return n.doubleValue();
    }
    
    public Date pedirFecha(String mensaje){
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // dd/MM/YYYY
        Date fecha = new Date();
        String resp = pedirCadena(mensaje);
        try {
            fecha = df.parse(resp);
        } catch (ParseException ex) {

        }
        return fecha;
    }
    
    public void mostrarMensaje(String mensaje, int tipoIcono){
        JOptionPane.showMessageDialog(null, mensaje,"mensaje", tipoIcono);
    }
    
}
