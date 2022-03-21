package com.so.practica4;

public class DireccionMemoria {
    int numDireccion;
    String nombreProceso;
    Integer PID;

    public DireccionMemoria(Integer numDireccion, String nombreProceso, Integer PID) {
        this.numDireccion = numDireccion;
        this.nombreProceso = nombreProceso;
        this.PID = PID;
    }

    public void infoLocalidad(){
        System.out.println("numDireccion" + "     " + "PID" + "     " + "nombreProceso");
        System.out.println(numDireccion + "     " + PID + "     " + nombreProceso);
    }

    public int getNumDireccion() {
        return numDireccion;
    }

    public void setNumDireccion(int numDireccion) {
        this.numDireccion = numDireccion;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }
}