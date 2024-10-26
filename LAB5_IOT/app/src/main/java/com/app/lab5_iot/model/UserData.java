package com.app.lab5_iot.model;

import java.io.Serializable;

public class UserData implements Serializable {

    private String nombre;
    private Float peso;
    private Float altura;
    private Float edad;
    private String intensidad;
    private String objetivo;
    private String genero;
    private Float TBM;


    public UserData(String nombre, Float peso, Float altura, Float edad, String intensidad, String objetivo, String genero, Float TBM) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
        this.intensidad = intensidad;
        this.objetivo = objetivo;
        this.genero = genero;
        this.TBM = TBM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getEdad() {
        return edad;
    }

    public void setEdad(Float edad) {
        this.edad = edad;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Float getTBM() {
        return TBM;
    }

    public void setTBM(Float TBM) {
        this.TBM = TBM;
    }
}