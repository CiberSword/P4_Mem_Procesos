package com.so.practica4;

public class Estado {
    int LocalidadMemoria;
    int TamanioEstado;
    String Nombre;

    public Estado() {
    }

    public Estado(String Nombre, Integer LocalidadMemoria, Integer TamanioEstado) {
        this.Nombre = Nombre;
        this.LocalidadMemoria = LocalidadMemoria;
        this.TamanioEstado = TamanioEstado;
    }


    public int getLocalidadMemoria() {
        return LocalidadMemoria;
    }

    public void setLocalidadMemoria(int localidadMemoria) {
        LocalidadMemoria = localidadMemoria;
    }

    public int getTamanioEstado() {
        return TamanioEstado;
    }

    public void setTamanioEstado(int tamanioEstado) {
        TamanioEstado = tamanioEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
