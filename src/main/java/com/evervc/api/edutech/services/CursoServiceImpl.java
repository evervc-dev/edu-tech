package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;
import com.evervc.api.edutech.entities.Categoria;
import com.evervc.api.edutech.entities.Curso;
import com.evervc.api.edutech.exceptions.RecursoNoEncontradoException;
import com.evervc.api.edutech.mappers.CursoMapper;
import com.evervc.api.edutech.repositories.CategoriaRepository;
import com.evervc.api.edutech.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CategoriaRepository categoriaRepository; // para validar que la categoria exista
    private final CursoMapper cursoMapper;

    @Override
    @Transactional
    public CursoResponseDTO crearCurso(CursoRequestDTO requestDTO) {
        // Busca la Categoría en la BD IMPORTANTE: (hay que actualizar con el NotFound)
        Categoria categoria = categoriaRepository.findById(requestDTO.categoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la categoría con ID: " + requestDTO.categoriaId()));

        // Mapeo DTO a Entidad
        // Aquí MapStruct ejecuta el @AfterMapping y enlaza los módulos a este curso de una vez
        Curso nuevoCurso = cursoMapper.toEntity(requestDTO);

        // Se le asigna la categoría real que se buscó a la BD
        nuevoCurso.setCategoria(categoria);

        // Se guarda en la base de datos.
        // Se hacen los INSERT en cascada de cada Módulo en su respectiva tabla
        Curso cursoGuardado = cursoRepository.save(nuevoCurso);

        // Retorna el mensaje al cliente
        return cursoMapper.toResponseDTO(cursoGuardado);
    }
}
