package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;
import com.evervc.api.edutech.entities.Categoria;
import com.evervc.api.edutech.entities.Curso;
import com.evervc.api.edutech.entities.Modulo;
import com.evervc.api.edutech.exceptions.RecursoNoEncontradoException;
import com.evervc.api.edutech.exceptions.ReglaNegocioException;
import com.evervc.api.edutech.mappers.CursoMapper;
import com.evervc.api.edutech.repositories.CategoriaRepository;
import com.evervc.api.edutech.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public CursoResponseDTO obtenerPorId(Long id) {
        // Busca el objeto
        Curso curso = cursoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el curso con ID: " + id));

        // Se convierte a DTO
        return cursoMapper.toResponseDTO(curso);
    }

    @Override
    @Transactional(readOnly = true) // Consulta de solo lectura
    public Page<CursoResponseDTO> obtenerTodosLosCursos(int page, int size) {

        // Crea el objeto Pageable de Spring. La primera página es la número 0.
        Pageable pageable = PageRequest.of(page, size);

        // Ejecuta la búsqueda. JPA automáticamente hace el COUNT total y el LIMIT/OFFSET.
        Page<Curso> cursosPage = cursoRepository.findByActivoTrue(pageable);

        // E objeto Page tiene un método .map() integrado
        // recorre internamente cada Curso y lo pasa por MapStruct para convertirlo a DTO.
        return cursosPage.map(cursoMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public CursoResponseDTO actualizarCurso(Long id, CursoRequestDTO requestDTO) {
        // Busca el curso existente (solo los activos)
        Curso cursoExistente = cursoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el curso con ID: " + id));

        // Busca la categoría por si se ha cambiado
        Categoria categoria = categoriaRepository.findById(requestDTO.categoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la categoría con ID: " + requestDTO.categoriaId()));

        // Actualiza los campos escalares y la categoría
        cursoExistente.setTitulo(requestDTO.titulo());
        cursoExistente.setPrecio(requestDTO.precio());
        cursoExistente.setCategoria(categoria);

        // (Orphan Removal)
        // Limpia la lista actual. Hibernate detectará esto y borrará físicamente
        // de la tabla 'modulos' aquellos que ya no vengan en esta nueva petición.
        cursoExistente.getModulos().clear();

        // Mapea los módulos entrantes y restablece la bidireccionalidad
        List<Modulo> nuevosModulos = requestDTO.modulos().stream()
                .map(dto -> {
                    Modulo modulo = new Modulo();
                    modulo.setTitulo(dto.titulo());
                    modulo.setOrden(dto.orden());
                    modulo.setCurso(cursoExistente); // Indica quién es su padre
                    return modulo;
                }).toList();

        // Agrega los nuevos módulos a la lista vaciada
        cursoExistente.getModulos().addAll(nuevosModulos);

        // Guarda todos los cambios
        Curso cursoActualizado = cursoRepository.save(cursoExistente);

        return cursoMapper.toResponseDTO(cursoActualizado);
    }

    @Override
    @Transactional
    public CursoResponseDTO reactivarCurso(Long id) {
        // Verifica que el curso exista (gracias a @SQLRestriction
        // si ya fue "borrado" lógicamente, esto lanzará el 404 Not Found automáticamente)
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el curso con ID: " + id));

        if (curso.getActivo()) return cursoMapper.toResponseDTO(curso);

        curso.setActivo(true);

        // Guarda todos los cambios
        Curso cursoActualizado = cursoRepository.save(curso);

        return cursoMapper.toResponseDTO(cursoActualizado);
    }

    @Override
    @Transactional
    public void eliminarCurso(Long id) {
        // Verifica que el curso exista (gracias a @SQLRestriction
        // si ya fue "borrado" lógicamente, esto lanzará el 404 Not Found automáticamente)
        Curso curso = cursoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el curso con ID: " + id));

        curso.setActivo(false);

        // Actualiza con el nuevo estado
        cursoRepository.save(curso);
    }
}
