package com.telecom.kanban.dao;

import com.telecom.kanban.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao : Interface afin d'acceder à la BDD => repository sert à faire des requetes a notre place
 * Domain : Pour écrire dans la BDD
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
