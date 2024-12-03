package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Equipo;
import org.edgar.webtareas.repository.EquipoRepository;
import org.edgar.webtareas.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final TrabajadorRepository trabajadorRepository;

    public EquipoService(EquipoRepository equipoRepository, TrabajadorRepository trabajadorRepository) {
        this.equipoRepository = equipoRepository;
        this.trabajadorRepository = trabajadorRepository;
    }
    public void save(Equipo equipo) {
        equipoRepository.save(equipo);
    }
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }
    public Object findById(Long equipoId) {
        return equipoRepository.findById(equipoId).orElse(null);
    }
}