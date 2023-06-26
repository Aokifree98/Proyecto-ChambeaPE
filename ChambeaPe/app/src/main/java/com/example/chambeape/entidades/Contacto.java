package com.example.chambeape.entidades;

public class Contacto {

    private String idSeguidor,idSeguido;

    public Contacto(){

    }

    public Contacto(String idSeguidor, String idSeguido) {
        this.idSeguidor = idSeguidor;
        this.idSeguido = idSeguido;
    }

    public String getIdSeguidor() {
        return idSeguidor;
    }

    public void setIdSeguidor(String idSeguidor) {
        this.idSeguidor = idSeguidor;
    }

    public String getIdSeguido() {
        return idSeguido;
    }

    public void setIdSeguido(String idSeguido) {
        this.idSeguido = idSeguido;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "idSeguidor='" + idSeguidor + '\'' +
                ", idSeguido='" + idSeguido + '\'' +
                '}';
    }
}

