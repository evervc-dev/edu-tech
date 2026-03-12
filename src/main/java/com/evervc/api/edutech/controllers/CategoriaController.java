package com.evervc.api.edutech.controllers;

import com.evervc.api.edutech.dto.CategoriaRequestDTO;
import com.evervc.api.edutech.dto.CategoriaResponseDTO;
import com.evervc.api.edutech.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(
            @Valid @RequestBody CategoriaRequestDTO request) {

        CategoriaResponseDTO respuesta = categoriaService.crearCategoria(request);
        // 201 Created (estándar correcto para una inserción exitosa)
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
