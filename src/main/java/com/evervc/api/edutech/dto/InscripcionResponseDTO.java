package com.evervc.api.edutech.dto;

import java.time.LocalDateTime;

public record InscripcionResponseDTO(
        Long id,
        LocalDateTime fecha,
        String estado, // El Enum convertido a texto

        // Datos relevantes del estudiante
        Long estudianteId,
        String nombreEstudiante,

        // Datos del Curso
        Long cursoId,
        String tituloCurso
) {}
