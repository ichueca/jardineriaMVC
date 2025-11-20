/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.servicio;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.dao.JardineroDAO;
import org.zabalburu.daw1.trabajosjardineria.dao.JardineroList;
import org.zabalburu.daw1.trabajosjardineria.dao.TipoTrabajoDAO;
import org.zabalburu.daw1.trabajosjardineria.dao.TipoTrabajoMatriz;
import org.zabalburu.daw1.trabajosjardineria.dao.TrabajoDAO;
import org.zabalburu.daw1.trabajosjardineria.dao.TrabajoList;
import org.zabalburu.daw1.trabajosjardineria.modelo.Jardinero;
import org.zabalburu.daw1.trabajosjardineria.modelo.TipoTrabajo;
import org.zabalburu.daw1.trabajosjardineria.modelo.Trabajo;

/**
 *
 * @author ichueca
 */
public class JardineriaServicio {
    private TrabajoDAO trabajoDAO = new TrabajoList();
    private TipoTrabajoDAO tipoTrabajoDAO = new TipoTrabajoMatriz();
    private JardineroDAO jardineroDAO = new JardineroList();
    
    public JardineriaServicio(){
        addJardinero(new Jardinero(null, "11111111", "Juan", "López"));
        addJardinero(new Jardinero(null, "22222222", "Eva", "Sanz"));
        addJardinero(new Jardinero(null, "33333333", "Ana", "Martin"));
        nuevoTrabajo(new Trabajo(null, "Poda en Casa Manolo", 5, 40.5, 
            new GregorianCalendar(2025,11,1).getTime(), null, null, getTipoTrabajo(1)));
        nuevoTrabajo(new Trabajo(null, "Desbrozar en Amurrio", 8, 40.5, 
            new GregorianCalendar(2025,11,13).getTime(), null, null, getTipoTrabajo(2)));
        nuevoTrabajo(new Trabajo(null, "Arreglar parterre", 2, 40.5, 
            new GregorianCalendar(2025,11,15).getTime(), null, null, getTipoTrabajo(2)));
        nuevoTrabajo(new Trabajo(null, "Limpiar bosque", 15, 30.5,
            new GregorianCalendar(2025,11,20).getTime(), null, null, getTipoTrabajo(3)));
    }
    
    
    public Trabajo nuevoTrabajo(Trabajo nuevo){
        return trabajoDAO.nuevoTrabajo(nuevo);
    }
    
    public void modificarTrabajo(Trabajo modificar){
        trabajoDAO.modificarTrabajo(modificar);
    }
    
    public Trabajo getTrabajo (Integer id){
        return trabajoDAO.getTrabajo(id);
    }
    
    public void eliminarTrabajo (Integer id){
        trabajoDAO.eliminarTrabajo(id);
    }
    
    public void eliminarTrabajo (Trabajo trabajo){
        trabajoDAO.eliminarTrabajo(trabajo);
    }
    
    public List<Trabajo> getTrabajos(){
        return trabajoDAO.getTrabajos();
    }
    
    public Jardinero addJardinero(Jardinero nuevo){
        return jardineroDAO.addJardinero(nuevo);
    }
    
    public void deleteJardinero(Jardinero eliminar){
        jardineroDAO.deleteJardinero(eliminar);
    }
    
    public void deleteJardinero(Integer id){
        jardineroDAO.deleteJardinero(id);
    }
    
    public void modificarJardinero(Jardinero modificar){
        jardineroDAO.modificarJardinero(modificar);
    }
    
    public Jardinero getJardinero(Integer id){
        return jardineroDAO.getJardinero(id);
    }
    
    public List<Jardinero> getJardineros(){
        return jardineroDAO.getJardineros();
    }
    
    public Jardinero getJardinero(String dni){
        return jardineroDAO.getJardinero(dni);
    }
    
    public TipoTrabajo getTipoTrabajo(Integer id){
        return tipoTrabajoDAO.getTipoTrabajo(id);
    }
    
    public List<TipoTrabajo> getTiposTrabajo(){
        return tipoTrabajoDAO.getTiposTrabajo();
    }
    
    public void asignarTrabajo(Trabajo t, Jardinero j){
        t.setJardinero(j);
        trabajoDAO.modificarTrabajo(t);
        j.getTrabajos().add(t);
        jardineroDAO.modificarJardinero(j);
    }
    
    public void desAsignarTrabajo(Trabajo t){
        t.getJardinero().getTrabajos().remove(t);
        jardineroDAO.modificarJardinero(t.getJardinero());
        t.setJardinero(null);
        trabajoDAO.modificarTrabajo(t);
    }
    
    public void finalizarTrabajo(Trabajo t){
        t.setFechaFin(new Date());
        trabajoDAO.modificarTrabajo(t);
    }
    
    
    public static void main(String[] args) {
        DateFormat df = DateFormat.getDateInstance();
        JardineriaServicio servicio = new JardineriaServicio();
        servicio.addJardinero(new Jardinero(null, "11111111", "Juan", "López"));
        servicio.addJardinero(new Jardinero(null, "22222222", "Ana", "Sanz"));
        servicio.nuevoTrabajo(new Trabajo(null, "Poda Casa 1", 30, 25.0, 
                new GregorianCalendar(2025,10,21).getTime(), null, null, 
                servicio.getTipoTrabajo(1)));
        servicio.nuevoTrabajo(new Trabajo(null, "Arreglo jardín", 5, 25.0, 
                new GregorianCalendar(2025,10,24).getTime(), null, null, 
                servicio.getTipoTrabajo(3)));
        servicio.asignarTrabajo(servicio.getTrabajo(1), servicio.getJardinero(2));
        servicio.asignarTrabajo(servicio.getTrabajo(2), servicio.getJardinero(2));
        for(Trabajo t : servicio.getTrabajos()){
            System.out.println(t);
        }
        System.out.println("==============");
        for(Jardinero j : servicio.getJardineros()){
            System.out.println("%s %s [%s]".formatted(j.getNombre(),j.getApellidos(),j.getDni()));
            for(Trabajo t : j.getTrabajos()){
                System.out.println("\t%s %s [%s] : %d horas - %,.2f €/hora"
                    .formatted(
                            df.format(t.getFechaInicio()),
                            t.getDescripcion(),
                            t.getTipo().getDescripcion(),
                            t.getHoras(),
                            t.getPrecioHora()));
            }
        }
    }
    
}
