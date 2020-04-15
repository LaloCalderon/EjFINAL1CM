package com.example.ejercicio1cm.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String name;
    private String apat;
    private String amat;

    public Usuario(String name, String apat, String amat) {
        this.name = name;
        this.apat = apat;
        this.amat = amat;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getApat() {
        return apat;
    }
    public void setApat(String apat) { this.apat = apat; }

    public String getAmat() {
        return amat;
    }
    public void setAmat(String amat) {
        this.amat = amat;
    }
}
