package com.icleverly.RabbitmqDemo.dto;

import com.icleverly.RabbitmqDemo.model.Direccion;
import com.icleverly.RabbitmqDemo.model.Persona;

import java.util.List;

public class PersonaRequest {
    private Persona persona;
    private List<Direccion> direcciones;

    // Getters y Setters
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}

