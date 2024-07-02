package com.icleverly.RabbitmqDemo.repository;

import com.icleverly.RabbitmqDemo.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}
