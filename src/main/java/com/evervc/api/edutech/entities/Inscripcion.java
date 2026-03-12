package com.evervc.api.edutech.entities;

import com.evervc.api.edutech.enums.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING) // Para que guarde literalmente el texto ("ACTIVO", "COMPLETADO", "CANCELADO")
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estudiante estudiante;
}
