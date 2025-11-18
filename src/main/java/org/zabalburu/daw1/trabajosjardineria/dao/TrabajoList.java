/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.modelo.Trabajo;

/**
 *
 * @author ichueca
 */
public class TrabajoList implements TrabajoDAO{
    
    private static List<Trabajo> trabajos = new ArrayList<>();
    
    @Override
    public Trabajo nuevoTrabajo(Trabajo nuevo) {
        Integer id = 1;
        if (!trabajos.isEmpty()){
            id = trabajos.get(trabajos.size()-1).getId() + 1;
        }
        nuevo.setId(id);
        trabajos.add(nuevo);
        return nuevo;
    }

    @Override
    public void modificarTrabajo(Trabajo modificar) {
        int pos = trabajos.indexOf(modificar);
        if (pos != -1){
            trabajos.set(pos,modificar);
        }
    }

    @Override
    public Trabajo getTrabajo(Integer id) {
        Trabajo t = new Trabajo();
        t.setId(id);
        int pos = trabajos.indexOf(t);
        if (pos == -1){
            return null;
        } else {
            return trabajos.get(pos);
        }
    }

    @Override
    public void eliminarTrabajo(Integer id) {
        Trabajo t = new Trabajo();
        t.setId(id);
        trabajos.remove(t);
    }

    @Override
    public void eliminarTrabajo(Trabajo trabajo) {
        trabajos.remove(trabajo);
    }

    @Override
    public List<Trabajo> getTrabajos() {
        return trabajos;
    }
    
    public static void main(String[] args) {
        TrabajoDAO dao = new TrabajoList();
        TipoTrabajoDAO tipoDao = new TipoTrabajoMatriz();
        dao.nuevoTrabajo(new Trabajo(null, "Poda Casa 1", 30, 25.0, 
                new GregorianCalendar(2025,10,21).getTime(), null, null, tipoDao.getTipoTrabajo(1)));
        dao.nuevoTrabajo(new Trabajo(null, "Arreglo jardín", 5, 25.0, 
                new GregorianCalendar(2025,10,24).getTime(), null, null, tipoDao.getTipoTrabajo(3)));
        for(Trabajo t : dao.getTrabajos()){
            System.out.println(t);
        }
        System.out.println("=======================");
        System.out.println(dao.getTrabajo(1));
        System.out.println(dao.getTrabajo(10));
        Trabajo modificar = dao.getTrabajo(1);
        modificar.setPrecioHora(55.0);
        dao.modificarTrabajo(modificar);
        for(Trabajo t : dao.getTrabajos()){
            System.out.println(t);
        }
        System.out.println("=======================");
        dao.eliminarTrabajo(2);
        for(Trabajo t : dao.getTrabajos()){
            System.out.println(t);
        }
        System.out.println("=======================");
    }
    
}
