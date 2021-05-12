package uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.entity.Answer;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.AnswerDto;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_2_create_codingbat_restfull.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<ApiResponse> addAnswer(@Validated @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.getAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Integer id) {
        Answer answerById = answerService.getAnswerById(id);
        return ResponseEntity.ok(answerById);
    }
}
