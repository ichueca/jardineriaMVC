/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.dao;

import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.modelo.Trabajo;

/**
 *
 * @author ichueca
 */
public interface TrabajoDAO {
    public Trabajo nuevoTrabajo(Trabajo nuevo);
    public void modificarTrabajo(Trabajo modificar);
    public Trabajo getTrabajo (Integer id);
    public void eliminarTrabajo (Integer id);
    public void eliminarTrabajo (Trabajo trabajo);
    public List<Trabajo> getTrabajos();
}
