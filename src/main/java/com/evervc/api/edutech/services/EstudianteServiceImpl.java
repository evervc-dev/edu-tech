package com.evervc.api.edutech.services;

import com.evervc.api.edutech.dto.EstudianteRequestDTO;
import com.evervc.api.edutech.dto.EstudianteResponseDTO;
import com.evervc.api.edutech.entities.Estudiante;
import com.evervc.api.edutech.exceptions.ReglaNegocioException;
import com.evervc.api.edutech.mappers.EstudianteMapper;
import com.evervc.api.edutech.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    @Override
    @Transactional
    public EstudianteResponseDTO crearEstudiante(EstudianteRequestDTO requestDTO) {
        // Valida que el email no exista previamente
        if (estudianteRepository.existsByEmail(requestDTO.email())) {
            // Lanza una excepción indicando de que ya está registrado en la db
            throw new ReglaNegocioException("Ya existe un estudiante registrado con el email: " + requestDTO.email());
        }

        // Si va bien, procede con el flujo de guardado
        Estudiante nuevoEstudiante = estudianteMapper.toEntity(requestDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(nuevoEstudiante);

        return estudianteMapper.toResponseDTO(estudianteGuardado);
    }
}
