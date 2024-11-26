package org.edgar.webtareas.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trabajador_id;
    private String nombre;
    private int edad;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    @OneToMany(mappedBy = "trabajador")
    private List<Tareas> tareas = new ArrayList<>();

}