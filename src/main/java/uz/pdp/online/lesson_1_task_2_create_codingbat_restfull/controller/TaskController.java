package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Task;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.TaskDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse> addTask(@Validated @RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Task taskById = taskService.getTaskById(id);
        return ResponseEntity.ok(taskById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTask(@PathVariable Integer id, @Validated @RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.editTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id) {
        ApiResponse apiResponse = taskService.deleteTask(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
