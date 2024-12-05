package org.edgar.webtareas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tarea_id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private String tipo;
    private String estado;

    @ManyToMany(mappedBy = "tareas")  // Indicamos el atributo de la clase Author que se usará para la relación
    private Set<Trabajador> trabajadores = new HashSet<>();  // Atibuto de tipo Set para almacenar los autores

}