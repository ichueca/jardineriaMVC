/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.trabajosjardineria.modelo.Jardinero;
import org.zabalburu.daw1.trabajosjardineria.modelo.TipoTrabajo;
import org.zabalburu.daw1.trabajosjardineria.modelo.Trabajo;
import org.zabalburu.daw1.trabajosjardineria.servicio.JardineriaServicio;
import org.zabalburu.daw1.trabajosjardineria.vistas.JardineriaVista;

/**
 *
 * @author ichueca
 */
public class JardineriaControlador {

    private static JardineriaServicio servicio = new JardineriaServicio();
    private static JardineriaVista vista = new JardineriaVista();

    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

    public void iniciar() {
        int opc = 0;
        do {
            opc = vista.mostrarMenu();
            switch (opc) {
                case 1:
                    nuevoJardinero();
                    break;
                case 2:
                    nuevoTrabajo();
                    break;
                case 3:
                    asignarTrabajo();
                    break;
                case 4:
                    desAsignartrabajo();
                    break;
            }
        } while (opc != 7);
    }

    private void nuevoJardinero() {
        // La vista pide los datos al usuario y los pasa al controlados
        String nombre = vista.pedirCadena("Nombre del Jardinero");
        String apellidos = vista.pedirCadena("Apellidos del Jardinero");
        String dni = vista.pedirCadena("DNI del Jardinero");
        // El controlador crea los objetos
        Jardinero nuevo = new Jardinero(null, dni, nombre, apellidos);
        // e interactúa con el servicio (añadir el Jardinero a la zona de persistencia)
        nuevo = servicio.addJardinero(nuevo);
        // Pedimos a la vista que muestre un mensaje al usuario
        vista.mostrarMensaje(
                """
            El jardinero %s %s con DNI %s HA SIDO AÑADIDO CORRCTAMENTE con el id %d!
        """.formatted(nuevo.getNombre(),
                        nuevo.getApellidos(),
                        nuevo.getDni(),
                        nuevo.getId()), JOptionPane.INFORMATION_MESSAGE);
    }

    private void nuevoTrabajo() {
        String descripcion = vista.pedirCadena("Descripción del Trabajo");
        int horas = vista.pedirEntero("Horas");
        double precioHora = 20;
        try {
            precioHora = vista.pedirDouble("Prcio / Hora");
        } catch (ParseException ex) {

        }
        Date fechaInicio = vista.pedirFecha("Fecha Inicio Trabajo");

        String tiposTrabajo
                = """
        <html>
        <h3>Tipos de Trabajo</h3>
        <table border=1 bgcolor=white>
        <tr><td>Id</td><td>Tipo Trabajo</td></tr>
        """;
        for (TipoTrabajo t : servicio.getTiposTrabajo()) {
            tiposTrabajo
                    += """
            <tr><td>%d</td><td>%s</td></td></tr>
            """.formatted(t.getId(), t.getDescripcion());
        }
        tiposTrabajo
                += """
        </table>
        <h4>Introduzca el id del Tipo Trabajo deseado</h4>
        </html>
        """;
        int id = vista.pedirEntero(tiposTrabajo);
        TipoTrabajo tt = servicio.getTipoTrabajo(id);
        Trabajo t = new Trabajo(null, descripcion, horas, precioHora, fechaInicio, null, null, tt);
        servicio.nuevoTrabajo(t);
        vista.mostrarMensaje(
                """
            El Trabajo %s de tipo %s con fecha inicio %s HA SIDO AÑADIDO CORRCTAMENTE con el id %d!
        """.formatted(t.getDescripcion(),
                        t.getTipo().getDescripcion(),
                        df.format(t.getFechaInicio()),
                        t.getId()), JOptionPane.INFORMATION_MESSAGE);
    }

    private void asignarTrabajo() {
        String pedirTrabajo
                = """
        <html>
        <h2>Seleccione el trabajo a asignar</h2>
        <table border=1 bgcolor=white>
        <tr><td>Id</td><td>Descripcion</td><td>Tipo</td><td>Fecha Inicio</td></tr>
        """;
        for (Trabajo t : servicio.getTrabajos()) {
            pedirTrabajo
                    += """
            <tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td></tr>
            """.formatted(
                            t.getId(),
                            t.getDescripcion(),
                            t.getTipo().getDescripcion(),
                            df.format(t.getFechaInicio())
                    );
        }
        pedirTrabajo += """
        </table>
        <h4>Introduzca el ID del trabajo</h4>
        </html>
        """;
        int idTrabajo = vista.pedirEntero(pedirTrabajo);
        Trabajo t = servicio.getTrabajo(idTrabajo);

        if (t.getJardinero() != null) {
            vista.mostrarMensaje("El trabajo ya está asignado a %s %s"
                    .formatted(
                            t.getJardinero().getNombre(),
                            t.getJardinero().getApellidos()),
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String pedirJardinero
                    = """
        <html>
        <h2>Seleccione el jardinero que va a hacer el trabajo</h2>
        <table border=1 bgcolor=white>
        <tr><td>Id</td><td>Jardinero</td></tr>
        """;
            for (Jardinero j : servicio.getJardineros()) {
                pedirJardinero
                        += """
            <tr><td>%d</td><td>%s, %s</td></tr>
            """.formatted(j.getId(), j.getApellidos(),
                                j.getNombre());
            }
            pedirJardinero += """
        </table>
        <h4>Introduzca el ID del jardinero al que quiere asignar el trabajo</h4>
        </html>
        """;
            int idJardinero = vista.pedirEntero(pedirJardinero);
            Jardinero j = servicio.getJardinero(idJardinero);
            servicio.asignarTrabajo(t, j);
            vista.mostrarMensaje("Se ha asignado correctamente el trabajo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void desAsignartrabajo() {
        String pedirTrabajo
                = """
        <html>
        <h2>Seleccione el trabajo a desasignar</h2>
        <table border=1 bgcolor=white>
        <tr><td>Id</td><td>Descripcion</td><td>Tipo</td><td>Fecha Inicio</td><td>Asignado A</td></tr>
        """;
        boolean hayTrabajosAsignados = false;
        for (Trabajo t : servicio.getTrabajos()) {
            if (t.getJardinero() != null) {
                hayTrabajosAsignados = true;
                pedirTrabajo
                        += """
            <tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s, %s</td></tr>
            """.formatted(
                                t.getId(),
                                t.getDescripcion(),
                                t.getTipo().getDescripcion(),
                                df.format(t.getFechaInicio()),
                                t.getJardinero().getApellidos(),
                                t.getJardinero().getNombre()
                        );
            }
        }
        if (hayTrabajosAsignados){
            pedirTrabajo += """
            </table>
            <h4>Introduzca el ID del trabajo</h4>
            </html>
            """;
            int idTrabajo = vista.pedirEntero(pedirTrabajo);
            Trabajo t = servicio.getTrabajo(idTrabajo);
            servicio.desAsignarTrabajo(t);
            vista.mostrarMensaje("Se ha asignado correctamente el trabajo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            vista.mostrarMensaje("No hay trabajos aasignados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
