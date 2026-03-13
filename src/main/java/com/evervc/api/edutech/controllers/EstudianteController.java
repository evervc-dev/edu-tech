package com.evervc.api.edutech.controllers;

import com.evervc.api.edutech.audit.AuditarTiempoRespuesta;
import com.evervc.api.edutech.dto.EstudianteRequestDTO;
import com.evervc.api.edutech.dto.EstudianteResponseDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;
import com.evervc.api.edutech.services.EstudianteService;
import com.evervc.api.edutech.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final InscripcionService inscripcionService;

    @PostMapping
    @AuditarTiempoRespuesta
    public ResponseEntity<EstudianteResponseDTO> crearEstudiante(
            @Valid @RequestBody EstudianteRequestDTO request) {

        // Si el email ya existe, el Service lanzará la excepción que el ControllerAdvice atrapa automáticamente.
        EstudianteResponseDTO respuesta = estudianteService.crearEstudiante(request);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerCursosInscritos(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.cursosInscritosPorIdEstudiante(id));
    }
}
