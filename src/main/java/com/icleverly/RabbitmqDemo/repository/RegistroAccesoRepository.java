package com.icleverly.RabbitmqDemo.repository;
import com.icleverly.RabbitmqDemo.model.RegistroAcceso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Integer> {
}