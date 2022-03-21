package com.so.practica4;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Proceso {

    String nomProceso;
    int PID;
    int instruccionesTotales;
    int instruccionesEjecutadas;
    int tamanioProceso;
    List <DireccionMemoria> dirAsignadas;

    public Proceso() {
    }
    public void crearProceso() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Lectura del nombre del proceso
        System.out.println("Ingresa el nombre del proceso: ");
        this.setNomProceso(scanner.nextLine());
        //PID aleatorio
        this.setPID(random.nextInt(999999));
        //Num aleatorio instrucciones entre 10-30
        setInstruccionesTotales(random.nextInt(30) +10);
        //Num aleatorio para espacio proceso
        int tamaniolocalidades[] = {64,128,256,512};
        int selecciontamanio = random.nextInt(3);
        setTamanioProceso(tamaniolocalidades[selecciontamanio]);

       /* dirAsignadas.add(new DireccionMemoria(1456, this.nomProceso, this.PID));*/
    }

    public String getNomProceso() {
        return nomProceso;
    }

    public void setNomProceso(String nomProceso) {
        this.nomProceso = nomProceso;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getInstruccionesTotales() {
        return instruccionesTotales;
    }

    public void setInstruccionesTotales(int instruccionesTotales) {
        this.instruccionesTotales = instruccionesTotales;
    }

    public int getInstruccionesEjecutadas() {
        return instruccionesEjecutadas;
    }

    public void setInstruccionesEjecutadas(int instruccionesEjecutadas) {
        this.instruccionesEjecutadas = instruccionesEjecutadas;
    }

    public int getTamanioProceso() {
        return tamanioProceso;
    }

    public void setTamanioProceso(int tamanioProceso) {
        this.tamanioProceso = tamanioProceso;
    }

    public List getDirAsignadas() {
        return dirAsignadas;
    }

    public void setDirAsignadas(List dirAsignadas) {
        this.dirAsignadas = dirAsignadas;
    }


    public void VerProceso(){
        System.out.println("Nombre: "+this.nomProceso + "\n");
        System.out.println("ID Único: "+this.PID + "\n");
        System.out.println("Intrucciones Totales: "+this.instruccionesTotales + "\n");
        System.out.println("Instrucciones ejecutadas: "+this.instruccionesEjecutadas + "\n");
        System.out.println("Direcciones de memoria asignadas: "/*+this.dirAsignadas*/ + "\n");
        /*imprimirLista(dirAsignadas);*/
    }
    public void imprimirDirecciones(){
        for(DireccionMemoria var : dirAsignadas){
            System.out.print(var.getNumDireccion()+",");
        }
    }
}
