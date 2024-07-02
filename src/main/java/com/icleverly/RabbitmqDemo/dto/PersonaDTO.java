package com.icleverly.RabbitmqDemo.dto;
public class PersonaDTO {
    private Integer personaId;
    private String nombre;

    public PersonaDTO(Integer personaId, String nombre) {
        this.personaId = personaId;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}