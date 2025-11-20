/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.modelo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author ichueca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jardinero {
    @EqualsAndHashCode.Include
    private Integer id;
    private String dni;
    private String nombre;
    private String apellidos;
    private List<Trabajo> trabajos  = new ArrayList<>();

    public Jardinero(Integer id, String dni, String nombre, String apellidos) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    
    

    /*public void asignarTrabajo(Trabajo trabajo){
=======
    public void asignarTrabajo(Trabajo trabajo){

        this.trabajos.add(trabajo);
        trabajo.setJardinero(this);
    }
    
    public void desasignarTrabajo(Trabajo trabajo){
        this.trabajos.remove(trabajo);
        trabajo.setJardinero(null);

    }*/


    @Override
    public String toString() {
        return "Jardinero{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }
    
}
