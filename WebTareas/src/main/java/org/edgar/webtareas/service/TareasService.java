package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Tarea;
import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.repository.TareasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareasService {

    private final TareasRepository tareasRepository;

    public TareasService(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    public List<Tarea> findAll() {
        return tareasRepository.findAll();
    }

    public Object findById(Long tareaId) {
        return tareasRepository.findById(tareaId).orElse(null);
    }

    public void save(Tarea tarea) {
        tareasRepository.save(tarea);
    }

    public void editTarea(Long tareaId, Tarea tarea) {
        Tarea tarea1 = tareasRepository.findById(tareaId).orElse(null);
        tarea1.setTrabajadores(tarea.getTrabajadores());
        tarea1.setDescripcion(tarea.getDescripcion());
        tarea1.setNombre(tarea.getNombre());
        tarea1.setFechaApertura(tarea.getFechaApertura());
        tarea1.setFechaCierre(tarea.getFechaCierre());
        tarea1.setTipo(tarea.getTipo());
        tarea1.setEstado(tarea.getEstado());
        tareasRepository.save(tarea1);
    }
}
