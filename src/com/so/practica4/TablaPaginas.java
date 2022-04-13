package com.so.practica4;

import java.util.ArrayList;
import java.util.List;

public class TablaPaginas {
    int numPags;
    int tamanioPaginas;
    List<Integer> dirInicialPaginas = new ArrayList<Integer>();

    public TablaPaginas(int numPags, int tamanioPaginas) {
        this.numPags = numPags;
        this.tamanioPaginas = tamanioPaginas;
    }

    public void imprimirTablaPaginas(){
        System.out.println("\n\nTabla de páginas: ");
        System.out.println("Pagina          Marco");
        for (int i=0;i<=numPags-1;i++){
            System.out.println("   " + i + "         " + "Marco " + ((dirInicialPaginas.get(i)+1)/tamanioPaginas) +
                    " (direcciones "+ dirInicialPaginas.get(i)+" - " + (dirInicialPaginas.get(i)+tamanioPaginas-1) + ")");
        }
    }

    public List<Integer> getDirInicialPaginas() {
        return dirInicialPaginas;
    }

    public void setDirInicialPaginas(List<Integer> dirInicialPaginas) {
        this.dirInicialPaginas = dirInicialPaginas;
    }

}

