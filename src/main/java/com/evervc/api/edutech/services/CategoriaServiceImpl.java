package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CategoriaRequestDTO;
import com.evervc.api.edutech.dto.CategoriaResponseDTO;
import com.evervc.api.edutech.entities.Categoria;
import com.evervc.api.edutech.mappers.CategoriaMapper;
import com.evervc.api.edutech.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    @Transactional
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO requestDTO) {
        // Convierte el Request a Entidad
        Categoria nuevaCategoria = categoriaMapper.toEntity(requestDTO);

        // Guarda en la base de datos
        Categoria categoriaGuardada = categoriaRepository.save(nuevaCategoria);

        // Response DTO para mostrar al cliente
        return categoriaMapper.toResponseDTO(categoriaGuardada);
    }
}
