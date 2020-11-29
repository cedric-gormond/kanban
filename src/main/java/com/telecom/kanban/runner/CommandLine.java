package com.telecom.kanban.runner;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.domain.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
/**
 * This class implements {@link CommandLineRunner} and provides an initialization of the local Database by adding some
 * instances as developers, tasks, ...
 *
 * @author Cédric Gormond
 *
 * */
public class CommandLine implements CommandLineRunner {
    // Logger initialization
    private static final Logger logger = LoggerFactory.getLogger(CommandLine.class);

    @Autowired // Injection Dependence : it create objet DeveloperRepository
    private DeveloperRepository repository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Start test runner");

        repository.deleteAll();

        // Developers
        repository.save(new Developer( "Cedric", "Gormond", "exemple@gmail.com", "password", LocalDate.of(2020, 10, 27) ));
        repository.save(new Developer( "Joe", "Doe", "joe.doe@gmail.com", "password", LocalDate.of(2020, 10, 27) ));
        repository.save(new Developer( "Foo", "Doe", "foo.doe@gmail.com", "password", LocalDate.of(2020, 10, 27) ));

        repository.findAll().forEach((developper) -> {
            logger.info("Created {}", developper);
        });

        logger.info("Ending test runner");
    }
}