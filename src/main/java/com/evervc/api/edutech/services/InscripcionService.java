package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.InscripcionRequestDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;

public interface InscripcionService {
    InscripcionResponseDTO inscribirEstudiante(InscripcionRequestDTO requestDTO);
}
