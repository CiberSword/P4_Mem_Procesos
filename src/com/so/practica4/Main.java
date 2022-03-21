package com.so.practica4;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola
    static int opcion; //opción elegida del usuario


    public static void main(String[] args) {
        MapaMemoria memoriaSistema = new MapaMemoria(2048);
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
                        nuevoProceso.crearProceso();
                        nuevoProceso.Ver_Proceso();
                        if (memoriaSistema.espacioDisponible(nuevoProceso.getTamanioProceso())){
                            //Asignación del proceso a memoria
                        }
                        else
                            System.out.println("No hay suficiente espacio disponible para crear el proceso. \n" +
                                    "Ejecute o mate otros procesos e intente de nuevo");
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        /*nuevoProceso.Ver_Proceso();*/
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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