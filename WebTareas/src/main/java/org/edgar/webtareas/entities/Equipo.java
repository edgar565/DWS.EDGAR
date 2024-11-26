package org.edgar.webtareas.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipo_id;

    @OneToMany(mappedBy = "equipo")
    private List<Trabajador> trabajadores = new ArrayList<>();
}