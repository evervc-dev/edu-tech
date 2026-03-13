package com.evervc.api.edutech.repositories;

import com.evervc.api.edutech.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Para validar que no esté inscrito previamente en el curso
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
    // Valida si al menos esta inscrito en un curso (devuelve true en el caso que sí)
    boolean existsByEstudianteId(Long estudianteId);

    @Query("select i from Inscripcion i where i.estudiante.id=?1")
    List<Inscripcion> findAllByEstudianteId(Long estudianteId);
}
