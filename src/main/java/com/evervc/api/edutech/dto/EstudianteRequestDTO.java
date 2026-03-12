package com.evervc.api.edutech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EstudianteRequestDTO(
        @NotBlank(message = "El nombre del estudiante es obligatorio")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del correo electrónico no es válido")
        String email
) {}
