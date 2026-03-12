package com.evervc.api.edutech.mappers;

import com.evervc.api.edutech.dto.EstudianteRequestDTO;
import com.evervc.api.edutech.dto.EstudianteResponseDTO;
import com.evervc.api.edutech.entities.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inscripciones", ignore = true) // Se inicializa vacía
    Estudiante toEntity(EstudianteRequestDTO dto);

    EstudianteResponseDTO toResponseDTO(Estudiante entidad);
}
