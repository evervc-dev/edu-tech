package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.EstudianteRequestDTO;
import com.evervc.api.edutech.dto.EstudianteResponseDTO;

public interface EstudianteService {
    EstudianteResponseDTO crearEstudiante(EstudianteRequestDTO requestDTO);
}
