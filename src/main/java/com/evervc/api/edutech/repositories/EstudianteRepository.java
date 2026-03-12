package com.evervc.api.edutech.repositories;

import com.evervc.api.edutech.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Verifica si existe segun email
    boolean existsByEmail(String email);
}
