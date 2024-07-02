package com.icleverly.RabbitmqDemo.repository;

import com.icleverly.RabbitmqDemo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
