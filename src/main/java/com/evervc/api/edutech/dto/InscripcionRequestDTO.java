package com.evervc.api.edutech.dto;

import jakarta.validation.constraints.NotNull;

public record InscripcionRequestDTO(
        @NotNull(message = "El ID del estudiante es obligatorio")
        Long estudianteId,

        @NotNull(message = "El ID del curso es obligatorio")
        Long cursoId
) {}
