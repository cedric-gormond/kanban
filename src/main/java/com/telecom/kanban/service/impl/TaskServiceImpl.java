package com.telecom.kanban.service.impl;

import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.dao.TaskStatusRepository;
import com.telecom.kanban.dao.TaskTypeRepository;
import com.telecom.kanban.domain.ChangeLog;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;
import com.telecom.kanban.domain.TaskType;
import com.telecom.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;


/**
 * Task services implementation defines methods declared into {@link TaskService} interface.
 *
 * @author Cédric Gormond
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    /**
     * Create a given {@link Task} if it doesn't already exist.
     *
     * @param task to create
     * @return {@link Task} created or null if it already exists
     */
    @Override
    @Transactional
    public Task createTask(Task task) {
        TaskStatus todo = this.findTaskStatus((long)1);
        task.setStatus(todo);
        task.setCreated(LocalDate.now());
        taskRepository.save(task);

        // (!taskRepository.existsById(task.getId())) ? this.taskRepository.save(task) : null;
        // Line above doesn't work because is doesn't have an ID
        return taskRepository.save(task);
    }

    /**
     * Delete a given {@link Task}. Checks if the task was correctly deleted by checking if it still exists.
     *
     * @param task {@link Task}
     * @return true if the given deleted otherwise false
     */
    @Override
    @Transactional
    public boolean deleteTask(Task task) {

        //task = this.taskRepository.save(task);

        this.taskRepository.delete(task);
        task.clearChangeLogs();

        return !taskRepository.existsById(task.getId());
    }

    /**
     * Returns a collections of {@link Task} contained inside a {@link TaskRepository}
     *
     * @return a collection of all tasks
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Returns a collections of {@link TaskType} contained inside a {@link TaskTypeRepository}
     *
     * @return a collection of all {@link TaskType}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<TaskType> findAllTaskTypes() {
        return taskTypeRepository.findAll();
    }

    /**
     * Returns a collections of {@link TaskStatus} contained inside a {@link TaskStatusRepository}
     *
     * @return a collection of all {@link TaskStatus}
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<TaskStatus> findAllTaskStatus() {
        return taskStatusRepository.findAll();
    }

    /**
     * Find a task by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link Task}
     */
    @Override
    @Transactional(readOnly = true)
    public Task findTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Find a {@link TaskStatus} by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link TaskStatus}
     */
    @Override
    @Transactional(readOnly = true)
    public TaskStatus findTaskStatus(Long id) {
        return this.taskStatusRepository.findById(id).orElse(null);
    }

    /**
     * Find a {@link TaskType} by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link TaskType}
     */
    @Override
    @Transactional(readOnly = true)
    public TaskType findTaskType(Long id) {
        return this.taskTypeRepository.findById(id).orElse(null);
    }

    /**
     * Find all {@link ChangeLog} for a given {@link ChangeLog} if these logs exist.
     *
     * @param task {@link Task}
     * @return Collection of {@link ChangeLog} if these logs exist, otherwise null
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<ChangeLog> findChangeLogsForTask(Task task) {
        Task foundTask = this.findTask(task.getId());
        return foundTask != null ? foundTask.getChangeLogs() : new HashSet<>();
    }

    /**
     * Change the {@link TaskStatus} of a given {@link Task} and a given {@link TaskStatus}
     *
     * @param task targeted {@link Task}
     * @param newStatus targeted {@link TaskStatus}
     *
     * @return {@link Task} with changed Status
     */
    @Override
    @Transactional
    public Task changeTaskStatus(Task task, TaskStatus newStatus) {

        task = this.taskRepository.save(task);
        task.setStatus(newStatus);

        // Logs
        ChangeLog changeLog = new ChangeLog();
        changeLog.setOccured(LocalDateTime.now());
        changeLog.setSourceStatus(task.getStatus());
        changeLog.setTargetStatus(newStatus);
        task.addChangeLog(changeLog);

        return task;
    }

}
