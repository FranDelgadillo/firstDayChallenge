package com.icleverly.RabbitmqDemo.listener;

import com.icleverly.RabbitmqDemo.dto.PersonaDTO;
import com.icleverly.RabbitmqDemo.model.RegistroAcceso;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "registro_acceso_queue")
    public void listen(PersonaDTO personaDTO) {
        System.out.println("Mensaje recibido de RabbitMQ: ID = " + personaDTO.getPersonaId() + ", Nombre = " + personaDTO.getNombre());
        // Aquí puedes manejar la lógica para los mensajes recibidos
    }
}