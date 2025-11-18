/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.dao;

import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.modelo.Jardinero;

/**
 *
 * @author ichueca
 */
public interface JardineroDAO {
    public Jardinero addJardinero(Jardinero nuevo);
    public void deleteJardinero(Jardinero eliminar);
    public void deleteJardinero(Integer id);
    public void modificarJardinero(Jardinero modificar);
    public Jardinero getJardinero(Integer id);
    public List<Jardinero> getJardineros();
    public Jardinero getJardinero(String dni);
}
