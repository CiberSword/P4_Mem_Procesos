package com.so.practica4;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola
    static int opcion; //opción elegida del usuario


    public static void main(String[] args) {
        int tamanioPaginas = 16;
        MapaMemoria memoriaSistema = new MapaMemoria(1024);
        ColaProcesos colaProcesos = new ColaProcesos();

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
                        "9. Analizar memoria BETA\n" +
                        "8. Salir del programa");

                //Recoger una variable por consola
                opcion = Integer.parseInt(scanner.nextLine());

                //Ejemplo de switch case en Java
                switch (opcion) {
                    case 1:
                        Proceso nuevoProceso = new Proceso();
                        //Pide nombre del proceso y se generan los valores aleatorios de PID e Instruccciones
                        nuevoProceso.crearProceso();
                        int numPags = nuevoProceso.getTamanioProceso()/tamanioPaginas;
                        //Verifica si hay espacio en memoria revisando nulls
                        if (memoriaSistema.espacioDisponible(tamanioPaginas,numPags)) {
                            //Asignación de localidades de memoria al proceso
                            nuevoProceso.setDirAsignadas(memoriaSistema.direccionesParaProceso(nuevoProceso));
                            //Se añade proceso a la cola de procesos
                            colaProcesos.addProceso(nuevoProceso);
                            System.out.println("Proceso creado correctamente");
                        } else
                            System.out.println("No hay suficiente espacio disponible para crear el proceso. \n" +
                                    "Ejecute o mate otros procesos e intente de nuevo");
                        break;
                    case 2:
                        System.out.println("Procesos en cola: " + colaProcesos.getContadorProcesos());
                        memoriaSistema.imprimirHuecosMemoria();
                        colaProcesos.imprimirProcesosFinalizados();
                        colaProcesos.imprimirProcesosMatados();
                        break;
                    case 3:
                        colaProcesos.imprimirColaProcesos();
                        break;
                    case 4:
                        colaProcesos.colaProcesos.get(0).VerProceso();
                        break;
                    case 5:
                        boolean activo;
                        activo = colaProcesos.ejecutarActual();
                        if (activo == false) {
                            for (DireccionMemoria tempMem : colaProcesos.colaProcesos.get(colaProcesos.colaProcesos.size() - 1).dirAsignadas) {
                                tempMem.setPID(null);
                                tempMem.setNombreProceso(null);
                            }
                            colaProcesos.procesosFinalizados.add(colaProcesos.colaProcesos.get(colaProcesos.colaProcesos.size() - 1));
                            System.out.println("El proceso " + colaProcesos.colaProcesos.get(colaProcesos.colaProcesos.size() - 1).getNomProceso() + " con PID " +
                                    colaProcesos.colaProcesos.get(colaProcesos.colaProcesos.size() - 1).getPID() + " ha finalizado");
                            colaProcesos.colaProcesos.remove(colaProcesos.colaProcesos.size() - 1);
                            colaProcesos.contadorProcesos--;
                        }
                        break;
                    case 6:
                        colaProcesos.pasarSiguiente();
                        break;
                    case 7:
                        for (DireccionMemoria tempMem : colaProcesos.colaProcesos.get(0).dirAsignadas) {
                            tempMem.setPID(null);
                            tempMem.setNombreProceso(null);
                        }
                        colaProcesos.procesosMatados.add(colaProcesos.colaProcesos.get(0));
                        System.out.println("El proceso " + colaProcesos.colaProcesos.get(0).getNomProceso() + " con PID " +
                                colaProcesos.colaProcesos.get(0).getPID() + " con " + colaProcesos.colaProcesos.get(0).getInstruccionesTotales() +
                                " intrucciones pendientes fue detenido a la fuerza");
                        colaProcesos.colaProcesos.remove(0);
                        colaProcesos.contadorProcesos--;
                        break;
                    case 8:
                        System.out.println("Procesos antes del cierre:");
                        colaProcesos.imprimirColaProcesos();
                        System.out.println("Adios!");
                        break;
                    case 9:
                        memoriaSistema.analizarMemoria();
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