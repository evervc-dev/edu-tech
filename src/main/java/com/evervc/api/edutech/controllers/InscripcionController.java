package com.evervc.api.edutech.controllers;

import com.evervc.api.edutech.dto.InscripcionRequestDTO;
import com.evervc.api.edutech.dto.InscripcionResponseDTO;
import com.evervc.api.edutech.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<InscripcionResponseDTO> inscribirEstudiante(
            @Valid @RequestBody InscripcionRequestDTO request) {

        InscripcionResponseDTO respuesta = inscripcionService.inscribirEstudiante(request);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
