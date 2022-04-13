package com.so.practica4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaMemoria {

    int tamanio;
    List<DireccionMemoria> mapaMemoria = new ArrayList<DireccionMemoria>();
    //Constructor
    public MapaMemoria(int tamanio) {
        this.tamanio = tamanio;
        //Añade Direcciones de Memoria según el tamaño especificado
        for (int i = 0; i <= tamanio-1; i++) {
            mapaMemoria.add(new DireccionMemoria(i, null, null));
        }
    }
    //Para testear
    public void imprimirDetallesMem() {
        System.out.println("\nLocalidad       Proceso");
        for (DireccionMemoria mem : mapaMemoria)
                System.out.println("    "+mem.getNumDireccion() + "            " + mem.getNombreProceso());
    }

    public void imprimirHuecosMemoria() {
        System.out.println("\nLocalidad       Proceso");
        for (DireccionMemoria mem : mapaMemoria){
            if(mem.PID != null)
                System.out.println("    "+mem.getNumDireccion() + "            " + mem.getNombreProceso());
        }
    }

    public void analizarMemoria(){
        LinkedList<Estado> ListaLigadaMemoria = new LinkedList<Estado>();
        Estado Process = new Estado();
        Integer dirActual=0;
        Integer tamanio=0;
        Integer PIDactual=0;
        List<Integer> tamanioProceso = new ArrayList<Integer>();
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
                    ListaLigadaMemoria.add(new Estado("Hueco",dirActual,tamanio-1));
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
                if(!mem.PID.equals(PIDactual)){
                    tamanioProceso.add(tamanio-1);
                    ListaLigadaMemoria.add(new Estado("Proceso", dirActual,tamanio-1));
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
                if(mem.getNumDireccion() == 1023){
                    tamanioProceso.add(tamanio);
                    ListaLigadaMemoria.add(new Estado("Proceso", dirActual,tamanio));
                }
            }
            if(mem.PID == null){//Si es hueco
                tamanio++;
                if(PIDactual != null){
                    tamanioProceso.add(tamanio-1);
                    ListaLigadaMemoria.add(new Estado("Proceso", dirActual,tamanio-1));
                    dirActual = mem.getNumDireccion();
                    PIDactual = mem.PID;
                    tamanio=1;
                }
                if(mem.getNumDireccion() == 1023){
                    tamanioHueco.add(tamanio);
                    ListaLigadaMemoria.add(new Estado("Hueco",dirActual,tamanio));
                }
            }
        }
        System.out.println("Tamaño Procesos: "+tamanioProceso);
        System.out.println("Tamaño Huecos: "+tamanioHueco);
        imprimirLista(ListaLigadaMemoria);
    }

    public void imprimirLista(LinkedList<Estado> ListaLM){
        System.out.println("\nLista ligada de localidades de memoria (H/P - Dirección - Tamaño)\n");
        for(Estado list : ListaLM){
            if(list.equals(ListaLM.getLast())) {
                System.out.print("["+list.getNombre() + " - " +list.getLocalidadMemoria() + " - " + list.getTamanioEstado() +"] ");
            }
            else System.out.print("["+list.getNombre() + " - " +list.getLocalidadMemoria() + " - " + list.getTamanioEstado() +"]-> ");
        }
        System.out.println("\n");
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
                if(localidadesJuntas>=tamanioPaginas){
                    conteoMarcos++;
                    localidadesJuntas=0;
                }
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

    public List<DireccionMemoria> direccionesParaProceso (Proceso proceso, int tamanioPaginas, int PagsNecesarias){
        int localidadesJuntas=0;
        List<DireccionMemoria> localidadesAsignadas = new ArrayList<DireccionMemoria>();
        for (int i= 0; i<=tamanio-1;i++){
            if(mapaMemoria.get(i).getPID()==null) {
                localidadesJuntas++;
                if (localidadesJuntas >= tamanioPaginas && PagsNecesarias>=1) {
                    proceso.getTablaPaginas().getDirInicialPaginas().add(mapaMemoria.get(i-tamanioPaginas+1).getNumDireccion());
                    for (int j = tamanioPaginas-1; j >= 0; j--) {
                        localidadesAsignadas.add(mapaMemoria.get(i - j));
                        mapaMemoria.get(i - j).setPID(proceso.getPID());
                        mapaMemoria.get(i - j).setNombreProceso(proceso.getNomProceso());
                    }
                    PagsNecesarias--;
                    localidadesJuntas = 0;
                }
            }
            else
                localidadesJuntas=0;
        }
        return localidadesAsignadas;
    }
    public void desfragmentarMemoria(ColaProcesos colaProcesos, int tamanioPaginas){
            for(Proceso proceso:colaProcesos.getColaProcesos()) {
                for (DireccionMemoria dirMem : proceso.dirAsignadas) {
                    dirMem.setPID(null);
                    dirMem.setNombreProceso(null);
                }
                proceso.setDirAsignadas(direccionesParaProceso(proceso,tamanioPaginas,proceso.getNumPags()));
            }
        }
}
