package com.icleverly.RabbitmqDemo.repository;

import com.icleverly.RabbitmqDemo.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
