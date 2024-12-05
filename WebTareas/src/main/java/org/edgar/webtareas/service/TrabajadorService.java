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

    public void editTrabajador(Long trabajadorId, Trabajador trabajador) {
        Trabajador trabajador1 = findById(trabajadorId);
        trabajador1.setNombre(trabajador.getNombre());
        trabajador1.setEdad(trabajador.getEdad());
        trabajadorRepository.save(trabajador1);
    }
    public void save(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
    }
}