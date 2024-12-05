package org.edgar.webtareas.repository;

import org.edgar.webtareas.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository<Tarea, Long> {
}
