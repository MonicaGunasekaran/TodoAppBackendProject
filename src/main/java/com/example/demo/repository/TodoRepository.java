package com.example.demo.repository;

import com.example.demo.model.TodoModel;
import com.example.demo.projection.PendingTasksOnDue;
import com.example.demo.projection.ProjectionInterface;
import com.example.demo.projection.SortByPendingTasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
public interface TodoRepository extends JpaRepository<TodoModel, UUID> {

    @Query(
        value = "SELECT task_name as taskName,completed as completed, created_at as createdAt,due_date_time as dueDateTime FROM todo WHERE due_date_time < NOW()",
        nativeQuery = true
    )
    List<ProjectionInterface> findExpiredTodos();
    @Query(
    	    value ="SELECT t.task_name AS taskName, t.due_date_time AS dueDateTime, t.created_at AS createdAt " +
    	            "FROM todo t " +
    	            "WHERE (:status IS NULL OR t.completed = :status) " +
    	            "AND (CAST(:start AS timestamp) IS NULL OR t.created_at >= :start) " +
    	            "AND (CAST(:end AS timestamp) IS NULL OR t.created_at <= :end) " +
    	            "AND (CAST(:due AS timestamp) IS NULL OR t.due_date_time = :due)",
    	    nativeQuery = true
    	)
    	List<PendingTasksOnDue> findTodosByConditions(
    	        Boolean status,
    	        LocalDateTime start,
    	        LocalDateTime end,
    	        LocalDate due
    	);

    
    @Query(
    		value="select t.task_name as taskName, t.created_at as createdAt "+
    		"from todo t "+
    		"order by created_at desc ",
    		nativeQuery=true
    		)
    List<SortByPendingTasks> findTasksSorted();
    
    @Query(
    		value="select t.task_name as taskName, t.created_at as createdAt "+
    		"from todo t "+
    		"where t.completed= false order by due_date_time desc ",
    		nativeQuery=true
    		)
    List<PendingTasksOnDue> findDueByOrder();
    //i am from monica branch, the same feature has been changed as per the demo branch
    @Query(
    	    value="SELECT * FROM todo ORDER BY created_at DESC",
    	    nativeQuery=true
    	)
    	List<TodoModel> findTasksNew();

  
}