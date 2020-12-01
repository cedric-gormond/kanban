package com.telecom.kanban.runner;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.dao.TaskStatusRepository;
import com.telecom.kanban.dao.TaskTypeRepository;
import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;
import com.telecom.kanban.domain.TaskType;
import com.telecom.kanban.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
@Profile("!test")
@Component
/**
 * This class implements {@link CommandLineRunner} and provides an initialization of the local Database by adding some
 * instances as developers, tasks, ...
 *
 * @author Cédric Gormond
 *
 * */
public class LoadDataBaseRunner implements CommandLineRunner {

    // Constant
    final public static Long TASK_STATUS_TODO_ID = (long)1;
    final public static Long TASK_STATUS_DOING_ID = (long)2;
    final public static Long TASK_STATUS_TEST_ID = (long)3;
    final public static Long TASK_STATUS_DONE_ID = (long)4;
    final public static Long TASK_TYPE_BUG_ID = (long)1;
    final public static Long TASK_TYPE_FEATURE_ID = (long)2;


    // Logger initialization
    private static final Logger logger = LoggerFactory.getLogger(LoadDataBaseRunner.class);

    //@Autowired // Injection Dependence : it create objet DeveloperRepository
    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private  TaskTypeRepository taskTypeRepository;

    @Autowired
    private  TaskRepository taskRepository;


    @Override
    public void run(String... args) throws Exception {
        logger.info("LOAD DB RUNNER : Start test runner");

        initDevelopers(developerRepository);
        initTaskTypes(taskTypeRepository);
        initTaskStatus(taskStatusRepository);
        initTask(taskRepository);

        logger.info("Display all developers");
        developerRepository.findAll().forEach((developer) -> {
            logger.info("Created {}", developer);
        });

        logger.info("Display all taskStatus");
        taskStatusRepository.findAll().forEach((taskStatus) -> {
            logger.info("Created {}", taskStatus);
        });

        logger.info("Display all taskType");
        taskTypeRepository.findAll().forEach((taskType) -> {
            logger.info("Created {}", taskType);
        });

        /*
        logger.info("Display all task");
        taskRepository.findAll().forEach((task) -> {
            logger.info("Created {}", task);
        });
        */


        logger.info("LOAD DB RUNNER : Ending test runner");
    }

    /**
     * Initialization of {@link DeveloperRepository} with 2 {@link Developer}
     *
     * @param developerRepository
     */
    private void initDevelopers(DeveloperRepository developerRepository) {
        Developer dev1 = new Developer();
        dev1.setEmail("cedric.gormond@gmail.com");
        dev1.setFirstname("Cédric");
        dev1.setLastname("Gormond");
        dev1.setPassword("password");
        dev1.setStartContract(LocalDate.of(2020, Month.NOVEMBER, 29));
        developerRepository.save(dev1);
        log.info(dev1 + " saved to database !");

        Developer dev2 = new Developer();
        dev2.setEmail("john.doe@gmail.com");
        dev2.setFirstname("John");
        dev2.setLastname("Doe");
        dev2.setPassword("password");
        dev2.setStartContract(LocalDate.of(2020, Month.NOVEMBER, 10));
        developerRepository.save(dev2);
        log.info(dev2 + " saved to database !");
    }

    /**
     * Initialization of {@link TaskStatusRepository} with 4 {@link TaskStatus} : TODO, DOING, TEST, LABEL
     *
     * @param taskStatusRepository
     */
    private void initTaskStatus(TaskStatusRepository taskStatusRepository) {

        TaskStatus todo = new TaskStatus(TASK_STATUS_TODO_ID, "TODO");
        taskStatusRepository.save(todo);


        TaskStatus doing = new TaskStatus(TASK_STATUS_DOING_ID, "DOING");
        taskStatusRepository.save(doing);

        TaskStatus test = new TaskStatus(TASK_STATUS_TEST_ID, "TEST");
        taskStatusRepository.save(test);

        TaskStatus done = new TaskStatus(TASK_STATUS_DONE_ID, "LABEL");
        taskStatusRepository.save(done);
    }

    /**
     *Initialization of {@link TaskTypeRepository} with 2 {@link TaskType} : FEATURE, BUG
     *
     * @param taskTypeRepository
     */
    private void initTaskTypes(TaskTypeRepository taskTypeRepository) {

        TaskType feature = new TaskType(TASK_TYPE_FEATURE_ID, "FEATURE");
        taskTypeRepository.save(feature);


        TaskType bug = new TaskType(TASK_TYPE_BUG_ID, "BUG");
        taskTypeRepository.save(bug);
    }

    /**
     *Initialization of {@link TaskTypeRepository} with 2 {@link TaskType} : FEATURE, BUG
     *
     * @param taskRepository
     */
    private void initTask(TaskRepository taskRepository) {

        Task task = new Task();
        task.setTitle("Task of Cedric Gormond");
        task.setCreated(LocalDate.now());
        task.setTitle("Task of Cedric Gormond");
        task.setNbHoursForecast(0);
        task.setNbHoursReal(0);
        task.addDeveloper(developerRepository.findById(1L).orElse(null));
        task.setType(taskTypeRepository.findById(1L).orElse(null));
        task.setStatus(taskStatusRepository.findById(1L).orElse(null));
        taskRepository.save(task);
        log.info(task + " saved to database.");
    }
}