package com.chambeape.entidades;

public class Servicios {

    private Integer idServicio;
    private String nombServicio, tipoServicio;

    public Servicios (){
    }

    public Servicios(Integer idServicio, String nombServicio, String tipoServicio) {
        this.idServicio = idServicio;
        this.nombServicio = nombServicio;
        this.tipoServicio = tipoServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombServicio() {
        return nombServicio;
    }

    public void setNombServicio(String nombServicio) {
        this.nombServicio = nombServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return "Servicios{" +
                "idServicio=" + idServicio +
                ", nombServicio='" + nombServicio + '\'' +
                ", tipoServicio='" + tipoServicio + '\'' +
                '}';
    }
}
