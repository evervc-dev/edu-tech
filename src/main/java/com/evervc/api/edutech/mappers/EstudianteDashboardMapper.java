package com.evervc.api.edutech.mappers;

import com.evervc.api.edutech.dto.CursoInscritoDTO;
import com.evervc.api.edutech.dto.EstudianteDashboardResponseDTO;
import com.evervc.api.edutech.entities.Estudiante;
import com.evervc.api.edutech.entities.Inscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudianteDashboardMapper {

    // Mapeamos de Estudiante a Dashboard
    @Mapping(source = "id", target = "estudianteId")
    @Mapping(source = "nombre", target = "nombreEstudiante")
    @Mapping(source = "inscripciones", target = "cursosActivos") // MapStruct iterará esta lista automáticamente
    EstudianteDashboardResponseDTO toDashboardDTO(Estudiante estudiante);

    // Indica a MapStruct cómo convertir UNA Inscripcion en UN CursoInscritoDTO
    @Mapping(source = "id", target = "inscripcionId")
    @Mapping(source = "fecha", target = "fechaInscripcion")
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "curso.titulo", target = "tituloCurso")
    CursoInscritoDTO toCursoInscritoDTO(Inscripcion inscripcion);
}
