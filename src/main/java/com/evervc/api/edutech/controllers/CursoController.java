package com.evervc.api.edutech.controllers;

import com.evervc.api.edutech.dto.CursoRequestDTO;
import com.evervc.api.edutech.dto.CursoResponseDTO;
import com.evervc.api.edutech.services.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> crearCurso(
            @Valid @RequestBody CursoRequestDTO request) {

        CursoResponseDTO respuesta = cursoService.crearCurso(request);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> obtenerCursoPorId(@PathVariable Long id) {
        // Busca el recurso en la db a través del service
        CursoResponseDTO respuesta = cursoService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<CursoResponseDTO>> obtenerTodosLosCursos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CursoResponseDTO> respuesta = cursoService.obtenerTodosLosCursos(page, size);

        // Retorna 200 OK, lectura exitosa
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);

        // Retorna 204 No Content, (estándar HTTP para cuando algo se eliminó con éxito y no hay cuerpo JSON que devolver)
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<CursoResponseDTO> reactivarCurso(@PathVariable Long id) {
        CursoResponseDTO respuesta = cursoService.reactivarCurso(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> actualizarCurso(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequestDTO request
    ) {

        CursoResponseDTO respuesta = cursoService.actualizarCurso(id, request);
        return ResponseEntity.ok(respuesta);
    }
}
