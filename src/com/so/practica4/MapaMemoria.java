package com.so.practica4;

import java.util.ArrayList;
import java.util.List;

public class MapaMemoria {
    int tamanio;
    List<DireccionMemoria> mapaMemoria = new ArrayList<DireccionMemoria>();

    //Constructor
    public MapaMemoria(int tamanio) {
        this.tamanio = tamanio;
        //Añade Direcciones de Memoria según el tamaño especificado
        for (int i = 1; i <= tamanio; i++) {
            mapaMemoria.add(new DireccionMemoria(i, null, null));
        }
    }

    public void imprimirHuecosMemoria() {
        System.out.println("\nLocalidad       Proceso");
        for (DireccionMemoria mem : mapaMemoria){
            if(mem.PID != null)
                System.out.println("    "+mem.getNumDireccion() + "            " + mem.getNombreProceso());
        }
    }

    public void analizarMemoria(){
        Integer dirActual=0;
        Integer tamanio=0;
        Integer PIDactual=0;
        List<Integer> dirInicialesProceso = new ArrayList<Integer>();
        List<Integer> tamanioProceso = new ArrayList<Integer>();
        List<Integer> dirInicialesHuecos = new ArrayList<Integer>();
        List<Integer> tamanioHueco = new ArrayList<Integer>();


        for (DireccionMemoria mem : mapaMemoria){
            if(tamanio==0){
                dirActual = mem.getNumDireccion();
                PIDactual = mem.PID;
            }
            if(mem.PID != null){//Si hay proceso
                tamanio++;
                if(PIDactual == null){
                    tamanioHueco.add(tamanio-1);
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
                if(!mem.PID.equals(PIDactual)){
                    System.out.println("Entra y su ID de memoria es: "+mem.PID+" y el ID ACTUAL es: "+PIDactual);
                    tamanioProceso.add(tamanio-1);
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
            }
            if(mem.PID == null){//Si es hueco
                tamanio++;
                if(PIDactual != null){
                    tamanioProceso.add(tamanio-1);
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
            }
        }
        System.out.println("Tamaño Procesos: "+tamanioProceso);
        System.out.println("Tamaño Huecos: "+tamanioHueco);
    }

    public void imprimirInfo() {
        System.out.println("Memoria total del sistema: "+tamanio+" localidades");
    }

    public boolean espacioDisponible(int tamanioPaginas, int numPags){
        boolean cabenPags;
        int conteoMarcos=0;
        int localidadesJuntas=0;
        for (DireccionMemoria temp: mapaMemoria){
            if(temp.PID == null){
                localidadesJuntas++;
                if(localidadesJuntas>=tamanioPaginas)
                    conteoMarcos++;
            }
            else
                localidadesJuntas=0;
        }
        if (conteoMarcos>=numPags)
            cabenPags = true;
        else
            cabenPags = false;
        return cabenPags;
    }

    public List<DireccionMemoria> direccionesParaProceso (Proceso nuevoProce){
        int localidadesNecesarias = nuevoProce.getTamanioProceso();
        List<DireccionMemoria> localidadesAsignadas = new ArrayList<DireccionMemoria>();
        for(DireccionMemoria n : mapaMemoria){
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


