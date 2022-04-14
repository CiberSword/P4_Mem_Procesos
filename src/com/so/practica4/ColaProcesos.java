package com.so.practica4;

import java.util.ArrayList;
import java.util.List;

public class ColaProcesos {
    List<Proceso> colaProcesos = new ArrayList<Proceso>();
    List<Proceso> procesosFinalizados = new ArrayList<Proceso>();
    List<Proceso> procesosMatados = new ArrayList<Proceso>();
    int contadorProcesos = 0;
    int desplazamientos = 0;

    public int getContadorProcesos() {
        return contadorProcesos;
    }

    public void setContadorProcesos(int contadorProcesos) {
        this.contadorProcesos = contadorProcesos;
    }

    public ColaProcesos() {
    }

    public void imprimirColaProcesos() {
        int numeracion = 1;
        System.out.println("\nCola de procesos");
        System.out.println("\nNombre              PID         Instrucciones Pendientes");
        for (Proceso p : colaProcesos) {
            if (numeracion == 1)
                System.out.println(numeracion + ".- " + p.getNomProceso() + "         " + p.getPID() + "                    " + p.getInstruccionesTotales() + "     <------ Proceso activo");
            if (numeracion != 1)
                System.out.println(numeracion + ".- " + p.getNomProceso() + "         " + p.getPID() + "                    " + p.getInstruccionesTotales());
            numeracion++;
        }
    }

    public void imprimirProcesosFinalizados(){
        int contador = 0;
        System.out.println("Lista de procesos finalizados:");
        for (Proceso p : procesosFinalizados){
            System.out.println(contador + ".- "+p.getNomProceso());
            contador++;
        }
    }

    public void imprimirProcesosMatados(){
        int contador = 0;
        System.out.println("Lista de procesos matados:");
        for (Proceso p : procesosMatados){
            System.out.println(contador + ".- "+p.getNomProceso());
            contador++;
        }
    }

    public List<Proceso> getProcesosFinalizados() {
        return procesosFinalizados;
    }

    public void setProcesosFinalizados(List<Proceso> procesosFinalizados) {
        this.procesosFinalizados = procesosFinalizados;
    }

    public List<Proceso> getProcesosMatados() {
        return procesosMatados;
    }

    public void setProcesosMatados(List<Proceso> procesosMatados) {
        this.procesosMatados = procesosMatados;
    }

    public int getDesplazamientos() {
        return desplazamientos;
    }

    public void setDesplazamientos(int desplazamientos) {
        this.desplazamientos = desplazamientos;
    }

    public boolean ejecutarActual() {
        int tempPendientes = colaProcesos.get(0).getInstruccionesTotales() - 5;
        int tempEjecutadas = colaProcesos.get(0).getInstruccionesEjecutadas() + 5;
        boolean activo = true;

        colaProcesos.get(0).setInstruccionesTotales(tempPendientes);
        colaProcesos.get(0).setInstruccionesEjecutadas(tempEjecutadas);
        if (tempPendientes <= 0) {
            activo = false;
        }
        pasarSiguiente();
        return activo;
    }

    public void pasarSiguiente() {
        Proceso tempP;
        tempP = colaProcesos.get(0);
        colaProcesos.remove(0);
        colaProcesos.add(tempP);
        desplazamientos++;
        if (desplazamientos>= colaProcesos.size())
            desplazamientos=0;
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
