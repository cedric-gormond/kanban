package com.telecom.kanban.service.impl;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Developers services implementation defines methods declared into {@link DeveloperService} interface.
 *
 * @author Cédric Gormond
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;


    /**
     * Returns a collections of {@link Developer} contained inside a {@link DeveloperRepository}
     *
     * @return a collection of all tasks
     */
    @Override
    public Collection<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }
}
