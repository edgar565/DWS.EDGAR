package org.edgar.webtareas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tareas")
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tarea_id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private String tipo;
    private String estado;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

}