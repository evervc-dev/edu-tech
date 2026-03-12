package com.evervc.api.edutech.dto;

import java.util.List;

public record CursoResponseDTO(
        Long id,
        String titulo,
        Double precio,

        // Solo nombre de la Categoría
        String nombreCategoria,

        // Modulos que contiene el curso
        List<ModuloResponseDTO> modulos
) {}
