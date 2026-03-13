package com.evervc.api.edutech.dto;

import java.time.LocalDateTime;

// Resumen del curso sin módulos, pero con info de la inscripción
public record CursoInscritoDTO(
        Long inscripcionId,
        LocalDateTime fechaInscripcion,
        String estado,
        Long cursoId,
        String tituloCurso
) {}
