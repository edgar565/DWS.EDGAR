package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public Trabajador findById(Long trabajador_id) {
        return trabajadorRepository.findById(trabajador_id).orElse(null);
    }

    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    public void editTrabajador(Long trabajadorId, String nombre, int edad) {
        Trabajador trabajador = findById(trabajadorId);
        trabajador.setNombre(nombre);
        trabajador.setEdad(edad);
        trabajadorRepository.save(trabajador);
    }
}