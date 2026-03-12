package com.evervc.api.edutech.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record CursoRequestDTO(
        @NotBlank(message = "El título del curso no puede estar vacío")
        String titulo,

        @NotNull(message = "El precio es obligatorio")
        @PositiveOrZero(message = "El precio no puede ser negativo (puede ser 0 si es gratis)")
        Double precio,

        @NotNull(message = "Debes indicar a qué categoría pertenece el curso")
        Long categoriaId, // Pide la referencia, no el objeto Categoria completo

        @NotEmpty(message = "El curso debe tener al menos un módulo inicial")
        @Valid // Para validar cada módulo de la lista de forma recursiva
        List<ModuloRequestDTO> modulos
) {}
