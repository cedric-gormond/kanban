package com.telecom.kanban.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
/**
 * This class represents a task's status
 *
 * @author Cédric Gormond
 */
public class TaskStatus {

    private @Id Long id;

    private String label;

    public TaskStatus(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public TaskStatus() {
    }
}