package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;

public interface CursoService {
    CursoResponseDTO crearCurso(CursoRequestDTO requestDTO);
}
