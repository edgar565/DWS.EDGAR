package org.edgar.webtareas.service;

import org.edgar.webtareas.entities.Tarea;
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
}
