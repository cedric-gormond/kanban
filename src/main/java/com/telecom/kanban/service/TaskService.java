package com.telecom.kanban.service;

import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.dao.TaskStatusRepository;
import com.telecom.kanban.dao.TaskTypeRepository;
import com.telecom.kanban.domain.ChangeLog;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;
import com.telecom.kanban.domain.TaskType;

import java.util.Collection;

/**
 * Interface which declares some {@link TaskService} methods
 *
 * @author Cédric Gormond
 */
public interface TaskService {

    /**
     * Create a given {@link Task} if it doesn't already exist.
     *
     * @param task to create
     * @return {@link Task} created or null if it already exists
     */
    public Task createTask(Task task);

    /**
     * Delete a given {@link Task}
     *
     * @param {@link Task}
     * @return true if deleted otherwise false
     */
    public boolean deleteTask(Task task);

    /**
     * Returns a collections of {@link Task} contained inside a {@link TaskRepository}
     *
     * @return a collection of all tasks
     */
    public Collection<Task> findAllTasks();

    /**
     * Returns a collections of {@link TaskType} contained inside a {@link TaskTypeRepository}
     *
     * @return a collection of all {@link TaskType}
     */
    public Collection<TaskType> findAllTaskTypes();

    /**
     * Returns a collections of {@link TaskStatus} contained inside a {@link TaskStatusRepository}
     *
     * @return a collection of all {@link TaskStatus}
     */
    public Collection<TaskStatus> findAllTaskStatus();

    /**
     * Find a task by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link Task}
     */
    public Task findTask(Long id);

    /**
     * Find a {@link TaskStatus} by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link TaskStatus}
     */
    public TaskStatus findTaskStatus(Long id);

    /**
     * Find a {@link TaskType} by a given id if it exists, otherwise returns null.
     *
     * @param id (long)
     * @return a {@link TaskType}
     */
    public TaskType findTaskType(Long id);

    /**
     * Find all {@link ChangeLog} for a given {@link ChangeLog} if these logs exist.
     *
     * @param task {@link Task}
     * @return Collection of {@link ChangeLog} if these logs exist, otherwise null
     */
    public Collection<ChangeLog> findChangeLogsForTask(Task task);

    /**
     * Change the {@link TaskStatus} of a given {@link Task} and a given {@link TaskStatus}
     *
     * @param task targeted {@link Task}
     * @param newStatus targeted {@link TaskStatus}
     *
     * @return {@link Task} with changed Status
     */
    public Task changeTaskStatus(Task task, TaskStatus newStatus);
}
