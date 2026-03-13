package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.InscripcionRequestDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;
import com.evervc.api.edutech.entities.Curso;
import com.evervc.api.edutech.entities.Estudiante;
import com.evervc.api.edutech.entities.Inscripcion;
import com.evervc.api.edutech.enums.Estado;
import com.evervc.api.edutech.exceptions.RecursoNoEncontradoException;
import com.evervc.api.edutech.exceptions.ReglaNegocioException;
import com.evervc.api.edutech.mappers.InscripcionMapper;
import com.evervc.api.edutech.repositories.CursoRepository;
import com.evervc.api.edutech.repositories.EstudianteRepository;
import com.evervc.api.edutech.repositories.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final InscripcionMapper inscripcionMapper;

    @Override
    @Transactional
    public InscripcionResponseDTO inscribirEstudiante(InscripcionRequestDTO requestDTO) {
        Long estudianteId = requestDTO.estudianteId();
        Long cursoId = requestDTO.cursoId();

        // Evitar inscripciones duplicadas
        if (inscripcionRepository.existsByEstudianteIdAndCursoId(estudianteId, cursoId)) {
            throw new ReglaNegocioException("El estudiante ya se encuentra inscrito en este curso.");
        }

        // Verifica la existencia del Estudiante
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el estudiante con ID: " + estudianteId));

        // Verifica la existencia del Curso
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el curso con ID: " + cursoId));

        // Mapeo base (MapStruct ignoró estudiante, curso, fecha y estado)
        Inscripcion nuevaInscripcion = inscripcionMapper.toEntity(requestDTO);

        // Ensambla la entidad con los datos reales y del sistema
        nuevaInscripcion.setEstudiante(estudiante);
        nuevaInscripcion.setCurso(curso);
        nuevaInscripcion.setFecha(LocalDateTime.now()); // El backend controla la fecha
        nuevaInscripcion.setEstado(Estado.ACTIVO); // El backend controla el estado inicial

        // Guarda en la base de datos
        Inscripcion inscripcionGuardada = inscripcionRepository.save(nuevaInscripcion);

        // Retorna el ResponseDTO (MapStruct aplana los nombres del estudiante y curso aquí)
        return inscripcionMapper.toResponseDTO(inscripcionGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> cursosInscritosPorIdEstudiante(Long estudianteId) {
        // Verifica la existencia del Estudiante
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el estudiante con ID: " + estudianteId));

        // Verifica si tiene al menos una inscripción
        if (!inscripcionRepository.existsByEstudianteId(estudianteId)) {
            throw new ReglaNegocioException("El estudiante no tiene ningún curso inscrito.");
        }

        // Obtiene los cursos en los que se encuentra inscrito el estudiante
        List<Inscripcion> inscripciones = inscripcionRepository.findAllByEstudianteId(estudianteId);
        List<InscripcionResponseDTO> responseDTOS = new ArrayList<>();
        // Mapeo a respuesta dto
        for (Inscripcion inscripcion : inscripciones) {
            responseDTOS.add(inscripcionMapper.toResponseDTO(inscripcion));
        }

        return responseDTOS;
    }
}
