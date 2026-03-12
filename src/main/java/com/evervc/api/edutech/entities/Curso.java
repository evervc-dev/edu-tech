package com.evervc.api.edutech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;

    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    // Al guardar el curso, se guardan todos los módulos que le pertenezcan
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "curso")
    private List<Modulo> modulos = new ArrayList<>();
}
