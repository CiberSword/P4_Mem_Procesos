package com.so.practica4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola
    static int opcion; //opción elegida del usuario


    public static void main(String[] args) {
        MapaMemoria memoriaSistema = new MapaMemoria(2048);
        ColaProcesos listaProcesos = new ColaProcesos();

        memoriaSistema.imprimirInfo();
        //Elección menu
        while (opcion != 8) {
            //Try catch para evitar que el programa termine si hay un error
            try {
                System.out.println("Elige opción:\n" +
                        "1. Crear Proceso nuevo\n" +
                        "2. Ver estado actual del sistema\n" +
                        "3. Imprimir cola de procesos\n" +
                        "4. Ver proceso actual\n" +
                        "5. Ejecutar proceso actual\n" +
                        "6. Pasar al proceso siguiente\n" +
                        "7. Matar proceso actual\n" +
                        "8. Salir del programa");

                //Recoger una variable por consola
                opcion = Integer.parseInt(scanner.nextLine());

                //Ejemplo de switch case en Java
                switch (opcion) {
                    case 1:
                        Proceso nuevoProceso = new Proceso();
                        //Pide nombre del proceso y se generan los valores aleatorios de PID e Instruccciones
                        nuevoProceso.crearProceso();

                        //Verifica si hay espacio en memoria revisando nulls
                        if (memoriaSistema.espacioDisponible(nuevoProceso.getTamanioProceso())) {
                            //Asignación de localidades de memoria al proceso
                            nuevoProceso.setDirAsignadas(memoriaSistema.direccionesParaProceso(nuevoProceso));
                            //Se añade proceso a la cola de procesos
                            listaProcesos.addProceso(nuevoProceso);
                            System.out.println("Proceso creado correctamente");
                        } else
                            System.out.println("No hay suficiente espacio disponible para crear el proceso. \n" +
                                    "Ejecute o mate otros procesos e intente de nuevo");
                        break;
                    case 2:
                        System.out.println("Procesos en cola: " + listaProcesos.getContadorProcesos());
                        memoriaSistema.imprimirDetallesMem();
                        listaProcesos.imprimirProcesosFinalizados();
                        listaProcesos.imprimirProcesosMatados();
                        break;
                    case 3:
                        listaProcesos.imprimirColaProcesos();
                        break;
                    case 4:
                        listaProcesos.colaProcesos.get(0).VerProceso();
                        break;
                    case 5:
                        boolean activo;
                        activo = listaProcesos.ejecutarActual();
                        if (activo==false) {
                            for (DireccionMemoria tempMem : listaProcesos.colaProcesos.get(listaProcesos.colaProcesos.size()-1).dirAsignadas){
                                tempMem.setPID(null);
                                tempMem.setNombreProceso(null);
                            }
                            listaProcesos.procesosFinalizados.add(listaProcesos.colaProcesos.get(listaProcesos.colaProcesos.size()-1));
                            System.out.println("El proceso "+listaProcesos.colaProcesos.get(listaProcesos.colaProcesos.size()-1).getNomProceso()+ " con PID "+
                                    listaProcesos.colaProcesos.get(listaProcesos.colaProcesos.size()-1).getPID()+" ha finalizado");
                            listaProcesos.colaProcesos.remove(listaProcesos.colaProcesos.size()-1);
                            listaProcesos.contadorProcesos--;
                        }
                        break;
                    case 6:
                        listaProcesos.pasarSiguiente();
                        break;
                    case 7:
                        for (DireccionMemoria tempMem : listaProcesos.colaProcesos.get(0).dirAsignadas){
                            tempMem.setPID(null);
                            tempMem.setNombreProceso(null);
                        }
                        listaProcesos.procesosMatados.add(listaProcesos.colaProcesos.get(0));
                        System.out.println("El proceso "+listaProcesos.colaProcesos.get(0).getNomProceso()+ " con PID "+
                                listaProcesos.colaProcesos.get(0).getPID()+" con " + listaProcesos.colaProcesos.get(0).getInstruccionesTotales() +
                                " intrucciones pendientes fue detenido a la fuerza");
                        listaProcesos.colaProcesos.remove(0);
                        listaProcesos.contadorProcesos--;
                        break;
                    case 8:
                        System.out.println("Procesos antes del cierre:");
                        listaProcesos.imprimirColaProcesos();
                        System.out.println("Adios!");
                        break;
                    default:
                        System.out.println("Número inválido, selecciona de nuevo");
                        break;
                }

                System.out.println("\n");

            } catch (Exception e) {
                System.out.println("Error");
            }
        }

    }

}