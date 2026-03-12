package com.evervc.api.edutech.mappers;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;
import com.evervc.api.edutech.entities.Curso;
import com.evervc.api.edutech.entities.Modulo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    // --- DE ENTIDAD A DTO (SALIDA) ---
    // Indica de dónde sacar el dato aplanado (porque los atributos son diferentes del DTO a la Clase)
    @Mapping(source = "categoria.nombre", target = "nombreCategoria")
    CursoResponseDTO toResponseDTO(Curso curso);

    // --- DE DTO A ENTIDAD (ENTRADA) ---
    // Id no existe (se ignora), y la categoría porque el Service la buscará en la BD
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Curso toEntity(CursoRequestDTO requestDTO);

    // Enlaza el padre con los hijos (antes de guardarlos en la db)
    @AfterMapping
    default void enlazarModulos(@MappingTarget Curso curso) {
        if (curso.getModulos() != null) {
            for (Modulo modulo : curso.getModulos()) {
                // A cada módulo le inyecta su curso padre (sin necesidad ModuloMapper porque los campos coinciden
                // tanto en Modulo (la lista de Entidades que tiene curso) como en ModuloRequestDTO (DTO)
                modulo.setCurso(curso);
            }
        }
    }
}
