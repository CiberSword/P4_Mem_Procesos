package com.so.practica4;

import java.util.ArrayList;
import java.util.List;



public class ColaProcesos {
    List<Proceso> colaProcesos = new ArrayList<Proceso>();
    List<String> procesosFinalizados = new ArrayList<String>();
    public ColaProcesos() {
    }
    public void imprimirColaProcesos(){
        System.out.println("Cola de procesos");
        for(Proceso p : colaProcesos){
            System.out.println(p.getNomProceso());
        }
    }

    public List<Proceso> getColaProcesos() {
        return colaProcesos;
    }

    public void setColaProcesos(List<Proceso> colaProcesos) {
        this.colaProcesos = colaProcesos;
    }
    public void addProceso(Proceso nuevoProce) {
        this.colaProcesos.add(nuevoProce);
    }
}
