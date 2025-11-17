/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.trabajosjardineria.dao;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.trabajosjardineria.modelo.TipoTrabajo;

/**
 *
 * @author ichueca
 */
public class TipoTrabajoMatriz implements TipoTrabajoDAO{
    private static TipoTrabajo[] tiposTrabajo = {
        new TipoTrabajo(1, "Poda"),
        new TipoTrabajo(2, "desbrozar"),
        new TipoTrabajo(3, "Arreglar jardín"),
        new TipoTrabajo(4, "Ornamentación Floral")
    };
            
    @Override
    public TipoTrabajo getTipoTrabajo(Integer id) {
        int i;
        for(i=0; i<tiposTrabajo.length &&
            !id.equals(tiposTrabajo[i].getId());i++);
        if ( i < tiposTrabajo.length ){
            // El elemento que busco está en la POSICIÓN i
            return tiposTrabajo[i];
        } else {
            // No está
            return null;
        }
        /*for (int j = 0; j < tiposTrabajo.length; j++) {
            if (id.equals(tiposTrabajo[j].getId())){
                return tiposTrabajo[j];
            }
        }
        return null;*/
    }

    @Override
    public List<TipoTrabajo> getTiposTrabajo() {
        List<TipoTrabajo> tipos = new ArrayList<>();
        for(TipoTrabajo t : tiposTrabajo){
            tipos.add(t);
        }
        return tipos;
    }
    
    
    public static void main(String[] args) {
        TipoTrabajoDAO dao = new TipoTrabajoMatriz();
        for(TipoTrabajo tt : dao.getTiposTrabajo()){
            System.out.println(tt);
        }
        System.out.println("====================");
        System.out.println(dao.getTipoTrabajo(3));
        System.out.println(dao.getTipoTrabajo(5));
    }
}
