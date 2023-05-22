package com.glcm.chambeape;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Anuncio {
    private String habilidad1;
    private String descrAnuncio;
    private String ubiAnuncio;

    public Anuncio() {
    }

    public Anuncio(String habilidad1, String descrAnuncio, String ubiAnuncio) {
        this.habilidad1 = habilidad1;
        this.descrAnuncio = descrAnuncio;
        this.ubiAnuncio = ubiAnuncio;
    }

    public String getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(String habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public String getDescrAnuncio() {
        return descrAnuncio;
    }

    public void setDescrAnuncio(String descrAnuncio) {
        this.descrAnuncio = descrAnuncio;
    }

    public String getUbiAnuncio() {
        return ubiAnuncio;
    }

    public void setUbiAnuncio(String ubiAnuncio) {
        this.ubiAnuncio = ubiAnuncio;
    }
}
