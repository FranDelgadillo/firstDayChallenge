package com.icleverly.RabbitmqDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class RegistroAcceso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer usuarioId;
    private Integer personaId;
    private LocalDateTime fechaAcceso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public LocalDateTime getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(LocalDateTime fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }
}