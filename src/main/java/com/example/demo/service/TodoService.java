package com.example.demo.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import com.example.demo.model.TodoModel;
import com.example.demo.projection.PendingTasksOnDue;
import com.example.demo.projection.ProjectionInterface;
import com.example.demo.projection.SortByPendingTasks;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoModel createTodo(TodoModel todo) {
        return todoRepository.save(todo);
    }

    public TodoModel updateTodo(UUID id, TodoModel updatedTodo) {
        TodoModel existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        existingTodo.setTaskName(updatedTodo.getTaskName());
        existingTodo.setCompleted(updatedTodo.getCompleted());
        existingTodo.setDueDateTime(updatedTodo.getDueDateTime());

        return todoRepository.save(existingTodo);
    }
    public TodoModel updateTodoByTaskName(String task_name, TodoModel updatedTodo) {
        TodoModel existingTodo = (todoRepository).findByTaskName(task_name)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + task_name));

        existingTodo.setTaskName(updatedTodo.getTaskName());
        existingTodo.setCompleted(updatedTodo.getCompleted());
        existingTodo.setDueDateTime(updatedTodo.getDueDateTime());

        return todoRepository.save(existingTodo);
    }
    public List<ProjectionInterface> getExpiredTodos() {
        return todoRepository.findExpiredTodos();
    }

    public List<PendingTasksOnDue> findTodosByConditions(
            String status,
            String start,
            String end,
            String due
    ) {

        Boolean completedStatus = (status != null) ? Boolean.parseBoolean(status) : null;

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        LocalDate dueDate = null;

        if (start != null && !start.isBlank()) {
            startDate = LocalDateTime.parse(start + "T00:00:00");
        }

        if (end != null && !end.isBlank()) {
            endDate = LocalDateTime.parse(end + "T23:59:59");
        }

        if (due != null && !due.isBlank()) {
            dueDate = LocalDate.parse(due);
        }

        return todoRepository.findTodosByConditions(
                completedStatus,
                startDate,
                endDate,
                dueDate
        );
    }
    public List<SortByPendingTasks> getTasksSorted() {
        return todoRepository.findTasksSorted();
    }
    public List<PendingTasksOnDue> getDueTasksDesc(){
    	return todoRepository.findDueByOrder();
    }
    public int getPendingTasks() {
    	return todoRepository.pendingTasksCount();
    }


}
