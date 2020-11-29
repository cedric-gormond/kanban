package com.telecom.kanban.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
/**
 * This class provides logs for any changes
 *
 * @author Cédric Gormond
 */
public class ChangeLog {

    private @Id @GeneratedValue Long id;

    private LocalDateTime occured;

    @ManyToOne
    @ToString.Exclude
    private Task task;

    @ManyToOne
    private TaskStatus targetStatus;

    @ManyToOne
    private TaskStatus sourceStatus;

    public ChangeLog() {
    }
}

