package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Example;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ExampleDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service.ExampleService;

import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @PostMapping
    public ResponseEntity<ApiResponse> addExample(@Validated @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Example>> getExamples() {
        List<Example> examples = exampleService.getExamples();
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Example> getExampleById(@PathVariable Integer id) {
        Example exampleById = exampleService.getExampleById(id);
        return ResponseEntity.ok(exampleById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editExample(@PathVariable Integer id, @Validated @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExample(@PathVariable Integer id) {
        ApiResponse apiResponse = exampleService.deleteExample(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
