package com.chambeape.entidades;

public class DetalleAnuncio {

    private String idHabilidad, idUsuario, descrAnuncio, estadoAnuncio,
            ubiAnuncio, fecAnuncio, longitudAnuncio, latitudAnincio, idServicio,
            idRespoAnuncio, idFotoAnuncio, idAnuncio, puntEmpleAnuncio, puntTrabAnuncio;

    public DetalleAnuncio() {
    }

    public DetalleAnuncio(String idHabilidad, String idUsuario, String descrAnuncio, String estadoAnuncio,
                          String ubiAnuncio, String fecAnuncio, String longitudAnuncio, String latitudAnincio,
                          String idServicio, String idRespoAnuncio, String idFotoAnuncio, String idAnuncio,
                          String puntEmpleAnuncio, String puntTrabAnuncio, Boolean tipoAnuncio) {
        this.idHabilidad = idHabilidad;
        this.idUsuario = idUsuario;
        this.descrAnuncio = descrAnuncio;
        this.estadoAnuncio = estadoAnuncio;
        this.ubiAnuncio = ubiAnuncio;
        this.fecAnuncio = fecAnuncio;
        this.longitudAnuncio = longitudAnuncio;
        this.latitudAnincio = latitudAnincio;
        this.idServicio = idServicio;
        this.idRespoAnuncio = idRespoAnuncio;
        this.idFotoAnuncio = idFotoAnuncio;
        this.idAnuncio = idAnuncio;
        this.puntEmpleAnuncio = puntEmpleAnuncio;
        this.puntTrabAnuncio = puntTrabAnuncio;
    }

    public String getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(String idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescrAnuncio() {
        return descrAnuncio;
    }

    public void setDescrAnuncio(String descrAnuncio) {
        this.descrAnuncio = descrAnuncio;
    }

    public String getEstadoAnuncio() {
        return estadoAnuncio;
    }

    public void setEstadoAnuncio(String estadoAnuncio) {
        this.estadoAnuncio = estadoAnuncio;
    }

    public String getUbiAnuncio() {
        return ubiAnuncio;
    }

    public void setUbiAnuncio(String ubiAnuncio) {
        this.ubiAnuncio = ubiAnuncio;
    }

    public String getFecAnuncio() {
        return fecAnuncio;
    }

    public void setFecAnuncio(String fecAnuncio) {
        this.fecAnuncio = fecAnuncio;
    }

    public String getLongitudAnuncio() {
        return longitudAnuncio;
    }

    public void setLongitudAnuncio(String longitudAnuncio) {
        this.longitudAnuncio = longitudAnuncio;
    }

    public String getLatitudAnincio() {
        return latitudAnincio;
    }

    public void setLatitudAnincio(String latitudAnincio) {
        this.latitudAnincio = latitudAnincio;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getIdRespoAnuncio() {
        return idRespoAnuncio;
    }

    public void setIdRespoAnuncio(String idRespoAnuncio) {
        this.idRespoAnuncio = idRespoAnuncio;
    }

    public String getIdFotoAnuncio() {
        return idFotoAnuncio;
    }

    public void setIdFotoAnuncio(String idFotoAnuncio) {
        this.idFotoAnuncio = idFotoAnuncio;
    }

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
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

    @Override
    public String toString() {
        return "DetalleAnuncio{" +
                "idHabilidad='" + idHabilidad + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", descrAnuncio='" + descrAnuncio + '\'' +
                ", estadoAnuncio='" + estadoAnuncio + '\'' +
                ", ubiAnuncio='" + ubiAnuncio + '\'' +
                ", fecAnuncio='" + fecAnuncio + '\'' +
                ", longitudAnuncio='" + longitudAnuncio + '\'' +
                ", latitudAnincio='" + latitudAnincio + '\'' +
                ", idServicio='" + idServicio + '\'' +
                ", idRespoAnuncio='" + idRespoAnuncio + '\'' +
                ", idFotoAnuncio='" + idFotoAnuncio + '\'' +
                ", idAnuncio='" + idAnuncio + '\'' +
                ", puntEmpleAnuncio='" + puntEmpleAnuncio + '\'' +
                ", puntTrabAnuncio='" + puntTrabAnuncio + '\'' +
                '}';
    }
}
