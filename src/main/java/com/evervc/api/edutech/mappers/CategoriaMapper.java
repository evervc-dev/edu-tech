package com.evervc.api.edutech.mappers;

import com.evervc.api.edutech.dto.CategoriaRequestDTO;
import com.evervc.api.edutech.dto.CategoriaResponseDTO;
import com.evervc.api.edutech.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    @Mapping(target = "id", ignore = true) // La BD genera el ID
    Categoria toEntity(CategoriaRequestDTO dto);

    CategoriaResponseDTO toResponseDTO(Categoria entidad);
}