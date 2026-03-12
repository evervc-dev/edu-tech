package com.evervc.api.edutech.repositories;

import com.evervc.api.edutech.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Para validar que no esté inscrito previamente en el curso
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
