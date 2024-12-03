package org.edgar.webtareas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long equipo_id;
    private String nombre;

    @OneToMany(mappedBy = "equipo")
    private Set<Trabajador> trabajadores = new HashSet<>();

}