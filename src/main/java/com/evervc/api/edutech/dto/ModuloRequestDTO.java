package com.evervc.api.edutech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ModuloRequestDTO(
        @NotBlank(message = "El título del módulo es obligatorio")
        String titulo,

        @NotNull(message = "El orden del módulo es obligatorio")
        @Positive(message = "El orden debe ser un número positivo mayor a cero")
        Integer orden
) {}
