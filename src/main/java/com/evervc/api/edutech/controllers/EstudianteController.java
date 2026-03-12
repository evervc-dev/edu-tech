package com.evervc.api.edutech.controllers;

import com.evervc.api.edutech.audit.AuditarTiempoRespuesta;
import com.evervc.api.edutech.dto.EstudianteRequestDTO;
import com.evervc.api.edutech.dto.EstudianteResponseDTO;
import com.evervc.api.edutech.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    @AuditarTiempoRespuesta
    public ResponseEntity<EstudianteResponseDTO> crearEstudiante(
            @Valid @RequestBody EstudianteRequestDTO request) {

        // Si el email ya existe, el Service lanzará la excepción que el ControllerAdvice atrapa automáticamente.
        EstudianteResponseDTO respuesta = estudianteService.crearEstudiante(request);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
