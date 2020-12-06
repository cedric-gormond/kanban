package com.telecom.kanban;

import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.domain.*;
import com.telecom.kanban.service.DeveloperService;
import com.telecom.kanban.service.TaskService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;



@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j //logger
@AutoConfigureMockMvc
/**
 * Performs JUnit test for {@link Task} and {@link TaskService}.
 *
 * @author Cédric GORMOND
 */
public class TaskTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private DeveloperService developerService;

    /**
     * Testing {@link #log}
     *
     * This testing method is useless.
     */
    @Test
    public void monTest(){
        log.debug("MyTest starts !");

        // testing stuff

        log.debug("MyTest ends !");
    }

    /**
     *Testing the adding of a {@link Developer} to a {@link Task}
     */
    @Test
    public void testAddDeveloper() {
        Task task = new Task();
        Developer developer = new Developer();

        task.addDeveloper(developer);

        Assert.assertEquals(1, task.getDevelopers().size());
    }

    /**
     *Testing the creation of a {@link Task}
     */
    @Test
    public void testCreateTask() {

        Developer developer = this.developerService.findAllDevelopers().iterator().next();
        TaskStatus todoStatus = this.taskService.findTaskStatus(1l);

        Task task = new Task();
        task.setNbHoursForecast(0);
        task.setNbHoursReal(0);
        task.setTitle("title create task");
        task.setStatus(todoStatus);
        task.addDeveloper(developer);

        Assert.assertEquals(task, this.taskService.createTask(task));

        this.taskService.deleteTask(task);
    }

    /**
     * Testing the creation of a {@link Task} by creating one and deleting afterward. In this way, the size of the
     * repository must be equal to 1.
     */
    @Test
    public void testDeleteTask() {

        Developer developer = this.developerService.findAllDevelopers().iterator().next();
        TaskStatus todoStatus = this.taskService.findTaskStatus(1l);

        // Task instance
        Task task = new Task();
        task.setNbHoursForecast(0);
        task.setNbHoursReal(0);
        task.setTitle("Task to delete");
        task.setStatus(todoStatus);
        task.addDeveloper(developer);

        // create a Task in the service
        this.taskService.createTask(task);

        // Delete it : TODO je ne comprends pas l'erreur
        Assert.assertEquals(1, this.taskService.findAllTasks());
    }

    /**
     * Testing if {@link TaskService} returns a collection with a single {@link Task}.
     *
     * The task was loaded thanks to {@link com.telecom.kanban.runner.LoadDataBaseRunner} class.
     */
    @Test
    public void testFindAllTasks() {
        Collection<Task> tasks = this.taskService.findAllTasks();
        Assert.assertEquals(1, tasks.size());
    }

    /**
     * Testing if {@link TaskService} returns a collection with a 2 {@link TaskType} : BUG, FEATURE
     *
     * They were loaded thanks to {@link com.telecom.kanban.runner.LoadDataBaseRunner} class.
     */
    @Test
    public void testFindAllTaskTypes() {
        Collection<TaskType> taskTypes = this.taskService.findAllTaskTypes();
        Assert.assertEquals(2, taskTypes.size());
    }

    /**
     * Testing if {@link TaskService} returns a collection with a 4 {@link TaskStatus} : TODO, DOING, TEST, LABEL
     *
     * They were loaded thanks to {@link com.telecom.kanban.runner.LoadDataBaseRunner} class.
     */
    @Test
    public void testFindAllTaskStatus() {
        Collection<TaskStatus> taskStatus = this.taskService.findAllTaskStatus();
        Assert.assertEquals(4, taskStatus.size());
    }

    /**
     * Testing if we can correctly change the {@link TaskStatus} of a {@link Task} by looking to any change in task and
     * at the logs.
     */
    @Test
    public void testChangeTaskStatus() {

        Task task = this.taskService.findAllTasks().iterator().next();
        TaskStatus taskStatus1 = this.taskService.findTaskStatus((long)1);
        TaskStatus taskStatus2 = this.taskService.findTaskStatus((long)2);

        // Changing status
        task = this.taskService.changeTaskStatus(task, taskStatus2);
        Collection<ChangeLog> changeLogs = this.taskService.findChangeLogsForTask(task);

        // testing by looking task
        Assert.assertEquals(taskStatus2, task.getStatus());

        /*
        // testing by looking logs
        Assert.assertEquals(1, changeLogs.size());

        ChangeLog changeLog = changeLogs.iterator().next();
        Assert.assertEquals(taskStatus1, changeLog.getSourceStatus());
        Assert.assertEquals(taskStatus2, changeLog.getTargetStatus());
        */
    }

    @Test
    public void testMoveRightTask() {

        TaskStatus todoStatus  = this.taskService.findTaskStatus(1l);
        TaskStatus doingStatus = this.taskService.findTaskStatus(2l);
        TaskStatus testStatus  = this.taskService.findTaskStatus(3l);
        TaskStatus doneStatus  = this.taskService.findTaskStatus(4l);

        Task task = new Task();
        task.setNbHoursForecast(0);
        task.setNbHoursReal(0);
        task.setTitle("title");
        task.setStatus(todoStatus); // Initializataton with to do status
        task.addDeveloper(this.developerService.findAllDevelopers().iterator().next());
        task = this.taskService.createTask(task);

        // Move right : to do -> doing
        task = this.taskService.moveRightTask(task);
        Assert.assertEquals(doingStatus, task.getStatus());

        // Move right : doing -> test
        task = this.taskService.moveRightTask(task);
        Assert.assertEquals(testStatus, task.getStatus());

        // Move right : doing -> done
        task = this.taskService.moveRightTask(task);
        Assert.assertEquals(doneStatus, task.getStatus());

        // Move right : done -> null
        task = this.taskService.moveRightTask(task);
        Assert.assertEquals(null, task.getStatus());

        this.taskService.deleteTask(task);
    }

    @Test
    public void testMoveLeftTask() {

        TaskStatus todoStatus  = this.taskService.findTaskStatus(1l);
        TaskStatus doingStatus = this.taskService.findTaskStatus(2l);
        TaskStatus testStatus  = this.taskService.findTaskStatus(3l);
        TaskStatus doneStatus  = this.taskService.findTaskStatus(4l);

        Task task = new Task();
        task.setNbHoursForecast(0);
        task.setNbHoursReal(0);
        task.setTitle("title");
        task.setStatus(doneStatus);
        task.addDeveloper(this.developerService.findAllDevelopers().iterator().next());
        task = this.taskService.createTask(task);

        // Move right : done -> test
        task = this.taskService.moveLeftTask(task);
        Assert.assertEquals(testStatus, task.getStatus());

                /*
        // Move right : test -> doing
        task = this.taskService.moveLeftTask(task);
        Assert.assertEquals(doingStatus, task.getStatus());

        // Move right : doing -> to do
        task = this.taskService.moveLeftTask(task);
        Assert.assertEquals(todoStatus, task.getStatus());

        // Move right : done -> null
        task = this.taskService.moveLeftTask(task);
        Assert.assertEquals(null, task.getStatus());
        */
        this.taskService.deleteTask(task);
    }

}
