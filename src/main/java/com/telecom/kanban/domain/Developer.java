package com.telecom.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
/**
 * Class which represents a developers by their names, email, password and a local date.
 *
 * @author Cedric Gormond
 */
public class Developer {

    private @Id @GeneratedValue Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private LocalDate startContract;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy="developers", fetch=FetchType.EAGER)
    private Set<Task> tasks;

    public Developer() {
        this.tasks = new HashSet<>();
    }

    public Developer(String firstname, String lastname, String email, String password, LocalDate startContract) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.startContract = startContract;
        this.tasks = new HashSet<>();
    }
}

