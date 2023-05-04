package com.example.chambeape.entidades;

import android.graphics.Bitmap;

public class Usuario {

    private String idUsuario,nomUsuario,passUsuario,telUsuario,apepatUsuario,apematUsuario,mailUsuario,dirUsuario,fecUsuario,vidUsuario,fotUsuario,calUsuario,fotValidacion,verTelefono;


    public Usuario(){

    }

    public Usuario(String idUsuario, String nomUsuario, String passUsuario, String telUsuario, String apepatUsuario, String apematUsuario, String mailUsuario, String dirUsuario, String fecUsuario, String vidUsuario, String fotUsuario, String calUsuario, String fotValidacion, String verTelefono) {
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.passUsuario = passUsuario;
        this.telUsuario = telUsuario;
        this.apepatUsuario = apepatUsuario;
        this.apematUsuario = apematUsuario;
        this.mailUsuario = mailUsuario;
        this.dirUsuario = dirUsuario;
        this.fecUsuario = fecUsuario;
        this.vidUsuario = vidUsuario;
        this.fotUsuario = fotUsuario;
        this.calUsuario = calUsuario;
        this.fotValidacion = fotValidacion;
        this.verTelefono = verTelefono;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public String getTelUsuario() {
        return telUsuario;
    }

    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }

    public String getApepatUsuario() {
        return apepatUsuario;
    }

    public void setApepatUsuario(String apepatUsuario) {
        this.apepatUsuario = apepatUsuario;
    }

    public String getApematUsuario() {
        return apematUsuario;
    }

    public void setApematUsuario(String apematUsuario) {
        this.apematUsuario = apematUsuario;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
    }

    public String getDirUsuario() {
        return dirUsuario;
    }

    public void setDirUsuario(String dirUsuario) {
        this.dirUsuario = dirUsuario;
    }

    public String getFecUsuario() {
        return fecUsuario;
    }

    public void setFecUsuario(String fecUsuario) {
        this.fecUsuario = fecUsuario;
    }

    public String getVidUsuario() {
        return vidUsuario;
    }

    public void setVidUsuario(String vidUsuario) {
        this.vidUsuario = vidUsuario;
    }

    public String getFotUsuario() {
        return fotUsuario;
    }

    public void setFotUsuario(String fotUsuario) {
        this.fotUsuario = fotUsuario;
    }

    public String getCalUsuario() {
        return calUsuario;
    }

    public void setCalUsuario(String calUsuario) {
        this.calUsuario = calUsuario;
    }

    public String getFotValidacion() {
        return fotValidacion;
    }

    public void setFotValidacion(String fotValidacion) {
        this.fotValidacion = fotValidacion;
    }

    public String getVerTelefono() {
        return verTelefono;
    }

    public void setVerTelefono(String verTelefono) {
        this.verTelefono = verTelefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nomUsuario='" + nomUsuario + '\'' +
                ", passUsuario='" + passUsuario + '\'' +
                ", telUsuario='" + telUsuario + '\'' +
                ", apepatUsuario='" + apepatUsuario + '\'' +
                ", apematUsuario='" + apematUsuario + '\'' +
                ", mailUsuario='" + mailUsuario + '\'' +
                ", dirUsuario='" + dirUsuario + '\'' +
                ", fecUsuario='" + fecUsuario + '\'' +
                ", vidUsuario='" + vidUsuario + '\'' +
                ", fotUsuario='" + fotUsuario + '\'' +
                ", calUsuario='" + calUsuario + '\'' +
                ", fotValidacion='" + fotValidacion + '\'' +
                ", verTelefono='" + verTelefono + '\'' +
                '}';
    }
}
