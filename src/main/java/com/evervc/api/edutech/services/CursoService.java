package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;
import org.springframework.data.domain.Page;

public interface CursoService {
    CursoResponseDTO crearCurso(CursoRequestDTO requestDTO);
    Page<CursoResponseDTO> obtenerTodosLosCursos(int page, int size); // Recibe el número de página y la cantidad de elementos por página
    CursoResponseDTO obtenerPorId(Long id);
    void eliminarCurso(Long id);
}
