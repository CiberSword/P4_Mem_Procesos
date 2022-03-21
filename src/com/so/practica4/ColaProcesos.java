package com.so.practica4;

import java.util.ArrayList;
import java.util.List;



public class ColaProcesos {
    List<Proceso> colaProcesos = new ArrayList<Proceso>();
    List<String> procesosFinalizados = new ArrayList<String>();
    int contadorProcesos=0;

    public int getContadorProcesos() {
        return contadorProcesos;
    }

    public void setContadorProcesos(int contadorProcesos) {
        this.contadorProcesos = contadorProcesos;
    }

    public ColaProcesos() {
    }
    public void imprimirColaProcesos(){
        int numeracion=1;
        System.out.println("\nCola de procesos");
        System.out.println("\nNombre              PID         Instrucciones Pendientes");
        for(Proceso p : colaProcesos){
            if(numeracion==1)
                System.out.println(numeracion + ".- " + p.getNomProceso()+"         "+p.getPID()+"                    "+p.getInstruccionesTotales()+"     <------ Proceso activo");
            if(numeracion!=1)
                System.out.println(numeracion + ".- " + p.getNomProceso()+"         "+p.getPID()+"                    "+p.getInstruccionesTotales());
            numeracion++;
        }
    }

    public void ejecutarActual (){
        int tempPendientes = colaProcesos.get(0).getInstruccionesTotales()-5;
        int tempEjecutadas = colaProcesos.get(0).getInstruccionesEjecutadas()+5;
        Proceso tempP;

        colaProcesos.get(0).setInstruccionesTotales(tempPendientes);
        colaProcesos.get(0).setInstruccionesEjecutadas(tempEjecutadas);
        if (tempPendientes ==0){
            //EliminarProcesoMemoria
        }
        tempP = colaProcesos.get(0);
        colaProcesos.remove(0);
        colaProcesos.add(tempP);
    }

    public void pasarSiguiente (){
        Proceso tempP;
        tempP = colaProcesos.get(0);
        colaProcesos.remove(0);
        colaProcesos.add(tempP);
    }

    public List<Proceso> getColaProcesos() {
        return colaProcesos;
    }

    public void setColaProcesos(List<Proceso> colaProcesos) {
        this.colaProcesos = colaProcesos;
    }
    public void addProceso(Proceso nuevoProce) {
        contadorProcesos++;
        this.colaProcesos.add(nuevoProce);
    }
}
