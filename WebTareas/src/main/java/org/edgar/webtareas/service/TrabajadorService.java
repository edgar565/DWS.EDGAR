package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public Trabajador getTrabajador(int trabajador_id) {
        return trabajadorRepository.findById(trabajador_id).orElse(null);
    }
}