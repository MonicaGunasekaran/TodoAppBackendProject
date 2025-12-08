package com.example.demo.controller;

import com.example.demo.model.TodoModel;
import com.example.demo.projection.PendingTasksOnDue;
import com.example.demo.projection.ProjectionInterface;
import com.example.demo.projection.SortByPendingTasks;
import com.example.demo.service.TodoService;
import com.example.demo.ResponseEntity.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public Object createTodo(@RequestBody TodoModel todo) {
        TodoModel createdTodo = todoService.createTodo(todo);
        return Response.success("Todo created successfully", createdTodo);
    }
   
    @GetMapping("/expired")
    public ResponseEntity<?> getExpiredTodos() {
        List<ProjectionInterface> expiredTodos = todoService.getExpiredTodos();

        return ResponseEntity.ok(expiredTodos);
    }
    @PutMapping("/update/{id}")
    public Object updateTodo(@PathVariable UUID id, @RequestBody TodoModel updatedTodo) {
        TodoModel todo = todoService.updateTodo(id, updatedTodo);
        return Response.success("Todo updated successfully", todo);
    }
    @GetMapping("/filter")
    public Object findTodoByConditions(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String due
    ) {

        List<PendingTasksOnDue> result =
                todoService.findTodosByConditions(status, start, end, due);

        return Response.success("Filtered todos", result);
    }


    @GetMapping("/createdSorted")
    public Object getCompletedTasks() {
        List<SortByPendingTasks> tasks = todoService.getTasksSorted();
        return Response.success("Completed tasks sorted by createdAt DESC", tasks);
    }
    @GetMapping("/getPendingDesc")
    public Object getPendingSorted() {
    	List<PendingTasksOnDue> pending=todoService.getDueTasksDesc();
    	return Response.success("Pending Tasks on Due ordered by descending order", pending);
    }
    @GetMapping("/pendingTasksCount")
    public Object getCountOfPendingTasks() {
    	int pendingCount=todoService.getPendingTasks();
    	return Response.success("Total Number of Pending Tasks",pendingCount);
    }
 
}
