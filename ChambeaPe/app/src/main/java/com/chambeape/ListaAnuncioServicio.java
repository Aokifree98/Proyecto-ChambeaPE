package com.chambeape;

public class ListaAnuncioServicio {
    public String servicio;
    public String descripcion;

    public ListaAnuncioServicio(String servicio, String descripcion) {
        this.servicio = servicio;
        this.descripcion = descripcion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
