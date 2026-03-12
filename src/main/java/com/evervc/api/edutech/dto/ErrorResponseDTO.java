package com.evervc.api.edutech.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
        String mensaje,
        Map<String, String> detalles, // Aquí irán los campos que fallaron en el @Valid
        LocalDateTime timestamp
) {}
