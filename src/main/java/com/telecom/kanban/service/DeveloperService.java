package com.telecom.kanban.service;

import com.telecom.kanban.domain.Developer;

import java.util.Collection;
import java.util.List;

/**
 * Interface which declares some {@link DeveloperService} methods
 *
 * @author Cédric Gormond
 */
public interface DeveloperService {

    /**
     * Find and return all developers
     *
     * @return a collection of {@link Developer}
     */
    public Collection<Developer> findAllDevelopers();

    /**
     * Find a developper by an ID
     * @param id
     * @return
     */
    public Developer findByIDDeveloper(Long id);
}
