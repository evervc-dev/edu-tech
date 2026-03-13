package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.EstudianteDashboardResponseDTO;
import com.evervc.api.edutech.dto.InscripcionRequestDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;

import java.util.List;

public interface InscripcionService {
    InscripcionResponseDTO inscribirEstudiante(InscripcionRequestDTO requestDTO);
    EstudianteDashboardResponseDTO cursosInscritosPorIdEstudiante(Long estudianteId);
}
