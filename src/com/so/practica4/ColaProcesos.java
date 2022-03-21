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
            System.out.print(numeracion + ".- " + p.getNomProceso()+"         "+p.getPID()+"                    1"+(p.getInstruccionesTotales()-p.getInstruccionesEjecutadas()));
            if(numeracion==1)
                System.out.println("    <-------- Proceso activo");
            numeracion++;
        }
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
