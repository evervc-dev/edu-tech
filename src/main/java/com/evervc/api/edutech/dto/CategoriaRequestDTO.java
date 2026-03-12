package com.evervc.api.edutech.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        String nombre,

        @NotBlank(message = "La descripción de la categoría no puede estar vacía")
        String descripcion
) {}
