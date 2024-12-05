package org.edgar.webtareas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "trabajadores")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trabajador_id;
    private String nombre;
    private int edad;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    @ManyToMany
    @JoinTable(
            name = "trabajador_tareas",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "trabajador_id"),  // Columna que hace referencia al autor
            inverseJoinColumns = @JoinColumn(name = "tarea_id")  // Columna que hace referencia a la editorial
    )
    private Set<Tarea> tareas = new HashSet<>();  // Relación con editoriales

}