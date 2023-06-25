package com.example.chambeape.entidades;

public class Anuncio {
    private String idAnuncio, habilidad1Anuncio, habilidad2Anuncio, habilidad3Anuncio, idPublicadorAnuncio, tituloAnuncio, estadoAnuncio,
            direccionAnuncio, fecchaPublicacionAnuncio, longitudAnuncio, latitudAnuncio, tipoAnuncio,
            idResponsableAnuncio, idFotoAnuncio, idVideoAnuncio, puntEmpleAnuncio, puntTrabAnuncio,comTraAnuncio,comEmpAnuncio;

    public Anuncio() {
    }

    public Anuncio(String idAnuncio, String habilidad1Anuncio, String habilidad2Anuncio, String habilidad3Anuncio, String idPublicadorAnuncio, String tituloAnuncio, String estadoAnuncio, String direccionAnuncio, String fecchaPublicacionAnuncio, String longitudAnuncio, String latitudAnuncio, String tipoAnuncio, String idResponsableAnuncio, String idFotoAnuncio, String idVideoAnuncio, String puntEmpleAnuncio, String puntTrabAnuncio, String comTraAnuncio, String comEmpAnuncio) {
        this.idAnuncio = idAnuncio;
        this.habilidad1Anuncio = habilidad1Anuncio;
        this.habilidad2Anuncio = habilidad2Anuncio;
        this.habilidad3Anuncio = habilidad3Anuncio;
        this.idPublicadorAnuncio = idPublicadorAnuncio;
        this.tituloAnuncio = tituloAnuncio;
        this.estadoAnuncio = estadoAnuncio;
        this.direccionAnuncio = direccionAnuncio;
        this.fecchaPublicacionAnuncio = fecchaPublicacionAnuncio;
        this.longitudAnuncio = longitudAnuncio;
        this.latitudAnuncio = latitudAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.idResponsableAnuncio = idResponsableAnuncio;
        this.idFotoAnuncio = idFotoAnuncio;
        this.idVideoAnuncio = idVideoAnuncio;
        this.puntEmpleAnuncio = puntEmpleAnuncio;
        this.puntTrabAnuncio = puntTrabAnuncio;
        this.comTraAnuncio = comTraAnuncio;
        this.comEmpAnuncio = comEmpAnuncio;
    }

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getHabilidad1Anuncio() {
        return habilidad1Anuncio;
    }

    public void setHabilidad1Anuncio(String habilidad1Anuncio) {
        this.habilidad1Anuncio = habilidad1Anuncio;
    }

    public String getHabilidad2Anuncio() {
        return habilidad2Anuncio;
    }

    public void setHabilidad2Anuncio(String habilidad2Anuncio) {
        this.habilidad2Anuncio = habilidad2Anuncio;
    }

    public String getHabilidad3Anuncio() {
        return habilidad3Anuncio;
    }

    public void setHabilidad3Anuncio(String habilidad3Anuncio) {
        this.habilidad3Anuncio = habilidad3Anuncio;
    }

    public String getIdPublicadorAnuncio() {
        return idPublicadorAnuncio;
    }

    public void setIdPublicadorAnuncio(String idPublicadorAnuncio) {
        this.idPublicadorAnuncio = idPublicadorAnuncio;
    }

    public String getTituloAnuncio() {
        return tituloAnuncio;
    }

    public void setTituloAnuncio(String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    public String getEstadoAnuncio() {
        return estadoAnuncio;
    }

    public void setEstadoAnuncio(String estadoAnuncio) {
        this.estadoAnuncio = estadoAnuncio;
    }

    public String getDireccionAnuncio() {
        return direccionAnuncio;
    }

    public void setDireccionAnuncio(String direccionAnuncio) {
        this.direccionAnuncio = direccionAnuncio;
    }

    public String getFecchaPublicacionAnuncio() {
        return fecchaPublicacionAnuncio;
    }

    public void setFecchaPublicacionAnuncio(String fecchaPublicacionAnuncio) {
        this.fecchaPublicacionAnuncio = fecchaPublicacionAnuncio;
    }

    public String getLongitudAnuncio() {
        return longitudAnuncio;
    }

    public void setLongitudAnuncio(String longitudAnuncio) {
        this.longitudAnuncio = longitudAnuncio;
    }

    public String getLatitudAnuncio() {
        return latitudAnuncio;
    }

    public void setLatitudAnuncio(String latitudAnuncio) {
        this.latitudAnuncio = latitudAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getIdResponsableAnuncio() {
        return idResponsableAnuncio;
    }

    public void setIdResponsableAnuncio(String idResponsableAnuncio) {
        this.idResponsableAnuncio = idResponsableAnuncio;
    }

    public String getIdFotoAnuncio() {
        return idFotoAnuncio;
    }

    public void setIdFotoAnuncio(String idFotoAnuncio) {
        this.idFotoAnuncio = idFotoAnuncio;
    }

    public String getIdVideoAnuncio() {
        return idVideoAnuncio;
    }

    public void setIdVideoAnuncio(String idVideoAnuncio) {
        this.idVideoAnuncio = idVideoAnuncio;
    }

    public String getPuntEmpleAnuncio() {
        return puntEmpleAnuncio;
    }

    public void setPuntEmpleAnuncio(String puntEmpleAnuncio) {
        this.puntEmpleAnuncio = puntEmpleAnuncio;
    }

    public String getPuntTrabAnuncio() {
        return puntTrabAnuncio;
    }

    public void setPuntTrabAnuncio(String puntTrabAnuncio) {
        this.puntTrabAnuncio = puntTrabAnuncio;
    }

    public String getComTraAnuncio() {
        return comTraAnuncio;
    }

    public void setComTraAnuncio(String comTraAnuncio) {
        this.comTraAnuncio = comTraAnuncio;
    }

    public String getComEmpAnuncio() {
        return comEmpAnuncio;
    }

    public void setComEmpAnuncio(String comEmpAnuncio) {
        this.comEmpAnuncio = comEmpAnuncio;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "idAnuncio='" + idAnuncio + '\'' +
                ", habilidad1Anuncio='" + habilidad1Anuncio + '\'' +
                ", habilidad2Anuncio='" + habilidad2Anuncio + '\'' +
                ", habilidad3Anuncio='" + habilidad3Anuncio + '\'' +
                ", idPublicadorAnuncio='" + idPublicadorAnuncio + '\'' +
                ", tituloAnuncio='" + tituloAnuncio + '\'' +
                ", estadoAnuncio='" + estadoAnuncio + '\'' +
                ", direccionAnuncio='" + direccionAnuncio + '\'' +
                ", fecchaPublicacionAnuncio='" + fecchaPublicacionAnuncio + '\'' +
                ", longitudAnuncio='" + longitudAnuncio + '\'' +
                ", latitudAnuncio='" + latitudAnuncio + '\'' +
                ", tipoAnuncio='" + tipoAnuncio + '\'' +
                ", idResponsableAnuncio='" + idResponsableAnuncio + '\'' +
                ", idFotoAnuncio='" + idFotoAnuncio + '\'' +
                ", idVideoAnuncio='" + idVideoAnuncio + '\'' +
                ", puntEmpleAnuncio='" + puntEmpleAnuncio + '\'' +
                ", puntTrabAnuncio='" + puntTrabAnuncio + '\'' +
                ", comTraAnuncio='" + comTraAnuncio + '\'' +
                ", comEmpAnuncio='" + comEmpAnuncio + '\'' +
                '}';
    }
}
