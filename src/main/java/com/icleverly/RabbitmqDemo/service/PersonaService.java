package com.icleverly.RabbitmqDemo.service;
import com.icleverly.RabbitmqDemo.dto.PersonaDTO;
import com.icleverly.RabbitmqDemo.model.Direccion;
import com.icleverly.RabbitmqDemo.model.Persona;
import com.icleverly.RabbitmqDemo.model.RegistroAcceso;
import com.icleverly.RabbitmqDemo.repository.DireccionRepository;
import com.icleverly.RabbitmqDemo.repository.PersonaRepository;
import com.icleverly.RabbitmqDemo.repository.RegistroAccesoRepository;
import com.icleverly.RabbitmqDemo.repository.UsuarioRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RegistroAccesoRepository registroAccesoRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public Persona savePersona(Persona persona, List<Direccion> direcciones, Integer usuarioId) {
        Persona savedPersona = personaRepository.save(persona);
        Integer personaId = savedPersona.getId();



        // Guardar las direcciones asociadas a la persona
        if (direcciones != null) {
            for (Direccion direccion : direcciones) {
                direccion.setPersonaId(personaId);
                direccionRepository.save(direccion);
            }
        }

        // Crear hilos para manejar el registro de acceso y el envío a RabbitMQ
        executorService.submit(() -> registerAccessAndSendToRabbitMQ(usuarioId, personaId, savedPersona.getNombre()));

        return savedPersona;
    }

    private void registerAccessAndSendToRabbitMQ(Integer usuarioId, Integer personaId, String nombre) {
        RegistroAcceso registroAcceso = new RegistroAcceso();
        registroAcceso.setUsuarioId(usuarioId);
        registroAcceso.setPersonaId(personaId);
        registroAcceso.setFechaAcceso(LocalDateTime.now());
        registroAccesoRepository.save(registroAcceso);

        // Enviar la misma información a RabbitMQ
        sendToRabbitMQ(personaId, nombre);
    }

    private void sendToRabbitMQ(Integer personaId, String nombre) {
        PersonaDTO personaDTO = new PersonaDTO(personaId, nombre);
        rabbitTemplate.convertAndSend("registro_acceso_queue", personaDTO);
    }

    public Persona getPersonaById(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona updatePersona(Integer id, Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
            persona.setGenero(personaDetails.getGenero());
            persona.setEmail(personaDetails.getEmail());
            persona.setTelefono(personaDetails.getTelefono());
            persona.setOcupacion(personaDetails.getOcupacion());
            persona.setEstadoCivil(personaDetails.getEstadoCivil());
            persona.setNacionalidad(personaDetails.getNacionalidad());
            persona.setFechaRegistro(personaDetails.getFechaRegistro());
            return personaRepository.save(persona);
        } else {
            return null;
        }
    }

    public void deletePersona(Integer id) {
        personaRepository.deleteById(id);
    }
}