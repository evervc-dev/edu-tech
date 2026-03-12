package com.evervc.api.edutech.mappers;

import com.evervc.api.edutech.dto.InscripcionRequestDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;
import com.evervc.api.edutech.entities.Inscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    // --- DE ENTIDAD A DTO (SALIDA) ---
    // Datos del Estudiante
    @Mapping(source = "estudiante.id", target = "estudianteId")
    @Mapping(source = "estudiante.nombre", target = "nombreEstudiante")
    // Datos del Curso
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "curso.titulo", target = "tituloCurso")
    InscripcionResponseDTO toResponseDTO(Inscripcion inscripcion);

    // --- DE DTO A ENTIDAD (ENTRADA) ---
    // El RequestDTO solo trae estudianteId y cursoId.
    // Ignora todo esto para que MapStruct no genere advertencias de compilación.
    // En el Service se hará lo de buscar el Estudiante y el Curso reales y asignarlos
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Inscripcion toEntity(InscripcionRequestDTO requestDTO);
}
