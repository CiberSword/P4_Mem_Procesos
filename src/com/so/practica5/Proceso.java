package com.so.practica5;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Proceso {

    String nomProceso;
    int PID;
    int instruccionesTotales;
    int instruccionesEjecutadas=0;
    int tamanioProceso;
    int numPags;
    List <DireccionMemoria> dirAsignadas;
    TablaPaginas tablaPaginas;

    public Proceso() {
    }

    public void crearProceso(int tamanioPaginas) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Lectura del nombre del proceso
        System.out.println("Ingresa el nombre del proceso: ");
        this.setNomProceso(scanner.nextLine());
        //PID aleatorio
        this.setPID(random.nextInt(999999));
        //Num aleatorio instrucciones entre 10-30
        setInstruccionesTotales((int) (Math.random() * 30-10+1) + 10);
        int tamaniolocalidades[] = {64,128,256,512};
        int selecciontamanio;

        //Num aleatorio para espacio proceso
        selecciontamanio = random.nextInt(3);
        setTamanioProceso(tamaniolocalidades[selecciontamanio]);

        //Seleccionar tamaño proceso
/*        System.out.println("Seleccione el tamaño del proceso:\n" +
                "  1.- 64\n  2.- 128\n  3.- 256\n  4.- 512");
        selecciontamanio=scanner.nextInt();
        setTamanioProceso(tamaniolocalidades[selecciontamanio-1]);*/

        //Calculo de páginas
        calcNumPags(tamanioPaginas);
        tablaPaginas = new TablaPaginas(numPags,tamanioPaginas);
    }

    public void VerProceso(){
        System.out.println("Proceso Actual:");
        System.out.println("- Nombre: "+this.nomProceso);
        System.out.println("- ID Único: "+this.PID);
        System.out.println("- Intrucciones Totales: "+this.instruccionesTotales);
        System.out.println("- Instrucciones ejecutadas: "+this.instruccionesEjecutadas);
        System.out.println("- Direcciones de memoria asignadas: "/*+this.dirAsignadas*/);
        imprimirDirecciones();
        tablaPaginas.imprimirTablaPaginas();
    }
    public void imprimirDirecciones(){
        for(DireccionMemoria var : dirAsignadas){
            System.out.print(var.getNumDireccion()+",");
        }
    }

    public void calcNumPags(int tamanioPaginas) {
        this.numPags = tamanioProceso/tamanioPaginas;
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

    public int getNumPags() {
        return numPags;
    }

    public void setNumPags(int numPags) {
        this.numPags = numPags;
    }

    public List getDirAsignadas() {
        return dirAsignadas;
    }

    public void setDirAsignadas(List dirAsignadas) {
        this.dirAsignadas = dirAsignadas;
    }

    public TablaPaginas getTablaPaginas() {
        return tablaPaginas;
    }

    public void setTablaPaginas(TablaPaginas tablaPaginas) {
        this.tablaPaginas = tablaPaginas;
    }

}
