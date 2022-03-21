package com.so.practica4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaMemoria {
    int tamanio;
    List<DireccionMemoria> numDir = new ArrayList<DireccionMemoria>();

    //Constructor
    public MapaMemoria(int tamanio) {
        this.tamanio = tamanio;
        //Añade Direcciones de Memoria según el tamaño especificado
        for (int i = 1; i <= tamanio; i++) {
            numDir.add(new DireccionMemoria(i, null, null));
        }
    }

    public void imprimirInfo() {
        System.out.println("Memoria total disponible: " + tamanio + " localidades.");
    }

    public boolean espacioDisponible(int locsRequeridas){
        boolean hayEspacio;
        int conteoLibres=0;
        for (DireccionMemoria temp: numDir){
            if(temp.PID == null)
                conteoLibres++;
        }
        if (conteoLibres>=locsRequeridas)
            hayEspacio = true;
        else
            hayEspacio = false;
        return hayEspacio;
    }

    public List<DireccionMemoria> direccionesParaProceso (Proceso nuevoProce){
        int localidadesNecesarias = nuevoProce.getTamanioProceso();
        List<DireccionMemoria> localidadesAsignadas = new ArrayList<DireccionMemoria>();
        for(DireccionMemoria n : numDir){
            if(n.getPID()==null && localidadesNecesarias>=1){
                localidadesAsignadas.add(n);
                n.setPID(nuevoProce.getPID());
                n.setNombreProceso(nuevoProce.getNomProceso());
                localidadesNecesarias--;
            }
        }
        return localidadesAsignadas;
    }


    }


