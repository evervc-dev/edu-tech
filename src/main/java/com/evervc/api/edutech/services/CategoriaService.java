package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CategoriaRequestDTO;
import com.evervc.api.edutech.dto.CategoriaResponseDTO;

public interface CategoriaService {
    CategoriaResponseDTO crearCategoria(CategoriaRequestDTO requestDTO);
}
