package com.icleverly.RabbitmqDemo.controller;

import com.icleverly.RabbitmqDemo.dto.PersonaRequest;
import com.icleverly.RabbitmqDemo.model.Persona;
import com.icleverly.RabbitmqDemo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Integer id) {
        Persona persona = personaService.getPersonaById(id);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Persona createPersona(@RequestBody PersonaRequest personaRequest, @RequestParam Integer usuarioId) {
        return personaService.savePersona(personaRequest.getPersona(), personaRequest.getDirecciones(), usuarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Integer id, @RequestBody Persona personaDetails) {
        Persona updatedPersona = personaService.updatePersona(id, personaDetails);
        if (updatedPersona != null) {
            return ResponseEntity.ok(updatedPersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Integer id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}