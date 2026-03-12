package com.evervc.api.edutech.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "modulos")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;

    private Integer orden; // Para ordenar al momento de mostrar los datos

    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;
}
