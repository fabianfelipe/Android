package com.example.house.myproyect;

import java.io.Serializable;

/**
 * Created by house on 30-09-2017.
 */

public class Cliente implements Serializable{
    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
