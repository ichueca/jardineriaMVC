/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.dao;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.modelo.Jardinero;

/**
 *
 * @author ichueca
 */
public class JardineroList implements JardineroDAO {
    private static List<Jardinero> jardineros = new ArrayList<>();
    
    @Override
    public Jardinero addJardinero(Jardinero nuevo) {
       Integer id = 1;
       //if (jardineros.size()>0){
       if (!jardineros.isEmpty()){
           id = jardineros.get(jardineros.size()-1).getId() + 1;
       }
       nuevo.setId(id);
       jardineros.add(nuevo);
       return nuevo;
    }

    @Override
    public void deleteJardinero(Jardinero eliminar) {
        jardineros.remove(eliminar);
    }

    @Override
    public void deleteJardinero(Integer id) {
        Jardinero eliminar = new Jardinero();
        eliminar.setId(id);
        deleteJardinero(eliminar);
    }

    @Override
    public void modificarJardinero(Jardinero modificar) {
        // Buscamos la posición en la que está el jardineo con ese ID
        int pos = jardineros.indexOf(modificar);
        if (pos != -1){
            // Sustiuimos el jardinero 
            jardineros.set(pos, modificar);
        }
    }

    @Override
    public Jardinero getJardinero(Integer id) {
        Jardinero j = new Jardinero();
        j.setId(id);
        int pos = jardineros.indexOf(j);
        //return (pos == -1)?null:jardineros.get(pos);
        if (pos == -1){
            // No encontrado
            return null;
        } else {
            // El jardinero con ese id está en la posición pos
            return jardineros.get(pos);
        }
    }

    @Override
    public List<Jardinero> getJardineros() {
        return jardineros;
    }
    
    @Override
    public Jardinero getJardinero(String dni) {
        int i;
        for(i=0; i<jardineros.size() && 
            !dni.equalsIgnoreCase(jardineros.get(i).getDni());
            i++);
        if (i < jardineros.size()){
            // Está en la posición i
            return jardineros.get(i);
        } else {
            // No está
            return null;
        }
    }
    
    public static void main(String[] args) {
        JardineroDAO dao = new JardineroList();
        dao.addJardinero(new Jardinero(null, "11111111", "Juan", "López"));
        dao.addJardinero(new Jardinero(null, "22222222", "Ana", "Sanz"));
        List<Jardinero> js = dao.getJardineros();
        /*for(int i=0; i<js.size(); i++){
            System.out.println("%d : %s %s [%s]".formatted(
                    js.get(i).getId(),
                    js.get(i).getNombre(),
                    js.get(i).getApellidos(),
                    js.get(i).getDni()));
        }*/
        for(Jardinero j : js){
            System.out.println("%d : %s %s [%s]".formatted(
                    j.getId(),
                    j.getNombre(),
                    j.getApellidos(),
                    j.getDni()));
        }
        System.out.println("=======================");
        System.out.println(dao.getJardinero(2));
        System.out.println(dao.getJardinero(5));
        System.out.println("=======================");
        dao.deleteJardinero(1);
        dao.deleteJardinero(5);
        for(Jardinero j : js){
            System.out.println("%d : %s %s [%s]".formatted(
                    j.getId(),
                    j.getNombre(),
                    j.getApellidos(),
                    j.getDni()));
        }
        System.out.println("=======================");
        Jardinero m = dao.getJardinero(2);
        m.setNombre("Nuevo");
        for(Jardinero j : js){
            System.out.println("%d : %s %s [%s]".formatted(
                    j.getId(),
                    j.getNombre(),
                    j.getApellidos(),
                    j.getDni()));
        }
        System.out.println("=======================");
        System.out.println(dao.getJardinero("22222222"));
        System.out.println(dao.getJardinero("33333333"));
    }

    
    
}
