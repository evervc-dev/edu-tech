package com.evervc.api.edutech.dto;

import java.util.List;

public record EstudianteDashboardResponseDTO(
        Long estudianteId,
        String nombreEstudiante,
        String email,
        List<CursoInscritoDTO> cursosActivos
) {}
