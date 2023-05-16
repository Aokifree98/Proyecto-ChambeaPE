package com.chambeape.entidades;

public class Habilidades {
    private Integer idHabilidad;
    private String nomHabilidad;

    public Habilidades(Integer idHabilidad, String nomHabilidad) {
        this.idHabilidad = idHabilidad;
        this.nomHabilidad = nomHabilidad;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getNomHabilidad() {
        return nomHabilidad;
    }

    public void setNomHabilidad(String nomHabilidad) {
        this.nomHabilidad = nomHabilidad;
    }

    @Override
    public String toString() {
        return "Habilidades{" +
                "idHabilidad=" + idHabilidad +
                ", nomHabilidad='" + nomHabilidad + '\'' +
                '}';
    }
}
