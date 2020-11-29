package com.telecom.kanban.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
/**
 * This class represents the type of a task
 *
 * @author Cédric Gormond
 */
public class TaskType {

    @NonNull
    private @Id Long id;

    private String label;

    public TaskType(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public TaskType() {
    }
}