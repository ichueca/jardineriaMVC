/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.modelo;

import java.util.Date;
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
public class Trabajo {
    @EqualsAndHashCode.Include
    private Integer id;
    private String descripcion;
    private Integer horas;
    private Double precioHora;
    private Date fechaInicio;
    private Date fechaFin;
    private Jardinero jardinero;
    private TipoTrabajo tipo;
    
    
}
