package com.example.chambeape.entidades;

import android.graphics.Bitmap;

public class Usuario {

    private String dniUsuario, nomUsuario,passUsuario,telUsuario,apepatUsuario,apematUsuario,mailUsuario,dirUsuario,fecUsuario,desUsuario,vidUsuario,fotUsuario,calUsuarioTra,calUsuarioEmp,fotValidacion,verTelefono,genUsuario;
    public Usuario(){

    }

    public Usuario(String dniUsuario, String nomUsuario, String passUsuario, String telUsuario, String apepatUsuario, String apematUsuario, String mailUsuario, String dirUsuario, String fecUsuario, String desUsuario, String vidUsuario, String fotUsuario, String calUsuarioTra, String calUsuarioEmp, String fotValidacion, String verTelefono, String genUsuario) {
        this.dniUsuario = dniUsuario;
        this.nomUsuario = nomUsuario;
        this.passUsuario = passUsuario;
        this.telUsuario = telUsuario;
        this.apepatUsuario = apepatUsuario;
        this.apematUsuario = apematUsuario;
        this.mailUsuario = mailUsuario;
        this.dirUsuario = dirUsuario;
        this.fecUsuario = fecUsuario;
        this.desUsuario = desUsuario;
        this.vidUsuario = vidUsuario;
        this.fotUsuario = fotUsuario;
        this.calUsuarioTra = calUsuarioTra;
        this.calUsuarioEmp = calUsuarioEmp;
        this.fotValidacion = fotValidacion;
        this.verTelefono = verTelefono;
        this.genUsuario = genUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
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

    public String getDesUsuario() {
        return desUsuario;
    }

    public void setDesUsuario(String desUsuario) {
        this.desUsuario = desUsuario;
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

    public String getCalUsuarioTra() {
        return calUsuarioTra;
    }

    public void setCalUsuarioTra(String calUsuarioTra) {
        this.calUsuarioTra = calUsuarioTra;
    }

    public String getCalUsuarioEmp() {
        return calUsuarioEmp;
    }

    public void setCalUsuarioEmp(String calUsuarioEmp) {
        this.calUsuarioEmp = calUsuarioEmp;
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

    public String getGenUsuario() {
        return genUsuario;
    }

    public void setGenUsuario(String genUsuario) {
        this.genUsuario = genUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dniUsuario='" + dniUsuario + '\'' +
                ", nomUsuario='" + nomUsuario + '\'' +
                ", passUsuario='" + passUsuario + '\'' +
                ", telUsuario='" + telUsuario + '\'' +
                ", apepatUsuario='" + apepatUsuario + '\'' +
                ", apematUsuario='" + apematUsuario + '\'' +
                ", mailUsuario='" + mailUsuario + '\'' +
                ", dirUsuario='" + dirUsuario + '\'' +
                ", fecUsuario='" + fecUsuario + '\'' +
                ", desUsuario='" + desUsuario + '\'' +
                ", vidUsuario='" + vidUsuario + '\'' +
                ", fotUsuario='" + fotUsuario + '\'' +
                ", calUsuarioTra='" + calUsuarioTra + '\'' +
                ", calUsuarioEmp='" + calUsuarioEmp + '\'' +
                ", fotValidacion='" + fotValidacion + '\'' +
                ", verTelefono='" + verTelefono + '\'' +
                ", genUsuario='" + genUsuario + '\'' +
                '}';
    }
}

