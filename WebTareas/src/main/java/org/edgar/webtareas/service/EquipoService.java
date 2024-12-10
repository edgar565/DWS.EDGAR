package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Equipo;
import org.edgar.webtareas.repository.EquipoRepository;
import org.edgar.webtareas.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }
    public void save(Equipo equipo) {
        equipoRepository.save(equipo);
    }
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }
    public Equipo findById(Long equipoId) {
        return equipoRepository.findById(equipoId).orElse(null);
    }

    public void editEquipo(Long equipoId, Equipo equipo) {
        Equipo equipo1 = equipoRepository.findById(equipoId).orElse(null);
        equipo1.setNombre(equipo.getNombre());
        equipo1.setTrabajadores(equipo.getTrabajadores());
        equipoRepository.save(equipo1);
    }
}