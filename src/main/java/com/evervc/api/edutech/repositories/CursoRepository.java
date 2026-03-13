package com.evervc.api.edutech.repositories;

import com.evervc.api.edutech.entities.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Para el catálogo público paginado
    Page<Curso> findByActivoTrue(Pageable pageable);

    // Para buscar un curso específico que esté activo
    Optional<Curso> findByIdAndActivoTrue(Long id);

    // Para validar antes de eliminar
    boolean existsByIdAndActivoTrue(Long id);
}
